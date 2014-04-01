
package cs.helsinki.fi.desu;

/**
 * Does all operations on bytes and bits, for example XOR and getting
 * needed bits from a byte.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class BitOperation {
    
    public BitOperation() {
        
    }
    
    /**
     * Inserts a bit value to a byte vector. Calculates which nit needs to
     * be set and to which value.
     * 
     * @param input input vector
     * @param pos   place to insert value
     * @param value value to set bit in pos to
     */
    
    public void setBit(byte[] input, int pos, int value) {
        int posByte = pos / 8;
        int posBit = pos % 8;
        byte temp = input[posByte];
        temp = (byte) (((0xFF7F >> posBit) & temp) & 0x00FF);
        byte newByte = (byte) ((value << (8 - (posBit + 1))) | temp);
        input[posByte] = newByte;
    }
    
    /**
     * Takes a vector and extracts one bit from given position.
     * 
     * @param  input byte to extract bit from
     * @param  pos   where to extract bit from
     * @return       the bit from pos
     */
    public int extractBit(byte[] input, int pos) {
        int posByte = pos / 8;
        int posBit = pos % 8;
        byte temp = input[posByte];
        int bit = temp >> (8 - (posBit + 1)) & 0x0001;
        return bit;
    }
    
    /**
     * Takes a vector of bytes and extracts bits from it from starting position
     * to end position.
     *
     * @param  input vector of bytes
     * @param  pos   starting position
     * @param  n     number of bits to extract
     * @return       extracted vector of bytes
     **/
    public byte[] extractMultipleBits(byte[] input, int pos, int n) {
        int numBytes = (n - 1) / 8 + 1;
        byte[] output = new byte[numBytes];
        for (int i = 0; i < n; i++) {
            int val = extractBit(input, pos + i);
            setBit(output, i, val);
        }
        return output; 
    }
    
    /**
     * Rotates bits to the left from given input vector by given number of
     * positions. Variable len allows for a single byte from a byte array to
     * be rotated.
     *
     * @param  input input byte vector
     * @param  len   length of bits to rotate
     * @param  steps number of times to rotate
     * @return       new byte vector with bits rotated
     */
    public byte[] rotateLeft(byte[] input, int len, int steps) {
        int numBytes = (len - 1) / 8 + 1;
        byte[] output = new byte[numBytes];
        for (int i = 0; i < len; i++) {
            int val = extractBit(input, (i + steps) % len);
            setBit(output, i, val);
        }
        return output;
    }
    
    /**
     * Apply XOR to two sets of bytes and return the resulting byte[].
     *
     * @param  a first vector
     * @param  b second vector
     * @return   result vector from XOR-operation
     */
    public byte[] xor(byte[] a, byte[] b) {
        byte[] output = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            output[i] = (byte) (a[i] ^ b[i]);
        }
        return output;
    }
    
    /**
     * Separates blocks to chunks of required size.
     *
     * @param  input input vector of bytes
     * @param  len   length of block to get
     * @return       vector of bits of given length
     */
    public byte[] separateBytes(byte[] input, int len) {
        int numBytes = (8 * input.length - 1) / len + 1;
        byte[] output = new byte[numBytes];
        for (int i = 0; i < numBytes; i++) {
            for (int j = 0; j < len; j++) {
                int val = extractBit(input, len * i + j);
                setBit(output, 8 * i + j, val);
            }
        }
        return output;
    }
    
    /**
     * Concatenates a byte vector from two given bytes and a number of bits
     * two extract from each.
     * 
     * @param  a    first byte
     * @param  lenA length of bits to extract from a
     * @param  b    second byte
     * @param  lenB length of bits to extract from b
     * @return      resulting byte
     */
    public byte[] concatBits(byte[] a, int lenA, byte[] b, int lenB) {
        int numBytes = (lenA + lenB - 1) / 8 + 1;
        byte[] out = new byte[numBytes];
        int j = 0;
        for (int i = 0; i < lenA; i++) {
            int val = extractBit(a, i);
            setBit(out, j, val);
            j++;
        }
        for (int i = 0; i < lenB; i++) {
            int val = extractBit(b, i);
            setBit(out, j, val);
            j++;
        }
        return out;
    }
}
