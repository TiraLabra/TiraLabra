package chess.ai;

import chess.domain.Pieces;
import chess.domain.Players;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ScoresTest
{
	@Before
	public void setUp()
	{
		new Scores();
	}

	@Test
	public void positionalValuesAreMirrored()
	{
		assertEquals(Scores.POSITIONAL_PIECE_VALUES[Players.WHITE][Pieces.PAWN][1 * 8 + 3],
				Scores.POSITIONAL_PIECE_VALUES[Players.BLACK][Pieces.PAWN][6 * 8 + 3]);
		assertEquals(Scores.POSITIONAL_PIECE_VALUES[Players.WHITE][Pieces.BISHOP][2 * 8 + 6],
				Scores.POSITIONAL_PIECE_VALUES[Players.BLACK][Pieces.BISHOP][5 * 8 + 6]);	
	}
}
