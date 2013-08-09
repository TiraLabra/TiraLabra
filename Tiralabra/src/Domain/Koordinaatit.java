/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 * XY-koordinaatit paketoituna luokaksi.
 * @author Emleri
 */
public class Koordinaatit {

    private int x;
    private int y;

    /**
     * Konstruktori.
     * @param x
     * @param y
     */
    public Koordinaatit(int x, int y) {
        this.x = 0;
        this.y = 0;
        this.setX(x);
        this.setY(y);
    }

    /**
     * Getteri X-koordinaatille
     * @return X-koordinaatti
     */
    public int getX() {
        return x;
    }

    /**
     * Getteri Y-koordinaatille
     * @return Y-koordinaatti
     */
    public int getY() {
        return y;
    }

    /**
     * Setteri X-koordinaatille
     * @param x koordinaatin arvo
     */
    public void setX(int x) {
        if (x >= 0) {
            this.x = x;
        }
    }

    /**
     * Setteri Y-koordinaatille
     * @param y koordinaatin arvo
     */
    public void setY(int y) {
        if (y >= 0) {
            this.y = y;
        }
    }
}
