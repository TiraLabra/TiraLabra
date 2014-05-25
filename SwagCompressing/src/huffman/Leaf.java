/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

/**
 * Binary tree's leaf that has a symbol and a frequency
 * @author robertvalta
 */
public class Leaf extends Node{
    
    private int symbol;

    public Leaf(int frequency, int symbol) {
        super(frequency);
        this.symbol = symbol;
    }
    
    /**
     * 
     * @return returns the symbol of the leaf
     */
    public int getSymbol() {
        return this.symbol;
    }
}
