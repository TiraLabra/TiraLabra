------------------------------------------------------------------------------
-- | 
-- Module         : Mahjong.Hand.DevelopmentTree
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- "DevelopmentTree" models (greedy) development options of an mahjong
-- hand.
------------------------------------------------------------------------------
module Mahjong.Hand.DevelopmentTree where

import Data.Tree
import Mahjong.Tiles
import Mahjong.Mentsu
import Mahjong.Hand.Value

-- | An operation that develops the hand: In `(disc, drawn)` the tile
-- `drawn` is drawn from the wall and sequently the tile `disc` is
-- discarded.
type DevOp = (Tile, Tile)

-- | The nodes in a development tree represent either a hand development
-- operation (@DevOp@) (in internal nodes) or value of the hand that is
-- achieved by applying the DevOps on the path to root.
type DevelopmentTree = Tree (Either DevOp Value)

buildTree :: [Mentsu] -> [Tile] -> DevelopmentTree
buildTree ms ts = undefined

{- TODO vk3 ja eteenpäin

-- * Puu

-- | Epätyhjä useahaarainen puu, joka on erikseen parametrinen
-- solmukohtien elementtien (`a`) ja  lehtien (`l` tyypeissä.
data RoseTree a l = Branch a (NonEmpty (RoseTree a l))
                  | Leaf l

import qualified Data.Tree.DUAL as DUAL
-- |
--  * A path downwards represents a sequence of `(draw, discard)` pairs that
--    greedily get the hand closer to tenpai.
--    First argument to the DUALTree (`[DevSeq]`) gives the discard
--    sequence.
--  * A leaf stores the hand value.
type DevelopmentTree = DUAL.DUALTree [DevSeq] () DevSeq Value

-}
