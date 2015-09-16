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

    int leveys;
    int korkeus;
    private ArrayList<ArrayList<Solmu>> kartta;
    int alkupisteX;
    int alkupisteY;
    int maalipisteX;
    int maalipisteY;

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
        Solmu solmu;
        kartta = new ArrayList<ArrayList<Solmu>>();

        for (int x = 0; x < leveys; x++) {
            kartta.add(new ArrayList<Solmu>());
            for (int y = 0; y < korkeus; y++) {
                if (x == alkupisteX && y == alkupisteY) {
                    solmu = new Solmu(x, y, false, true, false);
                } else if (x == maalipisteX && y == maalipisteY) {
                    solmu = new Solmu(x, y, false, false, true);
                } else {
                    solmu = new Solmu(x, y, false, false, false);
                }
                kartta.get(x).add(solmu);
            }
        }
    }

    public ArrayList<ArrayList<Solmu>> getKartta() {
        return kartta;
    }

    /**
     * määrittää tietytn solmun koordinaateissa x ja y maalipisteeksi.
     *
     * @return
     */
    public void setMaali(int x, int y) {
        this.maalipisteX = x;
        this.maalipisteY = y;
        kartta.get(x).get(y).setMaali(true);
    }

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
        kartta.get(x).get(y).setEste(true);
    }

    /**
     * määrittää tietyn solmun koordinaateissa x ja y alkupisteeksi.
     *
     * @param x
     * @param y
     */
    public void setAlku(int x, int y) {
        this.alkupisteX = x;
        this.alkupisteY = y;
        kartta.get(x).get(y).setAlku(true);
    }

    public int getAlkuX() {
        return alkupisteX;
    }

    public int getAlkuY() {
        return alkupisteY;
    }

    public Solmu getAlkuSolmu() {
        return kartta.get(alkupisteX).get(alkupisteY);
    }

    public Solmu getMaaliSolmu() {
        return kartta.get(maalipisteX).get(maalipisteY);
    }

    public Solmu getSolmu(int x, int y) {
        return kartta.get(x).get(y);
    }

    /**
     * laskee matkan arvon. 1 jos normaali ja 100000 jos seinä.
     *
     * @param nykyinen
     * @param seuraava
     * @return
     */
    public int matkaArvo(Solmu nykyinen, Solmu seuraava) {
        if (seuraava.onkoEste == true) {
            return 1000000;
        } else {
            return 1;
        }
    }

//    public List<Solmu> naapurit(Solmu solmu) {
//        ArrayList<Solmu> naapurit = new ArrayList<Solmu>();
//        int x = solmu.getX();
//        int y = solmu.getY();
//
//        if (getSolmu(x + 1, y + 1).onkoEste == false) {
//            naapurit.add(getSolmu(x + 1, y + 1));
//        }
//        if (getSolmu(x + 1, y).onkoEste == false) {
//            naapurit.add(getSolmu(x + 1, y));
//        }
//        if (getSolmu(x + 1, y - 1).onkoEste == false) {
//            naapurit.add(getSolmu(x + 1, y - 1));
//        }
//        if (getSolmu(x, y + 1).onkoEste == false) {
//            naapurit.add(getSolmu(x, y + 1));
//        }
//        if (getSolmu(x - 1, y + 1).onkoEste == false) {
//            naapurit.add(getSolmu(x - 1, y + 1));
//        }
//        if (getSolmu(x - 1, y).onkoEste == false) {
//            naapurit.add(getSolmu(x - 1, y));
//        }
//        if (getSolmu(x - 1, y - 1).onkoEste == false) {
//            naapurit.add(getSolmu(x - 1, y - 1));
//        }
//        if (getSolmu(x, y - 1).onkoEste == false) {
//            naapurit.add(getSolmu(x, y - 1));
//        }
//
//        return naapurit;
//    }
}
