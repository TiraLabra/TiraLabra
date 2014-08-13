{-# LANGUAGE FlexibleInstances #-}
------------------------------------------------------------------------------
-- |
-- Module         : Mahjong.Hand.Algo
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- Algorithms to work with mahjong hands.
------------------------------------------------------------------------------
module Mahjong.Hand.Algo
    (
    -- * Shanten
    ShantenOf(..), Shanten,

    -- * Waits
    buildGreedyWaitTree,
    buildGreedyWaitTree',
    minDepth, levels,

    -- * Tile grouping
    tilesGroupL, tilesSplitGroupL,
    leftovers, waits, tileGroupTiles,

    -- * Types
    WaitTree, Wait, Grouping,
    TileGroup(..),

    -- * Misc
    devops
    ) where

import           Control.Monad
import           Control.Applicative
import           Data.Maybe
import           Data.Bifunctor
import           Data.List (delete, sort, foldl', find, groupBy)
import           Data.List.HT (removeEach)
import qualified Data.List.NonEmpty as NE

import Mahjong.Hand.Algo.WaitTree as Mahjong.Hand.Algo
import Mahjong.Hand.Mentsu
import Mahjong.Hand.Value
import Mahjong.Tiles

-- Types

-- | Right for shuntsu wait, Left for koutsu wait or ready pair
type Wait = Either Tile [Tile]

-- | A single grouping variant.
type Grouping = [TileGroup]

-- | Data type used to describe some group of tiles.
data TileGroup = GroupWait MentsuKind [Tile] [Tile]
                -- ^ A @MentsuWait kind Inhand waits@ describes a wait on
                -- any of the tiles `waits` for a mentsu of kind `kind`
                -- with tiles `inhand` already in hand.
                | GroupComplete Mentsu
                -- ^ Note that jantou (the pair) are viewed as koutsu
                -- waits. It's pretty logical when you think about it.
                | GroupLeftover Tile
                -- ^ A leftover tile which cannot be associated with any
                -- other tile.
                deriving (Show, Read, Eq, Ord)

-- | A nothing result means that the hand has 14 (or more) tiles but is not
-- complete. Thus, invalid.
type Shanten = Maybe Int

-- | Non-recursive helper type for the unfolder below.
--
-- Left for ready with, Right for (discarding, getting, stray tiles, waits).
type DevOp' = Either Tile (Tile, Tile, [Tile], [Wait])

-- Tiles split

-- | `tilesGroupL` simply returns all possible groupings for the tiles
--
-- The algorithm goes through the input list of tiles (after sorting it)
-- trying to use the first tile (head of the list) to form:
--
--  1. an ankan
--  2. an ankou
--  3. an ankou wait
--  4. a shuntsu
--  5. a shuntsu wait with its successor
--  6. a shuntsu wait the successor's successor (middle wait), or
--  7. discard the tile as a `GroupLeftOver`.
--
-- Whenever any of these are possible, the result is cons'ed to every
-- result from applying the function recurively on the remaining tiles.
-- The end result is a list of all possible groups.
--
-- = Performance concerns and optimizations
--
-- Complete mentsu are always preferred over incomplete (and incomplete
-- over leftovers) and are placed at the head of the list /on that level of
-- recursion/.  However, there is no guarentee that minimal groupings
-- couldn't reside in the result's tail.
tilesGroupL :: [Tile] -> [Grouping]
tilesGroupL = go . sort
    where
        go []       = [ [] ]
        go [x]      = [ [GroupLeftover x] ]
        go (x:y:xs) = takeKantsu ++ takeTriplet ++ takePair ++ takeShuntsu ++ dropOne
            where
                takePair
                    | x == y                            = GroupWait Koutsu [x, x] [x] `goWith` xs
                    | otherwise                         = []

                takeTriplet
                    | (z:xs') <- xs, x == z              = GroupComplete (koutsu x) `goWith` xs'
                    | otherwise                         = []

                takeKantsu
                    | (z:w:xs') <- xs, x == z, z == w    = GroupComplete (kantsu x) `goWith` xs'
                    | otherwise                         = []

                takeShuntsu = concat $ catMaybes
                    [ ts_complete    <$> my <*> mz
                    , ts_sequentical <$> my
                    , ts_inbetween   <$> mz
                    ] where
                        my = succMay x >>= \y' -> y' <$ guard (y' `elem` y:xs)
                        mz = succMay x >>= succMay >>= \z -> z <$ guard (z `elem` y:xs)

                ts_complete y' z  = GroupComplete (shuntsu x) `goWith` delete y' (delete z (y:xs))
                ts_sequentical y' = GroupWait Shuntsu [x,y'] (catMaybes [predMay x, succMay y']) `goWith` delete y' (y:xs)
                ts_inbetween   z  = GroupWait Shuntsu [x,z] [fromJust (succMay x)] `goWith` delete z (y:xs)

                dropOne = GroupLeftover x `goWith` (y:xs)

                goWith a = map (a :) . go 

-- | A follow-up optimization on `tilesGroupL` that first groups the input
-- tiles by their suit or honor kind, then feeds the groups separetely
-- to `tilesGroupL` and finally joins the results.
tilesSplitGroupL :: [Tile] -> [Grouping]
tilesSplitGroupL = combine . map tilesGroupL . groupBy compareKind . sort
    where
        a `compareKind` b = tileKind a == tileKind b

        -- | outer list : tile kinds
        --   middle list : tilegroup combinations
        --   inner list : tilegroups for the combination all of the same tile kind
        combine :: [[[a]]] -> [[a]]
        combine       [] = [[]]
        combine (tk:tks) = [ xs ++ ys | xs <- tk, ys <- combine tks ]

-- Shanten

-- | We use a type class to allow calculating shanten (tiles away from
-- tenpai) from different representations.
--
-- Note that instances strictly assume valid input: 13 or 14 tile hands, or
-- including kan in ([Mentsu], [Tile]) instance.
--
-- /Technical details./ Even though the subtract algorithm gives -1 for all
-- complete hands, not all -1 indicate a complete hand. Consider for
-- example a hand with 4x complete melds and tiles of a shuntsu wait: the
-- algorithm thinks this is a complete hand when in fact the pair is
-- missing. Therefore we check the condition that the pair is there and
-- then return @(Just -1)@. In the case of an invalid complete hand we
-- return `Nothing`.
class ShantenOf x where
    shanten :: x -> Shanten

-- | From functions like `tilesSplitGroupL`.  Result is minimum of the
-- sublists.
instance ShantenOf [Grouping] where
    shanten tgs = case mapMaybe shanten tgs of
                      [] -> Nothing
                      xs -> Just $ minimum xs

instance ShantenOf Grouping where
    shanten = tgShanten 8

-- | Uses `tilesGroupL`
instance ShantenOf [Tile] where
    shanten = shanten . tilesGroupL

-- | Regard the mentsu as fixed and subtract.
instance ShantenOf ([Mentsu], [Tile]) where
    shanten (ms, ts) = case mapMaybe (tgShanten (8 - 2 * length ms)) (tilesGroupL ts) of
                           [] -> Nothing
                           xs -> Just $ minimum xs

-- | @tgShanten n tgs@ calculates shanten of `tgs` using the subtract
-- algorithm starting at `n`.
tgShanten :: Int -> Grouping -> Shanten
tgShanten n tgs = case foldl' (\i -> (i -) . tgval) n tgs of
                    -1 | Nothing <- find isKoutsuWait tgs -> Nothing
                    s                                    -> Just s
    where
        tgval (GroupWait{})     = 1
        tgval (GroupComplete{}) = 2
        tgval (GroupLeftover{}) = 0

        isKoutsuWait (GroupWait Koutsu _ _) = True
        isKoutsuWait                      _ = False

-- Wait trees

-- | @devops free_discards waits@
--
-- = Algorithm description
--
-- TODO Write me
devops :: [Tile] -> [Wait] -> NE.NonEmpty DevOp'
devops f_ts w_ts
    | []  <- f_ts, []  <- w_ts     = error "devops: called with a malformed hand (all mentsu)"
    | []  <- f_ts, [x] <- w_ts     = error "devops: called with a malformed hand (one mentsu free only)"
    | [x] <- f_ts, []  <- w_ts     = return (Left x) -- Last pair wait
    | []  <- f_ts, twoKoutsu w_ts = tenpaiTwoKoutsu
    | []  <- f_ts                 = NE.fromList breakingWait
    |             []  <- w_ts     = NE.fromList leftoversOnly
    | otherwise                  = NE.fromList meldLeftovers
    where
        twoKoutsu [Left _, Left _] = True
        twoKoutsu _                = False

        tenpaiTwoKoutsu = NE.fromList $ map (\(Left t) -> Left t) w_ts

        meldLeftovers = melding f_ts w_ts

        breakingWait = do
            (w_b, w_ts') <- removeEach w_ts
            let f_ts' = either (\t -> [t,t]) shuntsuWaitToHandTiles w_b 
                in melding f_ts' w_ts'

        leftoversOnly = do
            (w_t, f_ts')  <- removeEach f_ts
            (d_t, f_ts'') <- removeEach f_ts'
            (draw, wait)  <- buildWaits w_t
            return $ Right (d_t, draw, f_ts'', [wait])

        -- All combinations of discarding from f_all and melding to one of
        -- w_all
        melding f_all w_all = do
            (d_t, f_ts') <- removeEach f_all
            (w_opts, w_ts') <- removeEach w_all
            draw  <- either return id w_opts
            return $ Right (d_t, draw, f_ts', w_ts')

-- | In a greedy wait tree every @DevOp@ brings the hand closer to
-- tenpai/win.
--
-- The tree is formed by splitting the tiles with @tilesSplitGroupL@ and
-- iterating different cases of replacing @GroupLeftover@ with @DevOp@ that
-- would complete some @GroupWait@.
buildGreedyWaitTree :: [Mentsu] -> [Tile] -> WaitTree
buildGreedyWaitTree ms ts = buildGreedyWaitTree' (map (map GroupComplete ms ++) $ tilesGroupL ts)

-- | @buildGreedyWaitTree' groups@ discards groupings strictly less than
-- shanten over `groups` before building the tree.
buildGreedyWaitTree' :: [Grouping] -> WaitTree
buildGreedyWaitTree' xs =
    unfoldRootedTree (concatMap (concatMap tileGroupTiles) xs)
        go (NE.toList $ devops <$> leftovers <*> waits $ head xs')
    where
        xs' = filter ((== shanten xs) . shanten) xs
        -- 
        go :: DevOp' -> Either TenpaiOp (DevOp, NE.NonEmpty DevOp')
        go = bimap (`TenpaiOp` 0)
                   (\(disc, draw, lo, wa) -> (DevOp disc draw, devops lo wa))

-- Auxilary funtions

-- | Leftover tiles in the hand.
leftovers :: Grouping -> [Tile]
leftovers = mapMaybe go
    where
        go (GroupLeftover x) = Just x
        go _                 = Nothing

-- | Waits in the hand. Inner lists represent choice.
waits :: Grouping -> [Wait]
waits = mapMaybe go
    where
        go (GroupWait Koutsu  _ [t]) = Just $ Left t
        go (GroupWait Shuntsu _ xs)  = Just $ Right xs
        go _                         = Nothing

-- | Get the tiles in hand from a @TileGroup@.
tileGroupTiles :: TileGroup -> [Tile]
tileGroupTiles (GroupWait _ tiles _)  = tiles
tileGroupTiles (GroupComplete mentsu) = mentsuTiles mentsu
tileGroupTiles (GroupLeftover tile)   = [tile]

-- | Get the tiles in hand of the shuntsu wait based on the tiles waited.
shuntsuWaitToHandTiles :: [Tile] -> [Tile]
shuntsuWaitToHandTiles [] = error "shuntsuWaitToHandTiles: empty list of waits"
shuntsuWaitToHandTiles [t] = catMaybes $ case tileNumber t of
    Just San -> [predMay t, predMay t >>= predMay]
    Just Chii -> [succMay t, succMay t >>= succMay]
    _ -> [predMay t, succMay t]
shuntsuWaitToHandTiles (t:_) = catMaybes [succMay t, succMay t >>= succMay]

-- | Build possible waits for the tile, always drawing _1
buildWaits :: Tile -> [(Tile, Wait)]
buildWaits t = (t, Left t) : catMaybes
    [ do -- kanchan up
        nx  <- succMay t
        nx' <- succMay nx
        return (nx', Right [nx])
    , do -- kanchan down
        pr  <- predMay t
        pr' <- predMay pr
        return (pr', Right [pr])
    , do -- penchan middle down
        nx <- succMay t
        pr <- predMay t
        maybe (Just (nx, Right [pr])) (const Nothing) (succMay nx)
    , do -- penchan end down
        pr <- predMay t
        pr' <- predMay pr
        maybe (Just (pr, Right [pr'])) (const Nothing) (succMay t)
    , do -- penchan middle up
        pr <- predMay t
        nx <- succMay t
        maybe (Just (pr, Right [nx])) (const Nothing) (predMay pr)
    , do -- penchan end up
        nx <- succMay t
        nx' <- succMay nx
        maybe (Just (nx, Right [nx'])) (const Nothing) (predMay t)
    , do -- ryanmen up
        nx <- succMay t
        pr <- predMay t
        nx' <- succMay nx
        return (nx, Right [pr, nx'])
    , do -- ryanmen down
        pr <- predMay t
        pr' <- predMay pr
        nx <- succMay t
        return (pr, Right [pr', nx])
    ]
