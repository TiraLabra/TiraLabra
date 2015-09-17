package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;
        
        
        Kartta kartta = new Kartta(20, 20,2,2,22,19);
        Astar astar = new Astar(kartta);
        s = astar.haku(2, 2, 22, 19);
        astar.tulostaPolku(s);
    }

}
