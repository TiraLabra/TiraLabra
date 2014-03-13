package pacman.hahmot;

import java.util.ArrayList;
import java.util.Random;
import pacman.alusta.Pelialusta;

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
     * Haamua liikuttava metodi. 
     * Jos haamun seuraava ruutu olisi seinä arvotaan uusi suunta, minkä jälkeen liikutaan tähän suuntaa. 
     * Liiku metodi myös tarkistaa, pääseekö ruudusta, jossa haamu on myös muualle kuin samaan suuntaan jatkamalla tai peruuttamalla. 
     * Jos muuallekin pääsee arvotaan uusi suunta ja liikutaan sinne. 
     * Muissa tapauksissa liikutaan suuntaan, joka haamulla on suuntana.
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

        mahdollisetSuunnat = new ArrayList<Suunta>();

        for (Suunta s : Suunta.values()) {
            if (alusta.getPeliruutu(x + s.getX(), y + s.getY()).getRuudunTyyppi() == 1 || alusta.getPeliruutu(x + s.getX(), y + s.getY()).getRuudunTyyppi() == 3) {
                mahdollisetSuunnat.add(s);
            }
        }

        Random arpoja = new Random();
        int arpaluku = arpoja.nextInt(mahdollisetSuunnat.size());
        this.suunta = mahdollisetSuunnat.get(arpaluku);
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

}
