/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import java.util.Scanner;
import sequencealgorithms.GSA;
import sequencealgorithms.GSAwGap;
import sequencealgorithms.LCS;
import sequencealgorithms.LSA;

/**
 *
 * @author riha
 */
public class SeqAlgUI {

    Scanner reader;

    public SeqAlgUI() {
        System.out.println("Sequence Algorithms");
        reader = new Scanner(System.in);
    }

    public void run() {
        main:
        while (true) {
            printMenu();
            switch (readSelection()) {
                case 1:
                    runLCS();
                    break;
                case 2:
                    runGSA();
                    break;
                case 3:
                    runLSA();
                    break;
                case 4:
                    runGSAwGap();
                    break;
                case 0:
                    System.out.println("Quitting");
                    break main;
                default:
                    System.out.println("Please enter one of the choices:");
                    break;
            }
        }

    }

    public void runLCS() {
        System.out.println("Enter input file name:");
        LCS lcs = new LCS(readFilename());
        lcs.calculateAlignment();
        lcs.findSolution();
        System.out.println("A longest common subsequence:");
        printSolution(lcs.getSolution());

    }

    public void runGSA() {
        System.out.println("Enter input file name:");
//        char[] alphabet = {'a', 't', 'c', 'g'};
        GSA gsa = new GSA(readFilename());
        gsa.calculateAlignment();
        gsa.findSolution();
        System.out.println("Best matching global sequence alignment:");
        printSolution(gsa.getSolution());
    }

    public void runGSAwGap() {
        System.out.println("Enter input file name:");
        GSAwGap gsawg = new GSAwGap(readFilename());
        gsawg.calculateAlignment();
        gsawg.findSolution();
        System.out.println("Best matching global sequence alignment with gap penalties:");
        printSolution(gsawg.getSolution());
    }

    public void runLSA() {
        System.out.println("Enter input file name:");
        char[] alphabet = {'a', 't', 'c', 'g'};
        LSA lsa = new LSA(readFilename());
        lsa.calculateAlignment();
        lsa.findSolution();
        System.out.println("An alignment with best local match sequence:");
        printSolution(lsa.getSolution());
    }

    private void printSolution(char[][] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                System.out.print(solution[i][j]);
            }
            System.out.println("");
        }
    }

    public void printMenu() {
        System.out.println("1 - Longest Common Substring");
        System.out.println("2 - Global Sequence Alignment");
        System.out.println("3 - Local Sequence Alignment");
        System.out.println("4 - Global Sequence Alignment with gap penalties");
        System.out.println("");
        System.out.println("0 - Quit");
    }

    public int readSelection() {
        int selection = reader.nextInt();
        reader.nextLine();
        return selection;
    }

    public String readFilename() {
        String filename = reader.nextLine();
        return filename;
    }
    
}
