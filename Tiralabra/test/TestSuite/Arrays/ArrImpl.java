/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 29.8.2014 
 */
package TestSuite.Arrays;

/**
 * Extending test class
 *
 * @author Marko <markoma@iki.fi>
 */
public class ArrImpl extends Arr {

    @Override
    public int[] get() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        return arr;
    }

    @Override
    public String toString() {
        return "TestArr";
    }
}
