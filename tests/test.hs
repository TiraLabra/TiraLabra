{-# LANGUAGE OverloadedStrings #-}
{-# OPTIONS_GHC -fno-warn-orphans #-}
------------------------------------------------------------------------------
-- | 
-- Module         : Tests
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
------------------------------------------------------------------------------
module Main where

import Test.Tasty.QuickCheck as QC

import TestImport
import AlgoTest
import Mahjong.Hand
import Mahjong.Hand.Mentsu
import Mahjong.Tiles


main :: IO ()
main = defaultMain tests

tests :: TestTree
tests = testGroup "Tests"
    [ algoTests
    , tilesTests
    , handTests
    , mentsuTests
    ]

-- | Mahjong.Tiles
tilesTests :: TestTree
tilesTests = testGroup "Tiles"
    [ QC.testProperty "succMay success when n /= maxBound" $ \n ->
        n /= maxBound ==>
        succMay (Suited SouTile n False) === Just (Suited SouTile (succ n) False)
    , QC.testProperty "succMay fails on honors"       $ \tile -> not (suited tile) ==> isNothing (succMay tile)
    , QC.testProperty "tileNumber gives the number"   $ \n -> Just n === tileNumber (Suited undefined n undefined)
    , QC.testProperty "read . show $ tile === tile"   $ \tile -> read (show tile) === (tile :: Tile)
    , QC.testProperty "t ==~ t"                       $ \tile -> tile ==~ tile
    , QC.testProperty "not $ t ==~ succMay/predMay t" $ \tile -> suited tile ==> not (tile ==~ fromMaybe (fromJust $ predMay tile) (succMay tile))
    , QC.testProperty "not $ suited ==~ not suited"   $ \t t' -> suited t && not (suited t') ==> not (t ==~ t')
    ]

-- | Mahjong.Hand
handTests :: TestTree
handTests = testGroup "Hand"
    [ QC.testProperty "(ms, ts) === (,) <$> called <*> concealed <*> fromTiles ms ts" $ \ms ts ->
        (ms, ts) === ((,) <$> called <*> concealed) (fromTiles ms ts)
    ]

-- | Mahjong.Hand.Mentsu
mentsuTests :: TestTree
mentsuTests = testGroup "Mentsu"
    [ QC.testProperty "shuntsuWith . mentsuTiles == id for shuntsu" $
        liftA2 (===) (shuntsuWith . mentsuTiles) Just <$> arbitraryShuntsu
    ]
