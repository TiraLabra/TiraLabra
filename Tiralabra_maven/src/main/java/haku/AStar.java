/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import tira.DynaaminenLista;
import tira.Hajautuslista;
import tira.Lista;
import tira.PrioriteettiJonoListalla;
import verkko.Pysakki;
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

    private DebugTieto debugTieto;

    //WIP
    /**
     * haun katkaisua varten
     */
    private final double EPSILON = 1;

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

    /*
     WIP:
     -EntÃ¤ jos alkutila/lopputila ei ole verkossa: esim. pysÃ¤kkiverkkoon pÃ¤Ã¤stÃ¤Ã¤n kÃ¤velemÃ¤llÃ¤ pysÃ¤kille?
     -EpÃ¤yhtenÃ¤iset verkot
     -EntÃ¤ jos on nopeampaa kÃ¤vellÃ¤ viereiselle pysÃ¤kille
     -Alkuajan esitys, ajan esitys
     */
    /**
     * Etsii a*-haulla verkosta parhaan reitin alkusolmusta loppusolmuun.
     * KÃ¤yttÃ¤Ã¤ omaa prioriteettijonoa
     *
     *
     * @param maali
     * @param alku
     * @return
     */
    public Node etsiReittiOma(Value alku, Value maali) {
        Node alkuTila = laskin.laskeSeuraava(null, null, alku, maali);
        int aika = 45; // vaikuttaa prioriteettijonon oletuskokoon
        final int tarkkuus = 100; // comparator-oliolle tarkkuus (1/tarkkuus), esim arvo 100 -> tarkkuus 0.01 kustannuspistettÃ¤
        Comparator<Node> comparator = alkuTila.vertailija(tarkkuus);

        PrioriteettiJonoListalla<Node> kasittelyJarjestys;
        kasittelyJarjestys = new PrioriteettiJonoListalla(aika * tarkkuus, comparator);

        kasittelyJarjestys.add(alkuTila);

        Lista<Node> parhaatReitit = new DynaaminenLista();
        double lowestCost = Integer.MAX_VALUE;
        
        Hajautuslista<Edge> kuljetutKaaret = new Hajautuslista();

        while (!kasittelyJarjestys.isEmpty()) {           // kaivannee katkaisun joho

            Node kasiteltava = kasittelyJarjestys.poll(); // otetaan jonon 1. pois kÃ¤siteltÃ¤vÃ¤ksi
            Value solmu = kasiteltava.getSolmu();
            // DEBUG: tietoa kÃ¤sittelystÃ¤ ja heuristiikan toiminnasta
            debugTieto.debugKasittelytieto(kasittelyJarjestys.size(), kasiteltava);
            debugTieto.debugHeuristiikka(kasiteltava);  // heuristiikan toiminta
            if (solmu.equals(maali)) {
                if (kasiteltava.getKustannus() < lowestCost) {
                    // pienempi kuin, pitÃ¤isi tapahtua vain kerran
                    lowestCost = kasiteltava.getKustannus();
                } else if (kasiteltava.getKustannus() > lowestCost + EPSILON) {
                    break;  // parempi ratkaisu lÃ¶ytynyt, tÃ¤mÃ¤ on yli tietyn rajan verran huonompi kuin paras ratkaisu: voidaan lopettaa
                }
                parhaatReitit.add(kasiteltava);
                continue;
                // }
            }
            if (kasiteltava.getKustannus() + kasiteltava.getArvioituKustannus() > lowestCost) {
                // continue;
            }
            for (Value naapuri : verkko.getNaapurit(solmu)) {
                for (Edge kaari : verkko.getKaaret(solmu, naapuri)) {
                    if ( kuljetutKaaret.contains(kaari)) continue;
                    Node seuraava = laskin.laskeSeuraava(kasiteltava, kaari, naapuri, maali);
                    debugTieto.debugKaari(kaari, seuraava, naapuri);
                    kasittelyJarjestys.add(seuraava);
                    kuljetutKaaret.add(kaari);
                }
            }
        }

        debugTieto.debugRatkaisu(parhaatReitit);
        return parhaatReitit.get(0);
    }

    /**
     * Etsii a*-haulla verkosta parhaan reitin alkusolmusta loppusolmuun.
     *
     *
     * @param maali
     * @param alku
     * @return
     */
    public Node etsiReitti(Value alku, Value maali) {

        Node alkuTila = laskin.laskeSeuraava(null, null, alku, maali);

        int aika = 45; // vaikuttaa prioriteettijonon oletuskokoon
        final int tarkkuus = 100; // comparator-oliolle tarkkuus (1/tarkkuus), esim arvo 100 -> tarkkuus 0.01 kustannuspistettÃ¤
        Comparator<Node> comparator = alkuTila.vertailija(tarkkuus);

        Queue<Node> kasittelyJarjestys;
        kasittelyJarjestys = new PriorityQueue(aika * tarkkuus, comparator);

        kasittelyJarjestys.add(alkuTila);

        ArrayList<Node> parhaatReitit = new ArrayList();
        double lowestCost = Integer.MAX_VALUE;

        while (!kasittelyJarjestys.isEmpty()) {           // kaivannee katkaisun joho

            Node kasiteltava = kasittelyJarjestys.poll(); // otetaan jonon 1. pois kÃ¤siteltÃ¤vÃ¤ksi
            Value solmu = kasiteltava.getSolmu();
            // DEBUG: tietoa kÃ¤sittelystÃ¤ ja heuristiikan toiminnasta
            debugTieto.debugKasittelytieto(kasittelyJarjestys.size(), kasiteltava);
            debugTieto.debugHeuristiikka(kasiteltava);  // heuristiikan toiminta
            if (solmu.equals(maali)) {
                // if (!this.debugMode) {
                //    parhaatReitit.add(kasiteltava); // palautetaan vain paras
                //    break;
                //} else {
                if (kasiteltava.getKustannus() < lowestCost) {
                    // pienempi kuin, pitÃ¤isi tapahtua vain kerran
                    lowestCost = kasiteltava.getKustannus();
                } else if (kasiteltava.getKustannus() > lowestCost + EPSILON) {
                    break;  // parempi ratkaisu lÃ¶ytynyt, tÃ¤mÃ¤ on yli tietyn rajan verran huonompi kuin paras ratkaisu: voidaan lopettaa
                }
                parhaatReitit.add(kasiteltava);
                continue;
                // }
            }
            if (kasiteltava.getKustannus() + kasiteltava.getArvioituKustannus() > lowestCost) {
                continue;
            }
            for (Value naapuri : verkko.getNaapurit(solmu)) {
                for (Edge kaari : verkko.getKaaret(solmu, naapuri)) {
                    Node seuraava = laskin.laskeSeuraava(kasiteltava, kaari, naapuri, maali);
                    debugTieto.debugKaari(kaari, seuraava, naapuri);
                    kasittelyJarjestys.add(seuraava);
                }
            }
        }

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
