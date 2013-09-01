
package kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet;

/**
 * 
 */
class Listasolmu {
    private Object key;
    private Listasolmu next;
    private Listasolmu prev;

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
