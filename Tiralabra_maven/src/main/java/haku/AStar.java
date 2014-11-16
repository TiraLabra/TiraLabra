/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import tira.PrioriteettiJonoListalla;
import tira.PrioriteettiJonoListalla;
import verkko.Kaari;
import verkko.Pysakki;
import verkko.Verkko;

/**
 * A*-haun toteutus
 *
 * @author E
 */
public class AStar {

    /**
     * Debug: Käydäänkö koko jono läpi vai lopetetaanko parhaaseen ratkaisuun
     */
    private boolean debugMode = true;
    /**
     * Debug-tulosteet
     */
    private boolean debugPrint = true;

    /**
     * Verkko, jossa hakuja suoritetaan
     */
    private Verkko verkko;
    /**
     * Apuolio reittien kustannusten ja jäljelläolevan kustannuksen arviointiin
     */
    private ReittiLaskin laskin;

    //WIP
    /**
     * haun katkaisua varten
     */
    private final double EPSILON = 0.25;

    ///////////////////
    // KONSTRUKTORIT //
    ///////////////////
    /**
     *
     * @param verkko
     * @param laskin
     */
    public AStar(Verkko verkko, ReittiLaskin laskin) {
        this.verkko = verkko;
        this.laskin = laskin;
        this.laskin.setVerkko(verkko);
    }

