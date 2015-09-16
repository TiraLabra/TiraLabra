package astar.astar;

public class Main {

    public static void main(String[] args) {
        Kartta kartta = new Kartta(10, 10);
        Astar astar = new Astar(kartta);
        astar.tulostaPolku(astar.haku(kartta.getAlkuX(), kartta.getAlkuY(), kartta.getMaaliX(), kartta.getMaaliY()));
    }

}
