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
    // HUOM: Paino siis tähän asti lyhyin reitti tähän solmuun
    private final int numero;
    private int paino;


    public Solmu(int numero) {
        paino = Integer.MAX_VALUE;
        this.numero = numero;
    }

    public int getSolmuNumero() {
        return numero;
    }

    public int getPaino() {
        return paino;
    }


    public void setPaino(int paino) {
        this.paino = paino;
    }
    
    @Override
    public String toString() {
        return ""+getPaino();
    }

}
