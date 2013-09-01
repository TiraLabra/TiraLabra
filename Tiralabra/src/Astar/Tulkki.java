package Astar;

import java.io.*;
import java.util.*;

/**
 * Tulkitsee labyrintin, eli laskee ja pyytää tarkistamaan sen korkeuden ja
 * leveyden, jotta staattiselle-taulukolle saadaan sopivat "mitat".
 *
 * @param Labyrintti jota halutaan tulkita.
 * @return Totuusarvo siitä onnistuttiinko labyrintin tulkitesemisessa.
 * 
 * @author Ilkka Maristo
 */
public class Tulkki {

    // Tietoja labyrintista:
    private int korkeus;
    private int leveys;
    private int maaliY;
    private int maaliX;
    private int lahtoY;
    private int lahtoX;
    private char[][] labyrintti;

    // Testi -pääohjelmassa kayttö: " if(!tulkki.tulkitseLabyrintti(tulTd)){...} " ,jonka
    // tarkoitus: jos labyrintti ei täytä vaatimuksia, niin sitä ei käsitella pidemmälle.
    public boolean tulkitseLabyrintti(File labyrinttitiedosto) throws FileNotFoundException {

        if (this.laskeKorkeusJaLeveys(labyrinttitiedosto)) {
            if (this.skannaaLabyrintti(labyrinttitiedosto)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Laskee labyrintin leveyden ja korkeuden seka tarkistaa etta labyrintti on
     * symmetrinen (jokainen rivi yhta pitkä).
     *
     * @param Labyrintti jonka korkeus ja leveys lasketaan
     * @return Totuusarvo laskennan onnistumisesta.
     * @throws FileNotFoundException Kyseistä tiedostoa ei ole olemassa.
     */
    // Labyrintin tulkitsemisen 1. osa:
    private boolean laskeKorkeusJaLeveys(File labyrintti) throws FileNotFoundException {
        // skannataan tiedostoa, johon labyrintti on talletettu
        Scanner skanneri = new Scanner(labyrintti);
        String rivi;
        int i = 0;
        int j = 0;
        int tuoreinLeveys = 0;
        // niin pitkään kun labyrintissa on riveja
        while (skanneri.hasNextLine()) {
            // luetaan riviksi labyrintin rivi
            rivi = skanneri.nextLine();
            j = rivi.length();
            ++i;
            // jos ollaan ensimmaisellä iteraatiokierroksella
            // merkitään tuoreimmaksi leveydeksi ensimmäisen rivin leveys
            if (i == 1) {
                tuoreinLeveys = j;
            } 
            // tarkastellaan onko ko. kierroksen rivin leveys sama
            // kuin edellisen, jos ei ole, palautetaan false
            // sillä talloin labyrintti ei ole symmetrinen
            else if (j != tuoreinLeveys) {
                return false;
            }
        }

        // labyrintin rivit ovat olleet yhtä pitkia, joten jatketaan:
        this.korkeus = i;
        this.leveys = j;
        // asetetaan kaksiulotteiselle 'labyrintti'-taulukolle mitat
        // jotta voidaan täyttää taulukko merkeillä
        this.labyrintti = new char[korkeus][leveys];
        skanneri.close(); // suljetaan skanneri
        return true;
    }

    /**
     * Labyrintti skannataan ja talletetaan 2-uloitteiseen taulukkoon mikäli
     * labyrintti on sisällöltään kelvollinen.
     *
     * @param Skannattava labyrintti.
     * @return Totuusarvo siitä, onnistuttuunko skannauksessa.
     */
    // Labyrintin tulkitsemisen 2. osa:
    private boolean skannaaLabyrintti(File labyrinttitiedosto) throws FileNotFoundException {

        // jos labyrintin leveys ja korkeus eivät täsmää, palautetaan false.
        // (labyrintti['a']['b'] pituudet: a=labyrintti.length , b=labyrintti[0].length)
        // (oletuksena: labyrintti on symmetrinen. tämä tarkastetaan laskeKorkeusJaLeveys:ssä)
        if (labyrintti.length != this.korkeus || labyrintti[0].length != this.leveys) {
            return false;
        }

        // skannataan tiedostoa, johon labyrintti on talletettu
        Scanner skanneri = new Scanner(labyrinttitiedosto);
        String rivi;

        int alut = 0; // alkuruutujen määrä, 'A'
        int loput = 0; // loppuruutujen määrä, 'M'

        // tarkisteaan etta labyrintista löytyy 1 alku- ja 1 loppu-ruutu,
        // sekä ainoastaan #- tai .-merkkeja.
        for (int i = 0; i < this.korkeus; i++) { // riveittäin
            rivi = skanneri.nextLine();
            for (int j = 0; j < this.leveys; j++) { // ja sarakkeittain
                if (rivi.charAt(j) == 'A') {
                    ++alut;
                    // aloituskohdan koordinaatit ylös
                    this.lahtoY = i;
                    this.lahtoX = j;
                }
                if (rivi.charAt(j) == 'M') {
                    ++loput;
                    // maalikohdan koordinaatit ylös
                    this.maaliY = i;
                    this.maaliX = j;
                }
                // jos on joitain muita kuin sallittuja merkkejä
                if (rivi.charAt(j) != 'A' && rivi.charAt(j) != 'M'
                        && rivi.charAt(j) != '#' && rivi.charAt(j) != '.') {
                    return false;
                }

                // talletetaan merkki matriisiin omalle paikalleen
                this.labyrintti[i][j] = rivi.charAt(j);

            }

        }

        skanneri.close(); // suljetaan skanneri

        // tarkastetaan vielä etta onko enemmän kuin
        // yksi alku- ja loppuruutu
        if (alut != 1 || loput != 1) {
            return false;
        }

        // labyrintti on kelvollinen, palautetaan true
        return true;

    }

    /**
     * Palauttaa tulkin käsittelyssä olevan labyrintin.
     *
     * @return tulkin käsittelyssä oleva labyrintti.
     */
    public char[][] getLabyrintti() {
        return this.labyrintti;
    }

    /**
     * Palauttaa labyrintin korkeuden.
     *
     * @return Labyrintin korkeus.
     */
    public int getKorkeus() {
        return this.korkeus;
    }

    /**
     * Palauttaa labyrintin leveyden.
     *
     * @return Labyrintin leveys.
     */
    public int getLeveys() {
        return this.leveys;
    }

    /**
     * Palauttaa lähtoruudun Y-akselin arvon.
     *
     * @return Lähtoruudun y-akselin arvo.
     */
    public int getLahtoY() {
        return this.lahtoY;
    }

    /**
     * Palauttaa lähtoruudun x-akselin arvon.
     *
     * @return Lähtoruudun x-akselin arvo.
     */
    public int getLahtoX() {
        return this.lahtoX;
    }

    /**
     * Palauttaa maaliruudun Y-akselin arvon.
     *
     * @return Maaliruudun y-akselin arvo.
     */
    public int getMaaliY() {
        return this.maaliY;
    }

    /**
     * Palauttaa maaliruudun x-akselin arvon.
     *
     * @return Maaliruudun x-akselin arvo.
     */
    public int getMaaliX() {
        return this.maaliX;
    }
}
