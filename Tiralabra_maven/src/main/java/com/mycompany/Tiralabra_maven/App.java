package com.mycompany.Tiralabra_maven;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import com.mycompany.Tiralabra_maven.logiikka.Piste;
import com.mycompany.Tiralabra_maven.logiikka.aStar.AstarWithHeap;
import com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot.KuvanKirjoittaja;
import com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot.KuvanLukija;
import com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot.XXXX;
import com.mycompany.Tiralabra_maven.logiikka.dijkstra.DijkstraWithHeap;
import com.mycompany.Tiralabra_maven.logiikka.paikkaPino.OmaPinoAlkionaPaikka;
import com.mycompany.Tiralabra_maven.logiikka.testausta.OmaKekoEtyyppiTestausta;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.PriorityQueue;
//import java.util.Stack;
import javax.imageio.ImageIO;

/**
 * Pääluokka, joka on ohjelman ajettava luokka. SIISTImistä riittää vielä (ja
 * mahdollisesti käyttöliittymän erottaminen, jos sellainen tulee).
 */
public class App {

    /**
     * Metodi ALUSTAVAN/KESKENRÄISEN kuvanlukijan testaamiseen.
     */
    public static void testaaXXXX() {
        XXXX kuvanlukija = new XXXX();

//        System.out.println(kulje(ruudukko, lahtoRuutu, maaliRuutu));
        System.out.println("kukkuu");
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE / 2 * 2);
        try {
            kuvanlukija.seeBMPImage("bitmaps/uusiTesti256.bmp");
        } catch (IOException ioe) {
            System.out.println("virhe");
        }
    }

    /**
     * Metodi ALUSTAVAN/KESKENRÄISEN kuvanlukijan testaamiseen.
     */
    public static void testaaKuvanLukija() {

        KuvanLukija kuvanLukija = new KuvanLukija();
        KuvanKirjoittaja kuvanKirjoittaja = new KuvanKirjoittaja();
        String inputFileName = "bitmaps/uusiTesti256.bmp";


////        System.out.println(kulje(ruudukko, lahtoRuutu, maaliRuutu));
//        System.out.println("kukkuu");
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE / 2 * 2);
        int[][] kuvataulukko = kuvanLukija.seeBMPImage(inputFileName);

//        kuvanKirjoittaja.writeImage(inputFileName, kuvataulukko, PINO!!!!!!!!!!);

    }

    public static void main(String[] args) {
//        System.out.println("main");
////        testaaXXXX();
//
//        KuvanLukija kuvanlukija = new KuvanLukija();
//
//        try {
//            int[][] lahtotietoTaulukko = kuvanlukija.seeBMPImage("bitmaps/uusiTesti256.bmp");
//        } catch (IOException ioe) {
//            System.out.println("virhe");
//        }

//        testaaKuvanLukija();



////    E on este, jonka yli/ali/läpi ei pääse
//
//        int e = Integer.MAX_VALUE / 10;
//        int[][] kartta = new int[][]{
//            {1, 1, 1, 1, 1},
//            {1, e, e, e, 1},
//            {1, 1, 1, e, 1},
//            {1, 1, 1, 1, 1}
//        };
//
//        Piste lahtoPiste = new Piste(2, 1);
////        Piste maaliPiste = new Piste(1, 4);
////        Piste maaliPiste = new Piste(2, 2);
//        Piste maaliPiste = new Piste(0, 2);
////        Piste maaliPiste = new Piste(2, 1);


        KuvanLukija kuvanLukija = new KuvanLukija();
        KuvanKirjoittaja kuvanKirjoittaja = new KuvanKirjoittaja();
        String inputFileName = "bitmaps/lahto256.bmp";


        int[][] kuvataulukko = kuvanLukija.seeBMPImage(inputFileName);


        DijkstraWithHeap ratkaisija = new DijkstraWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste());
//        AstarWithHeap ratkaisija = new AstarWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste());

        System.out.println(ratkaisija.ratkaise());

        OmaPinoAlkionaPaikka kaydytPaikatPino = ratkaisija.kaydytPaikat();
        OmaPinoAlkionaPaikka reittiPino = ratkaisija.shortestPath();

        kuvanKirjoittaja.writeImage(inputFileName, kuvataulukko, kaydytPaikatPino,reittiPino);


//        ratkaisija.testiTulostaReittikartta(reittiPino);

//        OmaKekoEtyyppiTestausta test=new OmaKekoEtyyppiTestausta();
//        test.testing();

    }
}
