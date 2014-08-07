------------------------------------------------------------------------------
-- |
-- Module         : Mahjong.Hand
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- This module provides the representation type for a mahjong hand
-- (@Hand@), and functions that operate on a hand.
------------------------------------------------------------------------------
module Mahjong.Hand where

import Mahjong.Tiles
import Mahjong.Mentsu
import Mahjong.Hand.Shanten
import Mahjong.Hand.DevelopmentTree

data Hand = Hand
          { called :: [Mentsu]
          , concealed :: [Tile]
          , shanten :: Shanten
          , developments :: DevelopmentTree
          }

-- | Build a @"Hand"@ from melded mentsu and concealed tiles.
fromTiles :: [Mentsu] -> [Tile] -> Hand
fromTiles ot ct = Hand ot ct (shantenOf ot ct) (buildTree ot ct)
