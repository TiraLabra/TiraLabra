/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Iterator;

/**
 * Linkitetty lista, toteutustapana kahteen suuntaan linkitetty taulukko
 *
 * @author E
 */
public class LinkitettyLista<E> implements Lista<E> {

    /**
     * Oletusmaksimikoko
     */
    public static final int DEFAULTSIZE = 5;
    /**
     * Oletuskasvatuskerroin
     */
    public static final int DEFAULTGROWFACTOR = 2;
    /**
     * taulukkojen koko
     */
    private int maksimiKoko;
    /**
     * Listan jäsenien lukumäärä
     */
    private int koko;
    /**
     * Listan arvot
     */
    private Object[] values;
    /**
     * Seuraavien arvojen indeksit
     */
    private int[] next;
    /**
     * Edellisten arvojen indeksit
     */
    private int[] prev;
    /**
     * Listan pää
     */
    private int head;
    /**
     * Listan häntä
     */
    private int tail;

    /////////////////
    //KONSTRUKTORIT//
    /////////////////
    public LinkitettyLista() {
        this(DEFAULTSIZE);
    }

    public LinkitettyLista(int aloitusKoko) {
        if (aloitusKoko <= 0) { // tai error
            aloitusKoko = 1;
        }
        this.koko = 0;
        this.maksimiKoko = aloitusKoko;
        this.next = new int[maksimiKoko]; // -1 arvona: ei seuraavaa
        this.prev = new int[maksimiKoko]; // -1 arvona: ei edellistä
        this.values = new Object[maksimiKoko]; // null: ei asetettu
        this.head = 0;
        this.tail = 0; // viimeinen alkio listassa
        this.prev[head] = -1;
        this.next[head] = -1;
    }

    //////////////
    //Lisääminen//
    //////////////
    /**
     * Lisätään viimeiseksi jäseneksi e
     *
     * @param e Lisättävä arvo
     */
    public void add(E e) { // TOIMII
        if (koko >= maksimiKoko) {
            // kasvatetaan listan kokoa
            this.kasvata();
        }
        int uusiIndeksi = seuraavaTyhja();
        this.values[uusiIndeksi] = e;
        this.next[uusiIndeksi] = -1; // loppumerkki
        if (uusiIndeksi != this.tail) {
            this.prev[uusiIndeksi] = this.tail;
            this.next[tail] = uusiIndeksi;
        }
        this.tail = uusiIndeksi;
        this.koko++;
    }

    /////////////////////////////
    //Listan jäsenten hakeminen//
    /////////////////////////////
    /**
     * Palauttaa listan ensimmäisen jäsenen
     *
     * @return ensimmäisen jäsen tai null jos lista tyhjä
     */
    public E peek() {
        return this.get(0);
    }

    /**
     * Palauttaa listan viimeisen jäsenen
     *
     * @return viimeinen jäsen tai null jos lista tyhjä
     */
    public E peekLast() {
        return this.get(this.koko - 1);
    }

    /**
     * Palauttaa listan index:n jäsenen
     *
     * @param index Järjestysnumero
     * @return Jäsen
     */
    public E get(int index) {
        if (index < 0 || index >= this.koko) {
            return null; // tai error
        }
        Object e = null;
        if (index == 0) { // head
            e = this.values[head];
        } else if (index == this.koko - 1) { // tail
            e = this.values[tail];
        } else {
            e = this.values[this.getPlace(index)];
        }
        return (E) e;
    }

    /**
     * Palauttaa listan index:n jäsenen paikan
     *
     * @param index Järjestysnumero
     * @return Jäsenen paikka
     */
    private int getPlace(int index) {
        int i = 0;

        int curr = this.head;
        // etsitään indeksiä (järjestysnumeroa) vastaava kohta listalta
        // O(n)
        while (curr >= 0) {
            if (index == i) {
                break;
            }
            curr = this.next[curr];
            i++;
        }
        return curr;
    }
    /////////////////////////
    // Poistaminen listalta//
    /////////////////////////

    /**
     * Poistaa ja palauttaa listan pään
     *
     * @return Listan pää
     */
    public E poll() {
        return this.remove(0);
    }

    public E pollLast() {
        return this.remove(this.koko - 1);
    }

    /**
     * Poistaa listasta jäsenen, jonka indeksi on index
     *
     * @param index Poistettavan järjestysnumero
     * @return Poistettava arvo
     */
    public E remove(int index) {
        // WIP
        E e = null;
        if (this.isEmpty() || index < 0 || index >= this.koko) {
            return null;
        } // head==tail <-> koko == 1
        else if (head == tail) {
            e = (E) this.values[this.head];
            this.values[this.head] = null;
            this.head = 0;
            this.tail = 0;
        } else if (index == 0) { // head
            e = (E) this.values[this.head];
            this.values[this.head] = null;
            this.head = this.next[this.head];
            this.prev[this.head] = -1;
        } else if (index == this.koko - 1) { // tail
            e = (E) this.values[this.tail];
            this.values[this.tail] = null;
            this.tail = this.prev[this.tail];
            this.next[this.tail] = -1;
        } else {
            int curr = this.getPlace(index); // paikka listassa
            e = (E) this.values[curr]; // poistettava arvo
            this.values[curr] = null;  // poistetaan taulusta
            int prevIndex = this.prev[curr];
            int nextIndex = this.next[curr];
            if (prevIndex >= 0) {
                this.next[prevIndex] = nextIndex;
            }
            if (nextIndex >= 0) {
                this.prev[nextIndex] = prevIndex;
            }
            this.prev[curr] = -1;
            this.next[curr] = -1;
        }
        this.koko--;
        return e;
    }

