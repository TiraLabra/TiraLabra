
package rakenteet;

import java.util.Arrays;

/**
 *
 * @author maef
 * Tietorakenteen tapainen, joka korvaa ohjelman valmiissa versiossa ArrayList-olion.
 */
public class Lista<E> {
    
    private transient Object[] lista;
    private int koko;

    public Lista() {
        lista = new Object[10];
    }
    
    public void add(E e) {
        if (koko == lista.length) {
            Object[] uusiLista = new Object[2*koko];
            for (int i = 0; i < koko; i++) {
                uusiLista[i] = lista[i];
            }
            lista = uusiLista;
        }
        
        lista[koko] = e;
        koko++;
    }
    
    public boolean contains(E e) {

        for (int i = 0; i < koko; i++) {
            if (lista[i].equals(e)) {
                return true;
            }
        }
        return false;
    }
    
    public E get(int i) {
        return (E) lista[i];
    }
    
    public int size() {
        return koko;
    }

    public void clear() {
        koko = 0;
    }
    
    
    
}
