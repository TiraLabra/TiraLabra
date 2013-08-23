package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EvaluatorTest
{
	private Evaluator e;

	@Before
	public void setUp()
	{
		e = new Evaluator(2);
		GameState state = new GameState("Kf1 Nd2 b7", "Ke8 Qd8 Re4 Bf8 Ng8 g2 h2", Players.WHITE);
		e.reset(state);
	}

	@Test
	public void newState()
	{
		assertEquals((-1000005 - 9004 - 5002 - 3001 - 3001 - 1008 - 1007
				+ 1000004 + 3005 + 1008), e.getScore());
	}

	@Test
	public void firstMovingPlayerBlack()
	{
		e = new Evaluator(2);
		GameState state = new GameState("Bd3", "g7", Players.BLACK);
		e.reset(state);
		assertEquals(1002 - 3007, e.getScore());
	}

	@Test
	public void makeCaptureMove()
	{
		int s = e.getScore();
		e.makeMove(Move.pack(51, 36, Pieces.KNIGHT, Pieces.ROOK, -1));
		assertEquals(-(s + 9 - 5 + 5002), e.getScore());
	}

	@Test
	public void undoCapture()
	{
		int s = e.getScore();
		e.makeMove(Move.pack(51, 36, Pieces.KNIGHT, Pieces.ROOK, -1));
		e.undoMove();
		assertEquals(s, e.getScore());
	}

	@Test
	public void makePromotionCaptureMove()
	{
		int s = e.getScore();
		e.makeMove(Move.pack(9, 0, Pieces.PAWN, Pieces.KING, Pieces.KNIGHT));
		assertEquals(-(s + 3000 - 1008 + 1000000), e.getScore());
	}

	@Test
	public void undoPromotionCapture()
	{
		int s = e.getScore();
		e.makeMove(Move.pack(9, 0, Pieces.PAWN, Pieces.KING, Pieces.KNIGHT));
		e.undoMove();
		assertEquals(s, e.getScore());
	}

	@Test
	public void makeMoveWithKing()
	{
		e.makeMove(Move.pack(63, 54, Pieces.KING, -1, -1));
		assertEquals(-(4 - 0), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithQueen()
	{
		e.makeMove(Move.pack(0, 27, Pieces.QUEEN, -1, -1));
		assertEquals(-(9 - 0), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithRook()
	{
		e.makeMove(Move.pack(58, 2, Pieces.ROOK, -1, -1));
		assertEquals(-(2 - 3), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithBishop()
	{
		e.makeMove(Move.pack(57, 43, Pieces.BISHOP, -1, -1));
		assertEquals(-(7 - 1), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithKnight()
	{
		e.makeMove(Move.pack(22, 7, Pieces.KNIGHT, -1, -1));
		assertEquals(-(0 - 4), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithPawn()
	{
		e.makeMove(Move.pack(52, 36, Pieces.PAWN, -1, -1));
		assertEquals(-(6 - 0), e.getRelativeScore());
	}

	@Test
	public void multipleMoves()
	{
		int s = e.getScore();

		e.makeMove(Move.pack(18, 32, Pieces.BISHOP, Pieces.PAWN, -1));
		int d1 = 1 - 4 + 1005;
		assertEquals(-(s + d1), e.getScore());
		assertEquals(-d1, e.getRelativeScore());

		e.makeMove(Move.pack(49, 58, Pieces.PAWN, Pieces.QUEEN, Pieces.BISHOP));
		int d2 = 3001 - 1008 + 9003;
		assertEquals(s + d1 - d2, e.getScore());
		assertEquals(d1 - d2, e.getRelativeScore());

		e.undoMove();
		assertEquals(-(s + d1), e.getScore());
		assertEquals(-d1, e.getRelativeScore());

		e.undoMove();
		assertEquals(s, e.getScore());
		assertEquals(0, e.getRelativeScore());
	}

	@Test
	public void resettingTheScore()
	{
		e.makeMove(Move.pack(18, 32, Pieces.BISHOP, Pieces.PAWN, -1));

		GameState state = new GameState("Bb2", "Ke8", Players.WHITE);

		e.reset(state);
		assertEquals(3004 - 1000005, e.getScore());
		assertEquals(0, e.getRelativeScore());
	}

	@Test
	public void nullMoveInvertsScore()
	{
		e.makeMove(Move.pack(22, 7, Pieces.KNIGHT, -1, -1));
		int s = e.getScore();
		int r = e.getRelativeScore();
		e.makeNullMove();
		assertEquals(-s, e.getScore());
		assertEquals(-r, e.getRelativeScore());
	}
}
