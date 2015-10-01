package astar.astar;

import astar.verkko.Kartta;
import astar.verkko.Solmu;
import astar.logiikka.Astar;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        System.out.println("Anna kartan leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine());
        System.out.println("Anna kartan korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine());

        System.out.println("Anna alkupiste X: ");
        int alkuX = Integer.parseInt(lukija.nextLine());
        System.out.println("Anna alkupiste Y: ");
        int alkuY = Integer.parseInt(lukija.nextLine());
        System.out.println("Anna maalipiste X: ");
        int maaliX = Integer.parseInt(lukija.nextLine());
        System.out.println("Anna maalipiste Y: ");
        int maaliY = Integer.parseInt(lukija.nextLine());
        System.out.println("Random kartta? (y/n): ");

        if (lukija.nextLine().equals("y")) {
            Random random = new Random();
            System.out.println("Kartta: ");
            long aikaAlussa = System.currentTimeMillis();

            toiminta(leveys, korkeus, alkuX, alkuY, maaliX, maaliY, random);

            long aikaLopussa = System.currentTimeMillis();
            System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");

        } else {
            System.out.println("Kartta: ");
            long aikaAlussa = System.currentTimeMillis();

            toiminta(leveys, korkeus, alkuX, alkuY, maaliX, maaliY, null);

            long aikaLopussa = System.currentTimeMillis();
            System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
        }

    }

    private static void toiminta(int leveys, int korkeus, int alkuX, int alkuY, int maaliX, int maaliY, Random random) {
        Solmu s;
        Kartta kartta = new Kartta(leveys, korkeus, random);
        Astar astar = new Astar(kartta);
        s = astar.haku(alkuX, alkuY, maaliX, maaliY);
    }

}
