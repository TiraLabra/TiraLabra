/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Algos;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Insertionsort extends Algo {
    /**
     * Sorts array with Insertion sort
     * @param a 
     */
    @Override
    public void sort(int[] a) {

        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            int j = i;
            while (j > 0 && a[j - 1] > tmp) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = tmp;
        }
    }
    
    /**
     * 
     * @return name
     */
    @Override
    public String toString() {
        return "Insertion sort";
    }
}
