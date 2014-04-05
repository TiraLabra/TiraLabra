package pacman.peli;

import pacman.tietorakenteet.Lista;
import pacman.tietorakenteet.Haku;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.gui.Paivitettava;
import pacman.hahmot.Haamu;
import pacman.hahmot.Man;
import pacman.hahmot.Suunta;

/**
 *
 * Pacman luokan avulla säädellään pelin kulkua ja luokka sisältää pelin
 * kannalta olennaiset toiminnot.
 *
 * @author Hanna
 */
public class Pacman extends Timer implements ActionListener {

    /**
     * Man, joka liikkuu pelissä.
     */
    private Man man;
    /**
     * Pelialusta, jolla kaikki toiminta tapahtuu.
     */
    private Pelialusta alusta;

    /**
     * Pistelaskuri, joka pitää kirjaa kasvavasta pistemäärästä.
     */
    private Pistelaskuri laskuri;
    /**
     * Arpojaa käytetään arpomaan uusi hedelmänpaikka.
     */
    private Random arpoja = new Random();
    /**
     * Kaikki mahdolliset peliruudut johon voi asettaa hedelman.
     */
    private Lista hedelmanPaikat;
    /**
     * Paivitettavan avulla voidaan kutsua Piirtoalustan, joka toteuttaa
     * rajapinnan paivitettava, metodia paivita.
     */
    private Paivitettava paivitettava;
    /**
     * Kertoo missä ruudussa hedelma sijaitsee.
     */
    private Peliruutu hedelmanPaikka;
    /**
     * Boolean arvo kertoo pelin tilan, että jatkuuko se (true) vai ei (false)
     * jos man voittaa tai kuolee.
     */
    private boolean jatkuu;
    /**
     * Boolean arvo kertoo onko peli voitettu (true) vai hävitty (false).
     */
    private boolean tilanne;

    /**
     * Boolean arvo kertoo onko haamut heikkoja (true) vai vahvoja (false).
     */
    private boolean heikko;

    /**
     * Haku on aStar, joka selvittää parasta reittiä haamuille.
     */
    private Haku haku;

    /**
     * Haamujenkäsittelijä on luokan ilmentymä, joka hallinnoi haamujen toimintaa.
     */
    private HaamujenKasittelija kasittelija;

