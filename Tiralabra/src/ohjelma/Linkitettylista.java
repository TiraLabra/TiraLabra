/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

/**
 *
 * @author kkivikat
 */
public class Linkitettylista {
    
    private PinoSolmu paalimmainen;

    public Linkitettylista() {
        paalimmainen = null;
    }

    public void push(int avain) {
        PinoSolmu x = new PinoSolmu();
        x.avain = avain;
        x.seuraava = paalimmainen;
        paalimmainen = x;
    }

    public int pop() {
        PinoSolmu x = paalimmainen;
        paalimmainen = x.seuraava;
        return x.getAvain();
    }

    public boolean onkoTyhja() {
        return (paalimmainen == null);
    }
}
