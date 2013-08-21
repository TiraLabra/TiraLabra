/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet;

/**
 * Jono jota käytetään bittien ja tavujen muodostuksessa.
 * @author joonaslongi
 */
public class Jono {
    
    private int ensimmainen;
    private int viimeinen;
    private int koko;
    private boolean[] jono;
    
    /**
     * Isompaa ei pitäisi tarvita.
     */
    
    private static final int MAKSIMIKOKO = 512;
    
    /**
     * Alustaa jonon 
     */
    
    public Jono(){
        this.ensimmainen = 0;
        this.viimeinen = 0;
        this.koko = 0;
        this.jono = new boolean[MAKSIMIKOKO];
    }
    
    /**
     * Palauttaa jonon koon
     * @return koko
     */
    
    public int getKoko(){
        return this.koko;
    }
    
    /**
     * Palauttaa true jos jono on tyhjä, muuten false.
     * @return 
     */
    
    public boolean tyhja(){
        if(ensimmainen == viimeinen){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Lisää jonoon boolean muuttujan.
     * @param x 
     */
    
    public void lisaa(boolean x){
        if(koko < MAKSIMIKOKO){
            jono[viimeinen] = x;
            viimeinen++;
            koko++;
            if(viimeinen == MAKSIMIKOKO){
                viimeinen = 0;
            }
        }
    }
    
    /**
     * Ottaa jonon päästä ensimmäisen, poistaa ja palauttaa sen
     * @return ensimmainen
     */
    
    public boolean ota(){
        boolean x = jono[ensimmainen];
        koko--;
        ensimmainen++;
        if(ensimmainen == MAKSIMIKOKO){
            ensimmainen = 0;
        }
        return x;
    }
    
}
