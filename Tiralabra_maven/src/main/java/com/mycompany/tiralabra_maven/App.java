package com.mycompany.tiralabra_maven;

import haku.AStar;
import verkko.Reitti;
import haku.ReittiLaskin;
import tira.LinkitettyLista;
import tira.DynaaminenLista;
import tira.Hajautuslista;
import tira.Hajautustaulu;
import tira.Lista;
import verkko.Linja;
import verkko.Pysakki;
import verkko.Verkko;
import verkko.VerkkoOmallaTietorakenteella;

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
     * Leveyssuuntainen haku, vaihdoton
     */
    public static ReittiLaskin bfsVaihdoton = new ReittiLaskin(1, 0, 3, 0, 0, sporanNopeus);
    /**
     * Matka-aikaa minimoiva laskin
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
        // debugAStarVertailu();
        // debugAStarHeuristiikka();
        // debugLinkitettyLista();
        // debugLista();
        // debugHajautustaulu();
        // debugHajautustauluPysakit();
        // debugVerkkoOmilla();
        debugOmia();
        // debugHajautuslista();

    }

    private static void debugAStarHeuristiikka() {
        Verkko verkko = new Verkko();
        ReittiLaskin reittiLaskin = normaali;
        reittiLaskin.setVerkko(verkko);

        long start, stop;
        start = System.currentTimeMillis();
        System.out.println("Heuristiikan onnistumisprosentti: " + reittiLaskin.toimiikoHeuristiikka());
        stop = System.currentTimeMillis();
        System.out.println("Aika " + (stop - start) + " ms");
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
            aOma.etsiReittiOma(alku, loppu);
            stop = System.currentTimeMillis();
            timeOma += stop - start;

            start = System.currentTimeMillis();
            aJava.etsiReitti(alku, loppu);
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

        ReittiLaskin reittiLaskin = vaihdotonMatkaaMinimoiva;

        Pysakki alku = verkko.getPysakit()[5];
        Pysakki loppu = verkko.getPysakit()[17];

        start = System.currentTimeMillis();
        System.out.println("Etsitään ratkaisua ");
        AStar aStar = new AStar(verkko, reittiLaskin);
        aStar.setDebugMode(true);   // koko jono: true, vain ratkaisuun asti: false
        aStar.setDebugPrint(false);
        Reitti reitti = (Reitti) aStar.etsiReitti(alku, loppu);
        System.out.println("" + reitti);
        System.out.println("" + aStar.getRatkaisu());
        stop = System.currentTimeMillis();
        System.out.println("Etsintä: " + (stop - start) + "ms");
        // System.out.println("" + aStar.getHeuristiikanOnnistumiset());
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
        System.out.println("" + lista.size());
        // System.out.println(""+lista.seuraavaTyhja());
        System.out.println("" + lista.contains(e[1]));
        System.out.println("" + lista.contains("huu"));
        System.out.println("" + lista.indexOf("huu"));
        System.out.println("" + lista.indexOf(e[1]));
        System.out.println("");
        lista.add("Moro");
        System.out.println("" + lista.size());
        // System.out.println(""+lista.seuraavaTyhja());
        System.out.println("" + lista.contains(e[1]));
        System.out.println("" + lista.contains("Moro"));
        System.out.println("" + lista.indexOf("Moro"));
        System.out.println("" + lista.indexOf(e[1]));
        System.out.println("");

        lista.remove("Moro");
        lista.remove(5);
        lista.remove(lista.size() - 1);
        System.out.println("" + lista.size());
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
        System.out.println("" + lista.size());
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

    private static void debugHajautustaulu() {
        Hajautustaulu<String, String> ht = new Hajautustaulu();

        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"};
        int n = e.length;
        System.out.println("put");
        for (int i = 0; i < n; i++) {
            String k = e[i], v = e[n - 1 - i];
            System.out.println("" + ht.put(e[i], e[n - 1 - i]));
        }
        System.out.println("get");
        for (int i = 0; i < n; i++) {
            String k = e[i], v = e[n - 1 - i];
            System.out.println("" + ht.get(k) + " == " + v);
        }
        System.out.println("contains");
        System.out.println("??? " + ht.contains("kukkia"));
        System.out.println("??? " + ht.contains("Koira"));
        Lista<String> keySet = ht.keySet();
        for (String s : e) {
            System.out.println("ht==ks <-> " + ht.contains(s) + "==" + keySet.contains(s));
        }
        System.out.println("" + keySet.size());
        System.out.println("remove");
        for (int i = 0; i < n; i++) {
            String k = e[i], v = e[n - 1 - i];
            System.out.println("" + ht.remove(k) + " == " + v);
            // ht.remove(k);
        }

    }

    private static void debugHajautustauluPysakit() {
        Verkko verkko = new Verkko();
        Hajautustaulu<String, Pysakki> pysakit = new Hajautustaulu(290);
        for (Pysakki p : verkko.getPysakit()) {
            pysakit.put(p.getKoodi(), p);
        }
        pysakit.debugPrint();
        Hajautustaulu<String, Linja> linjat = new Hajautustaulu(17);
        for (Linja l : verkko.getLinjat()) {
            linjat.put(l.getKoodi(), l);
        }
        linjat.debugPrint();
    }

    private static void debugVerkkoOmilla() {
        System.out.println("Verkon luonti omilla tietorakenteilla vs javan");
        long a, b, j = 0, o = 0;
        int n = 1;
        for (int i = 0; i < n; i++) {
            a = System.currentTimeMillis();
            Verkko verkko = new Verkko();
            b = System.currentTimeMillis();
            j += b - a;

            a = System.currentTimeMillis();
            VerkkoOmallaTietorakenteella verkkoX = new VerkkoOmallaTietorakenteella();
            b = System.currentTimeMillis();
            o += b - a;
        }
        System.out.println("Javan aika " + j + " vs omilla tietorakenteilla " + o);
    }

    private static void debugOmia() {
        System.out.println("Omat vs javan tietorakenteet käytössä");
        long a, b, j = 0, o = 0, jOma = 0;
        int n = 20;
        Verkko verkko = new Verkko();
        VerkkoOmallaTietorakenteella verkkoX = new VerkkoOmallaTietorakenteella();

        Pysakki alku = verkko.getPysakit()[10], loppu = verkko.getPysakit()[56];

        ReittiLaskin laskin = App.vaihdotonMatkaaMinimoiva;

        AStar aJava = new AStar(verkko, new ReittiLaskin(laskin));
        AStar aJavaOmaPrioriteettijono = new AStar(verkko, new ReittiLaskin(laskin));
        AStar aOma = new AStar(verkkoX, new ReittiLaskin(laskin));

        aJava.setDebugMode(true);   
        // aJava.setDebugPrint(false);
        aOma.setDebugMode(true);  
        // aOma.setDebugPrint(false);
        aJavaOmaPrioriteettijono.setDebugMode(true);  
        // aJavaOmaPrioriteettijono.setDebugPrint(false);        
        for (int i = 0; i < n; i++) {
            a = System.currentTimeMillis();
            aJava.etsiReitti(alku, loppu);
            b = System.currentTimeMillis();
            j += b - a;
            a = System.currentTimeMillis();
            aJavaOmaPrioriteettijono.etsiReittiOma(alku, loppu);
            b = System.currentTimeMillis();
            jOma += b - a;
            a = System.currentTimeMillis();
            aOma.etsiReittiOma(alku, loppu);
            b = System.currentTimeMillis();
            o += b - a;
        }
        System.out.println("Javan aika " + (j / (n)) + " vs omilla tietorakenteilla " + (o / (n)) + " vs omalla pq:lla " + (jOma / (n)));
        System.out.println("" + aJava.getRatkaisu());
        System.out.println("" + aOma.getRatkaisu());
        System.out.println("" + aJavaOmaPrioriteettijono.getRatkaisu());
    }

    private static void debugHajautuslista() {
        System.out.println("Debug hajautuslista");
        Hajautuslista<String> ht = new Hajautuslista();

        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"};
        int n = e.length;
        System.out.println("put");
        for (int i = 0; i < n; i++) {
            String k = e[i], v = e[n - 1 - i];
            System.out.println("" + ht.add(e[i]));
        }
        System.out.println("get");
        for (int i = 0; i < n; i++) {
            String k = e[i]; //, v = e[n - 1 - i];
            System.out.println("" + ht.get(k) + " == " + k);
        }
        System.out.println("contains");
        System.out.println("??? " + ht.contains("kukkia"));
        System.out.println("??? " + ht.contains("Koira"));
        Lista<String> keySet = ht.keySet();
        for (String s : e) {
            System.out.println("ht==ks <-> " + ht.contains(s) + "==" + keySet.contains(s));
        }
        System.out.println("" + keySet.size());
        System.out.println("remove");
        for (int i = 0; i < n; i++) {
            String k = e[i];
            System.out.println("" + ht.remove(k) + " == " + k);
            // ht.remove(k);
        }
        for (int i = 0; i < n; i++) {
            String k = e[i];
            System.out.println("" + ht.remove(k) + " == " + k);
            // ht.remove(k);
        }
        keySet = ht.keySet();
        for (String s : e) {
            System.out.println("ht==ks <-> " + ht.contains(s) + "==" + keySet.contains(s));
        }
    }
}
