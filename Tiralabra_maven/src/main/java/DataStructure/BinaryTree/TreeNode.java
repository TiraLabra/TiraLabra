package DataStructure.BinaryTree;

/**
 * This class is an implemantation of a node for a binary tree
 */
public class TreeNode {

    int symbol;
    int weight;
    TreeNode parent;
    TreeNode leftChild;
    TreeNode rightChild;

    /**
     * A node has two values: 
     * int symbol, representing a byte
     * int weight, the value of the symbol in question (rare symbols
     * are more valuable)
     */
    public TreeNode(int symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    /**
     * Returns the value of symbol
     */
    public int getSymbol() {
        return symbol;
    }

    /**
     * Returns the value of weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the parent of the node
     */
    public void setParent(TreeNode p) {

        parent = p;
    }

    /**
     * Sets the left child of the node
     */
    public void setLeftChild(TreeNode lc) {

        leftChild = lc;
    }

    /**
     * Sets the right child of the node
     */
    public void setRightChild(TreeNode rc) {

        rightChild = rc;
    }

    /**
     * Returns the parent of the node
     */
    public TreeNode getParent() {

        return parent;
    }

    /**
     * Returns the left child of the node
     */
    public TreeNode getLeftChild() {

        return leftChild;
    }

    /**
     * Returns the right child of the node
     */
    public TreeNode getRightChild() {

        return rightChild;
    }
}
