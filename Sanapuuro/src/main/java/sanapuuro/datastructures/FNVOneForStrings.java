/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.datastructures;

/**
 * A hash function that is based on the FNV-1 algorithm.
 * @author skaipio
 */
public class FNVOneForStrings extends HashFunction<String> {
    //http://www.isthe.com/chongo/tech/comp/fnv/index.html   
    private final long fnvOffsetBasis = 2166136261l;
    //private final long s = 4;
    //private final long b = 0b00110111;
    // http://www.isthe.com/chongo/tech/comp/fnv/index.html#fnv-prime
    //private final long fnvPrime = (long)(Math.pow(256, (5+Math.pow(2,s))/12)+Math.pow(2,8)+b);
    private final long fnvPrime = 16777619;
    
    /**
     * Calculates normal hash value for a string.
     * @param s String to calculate a hash for.
     * @param m Modulo i.e. max hash size.
     * @return The hash value of string s.
     */
    @Override
    public int getNormalHash(String s, int m){
        long hash = fnvOffsetBasis;
        for (int i = 0; i < s.length(); i++){
            long charVal = s.charAt(i);           
            hash = hash ^ charVal;
            hash = hash * fnvPrime;
        }
        return (int)Math.abs(hash % m);
    }
    
    /**
     * Calculates hash value for a string with number of tries taken into account.
     * @param s String to calculate a hash for.
     * @param m The m value, i.e. modulo or hash table size.
     * @param i The ith try to take into account.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s, int m, int i){
       int hash = this.getNormalHash(s, m);
       return (hash + i) % m;
    }
}
