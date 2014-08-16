/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author mikko
 */
public class PrioriteettiKeko<E> {

    //private Object[] taulukko;
    private int[] taulukko;
    private int koko;
    private int temp;
    
    
    public PrioriteettiKeko() {
        //his.taulukko = new Object[100];
        this.taulukko = new int[100];
    }
    
    public int seuraava() {
        if (koko == 0) {
            return -1;
        }
        int max = taulukko[0];
        taulukko[0] = taulukko[koko-1];
        koko--;
        heapify(0);
        return max;
    }
    
    public void lisaa(int luku) {
        koko++;
        int i = koko-1;
        while (i > 0 && luku > taulukko[vanhempi(i)]) {
            taulukko[i] = taulukko[vanhempi(i)];
            i = vanhempi(i);
        }
        taulukko[i] = luku;
    }


    private int vanhempi(int indeksi) {
        return indeksi / 2;
    }

    private int vasenLapsi(int indeksi) {
        return 2 * indeksi;
    }

    private int oikeaLapsi(int indeksi) {
        return 2 * indeksi + 1;
    }
    
    private void heapify(int i) {
        int right = oikeaLapsi(i);
        int left = vasenLapsi(i);
        if (right <= koko) {
            int largest;
            if (taulukko[left] > taulukko[right]) {
                largest = left;
            } else {
                largest = right;
            }
            if (taulukko[i] < taulukko[largest]) {
                temp = taulukko[i];
                taulukko[i] = taulukko[largest];
                taulukko[largest] = temp;
                heapify(largest);
            } 
        } else if (left == koko && taulukko[i] < taulukko[left]) {
            temp = taulukko[i];
            taulukko[i] = taulukko[left];
            taulukko[left] = temp;
        }
    }

}
