package cs.helsinki.fi.desu;

/**
 * The class to encrypt given message.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class Encryption {
    
    private DES des;
    private BitOperation bitOps;
    private Decryption dec;
    
    // three keys for triple DES, only K is used for single
    private byte[][] K1;
    private byte[][] K2;
    private byte[][] K3;
    
    public Encryption() {
        this.des = new DES();
        this.bitOps = new BitOperation();
        this.dec = new Decryption();
    }
    
    /**
     * Encrypts data by cycling through DES three times with three separate
     * keys. Each block is first encrypted with key 1, decrypted with key 2 and
     * finally encrypted with key 3.
     * 
     * @param  data data to be encrypted
     * @param  keys three keys to be used in each cycle of encryption
     * @return      encrypted data
     */
    public byte[] encryptTripleDES(byte[] data, byte[][] keys) {
        int length = 8 - data.length % 8;
        byte[] output = insertPadding(data);
        byte[] block = new byte[8];
        int i = 0;

        this.K1 = des.generateSubkeys(keys[0]);
        this.K2 = des.generateSubkeys(keys[1]);
        this.K3 = des.generateSubkeys(keys[2]);
		
        for (i = 0; i < data.length + length; i++) {
            if (i > 0 && i % 8 == 0) {
                block = encrypt64Block(block, this.K1);
                block = dec.decrypt64Block(block, this.K2);
                block = encrypt64Block(block, this.K3);
                System.arraycopy(block, 0, output, i - 8, block.length);
            }
            block[i % 8] = data[i];
        }
        
        return output;
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
        byte[] output = this.insertPadding(data);
        byte[] block = new byte[8];
        K1 = des.generateSubkeys(key);
        int i;

        for (i = 0; i < data.length; i++) {
            if (i > 0 && i % 8 == 0) {
                block = encrypt64Block(block, K1);
                System.arraycopy(block, 0, output, i - 8, block.length);
            }
            block[i % 8] = data[i];
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
     * Pads the byte array of data to be encrypted. If all blocks are full, inserts
     * a block of 0x08, otherwise 0x0n, where n is the number of empty bytes in
     * last block. Implements standard PCKS#5.
     * 
     * @param data data to be padded
     * @return     padded data
     */
    public byte[] insertPadding(byte[] data) {
        int count = (byte) (8 - data.length % 8);
        byte[] temp = new byte[data.length + count];
        
        System.arraycopy(data, 0, temp, 0, data.length);
        for (int i = data.length; i < temp.length; i++)
            temp[i] = (byte) count;
        
        return temp;
    }
}
