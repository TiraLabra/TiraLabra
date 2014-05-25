package huffman;

/**
 * @author alpa
 *
 * A node used in building a Huffman tree
 */
public class Node {

    private final int chara;
    private int freq;
    private Node leftChild;
    private Node rightChild;

    /**
     * 
     * @param chara byte representation of the symbol
     * @param freq the weight of the symbol
     */
    public Node(int chara, int freq) {
        this.chara = chara;
        this.freq = freq;
    }

    /**
     * Increments the weight of the node by one
     */
    public void add() {
        this.freq++;
    }

    /**
     *
     * @return the symbol this node represents
     */
    public int getChara() {
        return this.chara;
    }

    /**
     *
     * @return the weight of the symbol
     */
    public int getFreq() {
        return this.freq;
    }

    /**
     *
     * @param n sets the right child as the parameter
     */
    public void setRChild(Node n) {
        rightChild = n;
    }

    /**
     *
     * @param n sets the left child as the parameter
     */
    public void setLChild(Node n) {
        leftChild = n;
    }

    /**
     *
     * @return the right child of the node
     */
    public Node getRChild() {
        return rightChild;
    }

    /**
     *
     * @return the left child of the node
     */
    public Node getLChild() {
        return leftChild;
    }

    /**
     *
     * @return true if node is the root node
     */
    public boolean isRoot() {
        return false;
    }
}
