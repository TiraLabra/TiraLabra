package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Solmu-luokka sisältää verkon yksittäisen solmun tiedot
 */
public class Solmu {
    private int x; //X- ja Y -koordinaatit eräisyysarviota ja tulostusta varten
    private int y;
    private int paino;
    private int alkuun; //Astarin tiedossa oleva lyhin etäisyys alusta tähän solmuun
    private int loppuun; //Astarin etäisyysarvio tästä solmusta loppuun
    private Solmu polku; //solmu, josta lyhin polku saapui
    private ArrayList<Solmu> vierus; //vierussolmut

    /**
     * @param    x       solmun vaakakoordinaatti 2D-esityksessä
     * @param    y       solmun pystykoordinaatti 2D-esityksessä
     * @param    paino   solmun vaikeakulkuisuus (0 = helpoin, 9 = vaikein, -1 = läpäisemätön)
     */
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

    /**
     * Lisää solmulle vieruslistaan solmun. Jos lisättävän solmun paino on -1,
     * sitä ei lisätä.
     * 
     * @param    solmu  tälle solmulle vierukseksi lisättävä solmu
    */
    public void lisaaVierus(Solmu solmu) {
        if(solmu.getPaino() == -1) { //Vierussolmu on seinää, ei siis ole kaarta
            return;
        }
        this.vierus.add(solmu);
    }
    
    /**
     * Etsii parametrina annetun solmun vieruslistalta ja palauttaa kaaripainon
     * tältä solmulta parametrin solmulle
     * 
     * @param    solmu  kohdesolmu, jonka kaaripaino halutaan tietää
     * @return kaaripaino tältä solmulta parametrin solmulle
    */
    public int getKaaripaino(Solmu solmu) {
        for(Solmu vierussolmu : vierus) {
            if(vierussolmu == solmu) {
                return vierussolmu.getPaino();
            }
        }
        
        //jos ei kaarta (ei tarvitse tehdä kaarta # välille)
        return 1000000;
    }
}
