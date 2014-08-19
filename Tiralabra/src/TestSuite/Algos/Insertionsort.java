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
     *
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }

    /**
     *
     * @return name of algorithm
     */
    @Override
    public String toString() {
        return "Insertion sort";
    }
}
