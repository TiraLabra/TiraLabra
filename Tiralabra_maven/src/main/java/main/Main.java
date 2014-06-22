package main;

import hakualgoritmit.AStar;
import apurakenteet.*;
import heuristiikat.*;
import suorituskyky.*;
import tietorakenteet.*;
import ui.TekstiUI;

/**
 * Main-metodi, jonka tehtävänä on pääasiallisesti käynnistää käyttöliittymä.
 * Mukana on muutama sekalaiseen testaukseen tarkoitettu metodi, ja kommentoituna
 * koodia niiden kutsumiseksi.
 */
public class Main {
    
    public static void main( String[] args ) {
        
        System.out.println("Tiralabra - AStar-reitinhaku\n");
        
        TekstiUI ui = new TekstiUI();
        ui.suorita();
        
        // AStarin suorituskykytoistoja:
        //AStarSuorituskyky testaus = new AStarSuorituskyky();
        //testaus.suorita();
        
        // Testauskoodia sekalaiseen debuggaukseen:
        //sekatestausta();
        //Kuvatesti("testi.bmp");
        //pieniAluetesti();
    }
    
    /**
     * Metodi, jolla voi koodatessa helposti testata yksittäisiä pienempiä asioita.
     */
    private static void sekatestausta() {

    }
    
    /**
     * Testikäyttöä varten oleva metodi, jolla voi katsoa, millaisen kuvan ja alueen kuvalukija tulkitsee.
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
    
    /**
     * Testikäyttöä varten oleva metodi, jolla muodostetaan 16x16-alue hyvin yksinkertaista tarkastelua varten.
     */
    private static void pieniAluetesti() {
        //16x16-testialue
        Alue a1 = new Alue(16);
        a1.luoEsimerkkiTaulukko();
        System.out.println(a1.toString());
        
        Heuristiikka h = new Manhattan();
        
        AStar as = new AStar(h);
        if (as.AStarHaku(a1, a1.getnode(0, 0), a1.getnode(15,15))) {
            ArrayListOma reitti = as.kerroKuljettuReitti();
            System.out.println("\nKuljettu reitti: ("+ reitti.koko()+ " kpl)");
            for (int i = 0; i < reitti.koko(); i++) {
                System.out.println((Node)reitti.palautaKohdasta(i));
            }
            System.out.println(a1.toString());
            System.out.println(as.yhteenveto());
        } else {
            System.out.println("Hakua ei voitu suorittaa, tarkasta parametrit!");
        }
        
    }
}
