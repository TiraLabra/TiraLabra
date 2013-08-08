package chess.ai;

import chess.domain.GameState;
import chess.domain.Pieces;
import java.util.Random;

/**
 * Tekoäly, joka valitsee siirron satunnaisesti.
 */
public class RandomAI implements AI
{
	private Random rnd = new Random();

	@Override
	public void move(GameState state)
	{
		int player = state.getNextMovingPlayer();

		int fromSqr;
		long moves;
		do {
			fromSqr = rnd.nextInt(64);
			moves = state.getLegalMoves(fromSqr);
		} while (state.getBoard()[fromSqr] / Pieces.COUNT != player || moves == 0);

		int toSqr;
		do {
			toSqr = rnd.nextInt(64);
		} while ((moves & (1L << toSqr)) == 0);

		state.move(fromSqr, toSqr);
	}

	@Override
	public void setLoggingEnabled(boolean enabled)
	{
	}
}
