package com.mycompany.tiralabra_maven;

import haku.AStar;
import haku.Reitti;
import haku.ReittiLaskin;
import verkko.Pysakki;
import verkko.Verkko;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*
        TESTAAMISTA (toistaiseksi) TÄSSÄ
        */
        long start = System.currentTimeMillis();
        System.out.println( "Hello World!" );
        Verkko verkko = new Verkko();
        verkko.debugPrint();
        long stop = System.currentTimeMillis();
        System.out.println("Verkko luotu: " + (stop-start)+"ms");
        
        ReittiLaskin bfs = new ReittiLaskin(1,0,0,0,0,400); // A*-hausta saadaan leveyssuuntainen haku: heuristiikan arvo == 0 aina
        ReittiLaskin normaali = new ReittiLaskin(1,0,0,1,0,400); // Tavallinen, vain matka-aikaa laskeva haku
        ReittiLaskin vaihdoton = new ReittiLaskin(1,0,3,1,0,400); // Minimoi vaihtojen määrää
        ReittiLaskin reittiLaskin = vaihdoton;        
        
        Pysakki alku = verkko.getPysakit()[5];
        Pysakki loppu = verkko.getPysakit()[17];
        
        start = System.currentTimeMillis();
        System.out.println("Etsitään ratkaisua ");
        AStar aStar = new AStar( verkko, reittiLaskin );
        aStar.setDebugMode(true);   // koko jono: true, vain ratkaisuun asti: false
        aStar.setDebugPrint(false);
        Reitti reitti = aStar.etsiReitti(alku, loppu);
        System.out.println(""+reitti);
        System.out.println(""+aStar.getRatkaisu());
        stop = System.currentTimeMillis();
        System.out.println("Etsintä: " + (stop-start)+"ms");
        
    }
}
