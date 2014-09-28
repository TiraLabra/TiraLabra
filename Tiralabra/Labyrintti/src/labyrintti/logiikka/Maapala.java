/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logiikka;

import labyrintti.tietorakenteet.ListaAlkio;

/**
 * Tämä luokka edustaa maapaloja, jotka luovat labyrintin. Maapaloilla on omat
 * koordinaatit, jotka kertovat missä kohdassa labyrinttia ne sijaitsevat.
 * Maapaloilla on myös heuristiset arvot, jotka kertovat kuinka monen askelman
 * päässä ne ovat labyrintin "uloskäynnistä."
 *
 * @author Mikael Parvamo
 */
public class Maapala extends ListaAlkio {

    private int x;
    private int y;
    private int hArvo;
    private boolean seina;
    private boolean avoimellaListalla;
    private boolean suljetullaListalla;
    private Maapala vanhempi;

    public Maapala(int x, int y) {
        this.x = x;
        this.y = y;
        this.hArvo = 0;
        this.seina = false;
        this.avoimellaListalla = false;
        this.suljetullaListalla = false;
        this.vanhempi = null;
    }

    /**
     * @return this.hArvo, joka on maapalan heuristinen arvo
     */
    public int getHArvo() {
        return this.hArvo;
    }

    /**
     * @return this.x, joka on maapalan X-koordinaatti.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return this.y, joka on maapalan Y-koordinaatti.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Metodin tarkoituksena on asettaa maapalalle heuristinen arvo
     *
     * @param int hValue, joka on maapalalle asetettava uusi arvo.
     */
    public void setHValue(int hValue) {
        this.hArvo = hValue;
    }

    /**
     * Metodin tehtävänä on palauttaa boolean arvo (true/false) riippuen siitä,
     * onko maapala seinä eli läpäisemätön, vai ei.
     *
     * @return this.seina, joka on boolean arvo.
     */
    public boolean onkoSeina() {
        return this.seina;
    }

    /**
     * Metodi asettaa maapalan this.seina- arvoksi true. Eli maapalasta tulee
     * läpäisemätön.
     */
    public void asetaSeinaksi() {
        this.seina = true;
    }

    /**
     * Metodi asettaa maapalan takaisin normaaliin tilaan eli poistaa sen
     * läpäisemättömyyden.
     */
    public void asetaLapaistavaksi() {
        this.seina = false;
    }

    /**
     * Metodi "kertoo" maapalalle, että se on avoimella listalla.
     */
    public void siirraAvoimelleListalle() {
        this.avoimellaListalla = true;
    }

    /**
     * Metodi "kertoo" maapalalle, että se ei enää ole avoimen listan alkio.
     */
    public void poistaAvoimeltaListalta() {
        this.avoimellaListalla = false;
    }

    /**
     * Metodi "kertoo" maapalalle, että se on suljetulla listalla.
     */
    public void siirraSuljetulleListalle() {
        this.suljetullaListalla = true;
    }

    /**
     * Metodi "kertoo" maapalalle, että se ei enää kuulu suljettuun listaan.
     */
    public void poistaSuljetultaListalta() {
        this.suljetullaListalla = false;
    }

    /**
     * @return this.avoimellaListalla
     */
    public boolean onkoAvoimellaListalla() {
        return this.avoimellaListalla;
    }

    /**
     * @return this.suljetullaListalla
     */
    public boolean onkoSuljetullaListalla() {
        return this.suljetullaListalla;
    }

    /**
     * @param Maapala vanhempi, maapalalle asetettava vanhempi
     */
    public void setVanhempi(Maapala vanhempi) {
        this.vanhempi = vanhempi;
    }

    /**
     * @return this.vanhempi
     */
    public Maapala getVanhempi() {
        return this.vanhempi;
    }

    @Override
    public String toString() {
        if (this.onkoSeina()) {
            return "X";
        } else {
            return "" + this.hArvo;
        }
    }
}
