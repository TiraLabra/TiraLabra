package com.mycompany.tiralabra_maven;

import sequencealgorithms.LCS;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        LCS lcs = new LCS("filename");
        lcs.calculateAlignment();
        lcs.printSolution();
    }
}
