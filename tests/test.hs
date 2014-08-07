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

import Test.Tasty
import Test.Tasty.QuickCheck as QC
import Control.Applicative

import Mahjong.Tiles
import Mahjong.Mentsu

main :: IO ()
main = defaultMain tests

tests :: TestTree
tests = testGroup "Tests" [tileProps]

tileProps :: TestTree
tileProps = testGroup "Tiles"
    [ QC.testProperty "succMay success when n /= maxBound" $ \n ->
        n /= maxBound ==>
        succMay (Suited SouTile n False) == Just (Suited SouTile (succ n) False)
    , QC.testProperty "succMay fails on honors" $ \t ->
        not (suited t) ==> succMay t == Nothing
    , QC.testProperty "tileNumber gives the number" $ \n ->
        Just n == tileNumber (Suited undefined n undefined)
    ]

instance Arbitrary Tile where
    arbitrary = oneof [ Suited <$> arbitrary <*> arbitrary <*> pure False
                      , Honor <$> arbitrary
                      ]

instance Arbitrary Honor where
    arbitrary = oneof [Kazehai <$> arbitrary, Sangenpai <$> arbitrary]

instance Arbitrary TileKind where
    arbitrary = oneof $ pure <$> [ManTile, PinTile, SouTile]

instance Arbitrary Number where arbitrary = arbitraryBoundedEnum
instance Arbitrary Sangenpai where arbitrary = arbitraryBoundedEnum
instance Arbitrary Kazehai where arbitrary = arbitraryBoundedEnum

