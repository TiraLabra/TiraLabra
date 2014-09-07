package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.HeuristiikkaTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Toiminto;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import com.mycompany.tiralabra_maven.gui.PiirrettavaRuutu;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import fileio.KuvanLukija;
import java.io.File;

/**
 * Käyttöliittymästä riippumaton sovelluslogiikan luokka, jonka käyttöliittymä
 * tuntee. Sisältää metodit sovelluslogiikan toiminnan ohjaamiseen.
 *
 * @author mikko
 */
public class SovellusOhjain {

    private final KuvanLukija kuvanLukija;
    private final Simulaatio simulaatio;
    private Koordinaatit hiiri;
    private boolean hiiriPainettu;
    private Toiminto toiminto = Toiminto.SEINA;

    /**
     * Luo uuden sovellusohjaimen.
     */
    public SovellusOhjain() {
        this(new Simulaatio(), new KuvanLukija());
    }

    /**
     * Luo uuden sovellusohjaimen. Konstruktorille voidaan antaa parametreina
     * käytettävä simulaatio ja kuvanlukija.
     *
     * @param simulaatio
     * @param kuvanlukija
     */
    public SovellusOhjain(Simulaatio simulaatio, KuvanLukija kuvanlukija) {
        this.simulaatio = simulaatio;
        this.kuvanLukija = kuvanlukija;
    }

