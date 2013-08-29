package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RandomizerTest
{
	@Before
	public void setUp()
	{
		new GameGenerator();
	}

	@Test
	public void randomStateIsLegal()
	{
		Long[] seeds = new Long[]{
			1740108745L /*matti*/,
			1116454540L /*patti*/,
			115934186L /*musta shakissa*/};

		for (Long seed: seeds) {
			GameState s = GameGenerator.createGame(seed);
			assertFalse(s.isCheckMate());
			assertFalse(s.isStaleMate());
			assertTrue(s.areBothKingsAlive());
			assertFalse(s.isKingChecked(1 - s.getNextMovingPlayer()));
		}
	}
}
