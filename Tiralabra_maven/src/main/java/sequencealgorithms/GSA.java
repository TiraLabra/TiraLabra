package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Global Sequence Alignment problem.
 *
 * @author riha
 */
public class GSA extends PSA implements Problem {

    public GSA(String filename) {
        super(filename);
    }

    public void setUpScoringMatrix() {
        s.setMatchBonus(1);
        s.setIndelPenalty(-2);
        s.setMismatchPenalty(-1);
    }

    @Override
    public int findSolutionStartX() {
        return input1.length;
    }

    @Override
    public int findSolutionStartY() {
        return input2.length;
    }

    @Override
    public boolean solutionContinueCondition(int p, int q) {
        return (p > 0 || q > 0);
    }

    @Override
    public void setSolution(char[][] preSolution, int length) {
        solution = new char[2][length];
        for (int i = 0; i < length; i++) {
            solution[0][i] = preSolution[0][length - i - 1];
            solution[1][i] = preSolution[1][length - i - 1];
        }

    }

}
