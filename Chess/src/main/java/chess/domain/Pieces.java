package chess.domain;

/**
 * Nappuloita vastaavat kokonaislukuvakiot.
 */
public final class Pieces
{
	public static final int KING = 0;

	public static final int QUEEN = 1;

	public static final int ROOK = 2;

	public static final int BISHOP = 3;

	public static final int KNIGHT = 4;

	public static final int PAWN = 5;

	public static final int COUNT = 6;

	public static final int[] values = new int[Pieces.COUNT];

	static {
		values[Pieces.KING] = 1000 * 1000000;
		values[Pieces.QUEEN] = 9 * 1000000;
		values[Pieces.ROOK] = 5 * 1000000;
		values[Pieces.BISHOP] = 3 * 1000000;
		values[Pieces.KNIGHT] = 3 * 1000000;
		values[Pieces.PAWN] = 1 * 1000000;
	}

	private Pieces()
	{
	}
}
