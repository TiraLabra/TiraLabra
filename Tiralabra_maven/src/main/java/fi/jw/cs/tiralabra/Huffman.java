package fi.jw.cs.tiralabra;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Huffman {

    public String message;
    public boolean[] encoded;
    Map<Character, Integer> map;
    Map<Character, Integer> frequency;

    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        this.map = new HashMap<Character, Integer>();
    }

    public void encode() {
        encoded = new boolean[message.length()];
        if (encoded.length > 0) {
            // do processing
        }
    }
}
