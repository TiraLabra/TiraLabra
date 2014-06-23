/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import com.mycompany.tiralabra_maven.structures.*;

/**
 * Tämä luokka kuvaa ohjelman tietynhetkistä tilaa, joka pitää yllä tärkeitä
 * parametreja
 *
 * @author szetk
 */
public class Tila {

    private PakkausSuunnitelma pakkausSuunnitelma;
    private long tilavuus;
    private List<Laatikkotyyppi> vapaatLaatikot;
    private Stack<Tilapalkki> tilapalkit;

    /**
     * Tilan konstruktori
     *
     * @param kontti Kontti, jota täytetään
     * @param laatikot Lista laatikoista, jotka täytetään konttiin
     */
    public Tila(Kontti kontti, List<Laatikkotyyppi> laatikot) {
        this.pakkausSuunnitelma = new PakkausSuunnitelma();
        this.tilavuus = 0;
        this.vapaatLaatikot = laatikot;
        this.tilapalkit = new Stack<Tilapalkki>();
        this.tilapalkit.push(new Tilapalkki(kontti.getX(), kontti.getY(), kontti.getZ(), 0, 0, 0));
    }

    /**
     * Päivittää tilan kun lisätään uusi palkki pakkaussuunnitelmaan. Tämä
     * kutsuu muita metodeja, jotka tekevät suurimman osan työstä
     *
     * @param palkki Uusi, lisättävä palkki
     * @param tilapalkki Tilapalkki, johon palkki asetetaan
     */
    public void paivita(Palkki palkki, Tilapalkki tilapalkki) {
        lisaaTilapalkit(tilapalkki, palkki);
        this.pakkausSuunnitelma.lisaaPalkki(palkki);
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
        for (int j = 0; j < this.vapaatLaatikot.size(); j++) {
            Laatikkotyyppi tyyppi = this.vapaatLaatikot.get(j);
            if (tyyppi == palkki.getTyyppi()) {
                this.vapaatLaatikot.get(j).setLaatikoita(tyyppi.getLaatikoita() - palkki.getLaatikot().size());
            }
        }
    }

    /**
     * Tämä lisää tilaan uudet tilapalkit sen jälkeen kun pakkaussuunnitelmaan
     * on lisätty uusi palkki.
     *
     * @param tila Kontti, jota pakataan
     * @param palkki Viimeksi pakattu palkki, jonka ympärille tilapalkit luodaan
     */
    public void lisaaTilapalkit(Tilapalkki tila, Palkki palkki) {
        Sijainti sijainti = tila.getSijainti();
// palkki viereen:
        this.tilapalkit.push(new Tilapalkki(tila.getX() - palkki.getX(), palkki.getY(), tila.getZ(), sijainti.getX() + palkki.getX(), sijainti.getY(), sijainti.getZ()));
// palkki eteen:
        this.tilapalkit.push(new Tilapalkki(tila.getX(), tila.getY() - palkki.getY(), tila.getZ(), sijainti.getX(), sijainti.getY() + palkki.getY(), sijainti.getZ()));
// palkki yläpuolelle:
        this.tilapalkit.push(new Tilapalkki(palkki.getX(), palkki.getY(), tila.getZ() - palkki.getZ(), sijainti.getX(), sijainti.getY(), sijainti.getZ() + palkki.getZ()));

    }

    /**
     * Laskee kuinka monta vapaata laatikkoa tällä hetkellä on
     *
     * @return Palauttaa vapaiden laatikoiden määrän
     */
    public int vapaitaLaatikoita() {
        int sum = 0;
        for (int j = 0; j < this.vapaatLaatikot.size(); j++) {
            Laatikkotyyppi tyyppi = this.vapaatLaatikot.get(j);
            sum += tyyppi.getLaatikoita();
        }
        return sum;
    }
    public PakkausSuunnitelma getPakkausSuunnitelma() {
        return pakkausSuunnitelma;
    }

    public void setPakkausSuunnitelma(PakkausSuunnitelma pakkausSuunnitelma) {
        this.pakkausSuunnitelma = pakkausSuunnitelma;
    }

    public long getTilavuus() {
        return tilavuus;
    }

    public void setTilavuus(long tilavuus) {
        this.tilavuus = tilavuus;
    }

    public List<Laatikkotyyppi> getVapaatLaatikot() {
        return vapaatLaatikot;
    }

    public void setVapaatLaatikot(List<Laatikkotyyppi> vapaatLaatikot) {
        this.vapaatLaatikot = vapaatLaatikot;
    }

    public Stack<Tilapalkki> getTilapalkit() {
        return tilapalkit;
    }

    public void setTilapalkit(Stack<Tilapalkki> vapaatTilapalkit) {
        this.tilapalkit = vapaatTilapalkit;
    }

}
