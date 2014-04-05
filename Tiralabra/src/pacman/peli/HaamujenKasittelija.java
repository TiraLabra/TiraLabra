package pacman.peli;

import java.util.Random;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Cyan;
import pacman.hahmot.Green;
import pacman.hahmot.Haamu;
import pacman.hahmot.Magenta;
import pacman.hahmot.Red;
import pacman.hahmot.Suunta;
import pacman.tietorakenteet.Haku;

public class HaamujenKasittelija {

    private Red red;
    private Cyan cyan;
    private Green green;
    private Magenta magenta;
    private Peliruutu magentaMaali;
    private Haku haku;
    private Random arpoja;
    private Pacman peli;

    public HaamujenKasittelija(Pacman peli, Random arpoja, Haku hakija) {
        red = new Red(9, 7, Suunta.YLOS, "red", peli.getAlusta());
        green = new Green(10, 9, Suunta.YLOS, "green", peli.getAlusta());
        cyan = new Cyan(8, 9, Suunta.YLOS, "cyan", peli.getAlusta());
        magenta = new Magenta(9, 9, Suunta.YLOS, "magenta", peli.getAlusta());
        this.magentaMaali = magenta.selvitaMaaliMagenta(arpoja);
        this.haku = hakija;
        this.arpoja = arpoja;
        this.peli = peli;
    }

    public Red getRed() {
        return this.red;
    }

    public Magenta getMagenta() {
        return this.magenta;
    }

    public Cyan getCyan() {
        return this.cyan;
    }

    public Green getGreen() {
        return this.green;
    }

    /**
     * Asetetaan haamujen tyypiksi heikko, jolloin man pystyy syömään haamuja.
     */
    public void heikennaHaamut() {
        peli.setHeikko(true);
        heikennaHaamu(red);
        heikennaHaamu(green);
        heikennaHaamu(cyan);
        heikennaHaamu(magenta);

    }

    private void heikennaHaamu(Haamu haamu) {
        haamu.setTyyppi("heikko");
        haamu.setHeikkous(30);
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
                peli.setHeikko(false);
            } else {
                haamu.vahennaHeikkous();
            }
        }
    }

    public void liikutaHaamut() {
        liikutaRed();
        liikutaGreen();
        liikutaCyan();
        liikutaMagenta();

    }

    private void liikutaRed() {
        if (red.getTyyppi().equals("heikko")) {
            liikutaRedHeikko();
        } else {
            liikutaVahvaRed();
        }
        haamuHeikostaVahvaksi(red);
    }

    private void liikutaGreen() {
        if (green.getTyyppi().equals("heikko")) {
            liikutaGreenHeikko();
        } else {
            liikutaVahvaGreen();
        }
        haamuHeikostaVahvaksi(green);
    }

    private void liikutaMagenta() {
        if (magenta.getTyyppi().equals("heikko")) {
            liikutaMagentaHeikko();
        } else {
            liikutaVahvaMagenta();
        }
        haamuHeikostaVahvaksi(magenta);
    }

    private void liikutaCyan() {
        if (cyan.getTyyppi().equals("heikko")) {
            liikutaCyanHeikko();
        } else {
            liikutaVahvaCyan();
        }
        haamuHeikostaVahvaksi(cyan);
    }

    private void liikutaVahvaGreen() {
        green.liiku();
    }

    private void liikutaVahvaMagenta() {

        if (magenta.getX() == magentaMaali.getX() && magenta.getY() == magentaMaali.getY()) {
            while (magenta.getX() == magentaMaali.getX() && magenta.getY() == magentaMaali.getY()) {
                magentaMaali = magenta.selvitaMaaliMagenta(arpoja);
            }

        }
        Peliruutu siirto = haku.aStar(peli.getAlusta().getPeliruutu(magenta.getX(), magenta.getY()), magentaMaali, peli.getAlusta());
        magenta.liiku(siirto);

    }

    private void liikutaVahvaCyan() {

        Peliruutu maali = cyan.selvitaMaaliCyan(peli.getMan());
        Peliruutu siirto = haku.aStar(peli.getAlusta().getPeliruutu(cyan.getX(), cyan.getY()), maali, peli.getAlusta());
        cyan.liiku(siirto);

    }

    private void liikutaVahvaRed() {

        Peliruutu siirto = haku.aStar(peli.getAlusta().getPeliruutu(red.getX(), red.getY()), peli.getAlusta().getPeliruutu(peli.getMan().getX(), peli.getMan().getY()), peli.getAlusta());
        red.liiku(siirto);

    }

    private void liikutaGreenHeikko() {
        green.liiku();

    }

    private void liikutaMagentaHeikko() {
        this.liikutaVahvaMagenta();
    }

    private void liikutaCyanHeikko() {
        Peliruutu maali = cyan.selvitaMaaliCyan(peli.getMan());
        int peilaus = 9 - maali.getX();
        maali = peli.getAlusta().getPeliruutu(9 + peilaus, maali.getY());
        if (maali.getX() == cyan.getX() && maali.getY() == cyan.getY()) {
            maali = peli.getAlusta().getPeliruutu(peli.getMan().getX(), peli.getMan().getY());
        }
        Peliruutu siirto = haku.aStar(peli.getAlusta().getPeliruutu(cyan.getX(), cyan.getY()), peli.getAlusta().getPeliruutu(maali.getX(), maali.getY()), peli.getAlusta());
        cyan.liiku(siirto);
    }

    private void liikutaRedHeikko() {
        int peilaus = 9 - peli.getMan().getX();
        Peliruutu maali = peli.getAlusta().getPeliruutu(9 + peilaus, peli.getMan().getY());
        if (maali.getX() == red.getX() && maali.getY() == red.getY()) {
            maali = peli.getAlusta().getPeliruutu(peli.getMan().getX(), peli.getMan().getY());
        }
        Peliruutu siirto = haku.aStar(peli.getAlusta().getPeliruutu(red.getX(), red.getY()), peli.getAlusta().getPeliruutu(maali.getX(), maali.getY()), peli.getAlusta());
        red.liiku(siirto);
    }

    public int tarkistaOnkoHeikkoja() {
        int heikkoja = 0;
        heikkoja = onkoHeikkoHaamu(heikkoja, red);
        heikkoja = onkoHeikkoHaamu(heikkoja, cyan);
        heikkoja = onkoHeikkoHaamu(heikkoja, green);
        heikkoja = onkoHeikkoHaamu(heikkoja, magenta);

        return heikkoja;
    }

    private int onkoHeikkoHaamu(int heikkoja, Haamu haamu) {
        if (haamu.getTyyppi().equals("heikko")) {
            heikkoja++;
        }
        return heikkoja;
    }
}
