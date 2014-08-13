------------------------------------------------------------------------------
-- | 
-- Module         : Main (util.hs)
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
------------------------------------------------------------------------------
module Main (module Main) where

import Text.PrettyPrint.ANSI.Leijen as Main (pretty, putDoc)

import Mahjong.Tiles as Main
import Mahjong.Hand.Algo as Main
import AlgoTest as Main

gwt = putDoc . pretty . buildGreedyWaitTree'


