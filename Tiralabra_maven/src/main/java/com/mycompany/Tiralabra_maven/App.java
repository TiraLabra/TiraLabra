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

//        String fileNameBegining = args[0];
//        int lahtotiedostojenLkm = Integer.parseInt(args[1]);

//        String fileNameBegining = "D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TiraHT\\testing\\monimutkaisuus00";
//        int lahtotiedostojenLkm = 1;

//        String fileNameBegining = "D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TiraHT\\testing\\400x250kartta00";
//        int lahtotiedostojenLkm = 1;

        String fileNameBegining = "D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TiraHT\\testing\\800x500kartta00";
        int lahtotiedostojenLkm = 3;

//        String fileNameBegining = "D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TiraHT\\testing\\1000x625kartta00";
//        int lahtotiedostojenLkm = 1;




//        String fileNameBegining = "D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TiraHT\\testing\\tosiISOmonimutkaisuus00";
//        int lahtotiedostojenLkm = 1;

//        String inputFileName = "bitmaps/lahto256.bmp";
//        String inputFileName = "bitmaps/pienitestikartta256.bmp";
//        String inputFileName = "bitmaps/testikartta256.bmp";

        String inputFileName = "NOfile";
        KuvanLukija kuvanLukija = new KuvanLukija();
        KuvanKirjoittaja kuvanKirjoittaja = new KuvanKirjoittaja();
        OmaPinoAlkionaPaikka etaisyysAlkuunLaskettuPaikatPinoDijkstra;
        OmaPinoAlkionaPaikka reittiPinoDijkstra;
        OmaPinoAlkionaPaikka etaisyysAlkuunLaskettuPaikatPinoAstar;
        OmaPinoAlkionaPaikka reittiPinoAstar;
        long aikaAlussa;
        long aikaLopussa;
        long aikaAlustus;
        boolean minunKeko;
        int[][] kuvataulukko;
        DijkstraWithHeap ratkaisijaDijkstra;
        AstarWithHeap ratkaisijaAstar;
        long ratkaisuAikaDijkstra = 1000000;
        long ratkaisuAikaAstar = 1000000;
        long pieninRatkaisuAikaDijkstra = 1000000;
        long pieninRatkaisuAikaAstar = 1000000;
        int polunPituusDijkstra = 1000000;
        int polunPituusAstar = 1000000;

//        DijkstraWithHeap FIRSTratkaisijaDijkstra;
//        AstarWithHeap FIRSTratkaisijaAstar;
//        long FIRSTratkaisuAikaDijkstra = -10;
//        long FIRSTratkaisuAikaAstar = -10;
//        int FIRSTpolunPituusDijkstra = -10;
//        int FIRSTpolunPituusAstar = -10;


//        int i = 2;

        for (Integer i = 1; i <= lahtotiedostojenLkm; i++) {


            System.out.println("");

            pieninRatkaisuAikaDijkstra = 1000000;
            pieninRatkaisuAikaAstar = 1000000;


            inputFileName = fileNameBegining + Integer.toString(i) + ".bmp";

            kuvataulukko = kuvanLukija.seeBMPImage(inputFileName);

            minunKeko = true;

            for (int j = 0; j < 5; j++) {


//            FIRSTratkaisijaDijkstra = new DijkstraWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);
//            FIRSTratkaisijaAstar = new AstarWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);

                ratkaisijaDijkstra = new DijkstraWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);
                ratkaisijaAstar = new AstarWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);

//            for (int k = 0; k < 1000000; k++) {
//                aikaAlustus = System.currentTimeMillis();
//            }

//            aikaAlussa = System.currentTimeMillis();
//            FIRSTpolunPituusAstar = FIRSTratkaisijaAstar.ratkaise();
//            aikaLopussa = System.currentTimeMillis();
//            FIRSTratkaisuAikaAstar = aikaLopussa - aikaAlussa;
//
//            aikaAlussa = System.currentTimeMillis();
//            FIRSTpolunPituusDijkstra = FIRSTratkaisijaDijkstra.ratkaise();
//            aikaLopussa = System.currentTimeMillis();
//            FIRSTratkaisuAikaDijkstra = aikaLopussa - aikaAlussa;

