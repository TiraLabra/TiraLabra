/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;
import java.util.List;

/**
 * Karttarakenne Astar algoritmia varten, kartta muodostuu solmuista joilla on
 * koordinaatit.
 *
 * @author sasumaki
 */
public class Kartta {

    private int leveys;
    private int korkeus;
    private Ruutu[][] ruudukko;
    private int alkupisteX;
    private int alkupisteY;
    private int maalipisteX;
    private int maalipisteY;

    public Kartta(int leveys, int korkeus, int alkupisteX, int alkupisteY, int maalipisteX, int maalipisteY) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.alkupisteX = alkupisteX;
        this.alkupisteY = alkupisteY;
        this.maalipisteX = maalipisteX;
        this.maalipisteY = maalipisteY;
        luoKartta();

    }

    /**
     * Luo kaksiulotteisen taulukkokartan, taulukon alkioina on solmuja, jotka
     * kuvastavat koordinaatteja.
     *
     * @return
     */
    public void luoKartta() {
        ruudukko = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                ruudukko[y][x] = Ruutu.LATTIA;
            }
        }
    }

    /**
     * määrittää tietyn solmun koordinaateissa x ja y maalipisteeksi.
     *
     * @return
     */
//    public void setMaali(int x, int y) {
//        this.maalipisteX = x;
//        this.maalipisteY = y;
//        kartta.get(x).get(y).setMaali(true);
//    }
    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getMaaliX() {
        return maalipisteX;
    }

    public int getMaaliY() {
        return maalipisteY;
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
     * määrittää tietyn solmun koordinaateissa x ja y alkupisteeksi.
     *
     * @param x
     * @param y
     */
//    public void setAlku(int x, int y) {
//        
//    }
    public int getAlkuX() {
        return alkupisteX;
    }

    public int getAlkuY() {
        return alkupisteY;
    }

    public Ruutu getAlkuRuutu() {
        return ruudukko[alkupisteY][alkupisteX];
    }

    public Ruutu getMaaliRuutu() {
        return ruudukko[maalipisteY][maalipisteX];
    }

    public Ruutu getRuutu(int x, int y) {
        return ruudukko[y][x];
    }

    //
    //TODO: tsekkaa ettei listaan laiteta maailman ulkopuolelle osoittavia ruutuja
    public ArrayList<Solmu> naapurit(int x, int y, Solmu nykyinen, int matka) {
        ArrayList<Solmu> naapurit = new ArrayList<Solmu>();

        if (!(y - 1 < 0)) {
            naapurit.add(new Solmu(x, y - 1, nykyinen, matka + ruudukko[y - 1][x].liikeHinta()));
            if (!(x + 1 >= leveys)) {
                naapurit.add(new Solmu(x + 1, y - 1, nykyinen, matka + ruudukko[y - 1][x + 1].liikeHinta()));
            }
            if (!(x - 1 < 0)) {
                naapurit.add(new Solmu(x - 1, y - 1, nykyinen, matka + ruudukko[y - 1][x - 1].liikeHinta()));
            }
        }
        if (!(y + 1 >= korkeus)) {
            naapurit.add(new Solmu(x, y + 1, nykyinen, matka + ruudukko[y + 1][x].liikeHinta()));

            if (!(x + 1 >= leveys)) {
                naapurit.add(new Solmu(x + 1, y + 1, nykyinen, matka + ruudukko[y + 1][x + 1].liikeHinta()));
            }
            if (!(x - 1 < 0)) {
                naapurit.add(new Solmu(x - 1, y + 1, nykyinen, matka + ruudukko[y + 1][x - 1].liikeHinta()));
            }
        }
        if (!(x + 1 >= leveys) && !(x - 1 < 0)) {
            naapurit.add(new Solmu(x + 1, y, nykyinen, matka + ruudukko[y][x + 1].liikeHinta()));
            naapurit.add(new Solmu(x - 1, y, nykyinen, matka + ruudukko[y][x - 1].liikeHinta()));
        }

        return naapurit;
    }

}
