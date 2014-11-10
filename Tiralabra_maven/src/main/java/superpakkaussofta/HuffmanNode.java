package superpakkaussofta;

/**
 * Node object for Huffman trees.
 * A node may have left and/or right nodes. If it doesn't,
 * it has a byte value to represent. A node also has a frequency value
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
    
    /** Creates a new HuffmanNode for given byte with given frequency
     * 
     * @param b as byte this node represents
     * @param freq as byte's or child nodes' combined frequency
     */
    public HuffmanNode(byte b, int freq){
        this.b = b;
        this.freq = freq;
    }
    /** Creates a new HuffmanNode with given frequency and child nodes
     * 
     * @param left left child
     * @param right right child
     * @param freq as byte's or child nodes' combined frequency
     */
    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right){
        this.freq = freq;
        this.left = left;
        this.right = right;
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
    /**
     * Node is equal to another node if they both:
     *  - represent the same byte
     *  - have same frequensy value
     *  - have equal child nodes
     * 
     * @param o as another Object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o){
        HuffmanNode node = null;
        
        try {
            node = (HuffmanNode) o;
        } catch (Exception e) {
            return false;
        }
        if(node == null){
            return false;
        }
        
        if(node.getLeft() == null && node.getRight() != null){
            if(node.getByte() != b || node.getFreq() != freq){
                return false;
            }
        }
        
        if(node.getLeft() != null){
            if(!node.getLeft().equals(left)){
                return false;
            }
        }else{
            if(left == null){
                return false;
            }
        }
        
        if(node.getRight() != null){
            if(!node.getRight().equals(right)){
                return false;
            }
        }else{
            if(right == null){
                return false;
            }
        }
        return true;
    }
    
}
