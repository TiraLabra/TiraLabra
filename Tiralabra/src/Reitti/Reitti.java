/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reitti;

import Verkko.*;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 *
 * @author eniirane
 */
public class Reitti {
    Verkko verkko;
    ArrayList<Noodi> reitti;
    
 
    public Reitti(Verkko verkko) {
        this.reitti = new ArrayList<Noodi>();
        this.verkko = verkko;
    }
    
    public void AStar(Noodi alku, Noodi loppu) {
        PriorityQueue<Noodi> avoinjoukko = new PriorityQueue<Noodi>(); 
        ArrayList<Noodi> suljettujoukko = new ArrayList<Noodi>();
        
        // alustetaan verkon h- ja g-arvot
        astarheuristic(alku);
        
        // laitetaan alkusolmulle oikea g-arvo ja lisätään se tarkastelujoukkoon
        alku.setG(0);
        avoinjoukko.add(alku);
 
        // käydään läpi avointa joukkoa kunnes loppusolmu on käsitelty
        while (!suljettujoukko.contains(loppu)) {
            Noodi u = avoinjoukko.poll();
            suljettujoukko.add(u);
            for (Noodi i : this.verkko.getNaapurit(u) ) { 
                if (i.getG() > u.getG() + u.getW()) {
                    i.setG(u.getG() + u.getW());
                    i.setVanhempi(u);
                }
            }            
        }
        
        getReitti(alku, loppu);
        
    }

    public void getReitti(Noodi alku, Noodi loppu) {
        AStar(alku, loppu);
        Noodi i = loppu;
        while (i.getVanhempi() != null) {
            reitti.add(i.getVanhempi());
            i = i.getVanhempi();
        }
    
    }
    
    public void astarheuristic(Noodi loppu) {
        
        int loppuX = loppu.getX();
        int loppuY = loppu.getY();
        
        // alustetaan joka solmulle oikea h-arvo suhteessa loppusolmuun
        for (int x = 0; x < this.verkko.getLeveys(); x++) {
            for (int y = 0; y < this.verkko.getKorkeus(); y++) {
                this.verkko.getNoodi(x, y).setH(Math.abs(x - loppuX) + Math.abs(y - loppuY));
                this.verkko.getNoodi(x, y).setG(Integer.MAX_VALUE);
            }
        }
    }
    
    @Override
    public String toString() {
        String output = "";
        for (Noodi i : reitti) {
            output = output + i.toString();
        }
        return output;
    }
}
