/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

/**
 *
 * @author riha
 */
public class GSAwGap extends GSA {

    /**
     * Two matrixes to keep track the scoring of horizontal and vertical
     * movements.
     */
    private AlignmentMatrix horizontal, vertical;

    public GSAwGap(String filename) {
        super(filename);
        horizontal = new AlignmentMatrix(input1.length, input2.length);
        for (int i=0; i<input1.length; i++) {
            horizontal.setScore(i, 0, NEGINF);
        }
        vertical = new AlignmentMatrix(input1.length, input2.length);
        for (int j=0; j<input2.length; j++) {
            vertical.setScore(0, j, NEGINF);
        }
    }

//    @Override
//    public void setUpScoringMatrix() {
//        s.setMatchBonus(10);
//        s.setIndelPenalty(-15);
//        s.setMismatchPenalty(-2);
//        s.setGapPenalty(-7);
//    }

    @Override
    public double[] possibleScores(int i, int j) {
        double[] scores = new double[3];
        scores[0] = m.get(i - 1, j - 1) + s.getScore(input1[i - 1], input2[j - 1]);
        scores[1] = verticalScore(i, j);
        scores[2] = horizontalScore(i, j);
        return scores;

    }

    /**
     * Calculates the score for the vertical movement matrix, sets the
     * calculated score and returns the result.
     *
     * @param i
     * @param j
     * @return
     */
    private double verticalScore(int i, int j) {
        double extendGapScore = vertical.get(i - 1, j) + s.getGapPenalty();
        double openGapScore = m.get(i - 1, j) + s.getScore('-', input1[i - 1]) + s.getGapPenalty();
        double betterScore = max(extendGapScore, openGapScore);
        vertical.setScore(i, j, betterScore);
        return betterScore;
    }

    /**
     * Calculates the score for the horizontal movement matrix, sets the
     * calculated score and returns the result.
     *
     * @param i
     * @param j
     * @return
     */
    private double horizontalScore(int i, int j) {
        double extendGapScore = horizontal.get(i, j - 1) + s.getGapPenalty();
        double openGapScore = m.get(i, j - 1) + s.getScore('-', input1[i - 1]) + s.getGapPenalty();
        double betterScore = max(extendGapScore, openGapScore);
        horizontal.setScore(i, j, betterScore);
        return betterScore;
    }

    /**
     * Returns the larger of two given doubles.
     * 
     * @param a 
     * @param b 
     * @return  
     */
    private double max(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    }

}
