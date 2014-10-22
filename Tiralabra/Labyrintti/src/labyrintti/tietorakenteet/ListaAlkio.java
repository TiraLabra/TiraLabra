/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.tietorakenteet;


/**
 * Luokka edustaa alkioita, joita voidaan tallettaa linkitettyyn listaan.
 * 
 * @author Mikael Parvamo
 */
public class ListaAlkio {
    private ListaAlkio seuraava;
    
    public ListaAlkio(){
        this.seuraava = null;
    }
    
    /**
    * @param seuraava, eli alkiota seuraava jäsen listassa.
    */     
    public void setSeuraava(ListaAlkio seuraava){
        this.seuraava = seuraava;
    }
    
    /**
    * @return this.seuraava
    */  
    public ListaAlkio getSeuraava(){
        return this.seuraava;
    }
}
