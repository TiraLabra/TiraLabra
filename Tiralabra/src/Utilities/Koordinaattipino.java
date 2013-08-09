/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Domain.Koordinaatit;

/**
 *
 * @author Emleri
 */
public class Koordinaattipino {
    private int koko;
    private KoordinaattiNode pino;

    public Koordinaattipino() {
        this.koko = 0;
    }
    
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
    
    public  boolean isEmpty() {
        if (this.pino == null) {
            return true;
        } else {
            return false;
        }
    }
}
