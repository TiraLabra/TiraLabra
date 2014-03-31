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

    /**
     * Lista sisältää tiedon ruuduista, joita ei saa valita haamun
     * liikkumisruuduiksi. Haamu siis tietää mihin ruutuun ei saa mennä.
     */
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

    /**
     * Metodi lisää haamulle tiedon, mihin ruutuihin tämä ei saa missään nimessä
     * liikkua. Paikat ovat haamujen karsinassa ja reunoilla.
     */
    public void lisaaKielletytRuudut() {

        for (int i = 7; i == 7 || i == 11; i += 4) {
            for (int j = 0; j >= 0 && j < 3; j++) {
                kielletytRuudut.add(alusta.getPeliruutu(j, i));
                kielletytRuudut.add(alusta.getPeliruutu(alusta.getLeveys() - 1 - j, i));
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

    public ArrayList<Peliruutu> getKielletyt() {
        return this.kielletytRuudut;
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

    /**
     * Metodi selvittää mihin suuntii haamu voisi liikahtaa, siitä ruudussa
     * missä se metodin kutsuma hetkellä on. Tallentaa sopivat suunnat
     * ArrayListaan.
     */
    private void selvitaMahdollisetSuunnat() {

        mahdollisetSuunnat = new ArrayList<Suunta>();

        for (Suunta s : Suunta.values()) {
            Peliruutu tarkasteltava = alusta.getPeliruutu(x + s.getX(), y + s.getY());

            if (tarkasteltava.getRuudunTyyppi() == 1 || tarkasteltava.getRuudunTyyppi() == 3) {
                mahdollisetSuunnat.add(s);
            }
        }
    }

    /**
     * Metodi selvittää missä suunnassa uusi maali voisi olla, jos alkuperäinen
     * ruutu ei sovi maaliruuduksi.
     *
     * @param maali
     */
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

    /**
     * Metodi tarkistaa onko maali sopiva ruutu, haamulle Cyan. Haamulla on
     * kohteena kolme ruutua Manin edellä oleva ruutu. Jos ruutu ei ole sopiva
     * maaliksi etsitään uusi ruutu. os ei löydy mitään sopivaa ruutua, maali on
     * Man itse.
     *
     * @param man
     * @return Palauttaa sopivan maali ruudun.
     */
    public Peliruutu selvitaMaali(Man man) {

        int testiX = man.getX() + man.getSuunta().getX() * 3;
        int testiY = man.getY() + man.getSuunta().getY() * 3;

        if (onkoHuonoRuutu(testiX, testiY)) {
            return alusta.getPeliruutu(man.getX(), man.getY());
        }

        Peliruutu maali = alusta.getPeliruutu(testiX, testiY);

        if (maali.getX() == this.x && maali.getY() == this.y) {
            return selvitaMaaliSuunnista(maali, man);
        }

        return tarkistaOnkoSeina(maali, man);

    }

    /**
     * Metodi tarkistaa onko maalin tyyppi seinä.
     * Jos maali on seinä, metodi etsii uuden sopivan ruudun ja tämän jälkeen tarkistaa vielä, että etsitty ruutu ei ole sellainen missä olisi haamu.
     * Jos ruudussa on haamu, täytyy etsiä vielä uusi ruutu.
     * Jos alkuperäinen maali ei ole seinä, petodi palauttaa alkuperäisen maalin.
     * @param maali
     * @param man
     * @return palauttaa sopivan maali ruudun.
     */
    private Peliruutu tarkistaOnkoSeina(Peliruutu maali, Man man) {
        if (maali.getRuudunTyyppi() == 0) {
            Peliruutu uusiMaali = selvitaMaaliSuunnista(maali, man);
            if (uusiMaali.getX() == this.x && uusiMaali.getY() == this.y) {
                return selvitaMaaliSuunnista(uusiMaali, man);
            } else {
                return uusiMaali;
            }
        } else {
            return maali;
        }
    }

    /**
     * Metodi selvittää uuden maaliruudun, vanhan maaliruudun ympärillä olevista
     * ruuduista.
     *
     * @param maali
     * @param man
     * @return palauttaa uuden maaliruudun
     */
    private Peliruutu selvitaMaaliSuunnista(Peliruutu maali, Man man) {

        selvitaMahdollisetSuunnat2(maali);
        if (!mahdollisetSuunnat.isEmpty()) {
            Suunta snta = arvoSuunta();
            Peliruutu uusiMaali = alusta.getPeliruutu(maali.getX() + snta.getX(), maali.getY() + snta.getY());
            if (onkoAlustanSisalla(maali.getX(), maali.getY())) {
                return alusta.getPeliruutu(uusiMaali.getX(), uusiMaali.getY());
            }
        }
        return alusta.getPeliruutu(man.getX(), man.getY());
    }

    /**
     * Metodi arvoo listan komponenteista yhden.
     *
     * @return Palauttaa listasta arvotun suunnan.
     */
    private Suunta arvoSuunta() {
        Random arpoja = new Random();
        int arpaluku = arpoja.nextInt(mahdollisetSuunnat.size());
        Suunta snta = mahdollisetSuunnat.get(arpaluku);
        return snta;
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
        this.lisaaKielletytRuudut();
        if (onkoAlustanSisalla(testiX, testiY) == false || kielletytRuudut.contains(alusta.getPeliruutu(testiX, testiY)) == true) {
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
    private boolean onkoAlustanSisalla(int x, int y) {
        if (x <= 17 && x > 1 && y <= 19 && y > 1) {
            return true;
        }
        return false;
    }

}
