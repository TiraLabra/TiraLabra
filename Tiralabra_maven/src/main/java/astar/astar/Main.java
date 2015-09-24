package astar.astar;

import astar.verkko.Kartta;
import astar.verkko.Solmu;
import astar.logiikka.Astar;
import astar.verkko.PolkuTulostin;

public class Main {

    public static void main(String[] args) {
        long aikaAlussa = System.currentTimeMillis();
        Solmu s;

        Kartta kartta = new Kartta(1000, 1000);
        Astar astar = new Astar(kartta);
        s = astar.haku(0, 1, 970, 980);
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");

    }

}
