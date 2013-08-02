package fi.jw.cs.tiralabra;

import java.util.*;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Huffman {

    private String message;
    private boolean[] encoded;
    private Map<String, String> map;
    private int[] frequencies;
    private PriorityQueue<Node> sortedNodes;
    private BinaryTree tree;


    public Huffman() {
        this("");
    }

    public Huffman(String message) {
        this.message = message;
        map = new HashMap<String, String>();
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

        if (sortedNodes.size() == 1) {
            Node onlyChild = sortedNodes.poll();
            root = new Node(onlyChild.getLabel(), onlyChild.getWeight(), null, onlyChild, null);
            onlyChild.setParent(root);
        } else {
            while (sortedNodes.size() >= 2) {
                Node n1 = sortedNodes.poll();
                Node n2 = sortedNodes.poll();
                boolean firstIsBigger = (n1.getWeight() >= n2.getWeight());
                Node left = firstIsBigger ? n1 : n2;
                Node right = firstIsBigger ? n2 : n1;
                String label = left.getLabel() + right.getLabel();
                int weight = left.getWeight() + right.getWeight(); //TODO: possible interger overflow error
                Node parent = new Node(label, weight, null, left, right);
                left.setParent(parent);
                right.setParent(parent);
                sortedNodes.add(parent);
            }

            root = sortedNodes.poll();
        }
        tree = new BinaryTree(root);

    }

    protected void assignCodes() {
        if (tree != null && tree.getRoot() != null) {
            assignRecursive(tree.getRoot(), "");
        }
    }

    protected String getCodeFor(String symbol) {
        return map.get(symbol);
    }

    private void assignRecursive(Node node, String currentCode) {
        Node left = node.getLeft();
        Node right = node.getRight();

        if (left == null && right == null) {
            map.put(node.getLabel(), currentCode);
        } else {

            if (left != null)
                assignRecursive(left, currentCode + "0");

            if (right != null)
                assignRecursive(right, currentCode + "1");
        }


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

    public Map<String, String> getMap() {
        return new HashMap<String, String>(map);
    }

    public void setMap(final Map<String, String> map) {
        this.map = map;
    }

    public PriorityQueue<Node> getSortedNodes() {
        return new PriorityQueue<Node>(sortedNodes);
    }

    public BinaryTree getTree() {
        return tree;
    }
}
