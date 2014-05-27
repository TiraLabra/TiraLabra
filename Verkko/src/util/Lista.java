/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

/**
 * Tällänen listarakenne josta ei voi poistaa (koska sellaista toiminnalisuutta
 * en tarvitse). Auromaattisesti kasvava.
 *
 * @author Arvoitusmies
 * @param <E>
 */
public class Lista<E> {

    /**
     * Defaultti alkukoko
     */
    private static final int ALKUKOKO = 10;

    /**
     * Sisäinen taulukko alkioille
     */
    private E[] a;

    /**
     * Talletettujen olioiden määrä
     */
    private int koko;

    /**
     * Alustaa defaulttialkukoolla.
     */
    public Lista() {
        this(ALKUKOKO);
    }

    /**
     * Alustaa annetulla alkukoolla
     *
     * @param alkukoko
     */
    public Lista(int alkukoko) {
        this.a = (E[]) new Object[alkukoko];
        koko = 0;
    }

    /**
     * Lisää listan päähän
     *
     * @param lisattava
     */
    public void lisaa(E lisattava) {
        if (a.length == koko) {
            kasvataTaulukko();
        }
        a[koko] = lisattava;
        koko++;
    }

    /**
     * Tuplaa sisäisen taulukon koon.
     */
    private void kasvataTaulukko() {
        E[] vanha = a;
        a = (E[]) new Object[a.length * 2];
        System.arraycopy(vanha, 0, a, 0, vanha.length);
    }

    /**
     *
     * @param indeksi
     * @return Alkio kohdassa indeksi
     */
    public E get(int indeksi) {
        if (indeksi >= koko || indeksi < 0) {
            throw new IllegalArgumentException("Indeksi listan ulkopuolella");
        }
        return a[indeksi];
    }

    /**
     * Muuttaa indeksissä olevan arvon toiseksi.
     *
     * @param indeksi
     * @param uusiArvo
     */
    public void muuta(int indeksi, E uusiArvo) {
        if (indeksi >= koko || indeksi < 0) {
            throw new IllegalArgumentException("Indeksi listan ulkopuolella");
        }
        a[indeksi] = uusiArvo;
    }

    /**
     *
     * @param o
     * @return true jos listalta löytyy equals o, muutoin false.
     */
    public boolean onxNagyny(E o) {
        for (int i = 0; i < koko; i++) {
            if (o.equals(a[i])) {
                return true;
            }
        }
        return false;
    }
}
