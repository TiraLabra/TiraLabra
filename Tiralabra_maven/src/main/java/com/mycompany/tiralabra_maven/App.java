package com.mycompany.tiralabra_maven;

import static java.lang.Math.abs;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
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
        verkko2.lisaaSolmu( new Solmu(2, 1, -1) );
        verkko2.lisaaSolmu( new Solmu(0, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 2, 0) );
        
        //jokaiselle solmulle etsitään vieruksiksi solmut delta x-1 ja delta y-0 jne
        
        ArrayList<Solmu> solmut = verkko2.getSolmut();
        
        for(Solmu solmu : solmut) {
            if(verkko2.getSolmu(solmu.getX() - 1, solmu.getY()) != null) {
                solmu.lisaaVierus(verkko2.getSolmu(solmu.getX() - 1, solmu.getY()));
            }
            if(verkko2.getSolmu(solmu.getX() + 1, solmu.getY()) != null) {
                solmu.lisaaVierus(verkko2.getSolmu(solmu.getX() + 1, solmu.getY()));
            }
            if(verkko2.getSolmu(solmu.getX(), solmu.getY() - 1) != null) {
                solmu.lisaaVierus(verkko2.getSolmu(solmu.getX(), solmu.getY() - 1));
            }
            if(verkko2.getSolmu(solmu.getX(), solmu.getY() + 1) != null) {
                solmu.lisaaVierus(verkko2.getSolmu(solmu.getX(), solmu.getY() + 1));
            }
        }
       
        
        System.out.println("Tästä lähtee");
        Astar(verkko2, verkko2.getSolmu(2, 0), verkko2.getSolmu(2, 2));
    }
    
    public static void Astar(Verkko verkko, Solmu aloitus, Solmu kohde) {
        /*
        Astar(G,w,a,b) 
        // G tutkittava verkko, a lähtösolmu, b kohdesolmu ja w kaaripainot kertova funktio
        */
        
        
        /* for kaikille solmuille v ∈ V
                alkuun[v] = ∞
                loppuun[v] = arvioi suora etäisyys v ; b
                polku[v] = NIL */
        
        ArrayList<Solmu> solmut = verkko.getSolmut();
        
        for(Solmu solmu : solmut) {
            solmu.setAlkuun(1000000);
            
            solmu.setLoppuun( abs( (solmu.getX() - kohde.getX()) + (solmu.getY() - kohde.getY()) ) );
            solmu.setPolku(null);
        }
        
        
        /*  alkuun[a] = 0
            S = ∅ */
        
        aloitus.setAlkuun(0);
        ArrayList<Solmu> lapikaydyt = new ArrayList<Solmu>();
        
        /*  while ( solmu b ei ole vielä joukossa S )
                valitse solmu u ∈ V \ S, jolle alkuun[v]+loppuun[v] on pienin
                S = S ∪ {u}
        */
        
        while(!lapikaydyt.contains(kohde)) {
            solmut.removeAll(lapikaydyt); //toimiiko? oikein? testaa
            Solmu pienin = solmut.get(0);
            for(Solmu solmu : solmut) {
                if( (solmu.getAlkuun() + solmu.getLoppuun()) < (pienin.getAlkuun() + pienin.getLoppuun()) ) {
                    pienin = solmu;
                }
            }
            lapikaydyt.add(pienin);
            
            /* for jokaiselle solmulle v ∈ Adj[u] // kaikille u:n vierussolmuille v
                    if alkuun[v] > alkuun[u] + w(u,v)
                        alkuun[v] = alkuun[u]+w(u,v)
                        polku[v] = u */
            
            ArrayList<Vierussolmu> vierussolmut = pienin.getVierus();
            
            for(Vierussolmu vierussolmu : vierussolmut) {
                if(vierussolmu.getSolmu().getAlkuun() > (pienin.getAlkuun() + pienin.getKaaripaino(vierussolmu.getSolmu())) ) { 
                    vierussolmu.getSolmu().setAlkuun((pienin.getAlkuun() + pienin.getKaaripaino(vierussolmu.getSolmu())));
                    vierussolmu.getSolmu().setPolku(pienin);
                }
            }
        }
        
        System.out.println("Astar suoritus loppui");
        tulostaPolku(kohde);
    }
    
    public static void tulostaPolku(Solmu solmu) {
        while(solmu.getPolku() != null) {
            System.out.println("X" + solmu.getPolku().getX() + ", Y" + solmu.getPolku().getY());
            solmu = solmu.getPolku();
        }
    }
}
