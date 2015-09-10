/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.Comparator;
import java.util.PriorityQueue;

/** Reitinhaku algoritmi
 *
 * @author sasumaki
 */
public class Astar {

    private Kartta kartta;

    public Astar(Kartta kartta) {
        this.kartta = kartta;
    }
/** Hakee nopeimman reitin kahden solmun välillä, toiminta keskeneräinen.
   */ 
    public void haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {

        PriorityQueue<Solmu> rintama = new PriorityQueue<Solmu>(10, new Comparator<Solmu>() {

            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }
        });

        kartta.setAlku(alkuX, alkuY);
        kartta.setMaali(maaliX, maaliY);

        kartta.getAlkuSolmu().setMatkaAlusta(0);
        
        rintama.add(kartta.getAlkuSolmu());
        int matka = 0;
        
        while(!rintama.isEmpty()){
            Solmu nykyinen = rintama.poll();
             
            if(nykyinen.onkoMaali == true){
                break;
            }
            for(Solmu n: nykyinen.solmunNaapurit(nykyinen.getX(), nykyinen.getY())){
                int uusiMatka = (matka + 1) + kartta.matkaArvo(nykyinen, n);
            }
        }

    }
/** laskee kahden solmun välisen heuristisen matkan ns. manhattanmatkana eli ensin 
 * horisontaalitasossa ja sitten vertikaalitasossa
 * @param s
 * @param maaliX
 * @param maaliY
 * @return 
 */
    private int heuristinenMatka(Solmu s, int maaliX, int maaliY) {
        int heuristinen = 0;

        heuristinen = Math.abs((maaliY - s.getY()) + (maaliX - s.getX()));
        return heuristinen;
    }
}
