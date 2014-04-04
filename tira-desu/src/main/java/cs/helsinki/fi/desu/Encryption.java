package cs.helsinki.fi.desu;

/**
 * The class to encrypt given message.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class Encryption {
    
    private DES des;
    private BitOperation bitOps;
    
    // three keys for triple DES, only K is used for single
    private byte[][] K;
    private byte[][] K1;
    private byte[][] K2;
    
    public Encryption() {
        this.des = new DES();
        this.bitOps = new BitOperation();
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
        K = des.generateSubkeys(keys[0]);
        K1 = des.generateSubkeys(keys[1]);
        K2 = des.generateSubkeys(keys[2]);
        
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
        int length = 8 - data.length % 8;
        byte[] output = new byte[data.length + length];
        byte[] block = new byte[8];
        byte[] padding = insertPadding(length);
        K = des.generateSubkeys(key);
        int count = 0;
        int i;

        for (i = 0; i < data.length + length; i++) {
            if (i > 0 && i % 8 == 0) {
                block = encrypt64Block(block,K);
                System.arraycopy(block, 0, output, i - 8, block.length);
            }
            if (i < data.length)
                block[i % 8] = data[i];
            else {														
                block[i % 8] = padding[count % 8];
                count++;
            }
        }
        if (block.length == 8){
            block = encrypt64Block(block,K);
            System.arraycopy(block, 0, output, i - 8, block.length);
        }
        return output;
    }
    
    /**
     * Encrypts a single block of 64 bytes and passes it back to calling method.
     * 
     * @param  block   block to be encrypted
     * @param  subkeys set of subkeys to use
     * @return         encrypted block
     */
    public byte[] encrypt64Block(byte[] block, byte[][] subkeys) {
        byte[] temp = des.permute(block, des.getIP());
        byte[] L = bitOps.extractMultipleBits(temp, 0, des.getIP().length/2);
        byte[] R = bitOps.extractMultipleBits(temp, des.getIP().length/2, des.getIP().length/2);
 
        for (int i = 0; i < 16; i++) {
            byte[] tempR = R;
            R = des.feistelF(R,subkeys[i]);
            R = bitOps.xor(L, R);
            L = tempR;
        }
 
        temp = bitOps.concatBits(R, des.getIP().length/2, L, des.getIP().length/2);
        temp = des.permute(temp, des.getReverseIP());
        return temp;
    }
    
    /**
     * Creates a padding byte array to fill an incomplete block. First byte is "-128"
     * followed by necessary number of "0".
     * 
     * @param  length length of required padding
     * @return        padded block
     */
    public byte[] insertPadding(int length) {
        byte[] padding = new byte[length];
        padding[0] = -128;
        for (int i = 0; i < length; i++)
            padding[i] = 0;
        return padding;
    }
}
