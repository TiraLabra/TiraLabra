/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

/**
 *
 * @author essalmen
 */
public class Die {
    private boolean locked;
    private int number;
    
    /**
     * Toggle nopan valinnalle.
     * 
     * Lukitsee lukitsemattoman nopan tai poistaa lukon lukitusta nopasta.
     * @return tila, johon päädyttiin
     */
    
    public boolean lock()
    {
        if (locked) {
            locked = false;
        } else {
            locked = true;
        }
        return locked;
    }
    
    
    public boolean isLocked()
    {
        return locked;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    /**
     * Poistaa lukon nopasta.
     * 
     * Käytetään uuden heittovuoron alkaessa.
     */
    
    public void unlock()
    {
        locked = false;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
    
}
