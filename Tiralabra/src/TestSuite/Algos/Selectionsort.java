/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Algos;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Selectionsort extends Algo {
    /**
     * Sorts given array with selection sort
     * @param a array to sort
     */
    @Override
    public void sort(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[index]) {
                    index = j;
                }
            }

            int smaller = a[index];
            a[index] = a[i];
            a[i] = smaller;
        }
    }
    /**
     * 
     * @return name of algorithm
     */
    @Override
    public String toString() {
        return "Selection sort";
    }
}
