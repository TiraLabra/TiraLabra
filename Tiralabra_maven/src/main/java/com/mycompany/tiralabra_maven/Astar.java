/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 * Astar etsii lyhimmän polun verkosta kahden solmun välillä.
 * 
 * Luokka sisältää toiminnallisuuden ja pseudokoodikommentit TiRa k14 
 * -luentomateriaalista
 */
public class Astar {
    
    private Verkko verkko;
    private Solmu aloitus;
    private Solmu kohde;
    private ArrayList<Solmu> lapikaydyt;

    /**
     * Konstruktori alustaa parametrina annetun verkon solmut asettamalla
     * alkuarvon alkuun-muuttujaan ja laskemalla Manhattan-etäisyysarvion 
     * loppuun-muuttujaan 
     * 
     * @param    verkko     Verkko-olio, jossa on solmuja
     * @param    aloitus    Solmu-olio, josta polunetsintä alkaa
     * @param    kohde      Solmu-olio, johon polunetsintä tähtää
     */
    public Astar(Verkko verkko, Solmu aloitus, Solmu kohde) {
        /*
        Astar(G,w,a,b) 
        // G tutkittava verkko, a lähtösolmu, b kohdesolmu ja w kaaripainot kertova funktio
            [tässä toteutuksessa solmut tietävät kaaripainonsa - ei erillistä funktiota]
        */
        this.verkko = verkko;
        this.aloitus = aloitus;
        this.kohde = kohde;
        
        /* for kaikille solmuille v ∈ V
                alkuun[v] = ∞
                loppuun[v] = arvioi suora etäisyys v ; b
                polku[v] = NIL [tässä toteutuksessa polku on jo valmiiksi NIL] */
        
        for(Solmu solmu : verkko.getSolmut()) {
            solmu.setAlkuun(1000000);
            solmu.setLoppuun( abs( (solmu.getX() - kohde.getX()) + (solmu.getY() - kohde.getY()) ) );
        }
        
        /*  alkuun[a] = 0
            S = ∅ */
        
        aloitus.setAlkuun(0);
        this.lapikaydyt = new ArrayList<>();
    }
    
    /**
    * Etsii lyhimmän polun verkosta kahden solmun välillä.
    * 
    * @return kohdesolmu, jonka polku vie aloitussolmuun asti
    */
    public Solmu haeLyhinPolku() {
        /*  while ( solmu b ei ole vielä joukossa S )
                valitse solmu u ∈ V \ S, jolle alkuun[v]+loppuun[v] on pienin
                S = S ∪ {u}
        */
        
        while(!lapikaydyt.contains(kohde)) {
            verkko.getSolmut().removeAll(lapikaydyt);
            Solmu pienin = verkko.getSolmut().get(0);
            for(Solmu solmu : verkko.getSolmut()) {
                if( (solmu.getAlkuun() + solmu.getLoppuun()) < (pienin.getAlkuun() + pienin.getLoppuun()) ) {
                    pienin = solmu;
                }
            }
            lapikaydyt.add(pienin);
            
            /* for jokaiselle solmulle v ∈ Adj[u] // kaikille u:n vierussolmuille v
                    if alkuun[v] > alkuun[u] + w(u,v)
                        alkuun[v] = alkuun[u]+w(u,v)
                        polku[v] = u */
            
            ArrayList<Solmu> vierussolmut = pienin.getVierus();
            
            for(Solmu vierussolmu : vierussolmut) {
                if(vierussolmu.getAlkuun() > (pienin.getAlkuun() + pienin.getKaaripaino(vierussolmu)) ) { 
                    vierussolmu.setAlkuun((pienin.getAlkuun() + pienin.getKaaripaino(vierussolmu)));
                    vierussolmu.setPolku(pienin);
                }
            }
        }
        
        return kohde;
    }
}
