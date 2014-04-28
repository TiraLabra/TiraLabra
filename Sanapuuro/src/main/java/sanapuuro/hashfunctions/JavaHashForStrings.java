/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

/**
 *
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
        int hash = 0;
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i);
            hash += charVal*(int)Math.pow(31, s.length()-1-i);          
        }
        return hash;
    }
}
