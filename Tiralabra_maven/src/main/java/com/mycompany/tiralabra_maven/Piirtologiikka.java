/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikko
 */
public class Piirtologiikka {
    private final Ruutu[][] ruudut;
    private int leveys;
    private int korkeus;
    public Piirtologiikka(int leveys, int korkeus) {
        this.ruudut = new Ruutu[korkeus][leveys];
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    public Ruutu[][] getRuudukko() {
        return this.ruudut;
    }
    
    public Ruutu getRuutu(int x, int y) {
        return this.ruudut[y][x];
    }
    
    public void setRuutu(int x, int y, Ruutu ruutu) {
        if (ruudut[y][x] == ruutu) {
            return;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        this.ruudut[y][x] = ruutu;
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus(){
        return this.korkeus;
    }
}
