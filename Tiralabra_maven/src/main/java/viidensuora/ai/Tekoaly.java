package viidensuora.ai;

import viidensuora.peli.Koordinaatti;
import viidensuora.peli.Peli;

/**
 * Tietokonepelaajan käyttämä tekoäly. Tekoälyllä voi olla erilaisia
 * Etsintämetodeja.
 *
 * @author juha
 */
public class Tekoaly {

    /**
     * Pelitilanne/logiikka.
     */
    private final Peli peli;

    /**
     * Etsintämetodi, jolla paras siirto etsitään.
     */
    private Etsintametodi metodi;

    /**
     * Kuinka syvältä siirtoa maksimissaan etsitään pelipuusta.
     */
    private int syvyys;

    public Tekoaly(Peli peli, Etsintametodi metodi, int syvyys) {
        this.peli = peli;
        this.metodi = metodi;
        this.metodi.setPeli(peli);
        this.syvyys = syvyys;
    }

    public void setMetodi(Etsintametodi metodi) {
        this.metodi = metodi;
        this.metodi.setPeli(peli);
    }

    /**
     * Etsii parhaan siirron käyttäen Etsintämetodia. ToDo
     *
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasRistinSiirto() {
        return metodi.etsiParasRistinSiirto();
    }

    /**
     * Etsii parhaan siirron käyttäen Etsintämetodia. ToDo
     *
     * @return
     */
    public Koordinaatti etsiParasNollanSiirto() {
        return metodi.etsiParasRistinSiirto();
    }

    /**
     * Asettaa syvyyden josta siirtoa etsitään pelipuusta.
     *
     * @param syvyys Uusi syvyys. 
     */
    public void setSyvyys(int syvyys) {
        if (syvyys > 0) {
            this.syvyys = syvyys;
        }
    }

}
