/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import verkko.rajapinnat.Edge;

/**
 * Verkossa solmujen välinen kaari. Vastaa raitiovaunulla kuljettua pysäkin väliä.
 * 
 * @author E
 */
public class Kaari implements Edge {
    /**
     * Kaaren kulkemisen kustannus
     */
    private double kustannus;
    /**
     * Toinen kustannusta kuvaava muuttuja (jos kustannus on aika, tämä voi olla vaikka matka)
     */
    private double etaisyys;
    /**
    Verkkoa varten viitteet merkkijonoina
    */
    private String linjanNimi;
    /**
    Verkkoa varten viitteet merkkijonoina
    */    
    private String alkuSolmu;
    /**
    Verkkoa varten viitteet merkkijonoina
    */    
    private String loppuSolmu;
    
    
    /////////////////////////////////////////////
    ///// automaattiset setterit & getterit /////
    /////////////////////////////////////////////
    
    public double getKustannus() {
        return kustannus;
    }

    public void setKustannus(double kustannus) {
        this.kustannus = kustannus;
    }

    public double getEtaisyys() {
        return etaisyys;
    }

    public void setEtaisyys(double etaisyys) {
        this.etaisyys = etaisyys;
    }

    public String getLinjanNimi() {
        return linjanNimi;
    }

    public void setLinjanNimi(String linjanNimi) {
        this.linjanNimi = linjanNimi;
    }

    public String getAlkuSolmu() {
        return alkuSolmu;
    }

    public void setAlkuSolmu(String alkuSolmu) {
        this.alkuSolmu = alkuSolmu;
    }

    public String getLoppuSolmu() {
        return loppuSolmu;
    }

    public void setLoppuSolmu(String loppuSolmu) {
        this.loppuSolmu = loppuSolmu;
    }

    @Override
    public String toString() {
        return "Kaari{" + "kustannus=" + kustannus + ", etaisyys=" + etaisyys + ", linjanNimi=" + linjanNimi + ", alkuSolmu=" + alkuSolmu + ", loppuSolmu=" + loppuSolmu + '}';
    }
    
    
}
