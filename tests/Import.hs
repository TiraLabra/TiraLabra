{-# OPTIONS_GHC -fno-warn-orphans #-}
------------------------------------------------------------------------------
-- | 
-- Module         : Import
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
------------------------------------------------------------------------------
module Import
    ( module Import ) where

import Prelude
import Control.Monad        as Import
import Control.Applicative  as Import
import Data.List (sort)
import Data.Maybe           as Import
import Debug.Trace          as Import

import Test.Tasty           as Import
import Test.Tasty.QuickCheck

import Mahjong.Algo
import Mahjong.Mentsu
import Mahjong.Tiles

-- | xs .<-- ys succeeds when ys is contained within xs.
(.<--) :: (Show a, Ord a) => [[a]] -> [[a]] -> Property
xs .<-- ys = conjoin $ isElem <$> ys
    where isElem y = counterexample (show y ++ " `notElem` " ++ show xs) (sort y `elem` xs')
          xs' = map sort xs

-- Instances

instance Arbitrary Tile where
    arbitrary = oneof [ Suited <$> arbitrary <*> arbitrary <*> pure False
                      , Honor <$> arbitrary
                      ]

instance Arbitrary Honor where arbitrary     = oneof [Kazehai <$> arbitrary, Sangenpai <$> arbitrary]
instance Arbitrary TileKind where arbitrary  = oneof $ pure <$> [ManTile, PinTile, SouTile]
instance Arbitrary Number where arbitrary    = arbitraryBoundedEnum
instance Arbitrary Sangenpai where arbitrary = arbitraryBoundedEnum
instance Arbitrary Kazehai where arbitrary   = arbitraryBoundedEnum
instance Arbitrary MentsuKind where arbitrary = arbitraryBoundedEnum
instance Arbitrary Mentsu where
    arbitrary = do
        mk <- elements [Shuntsu, Koutsu, Kantsu]
        case mk of
            Shuntsu -> arbitraryShuntsu
            Koutsu  -> koutsu <$> arbitrary
            Kantsu  -> kantsu <$> arbitrary
            Jantou  -> discard -- Not reached

instance Arbitrary TileGroup where
    arbitrary = oneof
        [ (\t -> GroupWait Koutsu [t,t] [t]) <$> arbitrary
        , breakShuntsu =<< arbitraryShuntsu
        , GroupComplete <$> arbitrary
        , GroupLeftover <$> arbitrary
        ] where
            breakShuntsu ms =
                let [t,t',t''] = mentsuTiles ms
                    in elements
                        [ GroupWait Shuntsu [t , t' ] (catMaybes [predMay t, Just t''])
                        , GroupWait Shuntsu [t', t''] (t : catMaybes [succMay t''])
                        , GroupWait Shuntsu [t , t''] [t']
                        ]

arbitraryShuntsu :: Gen Mentsu
arbitraryShuntsu = shuntsu <$> (toSuited <$> (toEnum <$> choose (fromEnum Ii, fromEnum Chuu - 2)) <*> arbitrary)
