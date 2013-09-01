
package kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet;

/**
 *
 * 
 */
public class OmaLinkedList {
    private Listasolmu head;
    private Listasolmu tail;
    private int size;

    public OmaLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void addFirst(Object key) {
        Listasolmu uusiSolmu = new Listasolmu(key, head, null);
        if (head != null) {
            Listasolmu edellinenEka = uusiSolmu.getNext();
            edellinenEka.setPrev(uusiSolmu);
        }
        head = uusiSolmu;
        if (tail == null) {
            tail = uusiSolmu;
        }
        size++;
    }
    
    public void addLast(Object key) {
        Listasolmu uusiSolmu = new Listasolmu(key, null, tail);
        if (tail != null) {
            Listasolmu edellinenVika = uusiSolmu.getPrev();
            edellinenVika.setNext(uusiSolmu);
        }
        tail = uusiSolmu;
        if (head == null) {
            head = uusiSolmu;
        }
        size++;
    }
    
    public Object removeFirst() {
        Listasolmu poistettava = head;
        Listasolmu uusiHead = poistettava.getNext();
        head = uusiHead;
        
        return null;
    }
    
    public Object removeLast() {
        return null;
    }
    
    public int size() {
        return size;
    }
    
    
}
