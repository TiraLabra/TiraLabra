/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import tira.DynaaminenLista;
import tira.Hajautuslista;
import tira.Lista;
import tira.PrioriteettiJonoListalla;
import verkko.Verkko;
import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Graph;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;

/**
 * A*-haun toteutus
 *
 * @author E
 */
public class AStar {

    /**
     * Verkko, jossa hakuja suoritetaan
     */
    private Graph verkko;
    /**
     * Apuolio reittien kustannusten ja jÃ¤ljellÃ¤olevan kustannuksen arviointiin
     */
    private Laskin laskin;
    /**
     * Haun toiminnasta tietoa DebugTieto-oliossa
     */
    private DebugTieto debugTieto;

    /**
     * Esitys solmuista, joissa on käyty. Guita varten
     */
    private Lista<Value> kaydytSolmut;

    ///////////////////
    // KONSTRUKTORIT //
    ///////////////////
    /**
     *
     * @param verkko
     * @param laskin
     */
    public AStar(Graph verkko, Laskin laskin) {
        this.verkko = verkko;
        this.laskin = laskin;
        this.laskin.setVerkko(verkko);
        this.debugTieto = new DebugTieto();
    }

    /**
     *
     * @param verkko Verkko, jossa haut suoritetaan
     */
    public AStar(Graph verkko) {
        this(verkko, new ReittiLaskin());
    }

    /**
     * Oletuskonstruktori
     */
    public AStar() {
        this(new Verkko(), new ReittiLaskin());
    }

    /**
     * Etsii a*-haulla verkosta parhaan reitin alkusolmusta loppusolmuun.
     * KÃ¤yttÃ¤Ã¤ omaa prioriteettijonoa ja hajautuslistaa
     *
     *
     * @param maali
     * @param alku
     * @return
     */
    public Node etsiReittiOma(Value alku, Value maali) {
        boolean dbg = this.isDebugMode();
        boolean dbgPrint = this.isDebugPrint();
        this.debugTieto = new DebugTieto();
        this.setDebugMode(dbg);
        this.setDebugPrint(dbgPrint);
        Node alkuTila = laskin.laskeSeuraava(null, null, alku, maali);
        int aika = 100; // vaikuttaa prioriteettijonon oletuskokoon
        final int tarkkuus = 100; // comparator-oliolle tarkkuus (1/tarkkuus), esim arvo 100 -> tarkkuus 0.01 kustannuspistettÃ¤
        Comparator<Node> comparator = alkuTila.vertailija(tarkkuus);

        PrioriteettiJonoListalla<Node> kasittelyJarjestys;
        kasittelyJarjestys = new PrioriteettiJonoListalla(aika * tarkkuus, comparator);

        kasittelyJarjestys.add(alkuTila);

        Lista<Node> parhaatReitit = new DynaaminenLista();

        double lowestCost = Integer.MAX_VALUE;

        Hajautuslista<Value> kasitellytSolmut = new Hajautuslista(1000);

        while (!kasittelyJarjestys.isEmpty()) {           

            Node kasiteltava = kasittelyJarjestys.poll(); 
            Value solmu = kasiteltava.getSolmu();
            
            
            // DEBUG: tietoa kÃ¤sittelystÃ¤ ja heuristiikan toiminnasta
            debugTieto.debugKasittelytieto(kasittelyJarjestys.size(), kasiteltava);
            debugTieto.debugHeuristiikka(kasiteltava);  // heuristiikan toiminta
            if (solmu.equals(maali)) {
                if ( lowestCost > kasiteltava.getKustannus() ) lowestCost = kasiteltava.getKustannus();
                parhaatReitit.add(kasiteltava);
                break;
            }
            if (kasiteltava.getKustannus() > lowestCost) {
                // continue;
                break;
            }
            
            if ( kasitellytSolmut.contains(solmu) ) continue;
            
            for (Value naapuri : verkko.getNaapurit(solmu)) {
                for (Edge kaari : verkko.getKaaret(solmu, naapuri)) {
                    // if ( kuljetutKaaret.contains(kaari)) continue;
                    // else kuljetutKaaret.add(kaari);
                    Node seuraava = laskin.laskeSeuraava(kasiteltava, kaari, naapuri, maali);
                    debugTieto.debugKaari(kaari, seuraava, naapuri);
                    kasittelyJarjestys.add(seuraava);                    
                }
            }
            
            kasitellytSolmut.add(solmu);
        }

        // GUI tulostusta varten talteen
        kaydytSolmut = kasitellytSolmut.keySet();
        
        debugTieto.debugRatkaisu(parhaatReitit);
        return parhaatReitit.get(0);
    }

