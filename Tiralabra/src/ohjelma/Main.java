package ohjelma;

import java.util.HashSet;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class Main {
//

    public static void main(String[] args) {
        iHashMap<Integer, Solmu> solmut = new iHashMap<Integer, Solmu>();
        HashSet<Kaari> kaaret = new HashSet<Kaari>();
        //*****************************************//
        
        Verkko verkko = new Verkko(solmut, kaaret);
        verkko.teeVerkko();

//        BellmanFord aloita1 = new BellmanFord(verkko);
//        System.out.println(aloita1.BellmanFord(verkko));
        
        Dijkstra aloita2 = new Dijkstra(verkko);
        aloita2.Dijkstra(verkko);
    }
}