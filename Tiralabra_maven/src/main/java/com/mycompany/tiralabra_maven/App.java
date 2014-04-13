package com.mycompany.tiralabra_maven;

import javax.swing.SwingUtilities;
import labyrintti.Kaynnistys;
import labyrintti.osat.Pohja;
import labyrintti.sovellus.Etsija;
import labyrintti.sovellus.Minimikeko;
import labyrintti.gui.Kayttoliittyma;

public class App {

    public static void main(String[] args) {

//        Minimikeko keko = new Minimikeko(8);
//        keko.alustaTaulukko(p);
//        keko.tulosta();
//        keko.rakennaKeko();
//        System.out.println("Järjestetty keko: ");
//        keko.tulosta();
//        for (int i = 0; i < 100; i++) {
//            Pohja p = new Pohja();
//            p.alustaPohja1("src/main/java/labyrintti/osat/kartta1.txt");
//
//            Etsija e = new Etsija(p);
//            e.aStar();
//        }
//        System.out.println("Reitin koordinaatit:");
//        System.out.println("x,y");
//        System.out.println(e.getReitti());
        new Kaynnistys().kaynnista();
    }
}
