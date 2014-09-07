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
     *
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }

            int smaller = arr[index];
            arr[index] = arr[i];
            arr[i] = smaller;
        }
    }

    /**
     *
     * @return name of the algorithm
     */
    @Override
    public String toString() {
        return "Selection sort";
    }
}
