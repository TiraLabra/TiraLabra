/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

/**
 * Simple class that knows it's left and right node in the binary tree
 * @author robertvalta
 */
public class NodeWithFrequency extends Node {
    private Node left;
    private Node right;
    
    public NodeWithFrequency(int frequency, Node left, Node right) {
        super(frequency);
        this.left = left;
        this.right = right;
    }
    /**
     * 
     * @return returns left child
     */
    
    public Node getLeft() {
        return this.left;
    }
    
    /**
     * 
     * @return returns right child
     */
    public Node getRight() {
        return this.right;
    }
}
