------------------------------------------------------------------------------
-- | 
-- Module         : Mahjong.Tiles
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- The tile type practically ripped from *hajong*.
------------------------------------------------------------------------------
module Mahjong.Tiles where

-- | A (japanese) mahjong tile.
data Tile = Man Number Aka
          | Pin Number Aka
          | Sou Number Aka
          | Honor HonorTile
          deriving (Show, Read, Eq, Ord)

-- | Is akadora?
type Aka = Bool

-- | The number of man, pin and sou tiles.
data Number = Ii | Ryan | San | Suu | Wu | Rou | Chii | Paa | Chuu
            deriving (Show, Read, Eq, Ord, Enum, Bounded)

data HonorTile = Sangenpai Sangenpai
               | Kazehai Kazehai
               deriving (Show, Read, Eq, Ord)

data Sangenpai = Haku | Hatsu | Chun
               deriving (Show, Read, Eq, Ord, Enum, Bounded)

data Kazehai = Ton | Nan | Shaa | Pei
             deriving (Show, Read, Eq, Ord, Enum, Bounded)
