package chess.game;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import chess.util.CustomArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest
{
	private Game game;

	private static String[] mateMoves = new String[]{
		"f2-f3",
		"e7-e5",
		"g2-g4",
		"Qd8-h4"
	};

	private static String[] staleMateMoves = new String[]{
		"e2-e3",
		"a7-a5",
		"Qd1-h5",
		"Ra8-a6",
		"Qh5xa5",
		"h7-h5",
		"h2-h4",
		"Ra6-h6",
		"Qa5xc7",
		"f7-f6",
		"Qc7xd7",
		"Ke8-f7",
		"Qd7xb7",
		"Qd8-d3",
		"Qb7xNb8",
		"Qd3-h7",
		"Qb8xBc8",
		"Kf7-g6",
		"Qc8-e6"
	};

	private static class TestPlayer implements Player
	{
		private String[] moves;

		public TestPlayer(String[] moves)
		{
			this.moves = moves;
		}

		@Override
		public int getMove(GameState state) throws InterruptedException
		{
			int ply = state.getEarlierStates().length;
			if (ply < moves.length)
				return Move.fromString(moves[ply]);
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
		List<Integer> moves = new CustomArrayList<Integer>();

		List<Integer> results = new CustomArrayList<Integer>();

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
		assertEquals(Pieces.COUNT + Pieces.PAWN, game.getState().getBoard()[28]);
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
		assertEquals(mateMoves[2], Move.toString(obs.moves.get(2).intValue()));
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
		assertEquals(mateMoves[0], Move.toString(game.getMoves().get(0).intValue()));
		assertEquals(mateMoves[1], Move.toString(game.getMoves().get(1).intValue()));
		assertEquals(mateMoves[2], Move.toString(game.getMoves().get(2).intValue()));
		assertEquals(mateMoves[3], Move.toString(game.getMoves().get(3).intValue()));
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