//            ratkaisijaAstar.initialiseAstar();
                aikaAlussa = System.currentTimeMillis();
                polunPituusAstar = ratkaisijaAstar.ratkaise();
                aikaLopussa = System.currentTimeMillis();
                ratkaisuAikaAstar = aikaLopussa - aikaAlussa;
                if (ratkaisuAikaAstar < pieninRatkaisuAikaAstar) {
                    pieninRatkaisuAikaAstar = ratkaisuAikaAstar;
                }

                etaisyysAlkuunLaskettuPaikatPinoAstar = ratkaisijaAstar.kaydytPaikat();
                reittiPinoAstar = ratkaisijaAstar.shortestPath();
                kuvanKirjoittaja.writeImage(inputFileName, "ASTAR", etaisyysAlkuunLaskettuPaikatPinoAstar, reittiPinoAstar);

//            ratkaisijaDijkstra.initialiseSingleSource();
                aikaAlussa = System.currentTimeMillis();
                polunPituusDijkstra = ratkaisijaDijkstra.ratkaise();
                aikaLopussa = System.currentTimeMillis();
                ratkaisuAikaDijkstra = aikaLopussa - aikaAlussa;
                if (ratkaisuAikaDijkstra < pieninRatkaisuAikaDijkstra) {
                    pieninRatkaisuAikaDijkstra = ratkaisuAikaDijkstra;
                }

                etaisyysAlkuunLaskettuPaikatPinoDijkstra = ratkaisijaDijkstra.kaydytPaikat();
                reittiPinoDijkstra = ratkaisijaDijkstra.shortestPath();
                kuvanKirjoittaja.writeImage(inputFileName, "DIJKSTRA", etaisyysAlkuunLaskettuPaikatPinoDijkstra, reittiPinoDijkstra);

////            System.out.println("");
////            System.out.println("ratkaisu=algoritmien ratkaise() metodin suoritusaika (ms)");
////            System.out.println("polunpituus=polun kulkemiseen kaytetty aika (aikayksikkoa)");
////            System.out.println("");
////
//                System.out.print(String.format("%-100s", "Lahtotoedosto"));
//                System.out.print(String.format("%-30s", "ratkaisu (ms)"));
//                System.out.print(String.format("%-30s", "polunpituus (aikayksikkoa)"));
//                System.out.println("");
//
//                System.out.print(String.format("%-100s", ""));
//                System.out.print(String.format("%-15s", "Astar"));
//                System.out.print(String.format("%-15s", "Dijkstra"));
//                System.out.print(String.format("%-15s", "Astar"));
//                System.out.print(String.format("%-15s", "Dijkstra"));
//                System.out.println("");
//
//                System.out.print(String.format("%-100s", inputFileName));
//                System.out.print(String.format("%-15d", ratkaisuAikaAstar));
//                System.out.print(String.format("%-15d", ratkaisuAikaDijkstra));
//                System.out.print(String.format("%-15d", polunPituusAstar));
//                System.out.print(String.format("%-15d", polunPituusDijkstra));
//                System.out.println("");

            }

            System.out.println("");
            System.out.println("-------------------------------------------------------------------------------------------");


            System.out.print(String.format("%-100s", "Lahtotoedosto"));
            System.out.print(String.format("%-30s", "PIENIN ratkaisuaika (ms)"));
            System.out.print(String.format("%-30s", "polunpituus (aikayksikkoa)"));
            System.out.println("");

            System.out.print(String.format("%-100s", ""));
            System.out.print(String.format("%-15s", "Astar"));
            System.out.print(String.format("%-15s", "Dijkstra"));
            System.out.print(String.format("%-15s", "Astar"));
            System.out.print(String.format("%-15s", "Dijkstra"));
            System.out.println("");

            System.out.print(String.format("%-100s", inputFileName));
            System.out.print(String.format("%-15d", pieninRatkaisuAikaAstar));
            System.out.print(String.format("%-15d", pieninRatkaisuAikaDijkstra));
            System.out.print(String.format("%-15d", polunPituusAstar));
            System.out.print(String.format("%-15d", polunPituusDijkstra));
            System.out.println("");

        }




    }
}
