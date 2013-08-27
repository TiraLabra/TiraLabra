package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
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
		assertEquals((-100000005 - 904 - 502 - 301 - 301 - 108 - 107
				+ 100000004 + 305 + 108), e.getScore());
	}

	@Test
	public void firstMovingPlayerBlack()
	{
		e = new Evaluator(2);
		GameState state = new GameState("Bd3", "g7", Players.BLACK);
		e.reset(state);
		assertEquals(102 - 307, e.getScore());
	}

	@Test
	public void makeCaptureMove()
	{
		int s = e.getScore();
		e.makeMove(Move.fromString("Nd2xRe4"));
		assertEquals(-(s + 9 - 5 + 502), e.getScore());
	}

	@Test
	public void undoCapture()
	{
		int s = e.getScore();
		e.makeMove(Move.fromString("Nd2xRe4"));
		e.undoMove();
		assertEquals(s, e.getScore());
	}

	@Test
	public void makePromotionCaptureMove()
	{
		int s = e.getScore();
		e.makeMove(Move.fromString("b7xKa8N"));
		assertEquals(-(s + 300 - 108 + 100000000), e.getScore());
	}

	@Test
	public void undoPromotionCapture()
	{
		int s = e.getScore();
		e.makeMove(Move.fromString("b7xKa8N"));
		e.undoMove();
		assertEquals(s, e.getScore());
	}

	@Test
	public void makeMoveWithKing()
	{
		e.makeMove(Move.fromString("Kh1-g2"));
		assertEquals(-(4 - 0), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithQueen()
	{
		e.makeMove(Move.fromString("Qa8-d5"));
		assertEquals(-(9 - 0), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithRook()
	{
		e.makeMove(Move.fromString("Rc1-c8"));
		assertEquals(-(2 - 3), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithBishop()
	{
		e.makeMove(Move.fromString("Bb1-d3"));
		assertEquals(-(7 - 1), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithKnight()
	{
		e.makeMove(Move.fromString("Ng6-h8"));
		assertEquals(-(0 - 4), e.getRelativeScore());
	}

	@Test
	public void makeMoveWithPawn()
	{
		e.makeMove(Move.fromString("e2-e4"));
		assertEquals(-(6 - 0), e.getRelativeScore());
	}

	@Test
	public void multipleMoves()
	{
		int s = e.getScore();

		e.makeMove(Move.fromString("Bc6xa4"));
		int d1 = 1 - 4 + 105;
		assertEquals(-(s + d1), e.getScore());
		assertEquals(-d1, e.getRelativeScore());

		e.makeMove(Move.fromString("b2xQc1B"));
		int d2 = 301 - 108 + 903;
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
		e.makeMove(Move.fromString("Bc6xa4"));

		GameState state = new GameState("Bb2", "Ke8", Players.WHITE);

		e.reset(state);
		assertEquals(304 - 100000005, e.getScore());
		assertEquals(0, e.getRelativeScore());
	}

	@Test
	public void nullMoveInvertsScore()
	{
		e.makeMove(Move.fromString("Ng6-h8"));
		int s = e.getScore();
		int r = e.getRelativeScore();
		e.makeNullMove();
		assertEquals(-s, e.getScore());
		assertEquals(-r, e.getRelativeScore());
	}
}
