package chess.domain;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameStateTest
{
	private GameState s;

	@Before
	public void setUp()
	{
		s = new GameState();
	}

	private static long sqrs(int... sqrs)
	{
		long m = 0;
		for (int i = 0; i < sqrs.length; ++i)
			m |= 1L << sqrs[i];
		return m;
	}

	@Test
	public void whiteHasFirstTurn()
	{
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void initialPositionIsCorrect()
	{
		assertEquals(0xffffl << 48, s.getPieces(Players.WHITE));
		assertEquals(0xffffl, s.getPieces(Players.BLACK));
		assertEquals(1l << 60, s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(1l << 59, s.getPieces(Players.WHITE, Pieces.QUEEN));
		assertEquals(1l << 56 | 1l << 63, s.getPieces(Players.WHITE, Pieces.ROOK));
		assertEquals(1l << 58 | 1l << 61, s.getPieces(Players.WHITE, Pieces.BISHOP));
		assertEquals(1l << 57 | 1l << 62, s.getPieces(Players.WHITE, Pieces.KNIGHT));
		assertEquals(0xffl << 48, s.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(1l << 4, s.getPieces(Players.BLACK, Pieces.KING));
		assertEquals(1l << 3, s.getPieces(Players.BLACK, Pieces.QUEEN));
		assertEquals(1l << 0 | 1l << 7, s.getPieces(Players.BLACK, Pieces.ROOK));
		assertEquals(1l << 2 | 1l << 5, s.getPieces(Players.BLACK, Pieces.BISHOP));
		assertEquals(1l << 1 | 1l << 6, s.getPieces(Players.BLACK, Pieces.KNIGHT));
		assertEquals(0xffl << 8, s.getPieces(Players.BLACK, Pieces.PAWN));
	}

	@Test
	public void testGetBoard()
	{
		int[] b = s.getBoard();
		assertEquals(Players.BLACK * Pieces.COUNT + Pieces.KING, b[4]);
		assertEquals(-1, b[16]);
		assertEquals(Players.WHITE * Pieces.COUNT + Pieces.PAWN, b[48]);
	}

	@Test
	public void equalWhenSamePosition()
	{
		s.move(Move.pack(48, 40, Pieces.PAWN, -1, -1));
		GameState s2 = new GameState();
		s2.move(Move.pack(48, 40, Pieces.PAWN, -1, -1));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
	}

	@Test
	public void equalWhenTwoDifferentPathsToSamePosition()
	{
		s.move(Move.pack(48, 40, Pieces.PAWN, -1, -1));
		s.move(Move.pack(8, 16, Pieces.PAWN, -1, -1));
		s.move(Move.pack(62, 45, Pieces.KNIGHT, -1, -1));
		s.move(Move.pack(1, 18, Pieces.KNIGHT, -1, -1));
		GameState s2 = new GameState();
		s2.move(Move.pack(62, 45, Pieces.KNIGHT, -1, -1));
		s2.move(Move.pack(1, 18, Pieces.KNIGHT, -1, -1));
		s2.move(Move.pack(48, 40, Pieces.PAWN, -1, -1));
		s2.move(Move.pack(8, 16, Pieces.PAWN, -1, -1));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
	}

	@Test
	public void notEqualWhenDifferentPosition()
	{
		s.move(Move.pack(57, 42, Pieces.KNIGHT, -1, -1));
		GameState s2 = new GameState();
		s2.move(Move.pack(57, 44, Pieces.KNIGHT, -1, -1));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
	}

	@Test
	public void notEqualWhenSamePositionButDifferentPlayer()
	{
		s.move(Move.pack(52, 44, Pieces.PAWN, -1, -1));
		s.move(Move.pack(8, 16, Pieces.PAWN, -1, -1));
		s.move(Move.pack(33, 36, Pieces.PAWN, -1, -1));
		GameState s2 = new GameState();
		s2.move(Move.pack(52, 36, Pieces.PAWN, -1, -1));
		s2.move(Move.pack(8, 16, Pieces.PAWN, -1, -1));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
	}

	@Test
	public void clonedStateHasSameData()
	{
		s.move(Move.pack(52, 44, Pieces.PAWN, -1, -1));
		GameState s2 = s.clone();
		assertTrue(s.equals(s2));
		assertTrue(s2.equals(s));
		assertTrue(s.getId() == s2.getId());
		assertTrue(s.getPieces(Players.WHITE) == s2.getPieces(Players.WHITE));
		assertTrue(s.getPieces(Players.BLACK) == s2.getPieces(Players.BLACK));
		assertTrue(s.getPieces(Players.WHITE, Pieces.PAWN)
				== s2.getPieces(Players.WHITE, Pieces.PAWN));
		assertTrue(s.getNextMovingPlayer() == s2.getNextMovingPlayer());
	}

	@Test
	public void getKingMovesAndAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 9);
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 10);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(0, 1, 2, 8, 16, 17, 18),
				s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, 9));
		assertEquals(sqrs(0, 1, 2, 8, 16, 17, 18),
				s.getAttackMoves(Players.BLACK, Pieces.KING, 9));
	}

	@Test
	public void getQueenMovesAndAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 27);
		bb.addPiece(Players.WHITE, Pieces.KING, 51);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(19, 11, 3, 18, 9, 0, 26, 25, 24, 34, 41, 48, 35, 43, 36, 45, 28, 29, 30,
				31, 20, 13, 6), s.getPseudoLegalMoves(Players.WHITE, Pieces.QUEEN, 27));
		assertEquals(sqrs(19, 11, 3, 18, 9, 0, 26, 25, 24, 34, 41, 48, 35, 43, 36, 45, 28, 29, 30,
				31, 20, 13, 6), s.getAttackMoves(Players.WHITE, Pieces.QUEEN, 27));
	}

	@Test
	public void getRookMovesAndAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.ROOK, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 58);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 32);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(26, 18, 10, 2, 33, 42, 50, 58, 35, 36, 37, 38, 39),
				s.getPseudoLegalMoves(Players.BLACK, Pieces.ROOK, 34));
		assertEquals(sqrs(26, 18, 10, 2, 33, 42, 50, 58, 35, 36, 37, 38, 39),
				s.getAttackMoves(Players.BLACK, Pieces.ROOK, 34));
	}

	@Test
	public void getBishopMovesAndAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.BISHOP, 27);
		bb.addPiece(Players.WHITE, Pieces.KING, 48);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(18, 9, 0, 34, 41, 36, 45, 20, 13, 6),
				s.getPseudoLegalMoves(Players.WHITE, Pieces.BISHOP, 27));
		assertEquals(sqrs(18, 9, 0, 34, 41, 36, 45, 20, 13, 6),
				s.getAttackMoves(Players.WHITE, Pieces.BISHOP, 27));
	}

	@Test
	public void getKnightMovesAndAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		bb.addPiece(Players.WHITE, Pieces.KING, 51);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 60);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(35, 28, 30, 39, 55, 62, 51),
				s.getPseudoLegalMoves(Players.BLACK, Pieces.KNIGHT, 45));
		assertEquals(sqrs(35, 28, 30, 39, 55, 62, 51),
				s.getAttackMoves(Players.BLACK, Pieces.KNIGHT, 45));
	}

	@Test
	public void getPawnMovesInInitialPosition()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 52);
		bb.addPiece(Players.BLACK, Pieces.KING, 43);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(36, 43, 44, 45), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 52));
	}

	@Test
	public void getPawnMoves()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 44);
		bb.addPiece(Players.BLACK, Pieces.KING, 35);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 37);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(35, 36, 37), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 44));
	}

	@Test
	public void getPawnMovesWithoutAttackPossibilities()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 44);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 37);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(36), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 44));
	}

	@Test
	public void getPawnMovesWhenBlocked()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 44);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 36);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 44));
	}

	@Test
	public void getPawnMovesWhenDoublePushIsBlocked()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 52);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 36);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(44), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 52));
	}

	@Test
	public void getPawnMovesAndAttacksOnLastRow()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 3);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 3));
		assertEquals(sqrs(), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 3));
	}

	@Test
	public void getPawnMovesAndAttacksOnFirstAndLastColumn()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 24);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 17);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(16, 17), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 24));
		assertEquals(sqrs(17), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 24));

		bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 55);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 46);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(39, 46, 47), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 55));
		assertEquals(sqrs(46), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 55));
	}

	@Test
	public void getPawnAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 52);
		bb.addPiece(Players.BLACK, Pieces.KING, 43);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(43, 45), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 52));

		bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 52);
		bb.addPiece(Players.WHITE, Pieces.KING, 43);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 52));
	}

	@Test
	public void getPseudoLegalReturnsZeroIfNoPieceInSqr()
	{
		BitBoard bb = new BitBoard();
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getPseudoLegalMoves(Players.WHITE, 63));
	}

	@Test
	public void getLegalMovesReturnsEmptyListIfNoPieceInSqr()
	{
		BitBoard bb = new BitBoard();
		s = new GameState(bb, Players.WHITE);
		assertEquals(0, s.getLegalMoves(13).length);
	}

	@Test
	public void areBothKingsAliveTrue()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KING, 56);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertTrue(s.areBothKingsAlive());
	}

	@Test
	public void areBothKingsAliveFalse()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.areBothKingsAlive());

		bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 56);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.areBothKingsAlive());
	}

	@Test
	public void getKingSquare()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KING, 56);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertEquals(4, s.getKingSquare(Players.WHITE));
		assertEquals(56, s.getKingSquare(Players.BLACK));
	}

	@Test
	public void getKingSquareWhenKingDoesntExist()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		s = new GameState(bb, Players.WHITE);
		assertEquals(-1, s.getKingSquare(Players.BLACK));
	}

	@Test
	public void isCheckMateReturnsTrue()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 3);
		s = new GameState(bb, Players.WHITE);
		assertTrue(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseIfKingCanEscape()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 28);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 26);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseIfKingCanCaptureAgressor()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 1);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseWhenStaleMate()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 18);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isStaleMateReturnsTrue()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 18);
		s = new GameState(bb, Players.WHITE);
		assertTrue(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseWhenCheckMate()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 3);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseIfKingCanEscape()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 28);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 26);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseIfKingCanCaptureAgressor()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 1);
		s = new GameState(bb, Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void randomStateIsLegal()
	{
		Integer[] seeds = new Integer[]{
			1740108745 /*matti*/,
			1116454540 /*patti*/,
			115934186 /*musta shakissa*/};

		for (Integer seed: seeds) {
			s = new GameState(new Random(seed));
			assertFalse(s.isCheckMate());
			assertFalse(s.isStaleMate());
			assertTrue(s.areBothKingsAlive());
			assertFalse(s.isKingChecked(1 - s.getNextMovingPlayer()));
		}
	}

	@Test
	public void moveChangesBoardLayout()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 11);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		s.move(Move.pack(42, 34, Pieces.KING, -1, -1));
		assertEquals(sqrs(34, 51), s.getPieces(Players.WHITE));
		assertEquals(sqrs(11, 29), s.getPieces(Players.BLACK));
		assertEquals(sqrs(34), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK, Pieces.KING));
		assertEquals(sqrs(51), s.getPieces(Players.WHITE, Pieces.KNIGHT));
		assertEquals(sqrs(11), s.getPieces(Players.BLACK, Pieces.PAWN));
	}

	@Test
	public void moveChangesPlayer()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 11);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		s.move(Move.pack(42, 34, Pieces.KING, -1, -1));
		assertEquals(Players.BLACK, s.getNextMovingPlayer());
	}

	@Test
	public void moveUpdatesHashCode()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 11);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		long h = s.getId();
		s.move(Move.pack(42, 34, Pieces.KING, -1, -1));
		assertFalse(h == s.getId());
	}

	@Test
	public void undoRevertsOldStateWhenNoCapture()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 11);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.pack(42, 34, Pieces.KING, -1, -1);
		s.move(move);
		s.undoMove(move);
		assertEquals(sqrs(42, 51), s.getPieces(Players.WHITE));
		assertEquals(sqrs(11, 29), s.getPieces(Players.BLACK));
		assertEquals(sqrs(42), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK, Pieces.KING));
		assertEquals(sqrs(51), s.getPieces(Players.WHITE, Pieces.KNIGHT));
		assertEquals(sqrs(11), s.getPieces(Players.BLACK, Pieces.PAWN));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void undoRevertsOldStateWhenCapturedPiece()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.pack(42, 34, Pieces.KING, Pieces.PAWN, -1);
		s.move(move);
		s.undoMove(move);
		assertEquals(sqrs(42, 51), s.getPieces(Players.WHITE));
		assertEquals(sqrs(34, 29), s.getPieces(Players.BLACK));
		assertEquals(sqrs(42), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK, Pieces.KING));
		assertEquals(sqrs(51), s.getPieces(Players.WHITE, Pieces.KNIGHT));
		assertEquals(sqrs(34), s.getPieces(Players.BLACK, Pieces.PAWN));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void undoRevertsOldStateWithPromotionMove()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 11);
		s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.pack(11, 3, Pieces.PAWN, -1, Pieces.QUEEN);
		s.move(move);
		s.undoMove(move);
		assertEquals(sqrs(11, 42), s.getPieces(Players.WHITE));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK));
		assertEquals(sqrs(42), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(11), s.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK, Pieces.KING));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void nullMove()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		s.nullMove();

		assertEquals(sqrs(42, 51), s.getPieces(Players.WHITE));
		assertEquals(sqrs(34, 29), s.getPieces(Players.BLACK));
		assertEquals(sqrs(42), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK, Pieces.KING));
		assertEquals(sqrs(51), s.getPieces(Players.WHITE, Pieces.KNIGHT));
		assertEquals(sqrs(34), s.getPieces(Players.BLACK, Pieces.PAWN));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
		assertEquals(Players.BLACK, s.getNextMovingPlayer());
	}

	@Test
	public void doubleNullMoveRevertsOldState()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		s.nullMove();
		s.nullMove();

		assertEquals(sqrs(42, 51), s.getPieces(Players.WHITE));
		assertEquals(sqrs(34, 29), s.getPieces(Players.BLACK));
		assertEquals(sqrs(42), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(29), s.getPieces(Players.BLACK, Pieces.KING));
		assertEquals(sqrs(51), s.getPieces(Players.WHITE, Pieces.KNIGHT));
		assertEquals(sqrs(34), s.getPieces(Players.BLACK, Pieces.PAWN));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void constructorGetsPiecesFromString()
	{
		s = new GameState("a2 Kd4", "Qh1 Ba8", Players.BLACK);
		assertEquals(Players.BLACK, s.getNextMovingPlayer());
		assertEquals(sqrs(48, 35), s.getPieces(Players.WHITE));
		assertEquals(sqrs(63, 0), s.getPieces(Players.BLACK));
		assertEquals(sqrs(48), s.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(sqrs(35), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(63), s.getPieces(Players.BLACK, Pieces.QUEEN));
		assertEquals(sqrs(0), s.getPieces(Players.BLACK, Pieces.BISHOP));
	}
}
