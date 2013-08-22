package chess.game;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest
{
	private Game game;

	private static int[] mateMoves = new int[]{
		Move.pack(53, 45, Pieces.PAWN, -1, -1),
		Move.pack(30, 36, Pieces.PAWN, -1, -1),
		Move.pack(54, 38, Pieces.PAWN, -1, -1),
		Move.pack(3, 39, Pieces.QUEEN, -1, -1)
	};

	private static int[] staleMateMoves = new int[]{
		Move.pack(52, 44, Pieces.PAWN, -1, -1),
		Move.pack(8, 24, Pieces.PAWN, -1, -1),
		Move.pack(59, 31, Pieces.QUEEN, -1, -1),
		Move.pack(0, 16, Pieces.ROOK, -1, -1),
		Move.pack(31, 24, Pieces.QUEEN, Pieces.PAWN, -1),
		Move.pack(15, 31, Pieces.PAWN, -1, -1),
		Move.pack(55, 39, Pieces.PAWN, -1, -1),
		Move.pack(16, 23, Pieces.ROOK, -1, -1),
		Move.pack(24, 10, Pieces.QUEEN, Pieces.PAWN, -1),
		Move.pack(13, 21, Pieces.PAWN, -1, -1),
		Move.pack(10, 11, Pieces.QUEEN, Pieces.PAWN, -1),
		Move.pack(4, 13, Pieces.KING, -1, -1),
		Move.pack(11, 9, Pieces.QUEEN, Pieces.PAWN, -1),
		Move.pack(3, 43, Pieces.QUEEN, -1, -1),
		Move.pack(9, 1, Pieces.QUEEN, Pieces.KNIGHT, -1),
		Move.pack(43, 15, Pieces.QUEEN, -1, -1),
		Move.pack(1, 2, Pieces.QUEEN, Pieces.BISHOP, -1),
		Move.pack(13, 22, Pieces.KING, -1, -1),
		Move.pack(2, 20, Pieces.QUEEN, Pieces.BISHOP, -1)
	};

	private static class TestPlayer implements Player
	{
		private int[] moves;

		public TestPlayer(int[] moves)
		{
			this.moves = moves;
		}

		@Override
		public int getMove(GameState state) throws InterruptedException
		{
			int ply = state.getEarlierStates().length;
			if (ply < moves.length)
				return moves[ply];
			else
				fail();
			return 0;
		}
	}

	private static class TestPlayer2 implements Player
	{
		@Override
		public int getMove(GameState state) throws InterruptedException
		{
			for (int i = 0; i < 10000; ++i) {
				if (Thread.interrupted())
					throw new InterruptedException();
				Thread.sleep(10);
			}
			fail();
			return 0;
		}
	}

	private static class TestObserver implements Observer
	{
		ArrayList<Integer> moves = new ArrayList<Integer>();

		ArrayList<Integer> results = new ArrayList<Integer>();

		@Override
		public void notifyMove(GameState state, int ply, Player player, int move)
		{
			moves.add(move);
		}

		@Override
		public void notifyEnd(GameState state, int result)
		{
			results.add(result);
		}
	}

	@Test
	public void usesPlayerMoves()
	{
		game = new Game(new TestPlayer(mateMoves), new TestPlayer(mateMoves), null);
		game.run();
		assertEquals(4, game.getState().getEarlierStates().length);
		assertEquals(Pieces.PAWN, game.getState().getBoard()[45]);
		assertEquals(Pieces.COUNT + Pieces.PAWN, game.getState().getBoard()[36]);
		assertEquals(Pieces.PAWN, game.getState().getBoard()[38]);
		assertEquals(Pieces.COUNT + Pieces.QUEEN, game.getState().getBoard()[39]);
	}

	@Test
	public void detectsStaleMate()
	{
		game = new Game(new TestPlayer(staleMateMoves), new TestPlayer(staleMateMoves), null);
		game.run();
		assertEquals(-1, game.getResult());
	}

	@Test
	public void setsResult()
	{
		game = new Game(new TestPlayer(mateMoves), new TestPlayer(mateMoves), null);
		game.run();
		assertEquals(Players.BLACK, game.getResult());
	}

	@Test
	public void notifiesObserverAboutMoves()
	{
		TestObserver obs = new TestObserver();
		game = new Game(new TestPlayer(mateMoves), new TestPlayer(mateMoves), obs);
		game.run();
		assertEquals(4, obs.moves.size());
		assertEquals(mateMoves[2], obs.moves.get(2).intValue());
	}

	@Test
	public void notifiesObserverAboutResult()
	{
		TestObserver obs = new TestObserver();
		game = new Game(new TestPlayer(mateMoves), new TestPlayer(mateMoves), obs);
		game.run();
		assertEquals(1, obs.results.size());
		assertEquals(1, obs.results.get(0).intValue());
	}

	@Test
	public void setsMoveHistory()
	{
		game = new Game(new TestPlayer(mateMoves), new TestPlayer(mateMoves), null);
		game.run();
		assertEquals(mateMoves.length, game.getMoves().size());
		assertEquals(mateMoves[0], game.getMoves().get(0).intValue());
		assertEquals(mateMoves[1], game.getMoves().get(1).intValue());
		assertEquals(mateMoves[2], game.getMoves().get(2).intValue());
		assertEquals(mateMoves[3], game.getMoves().get(3).intValue());
	}

	@Test
	public void canBeInterrupted()
	{
		game = new Game(new TestPlayer2(), new TestPlayer2(), null);
		Thread thr = new Thread(game);
		thr.start();
		thr.interrupt();
		assertEquals(0, game.getMoves().size());
		assertEquals(-1, game.getResult());
	}
}
