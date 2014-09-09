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
        int iteraatioidenLkm = Integer.parseInt(args[2]);

//        String fileNameBegining = "D:\\Hannun_tiedot\\opinnot\\2014_kesa\\TiraHT\\testing\\800x500kartta00";
//        int lahtotiedostojenLkm = 3;
//        int iteraatioidenLkm = 2;

        String inputFileName;
        KuvanLukija kuvanLukija = new KuvanLukija();
        KuvanKirjoittaja kuvanKirjoittaja = new KuvanKirjoittaja();

        OmaPinoAlkionaPaikka kaydytPaikatAstar;
        OmaPinoAlkionaPaikka reittiAstar;
        OmaPinoAlkionaPaikka kaydytPaikatDijkstra;
        OmaPinoAlkionaPaikka reittiDijkstra;

        long aikaAlussa;
        long aikaLopussa;

        /**
         * kun minunKeko=true käytetään omaa tietorakenteen minimikeko
         * toteutusta
         */
        boolean minunKeko;
        int[][] kuvataulukko;

        AstarWithHeap ratkaisijaAstar;
        DijkstraWithHeap ratkaisijaDijkstra;

        long ratkaisuAikaAstar;
        long ratkaisuAikaDijkstra;

        long pieninRatkaisuAikaAstar;
        long pieninRatkaisuAikaDijkstra;

        int polunPituusAstar = 1000000;
        int polunPituusDijkstra = 1000000;

        long ratkaisuAikojenSummaAstar;
        long ratkaisuAikojenSummaDijkstra;

        int iteraatioitaKeskiarvossaAstar;
        int iteraatioitaKeskiarvossaDijkstra;

        int vaakaResoluutio;
        int pystyResoluutio;

        System.out.println("");
        System.out.print(String.format("%-30s", "Lahtotiedosto"));
        System.out.print(String.format("%-20s", "Kuvan koko"));
        System.out.print(String.format("%-60s", "KESKIMAARAINEN ratkaisuaika (ms)"));
        System.out.print(String.format("%-30s", "PIENIN ratkaisuaika (ms)"));
        System.out.print(String.format("%-30s", "polunpituus (aikayksikkoa)"));
        System.out.println("");

        System.out.print(String.format("%-30s", ""));
        System.out.print(String.format("%-10s", "vaaka"));
        System.out.print(String.format("%-10s", "pysty"));
        System.out.print(String.format("%-15s", "Astar/aika"));
        System.out.print(String.format("%-15s", "Astar/n "));
        System.out.print(String.format("%-15s", "Dijkstra/aika"));
        System.out.print(String.format("%-15s", "Dijkstra/n"));
        System.out.print(String.format("%-15s", "Astar"));
        System.out.print(String.format("%-15s", "Dijkstra"));
        System.out.print(String.format("%-15s", "Astar"));
        System.out.print(String.format("%-15s", "Dijkstra"));
        System.out.println("");

        for (Integer i = 1; i <= lahtotiedostojenLkm; i++) {

            pieninRatkaisuAikaAstar = 1000000;
            pieninRatkaisuAikaDijkstra = 1000000;

            iteraatioitaKeskiarvossaAstar = 0;
            iteraatioitaKeskiarvossaDijkstra = 0;

            ratkaisuAikojenSummaAstar = 0;
            ratkaisuAikojenSummaDijkstra = 0;

            inputFileName = fileNameBegining + Integer.toString(i) + ".bmp";

            kuvataulukko = kuvanLukija.seeBMPImage(inputFileName);

            vaakaResoluutio = kuvataulukko.length;
            pystyResoluutio = kuvataulukko[0].length;

            minunKeko = true;

            for (int j = 0; j < iteraatioidenLkm; j++) {

                ratkaisijaDijkstra = new DijkstraWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);
                ratkaisijaAstar = new AstarWithHeap(kuvataulukko, kuvanLukija.getLahtoPiste(), kuvanLukija.getMaaliPiste(), minunKeko);

                aikaAlussa = System.currentTimeMillis();
                polunPituusAstar = ratkaisijaAstar.ratkaise();
                aikaLopussa = System.currentTimeMillis();
                ratkaisuAikaAstar = aikaLopussa - aikaAlussa;
                if (ratkaisuAikaAstar < pieninRatkaisuAikaAstar) {
                    pieninRatkaisuAikaAstar = ratkaisuAikaAstar;
                }
                if (j > iteraatioidenLkm / 5) {
                    iteraatioitaKeskiarvossaAstar++;
                    ratkaisuAikojenSummaAstar += ratkaisuAikaAstar;

                }

                aikaAlussa = System.currentTimeMillis();
                polunPituusDijkstra = ratkaisijaDijkstra.ratkaise();
                aikaLopussa = System.currentTimeMillis();
                ratkaisuAikaDijkstra = aikaLopussa - aikaAlussa;
                if (ratkaisuAikaDijkstra < pieninRatkaisuAikaDijkstra) {
                    pieninRatkaisuAikaDijkstra = ratkaisuAikaDijkstra;
                }
                if (j > iteraatioidenLkm / 5) {
                    iteraatioitaKeskiarvossaDijkstra++;
                    ratkaisuAikojenSummaDijkstra += ratkaisuAikaDijkstra;

                }

                if (j == iteraatioidenLkm - 1) {
                    kaydytPaikatAstar = ratkaisijaAstar.kaydytPaikat();
                    reittiAstar = ratkaisijaAstar.shortestPath();
                    kuvanKirjoittaja.writeImage(inputFileName, "ASTAR", kaydytPaikatAstar, reittiAstar);

                    kaydytPaikatDijkstra = ratkaisijaDijkstra.kaydytPaikat();
                    reittiDijkstra = ratkaisijaDijkstra.shortestPath();
                    kuvanKirjoittaja.writeImage(inputFileName, "DIJKSTRA", kaydytPaikatDijkstra, reittiDijkstra);
                }

            }

            System.out.print(String.format("%-30s", inputFileName));
            System.out.print(String.format("%-10d", vaakaResoluutio));
            System.out.print(String.format("%-10d", pystyResoluutio));
            if (iteraatioitaKeskiarvossaAstar > 0) {
                System.out.print(String.format("%-15d", ratkaisuAikojenSummaAstar / iteraatioitaKeskiarvossaAstar));
                System.out.print(String.format("%-15d", iteraatioitaKeskiarvossaAstar));
            } else {
                System.out.print(String.format("%-15s", "-"));
                System.out.print(String.format("%-15s", "-"));
            }
            if (iteraatioitaKeskiarvossaDijkstra > 0) {
                System.out.print(String.format("%-15d", ratkaisuAikojenSummaDijkstra / iteraatioitaKeskiarvossaDijkstra));
                System.out.print(String.format("%-15d", iteraatioitaKeskiarvossaDijkstra));
            } else {
                System.out.print(String.format("%-15s", "-"));
                System.out.print(String.format("%-15s", "-"));
            }
            System.out.print(String.format("%-15d", pieninRatkaisuAikaAstar));
            System.out.print(String.format("%-15d", pieninRatkaisuAikaDijkstra));
            System.out.print(String.format("%-15d", polunPituusAstar));
            System.out.print(String.format("%-15d", polunPituusDijkstra));
            System.out.println("");
        }
    }
}
