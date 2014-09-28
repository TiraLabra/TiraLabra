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

    private boolean miettii;

    public Tekoaly(Peli peli, Etsintametodi metodi, int syvyys) {
        this.peli = peli;
        this.metodi = metodi;
        this.metodi.setPeli(peli);
        this.syvyys = syvyys;
        this.miettii = false;
    }

    public void setMetodi(Etsintametodi metodi) {
        this.metodi = metodi;
        this.metodi.setPeli(peli);
    }

    public boolean miettii() {
        return miettii;
    }

    /**
     * Etsii parhaan siirron käyttäen Etsintämetodia. ToDo
     *
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasRistinSiirto() {
        miettii = true;
        Koordinaatti k = metodi.etsiParasRistinSiirto(syvyys);
        miettii = false;
        return k;
    }

    /**
     * Etsii parhaan siirron käyttäen Etsintämetodia. ToDo
     *
     * @return
     */
    public Koordinaatti etsiParasNollanSiirto() {
        miettii = true;
        Koordinaatti k = metodi.etsiParasNollanSiirto(syvyys);
        miettii = false;
        return k;
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
