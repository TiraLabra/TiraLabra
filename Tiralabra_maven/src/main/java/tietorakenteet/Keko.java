
package tietorakenteet;

/**
 * Oma versio Keko-tietorakenteen toteutuksesta.
 * Ei toteutettu vielä, aloitettu vasta aivan alustavana runkona...
 */
public class Keko {
    
    private Object[] sisalto;
    
    private int koko;
    
    

    public boolean onTyhja() {
        if (koko == 0)
            return true;
        else
            return false;
    }
    
}
