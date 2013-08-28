package chess.domain;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RandomizerTest
{
	@Before
	public void setUp()
	{
		new Randomizer();
	}

	@Test
	public void randomStateIsLegal()
	{
		Integer[] seeds = new Integer[]{
			1740108745 /*matti*/,
			1116454540 /*patti*/,
			115934186 /*musta shakissa*/};

		for (Integer seed: seeds) {
			GameState s = Randomizer.createGame(new Random(seed));
			assertFalse(s.isCheckMate());
			assertFalse(s.isStaleMate());
			assertTrue(s.areBothKingsAlive());
			assertFalse(s.isKingChecked(1 - s.getNextMovingPlayer()));
		}
	}
}
