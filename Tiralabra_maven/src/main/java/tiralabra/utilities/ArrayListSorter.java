/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.ListIterator;

/**
 * Sorts objects of an ArrayList by using compareTo() - method.
 *
 * @author atte
 */
public class ArrayListSorter {

    /**
     * Sorts the given ArrayList using merge sort. The objects are set in place
     * using the ListIterator class. The objects that the ArrayList holds must
     * implement the Comparable class.
     *
     * @param <T>
     * @param list
     */
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
        appendToSublist(list, left, 0, middle);
        appendToSublist(list, right, middle, list.size());

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    /**
     * Appends all objects from the primary list to the given sublist from a
     * given range.
     *
     * @param <T>
     * @param primary
     * @param sublist
     * @param start
     * @param end
     */
    public static <T extends Comparable<? super T>> void appendToSublist(ArrayList<T> primary, ArrayList<T> sublist, int start, int end) {
        for (int i = start; i < end; i++) {
            sublist.add(primary.get(i));
        }
    }

    /**
     * Used in merge().
     */
    private static int i;
    /**
     * Used in merge().
     */
    private static int j;

    /**
     * Merges the two arrays, left and right, to one. This is intentionally long
     * as java can't do pointers.
     *
     * @param <T>
     * @param left
     * @param right
     * @return
     */
    private static <T extends Comparable<? super T>> ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right) {
        ArrayList<T> result = new ArrayList<>();

        i = 0;
        j = 0;
        while (i < left.size() || j < right.size()) {
            if (i < left.size() && j < right.size()) {
                compareValuesAndAppend(left, right, result);
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

    /**
     * Add the object that compares more favorably between the objects at i and
     * j in their respective lists to the result.
     *
     * @param <T>
     * @param left
     * @param right
     * @param result
     */
    private static <T extends Comparable<? super T>> void compareValuesAndAppend(ArrayList<T> left, ArrayList<T> right, ArrayList<T> result) {
        if (left.get(i).compareTo(right.get(j)) <= 0) {
            result.add(left.get(i));
            i++;
        } else {
            result.add(right.get(j));
            j++;
        }
    }

}
