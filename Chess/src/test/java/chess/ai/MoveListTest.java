package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
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
		// .  .  R  .  .  .  .  .
		// .  p  .  P  .  .  K  k
		// .  Q  .  .  .  .  .  P
		// .  .  p  .  .  .  .  n
		// .  .  .  .  .  q  .  .
		// .  .  .  .  R  .  .  .
		// .  .  .  .  .  .  .  .
		// .  .  .  .  .  .  .  .
		GameState state = new GameState("Kh7 Nh5 Qf4 b7 c5", "Rc8 Kg7 h6 Qb6 Re3 d7",
				Players.BLACK);
		state.makeMove(Move.fromString("d7-d5")); // Luo ohestalyöntitilanteen.
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
		assertEquals(1, find("c5xQb6"));
		assertEquals(5, find("Qf4xRe3"));
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
	public void enPassantHasPriority4()
	{
		assertEquals(4, find("c5xd6"));
	}

	@Test
	public void normalMovesHavePriority10()
	{
		assertEquals(10, find("Kh7-h8"));
		assertEquals(10, find("Qf4-d6"));
	}

	@Test
	public void moveCountIsCorrect()
	{
		assertEquals(2, list.getCount(0));
		assertEquals(1, list.getCount(1));
		assertEquals(4, list.getCount(2));
		assertEquals(27, list.getCount(10)); // Kh7:3 Nh5:2 Qf4:21 b7:0 c5:1
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
		assertEquals(-1, find("c5xQb6"));
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
