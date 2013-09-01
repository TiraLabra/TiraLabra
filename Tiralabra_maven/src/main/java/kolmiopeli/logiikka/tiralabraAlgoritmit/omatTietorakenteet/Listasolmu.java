
package kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet;

/**
 * OmaLinkedList listan muodostava listasolmu olio.
 */
class Listasolmu {
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

    Object getKey() {
        return key;
    }

    Listasolmu getNext() {
        return next;
    }

    Listasolmu getPrev() {
        return prev;
    }

    void setPrev(Listasolmu uusiSolmu) {
        prev = uusiSolmu;
    }

    void setNext(Listasolmu uusiSolmu) {
        next = uusiSolmu;
    }
    
    
    
}
