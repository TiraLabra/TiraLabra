package com.mycompany.tiralabra_maven;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.PriorityQueue;
import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class App {



//    public static int kulje(int[][] ruudukko, Ruutu lahtoRuutu, Ruutu maaliRuutu) {
//        // Toteuta minut
//        // Dijkstran algoritmi
//
//        // Laitetaan tänne talteen jokaista sijaintia vastaava Paikka-olio
//        Paikka[][] paikat = new Paikka[vuori.length][vuori[0].length];
//        // Tässä prioriteettijono joka on algoritmin ydin
//        PriorityQueue<Paikka> q = new PriorityQueue<Paikka>(1, new PaikkaComparator());
//        // Lähdemme liikkeelle ylänurkasta
//        paikat[0][0] = new Paikka(0, 0, 0);
//        q.add(paikat[0][0]);
//
//        while (!q.isEmpty()) {
////            /*
//            System.out.println("--");
//            for (int i = 0; i < vuori.length; i++) {
//                for (int j = 0; j < vuori[0].length; j++) {
//                    if (paikat[i][j] == null) {
//                        System.out.print("   X ");
//                    } else {
//                        System.out.printf("%4d ", paikat[i][j].etaisyys);
//                    }
//                }
//                System.out.println();
//            }
////            */
//
//            Paikka p = q.poll();
//
//            // Käydään läpi naapurit
//            relaksoi(vuori, q, paikat, p, p.x - 1, p.y);
//            relaksoi(vuori, q, paikat, p, p.x + 1, p.y);
//            relaksoi(vuori, q, paikat, p, p.x, p.y - 1);
//            relaksoi(vuori, q, paikat, p, p.x, p.y + 1);
//        }
//
//        return paikat[vuori.length - 1][vuori[0].length - 1].etaisyys;
//    }
//
//    private static void relaksoi(int[][] vuori, PriorityQueue<Paikka> q, Paikka[][] paikat, Paikka p, int x, int y) {
//        // Ei käsitellä vuoren ulkopuolella olevia koordinaatteja
//        if (x < 0 || y < 0 || x >= paikat[0].length || y >= paikat.length) {
//            return;
//        }
//
//        int uusiE = p.etaisyys + Math.abs(vuori[p.y][p.x] - vuori[y][x]);
//
//        // Jos emme vielä ole koskaan käyneet tässä paikassa:
//        if (paikat[y][x] == null) {
//            paikat[y][x] = new Paikka(x, y, uusiE);
//            q.add(paikat[y][x]);
//            return;
//        }
//
//        // Muuten verrataan vanhaan
//        int vanhaE = paikat[y][x].etaisyys;
//        if (uusiE < vanhaE) {
//            q.remove(paikat[y][x]);
//            paikat[y][x] = new Paikka(x, y, uusiE);
//            q.add(paikat[y][x]);
//        }
//    }
//    
//    
//    
////    E on este, jonka yli/ali/läpi ei pääse
//    public static final int E = Integer.MAX_VALUE;
//    public static int[][] ruudukko = new int[][]{
//        {1, 1, 1, 1, 1},
//        {1, E, E, E, 1},
//        {1, 1, 1, E, 1},
//        {1, 1, 1, 1, 1}
//    };
//    public static Ruutu lahtoRuutu = new Ruutu(2, 2);
//    public static Ruutu maaliRuutu = new Ruutu(1, 4);
//public static void main(String[] args) {
    public static void main(String[] args) {
        
        Kuvanlukija kuvanlukija=new Kuvanlukija();
        
//        System.out.println(kulje(ruudukko, lahtoRuutu, maaliRuutu));
        System.out.println("kukkuu");
        try {
            kuvanlukija.seeBMPImage("bitmaps/testikartta006b-16-color.bmp");
//            kuvanlukija.seeBMPImage("D:\\Untitled3.bmp");
//            kuvanlukija.seeBMPImage("D:/Hannun_tiedot/opinnot/2014_kesa/TietorakenteidenJaAlgoritmienHarjoitustyo/Untitled3.bmp");
//            kuvanlukija.seeBMPImage("D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TietorakenteidenJaAlgoritmienHarjoitustyo\\Untitled3.bmp");
        } catch (IOException ioe) {
        }
    }
}
