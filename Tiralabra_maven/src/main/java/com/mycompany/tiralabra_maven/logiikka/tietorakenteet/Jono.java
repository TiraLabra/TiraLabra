package com.mycompany.tiralabra_maven.logiikka.tietorakenteet;

/**
 *
 * @author mikko
 * @param <E> talletettavien elementtien tyyppi
 */
public class Jono<E> {

    private Object[] taulukko;
    private int taulukonKoko;
    private int head;
    private int tail;

    /**
     * Luo uuden tyhjän jonon.
     */
    public Jono() {
        taulukonKoko = 11;
        taulukko = new Object[taulukonKoko];
        head = 0;
        tail = 0;
    }

    /**
     * Lisää jonon perälle elementin.
     * @param lisattava 
     */
    public void lisaa(E lisattava) {
        if (taysi()) {
            kasvataTaulukkoa();
        }
        taulukko[tail] = lisattava;
        tail = seuraavaPaikka(tail);
    }

    /**
     * Kertoo onko jono tyhjä.
     * @return onko jono tyhjä vai eikö ole
     */
    public boolean tyhja() {
        return head == tail;
    }

    private boolean taysi() {
        return seuraavaPaikka(tail) == head;
    }

    /**
     * Palauttaa jonosta seuraavan alkion.
     * @return seuraava
     */
    public E otaJonosta() {
        E palautus = (E) taulukko[head];
        head = seuraavaPaikka(head);
        return palautus;
    }

    private int seuraavaPaikka(int n) {
        return (n + 1) % taulukonKoko;
    }
    
    /**
     * Palauttaa tiedon siitä, montako elementtiä jonoon on talletettu.
     * @return koko
     */
    public int koko() {
        return (tail-head+taulukonKoko)%taulukonKoko;
    }
    
    private void kasvataTaulukkoa() {
        //tehdään uusi taulukko joka on kaksi kertaa vanhan kokoinen, alussa head ja tail 0
        Object[] uusiTaulukko = new Object[taulukonKoko*2];
        int uusiHead = 0;
        int uusiTail = 0;
        //niin kauan kuin vanhassa taulukossa riittää tavaraa (ei ole tyhjä), otetaan sieltä tavaraa pois ja siirretään uuteen
        while (!tyhja()) {
            uusiTaulukko[uusiTail] = taulukko[head];
            uusiTail++;
            head = seuraavaPaikka(head);
        }
        //lopuksi korvataan vanhat head, tail, taulukko ja taulukonKoko uusilla
        head = uusiHead;
        tail = uusiTail;
        taulukko=uusiTaulukko;
        taulukonKoko = taulukonKoko*2;
    }

}
