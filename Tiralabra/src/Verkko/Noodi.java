/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Verkko;

/**
 *
 * @author eniirane
 */
public class Noodi {
    int x;
    int y;
    
    int g;
    int h;
    int f = g + h;
    
    //vakiopaino verkkosiirtymille
    int w = 1;

    Noodi[] naapurit;
        
    Noodi vanhempi;
    
    public Noodi(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.g = -1;
        this.h = -1;
        
        this.vanhempi = null;
    }
    
    public void setG(int n) {
        this.g = n;
    }
    
    public void setH(int n) {
        this.h = n;
    }

    public void setVanhempi(Noodi n) {
        this.vanhempi = n;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getG() {
        return this.g;
    }
    
    public int getH() {
        return this.h;
    }
    
    public int getF() {
        return this.f;
    }
    
    public int getW() {
        return this.w;
    }
    
    public Noodi getVanhempi() {
        return this.vanhempi;
    }
    
    public Noodi[] getNaapurit() {
        return this.naapurit;
    }
}
