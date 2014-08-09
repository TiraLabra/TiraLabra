{-# OPTIONS_GHC -fno-warn-orphans #-}
------------------------------------------------------------------------------
-- | 
-- Module         : Main (benchmarks)
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- Benchmarking for "Mahjong.Algo".
------------------------------------------------------------------------------
module Main where

import Import (replicateM)
import Criterion
import Criterion.Main
import Control.DeepSeq
import Test.Tasty.QuickCheck (generate, arbitrary)

import Mahjong.Tiles
import Mahjong.Algo

main :: IO ()
main = defaultMain suite

-- TODO: instead of supplying a single env, randomize the runs themselves
suite :: [Benchmark]
suite =
    [ env randomTiles $ \tiles -> bgroup "tilesGroupL: random tiles"
        [ bench "13" $ nf tilesGroupL (take 13 tiles)
        , bench "17" $ nf tilesGroupL (take 17 tiles)
        , bench "21" $ nf tilesGroupL (take 21 tiles)
        , bench "25" $ nf tilesGroupL (take 25 tiles)
        , bench "28" $ nf tilesGroupL (take 28 tiles)
        ]
    , bgroup "tilesSplitGroupL"
        [
        ]
    , bgroup "shanten: random 13 tiles"
        [
        ]
    ]

-- randomTiles :: Int -> IO [Tile]
randomTiles :: IO [Tile]
randomTiles = replicateM 50 (generate arbitrary)

------------------------------------------------------

instance NFData Tile where
instance NFData TileGroup where
