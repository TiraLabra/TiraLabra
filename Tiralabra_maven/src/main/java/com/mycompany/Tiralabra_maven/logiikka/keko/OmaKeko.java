package com.mycompany.Tiralabra_maven.logiikka.keko;

import java.lang.reflect.Array;

/**
 * KESKENERÄINEN LUOKKA Oman tietorakenteen (minimikeko) toteutus. Keon alkiot
 * tyyppiä E.
 */
public class OmaKeko<E> implements MinKeko<E> {

    private E[] kekoTaulukko;
//    private E[] olioTaulukko;
//    private int[] kekoTaulukko;
    private int heapSize;

    public OmaKeko(Class<E> luokka) {
        // Use Array native method to create array of a type only known at run time
        int taulukonKoko = 1000000;
        @SuppressWarnings("unchecked")
        final E[] uusiTaulukko = (E[]) Array.newInstance(luokka, taulukonKoko);
        this.kekoTaulukko = uusiTaulukko;
        this.heapSize = 0;
    }

    public E getTest(int i) {
        return kekoTaulukko[i];
    }

    public void setTest(E kekoAlkio, int i) {
        kekoTaulukko[i] = kekoAlkio;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void heapHeapify(int i) {

        int left;
        int right;
        int largest;

        ////heapify(A,i)
////////E left = this.kekoTaulukko[left(i)];
////////E right = this.kekoTaulukko[right(i)];
        left = left(i);
        right = right(i);
//        if (right <= this.heapSize) {
//            if (this.kekoTaulukko[left].compareTo(this.kekoTaulukko[right])>0) {
//                largest = left;
//            } else {
//                largest = right;
//            }
//                    //6 if A[i] < A[largest]
//                    //7 vaihda A[i] ja A[largest]
//                    //8 heapify(A,largest)
//
//        }
//9 elsif l == A.heap-size and A[i]<A[l]
//10 vaihda A[i] ja A[l]        
    }

    @Override
    public void heapInsert(E kekoAlkio) {
////heap-insert(A,k)
//1 A.heap-size = A.heap-size+1
//2 i = A.heap-size
//3 while i>1 and A[parent(i)] < k
//4 A[i] = A[parent(i)]
//5 i = parent(i)
//6 A[i] = k

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E heapDelMin() {
        E min = this.kekoTaulukko[1];
        this.kekoTaulukko[1] = this.kekoTaulukko[this.heapSize];
        this.heapSize--;
        this.heapHeapify(1);
        return min;
    }

    @Override
    public void heapDelete(E kekoAlkio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void heapDecreaseKey(E kekoAlkio) {
////heap-dec-key(A,i,newk)
//1 if newk < A[i]
//2 A[i] = newk
//3 heapify(A,i)

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean heapIsEmpty() {
        if (this.heapSize == 0) {
            return true;
        }
        return false;
    }
}
