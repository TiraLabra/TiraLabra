/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class Laatikko {
    private double leveys;
    private double pituus;
    private double korkeus;
    
    public Laatikko (double pituus, double leveys, double korkeus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
    }
    
    public double getLeveys() {
        return this.leveys;
    }
    
    public double getPituus() {
        return this.pituus;
    }
    
    public double getKorkeus() {
        return this.korkeus;
    }
}