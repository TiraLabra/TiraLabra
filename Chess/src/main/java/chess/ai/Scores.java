package chess.ai;

import chess.domain.Pieces;

/**
 * Vakiot joita käytetään pelitilanteen pisteytyksessä.
 */
class Scores
{
	/**
	 * Pienin mahdollinen pistemäärä. (Huom. Integer.MIN_VALUE ei kelpaa, koska sen vastaluku
	 * aiheuttaa ylivuodon.)
	 */
	static final int MIN = -Integer.MAX_VALUE;

	/**
	 * Suurin mahdollinen pistemäärä.
	 */
	static final int MAX = Integer.MAX_VALUE;

	/**
	 * Määrä, jolla mattitilanteen arvoa pienennetään siirtoa kohden, jotta mattiin pyritään niin
	 * aikaisin kuin mahdollista, ja mattiin joutumista viivytetään niin pitkään kuin mahdollista.
	 * Tulee olla isompi kuin suurin mahdollinen nappuloiden arvon muutos yhdessä siirrossa.
	 */
	static final int CHECK_MATE_DEPTH_ADJUSTMENT = 10 * 1000;

	/**
	 * Arvo, jota (itseisarvoltaan) suurempi pistemäärä on aina matti.
	 */
	static final int CHECK_MATE_THRESHOLD = 300 * 1000;

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
		PIECE_VALUES[Pieces.KING] = 1000 * 1000;
		PIECE_VALUES[Pieces.QUEEN] = 9 * 1000;
		PIECE_VALUES[Pieces.ROOK] = 5 * 1000;
		PIECE_VALUES[Pieces.BISHOP] = 3 * 1000;
		PIECE_VALUES[Pieces.KNIGHT] = 3 * 1000;
		PIECE_VALUES[Pieces.PAWN] = 1 * 1000;
	}
}
