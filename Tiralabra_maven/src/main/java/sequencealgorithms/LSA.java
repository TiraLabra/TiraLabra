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
public class LSA extends PSA implements Problem {

    public LSA(String filename, char[] alphabet) {
        super(filename, alphabet);
    }

    public void setUpScoringMatrix() {
        s.setMatchBonus(2);
        s.setIndelPenalty(-1);
        s.setMismatchPenalty(-1);
    }

    /**
     * Extends the superclass method by adding the option zero to all score
     * possibilities.
     *
     * @param i, j The Coordinates of the target entry.
     * @return A double array consisting of the possible scores
     */
    @Override
    public double[] possibleScores(int i, int j) {
        double[] scores = new double[4];
        scores[0] = m.get(i - 1, j - 1) + s.getScore(input1[i - 1], input2[j - 1]);
        scores[1] = m.get(i - 1, j) + s.getScore('-', input1[i - 1]);
        scores[2] = m.get(i, j - 1) + s.getScore(input2[j - 1], '-');
        scores[3] = 0;
        return scores;

    }

//    public void findSolution() {
//        char[][] preSolution = new char[2][input1.length + input2.length];
//        int p = m.getBestScoreX();
//        int q = m.getBestScoreY();
//        int length = 0;
//        while (solutionContinueCondition(p, q)) {
//            switch (m.getPath(p, q)) {
//                case 0:
//                    preSolution[0][length] = input1[p - 1];
//                    preSolution[1][length] = input2[q - 1];
//                    p--;
//                    q--;
//                    break;
//                case 1:
//                    preSolution[0][length] = input1[p - 1];
//                    preSolution[1][length] = '-';
//                    p--;
//                    break;
//                case 2:
//                    preSolution[0][length] = '-';
//                    preSolution[1][length] = input2[q - 1];
//                    q--;
//                    break;
//            }
//            length++;
//        }
//
//        setSolution(preSolution, length);
//    }
    
    @Override
    public int findSolutionStartX() {
        return m.getBestScoreX();
    }
    
    @Override
    public int findSolutionStartY() {
        return m.getBestScoreY();
    }

    @Override
    public boolean solutionContinueCondition(int p, int q) {
        return (m.get(p, q) > 0);
    }

    @Override
    public void setSolution(char[][] preSolution, int length) {
        solution = new char[2][length];
        for (int i = 0; i < length; i++) {
            solution[0][i] = preSolution[0][length - i - 1];
            solution[1][i] = preSolution[1][length - i - 1];
        }

    }

    /**
     * Debug method
     *
     * @param p
     */
    private void print(char[][] p) {
        System.out.println("PRESOLUTION:");
        for (int i = 0; i < p[0].length; i++) {
            System.out.print(p[0][i]);
        }
        System.out.println("");
        for (int i = 0; i < p[1].length; i++) {
            System.out.print(p[1][i]);
        }
        System.out.println("");
    }

}
