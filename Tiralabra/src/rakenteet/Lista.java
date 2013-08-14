
package rakenteet;

/**
 *
 * @author maef
 * Tietorakenteen tapainen, joka korvaa ohjelman valmiissa versiossa ArrayList-olion.
 */
public class Lista<E> {
    
    private transient Object[] jono;
    private int koko;

    public Lista() {
        jono = new Object[10];
    }
    
    public void add(E e) {
        if (koko >= 10) {
            Object[] uusiJono = new Object[2*koko];
            for (int i = 0; i < koko; i++) {
                uusiJono[i] = jono[i];
            }
            jono = uusiJono;
        }
        
        jono[koko] = e;
        koko++;
    }
    
    
    
    
    
}
