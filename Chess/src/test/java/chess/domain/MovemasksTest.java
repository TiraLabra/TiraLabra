package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MovemasksTest
{
	private static String toStr(long mask)
	{
		String s = " ";
		for (; mask != 0; mask -= Long.lowestOneBit(mask))
			s += Long.numberOfTrailingZeros(mask) + " ";
		return s;
	}

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

		assertEquals(toStr(sqrs(9, 11, 16, 20, 32, 36, 41, 43)), toStr(Movemasks.KNIGHT_MOVES[26]));
	}

	@Test
	public void knightMovesNearBorder()
	{
		assertEquals(toStr(sqrs(33, 42, 58)), toStr(Movemasks.KNIGHT_MOVES[48]));
	}

	@Test
	public void kingMovesInCenter()
	{
		assertEquals(toStr(sqrs(20, 21, 22, 28, 30, 36, 37, 38)), toStr(Movemasks.KING_MOVES[29]));
	}

	@Test
	public void kingMovesNearBorder()
	{
		assertEquals(toStr(sqrs(54, 55, 62)), toStr(Movemasks.KING_MOVES[63]));
	}

	@Test
	public void rookMovesOnEmptyBoard()
	{
		assertEquals(toStr(sqrs(5, 13, 16, 17, 18, 19, 20, 22, 23, 29, 37, 45, 53, 61)),
				toStr(Movemasks.getRookMoves(21, sqrs())));
	}

	@Test
	public void rookMovesWithBlockers()
	{
		long moves = Movemasks.getRookMoves(35, sqrs(34, 19, 38, 39, 3, 23, 26, 42, 59));
		assertEquals(toStr(sqrs(34, 27, 19, 36, 37, 38, 43, 51, 59)), toStr(moves));
	}

	@Test
	public void bishopMovesOnEmptyBoard()
	{
		assertEquals(toStr(sqrs(57, 41, 32, 43, 36, 29, 22, 15, 59)),
				toStr(Movemasks.getBishopMoves(50, sqrs())));
	}

	@Test
	public void bishopMovesWithBlockers()
	{
		long moves = Movemasks.getBishopMoves(44, sqrs(17, 30, 62, 51, 43, 46, 49));
		assertEquals(toStr(sqrs(51, 35, 26, 17, 37, 30, 53, 62)), toStr(moves));
	}

	@Test
	public void queenMoves()
	{
		long moves = Movemasks.getQueenMoves(20, sqrs(22, 36, 29, 41, 18, 58));
		assertEquals(toStr(sqrs(19, 18, 11, 2, 12, 4, 13, 6, 21, 29, 28, 27, 34, 41, 22, 36)),
				toStr(moves));
	}
}
