package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BitBoardTest
{
	BitBoard bb;

	@Before
	public void setUp()
	{
		bb = new BitBoard();
	}

	@Test
	public void newBitBoardIsEmpty()
	{
		assertEquals(0, bb.getPieces(Players.WHITE));
		assertEquals(0, bb.getPieces(Players.BLACK));
		assertEquals(0, bb.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(0, bb.getPieces(Players.BLACK, Pieces.PAWN));
	}

	@Test
	public void testAddPiece()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 35);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 63);
		assertEquals(1L << 35, bb.getPieces(Players.BLACK, Pieces.KNIGHT));
		assertEquals(1L << 63, bb.getPieces(Players.BLACK, Pieces.BISHOP));
		assertEquals(1L << 35 | 1L << 63, bb.getPieces(Players.BLACK));
		assertEquals(0, bb.getPieces(Players.WHITE));
	}

	@Test
	public void testRemovePiece2()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 27);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 63);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 25);
		bb.removePiece(Players.BLACK, 27);
		assertEquals(1L << 63, bb.getPieces(Players.BLACK, Pieces.KNIGHT));
		assertEquals(1L << 63 | 1L << 25, bb.getPieces(Players.BLACK));
	}

	@Test
	public void testRemovePiece3()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 27);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 63);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 25);
		bb.removePiece(Players.BLACK, Pieces.KNIGHT, 27);
		assertEquals(1L << 63, bb.getPieces(Players.BLACK, Pieces.KNIGHT));
		assertEquals(1L << 63 | 1L << 25, bb.getPieces(Players.BLACK));
	}

	@Test
	public void testRemovePieceReturnVal()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 27);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 63);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 25);
		int p = bb.removePiece(Players.BLACK, 27);
		assertEquals(Pieces.KNIGHT, p);
		p = bb.removePiece(Players.BLACK, 28);
		assertEquals(-1, p);
	}

	@Test
	public void testClear()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 27);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 63);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 25);
		bb.clear();
		assertEquals(0, bb.getPieces(Players.WHITE));
		assertEquals(0, bb.getPieces(Players.BLACK));
		assertEquals(0, bb.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(0, bb.getPieces(Players.BLACK, Pieces.KNIGHT));
	}

	@Test
	public void testHasPiece1()
	{
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 27);
		assertTrue(bb.hasPiece(27));
		assertFalse(bb.hasPiece(28));
	}

	@Test
	public void testHasPiece2()
	{
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 27);
		assertTrue(bb.hasPiece(Players.WHITE, 27));
		assertFalse(bb.hasPiece(Players.BLACK, 27));
		assertFalse(bb.hasPiece(Players.WHITE, 28));
	}

	@Test
	public void testHasPiece3()
	{
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 27);
		assertTrue(bb.hasPiece(Players.WHITE, Pieces.KNIGHT, 27));
		assertFalse(bb.hasPiece(Players.WHITE, Pieces.PAWN, 27));
		assertFalse(bb.hasPiece(Players.BLACK, Pieces.KNIGHT, 27));
		assertFalse(bb.hasPiece(Players.WHITE, Pieces.KNIGHT, 28));
	}

	@Test
	public void testToArray()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 27);
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 40);
		int[] a = bb.toArray();
		assertEquals(64, a.length);
		assertEquals(-1, a[0]);
		assertEquals(-1, a[63]);
		assertEquals(Players.BLACK * Pieces.COUNT + Pieces.KNIGHT, a[27]);
		assertEquals(Players.WHITE * Pieces.COUNT + Pieces.QUEEN, a[40]);
	}

	@Test
	public void testCopyFrom()
	{
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 27);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 63);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 25);
		BitBoard bb2 = new BitBoard();
		bb2.copyFrom(bb);
		assertEquals(1L << 27, bb2.getPieces(Players.BLACK, Pieces.KNIGHT));
		assertEquals(1L << 63, bb2.getPieces(Players.BLACK, Pieces.BISHOP));
		assertEquals(1L << 27 | 1L << 63, bb2.getPieces(Players.BLACK));
		assertEquals(1L << 25, bb.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(1L << 25, bb.getPieces(Players.WHITE));
	}
}
