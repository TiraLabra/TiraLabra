/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.verkko;

import astar.logiikka.Bestfirst;
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

        luoPseudoKartta(random);
    }

    /**
     * Luo kaksiulotteisen taulukkokartan.
     *
     * @param leveys
     * @param korkeus
     */
    public Kartta(int leveys, int korkeus) {

        this(leveys, korkeus, null);

        luoKartta();

    }

    /**
     * Luo satunnaisen kaksiulotteisen taulukkokartan, taulukon alkioina on
     * solmuja, jotka kuvastavat koordinaatteja.
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
                } else if (i >= 5 && i < 15) {
                    ruudukko[y][x] = Ruutu.VESI;
                } else if (i >= 15 && i < 25) {
                    ruudukko[y][x] = Ruutu.METSÄ;
                } else {
                    ruudukko[y][x] = Ruutu.LATTIA;
                }
            }
        }
    }

    private void luoPseudoKartta(Random random) {
        ruudukko = new Ruutu[korkeus][leveys];
        Lista<Solmu> lista = new Lista<>();
        int i;
        int vesikerroin = 1;
        int seinatsanssi;
        boolean vieressaseina;
        boolean vieressatoinenseina;

        boolean ekavesi = false;

        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                i = random.nextInt(100);

                if (i == 15) {
                    ruudukko[y][x] = Ruutu.METSÄ;
                } else {
                    ruudukko[y][x] = Ruutu.LATTIA;
                }
            }

        }
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                i = random.nextInt(1000);
                if (x - 1 > 0) {
                    if (ruudukko[y][x - 1] == Ruutu.VESI) {
                        if (!ekavesi) {
                            vesikerroin = vesikerroin + 175;
                            ekavesi = true;
                        } else {
                            vesikerroin = vesikerroin + 175;
                        }
                    }

                    if (x + 1 < leveys && !(y - 1 < 0)) {
                        if (ruudukko[y - 1][x + 1] == Ruutu.VESI) {
                            if (!ekavesi) {
                                vesikerroin = vesikerroin + 100;
                                ekavesi = true;
                            } else {
                                vesikerroin = vesikerroin + 100;
                            }
                        }
                    }

                    if (!(y - 1 < 0)) {
                        if (ruudukko[y - 1][x] == Ruutu.VESI) {
                            if (!ekavesi) {
                                vesikerroin = vesikerroin + 100;

                            } else {
                                vesikerroin = vesikerroin + 100;
                            }
                        }
                        if (x - 1 > 0) {
                            if (ruudukko[y - 1][x - 1] == Ruutu.VESI) {
                                if (!ekavesi) {
                                    vesikerroin = vesikerroin + 100;

                                } else {
                                    vesikerroin = vesikerroin + 100;
                                }
                            }
                        }

                        if (i < 2 * vesikerroin) {
                            ruudukko[y][x] = Ruutu.VESI;
                        }
                        vesikerroin = 1;
                        ekavesi = false;

                        if (ruudukko[y][x] == Ruutu.LATTIA) {
                            seinatsanssi = random.nextInt(1000);
                            if (seinatsanssi == 666) {
                                ruudukko[y][x] = Ruutu.SEINÄ;
                                lista.add(new Solmu(x, y, null, 0));

                            }

                        }
                    }

                }
            }
        }
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                i = random.nextInt(1000);
                if (ruudukko[y][x] != Ruutu.VESI) {
                    if (x + 1 < leveys) {
                        if (ruudukko[y][x + 1] == Ruutu.VESI) {
                            vesikerroin += 175;
                        }
                    }
                    if (y + 1 < korkeus) {
                        if (ruudukko[y + 1][x] == Ruutu.VESI) {
                            vesikerroin += 100;
                        }
                        if (x + 1 < leveys) {
                            if (ruudukko[y + 1][x + 1] == Ruutu.VESI) {
                                vesikerroin += 100;
                            }

                        }
                        if (x - 1 > 0) {
                            if (ruudukko[y + 1][x - 1] == Ruutu.VESI) {
                                vesikerroin += 100;
                            }
                        }
                    }
                    if (i < 2 * vesikerroin) {
                        ruudukko[y][x] = Ruutu.VESI;
                        vesikerroin = 1;
                    }

                }

                if (ruudukko[y][x] == Ruutu.LATTIA) {
                    seinatsanssi = random.nextInt(1000);

                    vieressaseina = false;
                    vieressatoinenseina = false;

                    if (x - 1 > 0) {
                        if (ruudukko[y][x - 1] == Ruutu.SEINÄ) {
                            vieressaseina = true;
                        }

                    }
                    if (x + 1 < leveys && !(y - 1 < 0)) {
                        if (ruudukko[y - 1][x + 1] == Ruutu.SEINÄ) {
                            if (vieressaseina) {
                                vieressatoinenseina = true;
                            }
                            vieressaseina = true;
                        }
                    }

                    if (!(y - 1 < 0)) {
                        if (ruudukko[y - 1][x] == Ruutu.SEINÄ) {
                            if (vieressaseina) {
                                vieressatoinenseina = true;
                            }
                            vieressaseina = true;
                        }
                    }
                    if (x + 1 < leveys) {
                        if (ruudukko[y][x + 1] == Ruutu.SEINÄ) {
                            vieressaseina = true;
                        }

                    }
                    if (x + 1 < leveys && y + 1 < korkeus) {
                        if (ruudukko[y + 1][x + 1] == Ruutu.SEINÄ) {
                            if (vieressaseina) {
                                vieressatoinenseina = true;
                            }
                            vieressaseina = true;
                        }
                    }

                    if (y + 1 < korkeus) {
                        if (ruudukko[y + 1][x] == Ruutu.SEINÄ) {
                            if (vieressaseina) {
                                vieressatoinenseina = true;
                            }
                            vieressaseina = true;
                        }
                    }

                    if (vieressaseina && !vieressatoinenseina && seinatsanssi < 500) {
                        ruudukko[y][x] = Ruutu.SEINÄ;
                    }
                    //luoSeinat(lista);
                }
            }
        }
    }

    private void luoSeinat(Lista<Solmu> lista) {
        Bestfirst bestfirst = new Bestfirst(lista, this);
        lista = bestfirst.haku();

        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {

                for (Solmu s : lista) {
                    if (s.getX() == x && s.getY() == y) {
                        ruudukko[y][x] = Ruutu.SEINÄ;
                    }
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
