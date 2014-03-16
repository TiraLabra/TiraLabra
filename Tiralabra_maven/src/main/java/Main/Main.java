/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Sorters.MergeSorter;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Setting up...");
        Random randomizer = new Random();
        MergeSorter mergesorter = new MergeSorter();
        int[] smallArray = new int[1000];
        int[] mediumArray = new int[100000];
        int[] largeArray = new int[10000000];
        for(int i = 0; i < smallArray.length; i++){
            smallArray[i] = randomizer.nextInt(smallArray.length);
        }
        for(int i = 0; i < mediumArray.length; i++){
            mediumArray[i] = randomizer.nextInt(mediumArray.length);
        }
        for(int i = 0; i < largeArray.length; i++){
            largeArray[i] = randomizer.nextInt(largeArray.length);
        }
        
        long smallStartTime = System.nanoTime();
        smallArray = mergesorter.mergeSort(smallArray);
        long smallStopTime = System.nanoTime();
        long elapsedS = smallStopTime - smallStartTime;
        System.out.println("Small array: "+(elapsedS/1000000)+"ms");
        long mediumStartTime = System.nanoTime();
        mergesorter.mergeSort(mediumArray);
        long mediumStopTime = System.nanoTime();
        long elapsedM = mediumStopTime - mediumStartTime;
        System.out.println("Medium array: "+(elapsedM/1000000)+"ms");
        long largeStartTime = System.nanoTime();
        mergesorter.mergeSort(largeArray);
        long largeStopTime = System.nanoTime();
        long elapsedL = largeStopTime - largeStartTime;
        System.out.println("Large array: "+(elapsedL/1000000)+"ms");
    }

}
