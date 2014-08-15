{-# LANGUAGE TypeSynonymInstances, FlexibleInstances #-}
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

import Data.Functor
import Data.List.NonEmpty (toList)
import TestImport (replicateM)
import Criterion
import Criterion.Main
import Control.DeepSeq
import Test.Tasty.QuickCheck (generate, arbitrary)

import Mahjong.Tiles
import Mahjong.Hand.Algo
import Mahjong.Hand.Algo.WaitTree

main :: IO ()
main = defaultMain suite

-- TODO: instead of supplying a single env, randomize the runs themselves
suite :: [Benchmark]
suite =
    [ bgroup "tilesGroupL (random)"
        [ bench "13" $ nfIO $ tilesGroupL <$> randomTiles 13
        , bench "19" $ nfIO $ tilesGroupL <$> randomTiles 19
        , bench "22" $ nfIO $ tilesGroupL <$> randomTiles 22
        , bench "24" $ nfIO $ tilesGroupL <$> randomTiles 24
        , bench "26" $ nfIO $ tilesGroupL <$> randomTiles 26
        ]
    , bgroup "tilesSplitGroupL (random)"
        [ bench "13" $ nfIO $ tilesSplitGroupL <$> randomTiles 13
        , bench "19" $ nfIO $ tilesSplitGroupL <$> randomTiles 19
        , bench "22" $ nfIO $ tilesSplitGroupL <$> randomTiles 22
        , bench "24" $ nfIO $ tilesSplitGroupL <$> randomTiles 24
        , bench "26" $ nfIO $ tilesSplitGroupL <$> randomTiles 26
        ]
    , bgroup "shanten (random, tiles)"
        [ bench "13" $ nfIO $ shanten <$> randomTiles 13
        , bench "22" $ nfIO $ shanten <$> randomTiles 20
        , bench "24" $ nfIO $ shanten <$> randomTiles 20
        ]

    , bgroup "buildGreedyWaitTree (random tiles)"
        [ bench "13" $ nfIO $ buildGreedyWaitTree [] <$> randomTiles 13
        ]
    ]

-- TODO The algos assume no uncalled kantsu - so they should be ignored
-- here. buildGreedyWaitTree even fails with them (kinda by design)!
randomTiles :: Int -> IO [Tile]
randomTiles n = replicateM n (generate arbitrary)

------------------------------------------------------

instance NFData Tile where
instance NFData TileGroup where
instance (NFData r, NFData i, NFData l) => NFData (RootedTree r i l) where
    rnf (RootedTree r bs) = rnf (r, bs)

instance (NFData i, NFData l) => NFData (RootedBranch i l) where
    rnf (RootedLeaf x) = rnf x
    rnf (RootedBranch i bs) = rnf (i, toList bs)

instance NFData DevOp where
    rnf (DevOp x y) = rnf (x,y)

instance NFData TenpaiOp where
    rnf (TenpaiOp x y) = rnf (x,y)
