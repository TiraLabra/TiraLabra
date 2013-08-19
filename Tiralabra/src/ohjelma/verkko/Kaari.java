/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.verkko;

/**
 * Kaari koostuu kahdesta solmusta ja niiden etäisyydestä
 *
 * @author kkivikat
 */
public class Kaari {
    // HUOM: Etäisyys siis kahden solmun välinen etäisyys

    private final Solmu alku;
    private final Solmu kohde;
    private int etaisyys;

    /**
     * Alustetaan kaarelle solmut ja niiden välinen etäisyys.
    *
     */
    public Kaari(Solmu alku, Solmu kohde, int etaisyys) {
        this.alku = alku;
        this.kohde = kohde;
        this.etaisyys = etaisyys;
    }

    /**
     * Palauttaa alkusolmun.
    *
     */
    public Solmu getAlku() {
        return alku;
    }

    /**
     * Palauttaa kohdesolmun.
    *
     */
    public Solmu getKohde() {
        return kohde;
    }

    /**
     * Palauttaa kaaren etäisyyden, eli pituuden alkusolmusta kohdesolmuun).
    *
     */
    public int getEtaisyys() {
        return etaisyys;
    }

    /**
     * Merkkiesitys testausta varten.
    *
     */
    @Override
    public String toString() {
        return "" + kohde.getSolmuNumero();
    }
}
