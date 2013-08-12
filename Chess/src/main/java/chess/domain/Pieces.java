package chess.domain;

/**
 * Nappuloita vastaavat kokonaislukuvakiot.
 */
public final class Pieces
{
	/**
	 * Kuningas.
	 */
	public static final int KING = 0;

	/**
	 * Kuningatar.
	 */
	public static final int QUEEN = 1;

	/**
	 * Torni.
	 */
	public static final int ROOK = 2;

	/**
	 * Lähetti.
	 */
	public static final int BISHOP = 3;

	/**
	 * Ratsu.
	 */
	public static final int KNIGHT = 4;

	/**
	 * Sotilas.
	 */
	public static final int PAWN = 5;

	/**
	 * Nappulatyyppien lukumäärä.
	 */
	public static final int COUNT = 6;

	public static final String[] symbols = new String[Pieces.COUNT];

	static {
		symbols[Pieces.KING] = "K";
		symbols[Pieces.QUEEN] = "Q";
		symbols[Pieces.ROOK] = "R";
		symbols[Pieces.BISHOP] = "B";
		symbols[Pieces.KNIGHT] = "N";
		symbols[Pieces.PAWN] = "";
	}

	private Pieces()
	{
	}
}
