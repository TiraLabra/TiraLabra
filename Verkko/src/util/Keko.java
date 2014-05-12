
package util;

import java.util.Collection;
import java.util.Comparator;

/**
 * Binäärikeko, keon huippu kohdassa 1.
 * @author Arvoitusmies
 * @param <E> Luokka jota laitetaan kekoon
 */
public class Keko<E> {

    private E[] taulukko;
    private static final int ALKUKOKO = 10;
    private int koko;
    private Comparator<E> comparator;

    /**
     * Lisää yhden elementin kekoon
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

    private void kasvataTaulukko() {
        E[] vanha = taulukko;
        taulukko = (E[]) new Object[taulukko.length * 2];
        System.arraycopy(vanha, 0, taulukko, 0, vanha.length);
    }

    /**
     * Poistaa keon ensimmäisen elementin
     * @return 
     */
    public E poista() {
        if (koko < 1) {
            return null;
        }
        E paluu = taulukko[1];
        swap(1, koko);
        siftDown(1);
        koko--;
        return paluu;

    }

    private void siftUp(int indeksi) {
        if (comparator.compare(taulukko[parent(indeksi)], taulukko[indeksi]) > 0
                && indeksi > 1) {
            swap(parent(indeksi), indeksi);
            siftUp(parent(indeksi));
        }
    }

    private void siftDown(int indeksi) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Uusi keko collectionista
     * @param collection
     * @param comparator
     */
    public Keko(Collection<E> collection, Comparator<E> comparator) {
        taulukko = (E[]) collection.toArray();
        this.comparator = comparator;
        koko = collection.size();
    }

    /**
     * Uusi tyhjä keko
     * @param comparator
     */
    public Keko(Comparator<E> comparator) {
        taulukko = (E[]) new Object[ALKUKOKO];
        koko = 0;
        this.comparator = comparator;
    }

    /**
     * Uusi keko taulukosta
     * @param taulukko
     * @param comparator
     */
    public Keko(E[] taulukko, Comparator<E> comparator) {
        this.taulukko = taulukko;
        this.comparator = comparator;
        koko = taulukko.length;
    }

    private void swap(int a, int b) {
        E temp = taulukko[a];
        taulukko[a] = taulukko[b];
        taulukko[b] = temp;
    }

    private int parent(int i) {
        if (i < 2) {
            return 0;
        }
        return i >> 1;
    }
    
    private int left(int i){
        int a = i<<1;
        if(a>koko){
            return 0;
        }
        return a;
    }
    private int right(int i){
        final int left = left(i);
        if(left==0){
            return 0;
        }
        return left+1;
    }
}
