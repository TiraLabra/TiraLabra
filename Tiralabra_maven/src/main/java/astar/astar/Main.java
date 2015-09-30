package astar.astar;

import astar.verkko.Kartta;
import astar.verkko.Solmu;
import astar.logiikka.Astar;

public class Main {

    public static void main(String[] args) {
        long aikaAlussa = System.currentTimeMillis();
        Solmu s;

        Kartta kartta = new Kartta(10, 10);
        Astar astar = new Astar(kartta);
        s = astar.haku(9, 8, 0,1);
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");

    }

}
