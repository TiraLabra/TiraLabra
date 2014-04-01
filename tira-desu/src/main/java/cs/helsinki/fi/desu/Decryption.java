
package cs.helsinki.fi.desu;

/**
 * The class to decrypt a given message.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class Decryption {
    
    private DES des;
    
    // three keys for triple DES, only K is used for single
    private byte[][] K;
    private byte[][] K1;
    private byte[][] K2;
    
    public Decryption() {
        this.des = new DES();
    }
    
    /**
     * Decrypts data by cycling through three layers of DES with three separate
     * keys.
     * 
     * @param data data to be decrypted
     * @param keys set of keys to use
     * @return     decrypted data
     */
    public byte[] decryptTripleDES(byte[] data, byte[][] keys) {
        K = des.generateKeys(keys[0]);
        K1 = des.generateKeys(keys[1]);
        K2 = des.generateKeys(keys[2]);
        
        return null;
    }

    /**
     * Decrypts given data by cycling it through DES with supplied key.
     * 
     * @param data data to decrypt
     * @param key  key to use in decryption
     * @return     decrypted data
     */
    public byte[] decryptSingleDES(byte[] data, byte[] key) {
        K = des.generateKeys(key);
        
        return null;
    }

    public byte[] decrypt64Block() {
        return null;
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
