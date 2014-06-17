/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Arrays;
import java.util.ListIterator;

/**
 *
 * @author atte
 */
public class ArrayListSorter {

    public static <T extends Comparable<? super T>> void sort(ArrayList<T> list) {
        ArrayList<T> result = mergeSort(list);
        ListIterator<T> it = list.listIterator();
        for (int i = 0; i < result.size(); i++) {
            it.set(result.get(i));
            it.next();
        }
    }

    /**
     * Uses merge sorting to produce a new ArrayList of the given ArrayList,
     * where the objects are stored in order based on the compareTo() - method.
     *
     * @param <T>
     * @param list
     * @return
     */
    private static <T extends Comparable<? super T>> ArrayList<T> mergeSort(ArrayList<T> list) {
        if (list.size() <= 1) {
            return list;
        }

        ArrayList<T> left = new ArrayList<>();
        ArrayList<T> right = new ArrayList<>();
        int middle = list.size() / 2;

        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }
        for (int i = middle; i < list.size(); i++) {
            right.add(list.get(i));
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    /**
     * Merges the two arrays, left and right, to one.
     *
     * @param <T>
     * @param left
     * @param right
     * @return
     */
    private static <T extends Comparable<? super T>> ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right) {
        ArrayList<T> result = new ArrayList<>();

        int j = 0;
        int i = 0;
        while (i < left.size() || j < right.size()) {
            if ((i < left.size() && j < right.size())) {
                if (left.get(i).compareTo(right.get(j)) <= 0) {
                    result.add(left.get(i));
                    i++;
                } else {
                    result.add(right.get(j));
                    j++;
                }
            } else if (i < left.size()) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        return result;
    }
}
