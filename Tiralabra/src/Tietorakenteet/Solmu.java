/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Keko.Iteroitava;

/**
 *
 * Konkreettinen luokka, joka toteuttaa keon alkion ehdot.
 */
public class Solmu implements Iteroitava, abstraktiSolmu {

    private double arvo;
    private double x;
    private double y;
    private int sijainti;
    private Verkko verkko;
    private int vari;
    private abstraktiSolmu edellinen;

    /**
     *
     * Asettaa Solmulle x ja y kordinaatin
     */
    public Solmu(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * Palauttaa x kordinaatin
     */
    public double PalautaX() {
        return this.x;
    }

    /**
     *
     * Palauttaa y kordinaatin
     */
    public double PalautaY() {
        return this.y;
    }

    /**
     *
     * Palauttaa alkion arvon
     */
    @Override
    public double Arvo() {
        return this.arvo;
    }

    /**
     *
     * Palauttaa sijainnin keossa
     */
    @Override
    public int SijaintiKeossa() {
        return this.sijainti;
    }

    /**
     *
     * Asettaa alkiolle sijainnin keossa
     */
    @Override
    public void asetaSijainti(int i) {
        this.sijainti = i;
    }

    /**
     *
     * Asettaa alkiolle arvon
     */
    @Override
    public void asetaArvo(double d) {
        this.arvo = d;
    }

    /**
     *
     * Asettaa verkon
     */
    @Override
    public void asteaVerkko(Verkko verkko) {
        this.verkko = verkko;
    }

    /**
     *
     * Palauttaa verkon
     */
    @Override
    public Verkko palautaVerkko() {
        return this.verkko;
    }

    /**
     *
     * Värittää solmun
     */
    @Override
    public void Varita(int i) {
        this.vari = i;
    }

    /**
     *
     * Palauttaa solmun värin
     */
    @Override
    public int palautaVari() {
        return this.vari;
    }

    @Override
    public void asetaEdellinen(abstraktiSolmu solmu) {
        this.edellinen = solmu;
    }

    @Override
    public abstraktiSolmu palautaEdellinen() {
        return this.edellinen;
    }
}
