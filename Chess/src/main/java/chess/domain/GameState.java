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

	public List<Integer> getAllowedMoves(int sqr)
	{
		List<Integer> moves = new ArrayList<Integer>(27);

		if (board[sqr][0] < 0)
			return moves;

		int row = sqr / 8;
		int col = sqr % 8;
		int player = board[sqr][0];

		switch (board[sqr][1]) {
			case Pieces.KING:
				addMove(moves, row - 1, col - 1, player);
				addMove(moves, row - 1, col, player);
				addMove(moves, row - 1, col + 1, player);
				addMove(moves, row, col - 1, player);
				addMove(moves, row, col + 1, player);
				addMove(moves, row + 1, col - 1, player);
				addMove(moves, row + 1, col, player);
				addMove(moves, row + 1, col + 1, player);
				break;
			case Pieces.QUEEN:
				addLineMoves(moves, row, col, -1, -1);
				addLineMoves(moves, row, col, -1, 0);
				addLineMoves(moves, row, col, -1, 1);
				addLineMoves(moves, row, col, 0, -1);
				addLineMoves(moves, row, col, 0, 1);
				addLineMoves(moves, row, col, 1, -1);
				addLineMoves(moves, row, col, 1, 0);
				addLineMoves(moves, row, col, 1, 1);
				break;
			case Pieces.ROOK:
				addLineMoves(moves, row, col, -1, 0);
				addLineMoves(moves, row, col, 0, -1);
				addLineMoves(moves, row, col, 0, 1);
				addLineMoves(moves, row, col, 1, 0);
				break;
			case Pieces.BISHOP:
				addLineMoves(moves, row, col, -1, -1);
				addLineMoves(moves, row, col, -1, 1);
				addLineMoves(moves, row, col, 1, -1);
				addLineMoves(moves, row, col, 1, 1);
				break;
			case Pieces.KNIGHT:
				addMove(moves, row - 2, col - 1, player);
				addMove(moves, row - 2, col + 1, player);
				addMove(moves, row - 1, col - 2, player);
				addMove(moves, row - 1, col + 2, player);
				addMove(moves, row + 2, col - 1, player);
				addMove(moves, row + 2, col + 1, player);
				addMove(moves, row + 1, col - 2, player);
				addMove(moves, row + 1, col + 2, player);
				break;
			case Pieces.PAWN:
				int nextRow = row - 1 + 2 * player;
				if ((nextRow & ~7) == 0) {
					if (board[nextRow * 8 + col][0] < 0) {
						moves.add(nextRow * 8 + col);
						int nextRow2 = row - 2 + 4 * player;
						if ((nextRow2 & ~7) == 0
								&& row == 6 - 5 * player && board[nextRow2 * 8 + col][0] < 0)
							moves.add(nextRow2 * 8 + col);
					}
					if (col > 0 && board[nextRow * 8 + col - 1][0] == 1 - player)
						moves.add(nextRow * 8 + col - 1);
					if (col < 7 && board[nextRow * 8 + col + 1][0] == 1 - player)
						moves.add(nextRow * 8 + col + 1);
				}

				break;
		}

		//TODO ohestalyönti, tornitus

		return moves;
	}

	private void addLineMoves(List<Integer> moves, int row, int col, int dr, int dc)
	{
		int player = board[row * 8 + col][0];
		for (;;) {
			row += dr;
			col += dc;
			if (addMove(moves, row, col, player)) {
				if (board[row * 8 + col][0] == 1 - player)
					break;
			} else
				break;
		}
	}

	private boolean addMove(List<Integer> moves, int row, int col, int player)
	{
		if (((row | col) & ~7) != 0)
			return false;

		if (board[row * 8 + col][0] == player)
			return false;

		moves.add(row * 8 + col);
		return true;
	}
}
