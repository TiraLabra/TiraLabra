{-# LANGUAGE OverloadedStrings, FlexibleInstances #-}
------------------------------------------------------------------------------
-- | 
-- Module         : Mahjong.Hand.Algo.WaitTree
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
------------------------------------------------------------------------------
module Mahjong.Hand.Algo.WaitTree
    (
    -- * WaitTree
    WaitTree, DevOp(..), TenpaiOp(..)

    -- * RootedTree
    , RootedTree(..), RootedBranch(..)
    -- ** Functions
    , minDepth, levels
    -- ** Unfolding
    , unfoldRootedTree, unfoldRootedBranch
    ) where

import Prelude hiding (minimum, foldl1)
import Data.Monoid
import Data.Foldable
import Data.Functor
import Data.Bifunctor
import Data.List.NonEmpty (NonEmpty)
import Text.PrettyPrint.ANSI.Leijen (Pretty(..))
import qualified Text.PrettyPrint.ANSI.Leijen as PP

import Mahjong.Tiles
import Mahjong.Hand.Value

-- * WaitTree

-- | A @WaitTree@ models the development options of an mahjong hand.
type WaitTree = RootedTree [Tile] DevOp TenpaiOp

-- | Development operation that waits for tile `waitFor` discarding
-- `waitDiscard`.
data DevOp = DevOp { opDiscard  :: Tile
                   , opFor      :: Tile
                   } deriving (Eq, Ord, Show)

-- | Getting the tile results in a win.
data TenpaiOp = TenpaiOp Tile Value
              deriving (Eq, Ord, Show)

-- Instances

instance Pretty DevOp where
    pretty (DevOp disc draw) = pretty disc <> " for " <> pretty draw

instance Pretty TenpaiOp where
    pretty (TenpaiOp draw val) = "Tenpai for " <> pretty draw <> " at " <> PP.int val <> " fu"

-- * RootedTree

-- | A rose tree with a single root of type `root`, leaves of type `leaf`
-- and inner data of type `inner`.
data RootedTree root inner leaf = RootedTree root [RootedBranch inner leaf]

data RootedBranch inner leaf = RootedLeaf leaf
                             | RootedBranch inner (NonEmpty (RootedBranch inner leaf))

-- | Build a tree from root and a seed value
unfoldRootedTree :: r -> (b -> Either l (i, NonEmpty b)) -> [b] -> RootedTree r i l
unfoldRootedTree root f seed = RootedTree root (map (unfoldRootedBranch f) seed)

unfoldRootedBranch :: (b -> Either l (i, NonEmpty b)) -> b -> RootedBranch i l
unfoldRootedBranch f b =
    either RootedLeaf (\(i,bs) -> RootedBranch i (unfoldRootedBranch f <$> bs)) $ f b

-- | Minimum depth. 0 when no branches.
minDepth :: RootedTree r i l -> Int
minDepth (RootedTree _ []) = 0
minDepth (RootedTree _ xs) = minimum (go <$> xs)
    where
        go (RootedLeaf _)      = 1
        go (RootedBranch _ bs) = 1 + minimum (go <$> bs)

-- | Nodes on every level /excluding/ root.
levels :: RootedTree r i l -> [[Either i l]]
levels (RootedTree _ []) = []
levels (RootedTree _ branches) = foldl' go [] branches
    where
        go :: [[Either i l]] -> RootedBranch i l -> [[Either i l]]
        go []                       x = go [[]] x
        go (x:xs) (RootedLeaf l)      = (Right l : x) : xs
        go (x:xs) (RootedBranch i bs) = (Left i : x) : foldl' go xs bs

-- Instances

instance Bifunctor (RootedTree root) where
    bimap f g (RootedTree root xs) = RootedTree root (bimap f g <$> xs)

instance Bifunctor RootedBranch where
    bimap f g (RootedBranch inner xs) = RootedBranch (f inner) (bimap f g <$> xs)
    bimap _ g (RootedLeaf outer)      = RootedLeaf (g outer)

instance (Pretty r, Pretty i, Pretty l) => Pretty (RootedTree [r] i l) where
    pretty (RootedTree root branches) =
        foldl1 (PP.<+>) (pretty <$> root)
        PP.<$$> prettyList branches
        PP.<+> PP.hardline

instance (Pretty inner, Pretty leaf) => Pretty (RootedBranch inner leaf) where
    pretty (RootedLeaf leaf)       = "└╼" PP.<+> pretty leaf
    pretty (RootedBranch inner xs) =
        "└┬╼" PP.<+> pretty inner PP.<$> PP.indent 1 (PP.align (prettyList (toList xs)))

    prettyList branches = foldl1 (PP.<$>) (pretty <$> branches)
