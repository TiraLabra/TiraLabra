package kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet;

import java.util.Collection;
import java.util.Iterator;



/**
 * Oma implementaatio kahteen suuntaan linkitetysta listasta.
 */
public class OmaLinkedList<K> {

    private Listasolmu head;
    private Listasolmu tail;
    private int size;

    /**
     * Konstruktori, joka alustaa listan headiksi ja tailiksi null ja kooksi nolla.
     */
    public OmaLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Lisaa listan alkuun alkion.
     * @param key Listaan lisattava olio.
     */
    public void addFirst(K key) {
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

    /**
     * Lisaa listan loppuun alkion.
     * @param key Listaan lisattava olio.
     */
    public void addLast(K key) {
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

    /**
     * Poistaa ja palauttaa listan ensimmaisen alkion.
     * @return Listan alusta poistettu ja palautettu olio.
     */
    public K removeFirst() {
        if (head == null) {
            return null;
        }
        Listasolmu poistettava = head;
        if (poistettava.getNext() == null) {
            head = null;
            tail = null;
        } else {
            Listasolmu uusiHead = poistettava.getNext();
            uusiHead.setPrev(null);
            head = uusiHead;
        }
        size--;
        return (K) poistettava.getKey();
    }

    /**
     * Poistaa ja palauttaa listan viimeisen alkion.
     * @return Listan lopusta poistettu ja palautettu olio.
     */
    public K removeLast() {
        if (tail == null) {
            return null;
        }
        Listasolmu poistettava = tail;
        if (poistettava.getPrev() == null) {
            head = null;
            tail = null;
        } else {
            Listasolmu uusiTail = poistettava.getPrev();
            uusiTail.setNext(null);
            tail = uusiTail;
        }
        size--;
        return (K) poistettava.getKey();
    }

    /**
     * Palauttaa listan koon.
     * @return Listan koko.
     */
    public int size() {
        return size;
    }

}
