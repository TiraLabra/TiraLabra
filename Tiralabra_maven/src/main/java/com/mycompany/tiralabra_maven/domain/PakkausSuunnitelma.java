/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.domain;

import com.mycompany.tiralabra_maven.structures.List;

/**
 * Pakkaussuunnitelma on ohjelman tulos, joka sisältää listan konttiin
 * asetetuista palkeista.
 *
 * @author szetk
 */
public class PakkausSuunnitelma {

    private List<Palkki> palkit;
    private long tilavuus;
    private int laatikoita;

    public PakkausSuunnitelma(List<Palkki> palkit) {
        this.palkit = palkit;
        this.tilavuus = 0;
    }

    public PakkausSuunnitelma() {
        this.palkit = new List<Palkki>();
    }

    /**
     * Tämä lisää palkin pakkaussuunnitelmaan. Myös pakkaussuunnitelman tilavuus
     * päivitetään palkin tilavuudella.
     *
     * @param palkki
     */
    void lisaaPalkki(Palkki palkki) {
        this.tilavuus += palkki.getTilavuus();
        this.laatikoita += palkki.getLaatikot().size();
        this.palkit.add(palkki);

    }

    public List<Palkki> getPalkit() {
        return this.palkit;
    }

    public void setPalkit(List<Palkki> palkit) {
        this.palkit = palkit;
    }

    public long getTilavuus() {
        return tilavuus;
    }

    public int getLaatikoita() {
        return laatikoita;
    }

}
