package com.mycompany.tiralabra_maven;

import haku.AStar;
import haku.Reitti;
import haku.ReittiLaskin;
import java.util.Arrays;
import tira.LinkitettyLista;
import tira.DynaaminenLista;
import verkko.Pysakki;
import verkko.Verkko;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * Heuristiikan käyttämä tieto keskinopeudesta. Jos arvo on liian pieni, 
     * heuristiikka yliarvioi matkojen aikakustannuksia. Jos arvo liian suuri, 
     * aliarvioidaan & joudutaan laskemaan ylimääräisiä reittejä
     */
    public static double sporanNopeus = 526; // pistettä/min
    /**
     * Leveyssuuntainen haku tällä laskimella: heuristiikan arvo aina nolla
     */
    public static ReittiLaskin bfs = new ReittiLaskin(1, 0, 0, 0, 0, sporanNopeus); 
    /**
     * Matka-aikaa mininmoiva laskin
     */
    public static ReittiLaskin normaali = new ReittiLaskin(1, 0, 0, 1, 0, sporanNopeus); 
    /**
     * Vaihtoja välttelevä laskin
     */
    public static ReittiLaskin vaihdoton = new ReittiLaskin(1, 0, 3, 1, 0, sporanNopeus);
    /**
     * Matkaa ja matka-aikaa minimoiva laskin
     */
    public static ReittiLaskin normaaliMatkaaMinimoiva = new ReittiLaskin(1, 0.001, 0, 1, 0, sporanNopeus);
    /**
     * Matkaa ja matka-aikaa minimoiva laskin
     */
    public static ReittiLaskin vaihdotonMatkaaMinimoiva = new ReittiLaskin(1, 0.001, 3, 1, 0, sporanNopeus);
    
    public static void main(String[] args) {

        // debugAStar();
        debugAStarVertailu();
        // debugAStarHeuristiikka();
        // debugLinkitettyLista();
        // debugLista();

    }

    private static void debugAStarHeuristiikka() {
        Verkko verkko = new Verkko();
        ReittiLaskin reittiLaskin = normaali;
        reittiLaskin.setVerkko(verkko);
        
        long start, stop;
        start = System.currentTimeMillis();
        System.out.println("Heuristiikan onnistumisprosentti: "+reittiLaskin.toimiikoHeuristiikka());
        stop  = System.currentTimeMillis();
        System.out.println("Aika "+(stop-start)+" ms");
    }

    private static void debugAStarVertailu() {

        ReittiLaskin reittiLaskin = vaihdotonMatkaaMinimoiva;
        Verkko verkko = new Verkko();

        AStar aOma = new AStar(verkko, reittiLaskin); // omalla prioriteettijonolla
        aOma.setDebugMode(true);   // koko jono: true, vain ratkaisuun asti: false
        aOma.setDebugPrint(false);
        AStar aJava = new AStar(verkko, reittiLaskin); // javan pq:lla 
        aJava.setDebugMode(true);   // koko jono: true, vain ratkaisuun asti: false
        aJava.setDebugPrint(false);

        Pysakki alku = verkko.getPysakit()[5];
        Pysakki loppu = verkko.getPysakit()[66];
        int testSize = 100;
        long timeOma = 0, timeJava = 0, start, stop;
        for (int i = 0; i < testSize; i++) {
            start = System.currentTimeMillis();
            aOma.etsiReitti(alku, loppu);
            stop = System.currentTimeMillis();
            timeOma += stop - start;
            start = System.currentTimeMillis();
            aJava.etsiReittiJavanPQ(alku, loppu);
            stop = System.currentTimeMillis();
            timeJava += stop - start;
        }
        timeJava /= testSize;
        timeOma /= testSize;
        System.out.println("Oman keskiaika " + timeOma + " ms");
        System.out.println("" + aOma.getRatkaisu());
        System.out.println("Java keskiaika " + timeJava + " ms");
        System.out.println("" + aJava.getRatkaisu());
    }

    /**
     * A*-haun debuggausta varten
     */
    private static void debugAStar() {
        long start = System.currentTimeMillis();
        System.out.println("Hello World!");
        Verkko verkko = new Verkko();
        verkko.debugPrint();
        long stop = System.currentTimeMillis();
        System.out.println("Verkko luotu: " + (stop - start) + "ms");

        ReittiLaskin reittiLaskin = vaihdoton;

        Pysakki alku = verkko.getPysakit()[5];
        Pysakki loppu = verkko.getPysakit()[17];

        start = System.currentTimeMillis();
        System.out.println("Etsitään ratkaisua ");
        AStar aStar = new AStar(verkko, reittiLaskin);
        aStar.setDebugMode(true);   // koko jono: true, vain ratkaisuun asti: false
        aStar.setDebugPrint(false);
        Reitti reitti = aStar.etsiReitti(alku, loppu);
        System.out.println("" + reitti);
        System.out.println("" + aStar.getRatkaisu());
        stop = System.currentTimeMillis();
        System.out.println("Etsintä: " + (stop - start) + "ms");
        System.out.println("" + aStar.getHeuristiikanOnnistumiset());
    }

    /**
     * LinkitettyLista debuggaus
     */
    private static void debugLinkitettyLista() {
        LinkitettyLista<String> lista = new LinkitettyLista(8);
        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"}; // koko: 6, pitäisi siis kasvattaa
        for (String s : lista) {
            System.out.println("" + s);
        }
        System.out.println("" + lista.size());
        for (String s : e) {
            lista.add(s);
        }
        lista.add("huu");
        lista.add("haa");
        for (String s : lista) {
            System.out.println("" + s);
        }
        System.out.println("" + lista.size());
        lista.remove(0);
        System.out.println("" + lista.getHead());
        System.out.println("" + lista.getTail());
        System.out.println("" + lista.size());
        System.out.println("" + Arrays.toString(lista.getValues()));
        // System.out.println(""+lista.seuraavaTyhja());
        System.out.println("" + lista.contains(e[1]));
        System.out.println("" + lista.contains("huu"));
        System.out.println("" + lista.indexOf("huu"));
        System.out.println("" + lista.indexOf(e[1]));
        System.out.println("");
        lista.add("Moro");
        System.out.println("" + lista.getHead());
        System.out.println("" + lista.getTail());
        System.out.println("" + lista.size());
        System.out.println("" + Arrays.toString(lista.getValues()));
        // System.out.println(""+lista.seuraavaTyhja());
        System.out.println("" + lista.contains(e[1]));
        System.out.println("" + lista.contains("Moro"));
        System.out.println("" + lista.indexOf("Moro"));
        System.out.println("" + lista.indexOf(e[1]));
        System.out.println("");

        lista.remove("Moro");
        lista.remove(5);
        lista.remove(lista.size() - 1);
        System.out.println("" + lista.getHead());
        System.out.println("" + lista.getTail());
        System.out.println("" + lista.size());
        System.out.println("" + Arrays.toString(lista.getValues()));
        // System.out.println(""+lista.seuraavaTyhja());
        System.out.println("" + lista.contains(e[1]));
        System.out.println("" + lista.contains("Moro"));
        System.out.println("" + lista.indexOf("Moro"));
        System.out.println("" + lista.indexOf(e[1]));
        System.out.println("");

        while (!lista.isEmpty()) {
            String string = lista.poll();
            System.out.println("" + string);
        }
        System.out.println("" + lista.getHead());
        System.out.println("" + lista.getTail());
        System.out.println("" + lista.size());
        System.out.println("" + Arrays.toString(lista.getValues()));
        // System.out.println(""+lista.seuraavaTyhja());
        System.out.println("" + lista.contains(e[1]));
        System.out.println("" + lista.contains("Moro"));
        System.out.println("" + lista.indexOf("Moro"));
        System.out.println("" + lista.indexOf(e[1]));
        System.out.println("");

    }

    /**
     * Dynaamisen listan debuggaus
     */
    private static void debugLista() {
        DynaaminenLista<String> lista = new DynaaminenLista(5);
        System.out.println("" + lista.size());
        for (String s : lista) {
            System.out.println("" + s);
        }
        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"}; // koko: 6, pitäisi siis kasvattaa
        for (String s : e) {
            lista.add(s);
        }

        //System.out.println(""+Arrays.toString(lista.getValues()));
        for (String s : lista) {
            System.out.println("" + s);
        }
        System.out.println("" + lista.size());
        lista.remove(2);
        for (String s : lista) {
            System.out.println("" + s);
        }
        System.out.println("" + lista.size());
    }
}
