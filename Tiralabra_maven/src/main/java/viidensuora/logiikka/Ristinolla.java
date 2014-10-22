package viidensuora.logiikka;

import java.util.Observable;
import viidensuora.ai.Suunta;

public class Ristinolla extends Observable {

    /**
     * Pelilautana toimiva 2D-taulukko, johon Pelimerkit sijoitetaan.
     */
    private final Pelimerkki[][] pelilauta;

    /**
     * Voittoon tarvittavan suoran pituus.
     */
    public final int voittavaPituus;

    /**
     * Laudalle mahtuvien merkkien maksimimäärä.
     */
    private final int maxMerkkeja;

    /**
     * Pelilaudan leveys.
     */
    public final int leveys;

    /**
     * Pelilaudan korkeus.
     */
    public final int korkeus;

    /**
     * Seuraavan pelaajan vuoro.
     */
    private boolean ristinVuoro;

    /**
     * Tällä hetkellä laudalla olevien pelimerkkien määrä.
     */
    private int merkkeja;

    /**
     * Pelaaja, joka voitti pelin. NULL jos ei ole voittajaa.
     */
    private Pelimerkki voittaja;

    public Ristinolla(int leveys, int korkeus, int voittavaPituus) {
        this.pelilauta = new Pelimerkki[korkeus][leveys];
        this.voittavaPituus = voittavaPituus;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.maxMerkkeja = leveys * korkeus;
        this.ristinVuoro = true;
        this.merkkeja = 0;
        this.voittaja = null;
    }

    /**
     * Palauttaa merkin pelilaudalta.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return xy-koordinaatissa oleva Pelimerkki
     */
    public Pelimerkki getMerkki(int x, int y) {
        return pelilauta[y][x];
    }

    public Pelimerkki getVoittaja() {
        return voittaja;
    }

    /**
     * Palauttaa tällä hetkellä laudalla olevien pelimerkkien määrän.
     *
     * @return merkkien määrä
     */
    public int laudallaMerkkeja() {
        return merkkeja;
    }

    /**
     * Tarkistaa onko lauta täynnä.
     *
     * @return true jos lauta on täynnä, false jos ei
     */
    public boolean lautaTaynna() {
        return merkkeja >= maxMerkkeja;
    }

    /**
     * Asettaa uuden pelimerkin laudalle, mikäli ruutu on vapaa ja päivittää
     * merkkilaskuria.
     *
     * @param x merkin x-koordinaatti
     * @param y merkin y-koordinaatti
     * @param merkki merkki joka laudalle asetetaan
     */
    public void lisaaMerkki(int x, int y, Pelimerkki merkki) {
        if (ruutuOnTyhja(x, y)) {
            pelilauta[y][x] = merkki;
            merkkeja++;
        }
    }

    /**
     * Lisää Nollan laudalle.
     *
     * @param x merkin x-koordinaatti
     * @param y merkin y-koordinaatti
     */
    public void lisaaNolla(int x, int y) {
        lisaaMerkki(x, y, Pelimerkki.NOLLA);
    }

    /**
     * Lisää Ristin laudalle.
     *
     * @param x merkin x-koordinaatti
     * @param y merkin y-koordinaatti
     */
    public void lisaaRisti(int x, int y) {
        lisaaMerkki(x, y, Pelimerkki.RISTI);
    }

    /**
     * Tarkistaa onko xy-koordinaatti laudalla.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return true jos koordinaatti on laudalla, false jos ei
     */
    public boolean onLaudalla(int x, int y) {
        return x >= 0 && x < leveys && y >= 0 && y < korkeus;
    }

    /**
     * Tarkistaa onko xy-koordinaatissa Nolla.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return true jos koordinaatissa Nolla, false jos ei
     */
    public boolean onNolla(int x, int y) {
        return pelilauta[y][x] == Pelimerkki.NOLLA;
    }

    /**
     * Tarkistaa onko xy-koordinaatissa Risti.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return true jos koordinaatissa Risti, false jos ei
     */
    public boolean onRisti(int x, int y) {
        return pelilauta[y][x] == Pelimerkki.RISTI;
    }

    /**
     * Tarkistaa onko seuraava pelivuoro ristillä.
     *
     * @return true jos ristin vuoro, false jos nollan vuoro.
     */
    public boolean onRistinVuoro() {
        return ristinVuoro;
    }

