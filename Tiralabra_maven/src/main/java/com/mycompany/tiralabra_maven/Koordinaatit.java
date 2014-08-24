/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

/**
 * Yksinkertainen immutaabeli luokka, jota käytetään kaksiulotteisten
 * koordinaattien tallettamiseen.
 *
 * @author mikko
 */
public class Koordinaatit {

    private final int x;
    private final int y;
    
    /**
     * Luo uuden koordinaatit-olion annetuilla koordinaateilla
     * @param x
     * @param y 
     */

    public Koordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Palauttaa x-koordinaatin
     * @return x-koordinaatti
     */

    public int getX() {
        return this.x;
    }
    
    /**
     * Palauttaa y-koordinaatin
     * @return y-koordinaatti
     */

    public int getY() {
        return this.y;
    }

    /**
     * Palauttaa uuden Koordinaatit-olion, joka sijaitsee tämän olion
     * parametrina annetulla suunnalla olevalla puolella.
     *
     * @param suunta
     * @return uudet koordinaatit
     */
    public Koordinaatit suuntaan(Suunta suunta) {
        switch (suunta) {
            case YLOS:
                return new Koordinaatit(this.x, this.y - 1);
            case ALAS:
                return new Koordinaatit(this.x, this.y + 1);
            case OIKEA:
                return new Koordinaatit(this.x + 1, this.y);
            case VASEN:
                return new Koordinaatit(this.x - 1, this.y);
            case YLAOIKEA:
                return new Koordinaatit(this.x+1, this.y-1);
            case YLAVASEN:
                return new Koordinaatit(this.x-1, this.y-1);
            case ALAOIKEA:
                return new Koordinaatit(this.x+1, this.y+1);
            case ALAVASEN:
                return new Koordinaatit(this.x-1, this.y+1);
        }
        return null;
    }

    /**
     * Metodi palauttaa true jos koordinaatit osoittavat samaan pisteeseen ja
     * false, jos eivät osoita.
     *
     * @param obj
     * @return ovatko koordinaatit samat
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Koordinaatit other = (Koordinaatit) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    /**
     * Palauttaa hashcoden
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.x;
        hash = 41 * hash + this.y;
        return hash;
    }



    /**
     * Palauttaa merkkijonomuotoisen esityksen koordinaateista, muotoa (x, y).
     * @return merkkijonoesitys
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
