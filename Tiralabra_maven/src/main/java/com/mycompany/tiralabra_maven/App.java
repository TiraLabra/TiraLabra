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

    public static void main(String[] args) {

        debugAStar();
        // debugLinkitettyLista();
        // debugLista();

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

        ReittiLaskin bfs = new ReittiLaskin(1, 0, 0, 0, 0, 400); // A*-hausta saadaan leveyssuuntainen haku: heuristiikan arvo == 0 aina
        ReittiLaskin normaali = new ReittiLaskin(1, 0, 0, 1, 0, 400); // Tavallinen, vain matka-aikaa laskeva haku
        ReittiLaskin vaihdoton = new ReittiLaskin(1, 0, 3, 1, 0, 400); // Minimoi vaihtojen määrää
        ReittiLaskin reittiLaskin = vaihdoton;

        Pysakki alku = verkko.getPysakit()[5];
        Pysakki loppu = verkko.getPysakit()[17];

        start = System.currentTimeMillis();
        System.out.println("Etsitään ratkaisua ");
        AStar aStar = new AStar(verkko, reittiLaskin);
        aStar.setDebugMode(false);   // koko jono: true, vain ratkaisuun asti: false
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
        for ( String s : lista ) {
            System.out.println(""+s);
        } 
        System.out.println(""+lista.size());
        for (String s : e) {
            lista.add(s);
        }        
        lista.add("huu");
        lista.add("haa");
        for ( String s : lista ) {
            System.out.println(""+s);
        }        
        System.out.println(""+lista.size());
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
        System.out.println(""+lista.size());
        for ( String s : lista ) {
            System.out.println(""+s);
        }        
        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"}; // koko: 6, pitäisi siis kasvattaa
        for (String s : e) {
            lista.add(s);
        }
        
        //System.out.println(""+Arrays.toString(lista.getValues()));
        
        for ( String s : lista ) {
            System.out.println(""+s);
        }
        System.out.println(""+lista.size());
        lista.remove(2);
        for ( String s : lista ) {
            System.out.println(""+s);
        }        
        System.out.println(""+lista.size());
    }
}
