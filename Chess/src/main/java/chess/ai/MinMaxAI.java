package chess.ai;

import chess.domain.GameState;
import chess.domain.Pieces;

public class MinMaxAI implements AI
{
	private int bestMoveFrom;

	private int bestMoveTo;

	private static final int SEARCH_DEPTH = 6; // Pitää olla vähintään 2!

	public void move(GameState state)
	{
		search(SEARCH_DEPTH, -Integer.MAX_VALUE, Integer.MAX_VALUE, state);
		state.move(bestMoveFrom, bestMoveTo);
	}

	private int search(int depth, int alpha, int beta, GameState state)
	{
		if (depth == 0 || !state.areBothKingsAlive())
			return getScore(state, depth);

		int player = state.getNextMovingPlayer();

		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (int fromSqr = 0; fromSqr < 64; ++fromSqr) {
				if ((pieces & (1L << fromSqr)) == 0)
					continue;

				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);
				for (int toSqr = 0; toSqr < 64; ++toSqr) {
					if ((moves & (1L << toSqr)) == 0)
						continue;

					GameState newState = state.clone();
					newState.move(fromSqr, toSqr);

					int value = -search(depth - 1, -beta, -alpha, newState);

					if (value >= beta)
						return value;

					if (value > alpha) {
						if (depth == SEARCH_DEPTH) {
							bestMoveFrom = fromSqr;
							bestMoveTo = toSqr;
						}
						alpha = value;
					}
				}
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
}
