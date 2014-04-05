package pacman.hahmot;

import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.peli.Pacman;

/**
 * Man luokka kuvaa pelin päähahmoa, jota pelaaja liikuttaa kentällä. Manin
 * tehtävä on liikuttaa itseään kentällä ja tietää minne se voi liikkua. Man
 * myös tietää omat elämänsä ja osaa vähentää niitä, kun man kuolee.
 *
 * @author hhkopper
 */
public class Man extends Hahmo {

    /**
     * Manin elämien määrä, kun elämät loppuvat, peli loppuu. Arvoksi asetetaan
     * kolme.
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
        super(x, y, suunta, alusta);
        elamat = 3;
    }

    /**
     * Kerrotaan alustalle, missä ruudussa Man on.
     */
    public void luoManAlustalle() {
        alusta.getPeliruutu(x, y).setOnkoMan(true);
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
     * Man jää paikalleen. Jos heikkous on true, eli haamut ovat heikkoja liikkuu Man kaksi ruutua kerralla, jos ei olla risteyksessä.
     *
     * @param heikkous
     * @param peli
     */
    public void liiku(boolean heikkous, Pacman peli) {

        etene();
        if (osuukoSeinaan()) {
            palaa();
        } else {
            siirraMan();
        }

        if (heikkous != false) {
            if (katsoVoikoLiikkuaSivuille() == false) {
                peli.manSyoPistepallo();
                peli.luoHedelma();
                peli.kuoleekoHaamuTaiMan();
                etene();
                if (osuukoSeinaan()) {
                    palaa();
                }
                siirraMan();
            }
        }

    }

    /**
     * Siirtyy yhden ruudun takaisin päin.
     */
    private void palaa() {
        this.y = this.y - this.suunta.getY();
        this.x = this.x - this.suunta.getX();
    }

    /**
     * Siirtyy yhden ruudun eteenpäin.
     */
    private void etene() {
        this.y = this.y + this.suunta.getY();
        this.x = this.x + this.suunta.getX();
    }

    /**
     * Siirtää manin tiedot alustalla.
     */
    private void siirraMan() {
        alusta.getPeliruutu(x, y).setOnkoMan(true);
        alusta.getPeliruutu(x - suunta.getX(), y - suunta.getY()).setOnkoMan(false);
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
