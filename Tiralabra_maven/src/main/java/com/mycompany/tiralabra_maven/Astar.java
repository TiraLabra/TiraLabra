package com.mycompany.tiralabra_maven;

import static java.lang.Math.abs;

/**
 * Astar etsii lyhimmän polun verkosta kahden solmun välillä.
 * 
 * Luokka sisältää toiminnallisuuden ja pseudokoodikommentit TiRa k14 
 * -luentomateriaalista
 */
public class Astar {
    
    private final int SEINAPAINO = -1;
    private final int AARETONPAINO = 1000000;
    private Verkko verkko;
    private Solmu aloitus;
    private Solmu kohde;
    private Minimikeko lapikaymattomat;

    /**
     * Konstruktori alustaa parametrina annetun verkon solmut asettamalla
     * alkuarvon alkuun-muuttujaan ja laskemalla Manhattan-etäisyysarvion 
     * loppuun-muuttujaan 
     * 
     * Aikavaativuus: |V| log |V| - jokaiselle solmulle V tehdään
     * vakioaikaisia operaatioita sekä lisätään kekoon (log |V|) ja lopuksi
     * aloitussolmun arvoa pienennetään (log |V|). 
     * O(|V| log |V| + log |V|) = O(|V| log |V|)
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
        this.lapikaymattomat = new Minimikeko(verkko.getSolmut().koko());
        
        /* for kaikille solmuille v ∈ V
                alkuun[v] = ∞
                loppuun[v] = arvioi suora etäisyys v ; b
                polku[v] = NIL [tässä toteutuksessa polku on jo valmiiksi NIL] 
                [tässä toteutuksessa lisätään solmu läpikäymättömien kekoon] */
        
        Listasolmu pinosolmu = verkko.getSolmut().getYlin();
        
        while (pinosolmu != null) {
            Solmu solmu = pinosolmu.getSisalto();
            
            solmu.setAlkuun(AARETONPAINO);
            solmu.setLoppuun( abs( (solmu.getX() - kohde.getX()) + (solmu.getY() - kohde.getY()) ) );
            this.lapikaymattomat.lisaa(solmu);
            
            pinosolmu = pinosolmu.getSeuraava();
        }
        
        /*  alkuun[a] = 0
            S = ∅ */
        
        aloitus.setAlkuun(0);
        lapikaymattomat.pienennaArvoa(aloitus);
    }
    
    /**
    * Etsii lyhimmän polun verkosta kahden solmun välillä.
    * 
    * Pahin tapaus: verkko on suora viiva, jonka eri päissä ovat alku- ja
    * kohdesolmu, jolloin kaikki solmut käydään läpi
    * Aikavaativuus: O((|E| + |V|) log |V|), sillä jokaiselle solmulle V
    * pienimmän valitseminen (|V| log |V|) ja jokaiselle vierussolmulle E 
    * haetaan kaaripaino (|E|) pienennetään arvoa (|E| log |V|)
    * 
    * @return kohdesolmu, jonka polku vie aloitussolmuun asti
    */
    public Solmu haeLyhinPolku() {
        /*  while ( solmu b ei ole vielä joukossa S ) [toteutettu boolean-muuttujalla]
                valitse solmu u ∈ V \ S, jolle alkuun[v]+loppuun[v] on pienin
                S = S ∪ {u} [toteutettu muuttamalla boolean-muuttujaa, jos kohdesolmu otettiin käsittelyyn]
        */
        
        boolean kohdesolmuLapikayty = false;
        
        while(!kohdesolmuLapikayty) {

            Solmu pienin = this.lapikaymattomat.poistaPienin();

            if(pienin == kohde) {
                kohdesolmuLapikayty = true;
            }
            
            /* for jokaiselle solmulle v ∈ Adj[u] // kaikille u:n vierussolmuille v [tässä toteutuksessa jotka eivät ole seinää]
                    if alkuun[v] > alkuun[u] + w(u,v)
                        alkuun[v] = alkuun[u]+w(u,v)
                        polku[v] = u */
            
            Listasolmu vieruspinosolmu = pienin.getVierus().getYlin();
        
            while (vieruspinosolmu != null) {
                Solmu vierussolmu = vieruspinosolmu.getSisalto();
                
                if(vierussolmu.getAlkuun() > (pienin.getAlkuun() + pienin.getKaaripaino(vierussolmu)) && pienin.getKaaripaino(vierussolmu) != SEINAPAINO ) { 
                    vierussolmu.setAlkuun((pienin.getAlkuun() + pienin.getKaaripaino(vierussolmu)));
                    lapikaymattomat.pienennaArvoa(vierussolmu);
                    vierussolmu.setPolku(pienin);
                }   
                vieruspinosolmu = vieruspinosolmu.getSeuraava();
            }
        }
        
        return kohde;
    }
}
