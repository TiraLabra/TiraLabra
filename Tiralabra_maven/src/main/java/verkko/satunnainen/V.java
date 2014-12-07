/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.satunnainen;

import verkko.rajapinnat.Value;

/**
 * Verkon solmu
 *
 * @author E
 */
public class V implements Value {

    /**
     * Solmun koordinaatit
     */
    private int x, y;
    /**
     * Etäisyyden laskemisessa käytettävä
     */
    private boolean liikkumisSaanto;

    public int getX() {
        return x;
    }

    /**
     * 
     * 
     * @param x Solmun koordinaatit
     * @param y Solmun koordinaatit
     * @param saanto Liikkumissääntö: saako liikkua vain koordinaattiakselien suuntaan
     */
    public V(int x, int y, boolean saanto ) {
        this.x = x;
        this.y = y;
        liikkumisSaanto=saanto;
    }
    
    public int getY() {
        return y;
    }

    public double etaisyys(Value s) {
        V v = (V) s;
        if (liikkumisSaanto) return Math.abs(v.getX() - this.getX()) + Math.abs(v.getY() - this.getY());
        return Math.max(Math.abs(v.getX() - this.getX()) , Math.abs(v.getY() - this.getY()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final V other = (V) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "V{" + "x=" + x + ", y=" + y + '}';
    }

}
