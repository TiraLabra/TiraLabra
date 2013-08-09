/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Domain.Koordinaatit;

/**
 * Pino-tietorakenne koordinaattien säilyttämiseen. Kapseloi sisäänsä  
 * yksisuuntaisen linkitetyn listan KoordinaattiNode-olioita.
 * @author Emleri
 */
public class Koordinaattipino {
    private int koko;
    private KoordinaattiNode pino;

    /**
     * Konstruktori, alustaa tyhjän pinon.
     */
    public Koordinaattipino() {
        this.koko = 0;
    }
    
    /**
     * Lisää koordinaatit pinoon ja kasvattaa sen kokoa.
     * @param k lisättävät koordinaatit
     */
    public void push(Koordinaatit k) {
        if(this.isEmpty()) {
            this.pino = new KoordinaattiNode(k);
        } else {
            KoordinaattiNode vanha = this.pino;
            this.pino = new KoordinaattiNode(k);
            this.pino.setSeuraava(vanha);
        }
        this.koko++;
    }
    
    /**
     * Poistaa päällimmäiset koordinaatit pinosta, pienentää pinon kokoa ja 
     * palauttaa poistetut koordinaatit.
     * @return päällimmäiset koordinaatit
     */
    public Koordinaatit pop() {
        if(this.isEmpty()) {
            return null;
        } else {
            Koordinaatit k = this.pino.getKoordinaatit();
            this.pino = this.pino.getSeuraava();
            this.koko--;
            return k;
        }
    }
    
    /**
     * Tarkistaa, onko pino tyhjä.
     * @return boolean on/ei ole tyhjä
     */
    public  boolean isEmpty() {
        if (this.pino == null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Getteri pinon koolle.
     * @return koko
     */
    public int getKoko() {
        return this.koko;
    }
}
