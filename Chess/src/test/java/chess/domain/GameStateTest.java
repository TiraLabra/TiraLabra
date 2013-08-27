package chess.domain;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameStateTest
{
	@Before
	public void setUp()
	{
	}

	private String toStr(int sqr)
	{
		return "" + (char) ('a' + sqr % 8) + (char) ('8' - sqr / 8);
	}

	private static int sqr(String s)
	{
		return s.charAt(0) - 'a' + ('8' - s.charAt(1)) * 8;
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
		GameState s = new GameState();
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void initialPositionIsCorrect()
	{
		GameState s = new GameState();
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
		GameState s = new GameState();
		int[] b = s.getBoard();
		assertEquals(Players.BLACK * Pieces.COUNT + Pieces.KING, b[4]);
		assertEquals(-1, b[16]);
		assertEquals(Players.WHITE * Pieces.COUNT + Pieces.PAWN, b[48]);
	}

	@Test
	public void equalWhenSamePosition()
	{
		GameState s = new GameState();
		s.makeMove(Move.fromString("a2-a3"));
		GameState s2 = new GameState();
		s2.makeMove(Move.fromString("a2-a3"));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
	}

	@Test
	public void equalWhenTwoDifferentPathsToSamePosition()
	{
		GameState s = new GameState();
		s.makeMove(Move.fromString("a2-a3"));
		s.makeMove(Move.fromString("a7-a6"));
		s.makeMove(Move.fromString("Ng1-f3"));
		s.makeMove(Move.fromString("Nb8-c6"));
		GameState s2 = new GameState();
		s2.makeMove(Move.fromString("Ng1-f3"));
		s2.makeMove(Move.fromString("Nb8-c6"));
		s2.makeMove(Move.fromString("a2-a3"));
		s2.makeMove(Move.fromString("a7-a6"));
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
	}

	@Test
	public void notEqualWhenDifferentPosition()
	{
		GameState s = new GameState();
		s.makeMove(Move.fromString("Nb1-c3"));
		GameState s2 = new GameState();
		s2.makeMove(Move.fromString("Nb1-a3"));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
	}

	@Test
	public void notEqualWhenSamePositionButDifferentPlayer()
	{
		GameState s = new GameState();
		s.makeMove(Move.fromString("e2-e3"));
		s.makeMove(Move.fromString("a7-a6"));
		s.makeMove(Move.fromString("e3-e4"));
		GameState s2 = new GameState();
		s2.makeMove(Move.fromString("e2-e4"));
		s2.makeMove(Move.fromString("a7-a6"));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
	}

	@Test
	public void notEqualWhenSamePositionButDifferentEnPassantSquare()
	{
		GameState s = new GameState("b2 d4", "c4", Players.WHITE);
		s.makeMove(Move.fromString("b2-b4"));
		GameState s2 = new GameState("b4 d2", "c4", Players.WHITE);
		s2.makeMove(Move.fromString("d2-d4"));
		assertEquals("b3", toStr(s.getEnPassantSquare()));
		assertEquals("d3", toStr(s2.getEnPassantSquare()));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
	}

	@Test
	public void clonedStateHasSameData()
	{
		GameState s = new GameState();
		s.makeMove(Move.fromString("e2-e4"));
		GameState s2 = s.clone();
		assertTrue(s.equals(s2));
		assertTrue(s2.equals(s));
		assertTrue(s.getId() == s2.getId());
		assertTrue(s.getPieces(Players.WHITE) == s2.getPieces(Players.WHITE));
		assertTrue(s.getPieces(Players.BLACK) == s2.getPieces(Players.BLACK));
		assertTrue(s.getPieces(Players.WHITE, Pieces.PAWN)
				== s2.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(s.getNextMovingPlayer(), s2.getNextMovingPlayer());
		assertEquals(s.getEnPassantSquare(), s2.getEnPassantSquare());
		assertEquals(s.getPly(), s2.getPly());
	}

	@Test
	public void getKingMovesAndAttacks()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 9);
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 10);
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(36, 43, 44, 45), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 52));
	}

	@Test
	public void getPawnMoves()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 44);
		bb.addPiece(Players.BLACK, Pieces.KING, 35);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 37);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(35, 36, 37), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 44));
	}

	@Test
	public void getPawnMovesWithoutAttackPossibilities()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 44);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 37);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(36), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 44));
	}

	@Test
	public void getPawnMovesWhenBlocked()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 44);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 36);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 44));
	}

	@Test
	public void getPawnMovesWhenDoublePushIsBlocked()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 52);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 36);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(44), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 52));
	}

	@Test
	public void getPawnMovesAndAttacksOnLastRow()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 3);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 3));
		assertEquals(sqrs(), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 3));
	}

	@Test
	public void getPawnMovesAndAttacksOnFirstAndLastColumn()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 24);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 17);
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(43, 45), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 52));

		bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.PAWN, 52);
		bb.addPiece(Players.WHITE, Pieces.KING, 43);
		s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getAttackMoves(Players.WHITE, Pieces.PAWN, 52));
	}

	@Test
	public void getPawnMovesWithEnPassant()
	{
		GameState s = new GameState("c2", "d4", Players.WHITE);
		s.makeMove(Move.fromString("c2-c4"));
		long tmp = s.getPseudoLegalMoves(Players.BLACK, Pieces.PAWN, 35);
		assertEquals(sqrs(42, 43), tmp);
	}

	@Test
	public void otherPiecesCannotDoEnPassant()
	{
		GameState s = new GameState("c2", "Nd4", Players.WHITE);
		s.makeMove(Move.fromString("c2-c4"));
		long tmp = s.getPseudoLegalMoves(Players.BLACK, Pieces.KNIGHT, 35);
		assertEquals(sqrs(41, 25, 18, 20, 29, 45, 52, 50), tmp);
	}

	@Test
	public void bugfixTest1()
	{
		// ohestalyöntiruudun arvo 0 eikä -1
		GameState s = new GameState("b7", "", Players.WHITE);
		long tmp = s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, 9);
		assertEquals(sqrs(1), tmp);
	}

	@Test
	public void getPseudoLegalReturnsZeroIfNoPieceInSqr()
	{
		BitBoard bb = new BitBoard();
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(sqrs(), s.getPseudoLegalMoves(Players.WHITE, 63));
	}

	@Test
	public void getLegalMovesReturnsEmptyListIfNoPieceInSqr()
	{
		BitBoard bb = new BitBoard();
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(0, s.getLegalMoves(13).length);
	}

