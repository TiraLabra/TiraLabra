
package rakenteet;

/**
 *
 * @author maef
 * Tietorakenteiden rajapinta.
 */
public interface TiRa<E>{
    
    void add(E e);
    boolean contains(E e);
    int size();
    void clear();
}
