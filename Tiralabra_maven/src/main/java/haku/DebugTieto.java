/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import java.util.ArrayList;
import java.util.HashMap;
import verkko.Kaari;
import verkko.Pysakki;
import verkko.Reitti;
import verkko.Verkko;
import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;

/**
 * AStar-hausta tietoa tallennetaan näihin olioihin
 * 
 * @author E
 */
public class DebugTieto {
   
    /*
     //////////////////////////////////////////////////
     //Testaaminen toistaiseksi täällä: debug-metodit//
     //////////////////////////////////////////////////
     */
    
    // haun tilasta ja toiminnasta kertovia kenttiä:
    /**
     * Kerätäänkö debug-tietoa
     */
    private boolean debugMode = false;
    /**
     * Debug-tulosteet
     */
    private boolean debugPrint = false;    
    /**
     * Kaaret, joita pitkin on jo kuljettu. Kaaren tallennusmuoto kaaren
     * toString
     */
    private ArrayList<String> kasitellytKaaret = new ArrayList();
    /**
     * Taulussa pysäkki ja pienin kustannus jolla sinne päästään
     */
    private HashMap<Value, Double> nopeimmatSiirtymatPysakille = new HashMap();
    /**
     * Jos heuristiikka toimii, tämä kasvaa (h(x) pienempi/yhtäsuuri kuin
     * d(x,y)+h(y))
     */
    private double heuristiikanOnnistumiset = 0;
    /**
     * Käsitellyt reitit (jonosta pois käsiteltäväksi)
     */
    private double kasiteltyja = 0;
    /**
     * Prioriteettijonon koko
     */
    private double keskimaarainenJononKoko = 0;
    /**
     * Toistuuko kaari haun aikana
     */
    private double toistuvaKaari = 0;
    /**
     * Kaaret joita kuljetaan ensimmäistä kertaa
     */
    private double uusiKaari = 0;
    /**
     * Kerrat joilla nopeimmatSiirtymatPysakille-taulu sisältää pysäkille
     * pienemmän kustannuksen
     */
    private double solmussaHitaammin = 0;
    /**
     * Kerrat joilla nopeimmatSiirtymatPysakille-tauluun päivitetään pienempi
     * kustannus pysäkille
     */
    private double solmussaNopeammin = 0;
    /**
     * Apumuuttuja, jossa on viimeksi käsitellyn solmun kokonaiskustannus
     */
    private double totalCostLast = 0;
    /**
     * Kokonaiskustannusten muutosten summa suorituksen aikana
     */
    private double totalCostDiffSum = 0;
    /**
     * Merkkijonoesitys haun vastauksesta tallennetaan tähän
     */
    private String ratkaisu;

    /**
     * Kun haku on valmis, ratkaisu-kenttään tallennetaan merkkijonoesitys
     * ratkaisusta ja algoritmin toiminnasta
     *
     * @return
     */
    public String getRatkaisu() {
        return ratkaisu;
    }

    /**
     * Laskee heuristiikan onnistumisten osuuden.
     *
     * @return Heuristiikan onnistumisten osuus käsitellyistä reiteistä (0-1)
     */
    public double getHeuristiikanOnnistumiset() {
        return heuristiikanOnnistumiset / kasiteltyja;
    }

    /**
     * Kaaren käsittelystä tallennetaan tietoa
     *
     * @param kaari
     * @param seuraava
     * @param naapuri
     */
    public void debugKaari(Edge kaari, Node seuraava, Value naapuri) {
        if (!debugMode) return;
        if (kasitellytKaaret.contains(kaari.toString())) {
            this.toistuvaKaari++;
        } else {
            kasitellytKaaret.add(kaari.toString());
            this.uusiKaari++;
        }
        if (nopeimmatSiirtymatPysakille.containsKey(naapuri) && // solmuun on päästy aiemmin käsittelyjärjestyksessä
                nopeimmatSiirtymatPysakille.get(naapuri) <= seuraava.getKustannus()) {  // ja kustannus ollut pienempi
            this.solmussaHitaammin++;
        } else {
            this.solmussaNopeammin++;
            nopeimmatSiirtymatPysakille.put(naapuri, seuraava.getKustannus()); // tallennetaan solmuun pääsemisen kustannus
        }
    }

