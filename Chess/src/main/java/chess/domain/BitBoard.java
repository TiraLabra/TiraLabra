package chess.domain;

import java.util.Arrays;

public final class BitBoard
{
	private final long[] pieces = new long[2 * Pieces.COUNT];

	private final long[] playerPieces = new long[2];

	public void clear()
	{
		for (int player = 0; player < 2; ++player) {
			for (int piece = 0; piece < Pieces.COUNT; ++piece)
				pieces[player * Pieces.COUNT + piece] = 0;
			playerPieces[player] = 0;
		}
	}

	public void addPiece(int player, int piece, int sqr)
	{
		long sqrBit = 1L << sqr;
		pieces[player * Pieces.COUNT + piece] |= sqrBit;
		playerPieces[player] |= sqrBit;
	}

	public void removePiece(int player, int piece, int sqr)
	{
		long sqrBit = 1L << sqr;
		pieces[player * Pieces.COUNT + piece] &= ~sqrBit;
		playerPieces[player] &= ~sqrBit;
	}

	public int removePiece(int player, int sqr)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (hasPiece(player, piece, sqr)) {
				removePiece(player, piece, sqr);
				return piece;
			}
		}
		return -1;
	}

	public long getPieces(int player, int piece)
	{
		return pieces[player * Pieces.COUNT + piece];
	}

	public long getPieces(int player)
	{
		return playerPieces[player];
	}

	public boolean hasPiece(int player, int piece, int sqr)
	{
		return (pieces[player * Pieces.COUNT + piece] & (1L << sqr)) != 0;
	}

	public boolean hasPiece(int player, int sqr)
	{
		return (playerPieces[player] & (1L << sqr)) != 0;
	}

	public boolean hasPiece(int sqr)
	{
		return ((playerPieces[Players.WHITE] | playerPieces[Players.BLACK]) & (1L << sqr)) != 0;
	}

	public int[] toArray()
	{
		int[] board = new int[64];
		Arrays.fill(board, -1);

		for (int player = 0; player < 2; ++player) {
			for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
				for (int sqr = 0; sqr < 64; ++sqr) {
					if ((pieces[player * Pieces.COUNT + pieceType] & (1L << sqr)) != 0)
						board[sqr] = player * Pieces.COUNT + pieceType;
				}
			}
		}

		return board;
	}

	public void copyFrom(BitBoard source)
	{
		for (int player = 0; player < 2; ++player) {
			for (int piece = 0; piece < Pieces.COUNT; ++piece) {
				int p = player * Pieces.COUNT + piece;
				pieces[p] = source.pieces[p];
			}
			playerPieces[player] = source.playerPieces[player];
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		BitBoard bb2 = (BitBoard) obj;
		for (int i = Pieces.COUNT - 1; i >= 0; --i) {
			if (pieces[i] != bb2.pieces[i])
				return false;
			if (pieces[Pieces.COUNT + i] != bb2.pieces[Pieces.COUNT + i])
				return false;
		}

		return true;
	}
}
