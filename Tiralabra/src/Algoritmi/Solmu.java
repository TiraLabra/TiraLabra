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
    public int Reittipituus;

    public Solmu Edeltävä;
    
    public int koordinaattiX;
    public int koordinaattiY;
    
    private boolean seinä;
    
/**
 *Konstruktori
 * 
 * @param x koordinaatti X
 * @param y koordinaatti Y
 * @param hArvo Heurestiika arvo
 */        
    
    public Solmu (int x, int y, int hArvo, int väri ){
        
        koordinaattiX=x;
        koordinaattiY=y;
        Heurestiikaarvo=hArvo;
        Reittipituus=0;
        Edeltävä=null;
        if(väri==255){
            seinä=false;
        }else{
            seinä=true;
        }
        
    }
    
    public boolean haeSeina(){
        
        
        return seinä;
        
    }
    
}
