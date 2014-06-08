package lib.io;

import lib.utils.ByteAsBits;
import java.io.IOException;
import java.util.LinkedList;

/**Ulostulopuskuri, joka kirjoittaa bittivirran tavuittain tiedostoon.   
 * @author Iiro
 */
public class OutputBuffer {
    private final LinkedList<Boolean> list;
    private final IO io;
    
    public OutputBuffer(IO io){
        list = new LinkedList<Boolean>();
        this.io = io;
    }
    
    /**
     * Kirjoittaa bittivirrasta tavuittain tiedoostoon, kunnes virran pituus olla tavun.  
     * @throws IOException 
     */
    public void write() throws IOException{
        while(list.size() >= 8){
            boolean[] bits = new boolean[8];
            for(int i = 0; i < 8; i++){
                bits[i] = list.poll();
            }
            ByteAsBits b = new ByteAsBits(bits);
            io.write(b);
        }
    }
    /**
     * Lisää tavun bittivirtaan.
     * @param b lisättävä tavu
     */
    public void addByte(byte b){
        ByteAsBits bits = new ByteAsBits(b);
        for(boolean bit: bits.getAllBits()){
            list.add(bit);
        }
    }
    /**
     * Lisää bitin bittivirtaan. 
     * @param bit lisättävä bitti. True = 1, False = 0;
     */
    public void addBits(boolean[] bits){
        for(boolean b: bits){
            list.add(b);
        }
    }
    
    public void addBit(boolean bit){
        list.add(bit);
    }
    
    /**
    * Kirjoittaa "ylijäämäbitit" tiedostoon. Tavu täydennetään nollilla.
    * @throws IOException 
    */
    public void finalWrite() throws IOException{
        while(list.size()<8){
            list.add(false);
        }
        write();
    }
}
