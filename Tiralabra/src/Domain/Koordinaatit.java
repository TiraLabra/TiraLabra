/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Emleri
 */
public class Koordinaatit {

    private int x;
    private int y;

    public Koordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (x >= 0) {
            this.x = x;
        }
    }

    public void setY(int y) {
        if (y >= 0) {
            this.y = y;
        }
    }
}
