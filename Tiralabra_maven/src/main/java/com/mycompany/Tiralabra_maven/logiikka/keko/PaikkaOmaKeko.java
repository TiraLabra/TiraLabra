// KESKENERÄINEN LUOKKA
package com.mycompany.Tiralabra_maven.logiikka.keko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * KESKENERÄINEN LUOKKA
 * Oman tietorakenteen (minimikeko) toteutus.
 * Keon alkiot tyyppiä Paikka.
 */
public class PaikkaOmaKeko implements PaikkaMinKeko{

    private Paikka[] kekoTaulukko; //HUOM. kekotaulukko alkaa indeksistä 1 !!!
    private int heapSize;

    public PaikkaOmaKeko() {
        this.kekoTaulukko = new Paikka[1000000];
        this.heapSize = 0;
    }

    private void heapHeapify(int i) {
////heapify(A,i)
//1 l = left(i)
//2 r = right(i)
//3 if r ≤ A.heap-size
//4 if A[l] > A[r] largest = l
//5 else largest = r
//6 if A[i] < A[largest]
//7 vaihda A[i] ja A[largest]
//8 heapify(A,largest)
//9 elsif l == A.heap-size and A[i]<A[l]
//10 vaihda A[i] ja A[l]        
    }

    @Override
    public void heapInsert(Paikka kekoAlkio) {

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
    public Paikka heapDelMin() {
        Paikka min = this.kekoTaulukko[1];
        this.kekoTaulukko[1] = this.kekoTaulukko[this.heapSize];
        this.heapSize--;
        this.heapHeapify(1);
        return min;
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void heapDelete(Paikka kekoAlkio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void heapDecreaseKey(Paikka kekoAlkio) {

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
