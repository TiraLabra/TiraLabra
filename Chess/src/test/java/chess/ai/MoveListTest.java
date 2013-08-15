package chess.ai;

import chess.domain.BitBoard;
import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoveListTest
{
	private MoveList list;

	private int find(int fromSqr, int toSqr, int pieceType, int capturedType, int promotedType)
	{
		int move = Move.pack(fromSqr, toSqr, pieceType, capturedType, promotedType);
		for (int pri = 0; pri < MoveList.PRIORITIES; ++pri) {
			for (int i = 0; i < list.getCount(pri); ++i) {
				if (list.getMove(pri, i) == move)
					return pri;
			}
		}
		return -1;
	}

	@Before
	public void setUp()
	{
		list = new MoveList();
		// 0  1  2  3  4  5  6  7
		// 8  9  10 11 12 13 14 15
		// 16 17 18 19 20 21 22 23
		// 24 25 26 27 28 29 30 31
		// 32 33 34 35 36 37 38 39
		// 40 41 42 43 44 45 46 47
		// 48 49 50 51 52 53 54 55
		// 56 57 58 59 60 61 62 63
		//
		// .  .  R  .  .  .  .  .
		// .  p  .  .  .  .  K  k
		// .  .  .  .  .  .  .  P
		// .  Q  .  .  .  .  .  n
		// .  .  p  .  .  .  .  .
		// .  .  .  .  .  q  .  .
		// .  .  .  .  R  .  .  .
		// .  .  .  .  .  .  .  .
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.KING, 14);
		bb.addPiece(Players.BLACK, Pieces.PAWN, 23);
		bb.addPiece(Players.BLACK, Pieces.QUEEN, 25);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 52);
		bb.addPiece(Players.BLACK, Pieces.ROOK, 2);
		bb.addPiece(Players.WHITE, Pieces.KING, 15);
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 31);
		bb.addPiece(Players.WHITE, Pieces.QUEEN, 45);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 9);
		bb.addPiece(Players.WHITE, Pieces.PAWN, 34);
		GameState state = new GameState(bb, Players.WHITE);
		list.populate(state);
	}

	@Test
	public void newMoveListIsEmpty()
	{
		list = new MoveList();
		for (int pri = 0; pri < MoveList.PRIORITIES; ++pri)
			assertEquals(0, list.getCount(pri));
	}

	@Test
	public void kingCapturesHavePriority0()
	{
		assertEquals(0, find(31, 14, Pieces.KNIGHT, Pieces.KING, -1));
		assertEquals(0, find(15, 14, Pieces.KING, Pieces.KING, -1));
	}

	@Test
	public void normalCapturesHavePriorities1to9()
	{
		assertEquals(1, find(34, 25, Pieces.PAWN, Pieces.QUEEN, -1));
		assertEquals(5, find(45, 52, Pieces.QUEEN, Pieces.ROOK, -1));
		assertEquals(9, find(15, 23, Pieces.KING, Pieces.PAWN, -1));
	}

	@Test
	public void capturePromotionsHaveCorrectPriorities()
	{
		assertEquals(2, find(9, 2, Pieces.PAWN, Pieces.ROOK, Pieces.QUEEN));
		assertEquals(2, find(9, 2, Pieces.PAWN, Pieces.ROOK, Pieces.KNIGHT));
	}

	@Test
	public void queenPromotionsHavePriority3()
	{
		assertEquals(3, find(9, 1, Pieces.PAWN, -1, Pieces.QUEEN));
	}

	@Test
	public void otherPromotionsHavePriority11()
	{
		assertEquals(11, find(9, 1, Pieces.PAWN, -1, Pieces.ROOK));
		assertEquals(11, find(9, 1, Pieces.PAWN, -1, Pieces.BISHOP));
		assertEquals(11, find(9, 1, Pieces.PAWN, -1, Pieces.KNIGHT));
	}

	@Test
	public void normalMovesHavePriority10()
	{
		assertEquals(10, find(15, 7, Pieces.KING, -1, -1));
		assertEquals(10, find(45, 27, Pieces.QUEEN, -1, -1));
	}

	@Test
	public void moveCountIsCorrect()
	{
		assertEquals(2, list.getCount(0));
		assertEquals(1, list.getCount(1));
		assertEquals(4, list.getCount(2));
		assertEquals(27, list.getCount(10));
		assertEquals(3, list.getCount(11));
	}

	@Test
	public void findsMovesFromCorrectPlayer()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.BLACK, Pieces.PAWN, 44);
		GameState state = new GameState(bb, Players.BLACK);
		list.populate(state);
		assertEquals(10, find(44, 52, Pieces.PAWN, -1, -1));
	}

	@Test
	public void repopulatingRemovesOldMoves()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 7);
		GameState state = new GameState(bb, Players.WHITE);
		list.populate(state);
		assertEquals(-1, find(34, 25, Pieces.PAWN, Pieces.QUEEN, -1));
	}

	@Test
	public void hasCorrectMovesAfterRepopulating()
	{
		BitBoard bb = new BitBoard();
		bb.addPiece(Players.WHITE, Pieces.KNIGHT, 7);
		GameState state = new GameState(bb, Players.WHITE);
		list.populate(state);
		for (int pri = 0; pri < MoveList.PRIORITIES; ++pri)
			assertEquals(pri == 10 ? 2 : 0, list.getCount(pri));
	}
}
