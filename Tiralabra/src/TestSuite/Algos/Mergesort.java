/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Algos;

import java.util.Arrays;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Mergesort extends Algo {

    /**
     * Sorts given array with Merge sort
     *
     * @param arr array to sort
     */
    @Override
    public void sort(int[] arr) {

        if (arr.length > 1) {
            int q = arr.length / 2;

            int[] leftArray = Arrays.copyOfRange(arr, 0, q);
            int[] rightArray = Arrays.copyOfRange(arr, q, arr.length);

            sort(leftArray);
            sort(rightArray);

            merge(arr, leftArray, rightArray);
        }
    }

    static void merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;

        int i, li, ri;
        i = li = ri = 0;
        while (i < totElem) {
            if ((li < l.length) && (ri < r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                } else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            } else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
    }

    /**
     *
     * @return name of the algorithm
     */
    @Override
    public String toString() {
        return "Merge sort";
    }
}
