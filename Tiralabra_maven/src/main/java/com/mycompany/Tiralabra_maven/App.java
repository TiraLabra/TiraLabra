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

        String fileNameBegining = args[0];
        int lahtotiedostojenLkm = Integer.parseInt(args[1]);

//        String inputFileName = "bitmaps/lahto256.bmp";
//        String inputFileName = "bitmaps/pienitestikartta256.bmp";
//        String inputFileName = "bitmaps/testikartta256.bmp";

        String inputFileName;
        KuvanLukija kuvanLukija = new KuvanLukija();
        KuvanKirjoittaja kuvanKirjoittaja = new KuvanKirjoittaja();
        OmaPinoAlkionaPaikka etaisyysAlkuunLaskettuPaikatPinoDijkstra;
        OmaPinoAlkionaPaikka reittiPinoDijkstra;
        OmaPinoAlkionaPaikka etaisyysAlkuunLaskettuPaikatPinoAstar;
        OmaPinoAlkionaPaikka reittiPinoAstar;
        long aikaAlussa;
        long aikaLopussa;
        long ratkaisuAikaDijkstra;
        long ratkaisuAikaAstar;
        boolean minunKeko;
        int[][] kuvataulukko;
        DijkstraWithHeap ratkaisijaDijkstra;
        AstarWithHeap ratkaisijaAstar;
        int polunPituusDijkstra;
        int polunPituusAstar;


        for (Integer i = 1; i <= lahtotiedostojenLkm; i++) {

            inputFileName = fileNameBegining + Integer.toString(i) + ".bmp";
        
            kuvataulukko = kuvanLukija.seeBMPImage(inputFileName);

            minunKeko = true;

            ratkaisijaDijkstra = new DijkstraWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);
            ratkaisijaAstar = new AstarWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);

            aikaAlussa = System.currentTimeMillis();
            polunPituusDijkstra = ratkaisijaDijkstra.ratkaise();
            aikaLopussa = System.currentTimeMillis();
            ratkaisuAikaDijkstra = aikaLopussa - aikaAlussa;

            aikaAlussa = System.currentTimeMillis();
            polunPituusAstar = ratkaisijaAstar.ratkaise();
            aikaLopussa = System.currentTimeMillis();
            ratkaisuAikaAstar = aikaLopussa - aikaAlussa;

            etaisyysAlkuunLaskettuPaikatPinoDijkstra = ratkaisijaDijkstra.kaydytPaikat();
            reittiPinoDijkstra = ratkaisijaDijkstra.shortestPath();
            kuvanKirjoittaja.writeImage(inputFileName, "DIJKSTRA", etaisyysAlkuunLaskettuPaikatPinoDijkstra, reittiPinoDijkstra);

            etaisyysAlkuunLaskettuPaikatPinoAstar = ratkaisijaAstar.kaydytPaikat();
            reittiPinoAstar = ratkaisijaAstar.shortestPath();
            kuvanKirjoittaja.writeImage(inputFileName, "ASTAR", etaisyysAlkuunLaskettuPaikatPinoAstar, reittiPinoAstar);

//            System.out.println("");
//            System.out.println("ratkaisu=algoritmien ratkaise() metodin suoritusaika (ms)");
//            System.out.println("polunpituus=polun kulkemiseen kaytetty aika (aikayksikkoa)");
//            System.out.println("");
//
//            System.out.print(String.format("%-40s", "Lahtotoedosto"));
//            System.out.print(String.format("%-30s", "ratkaisu (ms)"));
//            System.out.print(String.format("%-30s", "polunpituus (aikayksikkoa)"));
//            System.out.println("");
//
//            System.out.print(String.format("%-40s", ""));
//            System.out.print(String.format("%-15s", "Dijkstra"));
//            System.out.print(String.format("%-15s", "Astar"));
//            System.out.print(String.format("%-15s", "Dijkstra"));
//            System.out.print(String.format("%-15s", "Astar"));
//            System.out.println("");

            System.out.print(String.format("%-40s", inputFileName));
            System.out.print(String.format("%-15d", ratkaisuAikaDijkstra));
            System.out.print(String.format("%-15d", ratkaisuAikaAstar));
            System.out.print(String.format("%-15d", polunPituusDijkstra));
            System.out.print(String.format("%-15d", polunPituusAstar));
            System.out.println("");

        }


    }
}
