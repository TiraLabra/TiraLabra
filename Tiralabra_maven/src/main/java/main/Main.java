package main;

import heuristiikat.*;
import tietorakenteet.*;
import tietorakenteet.*;

/**
 *
 */
public class Main {
    
    public static void main( String[] args ) {
        
        System.out.println("Tiralabra alpha version");
        
//        Node n1 = new Node(1,1,0);
//        Node n2 = new Node(3,3,0);
//        n1.setEtaisyysAlusta(1);
//        n2.setEtaisyysAlusta(3);
//        
//        int vertailu = n1.compareTo(n2);
//        
//        System.out.println(vertailu);
        
        //Alue a = new Alue(16);
        //a.luoEsimerkkiTaulukko();
        Alue a = new Alue(8);
        a.luoPieniTestitaulukko();
        
        
        System.out.println(a.toString());
        
        
        Heuristiikka h = new Manhattan();
        //Heuristiikka h = new Dijkstra();
        
        AStar as = new AStar(h);
        
        as.AStarHaku(a, a.getnode(0, 0), a.getnode(6,6));
        
        System.out.println(as.getAskelia() + " askelta.");
    }
}
