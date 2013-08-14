package fi.jw.cs.tiralabra;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class BinaryTreeTest {
    @Test
    public void testKeySet() throws Exception {
        BinaryTree bt = new BinaryTree();
        assertTrue(bt.isEmpty());

        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");

        bt.insert(a);
        bt.insert(b);
        bt.insert(c);

        assertFalse(bt.isEmpty());
        String[] keys = bt.keys();
        assertEquals(3, keys.length);

        boolean aFound = false;
        for (String k : keys) {
            if (k == "a")
                aFound = true;
        }
        boolean bFound = false;
        for (String k : keys) {
            if (k == "b")
                bFound = true;
        }
        boolean cFound = false;
        for (String k : keys) {
            if (k == "c")
                cFound = true;
        }

        assertTrue(aFound);
        assertTrue(bFound);
        assertTrue(cFound);

    }

    @Test
    public void testSearchByLabel() throws Exception {
        BinaryTree bt = new BinaryTree();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        bt.insert(a);
        bt.insert(b);
        bt.insert(c);

        Node correct = bt.searchByLabel("a");
        Node incorrect = bt.searchByLabel("foo");

        assertEquals(a, bt.searchByLabel("a"));
        assertEquals(b, bt.searchByLabel("b"));
        assertEquals(c, bt.searchByLabel("c"));
        assertNull(incorrect);
    }

    @Test
    public void testEquality() {
        BinaryTree bt1 = new BinaryTree();
        BinaryTree bt2 = new BinaryTree();

        Node a = new Node("a");

        a.setCode("a");

        bt1.insert(a);
        bt2.insert(a);

        assertEquals(bt1, bt2);
    }
}
