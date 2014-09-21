package com.mycompany.tiralabra_maven;

public class App 
{
    public static void main( String[] args )
    {
        final int SEINAPAINO = -1;
        
        //Esitetään verkko vieruslistana
        
        /*
        000
        08#
        000
        */
        
        //Luo verkko
        Verkko verkko2 = new Verkko();
        
        //Luo solmut
        verkko2.lisaaSolmu( new Solmu(0, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 1, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 1, 8) );
        verkko2.lisaaSolmu( new Solmu(2, 1, SEINAPAINO) );
        verkko2.lisaaSolmu( new Solmu(0, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 2, 0) );
        
        verkko2.luoVieruslistat();
       
        Astar astar = new Astar(verkko2, verkko2.getSolmu(2, 0), verkko2.getSolmu(2, 2));
        tulostaPolku(astar.haeLyhinPolku());
    }
    
    public static void tulostaPolku(Solmu solmu) {
        while(solmu != null) {
            System.out.println("X" + solmu.getX() + ", Y" + solmu.getY());
            solmu = solmu.getPolku();
        }
    }
}
