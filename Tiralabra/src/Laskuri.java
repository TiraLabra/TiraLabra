/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class Laskuri {
    private Laatikko laatikko;
    private Lava lava;
    
    public Laskuri () {
        this.laatikko = new Laatikko(40, 60, 40);
        this.lava = new Lava(80, 120, 130);
    }
    
    public void laske (double leveys, double pituus, double korkeus) {
        
    }
    
    private void mahtuuko(double tarvittavaLeveys, double tarvittavaPituus, double tarvittavaKorkeus) {
        int alkuX = 0;
        int alkuY = 0;
        int alkuZ = 0;
        
        int tyhjaTila = 0;
        
        for (int i = 0; i < alkuX + tarvittavaLeveys; i++) {
            for (int j = 0; j < alkuY + tarvittavaPituus; j++) {
                for (int k = 0; k < alkuZ + tarvittavaKorkeus; k++) {
                    
                }
            }
        }
    }
}
