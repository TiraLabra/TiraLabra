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
    private Deque<Tilapalkki> vapaatTilapalkit;

    public Tila(Kontti kontti, ArrayList<Laatikkotyyppi> laatikot) {
        this.pakkausSuunnitelma = new PakkausSuunnitelma();
        this.tilavuus = 0;
        this.vapaatLaatikot = laatikot;
        this.vapaatTilapalkit = new ArrayDeque<Tilapalkki>();
        this.vapaatTilapalkit.push(new Tilapalkki(kontti.getX(), kontti.getY(), kontti.getZ(), 0, 0, 0));
    }

    /**
     * Päivittää tilan kun lisätään uusi palkki pakkaussuunnitelmaan. Tämä kutsuu muita metodeja, jotka tekevät suurimman osan työstä
     * 
     * @param palkki Uusi, lisättävä palkki
     * @param tilapalkki Tilapalkki, johon palkki asetetaan
     * @param kontti Kontti, jonka mukaan luodaan uudet tilapalkit
     */
    public void paivita(Palkki palkki, Tilapalkki tilapalkki, Kontti kontti) {

        lisaaTilapalkit(kontti, palkki, tilapalkki.getSijainti());
        palkki.setSijainti(tilapalkki.getSijainti());
        this.pakkausSuunnitelma.getPalkit().add(palkki);
        poistaVapaistaLaatikoista(palkki);
    }

    /**
     * Tämä poistaa parametrina saadun palkin sisältämät laatikot vapaiden laatikoiden listasta.
     * @param palkki Palkki, jonka sisältämät laatikot poistetaan vapaiden laatikoiden listasta
     */
    public void poistaVapaistaLaatikoista(Palkki palkki) {
        for (Laatikkotyyppi tyyppi : this.vapaatLaatikot) {
            for (Laatikko laatikko : palkki.getLaatikot()) {
                if (laatikko.getTyyppi() == tyyppi) {
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
        this.vapaatTilapalkit.push(new Tilapalkki(palkki.getX(), palkki.getY(), kontti.getZ() - palkki.getZ() - sijainti.getPosZ(), sijainti.getPosX(), sijainti.getPosY(), sijainti.getPosZ() + palkki.getZ()));
        this.vapaatTilapalkit.push(new Tilapalkki(kontti.getX() - palkki.getX() - sijainti.getPosX(), palkki.getY(), kontti.getZ() - sijainti.getPosZ(), sijainti.getPosX() + palkki.getX(), sijainti.getPosY(), sijainti.getPosZ()));
        this.vapaatTilapalkit.push(new Tilapalkki(kontti.getX() - sijainti.getPosX(), kontti.getY() - palkki.getY() - sijainti.getPosY(), kontti.getZ() - sijainti.getPosZ(), sijainti.getPosX(), sijainti.getPosY() + palkki.getY(), sijainti.getPosZ()));

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

    public Deque<Tilapalkki> getVapaatTilapalkit() {
        return vapaatTilapalkit;
    }

    public void setVapaatTilapalkit(Deque<Tilapalkki> vapaatTilapalkit) {
        this.vapaatTilapalkit = vapaatTilapalkit;
    }

}
