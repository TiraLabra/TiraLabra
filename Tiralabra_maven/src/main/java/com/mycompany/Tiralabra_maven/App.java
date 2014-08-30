package com.mycompany.Tiralabra_maven;

import com.mycompany.Tiralabra_maven.logiikka.aStar.AstarWithHeap;
import com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot.KuvanKirjoittaja;
import com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot.KuvanLukija;
import com.mycompany.Tiralabra_maven.logiikka.dijkstra.DijkstraWithHeap;
import com.mycompany.Tiralabra_maven.logiikka.paikkaPino.OmaPinoAlkionaPaikka;

/**
 * Pääluokka, joka on ohjelman ajettava luokka. SIISTImistä riittää vielä (ja
 * mahdollisesti käyttöliittymän erottaminen, jos sellainen tulee).
 */
public class App {

    public static void main(String[] args) {

        KuvanLukija kuvanLukija = new KuvanLukija();
        KuvanKirjoittaja kuvanKirjoittaja = new KuvanKirjoittaja();
//        String inputFileName = "bitmaps/lahto256.bmp";
//        String inputFileName = "bitmaps/pienitestikartta256.bmp";
        String inputFileName = "bitmaps/testikartta256.bmp";
        OmaPinoAlkionaPaikka etaisyysAlkuunLaskettuPaikatPinoDijkstra;
        OmaPinoAlkionaPaikka reittiPinoDijkstra;
        OmaPinoAlkionaPaikka etaisyysAlkuunLaskettuPaikatPinoAstar;
        OmaPinoAlkionaPaikka reittiPinoAstar;
        long aikaAlussa;
        long aikaLopussa;
        long ratkaisuAikaDijkstra;
        long ratkaisuAikaAstar;

        int[][] kuvataulukko = kuvanLukija.seeBMPImage(inputFileName);

        DijkstraWithHeap ratkaisijaDijkstra = new DijkstraWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste());
        AstarWithHeap ratkaisijaAstar = new AstarWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste());

        aikaAlussa = System.currentTimeMillis();
        int polunPituusDijkstra = ratkaisijaDijkstra.ratkaise();
        aikaLopussa = System.currentTimeMillis();
        ratkaisuAikaDijkstra = aikaLopussa - aikaAlussa;

        aikaAlussa = System.currentTimeMillis();
        int polunPituusAstar = ratkaisijaAstar.ratkaise();
        aikaLopussa = System.currentTimeMillis();
        ratkaisuAikaAstar = aikaLopussa - aikaAlussa;

        etaisyysAlkuunLaskettuPaikatPinoDijkstra = ratkaisijaDijkstra.kaydytPaikat();
        reittiPinoDijkstra = ratkaisijaDijkstra.shortestPath();
        kuvanKirjoittaja.writeImage(inputFileName, "DIJKSTRA", etaisyysAlkuunLaskettuPaikatPinoDijkstra, reittiPinoDijkstra);

        etaisyysAlkuunLaskettuPaikatPinoAstar = ratkaisijaAstar.kaydytPaikat();
        reittiPinoAstar = ratkaisijaAstar.shortestPath();
        kuvanKirjoittaja.writeImage(inputFileName, "ASTAR", etaisyysAlkuunLaskettuPaikatPinoAstar, reittiPinoAstar);

        System.out.println("Dijkstra: polun pituus=" + polunPituusDijkstra + ", ratkaisuun kului aikaa " + ratkaisuAikaDijkstra + " ms.");
        System.out.println("Astar: polun pituus=" + polunPituusAstar + ", ratkaisuun kului aikaa " + ratkaisuAikaAstar + " ms.");

    }
}
