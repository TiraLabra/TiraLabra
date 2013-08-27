package chess.ai;

import chess.domain.Move;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NodeTest
{
	@Before
	public void setUp()
	{
	}

	@Test
	public void constructorSetsParentAndPly()
	{
		Node parent = new Node(0, 1, 2, 0, 0, null);
		Node n = new Node(1, -1, 2, 1, 0, parent);
		assertSame(parent, n.parent);
		assertEquals(1, n.ply);
	}

	@Test
	public void toStringReturnsCorrectTextWhenScoreIsAlpha()
	{
		Node n = new Node(1, 42, 52, 1, Move.fromString("Ba8-h1"), null);
		n.setResult(42, StateInfo.NODE_TYPE_UPPER_BOUND);
		assertEquals("Ba8-h1 ≥-42 (α=42 β=52 s≤42)", n.toString());
	}

	@Test
	public void toStringReturnsCorrectTextWhenScoreLessThanAlpha()
	{
		Node n = new Node(1, -52, -42, 1, Move.fromString("Ba8-h1"), null);
		n.setResult(-53, StateInfo.NODE_TYPE_UPPER_BOUND);
		assertEquals("Ba8-h1 ≥53 (α=-52 β=-42 s≤-53)", n.toString());
	}

	@Test
	public void toStringReturnsCorrectTextWhenScoreIsBeta()
	{
		Node n = new Node(1, 13, 16, 0, Move.fromString("Ba8-h1"), null);
		n.setResult(16, StateInfo.NODE_TYPE_LOWER_BOUND);
		assertEquals("Ba8-h1 ≤-16 (α=13 β=16 s≥16)", n.toString());
	}

	@Test
	public void toStringReturnsCorrectTextWhenScoreGreaterThanBeta()
	{
		Node n = new Node(1, 13, 16, 0, Move.fromString("Ba8-h1"), null);
		n.setResult(17, StateInfo.NODE_TYPE_LOWER_BOUND);
		assertEquals("Ba8-h1 ≤-17 (α=13 β=16 s≥17)", n.toString());
	}

	@Test
	public void toStringReturnsCorrectTextWhenExactScore()
	{
		Node n = new Node(1, 13, 16, 0, Move.fromString("Ba8-h1"), null);
		n.setResult(14, StateInfo.NODE_TYPE_EXACT);
		assertEquals("Ba8-h1 =-14 (α=13 β=16 s=14)", n.toString());
	}

	@Test
	public void toStringReturnsCorrectTextForNullMove()
	{
		Node n = new Node(1, 13, 16, 1, 0, null);
		n.setResult(15, StateInfo.NODE_TYPE_EXACT);
		assertEquals("Null move search =-15 (α=13 β=16 s=15)", n.toString());
	}

	@Test
	public void toStringReturnsCorrectTextForRootNode()
	{
		Node n = new Node(0, 13, 16, 1, 0, null);
		n.setResult(15, StateInfo.NODE_TYPE_EXACT);
		assertEquals(" (α=13 β=16 s=15)", n.toString());
	}

	@Test
	public void toStringUsesInfinityForMinAndMax()
	{
		Node n = new Node(1, Scores.MIN, Scores.MAX, 1, Move.fromString("Ba8-h1"), null);
		n.setResult(Scores.MAX, StateInfo.NODE_TYPE_LOWER_BOUND);
		assertEquals("Ba8-h1 ≤-∞ (α=-∞ β=∞ s≥∞)", n.toString());
	}

	@Test
	public void toStringUsesInfinityPlusMinusOne()
	{
		Node n = new Node(1, Scores.MAX - 1, Scores.MAX, 1, Move.fromString("Ba8-h1"), null);
		n.setResult(Scores.MAX - 1, StateInfo.NODE_TYPE_UPPER_BOUND);
		assertEquals("Ba8-h1 ≥-∞+1 (α=∞-1 β=∞ s≤∞-1)", n.toString());
	}
}
