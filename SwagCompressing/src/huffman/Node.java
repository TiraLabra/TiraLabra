/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

/**
 *
 * @author robertvalta
 */
public class Node {
    
    public int symbol;
    public Node left;
    public Node right;
    public Node parent;
    
    public Node(Node parent, int symbol) {
        this.symbol = symbol;
    }
    
    public Node(Node left, Node right, int symbol) {
        this.left = left;
        this.right = right;
        this.symbol = symbol;
    }
}
