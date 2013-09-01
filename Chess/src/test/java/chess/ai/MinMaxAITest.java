package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MinMaxAITest
{
	private MinMaxAI ai;

	@Before
	public void setUp()
	{
		ai = new MinMaxAI(null, 3, 30, 0, 0);
	}

	private static void checkNode(Node node, int move, int alpha, int beta, int score,
			int nodeType)
	{
		assertEquals(move, node.move);
		assertEquals(alpha, node.alpha);
		assertEquals(beta, node.beta);
		assertEquals(score, node.score);
		assertEquals(nodeType, node.nodeType);
	}

	@Test
	public void avoidsCheckByEscaping() throws InterruptedException
	{
		GameState s = new GameState("Ka8", "Kb6 Bc6", Players.WHITE);
		assertEquals("Ka8-b8", Move.toString(ai.getMove(s)));
	}

	@Test
	public void avoidsCheckByCapturing() throws InterruptedException
	{
		GameState s = new GameState("Ke8", "Qe7 Kc3", Players.WHITE);
		assertEquals("Ke8xQe7", Move.toString(ai.getMove(s)));
	}

	@Test
	public void avoidsCheckByBlocking() throws InterruptedException
	{
		GameState s = new GameState("Ka8 Bb3", "Kb6 Be4 Bf4", Players.WHITE);
		assertEquals("Bb3-d5", Move.toString(ai.getMove(s)));
	}

	@Test
	public void canCheckMate() throws InterruptedException
	{
		GameState s = new GameState("Ke2 Rb4 Rd7", "Kf8", Players.WHITE);
		assertEquals("Rb4-b8", Move.toString(ai.getMove(s)));
	}

	@Test
	public void canFork() throws InterruptedException
	{
		GameState s = new GameState("Ka1 Qc1", "Kf8 Rb8", Players.WHITE);
		assertEquals("Qc1-f4", Move.toString(ai.getMove(s)));
	}

	@Test
	public void doesntStaleMateWhenHasMaterialAdvantage() throws InterruptedException
	{
		ai = new MinMaxAI(null, 5, 30, 0, 0);
		GameState s = new GameState("Ka8 Kb8", "Kh1", Players.BLACK);
		assertFalse("Qb8-g3".equals(Move.toString(ai.getMove(s))));
	}

	@Test
	public void staleMatesWhenHasMaterialDisadvantage() throws InterruptedException
	{
		GameState s = new GameState("Ka3 Qa1 Bb1 b3 a2 c2 Rb2", "Kh1 Bh3 c5 c3 Nb4", Players.BLACK);
		assertEquals("Bh3-d7", Move.toString(ai.getMove(s)));
	}

	@Test
	public void doesntMakeIllegalMoveWhenCheckMateInevitable() throws InterruptedException
	{
		ai = new MinMaxAI(null, 4, 30, 0, 0); // minimisyvyys mattitilanteen tunnistamiseksi
		GameState s = new GameState("Kb8", "Rc7 Kb6", Players.WHITE);
		assertEquals("Kb8-a8", Move.toString(ai.getMove(s)));
	}

	@Test
	public void promotesToKnightForFasterCheckMate() throws InterruptedException
	{
		GameState s = new GameState("Kc6 b7", "Ka6 a7 a5", Players.WHITE);
		assertEquals("b7-b8N", Move.toString(ai.getMove(s)));
	}

	@Test
	public void promotesToRookToAvoidStaleMate() throws InterruptedException
	{
		ai = new MinMaxAI(null, 6, 30, 0, 0);
		GameState s = new GameState("Ka7 g7 Qc5", "Kd7 Qd3", Players.WHITE);
		assertEquals("g7-g8R", Move.toString(ai.getMove(s)));
	}

	@Test
	public void testAlphaBetaPruning() throws InterruptedException
	{
		ai = new MinMaxAI(null, 2, 0, 0, 2);
		GameState s = new GameState("Kh1 g6 Nf7", "Ka8 Nh8", Players.BLACK);
		assertEquals("Nh8xg6", Move.toString(ai.getMove(s)));

		Node root = ai.getSearchTree();
		checkNode(root, 0, Scores.MIN, Scores.MAX,
				-5, StateInfo.NODE_TYPE_EXACT);

		Node n = root.nodes.get(0);
		assertEquals(5, n.nodes.size());
		checkNode(n, Move.fromString("Nh8xNf7"), Scores.MIN, Scores.MAX,
				109, StateInfo.NODE_TYPE_EXACT);
		checkNode(n.nodes.get(0), Move.fromString("g6xNf7"), Scores.MIN, Scores.MAX,
				-109, -1);
		checkNode(n.nodes.get(1), Move.fromString("Kh1-g2"), -110, -109,
				192, -1);

		n = root.nodes.get(1);
		assertEquals(9, n.nodes.size());
		checkNode(n, Move.fromString("Nh8xg6"), 108, 109,
				5, StateInfo.NODE_TYPE_UPPER_BOUND);

		n = root.nodes.get(2);
		assertEquals(9, n.nodes.size());
		checkNode(n, Move.fromString("Nh8xg6"), Scores.MIN, 109,
				5, StateInfo.NODE_TYPE_EXACT);

		n = root.nodes.get(3);
		assertEquals(1, n.nodes.size());
		checkNode(n, Move.fromString("Ka8-b8"), 4, 5,
				405, StateInfo.NODE_TYPE_LOWER_BOUND);
		checkNode(n.nodes.get(0), Move.fromString("Nf7xNh8"), -5, -4,
				-405, -1);

		n = root.nodes.get(4);
		assertEquals(1, n.nodes.size());
		checkNode(n, Move.fromString("Ka8-a7"), 4, 5,
				405, StateInfo.NODE_TYPE_LOWER_BOUND);
		checkNode(n.nodes.get(0), Move.fromString("Nf7xNh8"), -5, -4,
				-405, -1);

		n = root.nodes.get(5);
		assertEquals(1, n.nodes.size());
		checkNode(n, Move.fromString("Ka8-b7"), 4, 5,
				403, StateInfo.NODE_TYPE_LOWER_BOUND);
		checkNode(n.nodes.get(0), Move.fromString("Nf7xNh8"), -5, -4,
				-403, -1);
	}

	@Test
	public void canDoEnPassant() throws InterruptedException
	{
		GameState s = new GameState("Ka7 d2", "Kh1 e4", Players.WHITE);
		s.makeMove(Move.fromString("d2-d4"));
		assertEquals("e4xd3", Move.toString(ai.getMove(s)));
	}

	@Test
	public void bugfixTest1() throws InterruptedException
	{
		ai = new MinMaxAI(null, 6, 30, 0, 0);
		GameState s = new GameState("Ka7 Qg8 Qc5", "Kd7 Qd3", Players.BLACK);
		assertEquals("Qd3-a6", Move.toString(ai.getMove(s)));
	}

	@Test
	public void bugfixTest2() throws InterruptedException
	{
		// Laiton siirto jos Scores.CHECK_MATE_DEPTH_ADJUSTMENT liian pieni.
		ai = new MinMaxAI(null, 6, 30, 0, 0);
		GameState s = new GameState("Kb4 Qa1", "b2 Qc4 Rc5 Kd4", Players.WHITE);
		assertEquals("Kb4-a3", Move.toString(ai.getMove(s)));
	}

	@Test
	public void returnsWhenTimeLimit() throws InterruptedException
	{
		ai = new MinMaxAI(null, 99, 30, 0.0000001, 0);
		GameState s = new GameState("Kb8", "Kb6 Rc7", Players.WHITE);
		ai.getMove(s);
		assertNotNull(ai.getSearchTree());
	}

	private static class TestThread extends Thread
	{
		boolean yes = false;

		MinMaxAI ai;

		GameState state;

		TestThread(MinMaxAI ai, GameState state)
		{
			this.ai = ai;
			this.state = state;
		}

		@Override
		public void run()
		{
			try {
				ai.getMove(state);
			} catch (InterruptedException e) {
				yes = true;
			}
		}
	};

	@Test
	public void canBeInterrupted() throws InterruptedException
	{
		ai = new MinMaxAI(null, 99, 30, 5.0, 0);
		GameState s = new GameState("Kb8 b7 c7", "Kh2 Rh1", Players.WHITE);
		TestThread t = new TestThread(ai, s);
		t.start();
		Thread.sleep(10);
		t.interrupt();
		t.join();
		assertTrue(t.yes);
	}

	@Test
	public void throwsIfDepthTooSmall()
	{
		try {
			new MinMaxAI(null, 1, 30, 0, 3);
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
		}
	}
}
