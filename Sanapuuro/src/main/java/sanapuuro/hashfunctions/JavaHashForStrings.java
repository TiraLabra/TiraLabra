/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

/**
 * Uses Java's own .hashCode() method for getting hash values for string objects.
 * @author skaipio
 */
public class JavaHashForStrings extends HashFunction<String> {
    
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s) {
        return s.hashCode();
    }
}
