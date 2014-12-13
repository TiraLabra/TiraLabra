package com.mycompany.tiralabra_maven;

import haku.AStar;
import haku.Laskin;
import verkko.Reitti;
import haku.ReittiLaskin;
import haku.SatunnainenLaskin;
import java.util.Scanner;
import tira.LinkitettyLista;
import tira.DynaaminenLista;
import tira.Hajautuslista;
import tira.Hajautustaulu;
import tira.Lista;
import verkko.Linja;
import verkko.Pysakki;
import verkko.Verkko;
import verkko.VerkkoOmallaTietorakenteella;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;
import verkko.satunnainen.SatunnainenVerkko;

/**
 * Käynnistysluokka, tässä luokassa myös staattinen merkkipohjainen käyttöliittymä. 
 * Debug-metodeja myös.
 *
 */
public class App {

    /**
     * Heuristiikan kÃ¤yttÃ¤mÃ¤ tieto keskinopeudesta. Jos arvo on liian pieni,
     * heuristiikka yliarvioi matkojen aikakustannuksia. Jos arvo liian suuri,
     * aliarvioidaan & joudutaan laskemaan ylimÃ¤Ã¤rÃ¤isiÃ¤ reittejÃ¤
     */
    public static double sporanNopeus = 526; // pistettÃ¤/min
    /**
     * Leveyssuuntainen haku tÃ¤llÃ¤ laskimella: heuristiikan arvo aina nolla
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
     * Vaihtoja vÃ¤lttelevÃ¤ laskin
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
        // debugOmia();
        // debugHajautuslista();
        // debugSatunnainenVerkko();
        // yksinkertainenTestiUI();
        // Verkko verkko = new Verkko();
        // System.out.println("" + verkko.getPysakit().length);
        // ui();
        new Gui();
        
    }

    //////////////
    // TESTI-UI //
    //////////////
    
    /**
     * KÃ¤yttÃ¶liittymÃ¤n valinta
     */
    public static void ui() {
        System.out.println("TESTATTAVAN VERKON TYYPPI");
        System.out.println("p - PysÃ¤kkiverkko");
        System.out.println("s - Satunnainen verkko");
        Scanner scanner = new Scanner(System.in);
        String komento = scanner.nextLine();
        if (komento.contains("p")) {
            yksinkertainenTestiUIPysakit();
        } else if (komento.contains("s")) {
            yksinkertainenTestiUI();
        }
    }

    /**
     * Yksinkertainen testikÃ¤yttÃ¶liittymÃ¤ hauille pysÃ¤kkiverkosta
     */
    public static void yksinkertainenTestiUIPysakit() {
        System.out.println("REITTIHAKU");
        System.out.println("HSL RAITIOVAUNUVERKKO");
        System.out.println("alku:loppu - REITTIHAKU PYSÃ„KILTÃ„ ALKU PYSÃ„KILLE LOPPU. KOKONAISLUVUT 0...137");
        System.out.println("laskin:valinta - VALITAAN LASKIN. KOKONAISLUKU 1...6");
        Scanner scanner = new Scanner(System.in);
        Verkko verkko = new VerkkoOmallaTietorakenteella();
        Laskin laskin = normaali;
        AStar aStar = new AStar(verkko, laskin);
        do {
            String komento = scanner.nextLine();
            if (komento.contains("exit")) {
                break;
            } else if (komento.contains("laskin")) {
                if (komento.contains("1")) {
                    laskin = bfs;
                } else if (komento.contains("2")) {
                    laskin = bfsVaihdoton;
                } else if (komento.contains("3")) {
                    laskin = normaali;
                } else if (komento.contains("4")) {
                    laskin = vaihdoton;
                } else if (komento.contains("5")) {
                    laskin = normaaliMatkaaMinimoiva;
                } else if (komento.contains("6")) {
                    laskin = vaihdotonMatkaaMinimoiva;
                } else {
                    continue;
                }
                aStar = new AStar(verkko, laskin);
            } else if (komento.contains(":")) {
                String[] koordinaatit = komento.split(":");
                try {
                    Value alku = verkko.getPysakit()[Integer.parseInt(koordinaatit[0])], loppu = verkko.getPysakit()[Integer.parseInt(koordinaatit[1])];
                    long a, b, summa = 0, otos = 10, min = Integer.MAX_VALUE, max = 0;

                    Node reitti = null;
                    for (int i = 0; i < otos; i++) {
                        a = System.currentTimeMillis();
                        reitti = aStar.etsiReittiOma(alku, loppu);
                        b = System.currentTimeMillis();

                        long x = b - a;
                        if (x > max) {
                            max = x;
                        }
                        if (x < min) {
                            min = x;
                        }
                        summa += x;
                    }
                    System.out.println("Reitti=" + reitti + ", Keskiaika=" + (summa / otos)
                            + ", Otos=" + (otos)
                            + ", Min=" + (min)
                            + ", Max=" + (max));

                } catch (Exception ex) {
                    System.out.println("Haut muodossa alku:loppu");
                    // System.out.println("");
                }
            } else {
                System.out.println("Haut muodossa alku:loppu");
            }
        } while (true);
    }

