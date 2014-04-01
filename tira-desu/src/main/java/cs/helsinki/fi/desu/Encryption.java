package cs.helsinki.fi.desu;

/**
 * The class to encrypt given message.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class Encryption {
    
    private DES des;
    
    // three keys for triple DES, only K is used for single
    private byte[][] K;
    private byte[][] K1;
    private byte[][] K2;
    
    public Encryption() {
        this.des = new DES();
    }
    
    /**
     * Encrypts data by cycling through DES three times with three separate
     * keys.
     * 
     * @param  data data to be encrypted
     * @param  keys three keys to be used in each cycle of encryption
     * @return      encrypted data
     */
    public byte[] encryptTripleDES(byte[] data, byte[][] keys) {
        K = des.generateKeys(keys[0]);
        K1 = des.generateKeys(keys[1]);
        K2 = des.generateKeys(keys[2]);
        
        return null;
    }
    
    /**
     * Encrypts data by cycling through DES with supplied key.
     * 
     * @param data data to encrypt
     * @param key  key to use in encryption
     * @return     encrypted data
     */
    public byte[] encryptSingleDES(byte[] data, byte[] key) {
        K = des.generateKeys(key);
        
        return null;
    }
    
    /**
     * Inserts padding to an uncompleted block. If block is not
     * 64 bits inserts first a "1" and fills the rest with "0".
     * 
     * @param input block requiring padding
     * @return      padded block
     */
    public byte[] insertPadding(byte[] input) {
        return null;
    }
}
