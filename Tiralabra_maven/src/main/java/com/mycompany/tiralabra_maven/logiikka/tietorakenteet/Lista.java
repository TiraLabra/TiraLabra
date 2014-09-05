/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.tietorakenteet;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author mikko
 * @param <E>
 */
public class Lista<E> implements Iterable<E> {

    private Object[] taulukko;
    private int taulukonKoko;
    private int koko;

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

    public int size() {
        return koko;
    }

    public boolean isEmpty() {
        return koko == 0;
    }

    public boolean contains(Object o) {
        E otus;
        try {
            otus = (E) o;
        } catch (Exception e) {
            return false;
        }
        for (int i = 0; i<koko; i++) {
            if (((E) taulukko[i]).equals(otus)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Sormi();
    }

    public void add(E lisattava) {

        if (this.koko == this.taulukonKoko) {
            kasvataTaulukkoa();
        }
        this.taulukko[koko] = lisattava;
        koko++;
    }
    
    public E get(int i) {
        return (E)(taulukko[i]);
    }

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
