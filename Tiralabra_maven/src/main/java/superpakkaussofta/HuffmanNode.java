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
     * @param b byte this node represents
     * @param freq byte's or child nodes' combined frequency
     */
    public HuffmanNode(byte b, int freq){
        this.b = b;
        this.freq = freq;
    }
    /** Creates a new HuffmanNode with given frequency and child nodes
     * 
     * @param left left child
     * @param right right child
     * @param freq byte's or child nodes' combined frequency
     */
    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.freq = left.getFreq() + right.getFreq();
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
     *  - have same frequency value
     *  - have equal child nodes
     * 
     * @param o as another Object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o){
        
        if(!(o instanceof HuffmanNode)){
            return false;
        }
        
        HuffmanNode node = (HuffmanNode) o;
        
        
        if(node.getLeft() == null && node.getRight() == null){
            if(node.getByte() != this.getByte() || node.getFreq() != this.getFreq()){
                return false;
            }
        }
        
        if(node.getLeft() != null){
            if(!node.getLeft().equals(this.getLeft())){
                return false;
            }
            if(!node.getRight().equals(this.getRight())){
                return false;
            }
        }
        return true;
    }
    /**
     * Constructs and returns a String that contains each leaf node's byte and
     * frequancy.
     * 
     * Each node is separated with character 'b' and
     * byte is separated from it's frequency with character 'a'.
     * 
     * @return 
     */
    @Override
    public String toString(){
        
        StringBuffer sb = new StringBuffer();
        reqToString(sb, this);
        
        return sb.toString();
    }
    /**
     * Used to recursively build String representation of HuffmanNode.
     * 
     * @param sb StringBuffer
     * @param n root HuffmanNode
     */
    public void reqToString(StringBuffer sb, HuffmanNode n){
        
        if(n.getLeft() == null){
            sb.append(n.getByte());
            sb.append("a");
            sb.append(n.getFreq());
            sb.append("b");
        }else{
            reqToString(sb, n.getLeft());
            reqToString(sb, n.getRight());
        }
    }
}
