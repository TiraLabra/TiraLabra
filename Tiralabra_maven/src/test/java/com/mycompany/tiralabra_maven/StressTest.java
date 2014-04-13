/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import sequencealgorithms.GSA;
import sequencealgorithms.GSAwGap;
import sequencealgorithms.LCS;
import sequencealgorithms.LSA;
import sequencealgorithms.Problem;

/**
 *
 * @author riha
 */
public class StressTest {

    private static final String NAME_PREFIX = "stressTestFile";

    /**
     * Program to run a series of stress tests.
     * 
     * @param args 
     */
    public static void main(String[] args) {

        int numOfTests = 20;
        int lenOfSeqs = 1000;
        int numOfRepeats = 10;
        int selector = 3;
        System.out.println("Executing " + numOfTests + " stress tests, each with two inputs of length incrementing by " + lenOfSeqs + " with each file.");
        generateTestFiles(numOfTests, lenOfSeqs);
        System.out.println("Files generated, beginning stress test");

        for (int i = 0; i < numOfTests; i++) {
            System.out.println("Test number " + (i + 1));
            Problem p = new LCS(NAME_PREFIX + "0");
            p.solve();
            long[] results = new long[0];

            switch (selector) {
                case 1:
                    p = new LCS(NAME_PREFIX + i);
                    results = solveProblem(p, numOfRepeats);
                    System.out.println("LCS Avarage solve time: " + Math.round(avg(results)));
                    System.out.println("LCS Solve time standard deviation: " + Math.round(dev(results)));
                    break;
                case 2:
                    p = new GSA(NAME_PREFIX + i);
                    p.setUpScoring(5, -3, -1, 0);
                    results = solveProblem(p, numOfRepeats);
                    System.out.println("GSA Avarage solve time: " + Math.round(avg(results)));
                    System.out.println("GSA Solve time standard deviation: " + Math.round(dev(results)));
                    break;
                case 3:
                    p = new LSA(NAME_PREFIX + i);
                    p.setUpScoring(5, -3, -1, 0);
                    results = solveProblem(p, numOfRepeats);
                    System.out.println("LSA Avarage solve time: " + Math.round(avg(results)));
                    System.out.println("LSA Solve time standard deviation: " + Math.round(dev(results)));
                    break;
                case 4:
                    p = new GSAwGap(NAME_PREFIX + i);
                    p.setUpScoring(7, -3, -5, -1);
                    results = solveProblem(p, numOfRepeats);
                    System.out.println("GSA with Gap Avarage solve time: " + Math.round(avg(results)));
                    System.out.println("GSA with Gap Solve time standard deviation: " + Math.round(dev(results)));
                    break;

            }
            System.out.println("");

        }

        System.out.println("Tests done");
        deleteTestFiles();

    }

    /**
     * Solves given problem given number of times and returns an array of run times.
     * 
     * @param p Problem to be solved
     * @param repeat Number of repeats
     * @return 
     */
    private static long[] solveProblem(Problem p, int repeat) {
        long times[] = new long[repeat];
        System.out.print("Repeat ");
        for (int i = 0; i < repeat; i++) {
            System.out.print(i + " ");
            Long startTime = System.currentTimeMillis();
            p.solve();
            Long endTime = System.currentTimeMillis();
            times[i] = endTime - startTime;
        }
        System.out.println("");
        return times;
    }

    /**
     * Calculates the avarage of given array of long-type numbers.
     * 
     * @param nums
     * @return 
     */
    private static double avg(long[] nums) {
        long sum = 0;
        for (long num : nums) {
            sum += num;
        }
        return sum / nums.length;
    }

    /**
     * Calculates the standard deviation of given array of long-type numbers.
     * 
     * @param nums
     * @return 
     */
    private static double dev(long[] nums) {
        double avg = avg(nums);
        double sum = 0;
        for (long num : nums) {
            sum += (avg - num) * (avg - num);
        }
        return Math.sqrt(sum / nums.length);
    }

    /**
     * Generates test files with random input.
     * 
     * @param numberOfFiles Number of files to be generated
     * @param lengthIncrement The Difference between number of characters in each individual file. 
     */
    private static void generateTestFiles(int numberOfFiles, int lengthIncrement) {
        char[] alphabet = {'a', 'c', 't', 'g'};
        Random r = new Random();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < numberOfFiles; i++) {
            for (int j = 0; j < lengthIncrement; j++) {
                sb1.append(alphabet[r.nextInt(4)]);
                sb2.append(alphabet[r.nextInt(4)]);
            }

            writeFile(sb1.toString() + "\n" + sb2.toString(), NAME_PREFIX + i);
        }
    }

    /**
     * Writes parameter string to a file.
     * 
     * @param stringToWrite
     * @param filename 
     */
    private static void writeFile(String stringToWrite, String filename) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "utf-8"));
            writer.write(stringToWrite);
        } catch (IOException ex) {
            System.out.println("Could not write file (StressTest, writeFile) " + ex);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Deletes all the files that contain the filename prefix defined in NAME_PREFIX
    */
    private static void deleteTestFiles() {
        File[] files = new File(".").listFiles();
        for (File file : files) {
            if (file.toString().contains(NAME_PREFIX)) {
                file.delete();
            }
        }
    }

}
