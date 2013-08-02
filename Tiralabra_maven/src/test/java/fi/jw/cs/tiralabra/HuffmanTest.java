package fi.jw.cs.tiralabra;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.SortedSet;

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
        SortedSet<Symbol> freq = h.getSortedSymbols();
        assertEquals(freq.size(), 0);

        h.setMessage("a");
        h.calculateFrequencies();
        freq = h.getSortedSymbols();
        assertEquals(1, freq.size());
        Symbol s = freq.first();

        assertEquals('a', s.getCharacter());
        assertEquals(1, s.getWeight());
    }

    public void testMultipleFrequencies() {
        Huffman h = new Huffman("abbcbba");
        h.calculateFrequencies();
        SortedSet<Symbol> freq = h.getSortedSymbols();

        assertEquals('c', freq.first().getCharacter());
        assertEquals(1, freq.first().getWeight());

        assertEquals('b', freq.last().getCharacter());
        assertEquals(4, freq.last().getWeight());
    }
}
