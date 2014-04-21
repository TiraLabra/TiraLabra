/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import Sorters.IntroSorter;
import Sorters.MergeSorter;
import Sorters.SmoothSorter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class SorterReporter {
    String filePath;
    String report;
    String currentType;
    IntroSorter introSort;
    MergeSorter mergeSort;
    SmoothSorter smoothSort;
    Random randomizer;
    
    public SorterReporter(String filePath){
        this.filePath = filePath;
        this.report = "";
        currentType = "random";
        this.randomizer = new Random();
        introSort = new IntroSorter();
        mergeSort = new MergeSorter();
        smoothSort = new SmoothSorter();
    }
    
    public void testSorters(){
        initReport();
        doIntroSort();
        doMergeSort();
        doSmoothSort();
    }
    
    private void doIntroSort(){
        report += ("-------- INTROSORT -------\n");
        for(int i = 0; i <= 10000000; i = i+ 100000){
            long startTime = System.nanoTime();
            introSort.introSort(getArray(i));
            long stopTime = System.nanoTime();
            long finalTime = (stopTime - startTime)/1000000;
            report += i+","+finalTime+"\n";
        }
    }
    
    private void doMergeSort(){
        report += ("-------- MERGESORT -------\n");
        for(int i = 0; i <= 10000000; i = i+ 100000){
            long startTime = System.nanoTime();
            mergeSort.mergeSort(getArray(i));
            long stopTime = System.nanoTime();
            long finalTime = (stopTime - startTime)/1000000;
            report += i+","+finalTime+"\n";
        }
    }
    
    private void doSmoothSort(){
        report += ("-------- SMOOTHSORT -------\n");
        for(int i = 0; i <= 10000000; i = i+ 100000){
            long startTime = System.nanoTime();
            smoothSort.smoothSort(getArray(i));
            long stopTime = System.nanoTime();
            long finalTime = (stopTime - startTime)/1000000;
            report += i+","+finalTime+"\n";
        }
    }
    
    private void initReport(){
        this.report = "";
        this.report += "Algoritmien suorituskykytestaus.\n";
        for(int i = 0; i <= 1000000; i = i+ 1000){
            this.report += "      |";
        }
        this.report += "\n";
    }
    
    public void exportReport() throws IOException{
        PrintWriter kirjoittaja = new PrintWriter(filePath, "UTF-8");
        kirjoittaja.write(report);
        kirjoittaja.close();
    }
    
    private int[] getArray(int length){
        if(currentType.equals("random")){
            return getRandomArray(length);
        }
        else if(currentType.equals("ordered")){
            return getOrderedArray(length);
        }
        else if(currentType.equals("reversed")){
            return getReversedArray(length);
        }
        else{
            return getAllSamesArray(length);
        }
    }
    
    private int[] getOrderedArray(int koko) {
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = i;
        }
        return uusi;
    }
    
    private int[] getAllSamesArray(int koko){
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = 3;
        }
        return uusi;
    }
    
    private int[] getRandomArray(int koko) {
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = randomizer.nextInt(uusi.length);
        }
        return uusi;
    }
    
    private int[] getReversedArray(int koko) {
        int [] uusi = new int[koko];
        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = koko - i;
        }
        return uusi;
    }
}
