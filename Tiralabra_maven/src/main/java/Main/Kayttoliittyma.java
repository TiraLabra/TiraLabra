/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Sorters.*;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nkostiai
 */
public class Kayttoliittyma {

    private Random randomizer;
    int small = 1000;
    int medium = 100000;
    int large = 10000000;
    private MergeSorter mergeSorter;
    private IntroSorter introSorter;
    private SmoothSorter smoothSorter;

    public Kayttoliittyma() {
        randomizer = new Random();
        mergeSorter = new MergeSorter();
        introSorter = new IntroSorter();
        smoothSorter = new SmoothSorter();
    }

    public void kaynnista() {
        SorterReporter reportteri = new SorterReporter("raportti.txt");
        reportteri.testSorters();
        try {
            reportteri.exportReport();
        } catch (IOException ex) {
            System.out.println("Tiedostoon kirjoittaessa tapahtui virhe");
            Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muodostaa ja järjestää annetun kokoisen satunnaistaulukon kaikilla järjestämisalgoritmeillä.
     * @param koko Haluttu koko.
     */
    public void jarjestaSatunnaisTaulukko(int koko) {
        System.out.println("Mergesort...");
        jarjestaMergesortilla(muodostaTaulukko(koko));

        System.out.println("Introsort...");
        jarjestaIntrosortilla(muodostaTaulukko(koko));

        System.out.println("Smoothsort...");
        jarjestaSmoothsortilla(muodostaTaulukko(koko));
    }
    
    /**
     * Muodostaa ja järjestää annetun kokoisen järjestyksessä olevan taulukon kaikilla järjestämisalgoritmeillä.
     * @param koko 
     */
    public void jarjestaJarjestyksessaOlevaTaulukko(int koko){
        System.out.println("Mergesort...");
        jarjestaMergesortilla(muodostaJarjTaulukko(koko));

        System.out.println("Introsort...");
        jarjestaIntrosortilla(muodostaJarjTaulukko(koko));

        System.out.println("Smoothsort...");
        jarjestaSmoothsortilla(muodostaJarjTaulukko(koko));
    }
    
    public void jarjestaKaikkiSamojaTaulukko(int koko){
        System.out.println("Mergesort...");
        jarjestaMergesortilla(muodostaKaikkiSamojaTaulukko(koko));

        System.out.println("Introsort...");
        jarjestaIntrosortilla(muodostaKaikkiSamojaTaulukko(koko));

        System.out.println("Smoothsort...");
        jarjestaSmoothsortilla(muodostaKaikkiSamojaTaulukko(koko));
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
    /**
     * 
     * Muodostaa annetun kokoisen taulukon satunnaisgeneraattorilla.
     * @param koko Taulukon koko.
     * @return Taulukko.
     */
    private int[] muodostaTaulukko(int koko) {
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = randomizer.nextInt(uusi.length);
        }
        return uusi;
    }
    /**
     * Muodostaa annetun kokoisen taulukon järjestyksessä.
     * 
     * @param koko Taulukon koko.
     * @return Taulukko.
     */
    private int[] muodostaJarjTaulukko(int koko) {
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = i;
        }
        return uusi;
    }
    
    private int[] muodostaKaikkiSamojaTaulukko(int koko){
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = 3;
        }
        return uusi;
    }

}
