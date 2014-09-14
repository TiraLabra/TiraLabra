package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Verkko sisältää kokoelman solmuja 
 */
public class Verkko {
    private ArrayList<Solmu> solmut;

    public Verkko() {
        solmut = new ArrayList<Solmu>();
    }
    
    public void lisaaSolmu(Solmu solmu) {
        solmut.add(solmu);
    }

    public ArrayList<Solmu> getSolmut() {
        return solmut;
    }
    
    /**
     * Etsii ja palauttaa solmun verkosta annetuilla koordinaateilla
     * 
     * @param    x       solmun vaakakoordinaatti 2D-esityksessä
     * @param    y       solmun pystykoordinaatti 2D-esityksessä
     * @return etsitty solmu tai null, jos solmua ei löytynyt
    */    
    public Solmu getSolmu(int x, int y) {
        for(Solmu solmu : solmut) {
            if(solmu.getX() == x && solmu.getY() == y) {
                return solmu;
            }
        }
        return null;
    }
    
    /**
     * Luo verkon jokaiselle solmulle vieruslistan, johon kuuluu 2D-esityksessä
     * solmun ylä- ja alapuolella sekä vasemmalla ja oikealla puolella 
     * sijaitsevat solmut
    */ 
    public void luoVieruslistat() {
        for(Solmu solmu : solmut) {
            if(this.getSolmu(solmu.getX() - 1, solmu.getY()) != null) {
                solmu.lisaaVierus(this.getSolmu(solmu.getX() - 1, solmu.getY()));
            }
            if(this.getSolmu(solmu.getX() + 1, solmu.getY()) != null) {
                solmu.lisaaVierus(this.getSolmu(solmu.getX() + 1, solmu.getY()));
            }
            if(this.getSolmu(solmu.getX(), solmu.getY() - 1) != null) {
                solmu.lisaaVierus(this.getSolmu(solmu.getX(), solmu.getY() - 1));
            }
            if(this.getSolmu(solmu.getX(), solmu.getY() + 1) != null) {
                solmu.lisaaVierus(this.getSolmu(solmu.getX(), solmu.getY() + 1));
            }
        }
    }
}
