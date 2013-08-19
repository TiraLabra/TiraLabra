/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.verkko;

/**
 * Solmulla on oma paino ja numero.
 *
 * @author kkivikat
 */
public class Solmu {
    // HUOM: Paino siis tähän asti lyhyin reitti tähän solmuun

    private final int numero;
    private int paino;

    /**
     * Alustetaan solmulle numero ja paino.
    *
     */
    public Solmu(int numero) {
        paino = Integer.MAX_VALUE;
        this.numero = numero;
    }

    /**
     * Palauttaa solmun numeron.
    *
     */
    public int getSolmuNumero() {
        return numero;
    }

    /**
     * Palauttaa solmun painon.
    *
     */
    public int getPaino() {
        return paino;
    }

    /**
     * Asettaa solmulle uuden painon.
    *
     */
    public void setPaino(int paino) {
        this.paino = paino;
    }

    /**
     * Palauttaa tämän solmun.
    *
     */
    public Solmu getSolmu() {
        return this;
    }

    @Override
    /**
     * Merkkiesitys testausta varten.
    *
     */
    public String toString() { // tämä vaihettu getPainosta numeroks!
        return "" + numero;
    }
}
