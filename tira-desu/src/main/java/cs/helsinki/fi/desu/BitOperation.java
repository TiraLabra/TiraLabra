
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
     * @param  input  input vector
     * @param  pos    place to insert value
     */
    public void setBit(byte[] input, int pos) {
        int posByte = pos / 8;
        int posBit = pos % 8;
        byte temp = input[posByte];
        temp = (byte) (((0xFF7F >> posBit) & temp) & 0x00FF);
        byte newByte = (byte) ((val << (8 - (posBit + 1))) | temp);
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
     * @param  input  vector of bytes
     * @param  pos    starting position
     * @param  n      number of bits to extract
     * @return        extracted vector of bytes
     **/
    public byte[] extractMultipleBits(byte[] input, int pos, int n) {
        int numBytes = (n - 1) / 8 + 1;
        byte[] output = new byte[numBytes];
        for (int i = 0; i < n; i++) {
            int val = extractBit(input, pos + i);
            setBit(out, i, val);
        }
        return out; 
    }
    
    /**
     * Rotates bits to the left from given input vector by given number of
     * positions.
     *
     * @param   input  input byte vector
     * @param   len    length of bits to rotate
     * @param   pos    position from where to rotate
     * @return         new byte vector with bits rotated
     */
    public byte[] rotateLeft(byte[] input, int len, int pos) {
        int nrBytes = (len - 1) / 8 + 1;
        byte[] out = new byte[nrBytes];
        for (int i = 0; i < len; i++) {
            int val = extractBit(input, (i + pas) % len);
            setBit(out, i, val);
        }
        return out;
    }
    
    /**
     * Apply XOR to two sets of bytes and return the resulting byte[].
     *
     * @param  a first voctor
     * @param  b second vector
     * @return   result vector from XOR-operation
     */
    public byte[] xor(byte[] a, byte[] b) {
        byte[] output = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ b[i]);
        }
        return output;
    }
    
    /**
     * Separates blocks to chunks of required size.
     *
     * @param  input  input vector of bytes
     * @param  len    length of block to get
     * @return        vector of bits of given length
     */
    private static byte[] separateBytes(byte[] input, int len) {
        int numBytes = (8 * in.length - 1) / len + 1;
        byte[] output = new byte[numBytes];
        for (int i = 0; i < numBytes; i++) {
            for (int j = 0; j < len; j++) {
                int val = extractBit(input, len * i + j);
                setBit(output, 8 * i + j, val);
            }
        }
        return output;
    }
}
