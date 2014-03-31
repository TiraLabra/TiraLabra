/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Sorters.*;
import java.util.Random;

/**
 *
 * @author nkostiai
 */
public class Kayttoliittyma {

    private Random randomizer;
    private int[] smallArray;
    private int[] mediumArray;
    private int[] largeArray;
    private MergeSorter mergeSorter;
    private IntroSorter introSorter;
    private SmoothSorter smoothSorter;

    public Kayttoliittyma() {
        randomizer = new Random();
        smallArray = new int[1000];
        mediumArray = new int[100000];
        largeArray = new int[10000000];
        mergeSorter = new MergeSorter();
        introSorter = new IntroSorter();
        smoothSorter = new SmoothSorter();
    }

    public void kaynnista() {
        System.out.println("Muodostetaan taulukot...");
        muodostaTaulukot();
        System.out.println("Järjestetään pieni taulukko...");
        jarjestaTaulukko(smallArray);
        System.out.println("Järjestetään keskikokoinen taulukko...");
        jarjestaTaulukko(mediumArray);
        System.out.println("Järjestetään suuri taulukko...");
        jarjestaTaulukko(largeArray);
    }

    public void jarjestaTaulukko(int[] jarjestettava) {
        muodostaTaulukot();
        System.out.println("Mergesort...");
        jarjestaMergesortilla(jarjestettava);
        muodostaTaulukot();
        System.out.println("Introsort...");
        jarjestaIntrosortilla(jarjestettava);
        muodostaTaulukot();
        System.out.println("Smoothsort...");
        jarjestaSmoothsortilla(jarjestettava);
    }

    private void jarjestaMergesortilla(int[] jarjestettava) {
        long aloitus = System.nanoTime();
        mergeSorter.mergeSort(jarjestettava);
        long lopetus = System.nanoTime();
        System.out.println("Mergesortilla aikaa meni: " + ((lopetus - aloitus) / 1000000) + "ms");

    }

    private void jarjestaSmoothsortilla(int[] jarjestettava) {
        long aloitus = System.nanoTime();
        smoothSorter.smoothSort(jarjestettava);
        long lopetus = System.nanoTime();
        System.out.println("Smoothsortilla aikaa meni: " + ((lopetus - aloitus) / 1000000) + "ms");

    }
    
    
    private void jarjestaIntrosortilla(int[] jarjestettava) {
        long aloitus = System.nanoTime();
        introSorter.introSort(jarjestettava);
        long lopetus = System.nanoTime();
        System.out.println("Introsortilla aikaa meni: " + ((lopetus - aloitus) / 1000000) + "ms");

    }

    private void muodostaTaulukot() {
        for (int i = 0; i < smallArray.length; i++) {
            smallArray[i] = randomizer.nextInt(smallArray.length);
        }
        for (int i = 0; i < mediumArray.length; i++) {
            mediumArray[i] = randomizer.nextInt(mediumArray.length);
        }
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = randomizer.nextInt(largeArray.length);
        }
    }

}
