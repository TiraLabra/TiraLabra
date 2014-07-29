% vim:ft=haskell
\begin{code}
------------------------------------------------------------------------------
-- |
-- Module         : Shanten.Types
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
------------------------------------------------------------------------------
module Shanten where

-- * Tile type

-- | Tile represents any (japanese) mahjong tile.
--
-- From hajong.
data Tile = Man Number Bool
          | Pin Number Bool
          | Sou Number Bool
          | Sangen Sangenpai
          | Kaze Kazehai
          deriving (Show, Read, Eq, Ord)

-- | A custom number type (1-9) for numbered tiles. Constructors are named
-- according to the conventional chinese/japanese names, for fun.
--
-- From hajong.
data Number = Ii | Ryan | San | Suu | Wu | Rou | Chii | Paa | Chuu
            deriving (Show, Read, Eq, Ord, Enum, Bounded)

-- | The three dragon tiles, or sangenpai. The Ord instance obeys the
-- succession order.
--
-- From hajong.
data Sangenpai = Haku | Hatsu | Chun
               deriving (Show, Read, Eq, Ord, Enum, Bounded)

-- | The four wind tiles, or kazehai.
--
-- From hajong.
data Kazehai = Ton | Nan | Shaa | Pei
             deriving (Show, Read, Eq, Ord, Enum, Bounded)

\end{code}
