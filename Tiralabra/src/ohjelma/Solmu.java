/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

/**
 *
 * @author kkivikat
 */
public class Solmu {

    int numero;
    int paino;
    int edellinen;

    public Solmu() {
    }

    public int getSolmuNumero() {
        return numero;
    }

    public int getPaino() {
        return paino;
    }

    public int getEdellinenSolmu() {
        return edellinen;
    }

    public void setPaino(int paino) {
        this.paino = paino;
    }

    public void setEdellinen(int edellinen) {
        this.edellinen = edellinen;
    }
}
