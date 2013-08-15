package chess.domain;

/**
 * Nappuloita vastaavat kokonaislukuvakiot. Vakiot ovat nappuloiden laskevassa arvojärjestyksessä.
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

	/**
	 * Nappuloiden yksikirjaimiset symbolit.
	 */
	public static final String[] SYMBOLS = new String[Pieces.COUNT];

	static {
		SYMBOLS[Pieces.KING] = "K";
		SYMBOLS[Pieces.QUEEN] = "Q";
		SYMBOLS[Pieces.ROOK] = "R";
		SYMBOLS[Pieces.BISHOP] = "B";
		SYMBOLS[Pieces.KNIGHT] = "N";
		SYMBOLS[Pieces.PAWN] = "";
	}
}
