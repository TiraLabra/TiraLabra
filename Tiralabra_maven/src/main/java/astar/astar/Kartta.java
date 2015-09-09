/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;

/**
 *
 * @author sasumaki
 */
public class Kartta {
    
    int leveys;
    int korkeus;
    private ArrayList<ArrayList<Solmu>> kartta;
    
    public Kartta(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        
    }
    
    public void luoKartta() {
        Solmu solmu;
        for (int x = 0; x < leveys; x++) {
            kartta.add(new ArrayList<Solmu>());
            for (int y = 0; y < korkeus; y++) {
                solmu = new Solmu(x, y, false, false, false);
                kartta.get(x).add(solmu);
            }
        }
    }
    public void setMaali(int x, int y){
        kartta.get(x).get(y).setMaali(true);
    }
    public void setEste(int x, int y){
        kartta.get(x).get(y).setEste(true);
    }
    public void setAlku(int x, int y){
        kartta.get(x).get(y).setAlku(true);
    }
}
