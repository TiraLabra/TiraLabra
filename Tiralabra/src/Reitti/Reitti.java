/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reitti;

import Verkko.*;
import java.util.PriorityQueue;

/**
 *
 * @author eniirane
 */
public class Reitti {
    Verkko verkko;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Verkko gi = new Verkko();
        Reitti r = new Reitti(gi);
        
    }

    public Reitti(Verkko verkko) {
        this.verkko = verkko;
    }
    
    public void AStar(Noodi alku, Noodi loppu) {
        PriorityQueue<Noodi> avoinjoukko = new PriorityQueue<Noodi>(); 
        PriorityQueue<Noodi> suljettujoukko = new PriorityQueue<Noodi>();
        
        // alustetaan verkon h- ja g-arvot
        astarheuristic(alku);
        
        // laitetaan alkusolmulle oikea g-arvo ja lisätään se tarkastelujoukkoon
        alku.setG(0);
        avoinjoukko.add(alku);

        // käydään läpi avointa joukkoa kunnes loppusolmu on käsitelty
        while (!suljettujoukko.contains(loppu)) {
            Noodi u = avoinjoukko.poll();
            suljettujoukko.add(u);
            for (Noodi i : u.getNaapurit()) {
                if (i.getG() > u.getG() + u.getW()) {
                    i.setG(u.getG() + u.getW());
                    i.setVanhempi(u);
                }
            }            
        }
    }

    public void getReitti(Noodi alku, Noodi loppu) {
         // while !alku: i = loppu, print i.parent, i = i.parent
    
    }
    
    public void astarheuristic(Noodi loppu) {
        
        int loppuX = loppu.getX();
        int loppuY = loppu.getY();
        
        // alustetaan joka solmulle oikea h-arvo suhteessa loppusolmuun, ja 
        for (int x = 0; x < this.verkko.getLeveys(); x++) {
            for (int y = 0; y < this.verkko.getKorkeus(); y++) {
                this.verkko.getNoodi(x, y).setH(Math.abs(x - loppuX) + Math.abs(y - loppuY));
                this.verkko.getNoodi(x, y).setG(Integer.MAX_VALUE);
            }
        }
    }
    
    
}
