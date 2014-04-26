package pacman.peli;

import java.util.Random;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Cyan;
import pacman.hahmot.Green;
import pacman.hahmot.Haamu;
import pacman.hahmot.Magenta;
import pacman.hahmot.Red;
import pacman.hahmot.Suunta;
import pacman.tietorakenteet.AStar;

/**
 * Haamujenkäsittelijä on luokka, joka hallinnoi haamujen toimintaa
 * pelialustalla.
 *
 * @author Hanna
 */
public class HaamujenKasittelija {

    private Red red;
    private Cyan cyan;
    private Green green;
    private Magenta magenta;
    /**
     * Ruutu, joka kuvastaa haamun Magenta kohdetta.
     */
    private Peliruutu magentaMaali;
    /**
     * Hakualgoritmi
     */
    private AStar haku;
    /**
     * Random tyyppinen arpoja.
     */
    private Random arpoja;
    private Pacman peli;

    /**
     * Konstruktori, jossa määritellää haamut alustalle ja tarvittavat
     * parametrit.
     *
     * @param peli
     * @param arpoja
     * @param hakija
     */
    public HaamujenKasittelija(Pacman peli, Random arpoja, AStar hakija) {
        red = new Red(9, 7, Suunta.YLOS, "red", peli.getAlusta());
        green = new Green(10, 9, Suunta.YLOS, "green", peli.getAlusta());
        cyan = new Cyan(8, 9, Suunta.YLOS, "cyan", peli.getAlusta());
        magenta = new Magenta(9, 9, Suunta.YLOS, "magenta", peli.getAlusta());
        luoHaamutAlustalle();
        this.magentaMaali = magenta.selvitaMaaliMagenta(arpoja);
        this.haku = hakija;
        this.arpoja = arpoja;
        this.peli = peli;
    }

    /**
     * Luodaan haamut alustalle, eli asetetaan jokaiselle ruudulle tietohaamusta, jossa haamu alussa on.
     */
    private void luoHaamutAlustalle() {
        red.luoHaamuAlustalle();
        green.luoHaamuAlustalle();
        cyan.luoHaamuAlustalle();
        magenta.luoHaamuAlustalle();
    }

    public Red getRed() {
        return this.red;
    }

    public Magenta getMagenta() {
        return this.magenta;
    }

    public Cyan getCyan() {
        return this.cyan;
    }

    public Green getGreen() {
        return this.green;
    }

    public void setMagentaMaali(Peliruutu ruutu) {
        this.magentaMaali = ruutu;
    }

    public Peliruutu getMagentaMaali() {
        return this.magentaMaali;
    }

    /**
     * Asetetaan haamujen tyypiksi heikko, jolloin man pystyy syömään haamuja.
     */
    public void heikennaHaamut() {
        peli.setHeikko(true);
        heikennaHaamu(red);
        heikennaHaamu(green);
        heikennaHaamu(cyan);
        heikennaHaamu(magenta);

    }

    /**
     * Heikennetään parametrinä oleva haamu.
     *
     * @param haamu
     */
    public void heikennaHaamu(Haamu haamu) {
        haamu.setTyyppi("heikko");
        haamu.setHeikkous(30);
    }

    /**
     * Jos haamun tyyppi on heikko ja heikkousaika on loppunut, muutetaan haamun
     * tyyppi vahvaksi. Jos heikkousaika ei ole vielä loppu, vähennetään sitä.
     *
     * @param haamu haamu, jonka tyyppiä tutkitaan.
     */
    private void haamuHeikostaVahvaksi(Haamu haamu) {
        if (haamu.getTyyppi().equals("heikko")) {
            if (haamu.getHeikkous() == 0) {
                haamu.setTyyppi("vahva");
                peli.setHeikko(false);
            } else {
                haamu.vahennaHeikkous();
            }
        }
    }

    /**
     * Liikutetaan haamut.
     */
    public void liikutaHaamut() {

        liikutaHaamu(red);
        liikutaHaamu(green);
        liikutaHaamu(cyan);      
        liikutaHaamu(magenta);       

        tarkistaHaamujenRuudut();
    }

    /**
     * Kutsutaan tarkistus metodia jokaiselle haamulle
     */
    public void tarkistaHaamujenRuudut() {
        tarkistaEttaRuudutTietavatNiissaOlevanHaamu(cyan);
        tarkistaEttaRuudutTietavatNiissaOlevanHaamu(red);
        tarkistaEttaRuudutTietavatNiissaOlevanHaamu(green);
        tarkistaEttaRuudutTietavatNiissaOlevanHaamu(magenta);
    }

