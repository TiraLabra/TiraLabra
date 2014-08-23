// KESKENERÄINEN LUOKKA
package com.mycompany.Tiralabra_maven.logiikka.paikkaKeko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * KESKENERÄINEN LUOKKA Oman tietorakenteen (minimikeko) toteutus. Keon alkiot
 * tyyppiä Paikka.
 */
public class PaikkaOmaKeko implements PaikkaMinKeko {

    private Paikka[] kekoTaulukko; //HUOM. kekotaulukko alkaa indeksistä 1 !!!
//    private int heapSize;
    public int heapSize;

    public PaikkaOmaKeko() {
        this.kekoTaulukko = new Paikka[1000000];
        this.heapSize = 0;
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

    private void vaihda(int i, int j) {
        Paikka apuPaikka;
        apuPaikka = this.kekoTaulukko[i];
        this.kekoTaulukko[i] = this.kekoTaulukko[j];
        this.kekoTaulukko[j] = apuPaikka;
    }
    
//    private void vaihda(Paikka paikka1, Paikka paikka2) {
//        Paikka apuPaikka;
//        apuPaikka = paikka1;
//        paikka1 = paikka2;
//        paikka2 = apuPaikka;
//    }

    private void heapHeapify(int i) {

        int left;
        int right;
        int largest;

        ////heapify(A,i)
        left = left(i);
        right = right(i);
        if (right <= this.heapSize) {
            if (this.kekoTaulukko[left].compareTo(this.kekoTaulukko[right]) > 0) {
                largest = left;
            } else {
                largest = right;
            }
            if (this.kekoTaulukko[i].compareTo(this.kekoTaulukko[largest]) < 0) {
//                vaihda(this.kekoTaulukko[i], this.kekoTaulukko[largest]);
                vaihda(i,largest);
                heapHeapify(largest);
            }
        } else if (left == this.heapSize && this.kekoTaulukko[i].compareTo(this.kekoTaulukko[left]) < 0) {
//            vaihda(this.kekoTaulukko[i], this.kekoTaulukko[left]);
            vaihda(i, left);
        }
    }

    @Override
    public void heapInsert(Paikka kekoAlkio) {
        int i;
        this.heapSize++;
        i = this.heapSize;
        if (i > 1) {
            while (i > 1 && this.kekoTaulukko[parent(i)].compareTo(kekoAlkio) < 0) {
                this.kekoTaulukko[i] = this.kekoTaulukko[parent(i)];
                i = parent(i);
                if (i == 1) {
                    break;
                }
            }
        }

        kekoAlkio.heapIndex = i;
        this.kekoTaulukko[i] = kekoAlkio;
        System.out.println("keon koko: "+this.heapSize);

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

    /**
     * Asettaa kekoalkion oikealle paikalle keossa. Algoritmeissa tätä metodia
     * kutsutaan VAIN kun Paikka.etaisyysAlkuun on muuttunut eli myös kekoalkion
     * avain on muuttunut.
     *
     * @param kekoAlkio oikealle paikalle asetettava kekoalkio
     */
    @Override
    public void heapDecreaseKey(Paikka kekoAlkio) {
        this.heapHeapify(kekoAlkio.heapIndex);
    }

    @Override
    public boolean heapIsEmpty() {
        if (this.heapSize == 0) {
            return true;
        }
        return false;
    }
}