    /**
     * Yksinkertainen komentorivipohjainen testikÃ¤yttÃ¶liittymÃ¤
     * satunnaisgeneroiduille verkoille
     *
     */
    public static void yksinkertainenTestiUI() {

        System.out.println("REITTIHAKU");
        System.out.println("SATUNNAINEN VERKKO");
        System.out.println("Verkossa voi kulkea diagonaalisesti");
        System.out.println("Oletuskoko 10x10");
        System.out.println("KOMENNOT");
        System.out.println("exit - LOPETUS");
        System.out.println("verkko:koko");
        System.out.println("verkko:rivit:sarakkeet");
        System.out.println("verkko:rivit:sarakkeet:minimiPaino:kerroinPaino");
        System.out.println("laskin:0 - LEVEYSSUUNTAINEN HAKU");
        System.out.println("laskin:1 - NORMAALI HEURISTIIKKA");
        System.out.println("print - TULOSTAA VERKON");
        System.out.println("x1:y1:x2:y2 - REITTIHAKU");
        Scanner scanner = new Scanner(System.in);
        SatunnainenVerkko verkko = new SatunnainenVerkko(10);
        Laskin laskin = new SatunnainenLaskin(1);
        AStar aStar = new AStar(verkko, laskin);
        long otos=10;
        do {
            String komento = scanner.nextLine();

            if (komento.contains("exit")) {
                break;
            } else if (komento.contains("verkko")) {
                verkko = prosessoiVerkko(komento);
                if (verkko != null) {
                    aStar = new AStar(verkko, laskin);
                }
            } else if (komento.contains("laskin")) {
                if (komento.contains("1")) {
                    laskin = new SatunnainenLaskin(1);
                } else {
                    laskin = new SatunnainenLaskin(0);
                }
                aStar = new AStar(verkko, laskin);
            } else if (komento.contains("print")) {
                System.out.println("VERKKO: ");
                System.out.println("" + verkko.toString());
            } else {
                String[] koordinaatit = komento.split(":");

                try {
                    int x1 = Integer.parseInt(koordinaatit[0]), y1 = Integer.parseInt(koordinaatit[1]), x2 = Integer.parseInt(koordinaatit[2]), y2 = Integer.parseInt(koordinaatit[3]);
                    Value alku = verkko.getSolmu(x1, y1), loppu = verkko.getSolmu(x2, y2);
                    hakuOtos(aStar,alku,loppu,otos,true);

                } catch (Exception ex) {
                    System.out.println("Haut muodossa x1:y1:x2:y2");
                    // System.out.println("");
                }
            }

        } while (true);

    }

    /**
     * Generoi syÃ¶tteestÃ¤ satunnaisen verkon
     *
     * @param komento
     * @return
     */
    private static SatunnainenVerkko prosessoiVerkko(String komento) {
        String[] komennot = komento.split(":");
        try {
            SatunnainenVerkko verkko = null;
            if (komennot.length == 2) {
                int koko = Integer.parseInt(komennot[1]);
                verkko = new SatunnainenVerkko(koko);
            }
            if (komennot.length == 3) {
                int rivit = Integer.parseInt(komennot[1]), sarakkeet = Integer.parseInt(komennot[2]);
                verkko = new SatunnainenVerkko(rivit, sarakkeet);
            }
            if (komennot.length == 5) {
                int rivit = Integer.parseInt(komennot[1]), sarakkeet = Integer.parseInt(komennot[2]), minimiPaino = Integer.parseInt(komennot[3]), kerroinPaino = Integer.parseInt(komennot[4]);
                verkko = new SatunnainenVerkko(rivit, sarakkeet, minimiPaino, kerroinPaino);
            }
            if (verkko != null) {
                return verkko;
            }
        } catch (Exception ex) {

        }
        System.out.println("Huono syÃ¶te");
        return null;
    }

