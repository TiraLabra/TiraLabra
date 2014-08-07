------------------------------------------------------------------------------
-- |
-- Module      :  Mahjong.Hand.Shanten
-- Copyright   :  (C) 2014 Samuli Thomasson
-- License     :  BSD-style (see the file LICENSE)
-- Maintainer  :  Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability   :  experimental
-- Portability :  non-portable
--
-- Calculating the shanten of mahjong hands.
------------------------------------------------------------------------------
module Mahjong.Hand.Shanten where

import Mahjong.Tiles
import Mahjong.Mentsu

-- | (Valmiin) käden arvo
type Shanten = Int

-- | `shantenOf open closed` laskee shanten-arvon kädelle jossa on tiilet
-- `closed` ja "pakotetut" (esim. avoimet) mentsut `open`.
shantenOf :: [Mentsu] -> [Tile] -> Shanten
shantenOf ms ts = undefined
