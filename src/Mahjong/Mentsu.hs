------------------------------------------------------------------------------
-- | 
-- Module         : Mahjong.Mentsu
-- Copyright      : (C) 2014 Samuli Thomasson
-- License        : BSD-style (see the file LICENSE)
-- Maintainer     : Samuli Thomasson <samuli.thomasson@paivola.fi>
-- Stability      : experimental
-- Portability    : non-portable
--
-- This module defines @Mentsu@ and provides functions to build them.
--
-- Functions for grouping a collection of tiles into mentsu and
-- "almost-mentsu-tile-pairs" are also provided; see for example
-- @mentsuFrom@.
------------------------------------------------------------------------------
module Mahjong.Mentsu where

import Data.List (sort)
import Control.Monad
import Control.Applicative
import Mahjong.Tiles

-- * Mentsu types

-- | For Shuntsu, the tile is the **first** tile in chronological order.
data Mentsu = Mentsu MentsuKind Tile (Maybe Shout)
            deriving (Show, Read, Eq)

data MentsuKind = Shuntsu -- ^ 3 Tile straight
                | Koutsu -- ^ Triplet
                | Kantsu -- ^ Quadret
                | Jantou -- ^ Pair
                deriving (Show, Read, Eq)

-- | A mentsu can result from a shout; and a shout always produces
-- a mentsu.
data Shout = Pon { shoutedFrom :: Kazehai, shoutedTile :: Tile }
           | Kan { shoutedFrom :: Kazehai, shoutedTile :: Tile }
           | Chi { shoutedFrom :: Kazehai, shoutedTile :: Tile, shoutedTo :: [Tile] }
           | Ron { shoutedFrom :: Kazehai, shoutedTile :: Tile, shoutedTo :: [Tile] }
           deriving (Show, Read, Eq)

shuntsu, koutsu, kantsu, jantou :: Tile -> Mentsu
shuntsu = Mentsu Shuntsu `flip` Nothing
koutsu  = Mentsu Koutsu `flip` Nothing
kantsu  = Mentsu Kantsu `flip` Nothing
jantou  = Mentsu Jantou `flip` Nothing

-- * Functions that build mentsu

fromShout :: Shout -> Mentsu
fromShout shout = setShout $ case shout of
    Pon{} -> koutsu (shoutedTile shout)
    Kan{} -> kantsu (shoutedTile shout)
    Chi{} -> shuntsu (minimum $ shoutedTile shout : shoutedTo shout)
    Ron{}
        | [_]   <- shoutedTo shout         -> jantou (shoutedTile shout)
        | [x,y] <- shoutedTo shout, x == y -> koutsu (shoutedTile shout)
        | otherwise                       -> error "Impossible ron to a kantsu!"
    where
        setShout (Mentsu k t _) = Mentsu k t (Just shout)

-- | Get the mentsu kind
mentsuKind :: Mentsu -> MentsuKind
mentsuKind (Mentsu k _ _) = k

-- * Functions that operate on tiles

-- | `shuntsuWith tiles` attempts to build a shuntsu from `tiles`. Note
-- that **`tiles` must be in order**.
shuntsuWith :: [Tile] -> Maybe Mentsu
shuntsuWith (x:y:z:[]) = shuntsu x <$ do
    succMay x >>= guard . (== y)
    succMay y >>= guard . (== z)
shuntsuWith          _ = Nothing

-- * Group mentsu from tiles

-- | The type returned by @mentsuFrom@.
data MentsuLike = MentsuLike MentsuKind Tile -- ^ Missing one (the specified) tile
                | MentsuComplete Mentsu

-- | Build all enumerations of possible @MentsuLike@ groupings from given
-- tiles.
--
-- How the algorithm works:
--  * First the input tiles are sorted
--  * 
mentsuLike :: [Tile] -> [[MentsuLike]]
mentsuLike ts = go (sort ts)
    where
        go _ = undefined

-- Reference thingy from hajong.
--
-- | This gets only total mentsu: no orphan tiles are left in the result.
-- getMentsu :: [Tile] -> [Mentsus]
-- getMentsu tiles = map fst $ go ([], tiles)
--     where 
--         go :: ([Mentsu], [Tile]) -> [([Mentsu], [Tile])] -- (mentsu, leftovers)
--         go (done, xs@(a:b:es)) = let
-- 
--             takingJantou  = if a == b then go (Jantou [a,b] Nothing : done, es) ++ takingKoutsu else []
--             takingKoutsu  = case es of
--                 (c:es') | c == a      -> go (Koutsu [a,b,c] Nothing : done, es') ++ takingKantsu
--                         | otherwise   -> []
--                 _                     -> []
--             takingKantsu  = case es of
--                 (c:d:es') | c == d    -> go (Kantsu [a,b,c,d] Nothing : done, es')
--                           | otherwise -> []
--                 _                     -> []
--             takingShuntsu = case shuntsuOf a xs of
--                 Just ment   -> go (ment : done, xs L.\\ mentsuPai ment)
--                 Nothing     -> []
--             in takingJantou ++ takingShuntsu
-- 
--         go (done, []) = [(done, [])]
--         go (_,  _)    = [] -- branch cannot be complete with one orphan tile
