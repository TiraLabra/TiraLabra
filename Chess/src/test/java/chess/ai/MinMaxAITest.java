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
		ai = new MinMaxAI(null, 3, 0);
	}

	private static long sqrs(int... sqrs)
	{
		long m = 0;
		for (int i = 0; i < sqrs.length; ++i)
			m |= 1L << sqrs[i];
		return m;
	}

	@Test
	public void getScore()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 1);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 2);
		bb.addPiece(Players.BLACK, Pieces.BISHOP, 3);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 4);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 8);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals((-1000 - 9 - 5 - 3 - 3 - 1 + 1000 + 3) * 1000000 - 8 - 99, ai.getScore(s, 99));
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
}
