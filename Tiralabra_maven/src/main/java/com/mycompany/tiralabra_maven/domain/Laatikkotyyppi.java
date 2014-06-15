/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.domain;

import com.mycompany.tiralabra_maven.structures.List;


/**
 * Laatikot edustavat aina jotakin tyyppiä. Tyyppi sisältää listan laatikoista, jotka ovat tätä tyyppiä
 * @author szetk
 */
public class Laatikkotyyppi extends Sarmio{
    private List<Laatikko> laatikot;
    
    /**
     * Konstruktori
     * @param x Tyypin pituus
     * @param y Tyypin leveys
     * @param z Tyypin korkeus
     */
    public Laatikkotyyppi(int x, int y, int z) {
        super(x, y, z);
        this.laatikot = new List<Laatikko>();
    }
    
    /**
     * Toinen konstruktori
     * @param x Tyypin pituus
     * @param y Tyypin leveys
     * @param z Tyypin korkeus
     * @param laatikot Lista tyypin laatikoista
     */
    public Laatikkotyyppi(int x, int y, int z, List<Laatikko> laatikot) {
        super(x, y, z);
        this.laatikot = laatikot;
    }

    public List<Laatikko> getLaatikot() {
        return laatikot;
    }

    public void setLaatikot(List<Laatikko> laatikot) {
        this.laatikot = laatikot;
    }
    
    
}
