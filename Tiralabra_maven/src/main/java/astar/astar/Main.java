package astar.astar;

public class Main {

    public static void main(String[] args) {
        Solmu s;
        
        
        Kartta kartta = new Kartta(30, 30,20,20,15,6);
        Astar astar = new Astar(kartta);
        s = astar.haku(20, 20, 15, 6);
        astar.tulostaPolku(s);
    }

}
