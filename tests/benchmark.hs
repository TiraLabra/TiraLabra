{-# LANGUAGE TypeSynonymInstances, FlexibleInstances #-}
{-# LANGUAGE DeriveGeneric #-}
{-# LANGUAGE StandaloneDeriving #-}
{-# LANGUAGE CPP #-}
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
import Data.Maybe
import Data.List (sort, group)
import Data.List.NonEmpty (NonEmpty(..), toList)
import TestImport (replicateM)
import Criterion
import Criterion.Main
import Control.DeepSeq
import Control.DeepSeq.Generics (genericRnf)
import GHC.Generics
import Test.Tasty.QuickCheck (generate, arbitrary)

import Mahjong.Tiles
import Mahjong.Hand.Mentsu
import Mahjong.Hand.Algo
import Mahjong.Hand.Algo.WaitTree

main :: IO ()
main = defaultMain
#if GWT_ONLY
     gwtSuite
#else
    suite
#endif


-- Bar chart of shanten distribution (feed to gnuplot)
--    xs <- flip mapM [1..100000] $ \_ -> fromJust . shanten <$> randomTiles 13
--    let xs' = map (\xs -> (head xs, length xs)) $ group $ sort xs
--    mapM_ (\(sh, n) -> putStrLn $ show n ++ " " ++ show sh) xs'

-- TODO: instead of supplying a single env, randomize the runs themselves
suite :: [Benchmark]
suite =
    [ bgroup "tilesGroupL (random)"
        [ bench "13" $ nfIO $ tilesGroupL <$> randomTiles 13
        , bench "19" $ nfIO $ tilesGroupL <$> randomTiles 19
        , bench "22" $ nfIO $ tilesGroupL <$> randomTiles 22
        , bench "24" $ nfIO $ tilesGroupL <$> randomTiles 24
        ]
    , bgroup "tilesSplitGroupL (random)"
        [ bench "13" $ nfIO $ tilesSplitGroupL <$> randomTiles 13
        , bench "19" $ nfIO $ tilesSplitGroupL <$> randomTiles 19
        , bench "22" $ nfIO $ tilesSplitGroupL <$> randomTiles 22
        , bench "24" $ nfIO $ tilesSplitGroupL <$> randomTiles 24
        ]
    , bgroup "shanten (random, tiles)"
        [ bench "13" $ nfIO $ shanten <$> randomTiles 13
        , bench "22" $ nfIO $ shanten <$> randomTiles 22
        , bench "24" $ nfIO $ shanten <$> randomTiles 24
        ]
    ]

gwtSuite :: [Benchmark]
gwtSuite = 
    [ bgroup "buildGWT (random tiles)"
        [ bench "13" $ nfIO $ buildGWTs' [undefined, undefined] <$> randomTiles 7 ]
    ]

-- TODO The algos assume no uncalled kantsu - so they should be ignored
-- here. buildGreedyWaitTree even fails with them (kinda by design)!
randomTiles :: Int -> IO [Tile]
randomTiles n = do
    xs <- replicateM n (generate arbitrary)
    if any ((>= 4) . length) . group $ sort xs
        then randomTiles n
        else return xs

------------------------------------------------------
-- Just a lot of boilerplate NFData instances

deriving instance Generic TileGroup
deriving instance Generic (RootedTree r i l)
deriving instance Generic (RootedBranch i l)

instance NFData Tile
instance NFData Mentsu
instance NFData DevOp
instance NFData TenpaiOp
instance NFData MentsuKind
instance (NFData r, NFData i, NFData l) => NFData (RootedTree r i l) where rnf = genericRnf
instance (NFData i, NFData l) => NFData (RootedBranch i l) where rnf = genericRnf
instance NFData a => NFData (NonEmpty a) where rnf (x :| xs) = rnf x `seq` rnf xs
instance NFData TileGroup where rnf _ = ()
