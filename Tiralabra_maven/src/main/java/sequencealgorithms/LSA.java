/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Local Sequence Alignment. Finds an alignment that has the longest absolute
 * match.
 *
 * @author riha
 */
public class LSA {

    private char[] input1, input2;
    private AlignmentMatrix m;
    private ScoringMatrix s;
    private double bestScore;
    private int bestScoreX, bestScoreY;
    private char[][] solution;

    public LSA(String filename) {
        char[][] input = InputReader.readInput(filename);
        input1 = input[0];
        input2 = input[1];
        char[] alphabet = {'a', 't', 'c', 'g'};

        m = new AlignmentMatrix(input1.length, input2.length);
        s = new ScoringMatrix(alphabet);
        s.setMatchBonus(1);
        s.setIndelPenalty(-1);
        s.setMismatchPenalty(-5);

        bestScore = 0;
        bestScoreX = 0;
        bestScoreY = 0;
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
                if (max < 0) {
                    max = 0;
                    path = -1;
                }
                if (max > bestScore) {
                    bestScore = max;
                    bestScoreX = i;
                    bestScoreY = j;
                }
                m.setScore(i, j, max);
                m.setPath(i, j, path);
            }
        }
    }

    public void printAlignmentMatrix() {
        m.print();
    }

    public void findSolution() {

        solution = new char[2][input1.length + input2.length];
        if (bestScoreX > bestScoreY) {
            for (int i = 0; i < input1.length; i++) {
                solution[0][i] = input1[i];
            }
            int n = bestScoreX - bestScoreY;
            for (int i = 0; i < input2.length; i++) {
                solution[1][i + n] = input2[i];
            }
        } else {
            for (int i = 0; i < input2.length; i++) {
                solution[1][i] = input2[i];
            }
            int n = bestScoreY - bestScoreX;
            for (int i = 0; i < input1.length; i++) {
                solution[0][i + n] = input1[i];
            }
        }

        for (int i = 0; i < solution[0].length; i++) {
            if (solution[0][i] == '\u0000') {
                if (solution[1][i] == '\u0000') {
                    break;
                } else {
                    solution[0][i] = '-';
                }
            } else {
                if (solution[1][i] == '\u0000') {
                    solution[1][i] = '-';
                } else {
                    // convert to uppercase
                }
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
