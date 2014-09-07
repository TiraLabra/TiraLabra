/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 29.8.2014 
 */
package TestSuite.Algos;

import java.util.Arrays;

/**
 * Test Algo uses Java.utils.Array sort
 *
 * @author Marko <markoma@iki.fi>
 */
public class AlgoImpl extends Algo {

    @Override
    public void sort(int[] a) {
        Arrays.sort(a);
    }

    @Override
    public String toString() {
        return "Hogwarts School of Witchcraft and Wizardry";
    }
}
