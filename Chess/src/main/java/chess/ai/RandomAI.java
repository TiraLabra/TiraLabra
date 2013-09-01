package chess.ai;

import chess.domain.GameState;
import chess.game.Player;
import java.util.Random;

/**
 * Tekoäly, joka valitsee siirron satunnaisesti.
 */
public class RandomAI implements Player
{
	private Random rnd = new Random();

	@Override
	public int getMove(GameState state)
	{
		int[] moves = state.getLegalMoves();
		return moves[rnd.nextInt(moves.length)];
	}
}
