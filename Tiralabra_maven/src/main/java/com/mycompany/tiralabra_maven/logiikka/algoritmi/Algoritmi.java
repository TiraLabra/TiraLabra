package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Suunta;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.Lista;

/**
 * Algoritmi-luokka sisältää varsinaisen reittialgoritmin suoritettavana
 * säikeenä. Säikeen käynnistäminen (start-metodin kutsu) käynnistää algoritmin
 * suorituksen.
 *
 * @author mikko
 *
 */
public abstract class Algoritmi implements Runnable {

    protected boolean jatketaanko;
    protected final Koordinaatit alku;
    protected final Koordinaatit maali;
    protected int hidaste;
    protected final int leveys;
    protected final int korkeus;
    protected boolean valmis;
    protected Ruutu[][] maailma;
    protected RuudunTila[][] ruutujenTilat;
    protected Solmu reitti;
    protected boolean vinottain;
    private Paivitettava paivitettava;

    /**
     * Luo uuden algoritmin instanssin. Yksi luotu algotitmiolio voidaan
     * suorittaa vain kerran.
     *
     * @param maailma algoritmin toimintaympäristö, joka sisältää tiedon
     * ruutujen kustannuksista
     * @param hidaste odotetaan näin monta millisekuntia jokaisen algoritmin
     * suoritusaskeleen välillä.
     * @param alku alkupisteen koordinaatit
     * @param maali maalipisteen koordinaatit
     * @param vinottain sallitaanko liikkuminen vinottain
     */
    public Algoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain) {
        this.alku = alku;
        this.maali = maali;
        this.leveys = maailma[0].length;
        this.korkeus = maailma.length;
        this.valmis = false;
        this.hidaste = hidaste;
        this.maailma = maailma;
        this.ruutujenTilat = new RuudunTila[korkeus][leveys];
        this.jatketaanko = true;
        this.vinottain = vinottain;
    }

    /**
     * Asettaa algoritmille päivitettävän otuksen. paivita()-metodia kutsutaan
     * silloin, kun algoritmin suoritus on valmistunut eli se on löytänyt tien
     * maaliin.
     *
     * @param paivitettava
     */
    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    /**
     * Palauttaa tiedon siitä, onko algoritmin suorittaminen valmis, eli onko
     * reitti perille jo löytynyt.
     *
     * @return true, jos algoritmin suorittaminen on valmis ja false, jos ei ole
     */
    public boolean onkoValmis() {
        return this.valmis;
    }

    /**
     * Algoritmin asettama tila ruudulle tietyssä pisteessä. Jos ruutu on
     * koskematon, palautetaan null. Kertoo onko ruutu tutkittu, tai lisätty
     * tutkittavaksi, tai osa lopullista reittiä.
     *
     * @param x
     * @param y
     * @return ruudun tila
     */
    public RuudunTila getRuudunTila(int x, int y) {
        return this.ruutujenTilat[y][x];
    }

    /**
     * Lopettaa reittialgoritmin suorituksen.
     */
    public void lopeta() {
        this.jatketaanko = false;
    }

    /**
     * Jos algoritmin suoritus on valmis (onkoValmis() -metodi palauttaa true),
     * niin tämä metodi palauttaa valmiin reitin maalisolmun, joka on linkitetty
     * lista kohti lähtöpistettä
     *
     * @return reitin viimeinen solmu
     */
    public Solmu getReitti() {
        return this.reitti;
    }

    /**
     * Käynnistää algoritmin suorituksen. Algoritmi kannattaa laittaa omaan
     * säikeeseensä ja kutsua säikeelle start()-metodia.
     */
    @Override
    public abstract void run();

    protected Lista<Solmu> solmunNaapurit(Solmu solmu) {
        Lista<Solmu> palautus = new Lista<>();
        Suunta[] lapikaytavat;

        if (vinottain) {
            lapikaytavat = Suunta.values();
        } else {
            lapikaytavat = Suunta.kohtisuoratSuunnat();
        }

        for (Suunta suunta : lapikaytavat) {
            Koordinaatit koord = solmu.getKoord().suuntaan(suunta);
            if (koordinaatitUlkopuolella(koord)) {
                continue;
            }
            if (maailma[koord.getY()][koord.getX()] == null) {
                continue;
            }
            if (maailma[koord.getY()][koord.getX()].getKustannus() == 0) {
                continue;
            }
            palautus.add(new Solmu(koord, solmu.getKuljettuMatka() + maailma[solmu.getKoord().getY()][solmu.getKoord().getX()].getKustannus(), solmu));

        }
        return palautus;
    }

    private boolean koordinaatitUlkopuolella(Koordinaatit koordinaatit) {
        return koordinaatit.getX() < 0 || koordinaatit.getY() < 0 || koordinaatit.getX() >= maailma[0].length || koordinaatit.getY() >= maailma.length;
    }

    protected void odota() {
        if (this.hidaste == 0) {
            return;
        }
        try {
            Thread.sleep(hidaste);
        } catch (InterruptedException e) {

        }
    }

    protected void maaliLoytyi(Solmu solmu) {
        this.reitti = solmu;
        while (solmu != null) {
            ruutujenTilat[solmu.getKoord().getY()][solmu.getKoord().getX()] = RuudunTila.REITTI;
            solmu = solmu.getEdellinen();
        }
        this.valmis = true;
        if (this.paivitettava != null) {
            paivitettava.paivita();
        }
    }

}
