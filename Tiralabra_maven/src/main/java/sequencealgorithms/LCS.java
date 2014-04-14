package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Longest Common Subsequence problem. Takes two strings as input and produces
 * one of the longest subsequences.
 *
 * @author Jari Haavisto
 */
public class LCS extends PSA {

    public LCS(String filename) {
        super(filename);
        s.setUp(1, -1E100, 0);
    }

//    public void setUpScoringMatrix() {
//        s.setUp(1, -1E100, 0);  // -1E300 ~ negative infinity
//    }

    /**
     * LCS algorithm uses constant scoring and hence does not use this method.
     * 
     * @param matchBonus
     * @param mismatchPenalty
     * @param indelPenalty 
     */
    @Override
    public void setUpScoring(double matchBonus, double mismatchPenalty, double indelPenalty, double GapPenalty) {}
    
    @Override
    protected int findSolutionStartX() {
        return input1.length;
    }

    @Override
    protected int findSolutionStartY() {
        return input2.length;
    }

    @Override
    protected boolean solutionContinueCondition(int p, int q) {
        return (p > 0 && q > 0);
    }

    @Override
    protected void setSolution(char[][] preSolution, int length) {
        int actualLength = 0;
        for (int i = 0; i<length; i++) {
            if (preSolution[0][i] == preSolution[1][i]) {
                actualLength++;
            }
        }
        solution = new char[2][actualLength];
        int i = 0;
        while (length > 0) {
            length--;
            if (preSolution[0][length] == preSolution[1][length]) {
                solution[0][i] = preSolution[0][length];
                i++;
            }
        }
    }

}
