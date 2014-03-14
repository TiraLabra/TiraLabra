/**
 * Does all operations on bytes and bits, for example XOR and getting needed bits from a byte.
 */
package cs.helsinki.fi.desu;

/**
 * @author Juha Lindqvist
 * @email "juha.lindqvist@cs.helsinki.fi"
 */

public class BitOperation {
    
    public BitOperation() {
        
    }
    
    public void setBit() {
        
    }
    
    public int extractBit() {
        return 0;
    }
    
    public byte[] extractMultipleBits() {
       return null; 
    }
    
    public byte[] rotateLeft() {
        return null;
    }
    
    /**
     * Apply XOR to two sets of bytes and return the resulting byte[].
     * 
     * @return byte[] resulting from XOR-ing byte[] a and byte[] b
     */
    public byte[] xorBytes(byte[] a, byte[] b) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ b[i]); // xor-operation
        }
        return out;
    }
    
    public byte[] separateBytes() {
        return null;
    }
}
