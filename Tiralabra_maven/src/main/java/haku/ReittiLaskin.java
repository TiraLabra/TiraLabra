/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.Kaari;
import verkko.Pysakki;
import verkko.Verkko;

/**
 * A*-haun apuolio. Arvioi jäljellä olevaa kustannusta ja laskee kustannuksen kustakin siirtymästä
 * @author E
 */
public class ReittiLaskin {
    
    private Verkko verkko;
    
    private double aikaPaino;
    private double matkaPaino;
    private double heurMatkaPaino;
    private double heurAikaPaino;
    /**
     * Matka metreinä, aika minuutteina
     */
    private double heurKulkunopeus;

    /**
     * Oletuskonstruktorilla painottaa vain matka-aikaa
     */
    public ReittiLaskin() {
        this.aikaPaino = 1;
        this.matkaPaino = 0;
        this.heurMatkaPaino = 0;
        this.heurAikaPaino = 1;
        this.heurKulkunopeus = 660; // 250 m / min -> 15km/h, 660 m / min -> 40km/h
        // kävely 100m/min -> 6km/h
    }
    
    
    
    /**
     * Palauttaa annetussa verkossa solmun ja maalin välisen etäisyyden arvion
     * @param verkko
     * @param solmu
     * @param maali
     * @return 
     */
    public double heuristiikka( Pysakki solmu, Pysakki maali ) {
        double etaisyys = Math.pow(solmu.getX()-maali.getX(), 2)+Math.pow(solmu.getY()-maali.getY(), 2);
        etaisyys        = Math.pow(etaisyys, 0.5);
        return heurAikaPaino*etaisyys/heurKulkunopeus+heurMatkaPaino*etaisyys;
    }
    /**
     * Laskee kaaren kulkemisen kustannuksen
     * 
     * @param verkko
     * @param kuljettu Reitti jota pitkin on edetty tähän
     * @param uusi     Seuraavaksi kuljettava kaari
     * @return 
     */
    public double kustannus( Reitti kuljettu, Kaari uusi ) {        
        /*
        Lisäoptiot: vältä x, y, suosi z vaikuttavat tässä WIP
        */
        return this.getOdotusAika(kuljettu, uusi)*aikaPaino + uusi.getKustannus()*aikaPaino + uusi.getEtaisyys()*matkaPaino;
    }
    /**
     * Laskee uuden kaaren kulkemiseen liittyvän odotusajan. Jos pysytään samalla linjalla, ei tarvitse laskea.
     * 
     * @param kuljettu
     * @param uusi
     * @return 
     */
    public double getOdotusAika( Reitti kuljettu, Kaari uusi) {
        double odotusAika = 0;
        
        if ( kuljettu.getKuljettuKaari() == null 
                || uusi.getLinjanNimi()  == null
                || !kuljettu.getKuljettuKaari().getLinjanNimi().equals(uusi.getLinjanNimi())) { // vaihdetaan pysäkillä linjaa
            // lisätään odotusAikaan kaaren linjan saapumisajan ja tämänhetkisen ajan erotus
            odotusAika = verkko.getOdotusAika( kuljettu.getAika(), kuljettu.getSolmu().getKoodi(), uusi.getLinjanNimi() );
        }
        return odotusAika;
    }
    /**
     * Tuottaa uuden reitti-olion, jossa kuljettava kaari on käsitelty.
     * 
     * @param kuljettu  Mistä tullaan
     * @param kaariNaapuriin    Uusi kaari
     * @param naapuri   Mihin mennään
     * @param maali     
     * @return 
     */
    public Reitti laskeSeuraava( Reitti kuljettu, Kaari kaariNaapuriin, Pysakki naapuri, Pysakki maali ) {
        Reitti seuraava = new Reitti();
        seuraava.setKustannus(kuljettu.getKustannus() + this.kustannus( kuljettu, kaariNaapuriin));
        seuraava.setArvioituKustannus(this.heuristiikka( naapuri, maali));
        seuraava.setPrevious(kuljettu);
        seuraava.setKuljettuKaari(kaariNaapuriin);
        seuraava.setAika(  kuljettu.getAika()+kaariNaapuriin.getKustannus()+this.getOdotusAika(kuljettu, kaariNaapuriin) );
        seuraava.setMatka( kuljettu.getMatka()+kaariNaapuriin.getEtaisyys() );   
        seuraava.setSolmu(naapuri);
        return seuraava;
    }
    
    // automaattiset metodit

    public Verkko getVerkko() {
        return verkko;
    }

    public void setVerkko(Verkko verkko) {
        this.verkko = verkko;
    }

    public double getAikaPaino() {
        return aikaPaino;
    }

    public void setAikaPaino(double aikaPaino) {
        this.aikaPaino = aikaPaino;
    }

    public double getMatkaPaino() {
        return matkaPaino;
    }

    public void setMatkaPaino(double matkaPaino) {
        this.matkaPaino = matkaPaino;
    }

    public double getHeurMatkaPaino() {
        return heurMatkaPaino;
    }

    public void setHeurMatkaPaino(double heurMatkaPaino) {
        this.heurMatkaPaino = heurMatkaPaino;
    }

    public double getHeurAikaPaino() {
        return heurAikaPaino;
    }

    public void setHeurAikaPaino(double heurAikaPaino) {
        this.heurAikaPaino = heurAikaPaino;
    }

    public double getHeurKulkunopeus() {
        return heurKulkunopeus;
    }

    public void setHeurKulkunopeus(double heurKulkunopeus) {
        this.heurKulkunopeus = heurKulkunopeus;
    }
    
}
