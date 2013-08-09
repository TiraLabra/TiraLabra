/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Domain.Koordinaatit;

/**
 * Linkitetyn listan noodi, joka tallentaa itseensä yhdet koordinaatit.
 * @author Emleri
 */
public class KoordinaattiNode {
    private Koordinaatit koordinaatit;
    private KoordinaattiNode seuraava;

    /**
     * Konstruktori, saa parametrina tallennettavat koordinaatit.
     * @param k koordinaatit
     */
    public KoordinaattiNode(Koordinaatit k) {
        this.koordinaatit = k;
        this.seuraava = null;
    }

    /**
     * Getteri koordinaateille.
     * @return koordinaatit
     */
    public Koordinaatit getKoordinaatit() {
        return koordinaatit;
    }

    /**
     * Getteri listassa seuraavalle nodelle.
     * @return
     */
    public KoordinaattiNode getSeuraava() {
        return seuraava;
    }

    /**
     * Setteri koordinaateille.
     * @param koordinaatit
     */
    public void setKoordinaatit(Koordinaatit koordinaatit) {
        this.koordinaatit = koordinaatit;
    }

    /**
     * Setteri listan seuraavalle nodelle.
     * @param seuraava
     */
    public void setSeuraava(KoordinaattiNode seuraava) {
        this.seuraava = seuraava;
    }
    
    /**
     * Tarkistaa, onko node listan viimeinen.
     * @return boolean on/ei ole
     */
    public boolean isViimeinen() {
        if(seuraava == null) {
            return true;
        } else {
            return false;
        }
    }
}
