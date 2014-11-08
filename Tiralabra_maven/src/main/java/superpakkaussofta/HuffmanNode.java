package superpakkaussofta;

/**
 * Node object for Huffman trees.
 * A node may have left and/or right nodes. If it doesn't,
 * it has a byte value to represent. A node also has a frequensy value
 * to tell how many times it's represented byte or it's child nodes are
 * found in data.
 *
 * @author Jouko
 */
public class HuffmanNode {
    
    //voinee laittaa finaleiksi
    private HuffmanNode left;
    private HuffmanNode right;
    private byte b;
    private int freq;
    
    /**
     * 
     * @param b as byte this node represents
     * @param freq as byte's or child nodes' combined frequency
     */
    public HuffmanNode(byte b, int freq){
        this.b = b;
        this.freq = freq;
    }
    /**
     * 
     * @param freq as byte's or child nodes' combined frequency
     */
    public HuffmanNode(int freq){
        this.freq = freq;
    }
    /**
     * @return the left
     */
    public HuffmanNode getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public HuffmanNode getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    /**
     * @return the b
     */
    public byte getByte() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setByte(byte b) {
        this.b = b;
    }

    /**
     * @return the freq
     */
    public int getFreq() {
        return freq;
    }

    /**
     * @param freq the freq to set
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }
    
}
