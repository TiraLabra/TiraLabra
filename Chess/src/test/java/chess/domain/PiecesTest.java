package chess.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PiecesTest
{
	@Before
	public void setUp()
	{
		new Pieces();
	}

	@Test
	public void fromStringReturnsCorrectValue()
	{
		assertEquals(Pieces.KING, Pieces.fromString("K"));
		assertEquals(Pieces.PAWN, Pieces.fromString(""));
	}

	@Test
	public void fromStringReturnsMinusOneIfInvalidSymbol()
	{
		assertEquals(-1, Pieces.fromString("k"));
	}
}
