package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Global Sequence Alignment problem. Finds a best global alignment for two sequences.
 *
 * @author Jari Haavisto
 */
public class GSA extends PSA {

    public GSA(String filename) {
        super(filename);
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
