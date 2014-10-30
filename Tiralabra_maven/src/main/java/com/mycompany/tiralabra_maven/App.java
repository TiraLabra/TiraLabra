package com.mycompany.tiralabra_maven;

import haku.AStar;
import haku.Reitti;
import haku.ReittiLaskin;
import verkko.Verkko;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Verkko verkko = new Verkko();
        verkko.debugPrint();
        System.out.println("DONE");
        
        ReittiLaskin reittiLaskin = new ReittiLaskin();
        AStar aStar = new AStar();
        Reitti reitti = aStar.etsiReitti(verkko, verkko.getPysakit()[5], verkko.getPysakit()[17], reittiLaskin);
        System.out.println(""+reitti);
        
    }
}
