/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Arraymaker {

    public int[] sorted(int size) {
        int[] s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
        }
        return s;
    }

    public int[] reversed(int size) {
        int[] r = new int[size];
        for (int j = size - 1, i = 0; j >= 0; j--, i++) {
            r[j] = i;
        }
        return r;
    }

}
