package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.HeuristiikkaTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import com.mycompany.tiralabra_maven.gui.PiirrettavaRuutu;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.Algoritmi;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.AlgoritmiTehdas;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.Heuristiikka;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.Solmu;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.HeuristiikkaTehdas;

/**
 * Simulaatio tuntee ruudukon ja tiedon alku- ja maalipisteen koordinaateista ja
 * suorituksessa mahdollisesti olevan algoritmin.
 *
 * @author mikko
 */
public class Simulaatio {

    private Ruutu[][] maailma;
    private Koordinaatit alku;
    private Koordinaatit maali;
    private int hidaste;
    private boolean vinottain;
    private int leveys;
    private int korkeus;
    private Paivitettava paivitettava;

    private final HeuristiikkaTehdas heuristiikkaTehdas;
    private HeuristiikkaTyyppi heuristiikkaTyyppi;
    private Heuristiikka heuristiikka;

    private final AlgoritmiTehdas algoritmiTehdas;
    private AlgoritmiTyyppi algoritmiTyyppi;
    private Algoritmi algoritmi;

    /**
     * Luo uuden simulaation.
     */
    public Simulaatio() {
        this.leveys = 24;
        this.korkeus = 20;
        alustaMaailma();
        this.hidaste = 100;
        this.alku = new Koordinaatit(0, 0);
        this.maali = new Koordinaatit(9, 5);
        this.vinottain = false;
        this.heuristiikkaTyyppi = HeuristiikkaTyyppi.MANHATTAN;
        this.algoritmiTyyppi = AlgoritmiTyyppi.BREADTH_FIRST;
        this.algoritmiTehdas = new AlgoritmiTehdas();
        this.heuristiikkaTehdas = new HeuristiikkaTehdas();
    }

