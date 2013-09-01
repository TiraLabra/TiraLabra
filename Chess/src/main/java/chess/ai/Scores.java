package chess.ai;

import chess.domain.Pieces;
import chess.domain.Players;

/**
 * Vakiot joita käytetään pelitilanteen pisteytyksessä.
 */
final class Scores
{
	/**
	 * Pienin mahdollinen pistemäärä. (Huom. Integer.MIN_VALUE ei kelpaa, koska sen vastaluku
	 * aiheuttaa ylivuodon.)
	 */
	static final int MIN = -Integer.MAX_VALUE;

	/**
	 * Suurin mahdollinen pistemäärä.
	 */
	static final int MAX = -MIN;

	/**
	 * Määrä, jolla mattitilanteen arvoa pienennetään siirtoa kohden, jotta mattiin pyritään niin
	 * aikaisin kuin mahdollista, ja mattiin joutumista viivytetään niin pitkään kuin mahdollista.
	 * Tulee olla isompi kuin suurin mahdollinen nappuloiden yhteispistemäärän (pl. kuningas)
	 * erotus.
	 */
	static final int CHECK_MATE_DEPTH_ADJUSTMENT = 1000 * 100;

	/**
	 * Arvo, jota (itseisarvoltaan) suurempi pistemäärä on aina matti.
	 */
	static final int CHECK_MATE_THRESHOLD = 1000 * 100;

	/**
	 * Tasapelin pistemäärä.
	 */
	static final int DRAW = 0;

	/**
	 * Nappuloiden arvot, joita käytetään pelitilanteiden pistemäärien arvioimiseen. Arvot on
	 * skaalattu 1000:lla, jotta pienempiä arvoja voidaan käyttää muita pisteytyskriteerejä
	 * varten.
	 *
	 * Myös kuninkaalle on annettu pistemäärä, koska MinMaxAI perustuu pseudolaillisiin siirtoihin,
	 * jotka voivat mahdollistaa kuninkaan lyönnin.
	 */
	static final int[] PIECE_VALUES = new int[Pieces.COUNT];

	static {
		PIECE_VALUES[Pieces.KING] = 1000000 * 100;
		PIECE_VALUES[Pieces.QUEEN] = 9 * 100;
		PIECE_VALUES[Pieces.ROOK] = 5 * 100;
		PIECE_VALUES[Pieces.BISHOP] = 3 * 100;
		PIECE_VALUES[Pieces.KNIGHT] = 3 * 100;
		PIECE_VALUES[Pieces.PAWN] = 1 * 100;
	}

	/**
	 * Ruutukohtaiset lisäpisteet nappuloille.
	 */
	static final int[][][] POSITIONAL_PIECE_VALUES = new int[][][]{{
			// Kuningas
			{
				0, 2, 3, 4, 4, 3, 2, 0,
				2, 4, 5, 6, 6, 5, 4, 2,
				3, 5, 7, 8, 8, 7, 5, 3,
				3, 5, 8, 9, 9, 8, 5, 3,
				3, 5, 8, 9, 9, 8, 5, 3,
				3, 5, 7, 8, 8, 7, 5, 3,
				2, 4, 5, 6, 6, 5, 4, 2,
				0, 2, 3, 4, 5, 4, 3, 0
			},
			// Kuningatar
			{
				0, 2, 3, 4, 4, 3, 2, 0,
				2, 4, 5, 6, 6, 5, 4, 2,
				3, 5, 7, 8, 8, 7, 5, 3,
				3, 5, 8, 9, 9, 8, 5, 3,
				3, 5, 8, 9, 9, 8, 5, 3,
				3, 5, 7, 8, 8, 7, 5, 3,
				2, 4, 5, 6, 6, 5, 4, 2,
				0, 2, 3, 4, 4, 3, 3, 0
			},
			// Torni
			{
				0, 2, 2, 2, 2, 2, 2, 0,
				0, 2, 2, 2, 2, 2, 2, 0,
				0, 2, 2, 2, 2, 2, 2, 0,
				0, 2, 2, 2, 2, 2, 2, 0,
				0, 2, 2, 2, 2, 2, 2, 0,
				0, 2, 2, 2, 2, 2, 2, 0,
				0, 2, 2, 2, 2, 2, 2, 0,
				1, 2, 3, 4, 3, 3, 2, 1
			},
			// Lähetti
			{
				0, 1, 1, 1, 1, 1, 1, 0,
				1, 2, 3, 3, 3, 3, 2, 1,
				1, 3, 4, 6, 6, 4, 3, 1,
				1, 3, 7, 8, 8, 7, 3, 1,
				1, 3, 7, 9, 9, 7, 3, 1,
				2, 4, 5, 7, 7, 5, 4, 2,
				1, 4, 3, 3, 3, 3, 4, 1,
				0, 1, 1, 1, 1, 1, 1, 0
			},
			// Ratsu
			{
				0, 1, 1, 2, 2, 1, 1, 0,
				1, 2, 3, 5, 5, 3, 2, 1,
				3, 4, 6, 7, 7, 6, 4, 3,
				3, 5, 7, 9, 9, 7, 5, 3,
				3, 5, 7, 9, 9, 7, 5, 3,
				3, 4, 6, 8, 8, 6, 4, 3,
				1, 2, 3, 5, 5, 3, 2, 1,
				0, 1, 1, 2, 2, 1, 1, 0
			},
			// Sotilas
			{
				0, 0, 0, 0, 0, 0, 0, 0,
				7, 8, 9, 9, 9, 9, 8, 7,
				6, 7, 8, 8, 8, 8, 7, 6,
				5, 6, 7, 7, 7, 7, 6, 5,
				4, 5, 6, 6, 6, 6, 5, 4,
				3, 4, 4, 3, 3, 4, 4, 3,
				2, 2, 2, 0, 0, 2, 2, 2,
				0, 0, 0, 0, 0, 0, 0, 0
			}
		},
		{}
	};

	static {
		// Muodostetaan lopulliset pisteet lisäämällä nappuloiden arvot, ja kopioidaan samat arvot
		// mustille nappuloille, mutta peilattuna.
		POSITIONAL_PIECE_VALUES[Players.BLACK] = new int[Pieces.COUNT][64];
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			for (int sqr = 0; sqr < 64; ++sqr) {
				POSITIONAL_PIECE_VALUES[Players.WHITE][pieceType][sqr] += PIECE_VALUES[pieceType];
				int blackSqr = (7 - sqr / 8) * 8 + sqr % 8;
				POSITIONAL_PIECE_VALUES[Players.BLACK][pieceType][blackSqr] =
						POSITIONAL_PIECE_VALUES[Players.WHITE][pieceType][sqr];
			}
		}
	}
}
