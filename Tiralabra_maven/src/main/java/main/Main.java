package main;

import apurakenteet.Kuvalukija;
import heuristiikat.*;
import java.io.IOException;
import java.util.ArrayList;
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
        
        //16x16-testialue
        Alue a1 = new Alue(16);
        a1.luoEsimerkkiTaulukko();
        
        //8x8-testialue
        Alue a2 = new Alue(8);
        a2.luoPieniTestitaulukko();
        
        //Alue kuvasta:
        Kuvalukija kl = new Kuvalukija();
        Alue kuvaAlue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        
        
        // Mitä aluetta tarkastellaan...
        Alue hakualue = kuvaAlue;
        
        System.out.println(hakualue.toString());
        
        Heuristiikka h = new Manhattan();
        //Heuristiikka h = new Dijkstra();
        
        AStar as = new AStar(h);
        
        as.AStarHaku(hakualue, hakualue.getnode(0, 0), hakualue.getnode(6,6));
        
        System.out.println("Yhteensä " + as.getAskelia() + " laskenta-askelta.");
        
        ArrayList<Node> reitti = as.kerroKuljettuReitti();
        System.out.println("\nKuljettu reitti: ("+ reitti.size() + " kpl)");
        for (Node n : reitti) {
            System.out.println(n.toString());
            n.toString();
        }
        System.out.println(hakualue.toString());
        
        System.out.println("-------");
        
        
    }
}
