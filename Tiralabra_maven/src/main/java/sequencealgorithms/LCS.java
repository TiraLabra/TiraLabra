package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Longest Common Subsequence problem. Takes two strings as input and produces
 * one of the longest subsequences.
 *
 * @author Jari Haavisto
 */
public class LCS extends PSA implements Problem {

    public LCS(String filename, char[] alphabet) {
        super(filename, alphabet);
    }

    public void setUpScoringMatrix() {
        s.setUp(1, -1E100, 0);  // -1E300 ~ negative infinity
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
        return (p > 0 && q > 0);
    }

    @Override
    public void setSolution(char[][] preSolution, int length) {
        solution = new char[2][length];
        int i = 0;
        while (length > 0) {
            length--;
            if (preSolution[0][length] == preSolution[1][length]) {
                System.out.println("length on " + length);
                solution[0][i] = preSolution[0][length];
                i++;
            }
        }
    }

//    /**
//     * Finds the solution to the problem based on the calculated alignment
//     * matrix. Stores the solution on variable solution[][].
//     */
//    public void findSolution() {
//
//        m.print();
//        int i = input1.length, j = input2.length;
//        int suurempi = i;
//        if (j > i) {
//            suurempi = j;
//        }
//        solution = new char[1][suurempi];
//        int k = 0;
//        while (i > 0 && j > 0) {
//            int step = m.getPath(i, j);
//            if (step == 0) {
//                solution[0][k] = input1[i - 1];
//                k++;
//                i--;
//                j--;
//            }
//            if (step == 1) {
//                i--;
//            }
//            if (step == 2) {
//                j--;
//            }
//        }
//    }
}
