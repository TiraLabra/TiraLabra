/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Algos;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Quicksort extends Algo {

    private static int[] numbers;
    private static int number;

    /**
     * Recursive method for Quick sort checks if array is null or lenght 0
     *
     * @param arr array to sort with Quick sort
     */
    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }
        Quicksort.numbers = arr;
        number = arr.length;
        sort(0, number - 1);
    }

    /**
     * Quick sort algorithm with low/high divide
     *
     * @param low low
     * @param high high
     */
    private static void sort(int low, int high) {
        int i = low, j = high;
        int pivot = numbers[low + (high - low) / 2];

        // Divide
        while (i <= j) {
            while (numbers[i] < pivot) {
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }

            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }

        // Recursive 
        if (low < j) {
            sort(low, j);
        }
        if (i < high) {
            sort(i, high);
        }
    }

    /**
     * Swaps given columns
     *
     * @param i column to swap
     * @param j column to swap
     */
    private static void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    /**
     *
     * @return name of algorithm
     */
    @Override
    public String toString() {
        return "Quicksort";
    }
}
