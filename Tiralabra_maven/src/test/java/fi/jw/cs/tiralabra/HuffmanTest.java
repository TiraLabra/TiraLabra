package fi.jw.cs.tiralabra;

import junit.framework.TestCase;


/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */
public class HuffmanTest extends TestCase {

    public void testEncodeWithEmptyStringThrowsException() {
        Huffman h = new Huffman();
        try {
            h.encode();
            fail("Encode with zero length message should throw exception");
        } catch (IllegalArgumentException iae) {

        }
    }

    public void testNodeWeights() {
        Huffman h = new Huffman("a");
        h.frequencyAnalysis();

        SimplePriorityQueue<Node> nodes = h.getSortedNodes();
        assertEquals(1, nodes.size());

        Node s = nodes.poll();
        assertEquals("a", s.getLabel());
        assertEquals(1, s.getWeight());

        h = new Huffman("abbcbba");
        h.frequencyAnalysis();

        SimplePriorityQueue<Node> freq = h.getSortedNodes();
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

    public void testTreeBuilding() { // TODO: bad test
        Huffman h = new Huffman("122");
        h.frequencyAnalysis();
        h.buildTree();
        BinaryTree tree = h.getTree();
        Node root = tree.getRoot();
        assertEquals("Should be in alphabetical order", "12", root.getLabel());
        assertEquals(3, root.getWeight());
    }

    public void testCodeAssignment() {
        Huffman h = new Huffman("122");
        h.frequencyAnalysis();
        h.buildTree();
        h.assignCodes();

        /*
                12
           0  /    \ 1
            1       2
         */
        assertEquals("0", h.getCodeFor("1"));
        assertEquals("1", h.getCodeFor("2"));

        /*
                214
           0  /     \ 1
             21     4
        10 /    \ 11
         1       2

         */
        h = new Huffman("1224444");
        h.frequencyAnalysis();
        h.buildTree();
        h.assignCodes();

        assertEquals("1", h.getCodeFor("4"));
        assertEquals("00", h.getCodeFor("1"));
        assertEquals("01", h.getCodeFor("2"));


        /*
                   1245
                0/     \1
               421      5
            00/   \01
          12        4
       000/  \001
        1     2
         */
        h = new Huffman("122444455555");
        h.frequencyAnalysis();
        h.buildTree();
        h.assignCodes();

        assertEquals("1", h.getCodeFor("5"));
        assertEquals("01", h.getCodeFor("4"));
        assertEquals("001", h.getCodeFor("2"));
        assertEquals("000", h.getCodeFor("1"));
        assertEquals("1245", h.getTree().getRoot().getLabel());

        h = new Huffman("a");
        h.frequencyAnalysis();
        h.buildTree();
        h.assignCodes();

        assertEquals("0", h.getCodeFor("a"));
    }

    public void testEncodeMessage() {
        Huffman h = new Huffman("abc.");
        BinaryTreeMap map = new BinaryTreeMap();
        map.put("a", "0");
        map.put("b", "10");
        map.put("c", "110");
        map.put(".", "111");
        h.setMap(map);
        h.encodeMessage();
        assertEquals("010110111", h.getEncodedMessage());

    }

    public void testChooseChildSides() {
        Node l = new Node("a", 0);
        Node r = new Node("b", 0);
        Node result = Huffman.chooseChildSides(l, r);
        Node resultLeft = result.getLeft();
        Node resultRight = result.getRight();
        assertEquals(l.getLabel(), resultLeft.getLabel());
        assertEquals(r.getLabel(), resultRight.getLabel());
    }

    public void testMapParsing() {
        Huffman encoder = new Huffman("abb");
        encoder.encode();
        BinaryTreeMap old = encoder.getMap();
        String encodedMap = encoder.getStringifiedMap();
        Huffman decoder = new Huffman();
        BinaryTreeMap decoded = decoder.parseMap(encodedMap);
        assertEquals(old, decoded);
    }

    public void testEncodeDecode() throws fi.jw.cs.tiralabra.IllegalHuffmanCodeException {
        final String message = "Hello world!";
        Huffman encoder = new Huffman();
        encoder.setMessage(message);
        encoder.encode();
        String map = encoder.getStringifiedMap();
        String encodedMessage = encoder.getEncodedMessage();

        Huffman decoder = new Huffman();
        decoder.setMap(decoder.parseMap(map));
        decoder.setEncodedMessage(encodedMessage);

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

        BinaryTreeMap m = h.parseMap(map);
        assertTrue(m.isEmpty());
    }

    public void testDecode() {
        BinaryTreeMap map = new BinaryTreeMap();
        map.put("a", "0");
        Huffman h = new Huffman();
        h.setMap(map);
        h.setEncodedMessage("a");

        try {
            h.decode();
            fail("Illegal movement a");
        } catch (IllegalArgumentException iae) {
//            is ok
        } catch (Exception e) {
            fail("Wrong exception");
        }

        h.setEncodedMessage("1");

        try {
            h.decode();
            fail("No element with key 1");
        } catch (java.util.NoSuchElementException nsee) {
            // is ok
        } catch (Exception e) {
            fail("Wrong exception. Was supposed to be No Such Element");
        }

        map.put("b", "2");
        h.setMap(map);
        try {
            h.decode();
            fail("2 Is not a valid Huffman code.");
        } catch (IllegalHuffmanCodeException illegal) {

        }
    }

}
