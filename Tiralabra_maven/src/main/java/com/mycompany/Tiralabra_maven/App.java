package com.mycompany.Tiralabra_maven;

import com.mycompany.Tiralabra_maven.logiikka.Piste;
import com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot.XXXX;
import com.mycompany.Tiralabra_maven.logiikka.dijkstra.DijkstraWithHeap;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.PriorityQueue;
import javax.imageio.ImageIO;

/**
 * Pääluokka, joka on ohjelman ajettava luokka.
 * SIISTImistä riittää vielä (ja mahdollisesti käyttöliittymän erottaminen,
 * jos sellainen tulee).
 */
public class App {

    public static void testaaKuvanlukija() {
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

    public static void main(String[] args) {
        System.out.println("main");
        testaaKuvanlukija();

//    E on este, jonka yli/ali/läpi ei pääse

        int e = Integer.MAX_VALUE / 10;
        int[][] kartta = new int[][]{
            {1, 1, 1, 1, 1},
            {1, e, e, e, 1},
            {1, 1, 1, e, 1},
            {1, 1, 1, 1, 1}
        };

        Piste lahtoPiste = new Piste(2, 1);
//        Piste maaliPiste = new Piste(1, 4);
//        Piste maaliPiste = new Piste(2, 2);
        Piste maaliPiste = new Piste(0, 2);
//        Piste maaliPiste = new Piste(2, 1);

        DijkstraWithHeap dijkstraWithHeap = new DijkstraWithHeap(kartta, lahtoPiste, maaliPiste);

        System.out.println(dijkstraWithHeap.ratkaise());

        dijkstraWithHeap.shortestPath();
        
        dijkstraWithHeap.testiTulostaReittikartta();

    }
}
