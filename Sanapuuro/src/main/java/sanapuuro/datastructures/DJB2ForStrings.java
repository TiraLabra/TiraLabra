/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.datastructures;

/**
 * A java implementation of the djb2 algorithm.
 * http://www.cse.yorku.ca/~oz/hash.html
 * @author skaipio
 */
public class DJB2ForStrings extends HashFunction<String> {
    /**
     * Calculates normal hash value for a string.
     * @param s String to calculate a hash for.
     * @param m Modulo i.e. max hash size.
     * @return The hash value of string s.
     */
    @Override
    public int getNormalHash(String s, int m){
        long hash = 5381;
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i);           
            hash = ((hash << 5) + hash) + charVal;
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
