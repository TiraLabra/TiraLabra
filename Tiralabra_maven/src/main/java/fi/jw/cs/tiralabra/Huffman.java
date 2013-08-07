package fi.jw.cs.tiralabra;

import java.util.*;

/**
 * This class provides the utility of encoding and decoding a 8-bit text into a compressed Huffman encoding.
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-01
 */

public class Huffman {

    static final char LEFT = '0';
    static final char RIGHT = '1';

    private String message;
    private String encodedMessage;
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
    }


    /**
     * The main function to call when encoding.
     */
    public void encode() {
        if (message.length() == 0)
            throw new IllegalArgumentException("No message provided");

        frequencyAnalysis();
        buildTree();
        assignCodes();
        encodeMessage();
    }

    protected void frequencyAnalysis() {
        calculateFrequencies();
        createdWeightedNodes();
    }

    /**
     * The main function to call when decoding a message
     */
    public void decode() throws fi.jw.cs.tiralabra.IllegalHuffmanCodeException {
        buildReverseTree();
        decodeMessage();
    }

    private void calculateFrequencies() {
        frequencies = new int[256];

        char[] chars = message.toCharArray();
        if (chars.length > 0) {

            for (Character c : chars) {
                frequencies[(int) c.charValue()]++;
            }
        }
    }

    private void createdWeightedNodes() {
        sortedNodes = new PriorityQueue<Node>(1, Node.getComparator());
        for (int i = 0; i < frequencies.length; i++) {
            int weight = frequencies[i];
            if (weight > 0) {
                Node s = new Node("" + (char) i, weight);
                sortedNodes.add(s);
            }
        }
    }

    /**
     * Building a Huffman tree where each node has a unique path and the least probable data is at the bottom
     * <p/>
     * The left/right divide is decided by the label value.
     */
    protected void buildTree() {
        Node root;
        PriorityQueue<Node> nodes = new PriorityQueue<Node>(sortedNodes);

        if (nodes.size() == 1) {
            Node onlyChild = nodes.poll();
            root = new Node(onlyChild.getLabel(), onlyChild.getWeight(), null, onlyChild, null);
            onlyChild.setParent(root);
        } else {
            while (nodes.size() >= 2) {
                Node n1 = nodes.poll();
                Node n2 = nodes.poll();

                Node placeholder = chooseChildSides(n1, n2);
                Node left = placeholder.getLeft();
                Node right = placeholder.getRight();

                String label = left.getLabel() + right.getLabel();
                int weight = left.getWeight() + right.getWeight(); //TODO: possible interger overflow error
                Node parent = new Node(label, weight, null, left, right);

                left.setParent(parent);
                right.setParent(parent);

                nodes.add(parent);
            }

            root = nodes.poll();
        }
        tree = new BinaryTree(root);

    }

    /**
     * Assigns the nodes based on their labels so that we can build the tree in a deterministic fashion always
     *
     * @param n1 First <code>Node</code> to compare
     * @param n2 Second <code>Node</code> to compare
     * @return Returns a temporary Node whose children have been set in the correct order
     */
    protected static Node chooseChildSides(Node n1, Node n2) {
        Node parent = new Node();
        if (n1.getLabel().compareTo(n2.getLabel()) <= 0) {
            parent.setLeft(n1);
            parent.setRight(n2);
        } else {
            parent.setLeft(n2);
            parent.setRight(n1);
        }

        return parent;
    }

    /**
     * Given a map of key=>code pairs it will generate the Huffman tree that corresponds to it
     */
    protected void buildReverseTree() throws fi.jw.cs.tiralabra.IllegalHuffmanCodeException {
        Node root = new Node("root", 0);
        Node current = root;
        for (String key : map.keySet()) {
            String value = map.get(key);
            for (char c : value.toCharArray()) {
                if (c == LEFT) {
                    Node left = current.getLeft();
                    if (left != null) {
                        current = left;
                    } else {
                        left = new Node("", 0, current, null, null);
                        current.setLeft(left);
                        current = left;
                    }
                } else if (c == RIGHT) {
                    Node right = current.getRight();
                    if (right != null) {
                        current = right;
                    } else {
                        right = new Node("", 0, current, null, null);
                        current.setRight(right);
                        current = right;
                    }
                } else {
                    throw new IllegalHuffmanCodeException("Code must be either " + LEFT + " or " + RIGHT + ". But was: " + c);
                }
            }
            current.setLabel(key);
            current = root;
        }
        tree = new BinaryTree(root);
    }

    protected void assignCodes() {
        if (tree != null && tree.getRoot() != null) {
            assignRecursive(tree.getRoot(), "");
        }
    }


    private void assignRecursive(Node node, String currentCode) {
        Node left = node.getLeft();
        Node right = node.getRight();

        if (left == null && right == null) {
            map.put(node.getLabel(), currentCode);
        } else {

            if (left != null)
                assignRecursive(left, currentCode + LEFT);

            if (right != null)
                assignRecursive(right, currentCode + RIGHT);
        }
    }

    protected String getCodeFor(String symbol) {
        return map.get(symbol);
    }

    protected void encodeMessage() {
        encodedMessage = "";
        for (char c : message.toCharArray()) {
            encodedMessage += getCodeFor("" + c);
        }
    }

    protected void decodeMessage() {
        message = "";
        Node root = tree.getRoot();
        Node current = root;
        Node next;
        String currentPath = "";
        for (char c : encodedMessage.toCharArray()) {

            switch (c) {
                case LEFT:
                    next = current.getLeft();
                    break;
                case RIGHT:
                    next = current.getRight();
                    break;
                default:
                    throw new IllegalArgumentException("Code must be either " + LEFT + " or " + RIGHT + ". But was: " + c);
            }

            if (next == null) {
                throw new NoSuchElementException();
            }

            current = next;

            if (current.isLeaf()) {
                message += current.getLabel();
                current = root;
                currentPath = "";
            } else {
                currentPath += c;
                current.setLabel(currentPath);
            }
        }
    }


    public Map<String, String> parseMap(String serial) {
        String[] parts = serial.split("__");
        Map<String, String> m = new HashMap<String, String>();

        if (parts.length < 2) {
            return m;
        }

        for (int i = 0; i < parts.length; i += 2) {
            String key = parts[i];
            String value = parts[i + 1];
            m.put(key, value);
        }

        return m;
    }

    public String encodeMap() {
        String encodedMap = "";
        String sep = "__";
        for (String key : map.keySet()) {
            String value = map.get(key);
            encodedMap += key + sep;
            encodedMap += value + sep;
        }

        return encodedMap;
    }

    ///////////// GET & SET ///////////////////

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public void setEncodedMessage(String encodedMessage) {
        this.encodedMessage = encodedMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
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
