package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameStateTest
{
	@Before
	public void setUp()
	{
	}

	private static String str(int sqr)
	{
		return Character.toString((char) ('a' + sqr % 8)) + (char) ('8' - sqr / 8);
	}

	private static String str(long sqrMask)
	{
		String s = "";
		for (; sqrMask != 0; sqrMask -= Long.lowestOneBit(sqrMask))
			s += str(Long.numberOfTrailingZeros(sqrMask)) + " ";
		return s.isEmpty() ? "" : s.substring(0, s.length() - 1);
	}

	private static int sq(String s)
	{
		return s.charAt(0) - 'a' + ('8' - s.charAt(1)) * 8;
	}

	private static long perft(GameState state, int depth)
	{
		int[] moves = state.getLegalMoves();
		if (depth == 1)
			return moves.length;
		long count = 0;
		for (int i = 0; i < moves.length; ++i) {
			state.makeMove(moves[i]);
			count += perft(state, depth - 1);
			state.undoMove(moves[i]);
		}
		return count;
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
		assertEquals("e1", str(s.getPieces(Players.WHITE, Pieces.KING)));
		assertEquals("d1", str(s.getPieces(Players.WHITE, Pieces.QUEEN)));
		assertEquals("a1 h1", str(s.getPieces(Players.WHITE, Pieces.ROOK)));
		assertEquals("c1 f1", str(s.getPieces(Players.WHITE, Pieces.BISHOP)));
		assertEquals("b1 g1", str(s.getPieces(Players.WHITE, Pieces.KNIGHT)));
		assertEquals("a2 b2 c2 d2 e2 f2 g2 h2", str(s.getPieces(Players.WHITE, Pieces.PAWN)));
		assertEquals("e8", str(s.getPieces(Players.BLACK, Pieces.KING)));
		assertEquals("d8", str(s.getPieces(Players.BLACK, Pieces.QUEEN)));
		assertEquals("a8 h8", str(s.getPieces(Players.BLACK, Pieces.ROOK)));
		assertEquals("c8 f8", str(s.getPieces(Players.BLACK, Pieces.BISHOP)));
		assertEquals("b8 g8", str(s.getPieces(Players.BLACK, Pieces.KNIGHT)));
		assertEquals("a7 b7 c7 d7 e7 f7 g7 h7", str(s.getPieces(Players.BLACK, Pieces.PAWN)));
	}

	@Test
	public void testGetBoard()
	{
		GameState s = new GameState();
		BitBoard bb = s.getBoard();
		assertTrue(bb.hasPiece(Players.WHITE, Pieces.KNIGHT, sq("b1")));
		assertFalse(bb.hasPiece(sq("a6")));
		assertTrue(bb.hasPiece(Players.BLACK, Pieces.QUEEN, sq("d8")));
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
		assertEquals("b3", str(s.getEnPassantSquare()));
		assertEquals("d3", str(s2.getEnPassantSquare()));
		assertFalse(s.equals(s2));
		assertFalse(s.getId() == s2.getId());
	}

	@Test
	public void notEqualWhenSamePositionButDifferentCastlingRights()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		GameState s2 = s.clone();
		assertEquals(s2, s);
		s.makeMove(Move.fromString("Ke1-e2"));
		s.makeMove(Move.fromString("Ke8-e7"));
		s.makeMove(Move.fromString("Ke2-e1"));
		s.makeMove(Move.fromString("Ke7-e8"));
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
	public void getKingMovesAndThreatenedSquares()
	{
		GameState s = new GameState("Ka8", "Kb7 Nc7", Players.WHITE);
		assertEquals("a8 b8 c8 a7 a6 b6 c6",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("b7"))));
		assertEquals("a8 b8 c8 a7 c7 a6 b6 c6",
				str(s.getThreatenedSquares(Players.BLACK, Pieces.KING, sq("b7"))));
	}

	@Test
	public void castlingIsAllowedWhenAllConditionsAreMet()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1 Qe3 Rb5", "Ra8 Ke8 Rh8 Qe6 Rb4", Players.WHITE);
		assertEquals("d2 e2 f2 c1 d1 f1 g1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("c8 d8 f8 g8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfFriendlyPiecesBetweenKingAndRook()
	{
		GameState s = new GameState("Ra1 Nb1 Ke1 Bf1 Rh1", "Ra8 Ke8 Ng8 Rh8", Players.WHITE);
		assertEquals("d2 e2 f2 d1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("c8 d8 f8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfEnemyPiecesBetweenKingAndRook()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1 Bg8", "Nb1 Nf1 Ra8 Ke8 Rh8", Players.WHITE);
		assertEquals("d2 e2 f2 d1 f1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("c8 d8 f8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfKingInCheck()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1 Re5", "Ra8 Ke8 Rh8 Re4", Players.WHITE);
		assertEquals("d2 e2 f2 d1 f1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("d8 f8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfKingPassesOverThreatenedSquare()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1 Rg6", "Ra8 Ke8 Rh8 Be2", Players.WHITE);
		assertEquals("d2 e2 f2 d1 f1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("c8 d8 f8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfKingPassesOverSquareThreatenedByPawn()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1 b7", "Ra8 Ke8 Rh8 e2", Players.WHITE);
		assertEquals("d2 e2 f2 d1 f1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("d8 f8 g8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfKingMoved()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-e2"));
		s.makeMove(Move.fromString("Ke8-e7"));
		s.makeMove(Move.fromString("Ke2-e1"));
		s.makeMove(Move.fromString("Ke7-e8"));
		assertEquals("d2 e2 f2 d1 f1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("d8 f8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotAllowedIfRookMoved()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		s.makeMove(Move.fromString("Ra1-a2"));
		s.makeMove(Move.fromString("Rh8-h7"));
		s.makeMove(Move.fromString("Ra2-a1"));
		s.makeMove(Move.fromString("Rh7-h8"));
		assertEquals("d2 e2 f2 d1 f1 g1",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("c8 d8 f8 d7 e7 f7",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void castlingNotIncludedInThreatenedSquares()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		assertEquals("d2 e2 f2 d1 f1",
				str(s.getThreatenedSquares(Players.WHITE, Pieces.KING, sq("e1"))));
		assertEquals("d8 f8 d7 e7 f7",
				str(s.getThreatenedSquares(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void rookCaptureRemovesCastlingRights()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "b2", Players.BLACK);
		s.makeMove(Move.fromString("b2xRa1Q"));
		assertEquals("h1", str(s.getCastlingRights()));
		GameState s2 = new GameState("Ke1 Rh1", "Qa1", Players.WHITE);
		assertEquals(s2, s);
		assertEquals(s2.getId(), s.getId());
	}

	@Test
	public void standardInitialPositionHasCastlingRights()
	{
		GameState s = new GameState();
		assertEquals(Movemasks.INITIAL_CASTLING_RIGHTS, s.getCastlingRights());
		s.makeMove(Move.fromString("Ng1-f3"));
		s.makeMove(Move.fromString("Ng8-f6"));
		s.makeMove(Move.fromString("e2-e3"));
		s.makeMove(Move.fromString("e7-e6"));
		s.makeMove(Move.fromString("Bf1-e2"));
		s.makeMove(Move.fromString("Bf8-e7"));
		assertEquals("f1 g1", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.KING, sq("e1"))));
		s.makeMove(Move.fromString("Ke1-g1"));
		assertEquals("f8 g8", str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KING, sq("e8"))));
	}

	@Test
	public void getQueenMovesAndThreatenedSquares()
	{
		GameState s = new GameState("Kd2 Qd5", "Nf3", Players.WHITE);
		assertEquals("a8 d8 g8 b7 d7 f7 c6 d6 e6 a5 b5 c5 e5 f5 g5 h5 c4 d4 e4 b3 d3 f3 a2",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.QUEEN, sq("d5"))));
		assertEquals("a8 d8 g8 b7 d7 f7 c6 d6 e6 a5 b5 c5 e5 f5 g5 h5 c4 d4 e4 b3 d3 f3 a2 d2",
				str(s.getThreatenedSquares(Players.WHITE, Pieces.QUEEN, sq("d5"))));
	}

	@Test
	public void getRookMovesAndThreatenedSquares()
	{
		GameState s = new GameState("Kc1", "Rc4 Na4", Players.WHITE);
		assertEquals("c8 c7 c6 c5 b4 d4 e4 f4 g4 h4 c3 c2 c1",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.ROOK, sq("c4"))));
		assertEquals("c8 c7 c6 c5 a4 b4 d4 e4 f4 g4 h4 c3 c2 c1",
				str(s.getThreatenedSquares(Players.BLACK, Pieces.ROOK, sq("c4"))));
	}

	@Test
	public void getBishopMovesAndThreatenedSquares()
	{
		GameState s = new GameState("Ka2 Bd5", "Nf3", Players.WHITE);
		assertEquals("a8 g8 b7 f7 c6 e6 c4 e4 b3 f3",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.BISHOP, sq("d5"))));
		assertEquals("a8 g8 b7 f7 c6 e6 c4 e4 b3 f3 a2",
				str(s.getThreatenedSquares(Players.WHITE, Pieces.BISHOP, sq("d5"))));
	}

	@Test
	public void getKnightMovesAndThreatenedSquares()
	{
		GameState s = new GameState("Kd2", "Nf3 Ne1", Players.WHITE);
		assertEquals("e5 g5 d4 h4 d2 h2 g1",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KNIGHT, sq("f3"))));
		assertEquals("e5 g5 d4 h4 d2 h2 e1 g1",
				str(s.getThreatenedSquares(Players.BLACK, Pieces.KNIGHT, sq("f3"))));
	}

	@Test
	public void getPawnMovesInInitialPosition()
	{
		GameState s = new GameState("e2", "Kd3 Nf3", Players.WHITE);
		assertEquals("e4 d3 e3 f3",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("e2"))));
	}

	@Test
	public void getPawnMoves()
	{
		GameState s = new GameState("e3", "Kd4 Nf4", Players.WHITE);
		assertEquals("d4 e4 f4", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("e3"))));
	}

	@Test
	public void getPawnMovesWithoutAttackPossibilities()
	{
		GameState s = new GameState("e3 Nf4", "", Players.WHITE);
		assertEquals("e4", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("e3"))));
	}

	@Test
	public void getPawnMovesWhenBlocked()
	{
		GameState s = new GameState("e3", "Ne4", Players.WHITE);
		assertEquals("", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("e3"))));
	}

	@Test
	public void getPawnMovesWhenDoublePushIsBlocked()
	{
		GameState s = new GameState("e2", "Ne4", Players.WHITE);
		assertEquals("e3",
				str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("e2"))));
	}

	@Test
	public void getPawnMovesAndThreatenedSquaresOnLastRow()
	{
		GameState s = new GameState("d8", "", Players.WHITE);
		assertEquals("", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("d8"))));
		assertEquals("", str(s.getThreatenedSquares(Players.WHITE, Pieces.PAWN, sq("d8"))));
	}

	@Test
	public void getPawnMovesAndThreatenedSquaresOnFirstAndLastColumn()
	{
		GameState s = new GameState("a5", "Nb6", Players.WHITE);
		assertEquals("a6 b6", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("a5"))));
		assertEquals("b6", str(s.getThreatenedSquares(Players.WHITE, Pieces.PAWN, sq("a5"))));

		s = new GameState("h2", "Ng3", Players.WHITE);
		assertEquals("h4 g3 h3", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("h2"))));
		assertEquals("g3", str(s.getThreatenedSquares(Players.WHITE, Pieces.PAWN, sq("h2"))));
	}

	@Test
	public void getPawnThreatenedSquares()
	{
		GameState s = new GameState("e2", "", Players.WHITE);
		assertEquals("d3 f3", str(s.getThreatenedSquares(Players.WHITE, Pieces.PAWN, sq("e2"))));

		s = new GameState("e2 Kd3", "Kf3", Players.WHITE);
		assertEquals("d3 f3", str(s.getThreatenedSquares(Players.WHITE, Pieces.PAWN, sq("e2"))));
	}

	@Test
	public void getPawnMovesWithEnPassant()
	{
		GameState s = new GameState("c2", "d4", Players.WHITE);
		s.makeMove(Move.fromString("c2-c4"));
		assertEquals("c3 d3", str(s.getPseudoLegalMoves(Players.BLACK, Pieces.PAWN, sq("d4"))));
	}

	@Test
	public void otherPiecesCannotDoEnPassant()
	{
		GameState s = new GameState("c2", "Nd4", Players.WHITE);
		s.makeMove(Move.fromString("c2-c4"));
		assertEquals("c6 e6 b5 f5 b3 f3 c2 e2",
				str(s.getPseudoLegalMoves(Players.BLACK, Pieces.KNIGHT, sq("d4"))));
	}

	@Test
	public void bugfixTest1()
	{
		// ohestalyöntiruudun arvo 0 eikä -1
		GameState s = new GameState("b7", "", Players.WHITE);
		assertEquals("b8", str(s.getPseudoLegalMoves(Players.WHITE, Pieces.PAWN, sq("b7"))));
	}

	@Test
	public void getPseudoLegalReturnsZeroIfNoPieceInSqr()
	{
		GameState s = new GameState("", "", Players.WHITE);
		assertEquals("", str(s.getPseudoLegalMoves(Players.WHITE, sq("h1"))));
	}

	@Test
	public void getLegalMovesReturnsEmptyListIfNoPieceInSqr()
	{
		GameState s = new GameState("", "", Players.WHITE);
		assertEquals(0, s.getLegalMoves(sq("f3")).length);
	}

