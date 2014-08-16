/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet.keko;

import tietorakenteett.DiskreettiSolmu;
import java.util.HashMap;

/**
 *
 *
 */
public class Keko {

    private int nykyinenkoko;
    private int maksimikoko;
    private Iteroitava[] taulukko;

    /**
     *
     */
    public Keko() {

    }

    /**
     *
     *
     * Asettaa Keolle taulukon ja sen viimeisen alkion paikan.
     *
     */
    public boolean onkoTyhja() {
        if (nykyinenkoko == 0) {
            return true;
        }
        return false;
    }

    public void asetaTaulukko(Iteroitava[] taulukko, int nykyinenkoko) {
        this.taulukko = taulukko;
        this.maksimikoko = taulukko.length;
        this.nykyinenkoko = nykyinenkoko;

    }

    /**
     *
     * Palauttaa nykyisen taulukon viimeisen alkion paikan
     */
    public int palautaNykyinenKoko() {
        return this.nykyinenkoko;
    }

    /**
     *
     * Heapify metodi, jos keon ehto on rikki taulukon kohdassa i tämä metodi
     * korjaa sen
     */
    public void Korjaa(int i) {
        int vasen = vasen(i);
        int oikea = oikea(i);
        if (oikea <= this.nykyinenkoko - 1) {
            int pienempi = 0;
            if (this.taulukko[vasen].KekoArvo() < this.taulukko[oikea].KekoArvo()) {
                pienempi = vasen;
            } else {
                pienempi = oikea;
            }
            if (this.taulukko[i].KekoArvo() > this.taulukko[pienempi].KekoArvo()) {
                vaihda(i, pienempi);
                Korjaa(pienempi);
            }
        } else if ((vasen == this.nykyinenkoko - 1)) {
            if ((taulukko[i].KekoArvo() > taulukko[vasen].KekoArvo())) {
                vaihda(i, vasen);
            }
        }

    }

    /**
     *
     * Palauttaa minimin poistamatta sitä keosta
     */
    public Iteroitava Minimi() {
        return this.taulukko[0];
    }

    /**
     *
     * Palauttaa minimin ja poistaa sen keosta niin että kekoehto säilyy
     */
    public Iteroitava PoistaMinimi() {
        Iteroitava min = this.taulukko[0];
        this.taulukko[0] = this.taulukko[this.nykyinenkoko - 1];
        this.nykyinenkoko = this.nykyinenkoko - 1;
        Korjaa(0);
        return min;
    }

    /**
     *
     * Lisää objektin kekoon.
     *
     */
    public boolean Lisaa(Iteroitava objekti) {
        if (this.nykyinenkoko == this.maksimikoko) {
            return false;
        }
        this.nykyinenkoko = this.nykyinenkoko + 1;
        int i = this.nykyinenkoko - 1;
        objekti.asetaSijainti(i);
        this.taulukko[this.nykyinenkoko - 1] = objekti;
        while ((i > 0) && (this.taulukko[this.vanhempi(i)].KekoArvo() > objekti.KekoArvo())) {
            int vanhempi = this.vanhempi(i);
            vaihda(i, vanhempi);
            i = this.vanhempi(i);
        }
        return true;
    }

    /**
     *
     * Kun jotain alkion arvoa kasvatetaan, tämä metodi korjaa keon.
     */
    public void Kasvatettu(int j) {
        Korjaa(j);
    }

    /**
     *
     * Kun jotain alkion arvoa pienennetään , tämä metodi korjaa keon.
     */
    public void Pienennetty(int j) {
        int i = j;
        while ((i > 0) && (this.taulukko[this.vanhempi(i)].KekoArvo() > this.taulukko[i].KekoArvo())) {
            vaihda(i, this.vanhempi(i));
            i = this.vanhempi(i);

        }
    }

    /**
     * Vaihtaa paikassa i ja j olevat alkiot.
     */
    public void vaihda(int i, int j) {
        Iteroitava vaihdettava1 = this.taulukko[i];
        Iteroitava vaihdettava2 = this.taulukko[j];
        vaihdettava1.asetaSijainti(j);
        vaihdettava2.asetaSijainti(i);
        this.taulukko[i] = vaihdettava2;
        this.taulukko[j] = vaihdettava1;
    }

    /**
     * Palauttaa paiakssa i olevan alkion vanhemman
     */
    private int vanhempi(int i) {
        int j = i - 1;
        j = j / 2;
        return j;
    }

    /**
     * Palauttaa paiakssa i olevan alkion vasemman lapsen
     */
    private int vasen(int i) {
        return 2 * i + 1;
    }

    /**
     * Palauttaa paiakssa i olevan alkion oikean lapsen
     */
    private int oikea(int i) {
        return 2 * i + 2;
    }

    /**
     *
     * Palauttaa koko taulukon (Lähinnä testeja varten)
     */
    public Iteroitava[] palautaTaulukko() {
        return this.taulukko;
    }

}
