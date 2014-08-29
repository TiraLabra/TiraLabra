/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Algos;

/**
 * Includes abstract method that are required from extending classes
 *
 * @author Marko <markoma@iki.fi>
 */
public abstract class Algo {

    /**
     * required method for all extending classes
     *
     * @param a array to sort
     */
    public abstract void sort(int[] a);

    /**
     * required method for all extending classes
     *
     * @return name of sorting algorithm
     */
    @Override
    public abstract String toString();
}