    /**
     * Heuristiikalla tulle olla h(x) pienempi/yhtäsuuri kuin d(x,y)+h(y)
     * kaikilla x, y. Tallennetaan onnistumisesta tieto
     *
     * @param seuraava Käsittelyvuorossa oleva reitti
     */
    public void debugHeuristiikka(Node seuraava) {
        if (!debugMode) return;
        Node kasiteltava = seuraava.getPrevious();
        if (kasiteltava == null) { // ensimmäinen solmu!
            heuristiikanOnnistumiset++;
            return;
        }
        boolean heuristiikkaOnnistuu = (kasiteltava.getArvioituKustannus()
                <= seuraava.getKustannus() - kasiteltava.getKustannus()
                + seuraava.getArvioituKustannus());
        if (heuristiikkaOnnistuu) {
            heuristiikanOnnistumiset++;
        }
    }

    /**
     * Tietoja käsittelyvuorossa olevasta reitistä
     *
     * @param jononKoko Käsittelyjärjestys-jonon koko
     * @param kasiteltava
     * @return
     */
    public String debugKasittelytieto(int jononKoko, Node kasiteltava) {
        String tieto = "";
        if (!debugMode) return tieto;
        try {
            Reitti d = (Reitti) kasiteltava;
            tieto += ("{ Solmu=" + d.getSolmu().getKoodi() + ", travelTime=" + d.getAika()
                    + ", travelDist=" + d.getMatka());
        } catch (Exception e) {
            tieto += ("{ Solmu=" + kasiteltava.toString());
        }

        kasiteltyja++;
        keskimaarainenJononKoko += jononKoko;
        double totalCost = kasiteltava.getArvioituKustannus() + kasiteltava.getKustannus();
        double totalCostDiff = -totalCostLast + totalCost;
        tieto += (", cost=" + kasiteltava.getKustannus()
                + ", estCost=" + kasiteltava.getArvioituKustannus()
                + ", kuljettuKaari=" + kasiteltava.getKuljettuKaari()
                + ", tcDiff=" + (-totalCostLast + totalCost) + " }"
                + ", in queue=" + jononKoko + " }");
        totalCostLast = totalCost;
        totalCostDiffSum += totalCostDiff;
        if (this.debugPrint) {
            System.out.println("" + tieto);
        }
        return tieto;
    }

    /**
     * A*-algoritmin lopuksi löydetyistä reiteistä ja toimintatiedosta tehdään
     * esitys
     *
     * @param parhaatReitit
     * @return
     */
    public String debugRatkaisu(Iterable<Node> parhaatReitit) {
        
        String ratkaisut = "";
        // if (!debugMode) return ratkaisut;
        // ratkaisut += ("Yhtä hyviä ratkaisuja " + parhaatReitit.size()) + "\n";
        try {
            for (Node n : parhaatReitit) {
                Reitti r = (Reitti) n;
                ratkaisut += ("Reitti={"
                        + " getAika=" + r.getAika()
                        + " getMatka=" + r.getMatka()
                        + " getKustannus=" + r.getKustannus()
                        + "}")
                        + "\n";
                //ratkaisut +=  r.toString();
            }
        } catch (Exception e) {

        }
        if (this.debugMode) ratkaisut += ("Tiedot={ kasiteltyja=" + kasiteltyja
                + ", keskimaarainenJononKoko=" + (keskimaarainenJononKoko / kasiteltyja)
                + ", toistuvaKaari=" + toistuvaKaari
                + ", uusiKaari=" + uusiKaari
                + ", kuljetutKaaret=" + (uusiKaari + toistuvaKaari)
                + ", solmussaHitaammin=" + solmussaHitaammin
                + ", solmussaNopeammin=" + solmussaNopeammin
                + ", totalCostDiffSum/n=" + (totalCostDiffSum / kasiteltyja)
                + ", heuristiikanOnnistumiset/n=" + ((1 + heuristiikanOnnistumiset) / kasiteltyja)
                );
        if (this.debugPrint) {
            System.out.println("" + ratkaisut);
        }
        this.ratkaisu = ratkaisut;
        return ratkaisut;
    }

    /**
     * Tulostaa pysäkin naapurit ja kaaret niihin
     *
     * @param verkko
     * @param alku
     */
    public void debugPrint(Verkko verkko, Pysakki alku) {
        if (!debugMode) return;
        System.out.println("" + alku);
        for (Pysakki p : verkko.getNaapurit(alku)) {
            System.out.println("NAAPURI " + p);
            for (Kaari kaari : verkko.getKaaret(alku, p)) {
                System.out.println("REITTI " + kaari);
            }
        }

    }

    //DEBUG-setterit ja getterit//
    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isDebugPrint() {
        return debugPrint;
    }

    public void setDebugPrint(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }    
}
