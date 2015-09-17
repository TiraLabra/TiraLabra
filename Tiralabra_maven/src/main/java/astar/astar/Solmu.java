/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;
import java.util.List;

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
