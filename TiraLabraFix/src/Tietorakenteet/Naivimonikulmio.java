/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;

/**
 *
 * @author Serafim
 */
public class Naivimonikulmio {
      private Jono kulmat;
    private double maxX;
    private double minX;
    private double maxY;
    private double minY;
    private Kordinaatti[][] vektori;

    /**
     *
     * Luo uuden monikulmion parametreinä kordinaattien lista.
     *
     * @param kordinaatit
     */
    public Naivimonikulmio(Jono kordinaatit) {
        this.kulmat = kordinaatit;
        if ((this.kulmat != null) && (this.kulmat.palautaKoko() > 0)) {
            LaskeVirittavaNelio();
        }
        laskeJanat();
    }

    /**
     *
     * Palauttaakulmat
     *
     * @return ArrayList<Kordinaatti> kordinaatit
     */
    public Jono palautaKulmat() {
        return this.kulmat;
    }

    /**
     *
     * Palauttaaa virittavannelion
     */
    public Kordinaatti[] palautaVirittavaNelio() {
        Kordinaatti[] k = new Kordinaatti[4];
        k[0] = new Kordinaatti(this.minX, this.maxY);
        k[1] = new Kordinaatti(this.maxX, this.maxX);
        k[2] = new Kordinaatti(this.minX, this.minY);
        k[3] = new Kordinaatti(this.minX, this.maxX);
        return k;

    }

    /**
     *
     * Laskee virittavannelion
     */
    public void LaskeVirittavaNelio() {
        Kordinaatti k = (Kordinaatti) this.kulmat.palautaEnsimmainen().palautaObjekti();
        this.maxX = k.palautaX();
        this.minY = k.palautaY();
        this.maxY = k.palautaY();
        this.minX = k.palautaX();
        Jonoiteroitava iteroitava = this.kulmat.palautaEnsimmainen();
        while (iteroitava != null) {
            k = (Kordinaatti) iteroitava.palautaObjekti();
            double x = k.palautaX();
            double y = k.palautaY();
            if (x > maxX) {
                maxX = x;
            }
            if (x < minX) {
                minX = x;
            }
            if (y > maxY) {
                maxY = y;
            }
            if (y < minY) {
                minY = y;
            }
            iteroitava = iteroitava.palauataSeuraava();
        }

    }

    /**
     *
     * Laskee janat
     */
 

    public void laskeJanat() {
        this.vektori = new Kordinaatti[this.kulmat.palautaKoko()][2];
        int i = 0;
        Jonoiteroitava iter = this.kulmat.palautaEnsimmainen();
        while (iter != this.kulmat.palautaViimeinen()) {
            Kordinaatti k = (Kordinaatti) iter.palautaObjekti();
            Kordinaatti k2 = (Kordinaatti) iter.palauataSeuraava().palautaObjekti();
            vektori[i][0] = k;
            vektori[i][1] = k2;

            i++;
        }
        vektori[i][0] = (Kordinaatti) this.kulmat.palautaViimeinen().palautaObjekti();
        vektori[i][1] = (Kordinaatti) this.kulmat.palautaEnsimmainen().palautaObjekti();

    }

    public Kordinaatti[][] PalautaJanat() {
        return this.vektori;
    }

}

