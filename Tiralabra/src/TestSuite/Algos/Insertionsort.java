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

    @Override
    public void sort(int[] values) {

        if (values == null || values.length == 0) {
            return;
        }

        insertionSort(values);
    }

    private static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j = i;
            while (j > 0 && a[j - 1] > temp) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }

    @Override
    public String toString() {
        return "Insertion sort";
    }
}