    /**
     * Varmistetaan, että ruutu jossa haamu on tietää todella, että ruudussa on
     * haamu.
     *
     * @param haamu
     */
    private void tarkistaEttaRuudutTietavatNiissaOlevanHaamu(Haamu haamu) {
        Peliruutu haamunRuutu = peli.getAlusta().getPeliruutu(haamu.getX(), haamu.getY());
        if (haamunRuutu.getOnkoHaamu() == false) {
            haamunRuutu.setOnkoHaamu(true);
        }
    }

    /**
     * Metodi saa paramertinaan liikutettavan haamun ja liikuttaa sitä tavalla,
     * joka riippuu siitä onko haamu heikko vai vahva.
     *
     * @param haamu
     */
    private void liikutaHaamu(Haamu haamu) {
        if (haamu.getTyyppi().equals("heikko")) {
            liikutaHaamuHeikkona(haamu);
        } else {
            liikuHaamuVahvana(haamu);
        }
        haamuHeikostaVahvaksi(haamu);
    }

    /**
     * Metodi katsoo parametrina saamansa haamun nimen ja liikuttaa jokaista
     * haamua omalla tavallaan.
     *
     * @param haamu
     */
    private void liikuHaamuVahvana(Haamu haamu) {
        if (haamu.getNimi().equals("red")) {
            liikutaVahvaRed();
        } else if (haamu.getNimi().equals("green")) {
            liikutaVahvaGreen();
        } else if (haamu.getNimi().equals("cyan")) {
            liikutaVahvaCyan();
        } else if (haamu.getNimi().equals("magenta")) {
            liikutaVahvaMagenta();
        }
    }

    /**
     * Metodi katsoo parametrina saamansa haamun nimen ja liikuttaa jokaista
     * haamua omalla tavallaan.
     *
     * @param haamu
     */
    private void liikutaHaamuHeikkona(Haamu haamu) {
        if (haamu.getNimi().equals("red")) {
            liikutaRedHeikko();
        } else if (haamu.getNimi().equals("green")) {
            liikutaGreenHeikko();
        } else if (haamu.getNimi().equals("cyan")) {
            liikutaCyanHeikko();
        } else if (haamu.getNimi().equals("magenta")) {
            liikutaMagentaHeikko();
        }
    }

    /**
     * Liikutetaan vahva-tyyppinen Green sen omalla liiku-metodilla.
     */
    private void liikutaVahvaGreen() {
        green.liiku();
    }

