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
        data = this.insertPadding(data);
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
                block[i % 8] = data[count % 8];
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
     * Pads a block under 64 bits to 64 with bytes equal in value to bytes required.
     * Implements standard PCKS#5.
     * 
     * @param block block requiring padding
     * @return      padded block
     */
    public byte[] insertPadding(byte[] data) {
        int padding = 8 - data.length - 1;
        byte[] temp = new byte[8];
        for (int i = 0; i < 8; i++) {
            // if data[i] exists use that
            if (i < padding - 1)
                temp[i] = data[i];
            // otherwise use the number of required bytes
            else
                temp[i] = padding;
        }
        return temp;
    }
}
