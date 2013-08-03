package fi.jw.cs.tiralabra;

import java.util.*;

/**
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

    public void encode() {
        calculateFrequencies();
        buildTree();
        assignCodes();
        encodeMessage();
    }

    public void decode() {
        buildReverseTree();
        decodeMessage();
    }

    protected void calculateFrequencies() {
        char[] chars = message.toCharArray();
        if (chars.length > 0) {

            int uniques = 0;
            for (Character c : chars) {
                int key = (int) c.charValue();
                if (frequencies[key] == 0)
                    uniques++;

                frequencies[key]++;
            }


            sortedNodes = new PriorityQueue<Node>(uniques, Node.getComparator());
            for (int i = 0; i < frequencies.length; i++) {
                int weight = frequencies[i];
                if (weight > 0) {
                    Node s = new Node("" + (char) i, weight);
                    sortedNodes.add(s);
                }
            }
        }
    }

    /**
     * Building a Huffman tree where each node has a unique path and the least probable data is at the bottom
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
                boolean firstIsBigger = (n1.getWeight() >= n2.getWeight());
                Node left = firstIsBigger ? n1 : n2;
                Node right = firstIsBigger ? n2 : n1;
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
     * Given a map of key=>code pairs it will generate the Huffman tree that corresponds to it
     */
    protected void buildReverseTree() {
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
                    throw new IllegalArgumentException("Code must be either " + LEFT + " or " + RIGHT + ". But was: " + c);
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

            if (c == LEFT) {
                next = current.getLeft();
            } else if (c == RIGHT) {
                next = current.getRight();
            } else {
                throw new IllegalArgumentException("Code must be either " + LEFT + " or " + RIGHT + ". But was: " + c);
            }

            if (next != null) {
                current = next;
                if (current.isLeaf()) {
                    message += current.getLabel();
                    current = root;
                    currentPath = "";
                } else {
                    currentPath += c;
                    current.setLabel(currentPath);
                }
            } else {
                throw new NoSuchElementException();
            }

        }
    }

    public Map<String, String> parseMap(String serial) {
        String[] parts = serial.split("\\.");

        Map<String, String> m = new HashMap<String, String>();
        for (int i = 0; i < parts.length; i += 2) {
            String key = parts[i];
            String value = parts[i + 1];
            m.put(key, value);
        }

        return m;
    }

    public String encodeMap() {
        String encodedMap = "";
        String sep = ".";
        for (String key : map.keySet()) {
            String value = map.get(key);
            encodedMap += String.format("%03d", (int) (key.charAt(0))) + sep;
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
