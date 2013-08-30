
package rakenteet;

/**
 *
 * @author maef
 * Tietorakenteen tapainen, joka korvaa ohjelman valmiissa versiossa ArrayList-olion.
 * Ei sisällä sen kaikkia ominaisuuksia, mutta on riittävä.
 */
public class Lista<E> implements TiRa{
    
    protected transient Object[] lista;
    protected int koko;

    public Lista() {
        lista = new Object[10];
    }
    
    @Override
    public void add(Object e) {
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
    
    @Override
    public boolean contains(Object e) {

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
    
    @Override
    public int size() {
        return koko;
    }

    @Override
    public void clear() {
        koko = 0;
        lista = new Object[10];
    }
    
   
}
