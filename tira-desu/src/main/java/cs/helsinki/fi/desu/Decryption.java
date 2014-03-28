
package cs.helsinki.fi.desu;

/**
 * The class to decrypt a given message.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class Decryption {
    
    public Decryption() {
        
    }
    
    /**
     * Removes padding from the last block. If block is padded, method
     * removes padding by deleting each "0" and the first "1".
     * 
     * @param input block to be unpadded
     * @return      unpadded block
     */
    public byte[] deletePadding(byte[] input) {
        return null;
    }
}
