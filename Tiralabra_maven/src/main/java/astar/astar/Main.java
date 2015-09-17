package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;
        
        
        Kartta kartta = new Kartta(25, 25,3,22,24,2);
        Astar astar = new Astar(kartta);
        s = astar.haku(3, 22, 24, 2);
        astar.tulostaPolku(s);
    }

}
