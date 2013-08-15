package Tiralabra.util;

/** Kahteen suuntaan linkitetty lista.
 * Listaa oletetaan aina käytettävän listan ensimmäisestä solmusta (head) lähtien.
 * @author Pia Pakarinen
 */
public class Lista {

    private int arvo;
    private Lista next;
    private Lista prev;
    private boolean head;

    public Lista(int x, Lista p, Lista n) {
        if (n == null && p == null) {
            head = true;
        } else {
            head = false;
        }
        this.next = n;
        this.prev = p;
        this.arvo = x;
    }

    public void lisaa(int x) {
        Lista uusi = new Lista(x, this.prev, this);
        this.prev = uusi;
    }
    
    public boolean poista(int x) {
        if (this.arvo == x) {
            this.next.head = true;
            this.next.prev = this.prev;
            this.prev.next = this.next;
            return true;
        }
        
        Lista iter = this.next;
        while (iter.head == false) {
            if (iter.arvo == x) {
                iter.prev.next = iter.next;
                iter.next.prev = iter.prev;
                return true;
            }
            iter = iter.next;
        }
        return false;
    }

    /** Palauttaa listan alkion arvon.
     * 
     * @return arvo
     */
    public int getArvo() {
        return arvo;
    }

    /** Palauttaa listalla seuraavan alkion.
     * 
     * @return nykyistä alkiota seuraava
     */
    public Lista getNext() {
        return next;
    }

    /** Palauttaa listalla edeltävän alkion,
     * 
     * @return nykyistä alkiota edeltävä
     */
    public Lista getPrev() {
        return prev;
    }
    
}