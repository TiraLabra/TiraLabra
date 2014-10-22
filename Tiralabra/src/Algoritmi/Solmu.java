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
    
    public int heurestiikaArvo;
    public int reittipituus;

    public Solmu edeltävä;
    
    public int koordinaattiX;
    public int koordinaattiY;
    
    private boolean seinä;
    public boolean käyty;
    
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
        
        koordinaattiX=x;
        koordinaattiY=y;
        heurestiikaArvo=hArvo;
        reittipituus=0;
        edeltävä=null;
        
        if(väri==255){
            seinä=false;
        }else{
            seinä=true;
        }
        
        käyty=false;
        
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
    
    public int Vertaa(Solmu verrattava) {
        
        if (heurestiikaArvo+reittipituus < verrattava.heurestiikaArvo+verrattava.reittipituus){
            return 1;
        }else{
            return -1;
        }
        
        
        
    }
    
    
}
