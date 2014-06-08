/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

import java.util.ListIterator;

/**
 *
 * @author atte
 * @param <T>
 */
public interface List<T> extends Collection<T> {
    public ListIterator<T> listIterator();
}
