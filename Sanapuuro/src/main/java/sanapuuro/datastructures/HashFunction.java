/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

/**
 * A super class for hash functions that only implements a method
 * for calculating m-values i.e suitable hash table sizes.
 * @author skaipio
 */
public abstract class HashFunction<T> {
    /**
     * Calculates normal hash value.
     * @param o Object to calculate a hash for.
     * @param m Modulo i.e. max hash size.
     * @return The hash value of object o.
     */
    public abstract int getNormalHash(T o, int m);
    
    /**
     * Calculates hash value for a string with number of tries taken into account.
     * @param o Object to calculate a hash for.
     * @param m The m value, i.e. modulo or hash table size.
     * @param i The ith try to take into account.
     * @return The hash value of object o.
     */
    public abstract int getHash(T o, int m, int i);

    /**
     * Calculates the optimal M value based on the number of keys and desired
     * load or fill rate of the hash table.
     *
     * @param numberOfKeys Max number of keys the hash table will be holding.
     * @param desiredLoadRate Desired fill or load rate. Generally a smaller fill rate leads to less collisions.
     * @return A suitable value for hash table size.
     */
    public int calculateM(int numberOfKeys, double desiredLoadRate) {
        int estimatedTableSize = (int) (numberOfKeys / desiredLoadRate);
        int[] primesCloseToTableSize = PrimeNumberUtils.findPrimesCloseTo(estimatedTableSize);
        return PrimeNumberUtils.pickNumberThatIsFarthestFromPowerOfTwo(primesCloseToTableSize);
    }
}
