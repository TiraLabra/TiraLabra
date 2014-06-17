/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.structures;

/**
 * Tämä on implementaationi listasta, joka sisältää yksinkertaiset operaatiot
 * @author Sami
 */
public class List<E> {

    private int alkioita = 0;
    private int size = 50;
    private Object alkiot[];

    public List() {
        this.alkiot = new Object[this.size];
    }
/**
 * Tämä lisää listaan alkion, ja kasvattaa koka tarvittaessa
 * @param e 
 */
    public void add(E e) {
        if (this.alkioita == this.size) {
            lisaaTilaa();
        }
        this.alkiot[this.alkioita] = e;
        this.alkioita++;
    }
/**
 * 
 * Tämä kaksinkertaistaa listan pohjana olevan taulukon koon
 */
    private void lisaaTilaa() {
        this.size *= 2;
        this.alkiot = kopioi(this.alkiot, alkioita, this.size);
    }

    /**
     * Tämä kopioi taulukon sisältämät arvot uuteen tauluun
     * @param taulu Taulu josta kopioidaan
     * @param taulunPituus Luettavan taulukon 
     * @param koko Tulevan taulukon koko
     * @return Uusi taulukko, jonka koko on koko, ja sisältää taulun sisältämät alkiot
     */
    private Object[] kopioi(Object[] taulu, int taulunPituus, int koko) {
        Object[] taulu2 = new Object[koko];
        for (int i = 0; i < taulunPituus; i++) {
            taulu2[i] = taulu[i];
        }

        return taulu2;
    }

    /**
     * Palauttaa listan alkion paikasta i
     * @param i Indeksi, josta haetaan alkio
     * @return Indeksin i sisältämä alkio
     */
    public E get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("Indeksi: " + i + ", size: " + this.alkioita);
        }
        return (E) this.alkiot[i];
    }

    /**
     * Poistaa alkion indeksistä i, ja korjaa taulukon rakenteen tästä loppua kohti
     * @param i Poistettavan alkion indeksi
     */
    public void remove(int i) {
        this.alkiot[i] = null;
        
        for(int j = i+1; j < this.alkioita; j++){
            this.alkiot[j-1] = this.alkiot[j];
            this.alkiot[j] = null;
        }
        this.alkioita--;
    }
    
    /**
     * Tämä palauttaa listan koon
     *
     * @return Listan koko
     */
    public int size() {
        return this.alkioita;
    }

    /**
     * Tämä palauttaa true, jos lista on tyhjä, ja muutoin false
     *
     * @return Totuusarvo listan tyhjyydestä
     */
    public boolean empty() {
        return this.alkioita == 0;
    }

    
}
