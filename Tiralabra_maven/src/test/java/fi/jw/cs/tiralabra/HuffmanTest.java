package fi.jw.cs.tiralabra;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */
public class HuffmanTest extends TestCase {

    public void testEncodeWithEmptyStringReturnsEmptyArray() throws Exception {
        Huffman h = new Huffman();
        h.encode();
        boolean[] expected = new boolean[0];
        assertTrue(Arrays.equals(h.encoded, expected));
    }
}
