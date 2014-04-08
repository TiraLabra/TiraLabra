
package cs.helsinki.fi.desu;

/**
 * The class to decrypt a given message.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class Decryption {
    
    private BitOperation bitOps;
    private DES des;
    
    // three keys for triple DES, only K is used for single
    private byte[][] K;
    private byte[][] K1;
    private byte[][] K2;
    
    public Decryption() {
        this.des = new DES();
        this.bitOps = new BitOperation();
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
        K = des.generateSubkeys(keys[0]);
        K1 = des.generateSubkeys(keys[1]);
        K2 = des.generateSubkeys(keys[2]);
        
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
        byte[] output = new byte[data.length];
        byte[] block = new byte[8];
        K = des.generateSubkeys(key);
        int i;

        for (i = 0; i < data.length; i++) {
            if (i > 0 && i % 8 == 0) {
                block = decrypt64Block(block, K);
                System.arraycopy(block, 0, output, i - 8, block.length);
            }
            if (i < data.length)
                block[i % 8] = data[i];
        }
        
        block = decrypt64Block(block, K);
        System.arraycopy(block, 0, output, i - 8, block.length);
        output = deletePadding(output);

        return output;
    }
    
    /**
     * Decrypts a single block of 64 bytes and passes it back to calling method.
     * 
     * @param  block   block to be decrypted
     * @param  subkeys set of subkeys to use
     * @return         decrypted block
     */
    public byte[] decrypt64Block(byte[] block, byte[][] subkeys) {
        byte[] temp = des.permute(block, des.getIP());
        byte[] L = bitOps.extractMultipleBits(temp, 0, des.getIP().length/2);
        byte[] R = bitOps.extractMultipleBits(temp, des.getIP().length/2, des.getIP().length/2);
 
        for (int i = 0; i < 16; i++) {
            byte[] tempR = R;
            R = des.feistelF(R, subkeys[15-i]);
            R = bitOps.xor(L, R);
            L = tempR;
        }
 
        temp = bitOps.concatBits(R, des.getIP().length/2, L, des.getIP().length/2);
        temp = des.permute(temp, des.getReverseIP());
        return temp;
    }
    
    /**
     * Removes padding from the last, incomplete block. If block is padded, method
     * removes padding by deleting each "0" and the first "1".
     * 
     * @param input block to be unpadded
     * @return      unpadded block
     */
    public byte[] deletePadding(byte[] input) {
        int count = 0;
        int i = input.length - 1;
        while (input[i] == 0) {
            count++;
            i--;
        }
        
        byte[] output = new byte[input.length - count - 1];
        System.arraycopy(input, 0, output, 0, output.length);
        return output;
    }
}
