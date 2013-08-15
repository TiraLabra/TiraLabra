package chess.ai;

import chess.domain.BitBoard;
import chess.domain.GameState;
import chess.domain.Pieces;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MinMaxAITest
{
	MinMaxAI ai;

	@Before
	public void setUp()
	{
		ai = new MinMaxAI(null, 3, 0, 0);
	}

	private static long sqrs(int... sqrs)
	{
		long m = 0;
		for (int i = 0; i < sqrs.length; ++i)
			m |= 1L << sqrs[i];
		return m;
	}

	@Test
	public void avoidsCheckByEscaping()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 18);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(1), s.getPieces(Players.WHITE, Pieces.KING));
	}

	@Test
	public void avoidsCheckByCapturing()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 12);
		bb.addPiece(Players.BLACK, Pieces.KING, 42);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(12), s.getPieces(Players.WHITE, Pieces.KING));
	}

	@Test
	public void avoidsCheckByBlocking()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.WHITE, Pieces.BISHOP, 41);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 36);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 37);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(27), s.getPieces(Players.WHITE, Pieces.BISHOP));
	}

	@Test
	public void canCheckMate()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 52);
		bb.addPiece(Players.WHITE, Pieces.ROOK, 33);
		bb.addPiece(Players.WHITE, Pieces.ROOK, 11);
		bb.addPiece(Players.BLACK, Pieces.KING, 5);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(1, 11), s.getPieces(Players.WHITE, Pieces.ROOK));
	}

	@Test
	public void canFork()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 56);
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 58);
		bb.addPiece(Players.BLACK, Pieces.KING, 5);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 1);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(37), s.getPieces(Players.WHITE, Pieces.QUEEN));
	}

	@Test
	public void doesntStaleMateWhenHasMaterialAdvantage()
	{
		ai = new MinMaxAI(null, 5, 0, 0);
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 63);
		GameState s = new GameState(bb, Players.BLACK);
		ai.move(s);
		assertFalse(sqrs(37) == s.getPieces(Players.WHITE, Pieces.QUEEN));
	}

	@Test
	public void staleMatesWhenHasMaterialDisadvantage()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 40);
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 56);
		bb.addPiece(Players.WHITE, Pieces.BISHOP, 57);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 41);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 48);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 50);
		bb.addPiece(Players.WHITE, Pieces.ROOK, 49);
		bb.addPiece(Players.BLACK, Pieces.KING, 63);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 47);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 26);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 42);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 33);
		GameState s = new GameState(bb, Players.BLACK);
		ai.move(s);
		assertEquals(sqrs(11), s.getPieces(Players.BLACK, Pieces.BISHOP));
	}

	@Test
	public void doesntMakeIllegalMoveWhenCheckMateInevitable()
	{
		ai = new MinMaxAI(null, 4, 0, 0); // minimisyvyys mattitilanteen tunnistamiseksi
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 10);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(0), s.getPieces(Players.WHITE, Pieces.KING));
	}

	@Test
	public void promotesToKnightForFasterCheckMate()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 18);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 9);
		bb.addPiece(Players.BLACK, Pieces.KING, 16);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 8);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 24);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
		assertEquals(sqrs(1), s.getPieces(Players.WHITE, Pieces.KNIGHT));
	}

	@Test
	public void returnsWhenTimeLimit()
	{
		ai = new MinMaxAI(null, 20, 0.0000001, 0);
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 10);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		GameState s = new GameState(bb, Players.WHITE);
		ai.move(s);
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
