/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.tietorakenteet;

import java.util.Comparator;

/**
 * PriorityQueuen toiminnan korvaava keko
 *
 * @author sasumaki
 * @param <E>
 */
public class PrioKeko<E> {

    private int size;
    private Object[] heap;
    private Object temporary;

    private final Comparator<? super E> comparator;

    public PrioKeko(Comparator comparator) {
        this.comparator = comparator;
        this.size = 0;
        this.heap = new Object[11];
    }

    public PrioKeko() {
        this(null);
    }

    /**
     * lisää kekoon tavaraa ylläpitäen keko-ominaisuutta
     *
     * @param lisays
     */
    public void heapInsert(Object lisays) {

        if (size == heap.length) {
            Object[] uusiheap = new Object[2 * size];
            System.arraycopy(heap, 0, uusiheap, 0, size);

            heap = uusiheap;
        }

        int i = size;
        heap[i] = lisays;

        while (i > 1 && suurempi(i, parent(i)) == i) {
            vaihda(i, parent(i));
            i = parent(i);
        }
        heap[i] = lisays;

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ottaaa keosta ensimmäisen alkion (suurin prioriteetti) ja poistaa sen
     * keosta ylläpitäen keko-ominaisuuden
     *
     * @return
     */
    public E pullDelete() {
        if (isEmpty()) {
            return null;
        }
        E best = (E) (heap[0]);
        heap[0] = heap[size - 1];
        size--;

        if (size > 0) {
            heapify(0);
        }

        return best;
    }

    /**
     * kekonavigointia varten luotu
     *
     * @param i
     * @return
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * kekonavigointia varten luotu
     *
     * @param i
     * @return
     */
    private int left(int i) {
        return 2 * i;
    }

    /**
     * kekonavigointia varten luotu
     *
     * @param i
     * @return
     */
    private int right(int i) {
        return (2 * i) + 1;
    }

    /**
     * "Valuttaa" alkioita keossa keko-ominaisuuden korjaamiseksi.
     *
     * @param i
     */
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        if (r < size) {
            int largest = suurempi(l, r);

            if (suurempi(largest, i) == largest) {
                vaihda(i, largest);
                heapify(largest);
            }
        } else if (l == size - 1 && suurempi(l, i) == l) {
            vaihda(i, l);
        }
    }

    /**
     * palauttaa suuremman prioriteetin heuristisen haun arviolla
     *
     * @param a
     * @param b
     * @return
     */
    private int suurempi(int a, int b) {
        if (comparator == null) {
            Comparable<? super E> eka = (Comparable<? super E>) heap[a];
            E toka = (E) heap[b];
            if (eka.compareTo(toka) < 0) {
                return a;
            }

            return b;
        } else {
            if (comparator.compare((E) heap[a], (E) heap[b]) < 0) {
                return a;
            }
            return b;
        }
    }

    /**
     * vaihtaa alkioiden paikkaa keossa-
     *
     * @param a
     * @param b
     */
    private void vaihda(int a, int b) {
        this.temporary = heap[a];
        heap[a] = heap[b];
        heap[b] = this.temporary;
    }

}
