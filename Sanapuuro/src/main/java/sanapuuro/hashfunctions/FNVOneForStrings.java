/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

/**
 * A hash function that is based on the FNV-1 algorithm.
 * @author skaipio
 */
public class FNVOneForStrings extends HashFunction<String> {
    //http://www.isthe.com/chongo/tech/comp/fnv/index.html   
    private final int fnvOffsetBasis = (int)2166136261l;
    //private final long s = 4;
    //private final long b = 0b00110111;
    // http://www.isthe.com/chongo/tech/comp/fnv/index.html#fnv-prime
    //private final long fnvPrime = (long)(Math.pow(256, (5+Math.pow(2,s))/12)+Math.pow(2,8)+b);
    private final int fnvPrime = 16777619;
    
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s) {
        int hash = fnvOffsetBasis;
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i);           
            hash = hash ^ charVal;
            hash = hash * fnvPrime;
        }
        return hash;
    }
}
