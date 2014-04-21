/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.datastructures;

import java.math.BigInteger;

/**
 * Hash function implementation based on TirA-material.
 * Uses BigIntegers which makes a long series of lookups very slow
 * due to having to instantiate numerous BigInteger-objects.
 * @author skaipio
 */
public class GeneralHashFuncForStrings2 extends HashFunction<String> {
    /**
     * Calculates normal hash value for a string.
     * @param s String to calculate a hash for.
     * @param m Modulo i.e. max hash size.
     * @return The hash value of string s.
     */
    @Override
    public int getNormalHash(String s, int m){
        BigInteger hash = new BigInteger("0");
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i);
            BigInteger ascii = new BigInteger(charVal + "");
            BigInteger multiplier = new BigInteger("128");           
            multiplier = multiplier.pow(i);
            hash = hash.add(ascii.multiply(multiplier));
        }
        BigInteger remainder = hash.mod(new BigInteger(m+""));
        return remainder.intValue();
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
