package com.mycompany.tiralabra_maven;

import labyrintti.osat.Pohja;
import labyrintti.sovellus.Etsija;
import labyrintti.sovellus.Minimikeko;

public class App {

    public static void main(String[] args) {
        Pohja p = new Pohja();
        p.alustaPohja("src/main/java/labyrintti/osat/kartta2.txt");
        Minimikeko keko = new Minimikeko(8);
        keko.alustaTaulukko(p);
        keko.tulosta();
        keko.rakennaKeko();
        System.out.println("Järjestetty keko: ");
        keko.tulosta();
//        Etsija e = new Etsija(p);
//        e.aStar();
//        System.out.println("Reitin koordinaatit:");
//        System.out.println("x,y");
//        System.out.println(e.getReitti());
    }
}
