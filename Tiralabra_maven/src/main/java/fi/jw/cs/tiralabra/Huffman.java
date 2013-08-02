package fi.jw.cs.tiralabra;

import java.util.*;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Huffman {

    private String message;
    private boolean[] encoded;
    Map<Character, List<Boolean>> map;
    SortedMap<Character, Symbol> frequency;


    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        this.map = new HashMap<Character, List<Boolean>>();
        this.frequency = new TreeMap<Character, Symbol>();
        this.encoded = new boolean[0];
    }

    public void encode() {
        calculateFrequencies();
        assignCodes();
        encodeMessage();
    }

    protected void calculateFrequencies() {
        char[] chars = this.message.toCharArray();
        for (Character c : chars) {
            Symbol s;
            if (frequency.containsKey(c)) {
                s = frequency.get(c);
            } else {
                s = new Symbol(c, 0);
            }

            s.increaseWeight();

            frequency.put(c, s);
        }
    }

    protected void assignCodes() {

    }

    protected void encodeMessage() {

    }


    ///////////// GET & SET ///////////////////

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean[] getEncoded() {
        return encoded;
    }

    public Map<Character, List<Boolean>> getMap() {
        return map;
    }

    public void setMap(Map<Character, List<Boolean>> map) {
        this.map = map;
    }
}
