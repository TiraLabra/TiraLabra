package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;

        Kartta kartta = new Kartta(8, 8);
        Astar astar = new Astar(kartta);
        s = astar.haku(2, 3, 6, 5);
        astar.tulostaPolku(s);
    }

}
