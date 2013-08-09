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
public class KoordinaattiNode {
    private Koordinaatit koordinaatit;
    private KoordinaattiNode seuraava;

    public KoordinaattiNode(Koordinaatit k) {
        this.koordinaatit = k;
        this.seuraava = null;
    }

    public Koordinaatit getKoordinaatit() {
        return koordinaatit;
    }

    public KoordinaattiNode getSeuraava() {
        return seuraava;
    }

    public void setKoordinaatit(Koordinaatit koordinaatit) {
        this.koordinaatit = koordinaatit;
    }

    public void setSeuraava(KoordinaattiNode seuraava) {
        this.seuraava = seuraava;
    }
    
    public boolean isViimeinen() {
        if(seuraava == null) {
            return true;
        } else {
            return false;
        }
    }
}