//	@Test
//	public void getLegalDoesntReturnMovesThatLeaveKingInCheck()
//	{
//		
//	}
	@Test
	public void areBothKingsAliveTrue()
	{
		GameState s = new GameState("Ke8", "Ka1 Nf3", Players.WHITE);
		assertTrue(s.areBothKingsAlive());
	}

	@Test
	public void areBothKingsAliveFalse()
	{
		GameState s = new GameState("Ke8", "Nf3", Players.WHITE);
		assertFalse(s.areBothKingsAlive());

		s = new GameState("", "Ka1 Nf3", Players.WHITE);
		assertFalse(s.areBothKingsAlive());
	}

	@Test
	public void isCheckMateReturnsTrue()
	{
		GameState s = new GameState("Kb8", "Kb6 Rd8", Players.WHITE);
		assertTrue(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseIfKingCanEscape()
	{
		GameState s = new GameState("Ke5", "Kb6 Rc5", Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseIfKingCanCaptureAgressor()
	{
		GameState s = new GameState("Ka8", "Kb6 Rb8", Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isCheckMateReturnsFalseWhenStaleMate()
	{
		GameState s = new GameState("Kb8", "Kb6 Qc6", Players.WHITE);
		assertFalse(s.isCheckMate());
	}

	@Test
	public void isStaleMateReturnsTrue()
	{
		GameState s = new GameState("Kb8", "Kb6 Qc6", Players.WHITE);
		assertTrue(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseWhenCheckMate()
	{
		GameState s = new GameState("Kb8", "Kb6 Rd8", Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseIfKingCanEscape()
	{
		GameState s = new GameState("Ke5", "Kb6 Rc5", Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void isStaleMateReturnsFalseIfKingCanCaptureAgressor()
	{
		GameState s = new GameState("Ka8", "Kb6 Rb8", Players.WHITE);
		assertFalse(s.isStaleMate());
	}

	@Test
	public void makeMoveChangesBoardLayout()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 d7", Players.WHITE);
		s.makeMove(Move.fromString("Kc3-c4"));
		assertEquals(new BitBoard("Kc4 Nd2", "Kf5 d7"), s.getBoard());
	}

	@Test
	public void makeMoveHandlesEnPassantCorrectly()
	{
		GameState s = new GameState("b2 Kh1", "c4", Players.WHITE);
		s.makeMove(Move.fromString("b2-b4"));
		assertEquals(sq("b3"), s.getEnPassantSquare());
		s.makeMove(Move.fromString("c4xb3"));
		assertEquals(new BitBoard("Kh1", "b3"), s.getBoard());
		assertEquals(-1, s.getEnPassantSquare());
	}

	@Test
	public void makeMoveUpdatesRookPositionWhenCastling()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-c1"));
		assertEquals(new BitBoard("Kc1 Rd1 Rh1", "Ra8 Ke8 Rh8"), s.getBoard());
		s.makeMove(Move.fromString("Ke8-c8"));
		assertEquals(new BitBoard("Kc1 Rd1 Rh1", "Kc8 Rd8 Rh8"), s.getBoard());

		s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-g1"));
		assertEquals(new BitBoard("Ra1 Rf1 Kg1", "Ra8 Ke8 Rh8"), s.getBoard());
		s.makeMove(Move.fromString("Ke8-g8"));
		assertEquals(new BitBoard("Ra1 Rf1 Kg1", "Ra8 Rf8 Kg8"), s.getBoard());
	}

	@Test
	public void makeMoveUpdatesCastlingRights()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-e2"));
		assertEquals("a8 h8", str(s.getCastlingRights()));
		s.makeMove(Move.fromString("Ra8-b8"));
		assertEquals("h8", str(s.getCastlingRights()));
	}

	@Test
	public void makeMoveChangesPlayer()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 d7", Players.WHITE);
		s.makeMove(Move.fromString("Kc3-c4"));
		assertEquals(Players.BLACK, s.getNextMovingPlayer());
	}

	@Test
	public void equalsToConstructedStateAfterMakeMove()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 d7", Players.WHITE);
		s.makeMove(Move.fromString("Kc3-c4"));
		GameState s2 = new GameState("Kc4 Nd2", "Kf5 d7", Players.BLACK);
		assertEquals(s2, s);
		assertEquals(s2.getId(), s.getId());
	}

	@Test
	public void makeMoveUpdatesHashCode()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 d7", Players.WHITE);
		long h = s.getId();
		s.makeMove(Move.fromString("Kc3-c4"));
		assertFalse(h == s.getId());
	}

	@Test
	public void undoRestoresOldStateWhenNoCapture()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 d7", Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.fromString("Kc3-c4");
		s.makeMove(move);
		s.undoMove(move);
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void undoRestoresOldStateWhenCapturedPiece()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 c4", Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.fromString("Kc3xc4");
		s.makeMove(move);
		s.undoMove(move);
		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void undoRestoresOldStateWithPromotionMove()
	{
		GameState s = new GameState("Kc3 d7", "Kf5", Players.WHITE);
		GameState s2 = s.clone();
		int move = Move.fromString("d7-d8Q");
		s.makeMove(move);
		s.undoMove(move);
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
	public void undoRestoresOldStateWithQueenSideCastling()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-c1"));
		s.undoMove(Move.fromString("Ke1-c1"));
		assertEquals(new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.WHITE), s);
	}

	@Test
	public void undoRestoresOldStateWithKingSideCastling()
	{
		GameState s = new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.BLACK);
		s.makeMove(Move.fromString("Ke8-g8"));
		s.undoMove(Move.fromString("Ke8-g8"));
		assertEquals(new GameState("Ra1 Ke1 Rh1", "Ra8 Ke8 Rh8", Players.BLACK), s);
	}

	@Test
	public void nullMove()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 c4", Players.WHITE);
		GameState s2 = s.clone();
		s.makeNullMove();
		assertEquals(new GameState("Kc3 Nd2", "Kf5 c4", Players.BLACK), s);
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
	public void nullMoveKeepsCastlingRights()
	{
		GameState s = new GameState("Ra1 Ke1", "a2", Players.WHITE);
		s.makeNullMove();
		assertEquals("a1", str(s.getCastlingRights()));
	}

	@Test
	public void nullMoveKeepsHalfMoveClock()
	{
		GameState s = new GameState("Ra1 Ke1", "a2", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-e2"));
		s.makeNullMove();
		assertEquals(1, s.getHalfMoveClock());
	}

	@Test
	public void nullMoveAfterDoublePush()
	{
		GameState s = new GameState("a2", "b4", Players.WHITE);
		s.makeMove(Move.fromString("a2-a4"));
		s.makeNullMove();
		GameState s2 = new GameState("a4", "b4", Players.WHITE);
		assertEquals(s2, s);
		assertEquals(s2.getId(), s.getId());
	}

	@Test
	public void undoNullMoveRestoresOldState()
	{
		GameState s = new GameState("Kc3 Nd2", "Kf5 c4", Players.WHITE);
		GameState s2 = s.clone();
		s.makeNullMove();
		s.undoNullMove();

		assertTrue(s.equals(s2));
		assertTrue(s.getId() == s2.getId());
		assertEquals(Players.WHITE, s.getNextMovingPlayer());
	}

	@Test
	public void constructorGetsPiecesFromString()
	{
		GameState s = new GameState("a2 Kd4", "Qh1 Ba8", Players.BLACK);
		assertEquals(Players.BLACK, s.getNextMovingPlayer());
		assertEquals("d4 a2", str(s.getPieces(Players.WHITE)));
		assertEquals("a8 h1", str(s.getPieces(Players.BLACK)));
		assertEquals("a2", str(s.getPieces(Players.WHITE, Pieces.PAWN)));
		assertEquals("d4", str(s.getPieces(Players.WHITE, Pieces.KING)));
		assertEquals("h1", str(s.getPieces(Players.BLACK, Pieces.QUEEN)));
		assertEquals("a8", str(s.getPieces(Players.BLACK, Pieces.BISHOP)));
	}

	@Test
	public void testLegalMoveGenerationInInitialPosition()
	{
		GameState s = new GameState();
		for (int i = 0; i < 2; ++i) {
			assertEquals(20, perft(s, 1));
			assertEquals(400, perft(s, 2));
			assertEquals(8902, perft(s, 3));
			//assertEquals(197281, perft(s, 4));
			//assertEquals(4865609, perft(s, 5));
			//assertEquals(119060324, perft(s, 6));
			s.makeNullMove();
		}
	}

	@Test
	public void testLegalMoveGenerationInAnotherPosition()
	{
		// http://chessprogramming.wikispaces.com/Perft+Results (Position 4)
		GameState s = new GameState("Kg1 Qd1 Ra1 Rf1 Ba4 Bb4 Nf3 Nh6 a2 a7 b5 c4 d2 e4 g2 h2",
				"Ke8 Qa3 Ra8 Rh8 Bb6 Bg6 Na5 Nf6 b7 b2 c7 d7 f7 g7 h7", Players.WHITE);
		for (int i = 0; i < 2; ++i) {
			assertEquals(6, perft(s, 1));
			assertEquals(264, perft(s, 2));
			assertEquals(9467, perft(s, 3));
			//assertEquals(422333, perft(s, 4));
			//assertEquals(15833292, perft(s, 5));
			s = new GameState("Ke1 Qa6 Ra1 Rh1 Bb3 Bg3 Na4 Nf3 b2 b7 c2 d2 f2 g2 h2",
					"Kg8 Qd8 Ra8 Rf8 Ba5 Bb5 Nf6 Nh3 a7 a2 b4 c5 d7 e5 g7 h7", Players.BLACK);
		}

	}

	@Test
	public void repeatedPositionIsAStaleMate()
	{
		GameState s = new GameState("Ka1 Qb1", "Kg7 Qh8", Players.WHITE);
		s.makeMove(Move.fromString("Qb1-c1"));
		s.makeMove(Move.fromString("Qh8-g8"));
		s.makeMove(Move.fromString("Qc1-b1"));
		s.makeMove(Move.fromString("Qg8-h8"));
		assertTrue(s.isStaleMate());
	}

	@Test
	public void notRepeatedPositionIfDifferentEnPassantSquare()
	{
		GameState s = new GameState("Ka1 Qb1 e2", "Kg7 Qh8 d4", Players.WHITE);
		s.makeMove(Move.fromString("e2-e4"));
		s.makeMove(Move.fromString("Qh8-g8"));
		s.makeMove(Move.fromString("Qb1-c1"));
		s.makeMove(Move.fromString("Qg8-h8"));
		s.makeMove(Move.fromString("Qc1-b1"));
		assertFalse(s.isStaleMate());
	}

	@Test
	public void notRepeatedPositionIfDifferentCastlingRights()
	{
		GameState s = new GameState("Ke1 Ra1", "Ke8", Players.WHITE);
		s.makeMove(Move.fromString("Ra1-a2"));
		s.makeMove(Move.fromString("Ke8-e7"));
		s.makeMove(Move.fromString("Ra2-a1"));
		s.makeMove(Move.fromString("Ke7-e8"));
		assertFalse(s.isStaleMate());
	}

	@Test
	public void normalMovesIncreaseHalfMoveClock()
	{
		GameState s = new GameState("Ke1", "Ke8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-e2"));
		assertEquals(1, s.getHalfMoveClock());
		s.makeMove(Move.fromString("Ke8-e7"));
		assertEquals(2, s.getHalfMoveClock());
	}

	@Test
	public void pawnMovesResetHalfMoveClock()
	{
		GameState s = new GameState("Ke1", "Ke8 d7", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-e2"));
		s.makeMove(Move.fromString("d7-d6"));
		assertEquals(0, s.getHalfMoveClock());
	}

	@Test
	public void capturesResetHalfMoveClock()
	{
		GameState s = new GameState("Ke1 Ra1", "Ke8 Ra8", Players.WHITE);
		s.makeMove(Move.fromString("Ke1-e2"));
		s.makeMove(Move.fromString("Ra8xRa1"));
		assertEquals(0, s.getHalfMoveClock());
	}

	@Test
	public void staleMateWhenHalfMoveClockReaches50()
	{
		GameState s = new GameState("Ke1 Qa1", "Ke8 Qa8", Players.WHITE);
		String[][] moveCycles = new String[][]{
			{"Qa1-a2", "Qa2-a3", "Qa3-b3", "Qb3-c3", "Qc3-c2", "Qc2-b1", "Qb1-a1"}, // 7
			{"Qa8-a7", "Qa7-a6", "Qa6-b6", "Qb6-c6", "Qc6-c7", "Qc7-c8", "Qc8-b8", "Qb8-a8"} // 8
		};
		// 7 * 8 >= 50
		for (int i = 0; i < 50; ++i) {
			assertFalse(s.isStaleMate());
			s.makeMove(Move.fromString(moveCycles[i % 2][i / 2 % moveCycles[i % 2].length]));
		}
		assertTrue(s.isStaleMate());
	}
}
