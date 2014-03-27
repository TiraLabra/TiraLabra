package labyrintti.sovellus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

public class Etsija {
    //tira pruju s. 613 http://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf

    /**
     * Minimikeko käymättömistä ruuduista. Ensimmäisenä on ruutu, jolla alun ja
     * lopun etäisyyksien summa on pienin.
     */
    private Minimikeko kaymattomat;
    /**
     * Pohja-olio, johon pohjakartta on tallennettu.
     */
    private Pohja pohja;

    public Etsija(Pohja p) {
        pohja = p;
        kaymattomat = new Minimikeko(p.getKorkeus() * p.getLeveys());

    }

    /**
     * Alustaa pohjan ruutujen arvot.
     */
    public void alustus() {
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                Ruutu ruutu = pohja.getRuutu(i, j);
                ruutu.laskeEtaisyysMaaliin(pohja.getMaaliX(), pohja.getMaaliY());
                if (ruutu.equals(pohja.getLahto())) {
                    ruutu.setEtaisyysAlkuun(0);
                }
            }
        }
        kaymattomat.alustaTaulukko(pohja);
        kaymattomat.rakennaKeko();
    }

    /**
     * A*-algoritmi, joka etsii lyhimmän reitin lähdöstä maaliin.
     */
    public void aStar() {
        alustus();
        while (!pohja.getMaali().onkoKayty()) {
            Ruutu kasiteltava = kaymattomat.pollPienin();
            kasiteltava.setKayty(true);
            kayLapiViereisetRuudut(kasiteltava);
        }
    }

    /**
     * Käy läpi käsiteltävän ruudun kaikki naapuriruudut
     *
     * @param kasiteltava käsittelyssä oleva ruutu
     */
    private void kayLapiViereisetRuudut(Ruutu kasiteltava) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i == 0 && j != 0) || (i != 0 && j == 0)) { //käydään läpi viereiset ruudut pysty- ja vaakasuunnassa
                    int x = kasiteltava.getX() + i;
                    int y = kasiteltava.getY() + j;
                    if (ruutuPohjansisalla(x, y)) {
                        Ruutu viereinen = pohja.getRuutu(x, y);
                        relax(kasiteltava, viereinen);
                    }
                }
            }
        }
    }

    /**
     * Päivittää viereisen ruudun etäisyyden alkuun, jos on löytynyt lyhyempi
     * reitti
     *
     * @param kasiteltava käsittelyssä oleva ruutu
     * @param viereinen yksi käsittelyssä olevan ruudun viereisistä ruuduista
     */
    private void relax(Ruutu kasiteltava, Ruutu viereinen) {
        if (viereinen.getEtaisyysAlkuun() > kasiteltava.getEtaisyysAlkuun() + viereinen.getArvo() && viereinen.getArvo() != 9) { // Ysin arvoiseen ruutuun ei menn
            viereinen.setEtaisyysAlkuun(kasiteltava.getEtaisyysAlkuun() + viereinen.getArvo());
            viereinen.setEdellinen(kasiteltava);
            kaymattomat.paivitaRuutuKekoon(kasiteltava);

        }
    }

    /**
     * Tarkistetaan, että koordinaatit eivät mene taulukon ulkopuolelle.
     *
     * @param x Kertoo ruudun rivin.
     * @param y Kertoo ruudun sarakkeen.
     * @return false, jos ruutu on ulkopuolella, muuten true.
     */
    private boolean ruutuPohjansisalla(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= pohja.getKorkeus() || y >= pohja.getLeveys()) {
            return false;
        }
        return true;
    }

    /**
     * Tallentaa reitin listaan. Hyödyntää ruutujen edellinen-attribuuttia.
     * Koska reitti tallentuu listaan päinvastaisessa järjestyksessä, täytyy
     * lista kääntää.
     *
     * @return lista, jossa reitti. Ensimmäisenä on reitin lähtöruutu.
     */
    public void getReitti() {
        Ruutu kasiteltava = pohja.getMaali();
        tulostaReitti(kasiteltava);
    }
    
    public void tulostaReitti(Ruutu kasiteltava) {
        if(kasiteltava==null){
            return;
        }
        tulostaReitti(kasiteltava.getEdellinen());
        System.out.println(kasiteltava.koordinaatit());
    }

    public Minimikeko getKaymattomat() {
        return kaymattomat;
    }

    public Pohja getPohja() {
        return pohja;
    }
}
