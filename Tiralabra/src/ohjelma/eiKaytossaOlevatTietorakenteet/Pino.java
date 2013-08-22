/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.eiKaytossaOlevatTietorakenteet;

import java.util.EmptyStackException;

/**
 *
 * @author kkivikat
 */
public class Pino {

    private int paalimmainen;
    private int[] pino;

    public Pino(int koko) {
        this.pino = new int[koko];
        this.paalimmainen = -1;

    }

    public void push(int seuraava) {
        pino[++paalimmainen] = seuraava;
    }

    public int pop() {
        if (onkoTyhja() == false) {
            return pino[paalimmainen--];
        } else {
            return -1;
        }
    }

    public int koko() {
        return pino.length;
    }

    public boolean onkoTyhja() {
        if (paalimmainen == 0) {
            return true;
        } else {
            return false;
        }
    }
}