/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

import java.math.BigInteger;

/**
 * Hash function implementation based on TirA-material.
 * Uses BigIntegers which makes a long series of lookups very slow
 * due to having to instantiate numerous BigInteger-objects. This is basically
 * just a worse implementation compared to the one that uses int-primitives.
 * An integer value has to be extracted, but the overflow isn't handled well.
 * Any modulo would have to be applied before extracting the integer value
 * from a BigInteger, otherwise a bunch of collisions happen.
 * @author skaipio
 */
public class GeneralHashFuncForStrings2 extends HashFunction<String> {
    
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s) {
        BigInteger hash = new BigInteger("0");
        for (int i = 0; i < s.length(); i++){
            // the noncapital character ascii values start at decimal 97 and continue to 122
            int charVal = s.charAt(i)-96;       // -97 results in collisions but -96 doesnt??? 
            BigInteger ascii = new BigInteger(charVal + "");
            // smaller multiplier (though at least 26) = less overflow = less collisions, 26 because 26 characters  
            BigInteger multiplier = new BigInteger("26");  
            multiplier = multiplier.pow(i);
            hash = hash.add(ascii.multiply(multiplier));
        }
        return hash.intValue();
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
