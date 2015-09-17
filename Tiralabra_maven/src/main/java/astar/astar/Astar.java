/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Reitinhaku algoritmi
 *
 * @author sasumaki
 */
public class Astar {
    
    private Kartta kartta;
    
    public Astar(Kartta kartta) {
        this.kartta = kartta;
        
    }

    /**
     * Hakee nopeimman reitin kahden solmun välillä, toiminta keskeneräinen.
     */
    public Solmu haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {
        
        PriorityQueue<Solmu> rintama = new PriorityQueue<Solmu>(10000, new Comparator<Solmu>() {
            
            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }
            
        });
        
        rintama.add(new Solmu(alkuY, alkuX, null, 0));
        ArrayList<Solmu> kayty = new ArrayList<>();
        
        while (!rintama.isEmpty()) {
            
            Solmu nykyinen = rintama.poll();
            kayty.add(nykyinen);
            
            if (nykyinen.getY() == kartta.getMaaliY() && nykyinen.getX() == kartta.getMaaliX()) {
                break;
            }
            for (Solmu n : kartta.naapurit(nykyinen.getY(), nykyinen.getX(), nykyinen, nykyinen.getMatkaAlusta())) {
                if (!kayty.contains(n)) {
                    rintama.add(n);
                }
            }
        }
        return kayty.get(kayty.size() - 1);
    }

    /**
     * laskee kahden solmun välisen heuristisen matkan ns. manhattanmatkana eli
     * ensin horisontaalitasossa ja sitten vertikaalitasossa
     *
     * @param s
     * @param maaliX
     * @param maaliY
     * @return
     */
    private int heuristinenMatka(Solmu s, int maaliX, int maaliY) {
        int heuristinen = 0;
        
        heuristinen = Math.abs(maaliY - s.getY()) + Math.abs(maaliX - s.getX());
        return heuristinen;
    }

    /**
     * tulostaa kartan ja nopeimman polun kartalla (toivottavasti)
     *
     * @param polku
     */
    public void tulostaPolku(Solmu polku) {
        ArrayList<Solmu> ruudut = new ArrayList<>();
        ruudut.add(polku);
        Solmu d = polku.getEdellinen();
        
        while (d != null) {
            ruudut.add(d);
            d = d.getEdellinen();
        }
        
        
        for (int y = 0; y < kartta.getKorkeus(); y++) {
            for (int x = 0; x < kartta.getLeveys(); x++) {
                for (Solmu s : ruudut) {
                    if (s.getY() == y && s.getX() == x) {
                        System.out.print("+");
                    }
                }
                if(x == kartta.getAlkuX() && y == kartta.getAlkuY()){
                    System.out.print("O");
                }
                else if(x == kartta.getMaaliX() && y == kartta.getMaaliY()){
                    System.out.print("X");
                }
                else if (kartta.getRuutu(x, y) == Ruutu.LATTIA) {
                    System.out.print("-");
                }
            }
            System.out.println("");
            
        }
    }
}
