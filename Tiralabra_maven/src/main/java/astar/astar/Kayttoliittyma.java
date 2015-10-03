/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import astar.logiikka.Astar;
import astar.logiikka.BreadthFirst;
import astar.logiikka.Dijkstra;
import astar.verkko.Kartta;
import astar.verkko.Solmu;
import java.util.Random;
import java.util.Scanner;

/**
 * Perus tekstikäyttöliittymä.
 *
 * @author sasumaki
 */
public class Kayttoliittyma {

    private static int algoritmi;

    public Kayttoliittyma(Scanner lukija) {
        boolean skip = false;
        System.out.println("Anna kartan leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine());
        if (leveys < 1) {
            leveys = 1;
        }
        System.out.println("Anna kartan korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine());
        if (korkeus < 1) {
            korkeus = 1;
        }

        System.out.println("Anna alkupiste X: ");
        int alkuX = Integer.parseInt(lukija.nextLine());
        if (alkuX < 0) {
            alkuX = 0;
        }
        if (alkuX > leveys - 1) {
            alkuX = leveys - 1;
        }
        System.out.println("Anna alkupiste Y: ");
        int alkuY = Integer.parseInt(lukija.nextLine());
        if (alkuY < 0) {
            alkuY = 0;
        }
        if (alkuY > korkeus - 1) {
            alkuY = korkeus - 1;
        }
        System.out.println("Anna maalipiste X: ");
        int maaliX = Integer.parseInt(lukija.nextLine());
        if (maaliX < 0) {
            maaliX = 0;
        }
        if (maaliX > leveys - 1) {
            maaliX = leveys - 1;
        }
        System.out.println("Anna maalipiste Y: ");
        int maaliY = Integer.parseInt(lukija.nextLine());
        if (maaliY < 0) {
            maaliY = 0;
        }
        if (maaliY > korkeus - 1) {
            maaliY = korkeus - 1;
        }
        System.out.println("1 = A*");
        System.out.println("2 = Dijkstra");
        System.out.println("3 = BFS \n");
        algoritmi = Integer.parseInt(lukija.nextLine());

        System.out.println("Luodaanko superrandomkartta666? (y/n): ");
        if ((lukija.nextLine().equals("y"))) {
            if (korkeus > 200 || leveys
                    > 200) {
                System.out.println("Oletko varma? (Tässä voi kestää useita minuutteja,,) (y/n)");
                if (lukija.nextLine().equals("n")) {
                    skip = true;
                }
            }
            if (!skip) {
                Random random = new Random();
                System.out.println("Kartta: ");
                long aikaAlussa = System.currentTimeMillis();

                toiminta(leveys, korkeus, alkuX, alkuY, maaliX, maaliY, random);

                long aikaLopussa = System.currentTimeMillis();
                System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
            } else {
                peruskarttaluonti(leveys, korkeus, alkuX, alkuY, maaliX, maaliY);

            }

        } else {
            peruskarttaluonti(leveys, korkeus, alkuX, alkuY, maaliX, maaliY);
        }
    }

    private void peruskarttaluonti(int leveys, int korkeus, int alkuX, int alkuY, int maaliX, int maaliY) {
        System.out.println("Kartta: ");
        long aikaAlussa = System.currentTimeMillis();

        toiminta(leveys, korkeus, alkuX, alkuY, maaliX, maaliY, null);

        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
    }

    private static void toiminta(int leveys, int korkeus, int alkuX, int alkuY, int maaliX, int maaliY, Random random) {
        Solmu s;
        Kartta kartta = new Kartta(leveys, korkeus, random);
        Astar astar = new Astar(kartta);
        BreadthFirst bfs = new BreadthFirst(kartta);
        Dijkstra dijkstra = new Dijkstra(kartta);
        if (algoritmi == 1) {
            s = astar.haku(alkuX, alkuY, maaliX, maaliY);
        }
        if(algoritmi == 2){
            s = dijkstra.haku(alkuX, alkuY, maaliX, maaliY);
        }
        if (algoritmi == 3) {
            s = bfs.haku(alkuX, alkuY, maaliX, maaliY);
        }

    }

}
