package main;

import hakualgoritmit.AStar;
import apurakenteet.Kuvalukija;
import apurakenteet.Kuvanayttaja;
import heuristiikat.*;
import suorituskyky.AStarSuorituskyky;
import tietorakenteet.*;
import ui.TekstiUI;

/**
 *
 */
public class Main {
    
    public static void main( String[] args ) {
        
        System.out.println("Tiralabra - AStar-reitinhaku");
        
//        //16x16-testialue
//        Alue a1 = new Alue(16);
//        a1.luoEsimerkkiTaulukko();

        //System.out.println(hakualue.toString());
        
        //Heuristiikka h = new Manhattan();
        //Heuristiikka h = new Euklidinen();
        //Heuristiikka h = new Dijkstra();
        
        //AStar as = new AStar(h);
        
//        if (as.AStarHaku(hakualue, hakualue.getnode(0, 0), hakualue.getnode(15,15))) {
//            // Tulostus:
//            System.out.println("Yhteensä " + as.getAskelia() + " laskenta-askelta.");
//
//            ArrayListOma reitti = as.kerroKuljettuReitti();
//            System.out.println("\nKuljettu reitti: ("+ reitti.koko()+ " kpl)");
//            //for (Node n : reitti) {
//            for (int i = 0; i < reitti.koko(); i++) {
//                Node n = (Node)reitti.palautaKohdasta(i);
//                System.out.println(n.toString());
//                n.toString();
//            }
//            System.out.println(hakualue.toString());
//            System.out.println(as.yhteenveto());
//
//            System.out.println("-------");
//            
//            tulostaKuva = false;
//            if (tulostaKuva) {
//                Kuvanayttaja kn = new Kuvanayttaja(kl.getKuva());
//                kn.muodostaKuvaanPolku(reitti);
//                kn.naytaKuva();
//            }
//            
//        } else {
//            System.out.println("Hakua ei voitu suorittaa, tarkasta parametrit!");
//        }
        
        
        
        // Testauskoodia sekalaiseen debuggaukseen:
        //sekatestausta();
        
        //TekstiUI ui = new TekstiUI();
        //ui.suorita();
        
        //Kuvatesti("testi.bmp");
        
        AStarSuorituskyky testaus = new AStarSuorituskyky();
        testaus.suorita();
        
    }
    
    private static void sekatestausta() {

        Node n0 = new Node(0,0,1);
        Node n1 = new Node(1,0,1);
        Node n2 = new Node(2,0,1);
        Node n3 = new Node(3,0,1);
        Node n4 = new Node(4,0,1);
        Node n5 = new Node(5,0,1);

        Node [] nodeTaulukko = { n0, n1, n2, n3, n4, n5 };

        nodeTaulukko[0].setEtaisyysMaaliin(1);
        nodeTaulukko[1].setEtaisyysMaaliin(3);
        nodeTaulukko[2].setEtaisyysMaaliin(5);
        nodeTaulukko[3].setEtaisyysMaaliin(7);
        nodeTaulukko[4].setEtaisyysMaaliin(9);
        nodeTaulukko[5].setEtaisyysMaaliin(15);
        
        Keko k = new Keko(nodeTaulukko);
        
        Node nu = new Node(8,8,1);
        nu.setEtaisyysMaaliin(17);
        System.out.println(k.koko());
        System.out.println(k.toString());
        System.out.println("Lisätään uusi arvolta 17 tässä...");
        k.lisaa(nu);
        
        System.out.println(k.toString());
        
        nu.setEtaisyysMaaliin(2);
        
    }
    
    /**
     * Testikäyttöä varten oleva metodi, jolla voi katsoa, millaisen 
     * @param kuvanimi 
     */
    private static void Kuvatesti(String kuvanimi) {
        Kuvalukija kl = new Kuvalukija(kuvanimi);
        Alue kuvaAlue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        kuvaAlue.setAlueenKuva(kl.getKuva());
        
        System.out.println(kuvaAlue.toString());
        Kuvanayttaja kn = new Kuvanayttaja(kl.getKuva());
        //kn.muodostaKuvaanPolku(reitti);
        kn.naytaKuva();
        
    }
}
