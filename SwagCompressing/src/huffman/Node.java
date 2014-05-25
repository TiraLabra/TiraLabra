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
public abstract class Node {
    private int frequency;
    
    public Node(int frequency) {
        this.frequency = frequency;
    }
    
    public int getFrequency() {
        return this.frequency;
    }
}