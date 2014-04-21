/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.datastructures;

/**
 * Hash function implementation based on TirA-material.
 * Uses longs instead of BigIntegers which makes lookups
 * quite a bit faster.
 * @author skaipio
 */
public class GeneralHashFuncForStrings implements HashFunction<String> {
    /**
     * Calculates normal hash value for a string.
     * @param s String to calculate a hash for.
     * @param m Modulo i.e. max hash size.
     * @return The hash value of string s.
     */
    @Override
    public int getNormalHash(String s, int m){
        long hash = 0;
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i);
            long multiplier = (long)Math.pow(128, i);
            hash = hash+charVal*multiplier;            
        }
        return (int)(hash % m);
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
    
    @Override
    public int calculateM(int numberOfKeys, double desiredLoadRate) {
        int estimatedTableSize = (int) (numberOfKeys / desiredLoadRate);
        int[] primesCloseToTableSize = PrimeNumberUtils.findPrimesCloseTo(estimatedTableSize);
        return PrimeNumberUtils.pickNumberThatIsFarthestFromPowerOfTwo(primesCloseToTableSize);
    }
}