    /**
     * Lisää pelivuorossa olevan pelaajan merkin laudalle, tarkistaa oliko
     * siirto voittava, vaihtaa pelivuoron toiselle ja ilmoittaa muutoksesta
     * mahdollisille Observereille.
     *
     * @param x uuden merkin x-koordinaatti
     * @param y uuden merkin y-koordinaatti
     */
    public void pelaaVuoro(int x, int y) {
        if (voittaja == null && ruutuOnTyhja(x, y)) {
            if (ristinVuoro) {
                lisaaRisti(x, y);
            } else {
                lisaaNolla(x, y);
            }
            if (siirtoVoitti(x, y)) {
                voittaja = ristinVuoro ? Pelimerkki.RISTI : Pelimerkki.NOLLA;
            }
            ristinVuoro = !ristinVuoro;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Laskee kuinka monta samaa merkkiä on peräkkäin laudalla tietyssä
     * suunnassa.
     *
     * @param x lähtöpisteen x-koordinaatti
     * @param y lähtöpisteen y-koordinaatti
     * @param suunta suunta mistä merkit lasketaan
     * @return peräkkäisten merkkien lukumäärä
     */
    public int perakkaisiaMerkkeja(int x, int y, Suunta suunta) {
        Pelimerkki merkki = pelilauta[y][x];
        int n = 1;
        while (onLaudalla(x += suunta.deltaX, y += suunta.deltaY)
                && pelilauta[y][x] == merkki) {
            n++;
        }
        return n;
    }

    /**
     * Laskee pisimmän suoran mikä muodostuu tietyn Pelimerkin avulla.
     *
     * @param x pelimerkin x-koordinaatti
     * @param y pelimerkin y-koordinaatti
     * @return pisimmän suoran pituus
     */
    public int pisinSuora(int x, int y) {
        int vaaka = -1 + perakkaisiaMerkkeja(x, y, Suunta.VASEN)
                + perakkaisiaMerkkeja(x, y, Suunta.OIKEA);
        int pysty = -1 + perakkaisiaMerkkeja(x, y, Suunta.YLOS)
                + perakkaisiaMerkkeja(x, y, Suunta.ALAS);
        int diag1 = -1 + perakkaisiaMerkkeja(x, y, Suunta.YLAVASEN)
                + perakkaisiaMerkkeja(x, y, Suunta.ALAOIKEA);
        int diag2 = -1 + perakkaisiaMerkkeja(x, y, Suunta.ALAVASEN)
                + perakkaisiaMerkkeja(x, y, Suunta.YLAOIKEA);
        return Math.max(vaaka, Math.max(pysty, Math.max(diag1, diag2)));
    }

    /**
     * Poistaa merkin laudalta ja päivittää merkkilaskuria.
     *
     * @param x poistettavan merkin x-koordinaatti
     * @param y poistettavan merkin x-koordinaatti
     */
    public void poistaMerkki(int x, int y) {
        if (!ruutuOnTyhja(x, y)) {
            pelilauta[y][x] = null;
            merkkeja--;
        }
    }

    /**
     * Tarkistaa onko pelilaudan ruutu tyhjä.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return true jos ruutu on tyhjä, false jos ei
     */
    public boolean ruutuOnTyhja(int x, int y) {
        return pelilauta[y][x] == null;
    }

    /**
     * Tarkistaa muodostuuko annetusta pelimerkistä voittava suora.
     *
     * @param x merkin x-koordinaatti
     * @param y merkin y-koordinaatti
     * @return true jos muodostuvan suoran pituus on vähintään voittoon
     * vaadittava pituus, false jos ei
     */
    public boolean siirtoVoitti(int x, int y) {
        return pelilauta[y][x] != null && pisinSuora(x, y) >= voittavaPituus;
    }

    /**
     * Muodostaa pelilaudasta merkkijonoesityksen.
     *
     * @return merkkijono
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                if (pelilauta[y][x] == null) {
                    sb.append('.');
                } else {
                    sb.append(pelilauta[y][x]);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Laskee laudalla olevien vapaiden ruutujen lukumäärän.
     *
     * @return vapaiden ruutujen lukumäärä
     */
    public int vapaitaRuutuja() {
        return maxMerkkeja - merkkeja;
    }
}
