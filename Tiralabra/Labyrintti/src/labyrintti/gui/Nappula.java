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
    private boolean avattu;
    
    public Nappula(int x, int y){
        this.x = x;
        this.y = y;
        this.avattu = false;
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
    
    /**
     * Labyrintin etsintä on loppunut ja nappula asetetaan pois päältä.
     */
    
    public void avaa(){
        this.avattu = true;
    }
    
    /**
     * @return this.avattu;
     */
    
    public boolean getAvattu(){
        return this.avattu;
    }
}