    /**
     * Konstruktorissa luodaan pelialusta ja kaikki komponentit sille, luodaan
     * myös pistelaskuri ja highscore
     */
    public Pacman() {
        super(1000, null);
        this.haku = new Haku();
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        man = new Man(9, 11, Suunta.OIKEA, alusta);
        man.luoManAlustalle();
        laskuri = new Pistelaskuri();
        this.hedelmanPaikat = new Lista();
        this.jatkuu = true;
        this.tilanne = false;
        this.heikko = false;
        kasittelija = new HaamujenKasittelija(this, arpoja, haku);

        addActionListener(this);
        setInitialDelay(2000);
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public boolean getHeikko() {
        return this.heikko;
    }
    
    public void setHeikko(boolean muutos) {
        this.heikko = muutos;
    }

    public Man getMan() {
        return this.man;
    }

    public Pelialusta getAlusta() {
        return this.alusta;
    }

    public Pistelaskuri getLaskuri() {
        return this.laskuri;
    }

    public boolean getTilanne() {
        return this.tilanne;
    }

    public boolean getJatkuu() {
        return this.jatkuu;
    }

    public Peliruutu getHedelmanPaikka() {
        return this.hedelmanPaikka;
    }

    public Lista getHedelmanPaikat() {
        return this.hedelmanPaikat;
    }
    public HaamujenKasittelija getKasittelija() {
        return this.kasittelija;
    }

    /**
     * Man syö pistepallon kentältä ja kasvatetaan pistemäärää. Jos tavallinen
     * pistepallo, kasvatetaan vain pistemäärää. Jos ekstrapistepallo, muutetaan
     * haamujen tyyppi heikoksi ja kasvatetaan pistemäärää.
     */
    public void manSyoPistepallo() {
        if (alusta.getPeliruutu(man.getX(), man.getY()).getOnkoPallo()) {
            alusta.getPeliruutu(man.getX(), man.getY()).setOnkoPallo(false);
            laskuri.kasvata(20);
        } else if (alusta.getPeliruutu(man.getX(), man.getY()).getOnkoExtraPallo()) {
            alusta.getPeliruutu(man.getX(), man.getY()).setOnkoExtraPallo(false);
            laskuri.kasvata(50);
            kasittelija.heikennaHaamut();
        }
    }

    /**
     * Katsotaan kuoleeko haamu tai man, kun ne osuvat samaan ruutuun. Jos
     * haamun tyyppi on heikko, tällöin haamu kuolee ja palaa lähtöruutuunsa.
     * Jos taas haamun tyyppi on vahva, niin man kuolee ja palaa lähtöruutuun.
     */
    public void kuoleekoHaamuTaiMan() {
        tarkistaKumpiKuolee(kasittelija.getRed());
        tarkistaKumpiKuolee(kasittelija.getCyan());
        tarkistaKumpiKuolee(kasittelija.getMagenta());
        tarkistaKumpiKuolee(kasittelija.getGreen());
    }

    private void tarkistaKumpiKuolee(Haamu haamu) {
        if (haamu.olenkoSamassaRuudussaManinKanssa(man)) {
            if (haamu.getTyyppi().equals("heikko")) {
                haamu.palaaAlkuun();
                haamu.setTyyppi("vahva");
                laskuri.kasvata(80);
            } else {
                man.palaaAlkuun();
                man.vahennaElama();
            }
        }
    }

    /**
     * Kun kaikki haamut ovat poistuneet karsinastaan asetetaan karsinan
     * suuaukolle seinä, etteivät haamut mene sinne takaisin. Seinä kuitenkin
     * lähtee pois, kun haamu palaa lähtöpaikkaan etteivät haamut jää jumiin.
     * Tarkistetaan myös, että man ei ole karsinassa.
     */
    public void asetaSeina() {
        for (int y = 8; y < 11; y++) {
            for (int x = 8; x < 11; x++) {
                if (alusta.getPeliruutu(x, y).getOnkoHaamu() || alusta.getPeliruutu(x, y).getOnkoMan()) {
                    alusta.getPeliruutu(9, 8).setRuudunTyyppi(3);
                    return;
                }
            }
        }
        alusta.getPeliruutu(9, 8).setRuudunTyyppi(0);
    }

    /**
     * Etsitään sopivata paikat hedelmälle, minkä jälkeen arvotaan hedelmälle
     * uudet koordinaatit.
     */
    public void arvoHedelma() {
        etsiHedelmanPaikat();
        int luku = arpoja.nextInt(this.hedelmanPaikat.koko());
        this.hedelmanPaikka = (Peliruutu) this.hedelmanPaikat.getAlkio(luku);
    }

    /**
     * Käydään pelikenttä läpi ja katsotaan kaikki mahdolliset paikat mihin
     * hedelmän voisi asettaa. Paikaksi käy sellainen, jossa ei ole enää
     * pistepalloa.
     */
    public void etsiHedelmanPaikat() {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                if (onkoHedelmanPaikka(x, y)) {
                    Peliruutu ruutu = new Peliruutu(x, y);
                    this.hedelmanPaikat.lisaa(ruutu);
                }
            }
        }
    }

    /**
     * Katsotaan, onko koordinaateissa sijaitseva ruutu sopiva hedelmän
     * paikaksi.
     *
     * @param x koordinaatti X
     * @param y koordinaatti Y
     * @return palauttaa boolean arvon
     */
    private boolean onkoHedelmanPaikka(int x, int y) {
        return alusta.getPeliruutu(x, y).getRuudunTyyppi() == 1 && !alusta.getPeliruutu(x, y).getOnkoPallo() && !alusta.getPeliruutu(x, y).getOnkoExtraPallo();
    }

    /**
     * Katsotaan ovatko hedelmä ja man samassa ruudussa.
     *
     * @return palauttaa boolean arvon
     */
    public boolean manOsuuHedelmaan() {
        if (man.getX() == this.hedelmanPaikka.getX() && man.getY() == this.hedelmanPaikka.getY()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Katsotaan onko hedelmä jo kentällä.
     *
     * @return palauttaa boolean arvon
     */
    public boolean onkoHedelmaAlustalla() {
        if (this.hedelmanPaikka != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Kun pisteet ylittävät 400 ja kentällä ei vielä ole hedelmää arvotaan ensimmainen hedelmä.
     * Jatkossa, kun kentällä on hedelmä, arvotaan uusi vasta, kun man osuu hedelmään ja saa tästä 100 pistettä.
     */
    public void luoHedelma() {
        if (laskuri.getPisteet() > 400) {
            if (onkoHedelmaAlustalla()) {
                if (manOsuuHedelmaan()) {
                    laskuri.kasvata(100);
                    arvoHedelma();
                }
            } else {
                arvoHedelma();
            }
        }
    }

    /**
     * Käydään kenttä läpi ja katsotaan onko jälellä enää pistepalloja. Jos
     * pistepallot on kaikki kerätty, jatkuu arvo asetetaan falseksi ja tilanne
     * asetetaan trueksi, joka tarkoittaa, että peli päättyy ja peli on
     * voitettu.
     */
    public void paattyykoPeli() {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                if (alusta.getPeliruutu(x, y).getOnkoPallo() || alusta.getPeliruutu(x, y).getOnkoExtraPallo()) {
                    return;
                }
            }
        }
        this.jatkuu = false;
        this.tilanne = true;
    }

    /**
     * Suoritetaan pelin toiminnot yhdeltä peli kierrokselta.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        kasittelija.liikutaHaamut();
        kuoleekoHaamuTaiMan();
        if (kasittelija.tarkistaOnkoHeikkoja() == 0) {
            this.heikko = false;
        }
        this.man.liiku(this.heikko, this);
        kuoleekoHaamuTaiMan();
        manSyoPistepallo();
        luoHedelma();
        asetaSeina();
        paattyykoPeli();
        if (man.getElamat() <= 0) {
            jatkuu = false;
        }
        this.paivitettava.paivita();
        setDelay(300);

        if (!jatkuu) {
            this.stop();
        }
    }

}
