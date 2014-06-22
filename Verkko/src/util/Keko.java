/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Arvoitusmies
 * @param <E>
 */
public class Keko<E> {

    /**
     * Defaultti alkukoko sisäiselle taulukolle jota kasvatetaan tarvittaessa
     */
    protected static final int ALKUKOKO = 10;

    /**
     * Kuinkakertaiseksi kasvatetaan taulukko kun tila loppuu
     */
    protected static final int KASVUKERROIN = 2;

    /**
     * Taulu olioille. Kasvatetaan tarvittaessa
     */
    protected E[] taulukko;

    /**
     * Olioiden määrä keossa, samalla myös keon alimman indeksi.
     */
    private int koko;

    /**
     * Comparator jota käytetään järjestämään kekoa.
     */
    private final Comparator<E> comparator;

    /**
     * Tallettaa solmujen indeksejä jotta muuta(E,E), poista(E) ja contains(E)
     * olisivat nopeampia
     */
    private final Map<E, Integer> indeksit;

    /**
     * Uusi tyhjä keko
     *
     * @param comparator
     */
    @SuppressWarnings("unchecked")
    public Keko(Comparator<E> comparator) {
        taulukko = (E[]) new Object[ALKUKOKO];
        koko = 0;
        this.comparator = comparator;
        indeksit = new HashMap<>();
    }

    /**
     * Uusi keko taulukosta
     *
     * @param taulukko
     * @param comparator
     */
    public Keko(E[] taulukko, Comparator<E> comparator) {
        koko = taulukko.length - 1;
        this.taulukko = taulukko.clone();
        this.comparator = comparator;
        tarkistaOnkoNollaNull();//tämä siksi että jos ei ole null niin taulukon ensimäinen "katoaa" keosta.
        buildHeap();
        indeksit = new HashMap<>();
        for (int i = 1; i <= koko; i++) {
            indeksit.put(this.taulukko[i], i);
        }
    }

    /**
     * getteri
     *
     * @return
     */
    public int getKoko() {
        return koko;
    }

    /**
     * true jos tyhjä.
     *
     * @return
     */
    public boolean isEmpty() {
        return koko <= 0;
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
        indeksit.put(lisattava, koko);
        siftUp(koko);

    }

    /**
     * Tuplaa taulukon koon.
     */
    @SuppressWarnings({"unchecked"})
    protected void kasvataTaulukko() {
        E[] vanha = taulukko;
        taulukko = (E[]) new Object[taulukko.length * KASVUKERROIN];
        System.arraycopy(vanha, 0, taulukko, 0, vanha.length);
    }

    /**
     * getteri
     *
     * @return
     */
    public int getTaulukonLenght() {
        return taulukko.length;
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
        indeksit.remove(paluu);
        Taulukko.swap(taulukko, 1, koko);
        paivitaIndeksi(1);
        koko--;
        siftDown(1);
        return paluu;

    }

    /**
     * Päivittää annetun indeksin indeksit-hajautustauluun.
     * @param i
     */
    private void paivitaIndeksi(int i) {
        indeksit.put(taulukko[i], i);
    }

    /**
     * Binaarikeon siftup rekursiivisesti.
     *
     * @param indeksi
     */
    protected void siftUp(int indeksi) {
        if (indeksi > 1) {
            final int parent = parent(indeksi);
            if (comparator.compare(taulukko[parent], taulukko[indeksi]) > 0) {
                Taulukko.swap(taulukko, parent, indeksi);
                paivitaIndeksi(parent);
                paivitaIndeksi(indeksi);
                siftUp(parent);

            }
        }
    }

    /**
     * Binaarikeon siftDown rekursiivisesti
     *
     * @param indeksi
     */
    protected void siftDown(int indeksi) {
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
                paivitaIndeksi(indeksi);
                paivitaIndeksi(valittu);
                siftDown(valittu);
            }
        }

    }

    /**
     * Indeksin i vanhempi
     *
     * @param i
     * @return
     */
    protected int parent(int i) {
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
    protected int left(int i) {
        int a = i << 1;
        return a;
    }

    /**
     * True jos löytyy keossa
     *
     * @param o
     * @return
     */
    public boolean contains(E o) {
//        for (int i = 1; i <= koko; i++) {
//            if (taulukko[i].equals(o)) {
//                return true;
//            }
//        }
//        return false;
        return indeksit.containsKey(o);
    }

    /**
     * kutsutaan siftdown kaikille paitsi lehdille.
     */
    protected void buildHeap() {
        for (int i = parent(koko); i > 0; i--) {
            siftDown(i);
        }
    }

    /**
     * Poistaa olion
     *
     * @param o
     * @return
     */
    public E poista(E o) {

//        E paluu = null;
//        for (int i = 1; i <= koko; i++) {
//            if (taulukko[i].equals(o)) {
//                Taulukko.swap(taulukko, i, koko);
//                paluu = taulukko[koko];
//                koko--;
//                siftDown(i);
//                break;
//            }
//        }
//        return paluu;
        final Integer i = indeksit.get(o);
        E paluu = null;
        if (i != null) {
            paluu = taulukko[i];
            Taulukko.swap(taulukko, i, koko);
            indeksit.remove(paluu);
            koko--;
            paivitaIndeksi(i);
            siftDown(i);
        }
        return paluu;
    }

    /**
     * Muuttaa solmun
     *
     * @param korvattava
     * @param uusi
     * @return 
     */
    public boolean muuta(E korvattava, E uusi) {
//        for (int i = 1; i <= koko; i++) {
//            if (taulukko[i].equals(korvattava)) {
//                taulukko[i] = uusi;
//                int parent = parent(i);
//                if (parent > 0) {
//                    if (comparator.compare(taulukko[parent], taulukko[i]) > 0) {
//                        siftUp(i);
//                    } else {
//                        siftDown(i);
//                    }
//                }
//                break;
//            }
//        }
        final Integer i = indeksit.get(korvattava);
        if (i == null) {
            return false;
        }
        taulukko[i] = uusi;
        int parent = parent(i);
        if (parent > 0) {
            if (comparator.compare(taulukko[parent], taulukko[i]) > 0) {
                siftUp(i);
            } else {
                siftDown(i);
            }
        }
        return true;

    }

    /**
     * jossei [0] ole null niin tehdään siitä null!
     */
    private void tarkistaOnkoNollaNull() {
        if (taulukko[0] != null) {
            koko++;
            kasvataTaulukko();
            taulukko[koko] = taulukko[0];
            paivitaIndeksi(koko);
            taulukko[0] = null;
            siftUp(koko);
        }
    }
}
