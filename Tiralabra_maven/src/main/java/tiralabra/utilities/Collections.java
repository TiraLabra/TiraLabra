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
public class Collections {

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Object[] a = list.toArray();
        Arrays.sort(a);
        ListIterator<T> i = list.listIterator();
        for (int j = 0; j < a.length; j++) {
            i.set((T) a[j]);
            i.next();
        }
    }
}
