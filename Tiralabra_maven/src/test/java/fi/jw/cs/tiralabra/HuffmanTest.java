package fi.jw.cs.tiralabra;

import junit.framework.*;

import java.util.*;

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
        assertEquals("21", root.getLabel());
        assertEquals(3, root.getWeight());
    }

    public void testCodeAssignment() {
        Huffman h = new Huffman("122");
        h.calculateFrequencies();
        h.buildTree();
        h.assignCodes();

        /*
                21
           0  /    \ 1
            2       1
         */
        assertEquals("0", h.getCodeFor("2"));
        assertEquals("1", h.getCodeFor("1"));

        /*
                421
           0  /     \ 1
            4        21
                10 /    \ 11
                2       1

         */
        h = new Huffman("1224444");
        h.calculateFrequencies();
        h.buildTree();
        h.assignCodes();

        assertEquals("0", h.getCodeFor("4"));
        assertEquals("10", h.getCodeFor("2"));
        assertEquals("11", h.getCodeFor("1"));


        /*
                   5421
                0/     \1
               421      5
            00/   \01
            4      21
               010/  \011
                2     1
         */
        h = new Huffman("122444455555");
        h.calculateFrequencies();
        h.buildTree();
        h.assignCodes();

        assertEquals("1", h.getCodeFor("5"));
        assertEquals("00", h.getCodeFor("4"));
        assertEquals("010", h.getCodeFor("2"));
        assertEquals("011", h.getCodeFor("1"));

        h = new Huffman("a");
        h.calculateFrequencies();
        h.buildTree();
        h.assignCodes();

        assertEquals("0", h.getCodeFor("a"));
    }

    public void testEncodeMessage() {
        Huffman h = new Huffman("abc.");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "0");
        map.put("b", "10");
        map.put("c", "110");
        map.put(".", "111");
        h.setMap(map);
        h.encodeMessage();
        assertEquals("010110111", h.getEncodedMessage());

    }

    public void testEncodeDecode() {
        final String message = "Hello world!";
        Huffman encoder = new Huffman(message);
        encoder.encode();

        Huffman decoder = new Huffman();
        decoder.setMap(encoder.getMap());
        decoder.setEncodedMessage(encoder.getEncodedMessage());
        decoder.decode();

        assertEquals(decoder.getMessage(), message);
    }


    public void testParseMap() {
        Huffman h = new Huffman("");
        char nil = '\0';
        String map = "a" + nil + "0" + nil +
                "b" + nil + "10" + nil +
                "c" + nil + "110" + nil +
                "d" + nil + "111";
        Map<String, String> m = h.parseMap(map);

        assertTrue(m.containsKey("a"));
        assertTrue(m.containsKey("b"));
        assertTrue(m.containsKey("c"));
        assertTrue(m.containsKey("d"));

        assertEquals("0", m.get("a"));
        assertEquals("10", m.get("b"));
        assertEquals("110", m.get("c"));
        assertEquals("111", m.get("d"));
    }

}
