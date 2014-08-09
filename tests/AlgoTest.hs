------------------------------------------------------------------------------
-- | 
-- Module         : AlgoTest
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- Mahjong.Algo tests
------------------------------------------------------------------------------
module AlgoTest ( algoTests ) where

import Import
import Test.Tasty.QuickCheck as QC

import Mahjong.Algo
import Mahjong.Tiles
import Mahjong.Mentsu

algoTests :: TestTree
algoTests = testGroup "Algorithm tests"
    [ props_tilesGroupL
    ]

props_tilesGroupL :: TestTree
props_tilesGroupL = testGroup "tilesGroupL properties"
    [ QC.testProperty "tilesGroupL [n, n+1, n+2] is (only) a shuntsu for any suited tile" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                tilesGroupL [t, fromJust (succMay t), fromJust (succMay t >>= succMay) ]
                       .<--
                       [[GroupComplete (shuntsu t)]]

    , QC.testProperty "tilesGroupL [t, t] =~ a koutsu of t" $ \t ->
        tilesGroupL [t,t] .<-- [[GroupWait Koutsu [t, t] [t]]]

    , QC.testProperty "mensuLike [t, t, t, t+1, t+2]" $ \n tk ->
        let t   = toSuited n tk
            t'  = fromJust (succMay t)
            t'' = fromJust (succMay t')
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                tilesGroupL [t, t, t, t', t'']
                    .<--
                    [ [GroupComplete (koutsu t), GroupWait Shuntsu [t', t''] (t : catMaybes [succMay t'']) ]
                    , [GroupWait Koutsu [t, t] [t], GroupComplete (shuntsu t)  ]
                    ]
    , QC.testProperty "tilesGroupL [t, t, t+1, t+1, t+2, t+2]" $ \n tk ->
        let t = toSuited n tk
            in maybe False (/= maxBound) (tileNumber =<< succMay t) ==>
                tilesGroupL (catMaybes [Just t, Just t, succMay t, succMay t, succMay t >>= succMay, succMay t >>= succMay])
                    .<--
                    [ [ GroupComplete (shuntsu t), GroupComplete (shuntsu t) ]
                    , [ GroupWait Koutsu [t,t] [t]
                      , let t' = fromJust $ succMay t in GroupWait Koutsu [t',t'] [t']
                      , let t' = fromJust $ succMay t >>= succMay in GroupWait Koutsu [t', t'] [t']
                      ]
                    ]

    , QC.testProperty "tilesGroupL <tiles of some mentsu> `contains` <some mentsu>" $ \ms ->
        length ms <= 8 ==> tilesGroupL (concatMap mentsuTiles ms) .<-- [ map GroupComplete ms ]
        -- XXX: If you don't restrict the number of mentsu, the current
        -- tilesGroupL is inefficient enough that it explodes at exactly
        -- 9 mentsu (8 mentsu completes in split seconds). That should not
        -- be a problem as a hand has only 4 mentsu + pair, though.

    , QC.testProperty "tilesGroupL <tiles of some mentsulike> `contains` <...>" $ \ml ->
        -- traceShow ml $
        length ml <= 5 ==> tilesGroupL (concatMap tileGroupTiles ml) .<-- [ml]
    ]
