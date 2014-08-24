/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.ArrayList;

/**
 *
 * Monikulmio luokka
 */
public class Monikulmio {

    private ArrayList<Kordinaatti> kulmat;
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
    public Monikulmio(ArrayList<Kordinaatti> kordinaatit) {
        this.kulmat = kordinaatit;
        if ((this.kulmat != null) && (this.kulmat.size() > 0)) {
            LaskeVirittavaNelio();
        }
    }

    /**
     *
     * Palauttaakulmat
     *
     * @return ArrayList<Kordinaatti> kordinaatit
     */
    public ArrayList<Kordinaatti> palautaKulmat() {
        return this.kulmat;
    }
     /**
 *
 * Palauttaaa virittavannelion
 */

    public Kordinaatti[] palautaVirittavaNelio()
    {
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
        this.maxX = this.kulmat.get(0).palautaX();
        this.minY = this.kulmat.get(0).palautaY();
        this.maxY = this.kulmat.get(0).palautaY();
        this.minX = this.kulmat.get(0).palautaX();
        for (Kordinaatti k : this.kulmat) {
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

        }

    }
    
    /**
 *
 * Laskee janat
 */
    public void LaskeJanat()
    {
    this.vektori = new Kordinaatti[this.kulmat.size()][2];
    for (int i = 0; i < this.kulmat.size() - 1; i++)
    {
    vektori[i][0] = this.kulmat.get(i);
    vektori[i][1] = this.kulmat.get(i+1);
    }
    vektori[this.kulmat.size()-1][0] = this.kulmat.get(this.kulmat.size() - 1);
    vektori[this.kulmat.size()-1][1] = this.kulmat.get(0);
    
    }
    public Kordinaatti[][] PalautaJanat()
    {
    return this.vektori;
    }

}
