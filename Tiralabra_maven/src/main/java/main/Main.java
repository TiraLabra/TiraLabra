package main;

import tietorakenteet.Alue;
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
        
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        System.out.println(a.toString());
        
        AStar as = new AStar();
        
        as.AStarHaku(a, a.getnode(0, 0), a.getnode(14, 14));
    }
}
