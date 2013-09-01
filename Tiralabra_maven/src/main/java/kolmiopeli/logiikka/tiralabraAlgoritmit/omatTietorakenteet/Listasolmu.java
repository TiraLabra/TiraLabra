
package kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet;

/**
 * OmaLinkedList listan muodostava listasolmu olio.
 */
public class Listasolmu {
    private Object key;
    private Listasolmu next;
    private Listasolmu prev;

    /**
     * Konstruktori, jolle annetaan talletettava olio, seka seuraava ja edellinen
     * listasolmu.
     * @param key Talletettava olio.
     * @param next Listassa seuraava listasolmu.
     * @param prev Listassa edeltava listasolmu.
     */
    Listasolmu(Object key, Listasolmu next, Listasolmu prev) {
        this.key = key;
        this.next = next;
        this.prev = prev;
    }

    public Object getKey() {
        return key;
    }

    public Listasolmu getNext() {
        return next;
    }

    public Listasolmu getPrev() {
        return prev;
    }

    public void setPrev(Listasolmu uusiSolmu) {
        prev = uusiSolmu;
    }

    public void setNext(Listasolmu uusiSolmu) {
        next = uusiSolmu;
    }
    
    
    
}
