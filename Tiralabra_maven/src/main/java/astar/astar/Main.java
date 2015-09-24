package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;

        Kartta kartta = new Kartta(10, 10);
        Astar astar = new Astar(kartta);
        s = astar.haku(0, 1, 5, 5);
        astar.tulostaPolku(s);
    }

}
