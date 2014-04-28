package cs.helsinki.fi.desu;

/**
 * The class to decrypt a given message.
 *
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */
public class Decryption {

    private BitOperation bitOps;
    private Encryption enc;
    private DES des;

    // three keys for triple DES, only K is used for single
    private byte[][] K1;
    private byte[][] K2;
    private byte[][] K3;

    public Decryption() {
        this.des = new DES();
        this.bitOps = new BitOperation();
        this.enc = new Encryption();
    }

    /**
     * Decrypts data by cycling through three layers of DES with three separate
     * keys. Each block is first decrypted with key 1, encrypted with key 2 and
     * finally decrypted with key 3.
     *
     * @param data data to be decrypted
     * @param keys set of keys to use
     * @return     decrypted data
     */
    public byte[] decryptTripleDES(byte[] data, byte[][] keys) {
        int i;
        byte[] output = new byte[data.length];
        byte[] block = new byte[8];

        this.K1 = des.generateSubkeys(keys[0]);
        this.K2 = des.generateSubkeys(keys[1]);
        this.K3 = des.generateSubkeys(keys[2]);

        for (i = 0; i < data.length; i++) {
            if (i > 0 && i % 8 == 0) {
                block = decrypt64Block(block, K3);
                block = enc.encrypt64Block(block, K2);
                block = decrypt64Block(block, K1);
                System.arraycopy(block, 0, output, i - 8, block.length);
            }
            block[i % 8] = data[i];
        }
        
        block = decrypt64Block(block, K3);
        block = decrypt64Block(block, K2);
        block = decrypt64Block(block, K1);
        System.arraycopy(block, 0, output, i - 8, block.length);

        output = this.deletePadding(output);
        return output;
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
        K1 = des.generateSubkeys(key);
        int i;

        for (i = 0; i < data.length; i++) {
            if (i > 0 && i % 8 == 0) {
                block = decrypt64Block(block, K1);
                System.arraycopy(block, 0, output, i - 8, block.length);
            }
            block[i % 8] = data[i];
        }

        block = decrypt64Block(block, K1);
        System.arraycopy(block, 0, output, i - 8, block.length);
        output = deletePadding(output);

        return output;
    }

    /**
     * Decrypts a single block of 64 bytes and passes it back to calling method.
     *
     * @param block   block to be decrypted
     * @param subkeys set of subkeys to use
     * @return        decrypted block
     */
    public byte[] decrypt64Block(byte[] block, byte[][] subkeys) {
        byte[] temp = des.permute(block, des.getIP());
        byte[] L = bitOps.extractMultipleBits(temp, 0, des.getIP().length / 2);
        byte[] R = bitOps.extractMultipleBits(temp, des.getIP().length / 2, des.getIP().length / 2);

        for (int i = 0; i < 16; i++) {
            byte[] tempR = R;
            R = des.feistelF(R, subkeys[15 - i]);
            R = bitOps.xor(L, R);
            L = tempR;
        }

        temp = bitOps.concatBits(R, des.getIP().length / 2, L, des.getIP().length / 2);
        temp = des.permute(temp, des.getReverseIP());
        return temp;
    }

    /**
     * Removes padding from the last, incomplete block. Removes pad-bytes from
     * the end equal in value to the number of bytes to remove. Implements
     * PKCS#5.
     *
     * @param data block to be unpadded
     * @return     unpadded block
     */
    public byte[] deletePadding(byte[] data) {
        byte[] unpadded = new byte[data.length - data[7]];
        System.arraycopy(data, 0, unpadded, 0, unpadded.length);
        return unpadded;
    }
}
