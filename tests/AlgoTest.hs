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
module AlgoTest ( algoTests ) where

import Import
import Test.Tasty.QuickCheck as QC
import Test.Tasty.HUnit as HU

import Mahjong.Hand.Algo
import Mahjong.Hand.Mentsu
import Mahjong.Tiles

algoTests :: TestTree
algoTests = testGroup "Algorithm tests"
    [ tgSplitTests "tilesGroupL" tilesGroupL
    , tgSplitTests "tilesSplitGroupL" tilesSplitGroupL
    , props_shanten
    ]

tgSplitTests :: TestName -> ([Tile] -> [[TileGroup]]) -> TestTree
tgSplitTests desc fun = testGroup desc
    [ QC.testProperty "[n, n+1, n+2] .<-- a shuntsu for any suited tile" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                fun [t, fromJust (succMay t), fromJust (succMay t >>= succMay) ]
                       .<--
                       [[GroupComplete (shuntsu t)]]

    , QC.testProperty "[t, t] .<-- a koutsu wait for t" $ \t ->
        fun [t,t] .<-- [[GroupWait Koutsu [t, t] [t]]]

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

    , QC.testProperty "<<tiles of some mentsu>> .<-- <<the same some mentsu>>" $ \ms ->
        length ms <= 8 ==> fun (concatMap mentsuTiles ms) .<-- [ map GroupComplete ms ]
        -- XXX: If you don't restrict the number of mentsu, the current
        -- tilesGroupL is inefficient enough that it explodes at exactly
        -- 9 mentsu (8 mentsu completes in split seconds). That should not
        -- be a problem as a hand has only 4 mentsu + pair, though.

    , QC.testProperty "<<tiles of some TileGroups>> .<-- <<the same TileGroups>>" $ \ml ->
        -- traceShow ml $
        length ml <= 5 ==> fun (concatMap tileGroupTiles ml) .<-- [ml]
    ]

props_shanten :: TestTree
props_shanten = testGroup "`shanten` properties"
    [ HU.testCase "[TileGroup] complete hand"         $ Just (-1) @=? shanten shallowCompleteHand
    , HU.testCase "[TileGroup] invalid complete hand" $ Nothing   @=? shanten shallowInvalidCompleteHand
    , HU.testCase "[TileGroup] tenpai hand"           $ Just 0    @=? shanten shallowTenpaiHand
    , HU.testCase "[TileGroup] iishanten"             $ Just 1    @=? shanten shallowIishanten
    , HU.testCase "[[TileGroup]] with a complete hand" $ Just (-1) @=? shanten
        [ shallowInvalidCompleteHand
        , shallowCompleteHand
        , shallowTenpaiHand
        , shallowIishanten
        ]
    ]

shallowInvalidCompleteHand = GroupWait Shuntsu undefined undefined : replicate 4 (GroupComplete undefined)
shallowCompleteHand = GroupWait Koutsu undefined undefined : replicate 4 (GroupComplete undefined)
shallowTenpaiHand = GroupLeftover undefined : replicate 4 (GroupComplete undefined)
shallowIishanten = GroupLeftover undefined : GroupLeftover undefined : GroupWait undefined undefined undefined : replicate 3 (GroupComplete undefined)
