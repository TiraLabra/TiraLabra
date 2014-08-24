------------------------------------------------------------------------------
-- | 
-- Module         : Main (main.hs)
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
------------------------------------------------------------------------------
module Main where

import Mahjong.Hand.Algo

import Data.String
import System.Environment
import Text.PrettyPrint.ANSI.Leijen (pretty)

main :: IO ()
main = do
    (cmd:args) <- getArgs
    let tiles = map fromString args
    putStrLn $ case cmd of
        "shanten" -> "Shanten: " ++ show (shanten tiles)
        "gwt"     -> unlines $ map (show . pretty) $ buildGWTs' [] tiles
        _         -> "Available commands: shanten TILES, gwt TILES"