    /**
     *
     * @param verkko Verkko, jossa haut suoritetaan
     */
    public AStar(Verkko verkko) {
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
     -Entä jos alkutila/lopputila ei ole verkossa: esim. pysäkkiverkkoon päästään kävelemällä pysäkille?
     -Epäyhtenäiset verkot
     -Entä jos on nopeampaa kävellä viereiselle pysäkille
     -Alkuajan esitys, ajan esitys
     */
    public Reitti etsiReitti(Pysakki alku, Pysakki maali) {
        return this.etsiReitti(alku, maali, 0);
    }    
    /**
     * Etsii a*-haulla verkosta parhaan reitin alkusolmusta loppusolmuun.
     * 
     *
     * @param maali
     * @param alku
     * @param mode Valinta: mitä prioriteettijonoa käytetään. 0=javan, 1=oma prioriteettijono listalla
     * @return
     */
    public Reitti etsiReitti(Pysakki alku, Pysakki maali, int mode ) {

        Reitti alkuTila = new Reitti();
        alkuTila.setKustannus(0);
        alkuTila.setSolmu(alku);
        alkuTila.setArvioituKustannus(laskin.heuristiikka(alku, maali));
        
        int   aika         = 40; 
        final int tarkkuus = 100;
        Comparator<Reitti> comparator = new Comparator<Reitti>() {

            public int compare(Reitti t1, Reitti t2) {
                return (int) ( tarkkuus*(t1.getArvioituKustannus() + t1.getKustannus() - t2.getArvioituKustannus() - t2.getKustannus()));
            }
        };
        
        PriorityQueue<Reitti> kasittelyJarjestys;
        if (mode==1) kasittelyJarjestys = new PrioriteettiJonoListalla(aika*tarkkuus, comparator);
        else kasittelyJarjestys= new PriorityQueue(aika*tarkkuus, comparator);
        
        kasittelyJarjestys.add(alkuTila);
        
        ArrayList<Reitti> parhaatReitit = new ArrayList();
        double lowestCost = Integer.MAX_VALUE;

        while (!kasittelyJarjestys.isEmpty()) {           // kaivannee katkaisun joho

            Reitti kasiteltava = kasittelyJarjestys.poll(); // otetaan jonon 1. pois käsiteltäväksi
            Pysakki solmu = kasiteltava.getSolmu();
            // DEBUG: tietoa käsittelystä ja heuristiikan toiminnasta
            debugKasittelytieto(kasittelyJarjestys.size(), kasiteltava);
            debugHeuristiikka(kasiteltava);  // heuristiikan toiminta
            if (solmu.equals(maali)) {
                if (!this.debugMode) {
                    parhaatReitit.add(kasiteltava); // palautetaan vain paras
                    break;
                } else {
                    if (kasiteltava.getKustannus() < lowestCost) {
                        // pienempi kuin, pitäisi tapahtua vain kerran
                        lowestCost = kasiteltava.getKustannus();
                    } else if (kasiteltava.getKustannus() > lowestCost + EPSILON) {
                        break;  // parempi ratkaisu löytynyt, tämä on yli tietyn rajan verran huonompi kuin paras ratkaisu: voidaan lopettaa
                    }
                    parhaatReitit.add(kasiteltava);
                    continue;
                }
            }
            if (kasiteltava.getKustannus() + kasiteltava.getArvioituKustannus() > lowestCost) {
                continue;
            }
            for (Pysakki naapuri : verkko.getNaapurit(solmu)) {
                for (Kaari kaari : verkko.getKaaret(solmu, naapuri)) {
                    Reitti seuraava = this.laskeSeuraava(kasiteltava, kaari, naapuri, maali);
                    debugKaari(kaari, seuraava, naapuri);
                    kasittelyJarjestys.add(seuraava);
                }
            }
        }

        debugRatkaisu(parhaatReitit);
        return parhaatReitit.get(0);
    }

    /**
     * Tuottaa uuden reitti-olion, jossa kuljettava kaari on käsitelty.
     *
     * @param kuljettu Mistä tullaan
     * @param kaariNaapuriin Uusi kaari
     * @param naapuri Mihin mennään
     * @param maali
     * @return
     */
    public Reitti laskeSeuraava(Reitti kuljettu, Kaari kaariNaapuriin, Pysakki naapuri, Pysakki maali) {
        /*
         Kaarten käsittely: 
         * voi olla useita mahdollisia kaaria: esim. tien yli voi mennä, tai alikulun kautta, pysäkiltä toiselle pääsee monella bussilla
         -> Kaaret listana OK!
         * entä jos kustannus vaihtuu sen mukaan, milloin mennään (esim. bussipysäkillä odotusaika)
         -> Tilaan aika-muuttuja, kaareen tieto siitä miten vaihtelee ajan mukaan OK!
         * entä jos edellinen kaari vaikuttaa kustannukseen ( esim. ollaan jo bussissa matkalla eteenpäin, esim2. autolla suoraan: ei tarvitse u-käännöstä yms)
         -> Kaareen tieto tyypistä OK!               
         */
        Reitti seuraava = new Reitti();
        seuraava.setKustannus(kuljettu.getKustannus() + laskin.kustannus(kuljettu, kaariNaapuriin));
        seuraava.setArvioituKustannus(laskin.heuristiikka(naapuri, maali));
        seuraava.setPrevious(kuljettu);
        seuraava.setKuljettuKaari(kaariNaapuriin);
        seuraava.setAika(kuljettu.getAika() + kaariNaapuriin.getKustannus() + laskin.getOdotusAika(kuljettu, kaariNaapuriin));
        seuraava.setMatka(kuljettu.getMatka() + kaariNaapuriin.getEtaisyys());
        seuraava.setSolmu(naapuri);
        return seuraava;
    }

    /*
     //////////////////////////////////////////////////
     //Testaaminen toistaiseksi täällä: debug-metodit//
     //////////////////////////////////////////////////
     */
    // haun tilasta ja toiminnasta kertovia kenttiä:
    /**
     * Kaaret, joita pitkin on jo kuljettu. Kaaren tallennusmuoto kaaren
     * toString
     */
    private ArrayList<String> kasitellytKaaret = new ArrayList();
    /**
     * Taulussa pysäkki ja pienin kustannus jolla sinne päästään
     */
    private HashMap<Pysakki, Double> nopeimmatSiirtymatPysakille = new HashMap();
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
    private void debugKaari(Kaari kaari, Reitti seuraava, Pysakki naapuri) {
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
    private void debugHeuristiikka(Reitti seuraava) {
        Reitti kasiteltava = seuraava.getPrevious();
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
    private String debugKasittelytieto(int jononKoko, Reitti kasiteltava) {
        String tieto = "";
        kasiteltyja++;
        keskimaarainenJononKoko += jononKoko;
        double totalCost = kasiteltava.getArvioituKustannus() + kasiteltava.getKustannus();
        double totalCostDiff = -totalCostLast + totalCost;
        tieto += ("{ Solmu=" + kasiteltava.getSolmu().getKoodi() + ", travelTime=" + kasiteltava.getAika()
                + ", travelDist=" + kasiteltava.getMatka()
                + ", cost=" + kasiteltava.getKustannus()
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
    private String debugRatkaisu(ArrayList<Reitti> parhaatReitit) {

        String ratkaisut = "";
        ratkaisut += ("Yhtä hyviä ratkaisuja " + parhaatReitit.size()) + "\n";
        for (Reitti r : parhaatReitit) {
            ratkaisut += ("Reitti={"
                    + " getAika=" + r.getAika()
                    + " getMatka=" + r.getMatka()
                    + " getKustannus=" + r.getKustannus()
                    + "}")
                    + "\n";
            //ratkaisut +=  r.toString();
        }
        ratkaisut += ("Tiedot={ kasiteltyja=" + kasiteltyja
                + ", keskimaarainenJononKoko=" + (keskimaarainenJononKoko / kasiteltyja)
                + ", toistuvaKaari=" + toistuvaKaari
                + ", uusiKaari=" + uusiKaari
                + ", kuljetutKaaret=" + (uusiKaari + toistuvaKaari)
                + ", solmussaHitaammin=" + solmussaHitaammin
                + ", solmussaNopeammin=" + solmussaNopeammin
                + ", totalCostDiffSum/n=" + (totalCostDiffSum / kasiteltyja)
                + ", heuristiikanOnnistumiset/n=" + ((1 + heuristiikanOnnistumiset) / kasiteltyja)
                + ", laskin=" + laskin + "  }");
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
    private void debugPrint(Verkko verkko, Pysakki alku) {
        System.out.println("" + alku);
        for (Pysakki p : verkko.getNaapurit(alku)) {
            System.out.println("NAAPURI " + p);
            for (Kaari kaari : verkko.getKaaret(alku, p)) {
                System.out.println("REITTI " + kaari);
            }
        }

    }
}
