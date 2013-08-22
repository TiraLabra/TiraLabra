package chess.ai;

import chess.domain.BitBoard;
import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
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

	private static long sqrs(int... sqrs)
	{
		long m = 0;
		for (int i = 0; i < sqrs.length; ++i)
			m |= 1L << sqrs[i];
		return m;
	}

	@Test
	public void avoidsCheckByEscaping() throws InterruptedException
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 18);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(0, 1, Pieces.KING, -1, -1), ai.getMove(s));
	}

	@Test
	public void avoidsCheckByCapturing() throws InterruptedException
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 12);
		bb.addPiece(Players.BLACK, Pieces.KING, 42);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(4, 12, Pieces.KING, Pieces.QUEEN, -1), ai.getMove(s));
	}

	@Test
	public void avoidsCheckByBlocking() throws InterruptedException
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.WHITE, Pieces.BISHOP, 41);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 36);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 37);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(41, 27, Pieces.BISHOP, -1, -1), ai.getMove(s));
	}

	@Test
	public void canCheckMate() throws InterruptedException
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 52);
		bb.addPiece(Players.WHITE, Pieces.ROOK, 33);
		bb.addPiece(Players.WHITE, Pieces.ROOK, 11);
		bb.addPiece(Players.BLACK, Pieces.KING, 5);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(33, 1, Pieces.ROOK, -1, -1), ai.getMove(s));
	}

	@Test
	public void canFork() throws InterruptedException
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 56);
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 58);
		bb.addPiece(Players.BLACK, Pieces.KING, 5);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 1);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(58, 37, Pieces.QUEEN, -1, -1), ai.getMove(s));
	}

	@Test
	public void doesntStaleMateWhenHasMaterialAdvantage() throws InterruptedException
	{
		ai = new MinMaxAI(null, 5, 0, 0);
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 63);
		GameState s = new GameState(bb, Players.BLACK);
		assertFalse(Move.pack(1, 46, Pieces.QUEEN, -1, -1) == ai.getMove(s));
	}

	@Test
	public void staleMatesWhenHasMaterialDisadvantage() throws InterruptedException
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
		assertEquals(Move.pack(47, 11, Pieces.BISHOP, -1, -1), ai.getMove(s));
	}

	@Test
	public void doesntMakeIllegalMoveWhenCheckMateInevitable() throws InterruptedException
	{
		ai = new MinMaxAI(null, 4, 0, 0); // minimisyvyys mattitilanteen tunnistamiseksi
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 10);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(1, 0, Pieces.KING, -1, -1), ai.getMove(s));
	}

	@Test
	public void promotesToKnightForFasterCheckMate() throws InterruptedException
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 18);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 9);
		bb.addPiece(Players.BLACK, Pieces.KING, 16);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 8);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 24);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(Move.pack(9, 1, Pieces.PAWN, -1, Pieces.KNIGHT), ai.getMove(s));
	}

	@Test
	public void returnsWhenTimeLimit() throws InterruptedException
	{
		ai = new MinMaxAI(null, 20, 0.0000001, 0);
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 10);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		GameState s = new GameState(bb, Players.WHITE);
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
