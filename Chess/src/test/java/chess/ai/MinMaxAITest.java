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
		ai = new MinMaxAI(null, 3, 0, 0);
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
		ai = new MinMaxAI(null, 5, 0, 0);
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
		ai = new MinMaxAI(null, 4, 0, 0); // minimisyvyys mattitilanteen tunnistamiseksi
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
	public void returnsWhenTimeLimit() throws InterruptedException
	{
		ai = new MinMaxAI(null, 20, 0.0000001, 0);
		GameState s = new GameState("Kb8", "Kb6 Rc7", Players.WHITE);
		ai.getMove(s);
		assertNotNull(ai.getGameTree());
	}

	@Test
	public void throwsIfDepthTooSmall()
	{
		try {
			new MinMaxAI(null, 1, 0, 3);
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
		}
	}
}
