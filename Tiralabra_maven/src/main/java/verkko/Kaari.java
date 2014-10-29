/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

/**
 * Verkossa solmujen välinen kaari
 * 
 * @author E
 */
public class Kaari {
    /**
     * Kaaren pituus = kustannus
     */
    private double kustannus;
    
    
    // automaattiset setterit & getterit

    public double getKustannus() {
        return kustannus;
    }

    public void setKustannus(double kustannus) {
        this.kustannus = kustannus;
    }
    
    
}
