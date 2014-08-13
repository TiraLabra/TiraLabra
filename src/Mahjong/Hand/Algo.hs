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
module Mahjong.Hand.Algo where

import Control.Monad
import Control.Applicative
import Data.Maybe

import Data.List (delete, sort, foldl', find, groupBy)
import Data.List.HT (removeEach, unzipEithers)
import Data.Tree

import Mahjong.Hand.Mentsu
import Mahjong.Hand.Value
import Mahjong.Tiles

-- * Grouping tiles

-- ** Types

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

-- | Leftover tiles in the hand.
leftovers :: Grouping -> [Tile]
leftovers = mapMaybe go
    where
        go (GroupLeftover x) = Just x
        go _                 = Nothing

-- | Right for shuntsu wait, Left for koutsu wait or ready pair
type Wait = Either Tile [Tile]

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

-- ** Algorithms

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

-- * Calculating shanten

-- | A nothing result means that the hand has 14 (or more) tiles but is not
-- complete. Thus, invalid.
type Shanten = Maybe Int

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

-- * Wait trees

-- ** Types and helpers

-- | A @WaitTree@ models the development options of an mahjong hand.
--
-- The nodes in a development tree represent either a hand development
-- operation (@DevOp@) (in internal nodes) or value of the hand that is
-- achieved by applying the DevOps on the path to root.
type WaitTree = Forest (Either DevOp (Tile, Value))

-- | An operation that develops the hand: In @(discard, drawn)@ the tile
-- `drawn` is drawn from the wall and sequently the tile `discard` is
-- discarded.
type DevOp = (Tile, Tile)

-- | Left ready with. Right (op, free, waits).
type DevOp' = Either Tile (DevOp, [Tile], [Wait])

-- | @devops free_discards waits@
devops :: [Tile] -> [Wait] -> [DevOp']
devops f_ts w_ts
    | []  <- f_ts, [] <- w_ts = error "devops: called with a malformed hand (all mentsu)"
    | [x] <- f_ts, [] <- w_ts = return (Left x) -- Last pair wait
    | []  <- f_ts            = breakingWait
    | otherwise             = withReadyPair ++ withoutReadyPair
    where
        withoutReadyPair = drawing f_ts w_ts

        withReadyPair =
            let (pairs, notPairs) = unzipEithers w_ts
                in do
                    (_, w_ps) <- removeEach pairs
                    drawing f_ts (map Right notPairs ++ map Left w_ps)

        breakingWait = do
            (w_b, w_ts') <- removeEach w_ts
            let f_ts' = either (\t -> [t,t]) shuntsuWaitToHandTiles w_b 
                in drawing f_ts' w_ts'

        -- All combinations of discarding from f_all and melding to one of
        -- w_all
        drawing f_all w_all = do
            (d_t, f_ts') <- removeEach f_all
            (w_opts, w_ts') <- removeEach w_all
            draw  <- either return id w_opts
            return $ Right ((d_t, draw), f_ts', w_ts')

-- | Get the tiles in hand of the shuntsu wait based on the tiles waited.
shuntsuWaitToHandTiles :: [Tile] -> [Tile]
shuntsuWaitToHandTiles [] = error "shuntsuWaitToHandTiles: empty list of waits"
shuntsuWaitToHandTiles [t] = catMaybes $ case tileNumber t of
    Just San -> [predMay t, predMay t >>= predMay]
    Just Chii -> [succMay t, succMay t >>= succMay]
    _ -> [predMay t, succMay t]
shuntsuWaitToHandTiles (t:_) = catMaybes [succMay t, succMay t >>= succMay]

drawWaitTree :: WaitTree -> String
drawWaitTree = drawForest . fmap (fmap pp)
    where
        pp :: Either DevOp (Tile, Value) -> String
        pp = either (\(disc,draw) -> ppTile disc ++ " for " ++ ppTile draw)
                    (\(draw,val) -> "Tenpai for " ++ ppTile draw ++ " at " ++ show val ++ " fu")

-- ** Algorithms

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
buildGreedyWaitTree' xs = unfoldForest go (devops <$> leftovers <*> waits $ head xs')
    where
        xs' = filter ((== shanten xs) . shanten) xs

        go :: DevOp' -> (Either DevOp (Tile, Value), [DevOp'])
        go (Left agari)         = (Right (agari, 0), []) -- value
        go (Right (op, lo, wa)) = (Left op, devops lo wa)

{- TODO vk3 ja eteenpäin

-- * Puu

-- | Epätyhjä useahaarainen puu, joka on erikseen parametrinen
-- solmukohtien elementtien (`a`) ja  lehtien (`l` tyypeissä.
data RoseTree a l = Branch a (NonEmpty (RoseTree a l))
                  | Leaf l

import qualified Data.Tree.DUAL as DUAL
-- |
--  * A path downwards represents a sequence of `(draw, discard)` pairs that
--    greedily get the hand closer to tenpai.
--    First argument to the DUALTree (`[DevSeq]`) gives the discard
--    sequence.
--  * A leaf stores the hand value.
type DevelopmentTree = DUAL.DUALTree [DevSeq] () DevSeq Value

-}
