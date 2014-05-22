package main;

import tietorakenteet.Node;

/**
 *
 */
public class Main {
    
    public static void main( String[] args ) {
        
        System.out.println("Tiralabra alpha version");
        
        Node n1 = new Node(1,1,0);
        Node n2 = new Node(3,3,0);
        n1.setEtaisyysAlusta(1);
        n2.setEtaisyysAlusta(3);
        
        int vertailu = n1.compareTo(n2);
        
        System.out.println(vertailu);
    }
}
