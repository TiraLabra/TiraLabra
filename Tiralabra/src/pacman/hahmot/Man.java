package pacman.hahmot;

import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;

/**
 * Man luokka kuvaa pelin päähahmoa, jota pelaaja liikuttaa kentällä. Manin
 * tehtävä on liikuttaa itseään kentällä ja tietää minne se voi liikkua. Man myös
 * tietää omat elämänsä ja osaa vähentää niitä, kun man kuolee.
 *
 * @author hhkopper
 */
public class Man {

    /**
     * Koordinaatti X
     */
    private int x;
      /**
     * Koordinaatti Y
     */
    private int y;
      /**
     * Suunta, johon Man liikkuu.
     */
    private Suunta suunta;
      /**
     * Alusta, jolle Man luodaan.
     */
    private Pelialusta alusta;
      /**
     * Manin elämien määrä, kun elämät loppuvat, peli loppuu. Arvoksi asetetaan kolme.
     */
    private int elamat;

    /**
     * Konstruktorissa annetaan Manille tarvittavat arvot ja asetetaan elämien
     * määräksi kolme.
     *
     * @param x koordinaatti X
     * @param y koordinaatti Y
     * @param suunta Suunta johon man liikkuu.
     * @param alusta alusta, jolle man luodaan.
     */
    public Man(int x, int y, Suunta suunta, Pelialusta alusta) {
        this.x = x;
        this.y = y;
        this.suunta = suunta;
        this.alusta = alusta;
        elamat = 3;
    }

    /**
     * Kerrotaan alustalle, missä ruudussa Man on.
     */
    public void luoManAlustalle() {
        alusta.getPeliruutu(x, y).setOnkoMan(true);
    }

    public Suunta getSuunta() {
        return this.suunta;
    }

    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getElamat() {
        return elamat;
    }

    /**
     * Vähennetään elämien määrää yhdellä.
     */
    public void vahennaElama() {
        this.elamat--;
    }

    /**
     * Muutetaan koordinaatteja ja kerrotaan alustalle mistä ruudusta mihin
     * ruutuun Man liikkuu. Jos seuraava ruutu on seinä mihin ollaan liikkumassa
     * Man jää paikalleen.
     */
    public void liiku() {
        this.y = this.y + this.suunta.getY();
        this.x = this.x + this.suunta.getX();

        if (osuukoSeinaan()) {
            this.y = this.y - this.suunta.getY();
            this.x = this.x - this.suunta.getX();
        } else {
            alusta.getPeliruutu(x, y).setOnkoMan(true);
            alusta.getPeliruutu(x - suunta.getX(), y - suunta.getY()).setOnkoMan(false);
        }
    }

    /**
     * Tarkistetaan onko ruudyn tyyppi seinä vai ei (0 vai joku muu).
     *
     * @return palauttaa boolean arvon true, jos osuu seinään, false, jos ei.
     */
    private boolean osuukoSeinaan() {
        Peliruutu ruutu = alusta.getPeliruutu(x, y);
        if (ruutu.getRuudunTyyppi() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Asetetaan Manin koordinaateiksi sen lähtöruutu.
     */
    public void palaaAlkuun() {
        alusta.getPeliruutu(x, y).setOnkoMan(false);
        this.x = 9;
        this.y = 11;
        alusta.getPeliruutu(x, y).setOnkoMan(true);
    }
}
