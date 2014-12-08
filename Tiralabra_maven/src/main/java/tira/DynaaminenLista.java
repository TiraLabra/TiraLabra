/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Iterator;

/**
 * Dynaaminen lista, toteutustapana taulukko
 *
 * @author E
 * @param <E> Jäsenten tyyppi
 */
public class DynaaminenLista<E> implements Lista<E> {

    /**
     * Oletusmaksimikoko
     */
    public static final int DEFAULTSIZE = 5;
    /**
     * Oletuskasvatuskerroin
     */
    public static final int DEFAULTGROWFACTOR = 2;
    /**
     * Listan tämänhetkinen maksimikoko. Sama kuin values-taulukon pituus
     */
    private int maksimiKoko;
    /**
     * Listan tämänhetkinen koko. Koko-1 vastaa listan viimeistä alkiota
     */
    private int koko;
    /**
     * Listan jäsenet tallennetaan taulukkoon
     */
    private Object[] values;

    /////////////////
    //konstruktorit//
    /////////////////
    /**
     * Oletuskokoinen lista
     */
    public DynaaminenLista() {
        this(DEFAULTSIZE);
    }

    /**
     * Annetun kokoinen lista
     *
     * @param aloitusKoko
     */
    public DynaaminenLista(int aloitusKoko) {
        if (aloitusKoko <= 0) {
            aloitusKoko = 1;
        }
        this.maksimiKoko = aloitusKoko;
        this.values = new Object[maksimiKoko];
        this.koko = 0;
    }

    /**
     * Lisätään listan loppuun
     *
     * @param e
     */
    public void add(E e) {
        if (koko >= maksimiKoko) {
            kasvata();
        }
        this.values[koko] = e;
        koko++;
    }

    /**
     * Palautetaan listan i:s jäsen
     *
     * @param i Järjestysnumero
     * @return Jäsen tai null, jos i ei ole kelvollinen
     */
    public E get(int i) {
        if (i < 0 || i >= this.koko) {
            return null;
        }
        return (E) this.values[i];
    }

    /**
     * Palautetaan ja poistetaan listan i:s jäsen
     *
     * @param i Järjestysnumero
     * @return Jäsen tai null, jos i ei ole kelvollinen
     */
    public E remove(int i) {
        if (i < 0 || i >= this.koko) {
            return null;
        }
        E e = (E) this.values[i];
        // siirretään muita
        // O(n), mutta ei sen väliä: ohjelmasssa ei poistella suotta
        for (int j = i + 1; j < this.koko; j++) {
            this.values[j - 1] = this.values[j];
        }
        this.values[this.koko - 1] = null;
        this.koko--;
        return e;
    }

    /**
     * Sisältääkö lista arvon
     *
     * @param e Etsittävä arvo
     * @return True, jos löytyi
     */
    public boolean contains(E e) {
        /*for (int i = 0; i < this.koko; i++) {
            if (e == this.values[i] || e.equals(this.values[i])) {
                return true;
            }
        }
        return false;*/
        return indexOf(e)>=0;
    }

    /**
     * Arvon indeksi listalla
     *
     * @param e Etsittävä arvo
     * @return True, jos löytyi
     */
    public int indexOf(E e) {
        int i = 0;
        while (i < this.koko) {
            if (e == this.values[i] || e.equals(this.values[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Listan koko
     *
     * @return koko
     */
    public int size() {
        return this.koko;
    }

    /**
     * Onko listassa jäseniä
     *
     * @return true, jos tyhjä
     */
    public boolean isEmpty() {
        return this.koko <= 0;
    }

    //////////////////////
    //YKSITYISET METODIT//
    //////////////////////
    /**
     * Kasvattaa listan kokoa oletuskasvatuskertoimen verran
     */
    private void kasvata() {
        this.kasvata(DynaaminenLista.DEFAULTGROWFACTOR);
    }

    /**
     * Kasvattaa listan kokoa kertoimen verran
     *
     * @param factor
     */
    private void kasvata(int factor) {
        this.maksimiKoko = factor * this.maksimiKoko;
        Object[] uusiValues = new Object[this.maksimiKoko];
        System.arraycopy(this.values, 0, uusiValues, 0, this.koko);
        this.values = uusiValues;
    }
    //////////////////////////
    // FOREACH:ITERAATTORI////
    //////////////////////////

    /**
     * For each-toistoa varten
     * @return 
     */
    public Iterator<E> iterator() {
        return new ListaIteraattori<E>();
    }
    /**
     * For each-toistoa varten
     * @param <T> Listan tyyppi
     */
    private final class ListaIteraattori<T> implements Iterator<T>{

        private int i;

        public ListaIteraattori() {
            i=0;

        }

        public boolean hasNext() {
            return i<size();
        }

        public T next() {
            T t = (T)get(i);
            i++;
            return t;
        }
        @Override
        public void remove() { return; }        
    }

}
