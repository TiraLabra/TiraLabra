package ohjelma;


import ohjelma.verkko.Verkko;
import ohjelma.verkko.Solmu;
import ohjelma.verkko.Kaari;
import java.util.HashSet;
import ohjelma.algoritmit.BellmanFord;
import ohjelma.algoritmit.Dijkstra;
import ohjelma.tietorakenteet.iHashMap;


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
        
        /**************************************************************/
        
        Verkko verkko = new Verkko(solmut, kaaret);
        verkko.teeVerkko();
        
        /**************************************************************/

        // Bellman-Ford
        long bellmanFordAlussa = System.currentTimeMillis();
        BellmanFord bellmanAloita = new BellmanFord(verkko);
        System.out.println(bellmanAloita.BellmanFord());
        long bellmanFordLopussa = System.currentTimeMillis();
        
        System.out.println("Alussa: "  + bellmanFordAlussa);
        System.out.println("Lopussa: " + bellmanFordLopussa);
        System.out.println("Kesti: "   +(bellmanFordLopussa - bellmanFordAlussa) + " ms");
        
        /**************************************************************/
        
        // Dijkstra
        long dijkstraAlussa = System.currentTimeMillis();
        Dijkstra dijkstraAloita = new Dijkstra(verkko);
        dijkstraAloita.Dijkstra();
        long dijkstraLopussa = System.currentTimeMillis();
        
        System.out.println("Alussa: "  + dijkstraAlussa);
        System.out.println("Lopussa: " + dijkstraLopussa);
        System.out.println("Kesti: "   +(dijkstraLopussa - dijkstraAlussa) + " ms");
    }
}