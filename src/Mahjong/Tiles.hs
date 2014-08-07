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

import Control.Monad
import Control.Applicative

-- | A (japanese) mahjong tile.
data Tile = Suited TileKind Number Aka
          | Honor Honor
          deriving (Show, Read, Eq, Ord)

data TileKind = ManTile | PinTile | SouTile | HonorTile
              deriving (Show, Read, Eq, Ord)

-- | Is akadora?
type Aka = Bool

-- | The number of man, pin and sou tiles.
data Number = Ii | Ryan | San | Suu | Wu | Rou | Chii | Paa | Chuu
            deriving (Show, Read, Eq, Ord, Enum, Bounded)

data Honor = Sangenpai Sangenpai
           | Kazehai Kazehai
           deriving (Show, Read, Eq, Ord)

data Sangenpai = Haku | Hatsu | Chun
               deriving (Show, Read, Eq, Ord, Enum, Bounded)

data Kazehai = Ton | Nan | Shaa | Pei
             deriving (Show, Read, Eq, Ord, Enum, Bounded)

-- | Extract tile kind
tileKind :: Tile -> TileKind
tileKind (Suited k _ _) = k
tileKind (Honor _)      = HonorTile

-- | Number of suited tiles
tileNumber :: Tile -> Maybe Number
tileNumber (Suited _ n _) = Just n
tileNumber (Honor _)      = Nothing

-- | True for Man, Pin and Sou tiles; false for honors.
suited :: Tile -> Bool
suited = (/= HonorTile) . tileKind

-- | Like @succ@ but fail as nothing if the succession wouldn't make sense
-- (i.e the input or output would not be a (defined) suited tile).
succMay :: Tile -> Maybe Tile
succMay (Suited k n a) = Suited k (succ n) a <$ guard (n /= maxBound)
succMay _              = Nothing
