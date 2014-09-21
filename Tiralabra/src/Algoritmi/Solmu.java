/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

/**
 * @author Jaakko
 * 
 * Luokka solmu kuvaa solmua.
 */
public class Solmu {
    
    public int Heurestiikaarvo;

    public Solmu Edeltävä;
    
    public int koordinaattiX;
    public int koordinaattiY;
    
    public boolean seinä;
    
/**
 *Konstruktori
 * 
 * @param x koordinaatti X
 * @param y koordinaatti Y
 * @param hArvo Heurestiika arvo
 */        
    
    public Solmu (int x, int y, int hArvo){
        
        koordinaattiX=x;
        koordinaattiY=y;
        Heurestiikaarvo=hArvo;
        Edeltävä=null;
        seinä=false;
    }
}