    /**
     * Tätä metodia tulee kutsua silloin, kun hiiri on jonkun ruudukon ruudun
     * päällä.
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
     * Tätä metodia tulee kutsua, kun hiiri on poistunut ruudukon alueelta.
     */
    public void hiiriPoistunut() {
        this.hiiri = null;
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
     *
     * @param painettu onko vai eikö ole painettu
     */
    public void hiiriPainettu(boolean painettu) {
        this.hiiriPainettu = painettu;
        suoritaToimintoJosHiiriPainettu();
    }

    private void suoritaToimintoJosHiiriPainettu() {
        if (this.hiiriPainettu) {
            switch (toiminto) {
                case SEINA:
                    simulaatio.setRuutu(hiiri.getX(), hiiri.getY(), Ruutu.SEINA);
                    break;
                case LATTIA:
                    simulaatio.setRuutu(hiiri.getX(), hiiri.getY(), Ruutu.LATTIA);
                    break;
                case HIEKKA:
                    simulaatio.setRuutu(hiiri.getX(), hiiri.getY(), Ruutu.HIEKKA);
                    break;
                case RUOHO:
                    simulaatio.setRuutu(hiiri.getX(), hiiri.getY(), Ruutu.RUOHO);
                    break;
                case VESI:
                    simulaatio.setRuutu(hiiri.getX(), hiiri.getY(), Ruutu.VESI);
                    break;
                case ALKU:
                    simulaatio.setAlkuPiste(new Koordinaatit(hiiri.getX(), hiiri.getY()));
                    break;
                case MAALI:
                    simulaatio.setMaali(new Koordinaatit(hiiri.getX(), hiiri.getY()));
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
     * Asettaa simulaatiossa käytettävän algoritmin.
     *
     * @param algoritmi algoritmin tyyppi
     */
    public void asetaAlgoritmi(AlgoritmiTyyppi algoritmi) {
        this.simulaatio.asetaAlgoritmi(algoritmi);
    }

    /**
     * Palauttaa tiedon siitä, minkä tyyppinen algoritmi on käytössä.
     *
     * @return algoritmin tyyppi
     */
    public AlgoritmiTyyppi getAlgoritmiTyyppi() {
        return this.simulaatio.getAlgoritmiTyyppi();
    }

    /**
     * Asettaa simulaatiossa käytettävän heuristiikan.
     *
     * @param heuristiikka
     */
    public void asetaHeuristiikka(HeuristiikkaTyyppi heuristiikka) {
        this.simulaatio.asetaHeuristiikka(heuristiikka);
    }

    /**
     * Palauttaa tiedon siitä, minkä tyyppinen heuristiikka on käytössä.
     *
     * @return heuristiikan tyyppi
     */
    public HeuristiikkaTyyppi getHeuristiikkaTyyppi() {
        return simulaatio.getHeuristiikkaTyyppi();
    }

    /**
     * Asettaa algoritmin suorituksessa käytetyn hidasteen millisekunteina.
     * Oletus 100 ms.
     *
     * @param hidaste
     */
    public void setHidaste(int hidaste) {
        this.simulaatio.setHidaste(hidaste);
    }

    /**
     * Palauttaa algoritmin suorituksessa käytetyn hidasteen millisekunteina.
     *
     * @return hidaste
     */
    public int getHidaste() {
        return this.simulaatio.getHidaste();
    }

    /**
     * Tekee uuden ruudukon ja hävittää vanhan.
     *
     * @param leveys uuden ruudukon leveys
     * @param korkeus uuden ruudukon korkeus
     */
    public void teeUusiRuudukko(int leveys, int korkeus) {
        this.simulaatio.teeUusiRuudukko(leveys, korkeus);
    }

    /**
     * Tekee uuden ruudukon parametrina annetun kuvatiedoston perusteella ja
     * hävittää vanhan.
     *
     * @param tiedosto
     */
    public void lataaRuudukkoKuvasta(File tiedosto) {
        Ruutu[][] maailma = kuvanLukija.lueMaailmaKuvasta(tiedosto);
        if (maailma == null) {
            //teeUusiRuudukko(10, 10);
            return;
        }
        simulaatio.setMaailma(maailma);
    }

    /**
     * Palauttaa ruudukon
     *
     * @return ruudukko
     */
    public Ruutu[][] getRuudukko() {
        return this.simulaatio.getRuudukko();
    }

    /**
     * Asettaa yksittäisen ruudun.
     *
     * @param x
     * @param y
     * @param ruutu
     */
    public void setRuutu(int x, int y, Ruutu ruutu) {
        this.simulaatio.setRuutu(x, y, ruutu);
    }

    /**
     * Palauttaa algoritmin alkupisteen.
     *
     * @return alkupiste
     */
    public Koordinaatit getAlkuPiste() {
        return this.simulaatio.getAlkuPiste();
    }

    /**
     * Palauttaa algoritmin maalipisteen.
     *
     * @return maalipiste
     */
    public Koordinaatit getMaali() {
        return this.simulaatio.getMaali();
    }

    /**
     * Käynnistää reittialgoritmin suorituksen.
     */
    public void etsiReitti() {
        this.simulaatio.etsiReitti();
    }

    /**
     * Lopettaa reittialgoritmin suorituksen ja pyyhkii reitin.
     */
    public void lopetaReitinEtsiminen() {
        this.simulaatio.lopetaReitinEtsiminen();
    }

    /**
     * Palauttaa ruudukon leveyden
     *
     * @return leveys
     */
    public int getLeveys() {
        return this.simulaatio.getLeveys();
    }

    /**
     * Palauttaa ruudukon korkeuden
     *
     * @return korkeus
     */
    public int getKorkeus() {
        return this.simulaatio.getKorkeus();
    }

    /**
     * Palauttaa tiedon siitä, onko simulaatio käynnissä
     *
     * @return onko simulaatio käynnissä
     */
    public boolean onkoSimulaatioKaynnissa() {
        return this.simulaatio.onkoSimulaatioKaynnissa();
    }

    /**
     * Asettaa vinottain liikkumisen sallituksi tai kielletyksi
     *
     * @param sallittu
     */
    public void asetaVinottainLiikkuminenSallituksi(boolean sallittu) {
        this.simulaatio.asetaVinottainLiikkuminenSallituksi(sallittu);
    }

    /**
     * Palauttaa tiedon siitä, onko vinottain liikkuminen sallittu.
     *
     * @return saako liikkua vinottain
     */
    public boolean saakoLiikkuaVinottain() {
        return this.simulaatio.saakoLiikkuaVinottain();
    }

    /**
     * Palauttaa tiedon siitä, onko algoritmin suorittaminen valmis, eli onko
     * reitti perille jo löytynyt.
     *
     * @return true, jos algoritmin suorittaminen on valmis ja false, jos ei ole
     */
    public boolean onkoValmis() {
        return this.simulaatio.onkoValmis();
    }

    /**
     * Asettaa ruudun kustannuksen
     *
     * @param ruutu
     * @param kustannus
     */
    public void asetaRuudunKustannus(Ruutu ruutu, int kustannus) {
        Ruutu.asetaKustannus(ruutu, kustannus);
    }

    /**
     * Asettaa päivitettävän otuksen. paivita() -metodia kutsutaan siinä
     * vaiheessa, kun suorituksessa oleva algoritmi on löytänyt reitin perille.
     *
     * @param paivitettava
     */
    public void setPaivitettava(Paivitettava paivitettava) {
        simulaatio.setPaivitettava(paivitettava);
    }

    /**
     * Palauttaa maailman ruudun annetuissa koordinaateissa
     *
     * @param x
     * @param y
     * @return piirrettava ruutu
     */
    public PiirrettavaRuutu getMaailmaRuutu(int x, int y) {
        return simulaatio.getMaailmaRuutu(x, y);

    }

    /**
     * Palauttaa ruudun tilan annetuissa koordinaateissa
     *
     * @param x
     * @param y
     * @return piirrettava ruutu
     */
    public PiirrettavaRuutu getTilaRuutu(int x, int y) {
        return simulaatio.getTilaRuutu(x, y);
    }

    /**
     * Palauttaa reitin pituuden, jos reitti on löytynyt
     *
     * @return reitin pituus
     */
    public int getReitinPituus() {
        if (simulaatio.getReitti() == null) {
            return 0;
        }
        return simulaatio.getReitti().getKuljettuMatka();
    }

}
