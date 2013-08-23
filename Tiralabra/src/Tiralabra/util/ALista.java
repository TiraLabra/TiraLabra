package Tiralabra.util;

/**
 * Kahteen suuntaan linkitetty lista, jossa solmut ovat automaattisesti
 * järjestyksessä. Listaa oletetaan aina käytettävän listan ensimmäisestä
 * solmusta (head) lähtien. Aikavaativuus poistolla ja lisäämisellä O(n).
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
     * @param key uuden solmun arvo
     */
    public void lisaa(int key) {
        Listasolmu uudenNext = this.head;

        //lista on tyhjä
        if (uudenNext == null) {
            uudenNext = new Listasolmu(key, null, null);
            head = uudenNext;
            koko++;
            return;
        }

        //etsitään uuden solmun paikka
        while (uudenNext.getNext() != null && uudenNext.getArvo() < key) {
            uudenNext = uudenNext.getNext();
        }

        Listasolmu uusi;
        //uusi solmu on suurempi kuin listan edelliset, tulee viimeiseksi
        if (uudenNext.getNext() == null && uudenNext.getArvo() < key) {
            uusi = new Listasolmu(key, uudenNext, null);
            uudenNext.setNext(uusi);
        } //muuten uusi solmu tulee paikalleen joko listan alkuun tai jonnekkin väliin
        else {
            Listasolmu uudenPrev = uudenNext.getPrev();
            uusi = new Listasolmu(key, uudenPrev, uudenNext);
            if (uudenPrev != null) {
                uudenPrev.setNext(uusi);
            }
            uudenNext.setPrev(uusi);
            if (head == uudenNext) {
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
        if (this.koko == 0) {
            return false;
        }
        
        if (head.getArvo() == x) {
            if (head.getNext() == null) {
                head = null;
                koko = 0;
                return true;
            }
            
            head.getNext().setPrev(null);
            head = head.getNext();
            koko--;
            return true;
        }

        Listasolmu iter = head;
        while (iter != null && iter.getArvo() <= x) {
            if (iter.getArvo() == x) {
                iter.getPrev().setNext(iter.getNext());
                if (iter.getNext() != null) {
                    iter.getNext().setPrev(iter.getPrev());
                }
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

    /**
     * Palautaa listan alkiot string-muotoisena esityksenä.
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