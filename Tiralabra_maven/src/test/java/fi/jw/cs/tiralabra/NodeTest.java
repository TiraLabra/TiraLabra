package fi.jw.cs.tiralabra;

import junit.framework.*;

import java.util.*;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class NodeTest extends TestCase {

    public void testComparator() {
        Comparator<Node> comp = Node.getComparator();
        Node n1 = new Node("a", 0);
        Node n2 = new Node("a", 2);
        assertTrue(0 > comp.compare(n1, n2));
    }

    public void testLeafiness() {
        Node parent = new Node();
        Node child = new Node();
        parent.setLeft(child);

        assertTrue(child.isLeaf());
        assertFalse(parent.isLeaf());
    }

    public void testCounting() {
        Node root = new Node();
        Node left1 = new Node();
        Node right1 = new Node();
        Node left2 = new Node();

        assertEquals(0, root.getChildCount());
        assertEquals(0, root.getLeftCount());
        assertEquals(0, root.getRightCount());

        root.setLeft(left1);

        assertEquals(1, root.getChildCount());
        assertEquals(1, root.getLeftCount());
        assertEquals(0, root.getRightCount());

        left1.setRight(left2);

        assertEquals(2, root.getChildCount());
        assertEquals(2, root.getLeftCount());
        assertEquals(0, root.getRightCount());

        assertEquals(1, left1.getChildCount());
        assertEquals(0, left1.getLeftCount());
        assertEquals(1, left1.getRightCount());


    }
}
