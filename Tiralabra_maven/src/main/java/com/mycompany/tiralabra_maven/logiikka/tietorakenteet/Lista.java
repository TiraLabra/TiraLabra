package com.mycompany.tiralabra_maven.logiikka.tietorakenteet;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dynaamisesti kokoaan kasvattava lista. Listaan voi lisätä elementtejä,
 * elementtejä voidaan noutaa ja poistaa indeksin perusteella ja niiden läpi
 * voidaan iteroida iteraattorin avulla.
 *
 * @author mikko
 * @param <E>
 */
public class Lista<E> implements Iterable<E> {

    private Object[] taulukko;
    private int taulukonKoko;
    private int koko;

    /**
     * Luo uuden tyhjän listan.
     */
    public Lista() {
        this.taulukonKoko = 11;
        this.taulukko = new Object[taulukonKoko];
        this.koko = 0;
    }

    private void kasvataTaulukkoa() {
        Object[] uusiTaulukko = new Object[taulukonKoko * 2];
        System.arraycopy(taulukko, 0, uusiTaulukko, 0, taulukonKoko);
        this.taulukko = uusiTaulukko;
        this.taulukonKoko = taulukonKoko * 2;
    }

    /**
     * Palauttaa tiedon siitä, montako alkiota listaan on talletettu.
     *
     * @return alkioiden lkm
     */
    public int size() {
        return koko;
    }

    /**
     * Palauttaa tiedon siitä, onko lista tyhjä.
     *
     * @return onko lista tyhjä vai eikö ole.
     */
    public boolean isEmpty() {
        return koko == 0;
    }

    /**
     * Palauttaa tiedon siitä, sisältääkö lista parametrina annetun olion.
     *
     * @param o
     * @return onko olio listassa vai eikö ole
     */
    public boolean contains(Object o) {
        E otus;
        try {
            otus = (E) o;
        } catch (Exception e) {
            return false;
        }
        for (int i = 0; i < koko; i++) {
            if (((E) taulukko[i]).equals(otus)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa iteraattorin, jonka avulla listaa voidaan käydä läpi.
     *
     * @return iteraattori
     */
    @Override
    public Iterator<E> iterator() {
        return new Sormi();
    }

    /**
     * Lisää listan perälle alkion.
     *
     * @param lisattava
     */
    public void add(E lisattava) {

        if (this.koko == this.taulukonKoko) {
            kasvataTaulukkoa();
        }
        this.taulukko[koko] = lisattava;
        koko++;
    }

    /**
     * Palauttaa listasta alkion indeksin perusteella.
     *
     * @param i
     * @return alkio numero i
     */
    public E get(int i) {
        return (E) (taulukko[i]);
    }

    /**
     * Poistaa listasta alkion indeksin perusteella. Poistettavan alkion
     * jälkeisten alkioiden indeksit pienenevät samalla yhdellä.
     *
     * @param i
     */
    public void remove(int i) {
        //siirretään taulukon loppu-osaa yhdellä vasemmalle
        int siirrettavia = koko - i - 1;
        if (siirrettavia > 0) {
            System.arraycopy(taulukko, i + 1, taulukko, i, siirrettavia);
        }
        koko--;
        taulukko[koko] = null; //roskienkerääjä kerää poistetun pois
    }

    private class Sormi implements Iterator<E> {

        private int indeksi;
        private int viimeksiPalautettu;

        @Override
        public boolean hasNext() {
            return indeksi < koko;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) taulukko[viimeksiPalautettu = indeksi++];

        }

        @Override
        public void remove() {
            Lista.this.remove(viimeksiPalautettu);
            indeksi = viimeksiPalautettu;
            viimeksiPalautettu = -1;
        }

    }

}
