//Haamujen nimien perusteella kutsutaan tiettyä liikkumistapaa
package pacman.hahmot;

import java.util.ArrayList;
import java.util.Random;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;

/**
 *
 * Haamu luokka kuvaa kentällä vapaasti liikkuvia Manin vihollisia. Haamun
 * tehtävä on tietää minne se voi liikkua ja milloin. Haamu luokan avulla myös
 * asetetaan haamuille haluttuja ominaisuuksia.
 *
 * @author hhkopper
 */
public class Haamu extends Hahmo {

    /**
     * Tyyppi kertoo onko haamu heikko vai vahva.
     */
    private String tyyppi;
    /**
     * Nimen perusteella osataan antaa oikea väri haamuille haamujen muuttuessa
     * heikosta vahvaksi.
     */
    private String nimi;

    /**
     * Haamun sen hetkisestä ruudusta kaikki suunnat, jossa on käytävää.
     */
    private ArrayList<Suunta> mahdollisetSuunnat = new ArrayList<Suunta>();
    /**
     * Kertoo kuinka monen suorituskierroksen ajan haamu on vielä tyyppiä
     * heikko.
     */
    private int heikkous;

    private ArrayList<Peliruutu> kielletytRuudut;

    /**
     * Konstruktorissa Haamulle asetetaan tarvittavat arvot, jotka saadaan
     * parametrina. Haamun tyypiksi asetetaan aluksi vahva.
     *
     * @param x koordinaatti X
     * @param y koordinaatti Y
     * @param nimi haamun nimi, jonka avulla tunnistetaan väri.
     * @param suunta Suunta, johon haamu liikkuu.
     * @param alusta alusta, jolle haamu luodaan.
     */
    public Haamu(int x, int y, Suunta suunta, String nimi, Pelialusta alusta) {
        this.y = y;
        this.x = x;
        this.suunta = suunta;
        this.tyyppi = "vahva";
        this.nimi = nimi;
        this.alusta = alusta;
        this.kielletytRuudut = new ArrayList<Peliruutu>();
    }

    public void lisaaKielletytRuudut() {

        for (int y = 7; y == 7 && y == 11; y += 4) {
            for (int x = 0; x >= 0 && x < 3; x++) {
                kielletytRuudut.add(alusta.getPeliruutu(x, y));
                kielletytRuudut.add(alusta.getPeliruutu(alusta.getLeveys() - 1 - x, y));
            }
        }
        kielletytRuudut.add(alusta.getPeliruutu(8, 9));
        kielletytRuudut.add(alusta.getPeliruutu(9, 8));
        kielletytRuudut.add(alusta.getPeliruutu(9, 9));
        kielletytRuudut.add(alusta.getPeliruutu(10, 9));

    }

    public String getNimi() {
        return this.nimi;
    }

    public ArrayList<Suunta> getSuuntaLista() {
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
     * Haamua liikuttava metodi. Jos haamun seuraava ruutu olisi seinä arvotaan
     * uusi suunta, minkä jälkeen liikutaan tähän suuntaa. Liiku metodi myös
     * tarkistaa, pääseekö ruudusta, jossa haamu on myös muualle kuin samaan
     * suuntaan jatkamalla tai peruuttamalla. Jos muuallekin pääsee arvotaan
     * uusi suunta ja liikutaan sinne. Muissa tapauksissa liikutaan suuntaan,
     * joka haamulla on suuntana.
     */
    public void liiku() {
        if (alusta.getPeliruutu(x + this.suunta.getX(), y + this.suunta.getY()).getRuudunTyyppi() == 0) {
            arvoUusiSuunta();
            liikuSuunta();
        } else if (katsoVoikoLiikkuaSivuille()) {
            arvoUusiSuunta();
            liikuSuunta();
        } else {
            liikuSuunta();
        }
    }

    /**
     * Liikutaan suuntaan, joka haamulla on suuntana. Muutetaan haamun
     * koordinaatteja ja kerrotaan pelialustalle mistä ruudusta mihin ruutuun
     * haamu siirtyy.
     */
    public void liikuSuunta() {

        this.y = this.y + suunta.getY();
        this.x = this.x + suunta.getX();
        alusta.getPeliruutu(x, y).setOnkoHaamu(true);
        alusta.getPeliruutu(x - suunta.getX(), y - suunta.getY()).setOnkoHaamu(false);
    }

    /**
     * Kerätään listalle kaikki mahdolliset suunnat, johon haamu pystyy
     * liikkumaan. Arvotaan listan perusteella numero, joka kertoo mistä
     * indeksistä valitaan uusi suunta. Arvottu suunta asetaan haamulle uudeksi
     * suunnaksi.
     */
    public void arvoUusiSuunta() {

        selvitaMahdollisetSuunnat();

        Random arpoja = new Random();
        int arpaluku = arpoja.nextInt(mahdollisetSuunnat.size());
        this.suunta = mahdollisetSuunnat.get(arpaluku);
    }

    private void selvitaMahdollisetSuunnat() {

        mahdollisetSuunnat = new ArrayList<Suunta>();

        for (Suunta s : Suunta.values()) {
            Peliruutu tarkasteltava = alusta.getPeliruutu(x + s.getX(), y + s.getY());

            if (tarkasteltava.getRuudunTyyppi() == 1 || tarkasteltava.getRuudunTyyppi() == 3) {
                mahdollisetSuunnat.add(s);
            }
        }
    }

    private void selvitaMahdollisetSuunnat2(Peliruutu maali) {
        mahdollisetSuunnat = new ArrayList<Suunta>();

        for (Suunta s : Suunta.values()) {
            Peliruutu tarkasteltava = alusta.getPeliruutu(maali.getX() + s.getX(), maali.getY() + s.getY());

            if (tarkasteltava.getRuudunTyyppi() == 1) {
                mahdollisetSuunnat.add(s);
            }
        }
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

    public Peliruutu selvitaMaali(Man man) {

        int testiX = man.getX() + man.getSuunta().getX() * 3;
        int testiY = man.getY() + man.getSuunta().getY() * 3;

        if (onkoHuonoRuutu(testiX, testiY)) {
            return alusta.getPeliruutu(man.getX(), man.getY());
        }

        Peliruutu maali = alusta.getPeliruutu(testiX, testiY);

        if (maali.getRuudunTyyppi() == 0) {
            selvitaMahdollisetSuunnat2(maali);
            if (!mahdollisetSuunnat.isEmpty()) {

                Suunta snta = arvoSuunta();
                maali = alusta.getPeliruutu(maali.getX() + snta.getX(), maali.getY() + snta.getY());

                if (onkoAlustanSisalla(maali.getX(), maali.getY())) {
                    return maali;
                }
            }
        }
        return alusta.getPeliruutu(man.getX(), man.getY());

    }

    private Suunta arvoSuunta() {
        Random arpoja = new Random();
        int arpaluku = arpoja.nextInt(mahdollisetSuunnat.size());
        Suunta snta = mahdollisetSuunnat.get(arpaluku);
        return snta;
    }

    private boolean onkoHuonoRuutu(int testiX, int testiY) {
        if (onkoAlustanSisalla(testiX, testiY) == false || kielletytRuudut.contains(alusta.getPeliruutu(testiX, testiY)) == true) {
            return true;
        }
        return false;
    }

    public boolean onkoAlustanSisalla(int x, int y) {
        return x <= 17 && x > 1 && y <= 19 && y > 1;
    }

}
