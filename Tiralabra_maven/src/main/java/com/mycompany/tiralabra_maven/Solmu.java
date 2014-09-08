package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 *
 * @author iivo
 */
public class Solmu {
    private int x; //X- ja Y -koordinaatit eräisyysarviota ja tulostusta varten
    private int y;
    private int paino;
    private int alkuun; //Astarin tiedossa oleva lyhin etäisyys alusta tähän solmuun
    private int loppuun; //Astarin etäisyysarvio tästä solmusta loppuun
    private Solmu polku; //solmu, josta lyhin polku saapui
    private ArrayList<Solmu> vierus; //vierussolmut

    public Solmu(int x, int y, int paino) {
        this.x = x;
        this.y = y;
        this.paino = paino;
        this.vierus = new ArrayList<Solmu>();
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

    public int getPaino() {
        return paino;
    }

    public void setPaino(int paino) {
        this.paino = paino;
    }

    public int getAlkuun() {
        return alkuun;
    }

    public void setAlkuun(int alkuun) {
        this.alkuun = alkuun;
    }

    public int getLoppuun() {
        return loppuun;
    }

    public void setLoppuun(int loppuun) {
        this.loppuun = loppuun;
    }

    public Solmu getPolku() {
        return polku;
    }

    public void setPolku(Solmu polku) {
        this.polku = polku;
    }

    public ArrayList getVierus() {
        return vierus;
    }

    public void lisaaVierus(Solmu solmu) {
        this.vierus.add(solmu);
    }
}
