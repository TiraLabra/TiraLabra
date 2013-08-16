package Astar;

/**
 * Luokka toteuttaa tietorakenteen minimibinäärikeko. Keko voi sisältää vain
 * 'Ruutu'-luokan ilmentymiä. Keko toimii A*-algoritmin avoimenalistana ja se on
 * toteutettu taulukkorakenteena. Indeksointi tässä keossa alkaa 1:stä.
 * 
* @author Ilkka Maristo
 */
public class Keko {

    private Ruutu[] ruudut;
    private int ruutujenMaara;

    /**
     * Luo uuden keon ja varaa sille tilaa korkeus * leveys verran (labyrintin
     * maksimikoko)
     *
     * @param korkeus Labyrintin korkeus
     * @param leveys Labyrintin leveys
     */
    public Keko(int korkeus, int leveys) {

        // indeksointi alkaa 1:sta.
        this.ruudut = new Ruutu[(korkeus * leveys) + 1];
        this.ruutujenMaara = 0;

    }

    /**
     * Lisätään kekoon alkio eli yksi ruutu oikealle paikalleen.
     *
     * @param lisattava Lisättavä ruutu
     * @return Totuusarvo lisäyksen onnistumiesta
     */
    public boolean lisaaAlkio(Ruutu lisattava) {


        if (lisattava == null) {
            return false;
        }


        ++this.ruutujenMaara;

        // lisätään aluksi keon "perimmäiseksi"
        int sijainti = this.ruutujenMaara;

        // toistetaan kunnes ruutu on keon juuri tai
        // kunnes parametrina saadun ruudun F-arvo on suurempi kuin
        // vanhempansa F-arvo. (mitä pienempi F-arvo sitä suurempi prior.)
        while (sijainti > 1 && lisattava.vertaileFarvoja(this.ruudut[sijainti / 2]) < 0) {
            // Ehto '&&' jälkeen tosi, jos:
            // (lisättävän F - sen hetkisen vanhemman F) < 0 ,
            // eli ko. ruudun f-arvo on pienempi kuin vanhempansa
            // -> "vaihdetaan paikkoja"
            this.ruudut[sijainti] = this.ruudut[sijainti / 2];
            // asetetaan sijainniksi vanhemman sijainti
            sijainti = sijainti / 2;
        }
        // lisätään varsinainen olio oikealle paikalleen.
        this.ruudut[sijainti] = lisattava;

        return true;
    }

    /**
     * Poistaa pienimmän ruudun keosta.
     *
     * @return Poistettu ruutu
     */
    public Ruutu poistaPienin() {

        // Kyseessä min.binäärikeko, joten F-arvoltaan pienin ruutu on
        // keon ensimmaisenä.
        Ruutu pienin = this.ruudut[1];

        // uusin ruutu asetetaan juureksi, jota ruvetaan siirtämään alaspain keossa
        this.ruudut[1] = this.ruudut[this.ruutujenMaara];
        // poistetaan viimeisin ruutu tuohoamalla sen viite
        this.ruudut[this.ruutujenMaara] = null;
        --this.ruutujenMaara;

        int indeksi = 1;
        boolean jatketaanko = true;

        // toistetaan kunnes vertailtava on viimeisenä, tai
        // paikka on löydetty ja loppuosa on järjestyksessä
        while (2 * indeksi <= this.ruutujenMaara && jatketaanko) {

            // tarkasteltavaksi vasen lapsi
            int lapsi = 2 * indeksi;
            // vertaillaan vasemman ja oikean lapsen F-arvoja
            // ja valitaan oikea lapsi jos se on pienempi kuin vasenlapsi
            if (lapsi != this.ruutujenMaara && this.ruudut[lapsi].vertaileFarvoja(this.ruudut[lapsi + 1]) > 0) {
                ++lapsi;
            }

            // jos vertailtava on pienempää lasta suurempi niin vaihdetaan
            if (this.ruudut[indeksi].vertaileFarvoja(this.ruudut[lapsi]) > 0) {
                Ruutu temp = this.ruudut[indeksi];
                this.ruudut[indeksi] = this.ruudut[lapsi];
                this.ruudut[lapsi] = temp;
            } else {
                // loppu on oikeassa järjestyksessä
                jatketaanko = false;;
            }

            indeksi = lapsi;
        }

        return pienin;
    }

    /**
     * Onko keossa alkiota
     *
     * @return Totuusarvo siitä, löytyykö keosta alkioita vai ei
     */
    public boolean onTyhja() {
        if (this.ruutujenMaara == 0) {
            return true;
        } else {
            return false;
        }


    }

    /**
     * Testiohjelma, jolla voi testata Keon toimintaa.
     *
     */
    public static void main(String[] args) {

        Keko avoinLista = new Keko(2, 5);
        Ruutu temp = null;
        for (int i = 0; i < 10; i++) {
            int h = (int) Math.round(Math.random() * 99);
            Ruutu lisattava = new Ruutu(temp, 0, 0, 1, h);
            avoinLista.lisaaAlkio(lisattava);
            System.out.print(lisattava.getF() + " ");
            temp = lisattava;
        }
        System.out.println("\n");
        for (int i = 0; i < 10; i++) {
            temp = avoinLista.poistaPienin();
            System.out.println(temp.getF());
        }
    }
}