    /**
     * Suorittaa reittihaun ja palauttaa kuluneen ajan keskiarvon
     *
     * @param aStar Haku
     * @param alku Alkusolmu
     * @param loppu Loppusolmu
     * @param otos Otoskoko
     * @param print Tulostetaanko tulokset
     * @return
     */
    private static long hakuOtos(AStar aStar, Value alku, Value loppu, long otos, boolean print) {
        long a, b, summa = 0, min = Integer.MAX_VALUE, max = 0;

        Node reitti = null;
        for (int i = 0; i < otos; i++) {
            a = System.currentTimeMillis();
            reitti = aStar.etsiReittiOma(alku, loppu);
            b = System.currentTimeMillis();

            long x = b - a;
            if (x > max) {
                max = x;
            }
            if (x < min) {
                min = x;
            }
            summa += x;
        }
        long keskiaika = (summa / otos);
        if (print) System.out.println("Reitti=" + reitti + ", Keskiaika=" + (keskiaika)
                + ", Otos=" + (otos)
                + ", Min=" + (min)
                + ", Max=" + (max));
        return keskiaika;
    }
    
    
    ///////////////////
    // DEBUG-METODIT //
    ///////////////////

    /**
     * AStar-heuristiikan debuggaus pysäkkiverkossa
     */
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
    /**
     * Vertailee oman ja javan prioriteettijonojen toimintaa
     */
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
        System.out.println("EtsitÃ¤Ã¤n ratkaisua ");
        AStar aStar = new AStar(verkko, reittiLaskin);
        aStar.setDebugMode(true);   // koko jono: true, vain ratkaisuun asti: false
        aStar.setDebugPrint(false);
        Reitti reitti = (Reitti) aStar.etsiReitti(alku, loppu);
        System.out.println("" + reitti);
        System.out.println("" + aStar.getRatkaisu());
        stop = System.currentTimeMillis();
        System.out.println("EtsintÃ¤: " + (stop - start) + "ms");
        // System.out.println("" + aStar.getHeuristiikanOnnistumiset());
    }

    /**
     * LinkitettyLista debuggaus
     */
    private static void debugLinkitettyLista() {
        LinkitettyLista<String> lista = new LinkitettyLista(8);
        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"}; // koko: 6, pitÃ¤isi siis kasvattaa
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
        String[] e = {"Kissa", "Koira", "Lassi", "Leevi", "Mimmi", "Mummi"}; // koko: 6, pitÃ¤isi siis kasvattaa
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
    /**
     * Omilla tietorakenteilla tehdyn pysäkkiverkon hakutuloksia 
     */
    private static void debugOmia() {
        System.out.println("Omat vs javan tietorakenteet kÃ¤ytÃ¶ssÃ¤");
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
    /**
     * Hajautuslistan debuggausta
     */
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

    /**
     * Debugataan satunnaista verkkoa
     */
    private static void debugSatunnainenVerkko() {
        System.out.println("SATUNNAINEN VERKKO...");
        SatunnainenLaskin laskin = new SatunnainenLaskin();
        int n = 100;
        int rivit = n, sarakkeet = n;
        SatunnainenVerkko verkko = new SatunnainenVerkko(rivit, sarakkeet, 1, 10);
        Value alku = verkko.getSolmu(0, 0), loppu = verkko.getSolmu(rivit - 1, sarakkeet - 1);
        long a, b, summa = 0, min = Long.MAX_VALUE, max = 0, otos = 10;
        AStar aStar = new AStar(verkko, laskin);
        // aStar.setDebugMode(true);

        Node reitti = null;
        for (int i = 0; i < otos; i++) {
            a = System.currentTimeMillis();
            reitti = aStar.etsiReittiOma(alku, loppu);
            b = System.currentTimeMillis();

            long x = b - a;
            if (x > max) {
                max = x;
            }
            if (x < min) {
                min = x;
            }
            summa += x;
        }
        System.out.println("Verkon koko=" + rivit + "X" + sarakkeet
                + ", Keskiaika=" + (summa / otos)
                + ", Otos=" + (otos)
                + ", Min=" + (min)
                + ", Max=" + (max)
                + ""
        );
        System.out.println("" + reitti);
        
        // Polku p = (Polku) reitti;
        
        // System.out.println(""+reitti);
        System.out.println("" + aStar.getRatkaisu());
    }
}
