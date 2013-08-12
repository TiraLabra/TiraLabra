package chess.ai;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeGeneratorTest
{
	private TreeGenerator tg;

	@Before
	public void setUp()
	{
		tg = new TreeGenerator(1);
	}

	@Test
	public void newTreeIsEmpty()
	{
		assertNull(tg.getTree());
	}

	@Test
	public void saveNodeInfo()
	{
		tg.startNode(1, 2, 3);
		tg.endNode(4, 5, 6, 7, 8, 9);
		assertNotNull(tg.getTree());
		assertEquals(1, tg.getTree().alpha);
		assertEquals(2, tg.getTree().beta);
		assertEquals(3, tg.getTree().player);
		assertEquals(4, tg.getTree().fromSqr);
		assertEquals(5, tg.getTree().toSqr);
		assertEquals(6, tg.getTree().score);
		assertEquals(7, tg.getTree().pieceType);
		assertEquals(8, tg.getTree().capturedPiece);
		assertEquals(9, tg.getTree().nodeType);
	}

	@Test
	public void multipleChildren()
	{
		tg.startNode(1, 0, 0);
		tg.startNode(2, 0, 0);
		tg.endNode(2, 0, 0, 0, 0, 0);
		tg.startNode(3, 0, 0);
		tg.endNode(3, 0, 0, 0, 0, 0);
		tg.endNode(1, 0, 0, 0, 0, 0);
		assertNotNull(tg.getTree());
		assertEquals(1, tg.getTree().alpha);
		assertEquals(1, tg.getTree().fromSqr);
		assertEquals(2, tg.getTree().nodes.size());
		assertEquals(2, tg.getTree().nodes.get(0).alpha);
		assertEquals(2, tg.getTree().nodes.get(0).fromSqr);
		assertEquals(3, tg.getTree().nodes.get(1).alpha);
		assertEquals(3, tg.getTree().nodes.get(1).fromSqr);
	}

	@Test
	public void limitedByMaxDepth()
	{
		tg.startNode(1, 0, 0);
		tg.startNode(2, 0, 0);
		tg.startNode(3, 0, 0);
		tg.endNode(3, 0, 0, 0, 0, 0);
		tg.endNode(2, 0, 0, 0, 0, 0);
		tg.endNode(1, 0, 0, 0, 0, 0);
		assertNotNull(tg.getTree());
		assertEquals(0, tg.getTree().ply);
		assertEquals(1, tg.getTree().alpha);
		assertEquals(1, tg.getTree().fromSqr);
		assertEquals(1, tg.getTree().nodes.size());
		assertEquals(1, tg.getTree().nodes.get(0).ply);
		assertEquals(2, tg.getTree().nodes.get(0).alpha);
		assertEquals(2, tg.getTree().nodes.get(0).fromSqr);
		assertEquals(0, tg.getTree().nodes.get(0).nodes.size());
	}

	@Test
	public void startingNewTree()
	{
		tg.startNode(1, 0, 0);
		tg.endNode(1, 0, 0, 0, 0, 0);
		tg.startNode(2, 0, 0);
		tg.endNode(2, 0, 0, 0, 0, 0);
		assertNotNull(tg.getTree());
		assertEquals(2, tg.getTree().alpha);
		assertEquals(2, tg.getTree().fromSqr);
	}
}
