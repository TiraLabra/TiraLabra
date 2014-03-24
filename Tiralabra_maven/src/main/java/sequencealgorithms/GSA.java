/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Global Sequence Alignment problem.
 *
 * @author riha
 */
public class GSA {

    private char[] input1, input2;
    private AlignmentMatrix m;
    private ScoringMatrix s;
    private char[][] solution;

    public GSA(String filename) {
        char[][] input = InputReader.readInput(filename);
        input1 = input[0];
        input2 = input[1];
        char[] alphabet = {'a', 't', 'c', 'g'};

        m = new AlignmentMatrix(input1.length, input2.length);
        s = new ScoringMatrix(alphabet);
        s.setMatchBonus(1);
        s.setIndelPenalty(-1);
        s.setMismatchPenalty(-5);
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

    private double max(double a, double b, double c) {
        if (a > b) {
            if (a > c) {
                return a;
            }
            return c;
        }
        if (b > c) {
            return b;
        }
        return c;
    }

    public void printAlignmentMatrix() {
        m.print();
    }

    public void findSolution() {
        int l = input1.length + input2.length;
        solution = new char[2][l];

        int p = input1.length, q = input2.length;
        while (p > 0 || q > 0) {
            l--;
            int path = m.getPath(p, q);
//            System.out.println("p = " + p + " q = " + q + " path = " + path);
            if (path == 0) {
                solution[0][l] = input1[p - 1];
                solution[1][l] = input2[q - 1];
                p--;
                q--;
            }
            if (path == 1) {
                solution[0][l] = input1[p - 1];
                solution[1][l] = '-';
                p--;
            }
            if (path == 2) {
                solution[0][l] = '-';
                solution[1][l] = input2[q - 1];
                q--;
            }
        }
    }
    
    public char[][] getSolution() {
        return solution;
    }

    public void printSolution() {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                System.out.print(solution[i][j]);
            }
            System.out.println();
        }
    }

}
