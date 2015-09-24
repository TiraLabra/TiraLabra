/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.Comparator;

/**
 *
 * @author sasumaki
 * @param <E>
 */
public class PrioKeko<E> {

    private int size;
    private int heapsize;
    private Object[] heap;
    private Object temporary;

    private final Comparator<? super E>comparator;

    public PrioKeko(Comparator comparator) {
        this.comparator = comparator;
        this.size = 10;
        this.heapsize = 0;
        this.heap = new Object[size];
    }

    public void heapInsert(Object lisays) {
        heapsize = +1;
        if (size == heapsize) {
            Object[] uusiheap = new Object[2 * heapsize];
            System.arraycopy(heap, 0, uusiheap, 0, heapsize);
            heapsize = 2 * heapsize;
            heap = uusiheap;
        }

        int i = heapsize;

        heap[i] = lisays;

        while (i > 1 && suurempi(i, parent(i)) == i) {
            vaihda(i, parent(i));
            i = parent(i);
        }
        heap[i] = lisays;
    }

    public boolean isEmpty() {
        return heapsize == 0;
    }

    public Object pull() {
        return null;
    }

    public Object pullDelete() {
        if (size == 0) {
            return null;
        }
        E best = (E) (heap[0]);
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        
        return best;
    }

    public int parent(int i) {
        return i / 2;
    }

    public int left(int i) {
        return 2 * i;
    }

    public int right(int i) {
        return (2 * i) + 1;
    }

    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        if (r <= heapsize) {
            int largest = suurempi(l, r);

            if (suurempi(largest, i) == largest) {
                vaihda(i, largest);
                heapify(largest);
            }
        } else if (l == size && suurempi(l, i) == l) {
            vaihda(i, l);
        }
    }

    private int suurempi(int a, int b) {
        if (comparator.compare((E) heap[a], (E) heap[b]) < 0) {
            return a;
        }
        return b;
    }

    public void vaihda(int a, int b) {
        this.temporary = heap[a];
        heap[a] = heap[b];
        heap[b] = this.temporary;
    }

}
