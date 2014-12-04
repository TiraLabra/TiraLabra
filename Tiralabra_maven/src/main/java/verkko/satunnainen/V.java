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

    private int x, y;

    public int getX() {
        return x;
    }

    public V(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public double etaisyys(Value s) {
        V v = (V) s;
        return Math.abs(v.getX() - this.getX()) + Math.abs(v.getY() - this.getY());
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