    /**
     * Selvitetään ensin Magentalle maali ruutu, minkä jälkeen etsitään ruutu,
     * johon Magentan on kannattavinta liikkua. Tämän jälkeen siirretään Magenta
     * ruutuun, jonka haku palauttaa.
     */
    private void liikutaVahvaMagenta() {

        if (magenta.getX() == magentaMaali.getX() && magenta.getY() == magentaMaali.getY()) {
            while (magenta.getX() == magentaMaali.getX() && magenta.getY() == magentaMaali.getY()) {
                magentaMaali = magenta.selvitaMaaliMagenta(arpoja);
            }
        }

        haku.astar(peli.getAlusta(), peli.getAlusta().getPeliruutu(magenta.getX(), magenta.getY()), magentaMaali);
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length - 1];
        magenta.liiku(siirto);

    }

    /**
     * Selvitetään Cyanille maali ruutu ja tämän jälkeen haulla etsitään mihin
     * ruutuun Cyanin kannattaa liikahtaa. Tämän jälkeen siirretään Cyan haun
     * palauttamaan ruutuun.
     */
    private void liikutaVahvaCyan() {

        Peliruutu maali = cyan.selvitaMaaliCyan(peli.getMan());
        haku.astar(peli.getAlusta(), peli.getAlusta().getPeliruutu(cyan.getX(), cyan.getY()), maali);
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length - 1];
        cyan.liiku(siirto);

    }

    /**
     * Annetaan haulle manin koordinaatit maaliksi ja liikutetaan Red hauan
     * antamaan ruutuun.
     */
    private void liikutaVahvaRed() {

        haku.astar(peli.getAlusta(), peli.getAlusta().getPeliruutu(red.getX(), red.getY()), peli.getAlusta().getPeliruutu(peli.getMan().getX(), peli.getMan().getY()));
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length - 1];
        red.liiku(siirto);
    }

    /**
     * Liikutetaan Green samalla tavalla kuin vahvana.
     */
    private void liikutaGreenHeikko() {
        green.liiku();

    }

    /**
     * Liikutetaan Magenta heikkona samalla tavalla kuin vahvana.
     */
    private void liikutaMagentaHeikko() {
        this.liikutaVahvaMagenta();
    }

    /**
     * Liikutetaan Cyan heikkona siten, että lasketaan sille maaliksi
     * vastakkainen ruutu, kuin mikä sillä olisi sen ollessa vahva. Tämän
     * jälkeen etsitään taas haulla ruutu, johon Cyanin kannattaa liikahtaa.
     */
    private void liikutaCyanHeikko() {
        Peliruutu maali = selvitaCyanMaaliHeikkona();
        haku.astar(peli.getAlusta(), peli.getAlusta().getPeliruutu(cyan.getX(), cyan.getY()), peli.getAlusta().getPeliruutu(maali.getX(), maali.getY()));
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length - 1];
        cyan.liiku(siirto);
    }

    /**
     * Metodin avulla selvitään ensin normaalisti Cyanin maali ja tämän jälkeen
     * kutsutaan metodi, joka selvittää haamun maalin kun se on heikko.
     *
     * @return palauttaa maalin
     */
    public Peliruutu selvitaCyanMaaliHeikkona() {
        Peliruutu maali = cyan.selvitaMaaliCyan(peli.getMan());
        maali = maaliHeikolleCyan(maali);
        return maali;
    }

    /**
     * Selvittää peilatun maaliruudun ja tarkistaa vielä, että jos kyseinen
     * ruutu on haamun oma ruutu maaliruutu on tällöin manin ruutu.
     *
     * @param maali
     * @return
     */
    private Peliruutu maaliHeikolleCyan(Peliruutu maali) {
        int peilaus = 9 - maali.getX();
        maali = peli.getAlusta().getPeliruutu(9 + peilaus, maali.getY());
        if (maali.getX() == cyan.getX() && maali.getY() == cyan.getY()) {
            maali = peli.getAlusta().getPeliruutu(peli.getMan().getX(), peli.getMan().getY());
        }
        return maali;
    }

    /**
     * Liikutetaan Red heikkona siten, että lasketaan ensin vastakkainen ruutu,
     * kuin mikä sillä olisi sen ollessa vahva. Tämän jälkeen etsitään taas
     * haulla ruutu, johon Redin kannattaa liikahtaa.
     */
    private void liikutaRedHeikko() {
        Peliruutu maali = maaliHeikolleRed();
        haku.astar(peli.getAlusta(), peli.getAlusta().getPeliruutu(red.getX(), red.getY()), peli.getAlusta().getPeliruutu(maali.getX(), maali.getY()));
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length - 1];
        red.liiku(siirto);
    }

    /**
     * Selvittää peilatun haamun maalin, jos maaliruutu on haamun omaruutu,
     * maali ruutu on manin ruutu.
     *
     * @return
     */
    public Peliruutu maaliHeikolleRed() {
        int peilaus = 9 - peli.getMan().getX();
        Peliruutu maali = peli.getAlusta().getPeliruutu(9 + peilaus, peli.getMan().getY());
        if (maali.getX() == red.getX() && maali.getY() == red.getY()) {
            maali = peli.getAlusta().getPeliruutu(peli.getMan().getX(), peli.getMan().getY());
        }
        return maali;
    }

    /**
     * Lasketaan kuinka monta haamuista on edelleen heikkona.
     *
     * @return Palauttaa int arvon, kuinka monta haamua on edelleen heikkona.
     */
    public int tarkistaOnkoHeikkoja() {
        int heikkoja = 0;
        heikkoja = onkoHeikkoHaamu(heikkoja, red);
        heikkoja = onkoHeikkoHaamu(heikkoja, cyan);
        heikkoja = onkoHeikkoHaamu(heikkoja, green);
        heikkoja = onkoHeikkoHaamu(heikkoja, magenta);

        return heikkoja;
    }

    /**
     * Tarkistetaan onko haamu heikko ja jos on kasvatetaan heikkojen määrää.
     *
     * @param heikkoja
     * @param haamu
     * @return
     */
    private int onkoHeikkoHaamu(int heikkoja, Haamu haamu) {
        if (haamu.getTyyppi().equals("heikko")) {
            heikkoja++;
        }
        return heikkoja;
    }
}
