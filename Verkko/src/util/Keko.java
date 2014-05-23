/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

import java.util.Collection;
import java.util.Comparator;

/**
 * Binäärikeko jossa olioita järjestettynä annetun Comparatorin mukaan.
 * Indeksointi alkaa 1:stä, taulukon 0:s solu on aina null.
 *
 * @param <E> säilöttävä olio
 */
public class Keko<E> {

    /**
     * Defaultti alkukoko sisäiselle taulukolle jota kasvatetaan tarvittaessa
     */
    private static final int ALKUKOKO = 10;

    /**
     * Taulu olioille. Kasvatetaan tarvittaessa
     */
    private E[] taulukko;

    /**
     * Olioiden määrä keossa, samalla myös keon alimman indeksi.
     */
    private int koko;

    /**
     *
     */
    private Comparator<E> comparator;

    /**
     * Uusi keko collectionista
     *
     * @param collection
     * @param comparator
     */
    public Keko(Collection<E> collection, Comparator<E> comparator) {
        this((E[]) collection.toArray(), comparator);
    }

    /**
     * Uusi tyhjä keko
     *
     * @param comparator
     */
    public Keko(Comparator<E> comparator) {
        taulukko = (E[]) new Object[ALKUKOKO];
        koko = 0;
        this.comparator = comparator;
    }

    /**
     * Uusi keko taulukosta
     *
     * @param taulukko
     * @param comparator
     */
    public Keko(E[] taulukko, Comparator<E> comparator) {
        this.taulukko = taulukko;
        this.comparator = comparator;
        koko = taulukko.length;
        buildHeap();
    }

    /**
     * Lisää yhden elementin kekoon
     *
     * @param lisattava
     */
    public void lisaa(E lisattava) {
        if (taulukko.length == koko + 1) {
            kasvataTaulukko();
        }
        koko++;
        taulukko[koko] = lisattava;
        siftUp(koko);

    }

    /**
     * Tuplaa taulukon koon.
     */
    private void kasvataTaulukko() {
        E[] vanha = taulukko;
        taulukko = (E[]) new Object[taulukko.length * 2];
        System.arraycopy(vanha, 0, taulukko, 0, vanha.length);
    }

    /**
     * Poistaa keon ensimmäisen elementin
     *
     * @return
     */
    public E poista() {
        if (koko < 1) {
            return null;
        }
        E paluu = taulukko[1];
        Taulukko.swap(taulukko, 1, koko);
        koko--;
        siftDown(1);
        return paluu;

    }

    /**
     * Binaarikeon siftup rekursiivisesti.
     *
     * @param indeksi
     */
    private void siftUp(int indeksi) {
        if (indeksi > 1) {
            if (comparator.compare(taulukko[parent(indeksi)], taulukko[indeksi]) > 0) {
                Taulukko.swap(taulukko, parent(indeksi), indeksi);
                siftUp(parent(indeksi));
            }
        }
    }

    /**
     * Binaarikeon siftDown rekursiivisesti
     *
     * @param indeksi
     */
    private void siftDown(int indeksi) {
        final int kokoMiinusLeft = koko - left(indeksi);
        int valittu = indeksi;
        if (kokoMiinusLeft >= 0) {
            if (comparator.compare(taulukko[left(indeksi)], taulukko[valittu]) < 0) {
                valittu = left(indeksi);
            }
            if (kokoMiinusLeft > 0) {
                if (comparator.compare(taulukko[left(indeksi) + 1], taulukko[valittu]) < 0) {
                    valittu = left(indeksi) + 1;
                }
            }
            if (valittu != indeksi) {
                Taulukko.swap(taulukko, indeksi, valittu);
                siftDown(valittu);
            }
        }

    }

    /**
     *
     * @param i
     * @return
     */
    private int parent(int i) {
        if (i < 2) {
            return 0;
        }
        return i >> 1;
    }

    /**
     * Vasen lapsi. Ei testaa onko se olemassa.
     *
     * @param i
     * @return
     */
    private int left(int i) {
        int a = i << 1;
        return a;
    }

    /**
     *
     * @param i
     * @return
     */
    private int right(int i) {
        final int left = left(i);
        return left + 1;
    }

    /**
     *
     */
    private void buildHeap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
