/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Algos;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Quicksort extends Algo{

    private static int[] numbers;
    private static int number;

    @Override
    public void sort(int[] values) {

        if (values == null || values.length == 0) {
            return;
        }
        Quicksort.numbers = values;
        number = values.length;
        sort(0, number - 1);
    }

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

    private static void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    @Override
    public String toString() {
        return "Quicksort";
    }
}
