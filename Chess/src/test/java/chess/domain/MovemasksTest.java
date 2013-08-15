package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MovemasksTest
{
	private static long sqrs(int... sqrs)
	{
		long m = 0;
		for (int i = 0; i < sqrs.length; ++i)
			m |= 1L << sqrs[i];
		return m;
	}

	@Before
	public void setUp()
	{
		new Movemasks();
	}

	@Test
	public void knightMovesInCenter()
	{

		assertEquals(sqrs(9, 11, 16, 20, 32, 36, 41, 43), Movemasks.KNIGHT_MOVES[26]);
	}

	@Test
	public void knightMovesNearBorder()
	{
		assertEquals(sqrs(33, 42, 58), Movemasks.KNIGHT_MOVES[48]);
	}

	@Test
	public void kingMovesInCenter()
	{
		assertEquals(sqrs(20, 21, 22, 28, 30, 36, 37, 38), Movemasks.KING_MOVES[29]);
	}

	@Test
	public void kingMovesNearBorder()
	{
		assertEquals(sqrs(54, 55, 62), Movemasks.KING_MOVES[63]);
	}
}
