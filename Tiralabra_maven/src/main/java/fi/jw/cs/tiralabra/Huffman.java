package fi.jw.cs.tiralabra;

import java.util.*;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Huffman {

    private String message;
    private boolean[] encoded;
    private Map<Character, List<Boolean>> map;
    private int[] frequencies;
    private SortedSet<Symbol> sortedSymbols;


    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        this.map = new HashMap<Character, List<Boolean>>();
        this.frequencies = new int[256]; // accepting 8-bit chars
        this.encoded = new boolean[0];
    }

    public void encode() {
        calculateFrequencies();
        assignCodes();
        encodeMessage();
    }

    protected void calculateFrequencies() {
        char[] chars = this.message.toCharArray();
        SortedSet<Character> uniques = new TreeSet<Character>();

        for (Character c : chars) {
            frequencies[(int) c.charValue()]++;

            uniques.add(c);
        }

        sortedSymbols = new TreeSet<Symbol>(Symbol.getComparator());
        for (Character c : uniques) {
            int weight = frequencies[(int) c];
            if (weight > 0) {
                Symbol s = new Symbol(c, weight);
                sortedSymbols.add(s);
            }
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

    public SortedSet<Symbol> getSortedSymbols() {
        return sortedSymbols;
    }
}
