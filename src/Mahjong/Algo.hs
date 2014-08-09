{-# LANGUAGE FlexibleInstances #-}
------------------------------------------------------------------------------
-- | 
-- Module         : Mahjong.Algo
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable

-- This module provides algorithms to work with mahjong hands:
--  * Grouping tiles (@groupTiles@)
------------------------------------------------------------------------------
module Mahjong.Algo where

import Control.Monad
import Control.Applicative
import Data.List (delete, sort)
import Data.Maybe


import Mahjong.Tiles
import Mahjong.Mentsu

-- * Grouping tiles

-- | Data type used to describe some group of tiles.
data TileGroup = GroupWait MentsuKind [Tile] [Tile]
                -- ^ A `MentsuWait kind Inhand waits` describes a wait on
                -- any of the tiles `waits` for a mentsu of kind `kind`
                -- with tiles `inhand` already in hand.
                | GroupComplete Mentsu
                | GroupLeftover Tile
                -- ^ A leftover tile which cannot be associated with any
                -- other tile.
                deriving (Show, Read, Eq, Ord)

-- | Get the tiles in hand from a @TileGroup@.
tileGroupTiles :: TileGroup -> [Tile]
tileGroupTiles (GroupWait _ tiles _)  = tiles
tileGroupTiles (GroupComplete mentsu) = mentsuTiles mentsu
tileGroupTiles (GroupLeftover tile)   = [tile]

-- | `tilesGroupL` simply returns all possible groupings for the tiles
--
-- The algorithm goes through the input list of tiles (after sorting it)
-- trying to use the first tile (head of the list) to:
--  1. form an ankan with the first tile,
--  2. an ankou,
--  3. an ankou wait,
--  4. a shuntsu,
--  5. a shuntsu wait with its successor,
--  6. a shuntsu wait the successor's successor (middle wait),
--  7. discard the tile as a `GroupLeftOver`.
--
-- Whenever any of these are possible, the result is cons'ed to every
-- result from applying the function recurively on the remaining tiles.
-- The end result is a list of all possible groups.
--
-- = Performance concerns and optimizations
--
-- One one level of recursion complete mentsu are preferred over incomplete
-- (and incomplete over leftovers) and are placed at the head of the list
-- **on that level of recursion**.  However, there is no guarentee that
-- minimal groupings couldn't reside in the result's tail.
tilesGroupL :: [Tile] -> [[TileGroup]]
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


-- * Shanten
--
-- We use a type class to allow calculating shanten from different
-- representations. The fastest is to supply a [TileGroup] or
-- [[TileGroup]].

type Shanten = Int

class ShantenOf x where
    shanten :: x -> Shanten

instance ShantenOf [[TileGroup]] where
    shanten = minimum . map shanten

instance ShantenOf [TileGroup] where
    shanten = undefined

instance ShantenOf [Tile] where
    shanten = shanten . tilesGroupL

instance ShantenOf ([Mentsu], [Tile]) where
    shanten = undefined
