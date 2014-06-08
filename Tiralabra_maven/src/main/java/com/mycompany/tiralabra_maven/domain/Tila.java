/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * @author szetk
 */
public class Tila {

    private PakkausSuunnitelma pakkausSuunnitelma;
    private int tilavuus;
    private ArrayList<Laatikkotyyppi> vapaatLaatikot;
    private Deque<Tilapalkki> tilapalkit;

    public Tila(Kontti kontti, ArrayList<Laatikkotyyppi> laatikot) {
        this.pakkausSuunnitelma = new PakkausSuunnitelma();
        this.tilavuus = 0;
        this.vapaatLaatikot = laatikot;
        this.tilapalkit = new ArrayDeque<Tilapalkki>();
        this.tilapalkit.push(new Tilapalkki(kontti.getX(), kontti.getY(), kontti.getZ(), 0, 0, 0));
    }

    /**
     * Päivittää tilan kun lisätään uusi palkki pakkaussuunnitelmaan. Tämä
     * kutsuu muita metodeja, jotka tekevät suurimman osan työstä
     *
     * @param palkki Uusi, lisättävä palkki
     * @param tilapalkki Tilapalkki, johon palkki asetetaan
     * @param kontti Kontti, jonka mukaan luodaan uudet tilapalkit
     */
    public void paivita(Palkki palkki, Tilapalkki tilapalkki, Kontti kontti) {

        lisaaTilapalkit(kontti, palkki, tilapalkki.getSijainti());
        palkki.setSijainti(tilapalkki.getSijainti());
        this.pakkausSuunnitelma.getPalkit();
        poistaVapaistaLaatikoista(palkki);
    }

    /**
     * Tämä poistaa parametrina saadun palkin sisältämät laatikot vapaiden
     * laatikoiden listasta.
     *
     * @param palkki Palkki, jonka sisältämät laatikot poistetaan vapaiden
     * laatikoiden listasta
     */
    public void poistaVapaistaLaatikoista(Palkki palkki) {
        for (Laatikkotyyppi tyyppi : this.vapaatLaatikot) {
            if (tyyppi == palkki.getTyyppi()) {
                for (int i = 0; i < palkki.laatikoita(); i++) {
                    tyyppi.getLaatikot().remove(tyyppi.getLaatikot().size() - 1);
                }
            }
        }
    }

    /**
     * Tämä lisää tilaan uudet tilapalkit sen jälkeen kun pakkaussuunnitelmaan
     * on lisätty uusi palkki.
     *
     * @param kontti Kontti, jota pakataan
     * @param palkki Viimeksi pakattu palkki, jonka ympärille tilapalkit luodaan
     * @param sijainti Palkin sijainti
     */
    public void lisaaTilapalkit(Kontti kontti, Palkki palkki, Sijainti sijainti) {
// palkki eteen:
        this.tilapalkit.push(new Tilapalkki(kontti.getX() - sijainti.getX(), kontti.getY() - palkki.getY() - sijainti.getY(), kontti.getZ() - sijainti.getZ(), sijainti.getX(), sijainti.getY() + palkki.getY(), sijainti.getZ()));
// palkki viereen:
        this.tilapalkit.push(new Tilapalkki(kontti.getX() - palkki.getX() - sijainti.getX(), palkki.getY(), kontti.getZ() - sijainti.getZ(), sijainti.getX() + palkki.getX(), sijainti.getY(), sijainti.getZ()));
// palkki yläpuolelle:
        this.tilapalkit.push(new Tilapalkki(palkki.getX(), palkki.getY(), kontti.getZ() - palkki.getZ() - sijainti.getZ(), sijainti.getX(), sijainti.getY(), sijainti.getZ() + palkki.getZ()));

    }

    public int vapaitaLaatikoita() {
        int sum = 0;
        for (Laatikkotyyppi laatikkotyyppi : this.vapaatLaatikot) {
            sum += laatikkotyyppi.getLaatikot().size();
        }
        return sum;
    }

    public PakkausSuunnitelma getPakkausSuunnitelma() {
        return pakkausSuunnitelma;
    }

    public void setPakkausSuunnitelma(PakkausSuunnitelma pakkausSuunnitelma) {
        this.pakkausSuunnitelma = pakkausSuunnitelma;
    }

    public int getTilavuus() {
        return tilavuus;
    }

    public void setTilavuus(int tilavuus) {
        this.tilavuus = tilavuus;
    }

    public ArrayList<Laatikkotyyppi> getVapaatLaatikot() {
        return vapaatLaatikot;
    }

    public void setVapaatLaatikot(ArrayList<Laatikkotyyppi> vapaatLaatikot) {
        this.vapaatLaatikot = vapaatLaatikot;
    }

    public Deque<Tilapalkki> getTilapalkit() {
        return tilapalkit;
    }

    public void setTilapalkit(Deque<Tilapalkki> vapaatTilapalkit) {
        this.tilapalkit = vapaatTilapalkit;
    }

}
