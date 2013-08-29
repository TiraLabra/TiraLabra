package ohjelma;

import java.util.HashSet;
import java.util.Scanner;
import ohjelma.algoritmit.BellmanFord;
import ohjelma.algoritmit.Dijkstra;
import ohjelma.tietorakenteet.iHashMap;
import ohjelma.tietorakenteet.iHashSet;
import ohjelma.verkko.Kaari;
import ohjelma.verkko.Solmu;
import ohjelma.verkko.Verkko;


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


        Verkko verkko;
        Scanner lukija = new Scanner(System.in);
        while (true) {
            int vastaus;
            System.out.println("Valitse verkko jonka haluat ajaa");
            System.out.println("0. Tee oma verkko" + "\n"
                    + "1. Iso verkko" + "\n"
                    + "2. Pieni verkko" + "\n"
                    + "3. Pieni verkko jossa paljon kaaria" + "\n"
                    + "4. Negatiivisia painoja sisältävä verkko" + "\n"
                    + "5. Negatiivisen syklin sisältävä verkko");
            vastaus = lukija.nextInt();

            verkko = luoVerkko();
            if (vastaus == 0) {
                verkko.omaVerkko();

            } else if (vastaus == 1) {
                verkko.teeIsoVerkko();

            } else if (vastaus == 2) {
                verkko.teePieniVerkko1();

            } else if (vastaus == 3) {
                verkko.teePieniVerkko2();

            } else if (vastaus == 4) {
                verkko.teeNegPainollinenVerkko();

            } else if (vastaus == 5) {
                verkko.teeSyklillinenVerkko();

            } else {
                System.exit(0);
            }


            // Bellman-Ford
            long bellmanFordAlussa = System.currentTimeMillis();
            BellmanFord bellmanAloita = new BellmanFord(verkko);
            System.out.println(bellmanAloita.BellmanFord());
            long bellmanFordLopussa = System.currentTimeMillis();

            System.out.println("Kesti: " + (bellmanFordLopussa - bellmanFordAlussa) + " ms");
            System.out.println("");

            // Dijkstra
            long dijkstraAlussa = System.currentTimeMillis();
            Dijkstra dijkstraAloita = new Dijkstra(verkko);
            dijkstraAloita.Dijkstra();
            long dijkstraLopussa = System.currentTimeMillis();
            System.out.println("");

            System.out.println("Kesti: " + (dijkstraLopussa - dijkstraAlussa) + " ms");
            System.out.println("");
        }
    }

    public static Verkko luoVerkko() {
        return new Verkko(new iHashMap<Integer, Solmu>(), new HashSet<Kaari>());
    }
}
