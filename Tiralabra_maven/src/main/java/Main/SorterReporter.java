/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Sorters.IntroSorter;
import Sorters.MergeSorter;
import Sorters.SmoothSorter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Luokka luo syötetaulukot järjestysalgoritmeille ja niiden perusteella generoi
 * algoritmien suorituksesta HTML -muotoisen raportin.
 *
 * @author Admin
 */
public class SorterReporter {

    String filePath;
    String report;
    String currentType;
    String header;
    String footer;
    IntroSorter introSort;
    MergeSorter mergeSort;
    SmoothSorter smoothSort;
    Random randomizer;

    public SorterReporter(String filePath) {
        this.filePath = filePath;
        this.report = "";
        currentType = "random";
        this.randomizer = new Random();
        introSort = new IntroSorter();
        mergeSort = new MergeSorter();
        smoothSort = new SmoothSorter();
        initHTML();
    }

    private void initHTML() {
        header = "<html>\n"
                + "    <head>\n"
                + "        <title></title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width\">\n"
                + "		<style>\n"
                + "table,th,td\n"
                + "{\n"
                + "border:1px solid black;\n"
                + "}\n"
                + "</style>\n"
                + "    </head>\n"
                + "    <body>";
        footer = "</body>\n"
                + "</html>";
    }

    public void testSorters() {
        initReport();
        doIntroSort();
        doMergeSort();
        doSmoothSort();

        this.report += "</table><br>Järjestyksessä olevat taulukot";
        this.currentType = "ordered";
        sortOut();

        this.report += "</table><br>Käänteisessä järjestyksessä olevat taulukot";
        this.currentType = "reversed";
        sortOut();

        this.report += "</table><br>Kaikki samannumeroiset";
        this.currentType = "allsame;";
        sortOut();

        this.report += footer;

    }

    private void sortOut() {
        this.report += "<table><tr><td>Koko</td>";
        for (int i = 1000000; i <= 10000000; i = i + 1000000) {
            this.report += "<th>" + i + "</th>";
        }
        this.report += "</tr>\n";
        doIntroSort();
        doMergeSort();
        doSmoothSort();
    }

    private void doIntroSort() {
        this.report += "<tr><td>Aika (Introsort/ms)</td>";
        for (int i = 1000000; i <= 10000000; i = i + 1000000) {
            int[] arrayToSort = getArray(i);
            long startTime = System.nanoTime();
            introSort.introSort(arrayToSort);
            long stopTime = System.nanoTime();
            long finalTime = (stopTime - startTime) / 1000000;
            report += "<td>" + finalTime + "</td>";
        }
        this.report += "</tr>";
    }

    private void doMergeSort() {
        this.report += "<tr><td>Aika (Mergesort/ms)</td>";
        for (int i = 1000000; i <= 10000000; i = i + 1000000) {
            int[] arrayToSort = getArray(i);
            long startTime = System.nanoTime();
            mergeSort.mergeSort(arrayToSort);
            long stopTime = System.nanoTime();
            long finalTime = (stopTime - startTime) / 1000000;
            report += "<td>" + finalTime + "</td>";
        }
        this.report += "</tr>";
    }

    private void doSmoothSort() {
        this.report += "<tr>\n   <td>Aika (Smoothsort/ms)</td>\n";
        for (int i = 1000000; i <= 10000000; i = i + 1000000) {
            int[] arrayToSort = getArray(i);
            long startTime = System.nanoTime();
            smoothSort.smoothSort(arrayToSort);
            long stopTime = System.nanoTime();
            long finalTime = (stopTime - startTime) / 1000000;
            report += "   <td>" + finalTime + "</td>\n";
        }
        this.report += "</tr>";
    }

    private void initReport() {
        this.report = header;
        this.report += "Algoritmien suorituskykytestaus.\n"
                + "Satunnaistaulukot:";
        this.report += "<table><tr><td>Koko</td>";
        for (int i = 1000000; i <= 10000000; i = i + 1000000) {
            this.report += "<th>" + i + "</th>";
        }
        this.report += "</tr>\n";
    }

    public void exportReport() throws IOException {
        PrintWriter kirjoittaja = new PrintWriter(filePath, "UTF-8");
        kirjoittaja.write(report);
        kirjoittaja.close();
    }

    private int[] getArray(int length) {
        if (currentType.equals("random")) {
            return getRandomArray(length);
        } else if (currentType.equals("ordered")) {
            return getOrderedArray(length);
        } else if (currentType.equals("reversed")) {
            return getReversedArray(length);
        } else {
            return getAllSamesArray(length);
        }
    }

    private int[] getOrderedArray(int koko) {
        int[] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = i;
        }
        return uusi;
    }

    private int[] getAllSamesArray(int koko) {
        int[] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = 3;
        }
        return uusi;
    }

    private int[] getRandomArray(int koko) {
        int[] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = randomizer.nextInt(uusi.length);
        }
        return uusi;
    }

    private int[] getReversedArray(int koko) {
        int[] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = koko - i;
        }
        return uusi;
    }
}
