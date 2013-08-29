/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.eiKaytossaOlevatTietorakenteet;

/**
 *
 * @author kkivikat
 */
public class PinoSolmu {

    int avain;
    PinoSolmu seuraava;

    public PinoSolmu() {
    }

    public PinoSolmu(PinoSolmu seuraava) {
        this.seuraava = seuraava;
    }

    public int getAvain() {
        return this.avain;
    }
}
