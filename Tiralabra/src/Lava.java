/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class Lava {
    private double leveys;
    private double pituus;
    private double korkeus;
    
    private boolean[][][] asetetutLaatikot;
    
    public Lava (int pituus, int leveys, int korkeus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
        
        this.asetetutLaatikot = new boolean[leveys*2][pituus*2][korkeus*2];
    }
    
    public void asetaLaatikko (int x, int y, int z, int leveys,
            int pituus, int korkeus) {
        for (int i = x*2; i < x*2+leveys*2; i++) {
            for (int j = y*2; j < y*2+pituus*2; j++) {
                for (int k = z*2; k < z*2+korkeus*2; k++) {
                    asetetutLaatikot[i][j][k] = true;
                }
            }
        }
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
