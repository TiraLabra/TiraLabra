/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

/**
 * Hash function implementation based on TirA-material.
 * Uses longs instead of BigIntegers which makes lookups
 * quite a bit faster.
 * @author skaipio
 */
public class GeneralHashFuncForStrings extends HashFunction<String> {
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i)-97;
            // smaller multiplier (though at least 26) = less overflow = less collisions, 26 because 26 characters  
            hash += charVal*(int)Math.pow(26, i);   
        }
        return hash;
    }

    /**
     * Calculates hash value for a string with number of tries taken into account.
     * @param s String to calculate a hash for.
     * @param i The ith try to take into account.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s, int i) {
       return this.getHash(s)+i;
    }
}
