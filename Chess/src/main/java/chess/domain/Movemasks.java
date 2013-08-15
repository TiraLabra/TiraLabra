package chess.domain;

/**
 * Bittimaskit nopeaa siirtojen generointia yms varten.
 * //TODO lähetti/torni/kuningatar (magic bitmasks)
 */
public final class Movemasks
{
	/**
	 * Bittimaskit ratsun siirroista kussakin pelilaudan ruudussa.
	 */
	public static final long[] KING_MOVES = new long[64];

	/**
	 * Bittimaskit kuninkaan siirroista kussakin pelilaudan ruudussa.
	 */
	public static final long[] KNIGHT_MOVES = new long[64];

	/**
	 * Generoi kaikki siirtojen bittimaskit.
	 */
	static {
		for (int sqr = 0; sqr < 64; ++sqr) {
			int row = sqr / 8;
			int col = sqr % 8;
			KING_MOVES[sqr] = generateKingMoves(row, col);
			KNIGHT_MOVES[sqr] = generateKnightMoves(row, col);
		}
	}

	/**
	 * Generoi bittimaskin kuninkaan siirroista yhdessä ruudussa.
	 *
	 * @param row rivi
	 * @param col sarake
	 * @return siirrot bittimaskina
	 */
	private static long generateKingMoves(int row, int col)
	{
		long moves = 0;
		moves |= getMove(row - 1, col - 1);
		moves |= getMove(row - 1, col);
		moves |= getMove(row - 1, col + 1);
		moves |= getMove(row, col - 1);
		moves |= getMove(row, col + 1);
		moves |= getMove(row + 1, col - 1);
		moves |= getMove(row + 1, col);
		moves |= getMove(row + 1, col + 1);
		return moves;
	}

	/**
	 * Generoi bittimaskin ratsun siirroista yhdessä ruudussa.
	 *
	 * @param row rivi
	 * @param col sarake
	 * @return siirrot bittimaskina
	 */
	private static long generateKnightMoves(int row, int col)
	{
		long moves = 0;
		moves |= getMove(row - 2, col - 1);
		moves |= getMove(row - 2, col + 1);
		moves |= getMove(row - 1, col - 2);
		moves |= getMove(row - 1, col + 2);
		moves |= getMove(row + 2, col - 1);
		moves |= getMove(row + 2, col + 1);
		moves |= getMove(row + 1, col - 2);
		moves |= getMove(row + 1, col + 2);
		return moves;
	}

	/**
	 * Muodostaa bittimaskin yhdestä ruudusta, jos se sijaitsee pelilaudalla.
	 *
	 * @param row rivi
	 * @param col sarake
	 * @return bittimaski
	 */
	private static long getMove(int row, int col)
	{
		if (((row | col) & ~7) != 0)
			return 0;
		return 1L << row * 8 + col;
	}
}
