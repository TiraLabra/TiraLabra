package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;
        
        
        Kartta kartta = new Kartta(20, 20,18,19,19,2);
        Astar astar = new Astar(kartta);
        s = astar.haku(18, 19, 19, 2);
        astar.tulostaPolku(s);
    }

}
