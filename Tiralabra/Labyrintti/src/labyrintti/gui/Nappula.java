/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.gui;

import javax.swing.JButton;

/**
 * Tämä luokka luo labyrintin graafiset nappulat ja asettaa niille koordinaatit.
 * Luokka perii JButtonin.
 * 
 * @author Mikael Parvamo
 */

public class Nappula extends JButton{
    private int x;
    private int y;
    
    public Nappula(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
    *@return this.x 
    */
    
    public int getXKoordinaatti(){
        return this.x;
    }
    
    /**
    *@return this.y
    */
    public int getYKoordinaatti(){
        return this.y;
    }
}