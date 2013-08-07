package osat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class Lava {
    private int leveys;
    private int pituus;
    private int korkeus;
    
    private boolean[][] asetetutLaatikot;
    
    public Lava(int leveys, int pituus, int korkeus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
        
        asetetutLaatikot = new boolean[leveys][pituus];
    }
    
    public void merkitseLaatikko(int x, int y, int leveys, int pituus) {
        for (int i = x; i < x+leveys; i++) {
            for (int j = y; j < y+pituus; j++) {
                    asetetutLaatikot[i][j] = true;
            }
        }
    }
    
    public void taytaRuutu(int x, int y) {
        asetetutLaatikot[x][y] = true;
    }
    
    public Lava kopioi() {
        Lava kopio = new Lava(leveys, pituus, korkeus);
        
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < pituus; j++) {
                kopio.taytaRuutu(i, j);
            }
        }
        
        return kopio;
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
