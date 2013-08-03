package chess.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Tallentaa nappuloiden sijainnit ja muut pelitilanteen tiedot. (Sallitut tornitukset,
 * ohestalyönnit ja aikaisemmat laudan tilanteet 50 vuoron ajalta.)
 */
public class GameState
{
	private int[][] board = new int[64][2];

	public GameState()
	{
		clearBoard();
		setupInitialPosition();
	}

	private void clearBoard()
	{
		for (int i = 0; i < 64; ++i) {
			this.board[i][0] = -1;
			this.board[i][1] = -1;
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
		int sqr = row * 8 + col;
		board[sqr][0] = 1;
		board[sqr][1] = piece;
		int sqr2 = (7 - row) * 8 + col;
		board[sqr2][0] = 0;
		board[sqr2][1] = piece;
	}

	public int[][] getBoard()
	{
		return board;
	}

	public void move(int from, int to)
	{
		board[to][0] = board[from][0];
		board[to][1] = board[from][1];
		board[from][0] = -1;
		board[from][1] = -1;
	}

	public long getAllowedMoves(int sqr)
	{
		long moves = 0;
		if (board[sqr][0] < 0)
			return moves;

		int row = sqr / 8;
		int col = sqr % 8;
		int player = board[sqr][0];

		switch (board[sqr][1]) {
			case Pieces.KING:
				moves |= getMove(row - 1, col - 1, player);
				moves |= getMove(row - 1, col, player);
				moves |= getMove(row - 1, col + 1, player);
				moves |= getMove(row, col - 1, player);
				moves |= getMove(row, col + 1, player);
				moves |= getMove(row + 1, col - 1, player);
				moves |= getMove(row + 1, col, player);
				moves |= getMove(row + 1, col + 1, player);
				break;
			case Pieces.QUEEN:
				moves |= getLineMoves(row, col, -1, -1);
				moves |= getLineMoves(row, col, -1, 0);
				moves |= getLineMoves(row, col, -1, 1);
				moves |= getLineMoves(row, col, 0, -1);
				moves |= getLineMoves(row, col, 0, 1);
				moves |= getLineMoves(row, col, 1, -1);
				moves |= getLineMoves(row, col, 1, 0);
				moves |= getLineMoves(row, col, 1, 1);
				break;
			case Pieces.ROOK:
				moves |= getLineMoves(row, col, -1, 0);
				moves |= getLineMoves(row, col, 0, -1);
				moves |= getLineMoves(row, col, 0, 1);
				moves |= getLineMoves(row, col, 1, 0);
				break;
			case Pieces.BISHOP:
				moves |= getLineMoves(row, col, -1, -1);
				moves |= getLineMoves(row, col, -1, 1);
				moves |= getLineMoves(row, col, 1, -1);
				moves |= getLineMoves(row, col, 1, 1);
				break;
			case Pieces.KNIGHT:
				moves |= getMove(row - 2, col - 1, player);
				moves |= getMove(row - 2, col + 1, player);
				moves |= getMove(row - 1, col - 2, player);
				moves |= getMove(row - 1, col + 2, player);
				moves |= getMove(row + 2, col - 1, player);
				moves |= getMove(row + 2, col + 1, player);
				moves |= getMove(row + 1, col - 2, player);
				moves |= getMove(row + 1, col + 2, player);
				break;
			case Pieces.PAWN:
				int nextRow = row - 1 + 2 * player;
				if ((nextRow & ~7) == 0) {
					if (board[nextRow * 8 + col][0] < 0) {
						moves |= 1L << nextRow * 8 + col;
						int nextRow2 = row - 2 + 4 * player;
						if ((nextRow2 & ~7) == 0
								&& row == 6 - 5 * player && board[nextRow2 * 8 + col][0] < 0)
							moves |= 1L << nextRow2 * 8 + col;
					}
					if (col > 0 && board[nextRow * 8 + col - 1][0] == 1 - player)
						moves |= 1L << nextRow * 8 + col - 1;
					if (col < 7 && board[nextRow * 8 + col + 1][0] == 1 - player)
						moves |= 1L << nextRow * 8 + col + 1;
				}

				break;
		}

		//TODO ohestalyönti, tornitus

		return moves;
	}

	private long getLineMoves(int row, int col, int dr, int dc)
	{
		long moves = 0;
		int player = board[row * 8 + col][0];
		for (;;) {
			row += dr;
			col += dc;
			long move = getMove(row, col, player);
			if (move != 0) {
				moves |= move;
				if (board[row * 8 + col][0] == 1 - player)
					break;
			} else
				break;
		}
		return moves;
	}

	private long getMove(int row, int col, int player)
	{
		if (((row | col) & ~7) != 0)
			return 0;

		if (board[row * 8 + col][0] == player)
			return 0;

		return 1L << row * 8 + col;
	}
}
