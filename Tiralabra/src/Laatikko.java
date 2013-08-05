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
    
    public Laatikko(int leveys, int pituus, int korkeus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
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