    ////////////////////////////////////////////
    // Listan koko, totuusarvot, arvon indeksi//
    ////////////////////////////////////////////
    /**
     * Poistaa ja palauttaa olion e
     *
     * @param e Poistettava olio
     * @return Poistettava olio tai null jos listassa ei ollut sitä
     */
    public E remove(E e) {
        int index = indexOf(e);
        return this.remove(index);
    }

    /**
     * Sisältääkö lista olion e
     *
     * @param e Tarkistettava olio
     * @return True, jos sisältää, muutoin false
     */
    public boolean contains(E e) {
        for (Object value : this.values) {
            if (value == null) {
                continue;
            }
            if (e == value || e.equals((E) value)) {
                return true;
            }
        }
        return false;
        // return indexOf(e)>=0;
    }

    /**
     * Listan jäsenen järjestysnumero
     *
     * @param e Etsittävä arvo
     * @return -1 jos ei listalla, muutoin arvon järjestysnumero
     */
    public int indexOf(E e) {
        int index = 0;
        if (!this.isEmpty()) {
            int curr = this.head;
            while (curr >= 0) {
                E value = (E) this.values[curr];
                if (e.equals(value)) {
                    return index;
                }
                curr = this.next[curr];
                index++;
            }
        }
        return -1;
    }

    /**
     * Onko lista tyhjä
     *
     * @return Onko listassa jäseniä
     */
    public boolean isEmpty() {
        return this.koko <= 0;
    }

    /**
     *
     * @return Listan koko
     */
    public int size() {
        return this.koko;
    }

    //////////////////////
    //yksityiset metodit//
    //////////////////////
    
    // WIP: metodi, joka järjestää uudestaan listan: jarjesta()
    
    /**
     * Kasvattaa listan maksimikokoa oletusparametrin verran
     */
    private void kasvata() {
        this.kasvata(DEFAULTGROWFACTOR);
    }

    /**
     * Kasvattaa listan maksimikokoa
     *
     * @param factor Kerroin, jonka verran kasvatetaan
     */
    private void kasvata(int factor) {
        if (factor <= 1) {
            return;
        }
        int uusiMaksimi = factor * this.maksimiKoko; // exp-kasvu 2^n
        int[] uusiNext = new int[uusiMaksimi];
        int[] uusiPrev = new int[uusiMaksimi];
        Object[] uusiValues = new Object[uusiMaksimi];

        int curr = this.head;
        int previous = -1, i = 0;
        // kopioidaan arvot
        while (curr >= 0 && curr < this.maksimiKoko && this.values[curr] != null) {
            uusiValues[i] = this.values[curr];
            uusiPrev[i] = previous;
            uusiNext[i] = i + 1;
            previous = i;
            i++;
            curr = this.next[curr];
        }
        uusiNext[previous] = -1;
        this.head = 0;
        this.tail = previous;

        this.maksimiKoko = uusiMaksimi;
        this.next = uusiNext;
        this.prev = uusiPrev;
        this.values = uusiValues;
    }

    /**
     *
     * @return Seuraava tyhjä kohta listalla, -1 jos listalla ei tyhjiä paikkoja
     */
    private int seuraavaTyhja() {
        int curr = this.tail;
        Object e = this.values[curr];
        while (e != null) {
            curr++;
            if (curr >= this.maksimiKoko) {
                curr = 0;
            }
            if (curr == this.tail) { // pyörähti ympäri, tyhjää ei löytynyt
                // ei pitäisi tapahtua
                // voidaan esim. kasvattaa kokoa ja palauttaa siitä seuraava tyhjä
                // nyt näin: (WIP)
                // System.out.println("WOOT");
                return -1;
            }
            e = this.values[curr];
        }
        return curr;
    }
    //////////////////////////
    // FOREACH:ITERAATTORI////
    //////////////////////////

    /**
     * For each-toistoa varten
     *
     * @return
     */
    public Iterator<E> iterator() {
        return new ListaIteraattori<E>();
    }

    /**
     * For each-toistoa varten
     *
     * @param <T> Listan tyyppi
     */
    private final class ListaIteraattori<T> implements Iterator<T> {

        private int i;


        public ListaIteraattori() {
            i = head;
        }

        public boolean hasNext() {
            // !lista.isEmpty mukana, jottei sekaannuta 
            return !isEmpty() && i >= 0 && i < maksimiKoko; // -1 on loppumerkki
        }

        public T next() {
            T t = (T)get(i);
            if (t == null) {
                // hmm!
            }
            i = next[i]; // private-tyyppinen, mutta tässä menee
            return t;
        }
    }

    //////////////////////////
    // automaattiset metodit//
    //////////////////////////
    
    // WIP: poisteaan lopulta, testaamista varten
    // getterit

    public int getMaksimiKoko() {
        return maksimiKoko;
    }

    public int getKoko() {
        return koko;
    }

    public Object[] getValues() {
        return values;
    }

    public int[] getNext() {
        return next;
    }

    public int[] getPrev() {
        return prev;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

}
