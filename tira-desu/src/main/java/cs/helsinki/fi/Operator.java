package cs.helsinki.fi;

import cs.helsinki.fi.desu.DES;
import cs.helsinki.fi.desu.Decryption;
import cs.helsinki.fi.desu.Encryption;

/**
 * Container and logic class for the program. Handles everything from chopping
 * data into blocks to passing it to encryption and decryption.
 *
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */
public class Operator {

    private DES des;
    private Encryption enc;
    private Decryption dec;
    
    // three keys for triple DES, only K is used for single
    private byte[][] K;
    private byte[][] K1;
    private byte[][] K2;

    public Operator() {
        this.des = new DES();
        this.enc = new Encryption(des);
        this.dec = new Decryption(des);
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
    public byte[] encrypt(byte[] data, byte[] key) {
        K = des.generateKeys(key);
        
        return null;
    }

    public byte[] encrypt64Block() {
        return null;
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
    public byte[] decrypt(byte[] data, byte[] key) {
        K = des.generateKeys(key);
        
        return null;
    }

    public byte[] decrypt64Block() {
        return null;
    }
}
