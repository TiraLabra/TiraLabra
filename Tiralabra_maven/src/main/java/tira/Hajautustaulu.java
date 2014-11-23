/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Set;

/**
 *
 * @author E
 */
public class Hajautustaulu<K, V> {

    /**
     * Oletusmaksimikoko
     */
    public static final int DEFAULTSIZE = 100;
    /**
     * Oletuskasvatuskerroin
     */
    public static final int DEFAULTGROWFACTOR = 2;
    /**
     * Taulun koko
     */
    private int maksimiKoko;
    /**
     * Taulun avainten lukumäärä
     */
    private int koko;

    /**
     * Tallennetaan arvo-avain -parit linkitettyihin listoihin taulukkoon
     */
    private LinkitettyLista<Pari<K, V>>[] taulu;

    /**
     * Pidetään kirjaa törmäysten määrästä: saadaan tietoa toimintanopeudesta &
     * uudellenhajautuksen tarpeesta
     */
    private int tormaykset;
    
    /**
     * Pidetään kirjaa täyttöasteesta: kuinka moni taulun indekseistä sisältään vähintään yhden avain-arvo -parin
     */
    private int tayttoAste;

    // WIP keySet --> iteraatiota varten, values
    ///////////////////
    // KONSTRUKTORIT //
    ///////////////////
    public Hajautustaulu() {
        this(DEFAULTSIZE);
    }

    public Hajautustaulu(int aloitusKoko) {
        maksimiKoko = aloitusKoko;
        if (maksimiKoko <= 1) {
            maksimiKoko = DEFAULTSIZE;
        }
        koko = 0;
        // WIP keyset=new juttu; values=new juttu;
        tormaykset = 0;
        tayttoAste = 0;
        taulu = new LinkitettyLista[maksimiKoko];
    }

    ////////////////////////
    // JULKISET   METODIT //
    ////////////////////////
    public V put(K k, V v) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            this.taulu[key] = new LinkitettyLista(); // oletuskoko
            tayttoAste++;
        }
        if (!this.taulu[key].isEmpty()) { // jos listassa on jo arvo, on kyseessä törmäys
            this.tormaykset++;
        }
        Pari pari = new Pari(k, v);
        // tupla-arvoja? fiksumpi tapa tarkistaa?
        Pari alku = this.taulu[key].get(pari);
        V edellinenArvo = null;
        if (alku != null) {
            edellinenArvo = (V) alku.getV();
        } else {
            this.taulu[key].add(pari);
        }
        // ...
        this.taulu[key].add(pari);
        // WIP lisäys keySettiin, valuesiin myös?
        this.koko++;
        return edellinenArvo;
    }

    public boolean contains(K k) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            return false;
        }
        Pari pari = new Pari(k, null);
        return this.taulu[key].contains(pari);

    }
    /**
     * Palauttaa avaimen arvon
     * @param k Avain
     * @return Arvo
     */
    public V get(K k) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            return null;
        }
        Pari pari = new Pari(k, null);
        Pari arvo = this.taulu[key].get(pari);
        if (arvo == null) {
            return null;
        } else {
            return (V) arvo.getV();
        }
    }
    /**
     * Poistaa avaimella löytyvän arvon ja palauttaa sen
     * @param k Poistettava avain
     * @return Avaimen arvo
     */
    public V remove(K k) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            return null;
        }
        Pari pari = new Pari(k, null);
        Pari arvo = this.taulu[key].remove(pari); // jmm
        if (arvo == null) {
            return null;
        } else {
            this.koko--;
            return (V) arvo.getV();
        }
    }

    /**
     * Palauttaa listan avaimista
     *
     * @return Avaimet listassa
     */
    public Lista<K> keySet() {
        // WIP
        return null;
    }

    /**
     * Onko taulussa avaimia
     *
     * @return Onko listassa jäseniä
     */
    public boolean isEmpty() {
        return this.koko <= 0;
    }

    /**
     *
     * @return Avain-arvo -parien määrä
     */
    public int size() {
        return this.koko;
    }

    ////////////////////////
    // YKSITYISET METODIT //
    ////////////////////////

    /**
     * Uudelleenhajautus: jos törmäyksiä on tullut liikaa, luodaan uusi
     * hajatustaulu tämän taulun pareista
     *
     */
    private void rehash() {

    }

    /**
     * Taulun hajautusfunktio
     *
     * @param k Hajautettava avain
     * @return Taulun indeksi
     */
    private int hashKey(K k) {
        int i = k.hashCode() % this.maksimiKoko;
        // System.out.println(""+i+"="+k.hashCode()+" mod "+this.maksimiKoko);
        if ( i<0) i=-i; // negatiiviset hashCodet käsitellään näin
        return i; // h(k)=hash(k)%n
    }


}
