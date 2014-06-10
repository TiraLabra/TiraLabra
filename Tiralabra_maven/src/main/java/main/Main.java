package main;

import hakualgoritmit.AStar;
import apurakenteet.Kuvalukija;
import heuristiikat.*;
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
        int alkurivi = 0;
        int alkusarake = 0;
        int loppurivi = 70;
        int loppusarake = 75;
        
        System.out.println(hakualue.toString());
        
        Heuristiikka h = new Manhattan();
        //Heuristiikka h = new Dijkstra();
        
        AStar as = new AStar(h);
        
        as.AStarHaku(hakualue, hakualue.getnode(alkurivi, alkusarake), hakualue.getnode(loppurivi,loppusarake));
        
        System.out.println("Yhteensä " + as.getAskelia() + " laskenta-askelta.");
        
        ArrayList<Node> reitti = as.kerroKuljettuReitti();
        System.out.println("\nKuljettu reitti: ("+ reitti.size() + " kpl)");
        for (Node n : reitti) {
            System.out.println(n.toString());
            n.toString();
        }
        System.out.println(hakualue.toString());
        System.out.println(as.yhteenveto());
        
        System.out.println("-------");
        
//        Node n0 = new Node(0,0,1);
//        Node n1 = new Node(1,0,1);
//        Node n2 = new Node(2,0,1);
//        Node n3 = new Node(3,0,1);
//        Node n4 = new Node(4,0,1);
//        Node n5 = new Node(5,0,1);
//
//        Node [] nodeTaulukko = { n0, n1, n2, n3, n4, n5 };
//
//        nodeTaulukko[0].setEtaisyysMaaliin(1);
//        nodeTaulukko[1].setEtaisyysMaaliin(3);
//        nodeTaulukko[2].setEtaisyysMaaliin(5);
//        nodeTaulukko[3].setEtaisyysMaaliin(7);
//        nodeTaulukko[4].setEtaisyysMaaliin(9);
//        nodeTaulukko[5].setEtaisyysMaaliin(15);
//        
//        Keko k = new Keko(nodeTaulukko);
//        Node nu = new Node(8,8,1);
//        nu.setEtaisyysMaaliin(2);
//        //System.out.println(k.koko());
//        System.out.println(k.toString());
//        k.lisaa(nu);
//        
//        System.out.println(k.toString());
        
    }
}
