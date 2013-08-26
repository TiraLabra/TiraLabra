package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoveTest
{
	private int move, move2;

	@Before
	public void setUp()
	{
		new Move();
		move = Move.pack(61, 62, 3, 4, 5);
		move2 = Move.pack(62, 63, 5, -1, 5);
	}

	@Test
	public void testGetFromSqr()
	{
		assertEquals(61, Move.getFromSqr(move));
	}

	@Test
	public void testGetToSqr()
	{
		assertEquals(62, Move.getToSqr(move));
	}

	@Test
	public void testGetPieceType()
	{
		assertEquals(3, Move.getPieceType(move));
	}

	@Test
	public void testGetCapturedType()
	{
		assertEquals(4, Move.getCapturedType(move));
	}

	@Test
	public void testGetNewType()
	{
		assertEquals(5, Move.getNewType(move));
	}

	@Test
	public void testEmptyCapturedType()
	{
		assertEquals(-1, Move.getCapturedType(move2));
	}

	@Test
	public void testToStringWithNormalMove()
	{
		int mov = Move.pack(0, 63, Pieces.BISHOP, -1, Pieces.BISHOP);
		assertEquals("Ba8-h1", Move.toString(mov));
	}

	@Test
	public void testToStringWithPawnMove()
	{
		int mov = Move.pack(51, 35, Pieces.PAWN, -1, Pieces.PAWN);
		assertEquals("d2-d4", Move.toString(mov));
	}

	@Test
	public void testToStringWithCapture()
	{
		int mov = Move.pack(30, 20, Pieces.KNIGHT, Pieces.QUEEN, Pieces.KNIGHT);
		assertEquals("Ng5xQe6", Move.toString(mov));
	}

	@Test
	public void testToStringWithPromotion()
	{
		int mov = Move.pack(9, 1, Pieces.PAWN, -1, Pieces.ROOK);
		assertEquals("b7-b8R", Move.toString(mov));
	}

	@Test
	public void testFromStringWithNormalMove()
	{
		int mov = Move.pack(0, 63, Pieces.BISHOP, -1, Pieces.BISHOP);
		assertEquals(mov, Move.fromString("Ba8-h1"));
	}

	@Test
	public void testFromStringWithPawnMove()
	{
		int mov = Move.pack(51, 35, Pieces.PAWN, -1, Pieces.PAWN);
		assertEquals(mov, Move.fromString("d2-d4"));
	}

	@Test
	public void testFromStringWithCapture()
	{
		int mov = Move.pack(30, 20, Pieces.KNIGHT, Pieces.QUEEN, Pieces.KNIGHT);
		assertEquals(mov, Move.fromString("Ng5xQe6"));
	}

	@Test
	public void testFromStringWithPromotion()
	{
		int mov = Move.pack(9, 1, Pieces.PAWN, -1, Pieces.ROOK);
		assertEquals(mov, Move.fromString("b7-b8R"));
	}

	@Test
	public void fromStringThrowsOnIllegalSyntax()
	{
		try {
			Move.fromString("Na1-Bh8");
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void fromStringThrowsOnIllegalPromotion()
	{
		try {
			Move.fromString("Ra7-a8Q");
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
		}
	}
}
