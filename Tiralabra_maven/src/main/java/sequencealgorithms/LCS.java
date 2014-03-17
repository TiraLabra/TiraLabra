/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

/**
 * Longest Common Subsequence problem. Takes two strings as input and produces
 * one of the longest subsequences.
 *
 * @author riha
 */
public class LCS {

    private char[] input1, input2;
    private ScoringMatrix s;
    private AlignmentMatrix m;

    public LCS(String inputFilename) {

        String[] input = {"atctgat", "tgcata"}; // read from file
        input1 = input[0].toCharArray();

        input2 = input[1].toCharArray();

        char[] alphabet = {'a', 't', 'g', 'c'};
        s = new ScoringMatrix(alphabet);

        s.setIndelPenalty(0);
        s.setMatchBonus(1);
        s.setMismatchPenalty(Double.NEGATIVE_INFINITY);

        m = new AlignmentMatrix(input1.length, input2.length);

        m.print();
    }

    public void calculateAlignment() {

        System.out.println("calculating alignment matrix");

        for (int i = 1; i < input1.length + 1; i++) {

            System.out.println("calculating i=" + i);

            for (int j = 1; j < input2.length + 1; j++) {

                System.out.println("calculating j=" + j);
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
        System.out.println("calculated");
        m.print();
    }

    public void printSolution() {
        int i = input1.length, j = input2.length;
        int suurempi = i;
        if (j > i) {
            suurempi = j;
        }
        char[] solution = new char[suurempi];
        int k = 0;
        while (i > 0 && j > 0) {
            int step = m.getPath(i, j);
            if (step == 0) {
                solution[k] = input1[i - 1];
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
        
        for (int l = solution.length - 1; l >= 0; l--) {
            System.out.print(solution[l]);
        }
        System.out.println("");

    }

}
