package Tiralabra.util;

/** Listasolmu kahteen suuntaan linkitettyä listaa varten.
 *
 * @author Pia Pakarinen
 */
public class Listasolmu {
    
    /**
     * Listasolmun sisältämä arvo.
     */
    private int arvo;
    /**
     * Seuraava solmu listalla.
     */
    private Listasolmu next;
    /**
     * Edeltävä solmu listalla.
     */
    private Listasolmu prev;

    /** Luo uuden listasolmun.
     * 
     * @param x solmun arvo
     * @param prev edellinen listalla
     * @param next seuraava listalla
     */
    public Listasolmu(int x, Listasolmu prev, Listasolmu next) {
        this.arvo = x;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Palauttaa solmun arvon.
     * @return solmun arvo
     */
    public int getArvo() {
        return arvo;
    }

    /** Palauttaa seuraavan listalla.
     * 
     * @return seuraava solmu
     */
    public Listasolmu getNext() {
        return next;
    }

    /** Palauttaa edellisen solmun listalla.
     * 
     * @return edellinen solmu
     */
    public Listasolmu getPrev() {
        return prev;
    }

    /** Asettaa seuraavan solmun.
     * 
     * @param next nykyiselle solmulle uusi seuraaja
     */
    public void setNext(Listasolmu next) {
        this.next = next;
    }

    /** Asettaa edeltävän solmun.
     * 
     * @param prev nykyiselle solmulle uusi edeltäjä
     */
    public void setPrev(Listasolmu prev) {
        this.prev = prev;
    }
    
}