package chess.domain;

/**
 * Tallentaa nappuloiden sijainnit ja muut pelitilanteen tiedot. (Sallitut tornitukset,
 * ohestalyönnit ja aikaisemmat laudan tilanteet 50 vuoron ajalta.)
 */
public class GameState
{
	private int[][][] board = new int[8][8][2];

	public GameState()
	{
		clearBoard();
		setupInitialPosition();
	}

	private void clearBoard()
	{
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j) {
				this.board[i][j][0] = -1;
				this.board[i][j][1] = -1;
			}
	}

	private void setupInitialPosition()
	{
		addPiece(0, 0, Pieces.ROOK);
		addPiece(0, 1, Pieces.KNIGHT);
		addPiece(0, 2, Pieces.BISHOP);
		addPiece(0, 3, Pieces.QUEEN);
		addPiece(0, 4, Pieces.KING);
		addPiece(0, 5, Pieces.BISHOP);
		addPiece(0, 6, Pieces.KNIGHT);
		addPiece(0, 7, Pieces.ROOK);
		for (int i = 0; i < 8; ++i)
			addPiece(1, i, Pieces.PAWN);
	}

	private void addPiece(int row, int col, int piece)
	{
		board[row][col][0] = 0;
		board[row][col][1] = piece;
		board[7 - row][col][0] = 1;
		board[7 - row][col][1] = piece;
	}

	public int[][][] getBoard()
	{
		return board;
	}
}
