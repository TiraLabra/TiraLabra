package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;

        Kartta kartta = new Kartta(20, 20);
        Astar astar = new Astar(kartta);
        s = astar.haku(2, 3, 19, 14);
        astar.tulostaPolku(s);
    }

}