    /**
     * Palauttaa listan solmuista, joissa on käyty
     * 
     * @return 
     */
    public Lista<Value> getKaydytSolmut() {
        return kaydytSolmut;
    }

    
    
    /**
     * Etsii a*-haulla verkosta parhaan reitin alkusolmusta loppusolmuun. 
     * Käyttää Javan PriorityQueuea ja HashSettiä
     *
     *
     * @param maali
     * @param alku
     * @return
     */
    public Node etsiReitti(Value alku, Value maali) {
        boolean dbg = this.isDebugMode();
        boolean dbgPrint = this.isDebugPrint();
        this.debugTieto = new DebugTieto();
        this.setDebugMode(dbg);
        this.setDebugPrint(dbgPrint);
        Node alkuTila = laskin.laskeSeuraava(null, null, alku, maali);
        int aika = 100; // vaikuttaa prioriteettijonon oletuskokoon
        final int tarkkuus = 100; // comparator-oliolle tarkkuus (1/tarkkuus), esim arvo 100 -> tarkkuus 0.01 kustannuspistettÃ¤
        Comparator<Node> comparator = alkuTila.vertailija(tarkkuus);

        PriorityQueue<Node> kasittelyJarjestys;
        kasittelyJarjestys = new PriorityQueue(aika * tarkkuus, comparator);

        kasittelyJarjestys.add(alkuTila);

        Lista<Node> parhaatReitit = new DynaaminenLista();
        double lowestCost = Integer.MAX_VALUE;

        HashSet<Value> kasitellytSolmut = new HashSet(1000);

        while (!kasittelyJarjestys.isEmpty()) {           

            Node kasiteltava = kasittelyJarjestys.poll(); 
            Value solmu = kasiteltava.getSolmu();
            
            
            // DEBUG: tietoa kÃ¤sittelystÃ¤ ja heuristiikan toiminnasta
            debugTieto.debugKasittelytieto(kasittelyJarjestys.size(), kasiteltava);
            debugTieto.debugHeuristiikka(kasiteltava);  // heuristiikan toiminta
            if (solmu.equals(maali)) {
                if ( lowestCost > kasiteltava.getKustannus() ) lowestCost = kasiteltava.getKustannus();
                parhaatReitit.add(kasiteltava);
                break;
            }
            if (kasiteltava.getKustannus()  > lowestCost ) {
                break;
            }
            
            if ( kasitellytSolmut.contains(solmu) ) continue;
            
            for (Value naapuri : verkko.getNaapurit(solmu)) {
                for (Edge kaari : verkko.getKaaret(solmu, naapuri)) {
                    // if ( kuljetutKaaret.contains(kaari)) continue;
                    // else kuljetutKaaret.add(kaari);
                    Node seuraava = laskin.laskeSeuraava(kasiteltava, kaari, naapuri, maali);
                    debugTieto.debugKaari(kaari, seuraava, naapuri);
                    kasittelyJarjestys.add(seuraava);                    
                }
            }
            
            kasitellytSolmut.add(solmu);
        }

        // GUI tulostusta varten talteen
        kaydytSolmut = new DynaaminenLista();
        
        debugTieto.debugRatkaisu(parhaatReitit);
        return parhaatReitit.get(0);
    }
    
    //////////////////////////////
    //DEBUG-setterit ja getterit//
    //////////////////////////////
    
    // Varsinainen debug-tavara on nyt DebugTieto-oliossa
    
    public boolean isDebugMode() {
        return debugTieto.isDebugMode();
    }

    public void setDebugMode(boolean debugMode) {
        debugTieto.setDebugMode(debugMode);
    }

    public boolean isDebugPrint() {
        return debugTieto.isDebugPrint();
    }

    public void setDebugPrint(boolean debugPrint) {
        debugTieto.setDebugPrint(debugPrint);
    }

    public String getRatkaisu() {
        return debugTieto.getRatkaisu();
    }

    public DebugTieto getDebugTieto() {
        return this.debugTieto;
    }
}
