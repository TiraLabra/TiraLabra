package chess.ai;

import chess.domain.GameState;
import java.util.List;
import java.util.Random;

/**
 * Tekoäly, joka valitsee siirron satunnaisesti.
 */
public class RandomAI implements AI
{
	private Random rnd = new Random();

	@Override
	public void move(GameState state, int player)
	{
		for (;;) {
			int from = rnd.nextInt(64);
			if (state.getBoard()[from][0] == player) {
				List<Integer> moves = state.getAllowedMoves(from);
				if (moves.isEmpty())
					continue;
				int to = moves.get(rnd.nextInt(moves.size()));
				state.move(from, to);
				break;
			}
		}
	}
}
