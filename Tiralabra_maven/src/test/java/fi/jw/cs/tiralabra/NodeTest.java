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

}
