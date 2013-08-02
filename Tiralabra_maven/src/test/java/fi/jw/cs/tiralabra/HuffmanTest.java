package fi.jw.cs.tiralabra;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */
public class HuffmanTest extends TestCase {

    public void testEncodeWithEmptyStringReturnsEmptyArray() throws Exception {
        Huffman h = new Huffman();
        h.encode();
        boolean[] expected = new boolean[0];
        assertTrue(Arrays.equals(expected, h.getEncoded()));
    }

    public void testSimpleFrequencies() {
        Huffman h = new Huffman("");
        h.calculateFrequencies();
        PriorityQueue<Node> freq = h.getSortedNodes();
        assertEquals(freq.size(), 0);

        h.setMessage("a");
        h.calculateFrequencies();
        freq = h.getSortedNodes();

        assertEquals(1, freq.size());

        Node s = freq.poll();
        assertEquals("a", s.getLabel());
        assertEquals(1, s.getWeight());
    }

    public void testMultipleFrequencies() {
        Huffman h = new Huffman("abbcbba");
        h.calculateFrequencies();
        PriorityQueue<Node> freq = h.getSortedNodes();

        assertEquals(3, freq.size());

        Node head = freq.poll();

        assertEquals("c", head.getLabel());
        assertEquals(1, head.getWeight());

        head = freq.poll();
        assertEquals("a", head.getLabel());
        assertEquals(2, head.getWeight());

        head = freq.poll();
        assertEquals("b", head.getLabel());
        assertEquals(4, head.getWeight());

        assertTrue(freq.isEmpty());
    }

    public void testTreeBuilding() {
        Huffman h = new Huffman("122");
        h.calculateFrequencies();
        h.buildTree();
        BinaryTree tree = h.getTree();
        Node root = tree.getRoot();
        assertEquals("12", root.getLabel());
        assertEquals(3, root.getWeight());
    }
}
