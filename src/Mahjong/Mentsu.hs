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

import Mahjong.Tiles

-- * Mentsu types

-- | For Shuntsu, the tile is the **first** tile in chronological order.
data Mentsu = Mentsu MentsuKind Tile (Maybe Shout)
            deriving (Show, Read, Eq)

data MentsuKind = Shuntsu -- ^ 3 Tile straight
                | Koutsu -- ^ Triplet
                | Kantsu -- ^ Quadret
                | Jantou -- ^ Pair
                deriving (Show, Read, Eq)

-- | A mentsu can result from a shout; and a shout always produces
-- a mentsu.
data Shout = Pon { shoutedFrom :: Kazehai, shoutedTile :: Tile }
           | Kan { shoutedFrom :: Kazehai, shoutedTile :: Tile }
           | Chi { shoutedFrom :: Kazehai, shoutedTile :: Tile, shoutedTo :: [Tile] }
           | Ron { shoutedFrom :: Kazehai, shoutedTile :: Tile, shoutedTo :: [Tile] }
           deriving (Show, Read, Eq)

-- * Grouping mentsu from tiles

-- | The type returned by @mentsuFrom@.
data MentsuLike = MentsuLike MentsuKind Tile -- ^ Missing one (the specified) tile
                | MentsuComplete Mentsu

-- | Build all enumerations of possible @MentsuLike@ groupings from given
-- tiles.
mentsuFrom :: [Tile] -> [[MentsuLike]]
mentsuFrom ts = undefined
