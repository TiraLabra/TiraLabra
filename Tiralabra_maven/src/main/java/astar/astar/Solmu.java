/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;
import java.util.List;

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
    private int matkaAlusta;
    int heuristinenMatka;

    public Solmu(int x, int y, boolean onEste, boolean onMaali, boolean onAlku) {
        this.x = x;
        this.y = y;
        this.vieruslista = new ArrayList<Solmu>();
        this.onkoEste = onEste;
        this.onkoMaali = onMaali;
        this.onkoAlku = onAlku;
    }
    public Solmu(int x, int y){
        this.x = x;
        this.y = y;
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

    public boolean getAlkeus() {
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

    public void setMatkaAlusta(int matka) {
        this.matkaAlusta = matka;
    }

    public int getMatkaAlusta() {
        return matkaAlusta;
    }

//    public void setHeuristinenMatka(int heuristinen) {
//        this.heuristinenMatka = heuristinen;
//    }
//
//    public int getHeuristinenMatka() {
//        return heuristinenMatka;
//    }
    
    
/**
 * hakee solmun naapurit ja lisää ne listan ja palauttaa listan.
 * @param x
 * @param y
 * @return 
 */
    public List<Solmu> solmunNaapurit(int x, int y) {
        List<Solmu> naapurit = new ArrayList<Solmu>();

        naapurit.add(new Solmu(x + 1, y, false, false, false));
        naapurit.add(new Solmu(x + 1, y + 1, false, false, false));
        naapurit.add(new Solmu(x, y + 1, false, false, false));
        naapurit.add(new Solmu(x - 1, y + 1, false, false, false));
        naapurit.add(new Solmu(x - 1, y, false, false, false));
        naapurit.add(new Solmu(x - 1, y - 1, false, false, false));
        naapurit.add(new Solmu(x, y - 1, false, false, false));
        naapurit.add(new Solmu(x - 1, y - 1, false, false, false));

        return naapurit;
    }

}
