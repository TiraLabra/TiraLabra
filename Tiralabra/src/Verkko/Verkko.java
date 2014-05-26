/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Verkko;

/**
 *
 * @author eniirane
 */
public class Verkko {
    Noodi[][] noodit;
    
    int leveys = 10;
    int korkeus = 10;
    
    public Verkko() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                noodit[i][j] = new Noodi(i, j);
            }
        }
    }
    
    public Noodi getNoodi(int x, int y) {
        return noodit[x][y];
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }
}
