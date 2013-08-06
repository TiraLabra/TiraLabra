/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiByteEntities;

/**
 * Abstracts the use of a multiple-byte single entity.
 *
 * @author virta
 */
public abstract class MultiByteAbstraction {

    /**
     * Data to be stored.
     */
    protected byte[] bytes;
    /**
     * Index to which new data is loaded.
     */
    protected int index;
    /**
     * The byte-width of current multiple-byte entity.
     */
    protected int width;

    public MultiByteAbstraction(int width) {
        this.width = width;
        this.index = 0;
        this.bytes = new byte[width];
    }

    /**
     *
     * @return returns the data carried by this entity.
     */
    public byte[] getBytes() {
        return this.bytes;
    }

    /**
     * Adds data to the array at the location marked by the index, which is then
     * incremented.
     *
     * @param data
     * @return true, if the array was not full, false if no more room in array.
     */
    public boolean addData(byte data) {
        if (index < width) {
            bytes[index] = data;
            index++;
            return true;
        }
        return false;
    }

    /**
     * The implementation checks the class of supplied object and then its'
     * hashcode.
     *
     * @param o
     * @return true if hashcodes match, false if not the same class or hashcodes
     * do not match.
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            return this.hashCode() == o.hashCode();
        } else {
            return false;
        }
    }

    /**
     * The implementation tries to provide a perfect hash using a universal hash
     * function. The implementation might have to be re-written for improved
     * collision protection, but it is modelled so that all the permutations of
     * the current byte-width should fit into a single array of greater length
     * than the number of permutations.
     *
     * @return
     */
    @Override
    public int hashCode() {
        double hashCode = 3;

        for (int i = 0; i < bytes.length; i++) {
            hashCode += bytes[i];
        }

        double p = Math.pow(2, (width * 8)) + 3571;

        p = fetchPrime(width);

        double m = Math.pow(2, (width * 8)) + 607;
        m = fetchPrime(width);

        hashCode = (((5 * hashCode) + 11) % p) % m;

        return (int) hashCode;
    }


    private double fetchPrime(int width) {
        switch (width) {
            case 2:
                return 66221.0;
            case 3:
                return 16779199.0;
            case 4:
                return 4294970909.0;    
        }
        return 4297880887.0;
    }
}
