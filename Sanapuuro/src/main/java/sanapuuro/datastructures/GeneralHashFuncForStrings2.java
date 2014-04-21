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
public class GeneralHashFuncForStrings2 implements HashFunction<String> {
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
     * @param m
     * @param numberOfTry
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s, int m, int numberOfTry){
       int hash = this.getNormalHash(s, m);
       return (hash + numberOfTry) % m;
    }
    
    /**
     * Calculates the optimal M value based on the number of keys and desired
     * load or fill rate of the hash table.
     * @param numberOfKeys Max number of keys the hash table will be holding. 
     * @param desiredLoadRate Desired fill or load rate. Generally a smaller fill rate leads to less collisions.
     * @return A suitable value for hash table size.
     */
    @Override
    public int calculateM(int numberOfKeys, double desiredLoadRate) {
        int estimatedTableSize = (int) (numberOfKeys / desiredLoadRate);
        int[] primesCloseToTableSize = PrimeNumberUtils.findPrimesCloseTo(estimatedTableSize);
        return PrimeNumberUtils.pickNumberThatIsFarthestFromPowerOfTwo(primesCloseToTableSize);
    }
}
