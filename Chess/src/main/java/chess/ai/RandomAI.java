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

		int from;
		long moves;
		do {
			from = rnd.nextInt(64);
			moves = state.getLegalMoves(from);
		} while (state.getBoard()[from] / Pieces.COUNT != player || moves == 0);

		int to;
		do {
			to = rnd.nextInt(64);
		} while ((moves & (1L << to)) == 0);

		state.move(from, to);
	}

	@Override
	public void setLoggingEnabled(boolean enabled)
	{
	}
}
