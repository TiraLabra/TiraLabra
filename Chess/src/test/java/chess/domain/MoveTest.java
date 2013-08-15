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
		move2 = Move.pack(62, 63, 5, -1, -1);
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
	public void testGetPromotedType()
	{
		assertEquals(5, Move.getPromotedType(move));
	}

	@Test
	public void testEmptyCapturedType()
	{
		assertEquals(-1, Move.getCapturedType(move2));
	}

	@Test
	public void testEmptyPromotedType()
	{
		assertEquals(-1, Move.getPromotedType(move2));
	}

	@Test
	public void testToStringWithNormalMove()
	{
		assertEquals("Ba8-h1", Move.toString(Move.pack(0, 63, Pieces.BISHOP, -1, -1)));
	}

	@Test
	public void testToStringWithPawnMove()
	{
		assertEquals("d2-d4", Move.toString(Move.pack(51, 35, Pieces.PAWN, -1, -1)));
	}

	@Test
	public void testToStringWithCapture()
	{
		assertEquals("Ng5xQe6", Move.toString(Move.pack(30, 20, Pieces.KNIGHT, Pieces.QUEEN, -1)));
	}

	@Test
	public void testToStringWithPromotion()
	{
		assertEquals("b7-b8R", Move.toString(Move.pack(9, 1, Pieces.PAWN, -1, Pieces.ROOK)));
	}
}
