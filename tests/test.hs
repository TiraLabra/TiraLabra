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

import Control.Applicative
import Data.Maybe
import Data.List (sort)
import Debug.Trace

import Test.Tasty
import Test.Tasty.QuickCheck as QC
import Test.Tasty.HUnit

import Mahjong.Tiles
import Mahjong.Mentsu

main :: IO ()
main = defaultMain tests

tests :: TestTree
tests = testGroup "Tests"
    [ tileProps
    , props_mentsuLike
    ]

-- * Tiles

tileProps :: TestTree
tileProps = testGroup "Tiles"
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

-- ** Arbitrary instances

instance Arbitrary Tile where
    arbitrary = oneof [ Suited <$> arbitrary <*> arbitrary <*> pure False
                      , Honor <$> arbitrary
                      ]

instance Arbitrary Honor where arbitrary     = oneof [Kazehai <$> arbitrary, Sangenpai <$> arbitrary]
instance Arbitrary TileKind where arbitrary  = oneof $ pure <$> [ManTile, PinTile, SouTile]
instance Arbitrary Number where arbitrary    = arbitraryBoundedEnum
instance Arbitrary Sangenpai where arbitrary = arbitraryBoundedEnum
instance Arbitrary Kazehai where arbitrary   = arbitraryBoundedEnum

-- * Mentsu

props_mentsuLike :: TestTree
props_mentsuLike = testGroup "Mentsu"
    [ QC.testProperty "mentsuLike [n, n+1, n+2] is (only) a shuntsu for any suited tile" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                mentsuLike [t, fromJust (succMay t), fromJust (succMay t >>= succMay) ]
                       .<--
                       [[MentsuComplete (shuntsu t)]]

    , QC.testProperty "mentsuLike [t, t] a mentsuLike koutsu t" $ \t ->
        mentsuLike [t,t] .<-- [[MentsuWait Koutsu [t, t] [t]]]

    , QC.testProperty "mensuLike [t, t, t, t+1, t+2]" $ \n tk ->
        let t   = toSuited n tk
            t'  = fromJust (succMay t)
            t'' = fromJust (succMay t')
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                mentsuLike [t, t, t, t', t'']
                    .<--
                    [ [MentsuComplete (koutsu t), MentsuWait Shuntsu [t', t''] (t : catMaybes [succMay t'']) ]
                    , [MentsuWait Koutsu [t, t] [t], MentsuComplete (shuntsu t)  ]
                    ]
    , QC.testProperty "mentsuLike [t, t, t+1, t+1, t+2, t+2]" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                mentsuLike (catMaybes [Just t, Just t, succMay t, succMay t, succMay t >>= succMay, succMay t >>= succMay])
                    .<--
                    [ [ MentsuComplete (shuntsu t), MentsuComplete (shuntsu t) ]
                    , [ MentsuWait Koutsu [t,t] [t]
                      , let t' = fromJust $ succMay t in MentsuWait Koutsu [t',t'] [t']
                      , let t' = fromJust $ succMay t >>= succMay in MentsuWait Koutsu [t', t'] [t']
                      ]
                    ]

    , QC.testProperty "mentsuLike <tiles of some mentsu> `contains` <some mentsu>" $ \ms ->
        length ms <= 8 ==> mentsuLike (concatMap mentsuTiles ms) .<-- [ map MentsuComplete ms ]
        -- XXX: If you don't restrict the number of mentsu, the current
        -- mentsuLike is inefficient enough that it explodes at exactly
        -- 9 mentsu (8 mentsu completes in split seconds). That should not
        -- be a problem as a hand has only 4 mentsu + pair, though.

    , QC.testProperty "mentsuLike <tiles of some mentsulike> `contains` <...>" $ \ml ->
        -- traceShow ml $
        length ml <= 5 ==> mentsuLike (concatMap mentsuLikeTiles ml) .<-- [ml]
    ]

-- ** Arbitrary instances

instance Arbitrary MentsuKind where arbitrary = arbitraryBoundedEnum
instance Arbitrary Mentsu where
    arbitrary = do
        mk <- elements [Shuntsu, Koutsu, Kantsu]
        case mk of
            Shuntsu -> arbitraryShuntsu
            Koutsu  -> koutsu <$> arbitrary
            Kantsu  -> kantsu <$> arbitrary
            Jantou  -> discard -- Not reached

instance Arbitrary MentsuLike where
    arbitrary = oneof
        [ (\t -> MentsuWait Koutsu [t,t] [t]) <$> arbitrary
        , breakShuntsu =<< arbitraryShuntsu
        , MentsuComplete <$> arbitrary
        , MentsuLeftover <$> arbitrary
        ] where
            breakShuntsu ms =
                let [t,t',t''] = mentsuTiles ms
                    in elements
                        [ MentsuWait Shuntsu [t , t' ] (catMaybes [predMay t, Just t''])
                        , MentsuWait Shuntsu [t', t''] (t : catMaybes [succMay t''])
                        , MentsuWait Shuntsu [t , t''] [t']
                        ]

arbitraryShuntsu = shuntsu <$> (toSuited <$> (toEnum <$> choose (fromEnum Ii, fromEnum Chuu - 2)) <*> arbitrary)
                    

-- * Utility

-- | xs .<-- ys succeeds when ys is contained within xs.
(.<--) :: (Show a, Ord a) => [[a]] -> [[a]] -> Property
xs .<-- ys = conjoin $ isElem <$> ys
    where isElem y = counterexample (show y ++ " `notElem` " ++ show xs) (sort y `elem` xs')
          xs' = map sort xs
