package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

/**
 * Etsijä etsii lyhimmän reitin karttapohjasta.
 *
 * @author heidvill
 */
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
    /**
     * Lyhimmän reitin pituus.
     */
    private int reitinPituus;
    /**
     * Reitti tallennetaan 2-ulotteiseen taulukkoon.
     */
    private int[][] reitti;

    public Etsija(Pohja p) {
        pohja = p;
        kaymattomat = new Minimikeko(p.getKorkeus() * p.getLeveys());
        reitti = null;
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
     * Käy läpi käsiteltävän ruudun kaikki naapuriruudut.
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
     * reitti.
     *
     * @param kasiteltava käsittelyssä oleva ruutu
     * @param viereinen yksi käsittelyssä olevan ruudun viereisistä ruuduista
     */
    private void relax(Ruutu kasiteltava, Ruutu viereinen) {
        if (viereinen.getEtaisyysAlkuun() > kasiteltava.getEtaisyysAlkuun() + viereinen.getArvo() && viereinen.getArvo() != 9) { // Ysin arvoiseen ruutuun ei mennä
            viereinen.setEtaisyysAlkuun(kasiteltava.getEtaisyysAlkuun() + viereinen.getArvo());
            viereinen.setEdellinen(kasiteltava);
//            kaymattomat.paivitaRuutuKekoon(viereinen);
            kaymattomat.rakennaKeko();
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
     * Palauttaa reitin merkkijonona.
     *
     * @return merkkijono reitistä
     */
    public String getReittiMerkkijonona() {
        Ruutu kasiteltava = pohja.getMaali();
        String reitti = "";
        while (!kasiteltava.equals(pohja.getLahto())) {
            reitti = " " + kasiteltava.koordinaatit() + reitti;
            kasiteltava = kasiteltava.getEdellinen();
        }
        reitti = pohja.getLahto().koordinaatit() + reitti;
        return reitti;
    }

    /**
     * Tallentaa löydetyn reitin 2-ulotteiseen taulukkoon.
     */
    public void tallennaReittiTaulukkoon() {
        laskeReitinPituus();
        reitti = new int[reitinPituus][2];
        Ruutu kasiteltava = pohja.getMaali();
        for (int i = reitinPituus - 1; i >= 0; i--) {
            reitti[i][0] = kasiteltava.getX();
            reitti[i][1] = kasiteltava.getY();
            kasiteltava = kasiteltava.getEdellinen();
        }
    }
    
    /**
     * Laskee reitin pituuden.
     */
    private void laskeReitinPituus(){
        reitinPituus = 0;
        Ruutu kasiteltava = pohja.getMaali();
        reitinPituus++;
        while (!kasiteltava.equals(pohja.getLahto())) {
            kasiteltava = kasiteltava.getEdellinen();
            reitinPituus++;
        }
    }

    /**
     * Tarkistaa onko annetuissa koordinaateissa oleva ruutu lyhimmällä reitillä.
     * @param x rivi
     * @param y sarake
     * @return true, jos ruutu on lyhimmällä reitillä, muuten false.
     */
    public boolean onkoRuutuReitilla(int x, int y) {
        for (int i = 0; i < reitinPituus; i++) {
            if (reitti[i][0] == x && reitti[i][1] == y) {
                return true;
            }
        }
        return false;
    }

    public Minimikeko getKaymattomat() {
        return kaymattomat;
    }

    public Pohja getPohja() {
        return pohja;
    }

    public int[][] getReitti() {
        return reitti;
    }

    public int getReitinPituus() {
        return reitinPituus;
    }
}
