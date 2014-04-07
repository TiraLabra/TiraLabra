package pacman.peli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Highscore luokka käsittelee pelin piste-ennätykseen liittyvät toiminnot.
 *
 * @author hhkopper
 */
public class Highscore {

    /**
     * Tiedosto, joka sisältää parhaimman pistemäärän.
     */
    private File ennatyslista;
    /**
     * FileWriter, joka kirjaa uuden ennätyksen tiedostoon.
     */
    private FileWriter kirjuri;
    /**
     * Scanner, joka lukee parhaimman pistemäärän tiedostosta.
     */
    private Scanner lukija;
    /**
     * Kertoo parhaimman pistemäärän
     */
    private int paras;

    /**
     * Konstruktorissa luodaan ennätystiedosto ja annetaan muuttujille
     * tarvittavat arvot.
     */
    public Highscore(File tiedosto) {
        this.ennatyslista = tiedosto;
        this.paras = 0;

    }

    /**
     * Tarkistetaan onko parametrina annetut pisteet uusi ennätys. Jos
     * tiedostossa ei ole vielä mitään palautetaan true, jolloin uusi pistemäärä
     * on uusi ennätys. Jos taas aiempi paras pistemäärä on pienempä kuin uusi
     * pistemäärä on tehty ennätys. Muulloin palautetaan false.
     *
     * @param pisteet pelaajan saama pistemäärä
     * @return palauttaa boolean arvon, true, jos on ennätys ja false, jos ei.
     */
    public boolean tarkistaOnkoEnnatys(int pisteet) throws FileNotFoundException {

        if (!ennatyslista.exists()) {
            if(pisteet < 0) {
                return false;
            }
            return true;
        }
        this.lukija = new Scanner(ennatyslista);

        if (!lukija.hasNextInt()) {
            return true;
        } else if (tamanHetkinenEnnatys() < pisteet) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tulostetaan tämän hetkinen paras pistemäärä. Jos tiedostossa ei ole
     * mitään palautetaan nolla, muulloin lukija hakee tiedostosta seuraavan int
     * arvon ja asettaa tämän parhaan arvoksi ja tulostaa haetun arvon.
     *
     * @return int arvon, joka on tämän hetkinen ennätys.
     */
    private int tamanHetkinenEnnatys() {
        if (!lukija.hasNextInt()) {
            return 0;
        } else {
            int luku = lukija.nextInt();
            paras = luku;
            return luku;
        }
    }

    /**
     * FileWriter kirjoittaa uudet pisteet ennätystiedostoon.
     *
     * @param pisteet pelaajan pistemäärä, joka on todettu uudeksi ennätykseksi.
     */
    public void kirjaaEnnatys(int pisteet) throws IOException {

        this.kirjuri = new FileWriter(ennatyslista);
        kirjuri.write(Integer.toString(pisteet));
        kirjuri.close();
    }

    public int getParas() {
        return this.paras;
    }
}
