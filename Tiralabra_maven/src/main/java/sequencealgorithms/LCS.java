/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Longest Common Subsequence problem. Takes two strings as input and produces
 * one of the longest subsequences.
 *
 * @author Jari Haavisto
 */
public class LCS {

    private char[] input1, input2;
    private ScoringMatrix s;
    private AlignmentMatrix m;
    private char[][] solution;

    public LCS(String filename) {
        char[][] input = InputReader.readInput(filename);
        input1 = input[0];
        input2 = input[1];

        char[] alphabet = {'a', 't', 'c', 'g'};

        s = new ScoringMatrix(alphabet);
        m = new AlignmentMatrix(input1.length, input2.length);
        
        s.setUp(1, Double.NEGATIVE_INFINITY, 0);

    }

    public void calculateAlignment() {
        for (int i = 1; i < input1.length + 1; i++) {
            for (int j = 1; j < input2.length + 1; j++) {
                double d = m.get(i - 1, j - 1) + s.getScore(input1[i - 1], input2[j - 1]);
                double y = m.get(i - 1, j) + s.getScore('-', input1[i - 1]);
                double x = m.get(i, j - 1) + s.getScore(input2[j - 1], '-');

                double max = d;
                int path = 0;
                if (y > max) {
                    max = y;
                    path = 1;
                }
                if (x > max) {
                    max = x;
                    path = 2;
                }
                m.setScore(i, j, max);
                m.setPath(i, j, path);
            }
        }
    }

    public void findSolution() {
        int i = input1.length, j = input2.length;
        int suurempi = i;
        if (j > i) {
            suurempi = j;
        }
        solution = new char[1][suurempi];
        int k = 0;
        while (i > 0 && j > 0) {
            int step = m.getPath(i, j);
            if (step == 0) {
                solution[0][k] = input1[i - 1];
                k++;
                i--;
                j--;
            }
            if (step == 1) {
                i--;
            }
            if (step == 2) {
                j--;
            }
        }
    }
    
    public char[][] getSolution() {
        return solution;
    }

    public void printSolution() {
        for (int l = solution[0].length - 1; l >= 0; l--) {
            System.out.print(solution[0][l]);
        }
        System.out.println("");

    }

}
