package com.mycompany.tiralabra_maven;

import labyrintti.osat.Pohja;
import labyrintti.sovellus.Etsija;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        Pohja p = new Pohja();
        //System.out.println( "Hello World!" );
        p.luoPohja();
        //p.tulostaPohja();
        Etsija e = new Etsija(p);
        e.aStar();
        System.out.println("Reitin koordinaatit:");
        System.out.println("x,y");
        e.tulostaReitti(e.getReitti());
    }
}
