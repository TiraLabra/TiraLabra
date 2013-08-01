package fi.jw.cs.tiralabra;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  Jan Wikholm <jw@jw.fi>
 * @since   2013-08-01
 */

public class Huffman {
    String      message;
    boolean[]   encoded;
    Map<Character, Integer> map;

    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        this.map = new HashMap<Character, Integer>();
    }
}
