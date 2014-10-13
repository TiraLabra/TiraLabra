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
    public int suunta;
    
    public int koordinaattiX;
    public int koordinaattiY;
    
    private boolean seinä;
    
    /**
     *Konstruktori
     * 
     * @param x koordinaatti X
     * @param y koordinaatti Y
     * @param hArvo Heurestiika arvo
     * @param väri kertoo onko piste kuvassa jota solmu kuvaa seinä.
     * 
    */        
    
    public Solmu (int x, int y, int hArvo, int väri ){
        
        
        suunta=0;
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
    
    /**
     * Vertaa tämän ja annetun solmun Heurestiikaarvoja ja Reittipituuksia.
     * Jos tämän solmun Heurestiikka ja Reittipituusarvojen yhteisarvo on pienempi
     * kuin verrattavan solmun niin palautetaan 1 ja jos toisin päin niin palautetaan -1.
     * 
     * @param verrattava verrattava solmu
    */
    
    public int vertaa(Solmu verrattava) {
        
        if (Heurestiikaarvo+Reittipituus < verrattava.Heurestiikaarvo+verrattava.Reittipituus){
            return 1;
        }else if (Heurestiikaarvo+Reittipituus > verrattava.Heurestiikaarvo+verrattava.Reittipituus){
            return -1;
        }
        
        return 0;
        
    }
    
}
