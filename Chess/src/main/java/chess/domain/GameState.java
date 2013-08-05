package chess.domain;

import java.util.Random;

/**
 * Tallentaa nappuloiden sijainnit ja muut pelitilanteen tiedot. (Sallitut tornitukset,
 * ohestalyönnit ja aikaisemmat laudan tilanteet 50 vuoron ajalta.)
 */
public final class GameState
{
	private final BitBoard bitboard = new BitBoard();

	private int nextMovingPlayer = Players.WHITE;

	public GameState()
	{
		setupInitialPosition();
	}

	public GameState(Random rnd)
	{
		randomize(rnd);
	}

	public int getNextMovingPlayer()
	{
		return nextMovingPlayer;
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

	public long getPieces(int player, int piece)
	{
		return bitboard.getPieces(player, piece);
	}

	public int move(int from, int to)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (bitboard.hasPiece(nextMovingPlayer, piece, from)) {
				bitboard.removePiece(nextMovingPlayer, piece, from);
				bitboard.addPiece(nextMovingPlayer, piece, to);
				break;
			}
		}

		int capturedPiece = bitboard.removePiece(1 - nextMovingPlayer, to);

		nextMovingPlayer = 1 - nextMovingPlayer;

		return capturedPiece;
	}

	public int move(int from, int to, int pieceType)
	{
		bitboard.removePiece(nextMovingPlayer, pieceType, from);
		bitboard.addPiece(nextMovingPlayer, pieceType, to);
		int capturedPiece = bitboard.removePiece(1 - nextMovingPlayer, to);
		nextMovingPlayer = 1 - nextMovingPlayer;
		return capturedPiece;
	}

	public void undoMove(int from, int to, int movedPiece, int capturedPiece)
	{
		nextMovingPlayer = 1 - nextMovingPlayer;

		bitboard.removePiece(nextMovingPlayer, movedPiece, to);
		bitboard.addPiece(nextMovingPlayer, movedPiece, from);
		if (capturedPiece != -1)
			bitboard.addPiece(1 - nextMovingPlayer, capturedPiece, to);
	}

	public long getLegalMoves(int fromSqr)
	{
		long moves = getPseudoLegalMoves(nextMovingPlayer, fromSqr);

		for (int toSqr = 0; toSqr < 64; ++toSqr) {
			if ((moves & (1L << toSqr)) != 0) {
				if (!isLegalMove(fromSqr, toSqr))
					moves &= ~(1L << toSqr);
			}
		}

		return moves;
	}

	private boolean isLegalMove(int fromSqr, int toSqr)
	{
		GameState copy = clone();
		copy.move(fromSqr, toSqr);
		return !copy.isKingChecked(nextMovingPlayer);
	}

	public long getPseudoLegalMoves(int player, int sqr)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (bitboard.hasPiece(nextMovingPlayer, piece, sqr))
				return getPseudoLegalMoves(player, piece, sqr);
		}

		return 0;
	}

	private long getLineMoves(int player, int row, int col, int dr, int dc)
	{
		long moves = 0;
		for (;;) {
			row += dr;
			col += dc;

			long move = getMove(player, row, col);
			if (move == 0)
				break;

			moves |= move;
			if (bitboard.hasPiece(1 - player, row * 8 + col))
				break;
		}
		return moves;
	}

	private long getMove(int player, int row, int col)
	{
		if (((row | col) & ~7) != 0)
			return 0;

		int sqr = row * 8 + col;
		if (bitboard.hasPiece(player, sqr))
			return 0;

		return 1L << sqr;
	}

	public long getAttackMoves(int player, int piece, int sqr)
	{
		long moves = 0;

		int row = sqr / 8;
		int col = sqr % 8;

		switch (piece) {
			case Pieces.KING:
				moves |= getMove(player, row - 1, col - 1);
				moves |= getMove(player, row - 1, col);
				moves |= getMove(player, row - 1, col + 1);
				moves |= getMove(player, row, col - 1);
				moves |= getMove(player, row, col + 1);
				moves |= getMove(player, row + 1, col - 1);
				moves |= getMove(player, row + 1, col);
				moves |= getMove(player, row + 1, col + 1);
				break;
			case Pieces.QUEEN:
				moves |= getLineMoves(player, row, col, -1, -1);
				moves |= getLineMoves(player, row, col, -1, 0);
				moves |= getLineMoves(player, row, col, -1, 1);
				moves |= getLineMoves(player, row, col, 0, -1);
				moves |= getLineMoves(player, row, col, 0, 1);
				moves |= getLineMoves(player, row, col, 1, -1);
				moves |= getLineMoves(player, row, col, 1, 0);
				moves |= getLineMoves(player, row, col, 1, 1);
				break;
			case Pieces.ROOK:
				moves |= getLineMoves(player, row, col, -1, 0);
				moves |= getLineMoves(player, row, col, 0, -1);
				moves |= getLineMoves(player, row, col, 0, 1);
				moves |= getLineMoves(player, row, col, 1, 0);
				break;
			case Pieces.BISHOP:
				moves |= getLineMoves(player, row, col, -1, -1);
				moves |= getLineMoves(player, row, col, -1, 1);
				moves |= getLineMoves(player, row, col, 1, -1);
				moves |= getLineMoves(player, row, col, 1, 1);
				break;
			case Pieces.KNIGHT:
				moves |= getMove(player, row - 2, col - 1);
				moves |= getMove(player, row - 2, col + 1);
				moves |= getMove(player, row - 1, col - 2);
				moves |= getMove(player, row - 1, col + 2);
				moves |= getMove(player, row + 2, col - 1);
				moves |= getMove(player, row + 2, col + 1);
				moves |= getMove(player, row + 1, col - 2);
				moves |= getMove(player, row + 1, col + 2);
				break;
			case Pieces.PAWN:
				int nextRow = row - 1 + 2 * player;
				if ((nextRow & ~7) == 0) {
					if (col > 0 && bitboard.hasPiece(1 - player, nextRow * 8 + col - 1))
						moves |= 1L << nextRow * 8 + col - 1;
					if (col < 7 && bitboard.hasPiece(1 - player, nextRow * 8 + col + 1))
						moves |= 1L << nextRow * 8 + col + 1;
				}
				break;
		}

		return moves;
	}

	public long getPseudoLegalMoves(int player, int piece, int sqr)
	{
		long moves = 0;

		int row = sqr / 8;
		int col = sqr % 8;

		switch (piece) {
			case Pieces.KING:
			case Pieces.QUEEN:
			case Pieces.ROOK:
			case Pieces.BISHOP:
			case Pieces.KNIGHT:
				moves = getAttackMoves(player, piece, sqr);
				break;
			case Pieces.PAWN:
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
				break;
		}

		//TODO ohestalyönti, tornitus
		return moves;
	}

	public boolean isCheckMate()
	{
		for (int sqr = 0; sqr < 64; ++sqr)
			if (getLegalMoves(sqr) != 0)
				return false;
		return isKingChecked(nextMovingPlayer);
	}

	public boolean isStaleMate()
	{
		for (int sqr = 0; sqr < 64; ++sqr)
			if (getLegalMoves(sqr) != 0)
				return false;
		return !isKingChecked(nextMovingPlayer);
	}

	private boolean isKingChecked(int defendingPlayer)
	{
		int kingSqr = getKingSquare(defendingPlayer);
		return isSquareThreatened(defendingPlayer, kingSqr);
	}

	private boolean isSquareThreatened(int defendingPLayer, int sqr)
	{
		int attackingPlayer = 1 - defendingPLayer;
		long sqrBit = 1L << sqr;
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			for (int attackingSqr = 0; attackingSqr < 64; ++attackingSqr)
				if (bitboard.hasPiece(attackingPlayer, piece, attackingSqr)) {
					long attackMoves = getAttackMoves(attackingPlayer, piece, attackingSqr);
					if ((sqrBit & attackMoves) != 0)
						return true;
				}
		}
		return false;
	}

	public int getKingSquare(int player)
	{
		int sqr = 0;
		for (; sqr < 64; ++sqr) {
			if (bitboard.hasPiece(player, Pieces.KING, sqr))
				break;
		}
		return sqr;
	}

	private GameState(GameState copyFrom)
	{
		nextMovingPlayer = copyFrom.nextMovingPlayer;
		bitboard.copyFrom(copyFrom.bitboard);
	}

	@Override
	public GameState clone()
	{
		return new GameState(this);
	}

	public boolean areBothKingsAlive()
	{
		return bitboard.getPieces(Players.WHITE, Pieces.KING) != 0
				&& bitboard.getPieces(Players.BLACK, Pieces.KING) != 0;
	}

	private void randomize(Random rnd)
	{
		do {
			bitboard.clear();
			addRandomizedPieces(1, 1, Pieces.KING, rnd);
			addRandomizedPieces(0, 1, Pieces.QUEEN, rnd);
			addRandomizedPieces(0, 2, Pieces.ROOK, rnd);
			addRandomizedPieces(0, 2, Pieces.BISHOP, rnd);
			addRandomizedPieces(0, 2, Pieces.KNIGHT, rnd);
			addRandomizedPieces(0, 8, Pieces.PAWN, rnd);
		} while (isCheckMate() || isKingChecked(Players.BLACK));
	}

	private void addRandomizedPieces(int min, int max, int pieceType, Random rnd)
	{
		for (int player = 0; player < 2; ++player) {
			int n = min + rnd.nextInt(1 + max - min);
			for (int i = 0; i < n; ++i) {
				int sqr;
				do {
					sqr = pieceType != Pieces.PAWN ? rnd.nextInt(64) : 8 + rnd.nextInt(48);
				} while (bitboard.hasPiece(sqr));
				bitboard.addPiece(player, pieceType, sqr);
			}
		}
	}
}
