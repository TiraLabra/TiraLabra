package pacman.peli;

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
     * Lista, joka sisältää kaikki kentällä olevat haamut.
     */

    private Lista haamut;
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

    private boolean heikko;

    private Haku haku;

    private Peliruutu magentaMaali;

    /**
     * Konstruktorissa luodaan pelialusta ja kaikki komponentit sille, luodaan
     * myös pistelaskuri ja highscore
     */
    public Pacman() {
        super(1000, null);
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        man = new Man(9, 11, Suunta.OIKEA, alusta);
        man.luoManAlustalle();
        haamut = new Lista();
        this.luoHaamut();
        laskuri = new Pistelaskuri();
        this.hedelmanPaikat = new Lista();
        this.jatkuu = true;
        this.tilanne = false;
        this.heikko = false;
        this.haku = new Haku();
        Haamu haamu = (Haamu) haamut.getAlkio(3);
        this.magentaMaali = haamu.selvitaMaaliMagenta(arpoja);

        addActionListener(this);
        setInitialDelay(2000);
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public boolean getHeikko() {
        return this.heikko;
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

    public Lista getHaamuLista() {
        return this.haamut;
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

    /**
     * Luodaan haamut pelialustalle omaan karsinaan.
     */
    private void luoHaamut() {
        Haamu red = new Haamu(9, 7, Suunta.YLOS, "red", alusta);
        Haamu green = new Haamu(10, 9, Suunta.YLOS, "green", alusta);
        Haamu cyan = new Haamu(8, 9, Suunta.YLOS, "cyan", alusta);
        Haamu magenta = new Haamu(9, 9, Suunta.YLOS, "magenta", alusta);

        haamut.lisaa(red);
        haamut.lisaa(green);
        haamut.lisaa(cyan);
        haamut.lisaa(magenta);
    }

    /**
     * Asetetaan haamujen tyypiksi heikko, jolloin man pystyy syömään haamuja.
     */
    public void heikennaHaamut() {
        this.heikko = true;

        for (int i = 0; i < haamut.koko(); i++) {
            Haamu haamu = (Haamu) haamut.getAlkio(i);
            haamu.setTyyppi("heikko");
            haamu.setHeikkous(30);
        }
    }

    /**
     * Jos haamun tyyppi on heikko ja heikkousaika on loppunut, muutetaan haamun
     * tyyppi vahvaksi. Jos heikkousaika ei ole vielä loppu, vähennetään sitä.
     *
     * @param haamu haamu, jonka tyyppiä tutkitaan.
     */
    private void haamuHeikostaVahvaksi(Haamu haamu) {
        if (haamu.getTyyppi().equals("heikko")) {
            if (haamu.getHeikkous() == 0) {
                haamu.setTyyppi("vahva");
                this.heikko = false;
            } else {
                haamu.vahennaHeikkous();
            }
        }
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
            heikennaHaamut();
        }
    }

    /**
     * Katsotaan kuoleeko haamu tai man, kun ne osuvat samaan ruutuun. Jos
     * haamun tyyppi on heikko, tällöin haamu kuolee ja palaa lähtöruutuunsa.
     * Jos taas haamun tyyppi on vahva, niin man kuolee ja palaa lähtöruutuun.
     */
    public void kuoleekoHaamuTaiMan() {
        for (int i = 0; i < haamut.koko(); i++) {
            Haamu haamu = (Haamu) haamut.getAlkio(i);
            if (alusta.getPeliruutu(haamu.getX(), haamu.getY()).getOnkoMan()) {
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

        liikutaHaamut();
        kuoleekoHaamuTaiMan();
        if (tarkistaOnkoHeikkoja() == 0) {
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

    private void liikutaHaamut() {
        for (int i = 0; i < haamut.koko(); i++) {
            Haamu haamu = (Haamu) haamut.getAlkio(i);

            if (haamu.getTyyppi().equals("heikko")) {
                liikutaHaamutHeikkoina(haamu);
            } else {
                if (haamu.getNimi().equals("red")) {
                    Peliruutu siirto = haku.aStar(alusta.getPeliruutu(haamu.getX(), haamu.getY()), alusta.getPeliruutu(man.getX(), man.getY()), alusta);
                    haamu.liiku(siirto);
                } else if (haamu.getNimi().equals("cyan")) {
                    Peliruutu maali = haamu.selvitaMaaliCyan(man);
                    Peliruutu siirto = haku.aStar(alusta.getPeliruutu(haamu.getX(), haamu.getY()), maali, alusta);
                    haamu.liiku(siirto);
                } else if (haamu.getNimi().equals("magenta")) {
                    if (haamu.getX() == magentaMaali.getX() && haamu.getY() == magentaMaali.getY()) {
                        while (haamu.getX() == magentaMaali.getX() && haamu.getY() == magentaMaali.getY()) {
                            magentaMaali = haamu.selvitaMaaliMagenta(arpoja);
                        }

                    }
                    System.out.println(magentaMaali.toString());
                    Peliruutu siirto = haku.aStar(alusta.getPeliruutu(haamu.getX(), haamu.getY()), magentaMaali, alusta);
                    haamu.liiku(siirto);
                } else {
                    haamu.liiku();
                }
            }

            haamuHeikostaVahvaksi(haamu);
        }
    }

    private void liikutaHaamutHeikkoina(Haamu haamu) {
        liikutaRedHeikko(haamu);
        liikutaCyanHeikko(haamu);
        liikutaMagentaHeikko(haamu);
        liikutaGreenHeikko(haamu);
    }

    private void liikutaGreenHeikko(Haamu haamu) {
        if (haamu.getNimi().equals("green")) {
            haamu.liiku();
        }
    }

    private void liikutaMagentaHeikko(Haamu haamu) {
        if (haamu.getNimi().equals("magenta")) {
        }
    }

    private void liikutaCyanHeikko(Haamu haamu) {
        if (haamu.getNimi().equals("cyan")) {
            Peliruutu maali = haamu.selvitaMaaliCyan(man);
            int peilaus = 9 - maali.getX();
            maali = alusta.getPeliruutu(9 + peilaus, maali.getY());
            if (maali.getX() == haamu.getX() && maali.getY() == haamu.getY()) {
                maali = alusta.getPeliruutu(man.getX(), man.getY());
            }
            Peliruutu siirto = haku.aStar(alusta.getPeliruutu(haamu.getX(), haamu.getY()), alusta.getPeliruutu(maali.getX(), maali.getY()), alusta);
            haamu.liiku(siirto);
        }
    }

    private void liikutaRedHeikko(Haamu haamu) {
        if (haamu.getNimi().equals("red")) {
            int peilaus = 9 - man.getX();
            Peliruutu maali = alusta.getPeliruutu(9 + peilaus, man.getY());
            if (maali.getX() == haamu.getX() && maali.getY() == haamu.getY()) {
                maali = alusta.getPeliruutu(man.getX(), man.getY());
            }
            Peliruutu siirto = haku.aStar(alusta.getPeliruutu(haamu.getX(), haamu.getY()), alusta.getPeliruutu(maali.getX(), maali.getY()), alusta);
            haamu.liiku(siirto);
        }
    }

    private int tarkistaOnkoHeikkoja() {
        int heikkoja = 0;
        for (int i = 0; i < haamut.koko(); i++) {
            Haamu haamu = (Haamu) haamut.getAlkio(i);

            if (haamu.getTyyppi().equals("heikko")) {
                heikkoja++;
            }
        }
        return heikkoja;
    }
}
