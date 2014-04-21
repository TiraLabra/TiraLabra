/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.datastructures;

/**
 *
 * @author skaipio
 */
public interface HashFunction<T> {

    /**
     * Calculates hash value for an object with number of tries taken into account.
     * @param o Object to calculate a hash for.
     * @return The hash value of string s.
     */
    int getHash(T o, int m, int numberOfTry);

    /**
     * Calculates normal hash value for a string.
     * @param o Object to calculate a hash for.
     * @return The hash value of string s.
     */
    int getNormalHash(T o, int m);
    
    /**
     * Calculate m value based on number of keys and the desired load factor
     * for a hash table.
     * @param numberOfKeys
     * @param desiredLoadRate
     * @return 
     */
    int calculateM(int numberOfKeys, double desiredLoadRate);   
}
