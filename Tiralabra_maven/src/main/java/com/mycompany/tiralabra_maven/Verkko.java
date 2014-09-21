package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Verkko sisältää kokoelman solmuja 
 */
public class Verkko {
    private LinkitettyLista solmut;

    public Verkko() {
        solmut = new LinkitettyLista();
    }
    
    public void lisaaSolmu(Solmu solmu) {
        solmut.lisaa(solmu);
    }

    public LinkitettyLista getSolmut() {
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
        Pinosolmu pinosolmu = solmut.getYlin();
        
        while (pinosolmu != null) {
            Solmu solmu = pinosolmu.getSisalto();
            
            if(solmu.getX() == x && solmu.getY() == y) {
                return solmu;
            }
            pinosolmu = pinosolmu.getSeuraava();
        }
        
        return null;
    }
    
    /**
     * Luo verkon jokaiselle solmulle vieruslistan, johon kuuluu 2D-esityksessä
     * solmun ylä- ja alapuolella sekä vasemmalla ja oikealla puolella 
     * sijaitsevat solmut
    */ 
    public void luoVieruslistat() {
        Pinosolmu pinosolmu = solmut.getYlin();
        
        while (pinosolmu != null) {
            Solmu solmu = pinosolmu.getSisalto();
            
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
            
            pinosolmu = pinosolmu.getSeuraava();
        }
    }
}
