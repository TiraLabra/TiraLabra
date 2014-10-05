/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logiikka;

import labyrintti.tietorakenteet.LinkitettyLista;

/**
 * Tämä luokka edustaa itse labyrintin lyhimmän reitin etsimiseen käytettävää
 * mekanismia. Luokalla on maapalarekisteri käytössään sekä kaksi listaa,
 * avoiLista ja suljettuLista. Käsittelemättömät ja reitin välittömässä
 * läheisyydessä olevat nodet (maapalat) ovat avoinLista:ssa. Kun maapala on
 * käsitelty, siirtyy se suljettuLista:aan, josta tiedetään, että sitä ei
 * tarvitse käsitellä uudestaan.
 *
 * @author Mikael Parvamo
 */
public class LyhinReitti {

    private Maapalarekisteri maapalaRekisteri;
    private int alkuX;
    private int alkuY;
    private int loppuX;
    private int loppuY;
    private int avoimenListanKoko;
    private boolean loppuLoytynyt;
    private Maapala[][] labyrintti;
    private LinkitettyLista avoinLista1;
    private LinkitettyLista suljettuLista1;

    public LyhinReitti(Maapalarekisteri maapalaRekisteri) {
        this.loppuLoytynyt = false;
        this.maapalaRekisteri = maapalaRekisteri;
        this.alkuX = maapalaRekisteri.getAlkuX();
        this.alkuY = maapalaRekisteri.getAlkuY();
        this.loppuX = maapalaRekisteri.getLoppuX();
        this.loppuY = maapalaRekisteri.getLoppuY();
        this.labyrintti = maapalaRekisteri.getLabyrintti();
        this.avoinLista1 = new LinkitettyLista();
        this.suljettuLista1 = new LinkitettyLista();
        this.avoinLista1.lisaaListaan(this.labyrintti[alkuX][alkuY]);
        this.labyrintti[alkuX][alkuY].siirraAvoimelleListalle();
        this.avoimenListanKoko = 1;
    }

    /**
     * Metodi tarkastaa syötteenä saadun maapalan naapurit ja lisää ne
     * avoinLista:aan, sekä kasvattaa listan kokoa yhdellä.
     *
     * @param Maapala maapala, eli tarkasteltava maapala.
     */
    public void siirraNaapuritAvoimelleListalle(Maapala pieninMaapala) {
        Maapala maapala = pieninMaapala;

        int x = maapala.getX();
        int y = maapala.getY();

        if (x > 0) {         
            this.siirraNaapuriAvoimelleListalle(x - 1, y, maapala);
        }

        if (x < this.maapalaRekisteri.getKoko() - 1) {
            this.siirraNaapuriAvoimelleListalle(x + 1, y, maapala);
        }
        if (y > 0) {
            this.siirraNaapuriAvoimelleListalle(x, y - 1, maapala);
        }

        if (y < this.maapalaRekisteri.getKoko() - 1) {
            this.siirraNaapuriAvoimelleListalle(x, y + 1, maapala);
        }
    }

    private void siirraNaapuriAvoimelleListalle(int x, int y, Maapala maapala) {
        if (!this.labyrintti[x][y].onkoAvoimellaListalla() && !this.labyrintti[x][y].onkoSuljetullaListalla() && !this.labyrintti[x][y].onkoSeina()) {
            avoinLista1.lisaaListaan(this.labyrintti[x][y]);
            this.labyrintti[x][y].siirraAvoimelleListalle();
            this.labyrintti[x][y].setVanhempi(maapala);
            this.labyrintti[x][y].setKokonaisArvo();
            this.avoimenListanKoko++;
        }
    }

    /**
     * Metodi siirtaa parametrina saadun maapalan avoimelta listalta
     * suljettuLista:lle, sekä asettaa oikeat arvot maapalan muuttujille ja
     * pienentää avoinLista:n kokoa.
     */
    public void siirraMaapalatSuljetulleListalle(Maapala poistettava) {
        Maapala maapala = poistettava;

        avoinLista1.poistaListasta(maapala);
        suljettuLista1.lisaaListaan(maapala);
        this.labyrintti[maapala.getX()][maapala.getY()].siirraSuljetulleListalle();
        this.labyrintti[maapala.getX()][maapala.getY()].poistaAvoimeltaListalta();
        this.avoimenListanKoko--;
    }

    /**
     * Metodi käy läpi avoinLista:n ja etsii sieltä alkion, jolla on pienin
     * kokonaisarvo eli (liikkumiskustannus + heuristinenarvo + vanhemman kokonaisarvo). 
     * Kun lista on käyty loppuun, palauttaa metodi
     * kyseisen maapalan. Jos jollain listan maapaloista on heuristinen arvo 0,
     * asettaa metodi luokan muuttujalle loppuLoytynyt arvon true.
     */
    public Maapala etsiMaapalaJollaPieninKokonaisArvo() {
        Maapala pieninMaapala = (Maapala) avoinLista1.getPaa();
        Maapala maapala = (Maapala) avoinLista1.getPaa();
        int pieninKokArvo = maapala.getKokonaisArvo();

        while (maapala != null && avoimenListanKoko > 0) {
            if (maapala.getKokonaisArvo() < pieninKokArvo) {
                pieninKokArvo = maapala.getKokonaisArvo();
                pieninMaapala = maapala;
            }
            maapala = (Maapala) maapala.getSeuraava();
        }

        if (pieninMaapala.getHArvo() == 0) {
            this.loppuLoytynyt = true;
        }

        return pieninMaapala;
    }

    /**
     * @return this.loppuLoytynyt
     */
    public boolean onkoLoppuLoytynyt() {
        return this.loppuLoytynyt;
    }

    /**
     * @return this.avoimenListanKoko
     */
    public int getAvoimenListanKoko() {
        return this.avoimenListanKoko;
    }

    /**
     * Metodi suorittaa kierroksen labyrintin lyhimmän reitin etsinnässä. Eli
     * metodi kutsuu metodeja etsiMaapalaJollaPieninKokonaisArvo(),
     * siirraNaapuritAvoimelleListalle(maapala) ja
     * siirraMaapalaSuljetulleListalle(maapala).
     */
    public void kierros() {
        Maapala pieninMaapala = etsiMaapalaJollaPieninKokonaisArvo();
        siirraNaapuritAvoimelleListalle(pieninMaapala);
        siirraMaapalatSuljetulleListalle(pieninMaapala);

    }

    /**
     * Jos reitti on löytynyt, metodi tulostaa sen. Muuten metodi ilmoittaa,
     * että reittiä ei ole.
     */
    public void tulostaLyhinReitti() {
        if (avoimenListanKoko == 0 && !this.loppuLoytynyt) {
            System.out.println("Labyrintista ei ole reittiä ulos");
        } else {
            Maapala maapala = maapalaRekisteri.getLoppu();
            while (true) {
                System.out.println("" + maapala.getX() + "," + maapala.getY());
                maapala = maapala.getVanhempi();
                if (maapala == null) {
                    break;
                }
            }
        }
    }

    /**
     * Metodi kutsuu kierroksia, kunnes lyhin reitti on löytynyt tai avoimella
     * listalla ei ole uusia käsiteltäviä alkioita jäljellä.
     */
    public void etsiLyhinReitti() {
        while (avoimenListanKoko > 0 && !this.loppuLoytynyt) {
            kierros();
        }
    }
}