    private void alustaMaailma() {
        this.maailma = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                maailma[y][x] = Ruutu.LATTIA;
            }
        }
    }

    /**
     * Asettaa simulaatiossa käytettävän algoritmin.
     *
     * @param algoritmi algoritmin tyyppi
     */
    public void asetaAlgoritmi(AlgoritmiTyyppi algoritmi) {
        this.algoritmiTyyppi = algoritmi;
    }

    /**
     * Palauttaa tiedon siitä, minkä tyyppinen algoritmi on käytössä.
     *
     * @return algoritmin tyyppi
     */
    public AlgoritmiTyyppi getAlgoritmiTyyppi() {
        return this.algoritmiTyyppi;
    }

    /**
     * Asettaa simulaatiossa käytettävän heuristiikan.
     *
     * @param heuristiikka
     */
    public void asetaHeuristiikka(HeuristiikkaTyyppi heuristiikka) {
        this.heuristiikkaTyyppi = heuristiikka;
    }

    /**
     * Palauttaa tiedon siitä, minkä tyyppinen heuristiikka on käytössä.
     *
     * @return heuristiikan tyyppi
     */
    public HeuristiikkaTyyppi getHeuristiikkaTyyppi() {
        return this.heuristiikkaTyyppi;
    }

    /**
     * Asettaa algoritmin suorituksessa käytetyn hidasteen millisekunteina.
     * Oletus 100 ms.
     *
     * @param hidaste
     */
    public void setHidaste(int hidaste) {
        this.hidaste = hidaste;
    }

    /**
     * Palauttaa algoritmin suorituksessa käytetyn hidasteen millisekunteina.
     *
     * @return hidaste
     */
    public int getHidaste() {
        return this.hidaste;
    }

    /**
     * Tekee uuden ruudukon ja hävittää vanhan.
     *
     * @param leveys uuden ruudukon leveys
     * @param korkeus uuden ruudukon korkeus
     */
    public void teeUusiRuudukko(int leveys, int korkeus) {
        lopetaReitinEtsiminen();
        this.leveys = leveys;
        this.korkeus = korkeus;
        alustaMaailma();
    }

    public void setMaailma(Ruutu[][] maailma) {
        if (maailma == null) {
            return;
        }
        lopetaReitinEtsiminen();
        this.maailma = maailma;
        this.korkeus = maailma.length;
        this.leveys = maailma[0].length;
    }

    /**
     * Palauttaa ruudukon
     *
     * @return ruudukko
     */
    public Ruutu[][] getRuudukko() {
        return this.maailma;
    }

    /**
     * Asettaa yksittäisen ruudun.
     *
     * @param x
     * @param y
     * @param ruutu
     */
    public void setRuutu(int x, int y, Ruutu ruutu) {
        this.maailma[y][x] = ruutu;
    }

    /**
     * Asettaa algoritmin alkupisteen
     *
     * @param koord
     */
    public void setAlkuPiste(Koordinaatit koord) {
        this.alku = koord;
    }

    /**
     * Palauttaa algoritmin alkupisteen.
     *
     * @return alkupiste
     */
    public Koordinaatit getAlkuPiste() {
        return this.alku;
    }

    /**
     * Asettaa algoritmin maalipisteen.
     *
     * @param koord
     */
    public void setMaali(Koordinaatit koord) {
        this.maali = koord;
    }

    /**
     * Palauttaa algoritmin maalipisteen.
     *
     * @return maalipiste
     */
    public Koordinaatit getMaali() {
        return this.maali;
    }

    /**
     * Käynnistää reittialgoritmin suorituksen.
     */
    public void etsiReitti() {
        this.heuristiikka = heuristiikkaTehdas.getHeuristiikka(heuristiikkaTyyppi);
        this.algoritmi = algoritmiTehdas.luoAlgoritmi(algoritmiTyyppi, maailma, hidaste, alku, maali, vinottain, heuristiikka);

        this.algoritmi.setPaivitettava(paivitettava);
        new Thread(this.algoritmi).start();
    }

    /**
     * Lopettaa reittialgoritmin suorituksen ja pyyhkii reitin.
     */
    public void lopetaReitinEtsiminen() {
        if (this.algoritmi == null) {
            return;
        }
        this.algoritmi.lopeta();
        this.algoritmi = null;
    }

    /**
     * Palauttaa ruudukon leveyden
     *
     * @return leveys
     */
    public int getLeveys() {
        return this.leveys;
    }

    /**
     * Palauttaa ruudukon korkeuden
     *
     * @return korkeus
     */
    public int getKorkeus() {
        return this.korkeus;
    }

    /**
     * Palauttaa tiedon siitä, onko simulaatio käynnissä
     *
     * @return onko simulaatio käynnissä
     */
    public boolean onkoSimulaatioKaynnissa() {
        return this.algoritmi != null;
    }

    /**
     * Asettaa vinottain liikkumisen sallituksi tai kielletyksi
     *
     * @param sallittu
     */
    public void asetaVinottainLiikkuminenSallituksi(boolean sallittu) {
        this.vinottain = sallittu;
    }

    /**
     * Palauttaa tiedon siitä, onko vinottain liikkuminen sallittu.
     *
     * @return saako liikkua vinottain
     */
    public boolean saakoLiikkuaVinottain() {
        return this.vinottain;
    }

    /**
     * Palauttaa tiedon siitä, onko algoritmin suorittaminen valmis, eli onko
     * reitti perille jo löytynyt.
     *
     * @return true, jos algoritmin suorittaminen on valmis ja false, jos ei ole
     */
    public boolean onkoValmis() {
        return this.algoritmi.onkoValmis();
    }

    /**
     * Jos algoritmin suoritus on valmis (onkoValmis() -metodi palauttaa true),
     * niin tämä metodi palauttaa valmiin reitin maalisolmun, joka on linkitetty
     * lista kohti lähtöpistettä
     *
     * @return reitin viimeinen solmu
     */
    public Solmu getReitti() {
        if (algoritmi == null) {
            return null;
        }

        return algoritmi.getReitti();
    }

    /**
     * Palauttaa ruudun tyypin annetuissa koordinaateissa.
     *
     * @param x
     * @param y
     * @return ruudun tyyppi
     */
    public PiirrettavaRuutu getMaailmaRuutu(int x, int y) {
        return maailma[y][x];
    }

    /**
     * Palauttaa ruudun tilan annetuissa koordinaateissa
     *
     * @param x
     * @param y
     * @return piirrettava ruutu
     */
    public PiirrettavaRuutu getTilaRuutu(int x, int y) {
        if (algoritmi != null) {
            RuudunTila ruuduntila = algoritmi.getRuudunTila(x, y);
            if (ruuduntila != null) {
                return ruuduntila;
            }
        }
        return null;
    }

    /**
     * Asettaa päivitettävän. Tämä päivitettävä annetaan edelleen algoritmille.
     * Algoritmi kutsuu tämän otuksen paivita()-metodia kun algoritmin suoritus
     * on valmis.
     *
     * @param paivitettava
     */
    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

}
