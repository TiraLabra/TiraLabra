/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.verkko;

import astar.tietorakenteet.Lista;
import java.util.Random;

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
    private Random random;

    public Kartta(int leveys, int korkeus, Random random) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.random = random;

        luoKartta(random);
    }
/**
 * Luo kaksiulotteisen taulukkokartan.
 * @param leveys
 * @param korkeus 
 */
    public Kartta(int leveys, int korkeus) {

        this(leveys, korkeus, null);

        luoKartta();

    }

    /**
     * Luo satunnaisen kaksiulotteisen taulukkokartan, taulukon alkioina on solmuja, jotka
     * kuvastavat koordinaatteja.
     *
     */
    private void luoKartta() {
        ruudukko = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                ruudukko[y][x] = Ruutu.LATTIA;
            }
        }

    }

    private void luoKartta(Random random) {
        ruudukko = new Ruutu[korkeus][leveys];
        int i;
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                i = random.nextInt(100);
                if (i < 5) {
                    ruudukko[y][x] = Ruutu.SEINÄ;
                }
                else if (i >= 5 && i < 15) {
                    ruudukko[y][x] = Ruutu.VESI;
                }
                else if (i >= 15 && i < 25) {
                    ruudukko[y][x] = Ruutu.METSÄ;
                } else {
                    ruudukko[y][x] = Ruutu.LATTIA;
                }
            }
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
    public Lista<Solmu> naapurit(int x, int y, Solmu nykyinen, int matka) {
        Lista<Solmu> naapurit = new Lista<>();

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
