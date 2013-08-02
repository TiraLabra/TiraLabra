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
    private BinaryTree tree;


    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        map = new HashMap<Character, List<Boolean>>();
        frequencies = new int[256]; // accepting 8-bit chars
        sortedNodes = new PriorityQueue<Node>();
        encoded = new boolean[0];
    }

    public void encode() {
        calculateFrequencies();
        buildTree();
        assignCodes();
        encodeMessage();
    }

    protected void calculateFrequencies() {
        char[] chars = message.toCharArray();
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
                    Node s = new Node("" + c, weight);
                    sortedNodes.add(s);
                }
            }
        }
    }

    protected void buildTree() {
        Node root;

        while (sortedNodes.size() >= 2) {
            Node left = sortedNodes.poll();
            Node right = sortedNodes.poll();
            String label = left.getLabel() + right.getLabel();
            int weight = left.getWeight() + right.getWeight(); //TODO: possible interger overflow error
            Node parent = new Node(label, weight, null, left, right);
            left.setParent(parent);
            right.setParent(parent);
            sortedNodes.add(parent);
        }

        root = sortedNodes.poll();
        tree = new BinaryTree(root);

    }

    protected void assignCodes() {

    }

    protected void encodeMessage() {

    }


    ///////////// GET & SET ///////////////////

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean[] getEncoded() {
        return Arrays.copyOf(encoded, encoded.length);
    }

    public Map<Character, List<Boolean>> getMap() {
        return new HashMap<Character, List<Boolean>>(map);
    }

    public void setMap(final Map<Character, List<Boolean>> map) {
        this.map = map;
    }

    public PriorityQueue<Node> getSortedNodes() {
        return new PriorityQueue<Node>(sortedNodes);
    }

    public BinaryTree getTree() {
        return tree;
    }
}
