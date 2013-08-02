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
    private PriorityQueue<Node> sortedNodes;


    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        this.map = new HashMap<Character, List<Boolean>>();
        this.frequencies = new int[256]; // accepting 8-bit chars
        this.sortedNodes = new PriorityQueue<Node>();
        this.encoded = new boolean[0];
    }

    public void encode() {
        calculateFrequencies();
        buildTree();
        assignCodes();
        encodeMessage();
    }

    protected void calculateFrequencies() {
        char[] chars = this.message.toCharArray();
        if (chars.length > 0) {
            SortedSet<Character> uniques = new TreeSet<Character>();

            for (Character c : chars) {
                frequencies[(int) c.charValue()]++;

                uniques.add(c);
            }


            sortedNodes = new PriorityQueue<Node>(uniques.size(), Node.getComparator());
            for (Character c : uniques) {
                int weight = frequencies[(int) c];
                if (weight > 0) {
                    Node s = new Node(c, weight);
                    sortedNodes.add(s);
                }
            }
        }
    }

    protected void buildTree() {

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

    public PriorityQueue<Node> getSortedNodes() {
        return sortedNodes;
    }
}
