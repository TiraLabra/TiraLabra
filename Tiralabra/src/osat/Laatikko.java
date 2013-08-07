package osat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class Laatikko {
    private int leveys;
    private int pituus;
    private int korkeus;
    private int EAN;
    
    public Laatikko(int leveys, int pituus, int korkeus, int EAN) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
        this.EAN = EAN;
    }
    
    public int getLeveys() {
        return leveys;
    }
    
    public int getPituus() {
        return pituus;
    }
    
    public int getKorkeus() {
        return korkeus;
    }
}