//	@Test
//	public void getLegalDoesntReturnMovesThatLeaveKingInCheck()
//	{
//		
//	}
	@Test
	public void areBothKingsAliveTrue()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KING, 56);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		GameState s = new GameState(bb, Players.WHITE);
		assertTrue(s.areBothKingsAlive());
	}

	@Test
	public void areBothKingsAliveFalse()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		GameState s = new GameState(bb, Players.WHITE);
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
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(4, s.getKingSquare(Players.WHITE));
		assertEquals(56, s.getKingSquare(Players.BLACK));
	}

	@Test
	public void getKingSquareWhenKingDoesntExist()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 4);
		bb.addPiece(Players.BLACK, Pieces.KNIGHT, 45);
		GameState s = new GameState(bb, Players.WHITE);
		assertEquals(-1, s.getKingSquare(Players.BLACK));
	}

	@Test
	public void isCheckMateReturnsTrue()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 3);
		GameState s = new GameState(bb, Players.WHITE);
		assertTrue(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseIfKingCanEscape()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 28);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 26);
		GameState s = new GameState(bb, Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseIfKingCanCaptureAgressor()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 1);
		GameState s = new GameState(bb, Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseWhenStaleMate()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 18);
		GameState s = new GameState(bb, Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isStaleMateReturnsTrue()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 18);
		GameState s = new GameState(bb, Players.WHITE);
		assertTrue(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseWhenCheckMate()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 1);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 3);
		GameState s = new GameState(bb, Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseIfKingCanEscape()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 28);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 26);
		GameState s = new GameState(bb, Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseIfKingCanCaptureAgressor()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KING, 0);
		bb.addPiece(Players.BLACK, Pieces.KING, 17);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 1);
		GameState s = new GameState(bb, Players.WHITE);
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
			GameState s = new GameState(new Random(seed));
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
		GameState s = new GameState(bb, Players.WHITE);
		s.makeMove(Move.fromString("Kc3-c4"));
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
		GameState s = new GameState(bb, Players.WHITE);
		s.makeMove(Move.fromString("Kc3-c4"));
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
		GameState s = new GameState(bb, Players.WHITE);
		long h = s.getId();
		s.makeMove(Move.fromString("Kc3-c4"));
		assertFalse(h == s.getId());
	}

	@Test
	public void undoRestoresOldStateWhenNoCapture()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 11);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		GameState s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.fromString("Kc3-c4");
		s.makeMove(move);
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
	public void undoRestoresOldStateWhenCapturedPiece()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		GameState s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.fromString("Kc3xc4");
		s.makeMove(move);
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
	public void undoRestoresOldStateWithPromotionMove()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 11);
		GameState s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.fromString("d7-d8Q");
		s.makeMove(move);
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
	public void undoRestoresOldStateWithDoublePawnPush()
	{
		GameState s = new GameState("g2", "h4", Players.WHITE);
		int move = Move.fromString("g2-g4");
		s.makeMove(move);
		s.undoMove(move);
		s.equals(new GameState("g2", "h4", Players.WHITE));
	}

	@Test
	public void undoRestoresOldStateWithEnPassantMove()
	{
		GameState s = new GameState("g2", "h4", Players.WHITE);
		s.makeMove(Move.fromString("g2-g4"));
		GameState scopy = s.clone();
		int move = Move.fromString("h4xg3");
		scopy.makeMove(move);
		scopy.undoMove(move);
		assertEquals(s, scopy);
	}

	@Test
	public void nullMove()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		GameState s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		s.makeNullMove();

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
	public void nullMoveResetsEnPassantSquare()
	{
		GameState s = new GameState("a2", "b4", Players.WHITE);
		s.makeMove(Move.fromString("a2-a4"));
		s.makeNullMove();
		assertEquals(-1, s.getEnPassantSquare());
	}

	@Test
	public void undoNullMoveRestoresOldState()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 29);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 34);
		bb.addPiece(Players.WHITE, Pieces.KING, 42);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 51);
		GameState s = new GameState(bb, Players.WHITE);
		GameState s2 = s.clone();
		s.makeNullMove();
		s.undoNullMove();

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
		GameState s = new GameState("a2 Kd4", "Qh1 Ba8", Players.BLACK);
		assertEquals(Players.BLACK, s.getNextMovingPlayer());
		assertEquals(sqrs(48, 35), s.getPieces(Players.WHITE));
		assertEquals(sqrs(63, 0), s.getPieces(Players.BLACK));
		assertEquals(sqrs(48), s.getPieces(Players.WHITE, Pieces.PAWN));
		assertEquals(sqrs(35), s.getPieces(Players.WHITE, Pieces.KING));
		assertEquals(sqrs(63), s.getPieces(Players.BLACK, Pieces.QUEEN));
		assertEquals(sqrs(0), s.getPieces(Players.BLACK, Pieces.BISHOP));
	}
}
