package Tiralabra.util;

/**
 * Kahteen suuntaan linkitetty lista, jossa solmut ovat automaattisesti järjestyksessä. 
 * Listaa oletetaan aina käytettävän listan ensimmäisestä solmusta (head) lähtien. 
 * Aikavaativuus poistolla ja lisäämisellä O(n).
 * Solmujen järjestys on pienimmästä suurimpaan.
 *
 * @author Pia Pakarinen
 */
public class ALista {

    /**
     * Listan ensimmäinen solmu.
     */
    private Listasolmu head;
    /**
     * Listan koko.
     */
    private int koko;

    /**
     * Luo uuden listan ja asettaa annetun solmun sen ensimmäiseksi.
     *
     * @param h listan ensimmäinen listasolmu
     */
    public ALista(int arvo) {
        this.head = new Listasolmu(arvo, null, null);
        koko = 1;
    }

    /**
     * Luo uuden, tyhjän listan.
     */
    public ALista() {
        koko = 0;
    }

    /**
     * Lisää listalle uuden solmun oikeaan paikkaansa.
     *
     * @param x uuden solmun arvo
     */
    public void lisaa(int x) {
        Listasolmu y = this.head;

        //lista on tyhjä
        if (y == null) {
            y = new Listasolmu(x, null, null);
            head = y;
            return;
        }

        //etsitään uuden solmun paikka
        while (y.getNext() != null && y.getArvo() < x) {
            y = y.getNext();
        }

        Listasolmu uusi;
        //uusi solmu on suurempi kuin listan edelliset, tulee viimeiseksi
        if (y.getNext() == null && y.getArvo() < x) {
            uusi = new Listasolmu(x, y, null);
            y.setNext(uusi);
        }
        //muuten uusi solmu tulee paikalleen joko listan alkuun tai jonnekkin väliin
        else {
            Listasolmu edeltaja = y.getPrev();
            uusi = new Listasolmu(x, edeltaja, y);
            if (edeltaja != null) {
                edeltaja.setNext(uusi);
            }
            y.setPrev(uusi);
            if (head == y) {
                head = uusi;
            }
        }
        koko++;
    }

    /**
     * Poistaa listalta annetun arvon sisältämän solmun.
     *
     * @param x poistettavan solmun arvo
     * @return true jos poistettava solmu löytyy listalta, false muuten
     */
    public boolean poista(int x) {
        if (head.getArvo() == x) {
            head.getNext().setPrev(null);
            head = head.getNext();
            koko--;
            return true;
        }

        Listasolmu iter = head.getNext();
        while (iter.getNext() != null && iter.getArvo() <= x) {
            if (iter.getArvo() == x) {
                iter.getPrev().setNext(iter.getNext());
                iter.getNext().setPrev(iter.getPrev());
                koko--;
                return true;
            }
            iter = iter.getNext();
        }
        return false;
    }

    /**
     * Palauttaa listan ensimmäisen solmun.
     *
     * @return listan johtaja
     */
    public Listasolmu getLista() {
        return head;
    }

    /**
     * Palauttaa listan koon.
     *
     * @return listan alkioiden määrä
     */
    public int getKoko() {
        return koko;
    }

    /** Palautaa listan alkiot string-muotoisena esityksenä.
     * 
     * @return alkiot min ---> max
     */
    @Override
    public String toString() {
        Listasolmu s = head;
        String solmut = "";
        while (s != null) {
            solmut += s.getArvo() + "\n";
            s = s.getNext();
        }
        return solmut;
    }
}