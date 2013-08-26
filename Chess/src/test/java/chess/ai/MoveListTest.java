package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoveListTest
{
	private MoveList list;

	private int find(String moveStr)
	{
		int move = Move.fromString(moveStr);
		for (int pri = 0; pri < MoveList.PRIORITIES; ++pri) {
			for (int i = 0; i < list.getCount(pri); ++i) {
				if (list.getMove(pri, i) == move)
					return pri;
			}
		}
		return -1;
	}

	@Before
	public void setUp()
	{
		list = new MoveList();
		// 0  1  2  3  4  5  6  7
		// 8  9  10 11 12 13 14 15
		// 16 17 18 19 20 21 22 23
		// 24 25 26 27 28 29 30 31
		// 32 33 34 35 36 37 38 39
		// 40 41 42 43 44 45 46 47
		// 48 49 50 51 52 53 54 55
		// 56 57 58 59 60 61 62 63
		//
		// .  .  R  .  .  .  .  .
		// .  p  .  .  .  .  K  k
		// .  .  .  .  .  .  .  P
		// .  Q  .  .  .  .  .  n
		// .  .  p  .  .  .  .  .
		// .  .  .  .  .  q  .  .
		// .  .  .  .  R  .  .  .
		// .  .  .  .  .  .  .  .
		GameState state = new GameState("Kh7 Nh5 Qf3 b7 c4", "Rc8 Kg7 h6 Qb5 Re2", Players.WHITE);
		list.populate(state, false);
	}

	@Test
	public void newMoveListIsEmpty()
	{
		list = new MoveList();
		for (int pri = 0; pri < MoveList.PRIORITIES; ++pri)
			assertEquals(0, list.getCount(pri));
	}

	@Test
	public void kingCapturesHavePriority0()
	{
		assertEquals(0, find("Nh5xKg7"));
		assertEquals(0, find("Kh7xKg7"));
	}

	@Test
	public void normalCapturesHavePriorities1to9()
	{
		assertEquals(1, find("c4xQb5"));
		assertEquals(5, find("Qf3xRe2"));
		assertEquals(9, find("Kh7xh6"));
	}

	@Test
	public void capturePromotionsHaveCorrectPriorities()
	{
		assertEquals(2, find("b7xRc8Q"));
		assertEquals(2, find("b7xRc8N"));
	}

	@Test
	public void queenPromotionsHavePriority3()
	{
		assertEquals(3, find("b7-b8Q"));
	}

	@Test
	public void otherPromotionsHavePriority11()
	{
		assertEquals(11, find("b7-b8R"));
		assertEquals(11, find("b7-b8B"));
		assertEquals(11, find("b7-b8N"));
	}

	@Test
	public void normalMovesHavePriority10()
	{
		assertEquals(10, find("Kh7-h8"));
		assertEquals(10, find("Qf3-d5"));
	}

	@Test
	public void moveCountIsCorrect()
	{
		assertEquals(2, list.getCount(0));
		assertEquals(1, list.getCount(1));
		assertEquals(4, list.getCount(2));
		assertEquals(27, list.getCount(10));
		assertEquals(3, list.getCount(11));
	}

	@Test
	public void findsMovesFromCorrectPlayer()
	{
		GameState state = new GameState("", "e3", Players.BLACK);
		list.populate(state, false);
		assertEquals(10, find("e3-e2"));
	}

	@Test
	public void repopulatingRemovesOldMoves()
	{
		GameState state = new GameState("Nh8", "", Players.WHITE);
		list.populate(state, false);
		assertEquals(-1, find("c4xQb5"));
	}

	@Test
	public void hasCorrectMovesAfterRepopulating()
	{
		GameState state = new GameState("Nh8", "", Players.WHITE);
		list.populate(state, false);
		for (int pri = 0; pri < MoveList.PRIORITIES; ++pri)
			assertEquals(pri == 10 ? 2 : 0, list.getCount(pri));
	}
}
