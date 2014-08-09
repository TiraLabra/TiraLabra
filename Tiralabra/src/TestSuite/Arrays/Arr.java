/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

/**
 * Abstract class for arrays
 *
 * @author Marko <markoma@iki.fi>
 */
public abstract class Arr {

    /**
     * required method for all extending classes
     *
     * @return array
     */
    public abstract int[] get();

    /**
     * required method for all extending classes
     *
     * @return name of the array
     */
    @Override
    public abstract String toString();
}
