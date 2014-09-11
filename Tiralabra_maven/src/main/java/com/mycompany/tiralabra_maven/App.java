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
        Solmu solmu1 = new Solmu(0, 0, 0);
        Solmu solmu2 = new Solmu(1, 0, 0);
        Solmu solmu3 = new Solmu(2, 0, 0);
        Solmu solmu4 = new Solmu(0, 1, 0);
        Solmu solmu5 = new Solmu(1, 1, 8);
        Solmu solmu6 = new Solmu(2, 1, -1);
        Solmu solmu7 = new Solmu(0, 2, 0);
        Solmu solmu8 = new Solmu(1, 2, 0);
        Solmu solmu9 = new Solmu(2, 2, 0);
        
        //Lisää solmujen vierussolmut vieruslistalle
        solmu1.lisaaVierus(solmu2);
        solmu1.lisaaVierus(solmu4);
        
        solmu2.lisaaVierus(solmu1);
        solmu2.lisaaVierus(solmu3);
        solmu2.lisaaVierus(solmu5);
        
        solmu3.lisaaVierus(solmu2);
        //solmu3.lisaaVierus(solmu6);
        
        solmu4.lisaaVierus(solmu1);
        solmu4.lisaaVierus(solmu5);
        solmu4.lisaaVierus(solmu7);
        
        solmu5.lisaaVierus(solmu2);
        solmu5.lisaaVierus(solmu4);
        //solmu5.lisaaVierus(solmu6);
        solmu5.lisaaVierus(solmu8);
        
        //solmu6.lisaaVierus(solmu3);
        //solmu6.lisaaVierus(solmu5);
        //solmu6.lisaaVierus(solmu9);
        
        solmu7.lisaaVierus(solmu4);
        solmu7.lisaaVierus(solmu8);
        
        solmu8.lisaaVierus(solmu7);
        solmu8.lisaaVierus(solmu5);
        solmu8.lisaaVierus(solmu9);
        
        solmu9.lisaaVierus(solmu8);
        //solmu9.lisaaVierus(solmu6);
        
        //Lisää solmut verkkoon
        verkko2.lisaaSolmu(solmu1);
        verkko2.lisaaSolmu(solmu2);
        verkko2.lisaaSolmu(solmu3);
        verkko2.lisaaSolmu(solmu4);
        verkko2.lisaaSolmu(solmu5);
        verkko2.lisaaSolmu(solmu6);
        verkko2.lisaaSolmu(solmu7);
        verkko2.lisaaSolmu(solmu8);
        verkko2.lisaaSolmu(solmu9);
             
        
        System.out.println("Tästä lähtee");
        Astar(verkko2, solmu3, solmu9);
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
