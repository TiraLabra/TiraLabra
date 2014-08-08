------------------------------------------------------------------------------
-- | 
-- Module         : Mahjong.Mentsu
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- This module defines @Mentsu@ and provides functions to build them.
--
-- Functions for grouping a collection of tiles into mentsu and
-- "almost-mentsu-tile-pairs" are also provided; see for example
-- @mentsuFrom@.
------------------------------------------------------------------------------
module Mahjong.Mentsu where

import Data.List (sort, delete)
import Data.Maybe
import Control.Monad
import Control.Applicative
import Mahjong.Tiles

-- * Mentsu

-- | For Shuntsu, the tile is the **first** tile in chronological order.
data Mentsu = Mentsu MentsuKind Tile (Maybe Shout)
            deriving (Show, Read, Eq, Ord)

data MentsuKind = Shuntsu -- ^ 3 Tile straight
                | Koutsu -- ^ Triplet
                | Kantsu -- ^ Quadret
                | Jantou -- ^ Pair
                deriving (Show, Read, Eq, Ord, Bounded, Enum)

-- | A mentsu can result from a shout; and a shout always produces
-- a mentsu.
data Shout = Pon { shoutedFrom :: Kazehai, shoutedTile :: Tile }
           | Kan { shoutedFrom :: Kazehai, shoutedTile :: Tile }
           | Chi { shoutedFrom :: Kazehai, shoutedTile :: Tile, shoutedTo :: [Tile] }
           | Ron { shoutedFrom :: Kazehai, shoutedTile :: Tile, shoutedTo :: [Tile] }
           deriving (Show, Read, Eq, Ord)

shuntsu, koutsu, kantsu, jantou :: Tile -> Mentsu
shuntsu = Mentsu Shuntsu `flip` Nothing
koutsu  = Mentsu Koutsu `flip` Nothing
kantsu  = Mentsu Kantsu `flip` Nothing
jantou  = Mentsu Jantou `flip` Nothing

fromShout :: Shout -> Mentsu
fromShout shout = setShout $ case shout of
    Pon{} -> koutsu (shoutedTile shout)
    Kan{} -> kantsu (shoutedTile shout)
    Chi{} -> shuntsu (minimum $ shoutedTile shout : shoutedTo shout)
    Ron{}
        | [_]   <- shoutedTo shout         -> jantou (shoutedTile shout)
        | [x,y] <- shoutedTo shout, x == y -> koutsu (shoutedTile shout)
        | otherwise                       -> error "Impossible ron to a kantsu!"
    where
        setShout (Mentsu k t _) = Mentsu k t (Just shout)

-- ** Properties

-- | Get the mentsu kind
mentsuKind :: Mentsu -> MentsuKind
mentsuKind (Mentsu k _ _) = k

mentsuTiles :: Mentsu -> [Tile]
mentsuTiles (Mentsu mk t _) = case mk of
                                  Shuntsu -> t : catMaybes [succMay t, succMay t >>= succMay]
                                  Koutsu  -> replicate 3 t
                                  Kantsu  -> replicate 4 t
                                  Jantou  -> replicate 2 t

-- * Functions that operate on tiles

-- | `shuntsuWith tiles` attempts to build a shuntsu from `tiles`. Note
-- that **`tiles` must be in order**.
shuntsuWith :: [Tile] -> Maybe Mentsu
shuntsuWith (x:y:z:[]) = shuntsu x <$ do
    succMay x >>= guard . (== y)
    succMay y >>= guard . (== z)
shuntsuWith          _ = Nothing

-- * Group mentsu from tiles

-- | The type returned by @mentsuFrom@.
data MentsuLike = MentsuWait MentsuKind [Tile] [Tile] -- ^ `kind`, `In hand`, `Waits`
                | MentsuComplete Mentsu
                | MentsuLeftover Tile
                deriving (Show, Read, Eq, Ord)

-- | Get the tiles in the hand from a mentsulike record.
mentsuLikeTiles :: MentsuLike -> [Tile]
mentsuLikeTiles (MentsuWait _ tiles _)  = tiles
mentsuLikeTiles (MentsuComplete mentsu) = mentsuTiles mentsu
mentsuLikeTiles (MentsuLeftover tile)   = [tile]

-- | Build all enumerations of possible @MentsuLike@ groupings from given
-- tiles.
--
-- This algorithm goes through the input list depht-first trying to first
-- form an ankan with the first tile, then an ankou, then an ankou wait,
-- then a shuntsu, then an incomplete shuntsu with the first tile and its
-- successor, then an incomplete shuntsu with first and its successor's
-- successor. Whenever any of these melds are possible, the meld is cons'ed
-- to the result from applying the algorithm on the remaining tiles, and
-- the resulting list is returned.
--
-- Possible optimizations:
--
-- This could be optimized so that dropOne is only appended if no other
-- matches were made. Not sure how right that would be, though.
--
-- Also, traversing in BFS instead of this naive DFS would also give better
-- real-life performance when prioritizing low-shanten branches. But then
-- again this can be delegated to whatever is folding the result from
-- @mentsuLike@; shanten can be cumulatively calculated from every branch
-- (lists of lists).
mentsuLike :: [Tile] -> [[MentsuLike]]
mentsuLike = go . sort
    where
        go []       = [ [] ]
        go [x]      = [ [MentsuLeftover x] ]
        go (x:y:xs) = takeKantsu ++ takeTriplet ++ takePair ++ takeShuntsu ++ dropOne
            where
                takePair
                    | x == y                            = MentsuWait Koutsu [x, x] [x] `goWith` xs
                    | otherwise                         = []

                takeTriplet
                    | (z:xs') <- xs, x == z              = MentsuComplete (koutsu x) `goWith` xs'
                    | otherwise                         = []

                takeKantsu
                    | (z:w:xs') <- xs, x == z, z == w    = MentsuComplete (kantsu x) `goWith` xs'
                    | otherwise                         = []

                takeShuntsu = concat $ catMaybes
                    [ ts_complete    <$> my <*> mz
                    , ts_sequentical <$> my
                    , ts_inbetween   <$> mz
                    ] where
                        my = succMay x >>= \y' -> y' <$ guard (y' `elem` y:xs)
                        mz = succMay x >>= succMay >>= \z -> z <$ guard (z `elem` y:xs)

                ts_complete y' z  = MentsuComplete (shuntsu x) `goWith` delete y' (delete z (y:xs))
                ts_sequentical y' = MentsuWait Shuntsu [x,y'] (catMaybes [predMay x, succMay y']) `goWith` delete y' (y:xs)
                ts_inbetween   z  = MentsuWait Shuntsu [x,z] [fromJust (succMay x)] `goWith` delete z (y:xs)

                dropOne = MentsuLeftover x `goWith` (y:xs)

                goWith a = map (a :) . go 
