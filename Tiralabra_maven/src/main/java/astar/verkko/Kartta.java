/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.verkko;

import astar.verkko.Solmu;
import astar.verkko.Ruutu;
import java.util.ArrayList;

/**
 * Karttarakenne Astar algoritmia varten, kartta muodostuu solmuista joilla on
 * koordinaatit.
 *
 * @author sasumaki
 */
public final class Kartta {

    private final int leveys;
    private final int korkeus;
    private Ruutu[][] ruudukko;
    private int alkupisteX;
    private int alkupisteY;
    private int maalipisteX;
    private int maalipisteY;

    public Kartta(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;

        luoKartta();

    }

    /**
     * Luo kaksiulotteisen taulukkokartan, taulukon alkioina on solmuja, jotka
     * kuvastavat koordinaatteja.
     *
     */
    public void luoKartta() {
        ruudukko = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                ruudukko[y][x] = Ruutu.LATTIA;
            }
        }
        boolean haluatkoSeinan = true;
        if (haluatkoSeinan) {
            ruudukko[3][4] = Ruutu.SEINÄ;
            ruudukko[3][5] = Ruutu.SEINÄ;

            ruudukko[3][6] = Ruutu.SEINÄ;

            ruudukko[3][7] = Ruutu.SEINÄ;
            ruudukko[3][8] = Ruutu.SEINÄ;
            ruudukko[9][3] = Ruutu.SEINÄ;

            ruudukko[4][3] = Ruutu.SEINÄ;

            ruudukko[5][3] = Ruutu.SEINÄ;
            ruudukko[6][3] = Ruutu.SEINÄ;

            ruudukko[7][3] = Ruutu.SEINÄ;
            ruudukko[8][3] = Ruutu.SEINÄ;

        }

    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    /**
     * määrittää tietyn solmun koordinaateissa x ja y esteeksi
     *
     * @param x
     * @param y
     */
    public void setEste(int x, int y) {
        ruudukko[y][x] = Ruutu.SEINÄ;
    }

    /**
     * määrittää tietyn solmun koordinaateissa x ja y lattiaksi
     *
     * @param x
     * @param y
     */
    public void setLattia(int x, int y) {
        ruudukko[y][x] = Ruutu.LATTIA;
    }

    /**
     * määrittää tietyn solmun koordinaateissa x ja y metsäksi
     *
     * @param x
     * @param y
     */
    public void setMetsa(int x, int y) {
        ruudukko[y][x] = Ruutu.METSÄ;
    }

    /**
     * määrittää tietyn solmun koordinaateissa x ja y vedeksi
     *
     * @param x
     * @param y
     */
    public void setVesi(int x, int y) {
        ruudukko[y][x] = Ruutu.VESI;
    }

    /**
     * Palauttaa alkusolmun
     *
     * @param x
     * @param y
     * @return
     */
    public Ruutu getAlkuRuutu() {
        return ruudukko[alkupisteY][alkupisteX];
    }

    /**
     * palauttaa maalisolmun
     *
     * @return
     */
    public Ruutu getMaaliRuutu() {
        return ruudukko[maalipisteY][maalipisteX];
    }

    /**
     * palauttaa ruudun x,y koordinaateissa
     *
     * @param x
     * @param y
     * @return
     */
    public Ruutu getRuutu(int x, int y) {
        return ruudukko[y][x];
    }

    //
    /**
     * hakee solmun naapurisolmut. Kulmasolmut kommentoituna toistaiseksi
     *
     * @param x
     * @param y
     * @param nykyinen
     * @param matka
     * @return
     */
    public ArrayList<Solmu> naapurit(int x, int y, Solmu nykyinen, int matka) {
        ArrayList<Solmu> naapurit = new ArrayList<>();

        if (y >= 1) {
            naapurit.add(new Solmu(x, y - 1, nykyinen, matka + ruudukko[y - 1][x].liikeHinta()));
//            if (!(x + 1 >= leveys)) {
//                naapurit.add(new Solmu(x + 1, y - 1, nykyinen, matka + ruudukko[y - 1][x + 1].liikeHinta()));
//            }
//            if (!(x - 1 < 0)) {
//                naapurit.add(new Solmu(x - 1, y - 1, nykyinen, matka + ruudukko[y - 1][x - 1].liikeHinta()));
//            }
        }
        if (!(y + 1 >= korkeus)) {
            naapurit.add(new Solmu(x, y + 1, nykyinen, matka + ruudukko[y + 1][x].liikeHinta()));

//            if (!(x + 1 >= leveys)) {
//                naapurit.add(new Solmu(x + 1, y + 1, nykyinen, matka + ruudukko[y + 1][x + 1].liikeHinta()));
//            }
//            if (!(x - 1 < 0)) {
//                naapurit.add(new Solmu(x - 1, y + 1, nykyinen, matka + ruudukko[y + 1][x - 1].liikeHinta()));
//            }
        }
        if (!(x + 1 >= leveys)) {
            naapurit.add(new Solmu(x + 1, y, nykyinen, matka + ruudukko[y][x + 1].liikeHinta()));
        }
        if (!(x - 1 < 0)) {
            naapurit.add(new Solmu(x - 1, y, nykyinen, matka + ruudukko[y][x - 1].liikeHinta()));

        }

        return naapurit;
    }

}
