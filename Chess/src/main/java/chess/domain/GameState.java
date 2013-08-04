package chess.domain;

/**
 * Tallentaa nappuloiden sijainnit ja muut pelitilanteen tiedot. (Sallitut tornitukset,
 * ohestalyönnit ja aikaisemmat laudan tilanteet 50 vuoron ajalta.)
 */
public final class GameState
{
	private final BitBoard bitboard = new BitBoard();

	private int player = Players.WHITE;

	public GameState()
	{
		setupInitialPosition();
	}

	public int getPlayer()
	{
		return player;
	}

	private void setupInitialPosition()
	{
		addInitialPiece(0, 0, Pieces.ROOK);
		addInitialPiece(0, 1, Pieces.KNIGHT);
		addInitialPiece(0, 2, Pieces.BISHOP);
		addInitialPiece(0, 3, Pieces.QUEEN);
		addInitialPiece(0, 4, Pieces.KING);
		addInitialPiece(0, 5, Pieces.BISHOP);
		addInitialPiece(0, 6, Pieces.KNIGHT);
		addInitialPiece(0, 7, Pieces.ROOK);
		for (int i = 0; i < 8; ++i)
			addInitialPiece(1, i, Pieces.PAWN);
	}

	private void addInitialPiece(int row, int col, int piece)
	{
		int sqr = row * 8 + col;
		bitboard.addPiece(Players.BLACK, piece, sqr);
		int sqr2 = (7 - row) * 8 + col;
		bitboard.addPiece(Players.WHITE, piece, sqr2);
	}

	public int[] getBoard()
	{
		return bitboard.toArray();
	}

	public void move(int from, int to)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (bitboard.hasPiece(player, piece, from)) {
				bitboard.removePiece(player, piece, from);
				bitboard.addPiece(player, piece, to);
				break;
			}
		}

		bitboard.removePiece(1 - player, to);

		player = 1 - player;
	}

	public void undoMove()
	{
	}

	public long getAllowedMoves(int sqr)
	{
		long moves = 0;

		int row = sqr / 8;
		int col = sqr % 8;

		if (bitboard.hasPiece(player, Pieces.KING, sqr)) {
			moves |= getMove(row - 1, col - 1, player);
			moves |= getMove(row - 1, col, player);
			moves |= getMove(row - 1, col + 1, player);
			moves |= getMove(row, col - 1, player);
			moves |= getMove(row, col + 1, player);
			moves |= getMove(row + 1, col - 1, player);
			moves |= getMove(row + 1, col, player);
			moves |= getMove(row + 1, col + 1, player);
		} else if (bitboard.hasPiece(player, Pieces.QUEEN, sqr)) {
			moves |= getLineMoves(row, col, -1, -1);
			moves |= getLineMoves(row, col, -1, 0);
			moves |= getLineMoves(row, col, -1, 1);
			moves |= getLineMoves(row, col, 0, -1);
			moves |= getLineMoves(row, col, 0, 1);
			moves |= getLineMoves(row, col, 1, -1);
			moves |= getLineMoves(row, col, 1, 0);
			moves |= getLineMoves(row, col, 1, 1);
		} else if (bitboard.hasPiece(player, Pieces.ROOK, sqr)) {
			moves |= getLineMoves(row, col, -1, 0);
			moves |= getLineMoves(row, col, 0, -1);
			moves |= getLineMoves(row, col, 0, 1);
			moves |= getLineMoves(row, col, 1, 0);
		} else if (bitboard.hasPiece(player, Pieces.BISHOP, sqr)) {
			moves |= getLineMoves(row, col, -1, -1);
			moves |= getLineMoves(row, col, -1, 1);
			moves |= getLineMoves(row, col, 1, -1);
			moves |= getLineMoves(row, col, 1, 1);
		} else if (bitboard.hasPiece(player, Pieces.KNIGHT, sqr)) {
			moves |= getMove(row - 2, col - 1, player);
			moves |= getMove(row - 2, col + 1, player);
			moves |= getMove(row - 1, col - 2, player);
			moves |= getMove(row - 1, col + 2, player);
			moves |= getMove(row + 2, col - 1, player);
			moves |= getMove(row + 2, col + 1, player);
			moves |= getMove(row + 1, col - 2, player);
			moves |= getMove(row + 1, col + 2, player);
		} else if (bitboard.hasPiece(player, Pieces.PAWN, sqr)) {
			int nextRow = row - 1 + 2 * player;
			if ((nextRow & ~7) == 0) {
				if (!bitboard.hasPiece(nextRow * 8 + col)) {
					moves |= 1L << nextRow * 8 + col;
					int nextRow2 = row - 2 + 4 * player;
					if (row == 6 - 5 * player && (nextRow2 & ~7) == 0
							&& !bitboard.hasPiece(nextRow2 * 8 + col))
						moves |= 1L << nextRow2 * 8 + col;
				}
				if (col > 0 && bitboard.hasPiece(1 - player, nextRow * 8 + col - 1))
					moves |= 1L << nextRow * 8 + col - 1;
				if (col < 7 && bitboard.hasPiece(1 - player, nextRow * 8 + col + 1))
					moves |= 1L << nextRow * 8 + col + 1;
			}
		}

		//TODO ohestalyönti, tornitus

		return moves;
	}

	private long getLineMoves(int row, int col, int dr, int dc)
	{
		long moves = 0;
		for (;;) {
			row += dr;
			col += dc;

			long move = getMove(row, col, player);
			if (move == 0)
				break;

			moves |= move;
			if (bitboard.hasPiece(1 - player, row * 8 + col))
				break;
		}
		return moves;
	}

	private long getMove(int row, int col, int player)
	{
		if (((row | col) & ~7) != 0)
			return 0;

		int sqr = row * 8 + col;
		if (bitboard.hasPiece(player, sqr))
			return 0;

		return 1L << sqr;
	}
}
