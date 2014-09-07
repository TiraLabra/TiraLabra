{-# LANGUAGE OverloadedStrings #-}
------------------------------------------------------------------------------
-- |
-- Module         : AlgoTest
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- Mahjong.Hand.Algo tests
------------------------------------------------------------------------------
module AlgoTest where

import TestImport
import qualified Data.Set as S
import Test.Tasty.QuickCheck as QC
import Test.Tasty.HUnit as HU

import Mahjong.Hand.Algo
import Mahjong.Hand.Mentsu
import Mahjong.Tiles

algoTests :: TestTree
algoTests = testGroup "Algorithm tests"
    [ tgSplitTests "tilesGroupL" tilesGroupL
    , tgSplitTests "tilesSplitGroupL" tilesSplitGroupL
    , shantenTests
    , gwtTests
    ]

tgSplitTests :: TestName -> ([Tile] -> [[TileGroup]]) -> TestTree
tgSplitTests desc fun = testGroup desc

    [ QC.testProperty "[n, n+1, n+2] .<-- a shuntsu for any suited tile" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                fun (t : catMaybes [succMay t, succMay t >>= succMay])
                       .<--
                       [[GroupComplete (shuntsu t)]]

    , QC.testProperty "[t, t] .<-- a koutsu wait for t" $ \t -> fun [t,t] .<-- [[GroupWait Koutsu [t, t] [t]]]

    , QC.testProperty "[t, t, t, t+1, t+2] .<-- koutsu wait and shuntsu || koutsu and shuntsu wait" $ \n tk ->
        let t   = toSuited n tk
            t'  = fromJust (succMay t)
            t'' = fromJust (succMay t')
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                fun [t, t, t, t', t'']
                    .<--
                    [ [GroupComplete (koutsu t), GroupWait Shuntsu [t', t''] (t : catMaybes [succMay t'']) ]
                    , [GroupWait Koutsu [t, t] [t], GroupComplete (shuntsu t)  ]
                    ]

    , QC.testProperty "[t, t, t+1, t+1, t+2, t+2] .<-- shuntsu x2 || koutsu wait x3" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                fun (catMaybes [Just t, Just t, succMay t, succMay t, succMay t >>= succMay, succMay t >>= succMay])
                    .<--
                    [ [ GroupComplete (shuntsu t), GroupComplete (shuntsu t) ]
                    , [ GroupWait Koutsu [t,t] [t]
                      , let t' = fromJust $ succMay t in GroupWait Koutsu [t',t'] [t']
                      , let t' = fromJust $ succMay t >>= succMay in GroupWait Koutsu [t', t'] [t']
                      ]
                    ]

    , QC.testProperty "<<tiles of some mentsu>> .<-- <<the same some mentsu>>" $ do
        ms <- replicateM 6 arbitrary
        return $ fun (concatMap mentsuTiles ms) .<-- [ map GroupComplete ms ]
        -- 6 mentsu runs consistently with heap limit at 90mB

    , QC.testProperty "<<tiles of some TileGroups>> .<-- <<the same TileGroups>>" $ \ml ->
        -- traceShow ml $
        length ml <= 5 ==> fun (concatMap tileGroupTiles ml) .<-- [ml]
    ]

shantenTests :: TestTree
shantenTests = testGroup "`shanten` properties"

    [ HU.testCase "Grouping complete hand"          $ Just (-1) @=? shanten complete_1
    , HU.testCase "Grouping invalid complete hand"  $ Nothing   @=? shanten invalid_1
    , HU.testCase "Grouping tenpai hand"            $ Just 0    @=? shanten tenpai_1
    , HU.testCase "Grouping iishanten"              $ Just 1    @=? shanten iishanten_1
    , HU.testCase "[Grouping] with a complete hand" $ Just (-1) @=? shanten
        [ invalid_1
        , complete_1
        , tenpai_1
        , iishanten_1
        ]
    ]

gwtTests :: TestTree
gwtTests = testGroup "GWT: `buildGreedyWaitTree'`"
    [ HU.testCase "gwt depth correlates with shanten" $
        let testDepth (d, h) = minDepth (buildGWT h) @?= (d + 1)
            in mapM_ testDepth testHands

    , HU.testCase "testHands; No duplicate devops on one level" $
        let test (_, h) = (\l -> hasNoDups (levels l) @? show (levels l)) (buildGWT h)
            in mapM_ test testHands

    -- , QC.testProperty "buildGreedyWaitTree [] <<13tiles>> .. is total?"
    ]

hasNoDups :: Ord a => [a] -> Bool
hasNoDups xs = S.size (S.fromList xs) == length xs

--  * Shallow (*partial*) hands for testing

-- | a complete mentsu
men = GroupComplete (Mentsu Koutsu "W!" Nothing)

-- |
invalid_1 = GroupWait Shuntsu ["M1", "M2"] ["M3"] : replicate 4 men

testHands =
    [ (0, tenpai_1)
    , (0, tenpai_2)
    , (1, iishanten_1)
    , (1, iishanten_2)
    , (2, ryanshanten_1)
    ]

complete_1 = GroupWait Koutsu ["M4", "M4"] ["M4"] : replicate 4 men

-- | pair wait
tenpai_1 = GroupLeftover "S1"
         : replicate 4 men

-- | double pair wait
tenpai_2 = GroupWait Koutsu ["M1", "M1"] ["M1"]
         : GroupWait Koutsu ["M2", "M2"] ["M2"]
         : replicate 3 men

-- | shuntsu wait, two stray
iishanten_1 = GroupLeftover "S1"
            : GroupLeftover "S5"
            : GroupWait Shuntsu ["M4", "M5"] ["M3", "M6"]
            : replicate 3 men

-- | 2x koutsu wait, one stray
iishanten_2 = GroupLeftover "P1"
            : GroupWait Koutsu ["M3"] ["M3"]
            : GroupWait Koutsu ["S3"] ["S3"]
            : GroupWait Shuntsu ["S2", "S3"] ["S1", "S4"]
            : replicate 2 men

-- | four stray
ryanshanten_1 = GroupLeftover "P1"
              : GroupLeftover "M1"
              : GroupLeftover "S1"
              : GroupLeftover "S9"
              : replicate 3 men

ryanshanten_2 = GroupLeftover "P1"
              : GroupWait Shuntsu ["M1", "M3"] ["M2"]
              : GroupWait Shuntsu ["M4", "M6"] ["M5"]
              : GroupWait Shuntsu ["S1", "S3"] ["S2"]
              : replicate 2 men
