package chess.ai;

import chess.domain.GameState;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RandomAITest
{
	private RandomAI ai;

	private GameState s;

	@Before
	public void setUp()
	{
		ai = new RandomAI();
		s = new GameState("Ka7 b7", "Ke8", Players.WHITE);
	}

	@Test
	public void canReturnAnyMove()
	{
		int[] moves = s.getLegalMoves();
		boolean[] used = new boolean[moves.length];

		for (int i = 0; i < 1000; ++i) {
			int move = ai.getMove(s);
			for (int j = 0; j < moves.length; ++j)
				used[j] |= move == moves[j];
			boolean allUsed = true;
			for (int j = 0; j < used.length; ++j) {
				if (!used[j])
					allUsed = false;
			}
			if (allUsed)
				return;
		}

		fail("All moves were not used.");
	}

	@Test
	public void returnsLegalMove()
	{
		int[] moves = s.getLegalMoves();

		for (int i = 0; i < 1000; ++i) {
			int move = ai.getMove(s);
			boolean found = false;
			for (int j = 0; j < moves.length; ++j)
				found |= move == moves[j];
			if (!found)
				fail("Illegal move returned");
		}
	}
}
