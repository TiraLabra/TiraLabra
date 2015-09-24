/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

/**
 *
 * @author sasumaki
 */
public class Solmu {

    private final int x;
    private final int y;
    private final int matkaAlusta;
    private final Solmu edellinen;

    public Solmu(int x, int y, Solmu edellinen, int matkaAlusta) {
        this.x = x;
        this.y = y;
        this.edellinen = edellinen;
        this.matkaAlusta = matkaAlusta;

    }

    public int getX() {
        return x;
    }

    /** Jos solmun X ja Y koordinaatit ovat samat ne ovat sama solmu
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        final Solmu toinen = (Solmu) o;
        return this.x == toinen.getX() && this.y == toinen.getY();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    public int getY() {
        return y;
    }

    public int getMatkaAlusta() {
        return matkaAlusta;
    }

    public Solmu getEdellinen() {
        return edellinen;
    }

}
