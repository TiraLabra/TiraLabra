/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;

/**
 *
 * @author sasumaki
 */
public class Solmu {

    int x;
    int y;
    boolean onkoEste;
    boolean onkoMaali;
    boolean onkoAlku;
    private ArrayList<Solmu> vieruslista;

    public Solmu(int x, int y, boolean onEste, boolean onMaali, boolean onAlku) {
        this.x = x;
        this.y = y;
        this.vieruslista = new ArrayList<Solmu>();
        this.onkoEste = onEste;
        this.onkoMaali = onMaali;
        this.onkoAlku = onAlku;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getEsteys() {
        return onkoEste;
    }

    public void setEste(boolean esteys) {
        this.onkoEste = esteys;
    }

    public boolean getAlku() {
        return onkoAlku;
    }

    public void setAlku(boolean alkeus) {
        this.onkoAlku = alkeus;
    }

    public boolean getMaaleus() {
        return onkoMaali;
    }

    public void setMaali(boolean maaleus) {
        this.onkoMaali = maaleus;
    }
}
