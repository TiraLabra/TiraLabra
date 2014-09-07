/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

/**
 *
 * @author Jaakko
 */
public class Solmu {
    
    public int Heurestiikaarvo;
    public int Liikkumisarvo;
    public int Yhteisarvo;
    public int pääsemisarvo;
    
    public Solmu Edeltävä;
    
    public int koordinaattiX;
    public int koordinaattiY;
    
    
    public Solmu (int x, int y, int lArvo, int hArvo){
        
        koordinaattiX=x;
        koordinaattiY=y;
        Liikkumisarvo=lArvo;
        Heurestiikaarvo=hArvo;
        Yhteisarvo=lArvo+hArvo;
        pääsemisarvo=Integer.MAX_VALUE;

        Edeltävä=null;
    }
}
