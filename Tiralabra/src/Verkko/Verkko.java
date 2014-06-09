/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Verkko;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author eniirane
 */
public class Verkko {
    Noodi[][] noodit;
    
    int leveys = 50;
    int korkeus = 50;
    int tiheys = (korkeus + leveys) * 4;
    
    Random random;
    
    public Verkko() {
        this.random = new Random();

        noodit = new Noodi[korkeus][leveys];
        
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                noodit[i][j] = new Noodi(i, j);
            }
        }
        
        luoSatunnaisiaEsteita(tiheys);
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
    
    public void setNoodi(int x, int y) {
        noodit[x][y] = new Noodi(x, y);
    }
    
    public ArrayList<Noodi> getNaapurit(Noodi noodi) {
        ArrayList<Noodi> naapurit = new ArrayList<Noodi>();
        //Noodi[] naapurit = new Noodi[8];
        int x = noodi.getX();
        int y = noodi.getY();
        
        for (int i = x-1; i < x+1; i++) {
            for (int j = y; j < y+1; j++) {
                if (i != x && j != y && getNoodi(i, j).getTraversable() == true) {
                    naapurit.add(getNoodi(i,j));
                }
            }
        }
        return naapurit;
    }
    
    public void luoSatunnaisiaEsteita(int lkm) {
        for (int i = 0; i<lkm;i++) {
            int x = random.nextInt(korkeus-1);
            int y = random.nextInt(leveys-1);
            getNoodi(x, y).setTraversable(false);
        }
    }
    
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                if (getNoodi(i,j).getTraversable() == true) {
                    output = output + ".";
                } else {
                    output = output + "#";
                }
            }
            output = output + "\n";
        }
        
        return output;
    }
}
