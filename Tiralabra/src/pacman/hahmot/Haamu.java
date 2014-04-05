//Haamujen nimien perusteella kutsutaan tiettyä liikkumistapaa
package pacman.hahmot;

import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.tietorakenteet.Lista;

/**
 *
 * Haamu luokka kuvaa kentällä vapaasti liikkuvia Manin vihollisia. Haamun
 * tehtävä on tietää minne se voi liikkua ja milloin. Haamu luokan avulla myös
 * asetetaan haamuille haluttuja ominaisuuksia.
 *
 * @author hhkopper
 */
public abstract class Haamu extends Hahmo {

    /**
     * Tyyppi kertoo onko haamu heikko vai vahva.
     */
    protected String tyyppi;
    /**
     * Nimen perusteella osataan antaa oikea väri haamuille haamujen muuttuessa
     * heikosta vahvaksi.
     */
    protected String nimi;

    /**
     * Haamun sen hetkisestä ruudusta kaikki suunnat, jossa on käytävää.
     */
    protected Lista mahdollisetSuunnat;
//    private ArrayList<Suunta> mahdollisetSuunnat = new ArrayList<Suunta>();
    /**
     * Kertoo kuinka monen suorituskierroksen ajan haamu on vielä tyyppiä
     * heikko.
     */
    protected int heikkous;

    /**
     * Lista sisältää tiedon ruuduista, joita ei saa valita haamun
     * liikkumisruuduiksi. Haamu siis tietää mihin ruutuun ei saa mennä.
     */
    protected Lista kielletytRuudut;

    public Haamu(int x, int y, Suunta suunta, String nimi, Pelialusta alusta) {
        super(x, y, suunta, alusta);
        this.nimi = nimi;
        this.tyyppi = "vahva";
        kielletytRuudut = new Lista();
        this.lisaaKielletytRuudut();
    }

    /**
     * Metodi lisää haamulle tiedon, mihin ruutuihin tämä ei saa missään nimessä
     * liikkua. Paikat ovat haamujen karsinassa ja reunoilla.
     */
    public void lisaaKielletytRuudut() {

        for (int i = 7; i == 7 || i == 11; i += 4) {
            for (int j = 0; j >= 0 && j < 3; j++) {
                kielletytRuudut.lisaa(alusta.getPeliruutu(j, i));
                kielletytRuudut.lisaa(alusta.getPeliruutu(alusta.getLeveys() - 1 - j, i));
            }
        }
        lisaaHaamujenKarsina();
    }

    /**
     * Lisää kiellettyihin ruutuihin haamujen karsinan.
     */
    private void lisaaHaamujenKarsina() {
        kielletytRuudut.lisaa(alusta.getPeliruutu(8, 9));
        kielletytRuudut.lisaa(alusta.getPeliruutu(9, 8));
        kielletytRuudut.lisaa(alusta.getPeliruutu(9, 9));
        kielletytRuudut.lisaa(alusta.getPeliruutu(10, 9));
    }

    public String getNimi() {
        return this.nimi;
    }

    public Lista getKielletyt() {
        return this.kielletytRuudut;
    }

    public Lista getSuuntaLista() {
        return this.mahdollisetSuunnat;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getTyyppi() {
        return this.tyyppi;
    }

    public int getHeikkous() {
        return this.heikkous;
    }

    public void setHeikkous(int arvo) {
        this.heikkous = arvo;
    }

    /**
     * Vahennetaan heikkoutta yhdellä.
     */
    public void vahennaHeikkous() {
        this.heikkous--;
    }

    /**
     * Ilmoitetaan pelialustalle missä tietyssa ruudussa haamu sijaitsee.
     */
    public void luoHaamuAlustalle() {
        alusta.getPeliruutu(x, y).setOnkoHaamu(true);
    }

    /**
     * Liikuttaa haluttuun ruutuun.
     *
     * @param ruutu
     */
    public void liiku(Peliruutu ruutu) {
        this.setX(ruutu.getX());
        this.setY(ruutu.getY());
        alusta.getPeliruutu(x, y).setOnkoHaamu(true);
        alusta.getPeliruutu(x - suunta.getX(), y - suunta.getY()).setOnkoHaamu(false);
    }

    /**
     * Asetaan haamun koordinaatit lähtöpaikan koordinaateiksi.
     */
    public void palaaAlkuun() {
        alusta.getPeliruutu(x, y).setOnkoHaamu(false);
        this.y = 9;
        this.x = 9;
        alusta.getPeliruutu(x, y).setOnkoHaamu(true);
    }

    /**
     * Tarkistetaan onko kyseisissä koordinaateissa oleva ruutu huono vai
     * sopiva.
     *
     * @param testiX
     * @param testiY
     * @param man
     * @return
     */
    public boolean tarkistaOnkoHuonoRuutu(int testiX, int testiY) {
        if (onkoHuonoRuutu(testiX, testiY)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkistaa onko ruutu käyvä vai huono. Ruutu ei ole hyvä, jos se on
     * alustan ulkopuolella tai se kuuluu kiellettyihin ruutuihin.
     *
     * @param testiX
     * @param testiY
     * @return Palauttaa true, jos ruutu on huono.
     */
    private boolean onkoHuonoRuutu(int testiX, int testiY) {
        if (onkoAlustanSisalla(testiX, testiY) == false || kielletytRuudut.sisaltaa(alusta.getPeliruutu(testiX, testiY))) {
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkistaa ovatko koordinaatit pelialustan sisäpuolella.
     *
     * @param x
     * @param y
     * @return palauttaa totuusarvon.
     */
    public boolean onkoAlustanSisalla(int x, int y) {
        if (x <= 17 && x > 1 && y <= 19 && y > 1) {
            return true;
        }
        return false;
    }

    public boolean olenkoSamassaRuudussaManinKanssa(Man man) {
        if (man.getX() == this.x && man.getY() == this.y) {
            return true;
        }

        return false;
    }

}
