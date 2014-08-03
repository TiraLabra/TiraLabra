/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

/**
 *
 * @author mikko
 */
public class Koordinaatit {

    private final int x;
    private final int y;

    public Koordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public Koordinaatit suuntaan(Suunta suunta) {
        switch (suunta) {
            case YLOS:
                return new Koordinaatit(this.x, this.y-1);
            case ALAS:
                return new Koordinaatit(this.x, this.y+1);
            case OIKEA:
                return new Koordinaatit(this.x+1, this.y);
            case VASEN:
                return new Koordinaatit(this.x-1, this.y);
        }
        return null;
    }

//    public Koordinaatit ylos() {
//        return new Koordinaatit(this.x, this.y - 1);
//    }
//
//    public Koordinaatit alas() {
//        return new Koordinaatit(this.x, this.y + 1);
//    }
//
//    public Koordinaatit oikea() {
//        return new Koordinaatit(this.x + 1, this.y);
//    }
//
//    public Koordinaatit vasen() {
//        return new Koordinaatit(this.x - 1, this.y);
//    }

    public boolean equals(Koordinaatit koordinaatit) {
        return this.x == koordinaatit.x && this.y == koordinaatit.y;
    }
    
    public String toString() {
        return "("+this.x+", "+this.y+")";
    }
    
    
}
