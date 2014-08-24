/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Toiminto;
import com.mycompany.tiralabra_maven.gui.PiirrettavaRuutu;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import fileio.KuvanLukija;
import java.io.File;

public class Simulaatio {

    private Ruutu[][] maailma;
    private Koordinaatit alku;
    private Koordinaatit maali;
    private int hidaste;
    private boolean vinottain;
    private int leveys;
    private int korkeus;
    //private Paivitettava paivitettava;
    //private boolean valmis;
    private int[][] parhaatReitit;
    private Heuristiikka heuristiikka;

    private KuvanLukija kuvanLukija;

    //private Scanner sc;
    private Algoritmi algoritmi;

    //Jonkun toisen luokan asiaa(?):
    private Koordinaatit hiiri;
    private boolean hiiriPainettu;
    private Toiminto toiminto = Toiminto.SEINA;

    /**
     * Konstruktorissa annetaan parametrina tieto siitä, halutaanko hidastettu
     * vai nopea simulaatio.
     *
     */
    public Simulaatio() {
        this.leveys = 24;
        this.korkeus = 20;
        alustaMaailma();
        this.hidaste = 100;
        this.alku = new Koordinaatit(0, 0);
        this.maali = new Koordinaatit(9, 5);
        this.vinottain = true;
        this.kuvanLukija = new KuvanLukija();
        this.heuristiikka = new ManhattanHeuristiikka();
    }
    
    /**
     * Asettaa algoritmin suorituksessa käytetyn hidasteen. Oletus 100 ms.
     * @param hidaste
     */
    public void setHidaste(int hidaste) {
        this.hidaste = hidaste;
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

    /**
     * Tekee uuden ruudukon parametrina annetun kuvatiedoston perusteella ja
     * hävittää vanhan.
     *
     * @param tiedosto
     */
    public void lataaRuudukkoKuvasta(File tiedosto) {
        lopetaReitinEtsiminen();
        this.maailma = kuvanLukija.lueMaailmaKuvasta(tiedosto);
        if (maailma == null) {
            teeUusiRuudukko(10, 10);
            return;
        }
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
     * Asettaa algoritmin suorituksessa käytettävän heuristiikan.
     *
     * @param heuristiikka
     */
    public void setHeuristiikka(Heuristiikka heuristiikka) {
        this.heuristiikka = heuristiikka;
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
        this.algoritmi = new Algoritmi(maailma, hidaste, alku, maali, vinottain, heuristiikka);
        this.algoritmi.start();
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
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiri on tällä
     * hetkellä jonkun ruudukon ruudun päällä. Tätä tietoa voidaan käyttää
     * avuksi varsinaisessa käyttöliittymäkoodissa.
     *
     * @param x
     * @param y
     */
    public void hiiriRuudunPaalla(int x, int y) {
        if (hiiri == null || x != hiiri.getX() || y != hiiri.getY()) {
            this.hiiri = new Koordinaatit(x, y);
            suoritaToimintoJosHiiriPainettu();
        }
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiri on
     * poistunut ruudukon päältä.
     */
    public void hiiriPoistunut() {
        this.hiiri = null;
    }
    
    /**
     * Asettaa ruudun kustannuksen
     * @param ruutu
     * @param kustannus
     */
    public void asetaRuudunKustannus(Ruutu ruutu, int kustannus) {
        Ruutu.asetaKustannus(ruutu, kustannus);
    }

    /**
     * Palauttaa sen ruudun koordinaatit, jonka päällä hiiri on, tai null, jos
     * hiiri ei ole minkään ruudun päällä.
     *
     * @return hiiren koordinaatit
     */
    public Koordinaatit hiirenKoordinaatit() {
        return this.hiiri;
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiren nappi
     * on painettu pohjaan tai päästetty irti.
     */
    public void hiiriPainettu(boolean painettu) {
        //System.out.println("painettu: " + painettu);
        this.hiiriPainettu = painettu;
        suoritaToimintoJosHiiriPainettu();
    }

    private void suoritaToimintoJosHiiriPainettu() {
        if (this.hiiriPainettu) {
            switch (toiminto) {
                case SEINA:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.SEINA;
                    break;
                case LATTIA:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.LATTIA;
                    break;
                case HIEKKA:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.HIEKKA;
                    break;
                case RUOHO:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.RUOHO;
                    break;
                case VESI:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.VESI;
                    break;
                case ALKU:
                    this.alku = new Koordinaatit(hiiri.getX(), hiiri.getY());
                    break;
                case MAALI:
                    this.maali = new Koordinaatit(hiiri.getX(), hiiri.getY());
            }

        }
    }

    /**
     * Palauttaa true jos hiiren nappi on painettuna pohjaan.
     *
     * @return onko painettu pohjaan
     */
    public boolean onkoHiiriPainettu() {
        return this.hiiriPainettu;
    }

    /**
     * Palauttaa tiedon siitä, mikä hiiren toiminto on tällä hetkellä käytössä.
     *
     * @return toiminto
     */
    public Toiminto getValittuToiminto() {
        return this.toiminto;
    }

    /**
     * Asettaa toiminnon parametrina annetuksi toiminnoksi.
     *
     * @param toiminto
     */
    public void setToiminto(Toiminto toiminto) {
        this.toiminto = toiminto;
    }

    /**
     * Palauttaa maailman ruudun annetuissa koordinaateissa
     *
     * @param x
     * @param y
     * @return piirrettava ruutu
     */
    public PiirrettavaRuutu getMaailmaRuutu(int x, int y) {
        if (maailma == null) {
            return null;
        }

        return maailma[y][x];
    }

    /**
     * Palauttaa ruudun tilan annetuissa koordinaateissa
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

}
