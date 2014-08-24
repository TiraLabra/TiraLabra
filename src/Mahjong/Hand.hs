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

import Mahjong.Hand.Mentsu
import Mahjong.Hand.Algo
import Mahjong.Tiles

data Hand = Hand
          { called :: [Mentsu]
          , concealed :: [Tile]
          , handStatus :: Shanten
          , developments :: [WaitTree]
          }

-- | Build a @"Hand"@ from melded mentsu and concealed tiles.
fromTiles :: [Mentsu] -> [Tile] -> Hand
fromTiles ot ct = Hand ot ct (shanten (ot, ct)) (buildGWTs' ot ct)
