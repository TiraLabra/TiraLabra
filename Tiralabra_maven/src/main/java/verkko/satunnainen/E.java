/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.satunnainen;

import verkko.rajapinnat.Edge;

/**
 * Verkon kaari
 *
 * @author E
 */
public class E implements Edge {

    private double kustannus;

    public E(double kustannus) {
        this.kustannus = kustannus;
    }

    public void setKustannus(double kustannus) {
        this.kustannus = kustannus;
    }

    public double getKustannus() {
        return kustannus;
    }

    @Override
    public String toString() {
        return "E{" + "kustannus=" + kustannus + '}';
    }

}
