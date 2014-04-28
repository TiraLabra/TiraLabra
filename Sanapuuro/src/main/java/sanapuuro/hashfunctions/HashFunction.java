/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.hashfunctions;

/**
 * A super class for hash functions that only implements a method
 * for calculating m-values i.e suitable hash table sizes.
 * @author skaipio
 */
public abstract class HashFunction<T> {
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param o Object to calculate a hash for.
     * @return The hash value of object o.
     */
    public abstract int getHash(T o);
    
    /**
     * Calculates hash value for an object with number of tries taken into account.
     * @param o Object to calculate a hash for.
     * @param i The ith try to take into account.
     * @return The hash value of object o.
     */
    public int getHash(T o, int i) {
        if (i < 0) throw new IllegalArgumentException("The number of try cannot be negative.");
        return this.getHash(o)+i;
    }  
}
