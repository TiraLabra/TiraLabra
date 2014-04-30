/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.hashfunctions;

/**
 * MurmurHash3 java implementation.
 * https://code.google.com/p/smhasher/wiki/MurmurHash3
 *
 * @author skaipio
 */
public class MurmurHash3ForStrings extends HashFunction<String> {

    // constants that "just work well"
    private final int c1 = 0xcc9e2d51;
    private final int c2 = 0x1b873593;
    private final int r1 = 15;
    private int seed;
    
    public MurmurHash3ForStrings(int seed){
        this.seed = seed;
    }
    
    public MurmurHash3ForStrings(){
        this(739493471);
    }
    
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s) {
        byte[] bytes = s.getBytes();
        int length = bytes.length;
        int hash = seed ^ length;
        int length4 = length >> 2; // >> 2 is equivalent to dividing by 4 (shift all bits right two times)

        for (int i = 0; i < length4; i++) {
            int indexStart = i << 2;   // << 2 is equivalent to multiplying by 4 (shift all bits left two times)
            int k = this.getBlock(indexStart, bytes);
            
            k *= c1;
            k ^= this.rotateLeft(k, r1);
            k *= c2;
            
            hash ^= k;
            hash = this.rotateLeft(hash, r1);
            hash = hash*5+0xe6546b64;
        }

        int bytesLeft = length - (length4 << 2);
        
        int k = 0;
        
        switch(bytesLeft){
            case 3: k ^= bytes[length - 3] << 15;
            case 2: k ^= bytes[length - 2] << 8;
            case 1: k ^= bytes[length - 1];
                    k *= c1; k = this.rotateLeft(k, 15); k *= c2; hash ^= k;
        }

        hash ^= length;
        hash = this.finalizer32bit(hash);
        return hash;
    }
    
    /**
     * An improved version over MurmurHash2's finalizer.
     * Uses magic numbers that supposedly work well.
     * @param hash Hash value to finalize.
     * @return Finalized hash value.
     */
    private int finalizer32bit(int hash){
        hash ^= hash >>> 16;
        hash *= 0x85ebca6b;
        hash ^= hash >>> 13;
        hash *= 0xc2b2ae35;
        hash ^= hash >>> 16;
        return hash;
    }

    /**
     * Gets a block of 4 bytes as an integer value.
     * @param indexStart The index where to start taking 4 blocks from.
     * @param bytes A byte array to take the block from.
     * @return Integer value of a block of 4 bytes.
     */
    private int getBlock(int indexStart, byte[] bytes) {
        int k = bytes[indexStart + 3]; // add bits from first byte
        k = k << 8; // shift bits left 8 times
        k = k | (bytes[indexStart + 2] & 0xff); // add bits from next byte
        k = k << 8; // shift left again for next byte
        k = k | (bytes[indexStart + 1] & 0xff);
        k = k << 8;
        k = k | (bytes[indexStart + 0] & 0xff);
        return k;
    }

    /**
     * Rotates bits to the left.
     * Bits that overflow from left are added back from right.
     * @param toRotate 32-bit sequence to rotate.
     * @param bits By how many bits to rotate.
     * @return Integer value of rotated 32-bit sequence.
     */
    private int rotateLeft(int toRotate, int bits) {
        return (toRotate << bits) | (toRotate >>> (32 - bits));
    }
}
