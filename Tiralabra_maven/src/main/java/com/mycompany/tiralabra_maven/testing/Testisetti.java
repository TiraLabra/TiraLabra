/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.testing;

import com.mycompany.tiralabra_maven.domain.Kontti;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.structures.List;

/**
 * Testisetti sisältää listan laatikoita, kontin ja nimen. Näiden avulla tehdään
 * automatisoituja suorituskykytestauksia.
 *
 * @author szetk
 */
public class Testisetti {

    private String nimi;
    private Kontti kontti;
    private List<Laatikkotyyppi> laatikot;

    public Testisetti(String nimi, Kontti kontti, List<Laatikkotyyppi> laatikot) {
        this.nimi = nimi;
        this.kontti = kontti;
        this.laatikot = laatikot;
    }

    public List<Laatikkotyyppi> getLaatikot() {
        return laatikot;
    }

    public void setLaatikot(List<Laatikkotyyppi> laatikot) {
        this.laatikot = laatikot;
    }

    public Kontti getKontti() {
        return kontti;
    }

    public void setKontti(Kontti kontti) {
        this.kontti = kontti;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
