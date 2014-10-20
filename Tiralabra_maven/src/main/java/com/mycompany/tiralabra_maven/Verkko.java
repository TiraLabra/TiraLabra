package com.mycompany.tiralabra_maven;

/**
 * Verkko sisältää kokoelman solmuja 
 */
public class Verkko {
    private LinkitettyLista solmut;

    public Verkko() {
        solmut = new LinkitettyLista();
    }
    
    /**
     * Lisää tähän verkkoon solmun
     * 
     * Aikavaativuus: vakio
     * 
     * @param    solmu  verkkoon lisättävä solmu
    */    
    public void lisaaSolmu(Solmu solmu) {
        solmut.lisaa(solmu);
    }

    public LinkitettyLista getSolmut() {
        return solmut;
    }
    
    /**
     * Etsii ja palauttaa solmun verkosta annetuilla koordinaateilla
     * 
     * Pahin tapaus: etsittävää solmua ei ole linkitetyssä listassa
     * Aikavaativuus: lineaarinen solmujen lukumäärän suhteen
     * 
     * @param    x       solmun vaakakoordinaatti 2D-esityksessä
     * @param    y       solmun pystykoordinaatti 2D-esityksessä
     * @return etsitty solmu tai null, jos solmua ei löytynyt
    */    
    public Solmu getSolmu(int x, int y) {
        Listasolmu pinosolmu = solmut.getYlin();
        
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
     * 
     * Pahin tapaus: kaikki solmut käydään läpi (myös paras tapaus)
     * Aikavaativuus: lineaarinen solmujen lukumäärän suhteen
    */ 
    public void luoVieruslistat() {
        Listasolmu pinosolmu = solmut.getYlin();
        
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
