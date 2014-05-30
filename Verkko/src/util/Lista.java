/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

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
    @SuppressWarnings("unchecked")
    public Lista(int alkukoko) {
        this.a = (E[]) new Object[alkukoko];
        koko = 0;
    }

    public int koko() {
        return koko;
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
    @SuppressWarnings("unchecked")
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
        tsekkaaIndeksi(indeksi);
        return a[indeksi];
    }

    /**
     * Muuttaa indeksissä olevan arvon toiseksi.
     *
     * @param indeksi
     * @param uusiArvo
     */
    public void muuta(int indeksi, E uusiArvo) {
        tsekkaaIndeksi(indeksi);
        a[indeksi] = uusiArvo;
    }

    private void tsekkaaIndeksi(int indeksi) throws IllegalArgumentException {
        if (indeksi >= koko || indeksi < 0) {
            throw new IllegalArgumentException("Indeksi listan ulkopuolella");
        }
    }

    /**
     *
     * @param o
     * @return true jos listalta löytyy equals o, muutoin false.
     */
    public boolean contains(E o) {
        if (o != null) {
            for (E e : a) {
                if (o.equals(e)) {
                    return true;
                }
            }
        } else {
            for (E e : a) {
                if (o == e) {
                    return true;
                }
            }
        }
        return false;
    }
}
