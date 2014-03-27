package com.mycompany.tiralabra_maven;

import labyrintti.osat.Pohja;
import labyrintti.sovellus.Etsija;
import labyrintti.sovellus.Minimikeko;

public class App {

    public static void main(String[] args) {
        Pohja p = new Pohja();
        p.alustaPohja("src/main/java/labyrintti/osat/kartta1.txt");
        //p.tulostaPohja();
        Etsija e = new Etsija(p);
        e.aStar();
//        int[] lukuja = new int[10];
//        for (int i = 0; i < 10; i++) {
//            lukuja[i] = 11 - i;
//        }
//        Minimikeko keko = new Minimikeko(p.getKorkeus()*p.getLeveys());
//        keko.alustaTaulukko(p);
//        keko.rakennaKeko();
//        keko.tulosta();
//        keko.pollPienin();
//        System.out.println("poistettu maali");
//        keko.tulosta();

        System.out.println("Reitin koordinaatit:");
        System.out.println("x,y");
        e.getReitti();
    }
}
