/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 19.8.2014 
 */
package TestSuite.Algos;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Bubblesort extends Algo {

    /**
     * Sorts array with Bubble sort
     *
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     *
     * @return name of algorithm
     */
    @Override
    public String toString() {
        return "Bubble sort";
    }
}
