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
    private int taulunKoko;
    /**
     * Taulun avainten lukumäärä
     */
    private int koko;

    /**
     * Tallennetaan arvo-avain -parit, pari on samalla yhteen suuntaan
     * linkitetty lista
     */
    private Pari<K, V>[] taulu;

    /**
     * Pidetään kirjaa törmäysten määrästä: saadaan tietoa toimintanopeudesta &
     * uudellenhajautuksen tarpeesta
     */
    private int tormaykset;

    /**
     * Pidetään kirjaa täyttöasteesta: kuinka moni taulun indekseistä sisältään
     * vähintään yhden avain-arvo -parin
     */
    private int tayttoAste;

    /**
     * Pidetäään kirjaa siitä jos taulukon arvo korvataan
     */
    private int korvaukset;

    /**
     * Pidetään kirjaa poistettujen arvojen lukumäärästä
     */
    private int poistot;

    /**
     * Pidetään kirjaa uudelleenhajautusten lukumäärästä
     */
    private int uudelleenHajautukset;

    ///////////////////
    // KONSTRUKTORIT //
    ///////////////////
    public Hajautustaulu() {
        this(DEFAULTSIZE);
    }

    public Hajautustaulu(int aloitusKoko) {
        taulunKoko = aloitusKoko;
        if (taulunKoko <= 1) {
            taulunKoko = DEFAULTSIZE;
        }
        koko = 0;
        tormaykset = 0;
        tayttoAste = 0;
        poistot = 0;
        uudelleenHajautukset = 0;
        taulu = new Pari[taulunKoko];
    }

    ////////////////////////
    // JULKISET   METODIT //
    ////////////////////////
    /**
     * Asettaa tauluun uuden avain-arvo parin
     *
     * @param k Avain
     * @param v Arvo
     * @return Jos avain oli jo taulussa, palautetaan edellinen arvo
     */
    public V put(K k, V v) {
        int key = this.hashKey(k);
        Pari pari = new Pari(k, v);
        V value = null; // edellinen arvo tällä avaimella?
        if (this.taulu[key] == null) {
            this.taulu[key] = pari;
            tayttoAste++;
            this.koko++;
        } else {
            value = (V) this.taulu[key].replace(pari);
            if (value == null) { // kyseessä on kyseessä törmäys (ei korvata)
                this.tormaykset++;
                this.koko++;
            } else { // muutoin korvataan olemassaoleva arvo
                this.korvaukset++;
            }
        }
        return value;
    }

    /**
     * Onko avain taulussa
     *
     * @param k Avain
     * @return
     */
    public boolean contains(K k) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            return false;
        }

        return this.taulu[key].contains(k);

    }

    public boolean containsKey( K k ) {
        return contains(k);
    }
    /**
     * Palauttaa avaimen arvon
     *
     * @param k Avain
     * @return Arvo
     */
    public V get(K k) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            return null;
        }

        V arvo = (V) this.taulu[key].get(k);
        return arvo;
    }

    /**
     * Poistaa avaimella löytyvän arvon
     *
     * @param k Poistettava avain
     * @return Avaimen arvo
     */
    public V remove(K k) {
        int key = this.hashKey(k);
        if (this.taulu[key] == null) {
            return null;
        }
        V v = this.taulu[key].get(k);
        if (v != null) {
            this.taulu[key] = this.taulu[key].remove(k);
            if (this.taulu[key] == null) {
                this.tayttoAste--;
            } else {
                this.tormaykset--;
            }
            this.koko--;
        }
        return v;
    }

    /**
     * Palauttaa listan avaimista
     *
     * @return Taulun avaimet
     */
    public Lista<K> keySet() {
        Lista<K> lista = new DynaaminenLista();
        for (int i = 0; i < this.taulunKoko; i++) {
            Pari p = this.taulu[i];
            while (p != null) {
                lista.add((K) p.getK());
                p = p.getNext();
            }
        }
        return lista;
    }

    /**
     * Palauttaa lista taulun arvoista
     *
     * @return Taulun arvot
     */
    public Lista<V> values() {
        Lista<V> lista = new DynaaminenLista();
        for (int i = 0; i < this.taulunKoko; i++) {
            Pari p = this.taulu[i];
            while (p != null) {
                lista.add((V) p.getV());
                p = p.getNext();
            }
        }
        return lista;
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
        // loadFactor
        // WIP
        this.uudelleenHajautukset++;
    }

    /**
     * Taulun hajautusfunktio
     *
     * @param k Hajautettava avain
     * @return Taulun indeksi
     */
    private int hashKey(K k) {
        // WIP tänne jotain fiksumpaa
        // int p = 763; // h=k%p%n
        int i = k.hashCode()  % this.taulunKoko;
        // System.out.println(""+i+"="+k.hashCode()+" mod "+this.maksimiKoko);
        if (i < 0) {
            i = -i; // negatiiviset hashCodet käsitellään näin
        }
        return i; // h(k)=hash(k)%n
    }

    //////////////////////
    // DEBUG YMS  ////////
    //////////////////////
    /**
     * Tulostaa & palauttaa tietoa toiminnasta
     *
     * @return Tietoja merkkijonossa
     */
    public String debugPrint() {
        String s = "Hajautustaulu{"
                + "maksimiKoko=" + taulunKoko
                + ", koko=" + koko
                + ", tormaykset=" + tormaykset
                + ", tayttoAste=" + tayttoAste
                + ", korvaukset=" + korvaukset
                + ", poistot=" + poistot
                + ", uudelleenHajautukset=" + uudelleenHajautukset
                + ", "+this.debugTormaysListat()
                + '}';
        System.out.println("" + s);
        // System.out.println("CONTENTS={");
        // System.out.println(debugContents()+"}");
        
        return s;
    }
    /**
     * Tietoja törmäyksistä
     * 
     * @return Merkkijonoesitys törmäyksistä
     */
    public String debugTormaysListat() {
        if (this.isEmpty()) {
            return "";
        }
        double max = 0;
        double sum = 0;
        double n = 0;
        for (Pari p : this.taulu) {
            if (p == null) {
                continue;
            }
            double s = p.size();
            if (s > max) {
                max = s;
            }
            sum += s;
            n++;
        }
        String s = "tormaysLista{"
                + ", keskimaarainenKoko=" + (sum / n)
                + ", suurinKoko=" + max
                + "}";
        return s;
    }
    /**
     * Merkkijonoesitys hajautustaulun sisällöstä
     * 
     * @return 
     */
    public String debugContents() {
        String s = "";
        
        for ( Pari p : taulu ) {
            s+=""+p+"\n";
        }
        
        return s;
    }

}
