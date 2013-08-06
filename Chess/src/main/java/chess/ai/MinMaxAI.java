package chess.ai;

import chess.domain.GameState;
import chess.domain.Pieces;
import chess.util.Logger;

public class MinMaxAI implements AI
{
	private int bestMoveFrom;

	private int bestMoveTo;

	private static final int DEFAULT_SEARCH_DEPTH = 8;

	private final int searchDepth; // Pitää olla vähintään 2!

	private final Logger logger;

	private boolean loggingEnabled = false;

	public MinMaxAI(Logger logger)
	{
		this(logger, DEFAULT_SEARCH_DEPTH);
	}

	public MinMaxAI(Logger logger, int searchDepth)
	{
		this.logger = logger;
		this.searchDepth = searchDepth;
	}

	public void move(GameState state)
	{
		search(searchDepth, -Integer.MAX_VALUE, Integer.MAX_VALUE, state);
		state.move(bestMoveFrom, bestMoveTo);
	}

	private int search(int depth, int alpha, int beta, GameState state)
	{
		if (depth == 0 || !state.areBothKingsAlive())
			return getScore(state, depth);

		int player = state.getNextMovingPlayer();

		for (int pieceType = Pieces.COUNT - 1; pieceType >= 0; --pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);

				for (int capturedPiece = 0; capturedPiece < Pieces.COUNT; ++capturedPiece) {
					long captureMoves = moves & state.getPieces(1 - player, capturedPiece);
					int value = iterateMoves(depth, alpha, beta, state, pieceType, fromSqr,
							captureMoves);
					if (value >= beta)
						return value;
					alpha = Math.max(alpha, value);
				}
			}
		}

		for (int pieceType = Pieces.COUNT - 1; pieceType >= 0; --pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);
				moves &= ~state.getPieces(1 - player);
				int value = iterateMoves(depth, alpha, beta, state, pieceType, fromSqr, moves);
				if (value >= beta)
					return value;
				alpha = Math.max(alpha, value);
			}
		}

		return alpha;
	}

	private int iterateMoves(int depth, int alpha, int beta, GameState state, int pieceType,
			int fromSqr, long moves)
	{
		for (; moves != 0; moves -= Long.lowestOneBit(moves)) {
			int toSqr = Long.numberOfTrailingZeros(moves);

			int capturedPiece = state.move(fromSqr, toSqr, pieceType);
			int value = -search(depth - 1, -beta, -alpha, state);
			state.undoMove(fromSqr, toSqr, pieceType, capturedPiece);

			if (value >= beta)
				return value;

			if (value > alpha) {
				if (depth == searchDepth) {
					bestMoveFrom = fromSqr;
					bestMoveTo = toSqr;
					log("" + bestMoveFrom + " " + bestMoveTo + " "
							+ (value - getScore(state, 0)));
				}
				alpha = value;
			}
		}

		return alpha;
	}

	private int getScore(GameState state, int depth)
	{
		int player = state.getNextMovingPlayer();
		int score = -depth;
		Long.bitCount(bestMoveTo);
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			int pieceValue = Pieces.values[pieceType];
			score += Long.bitCount(state.getPieces(player, pieceType)) * pieceValue;
			score -= Long.bitCount(state.getPieces(1 - player, pieceType)) * pieceValue;
		}
		return score;
	}

	public void setLoggingEnabled(boolean enabled)
	{
		loggingEnabled = enabled;
	}

	private void log(String msg)
	{
		if (loggingEnabled)
			logger.logMessage(msg);
	}
}
