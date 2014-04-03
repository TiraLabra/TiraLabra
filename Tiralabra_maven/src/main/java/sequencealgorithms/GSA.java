package sequencealgorithms;

import com.mycompany.tiralabra_maven.InputReader;

/**
 * Global Sequence Alignment problem.
 *
 * @author riha
 */
public class GSA extends PSA implements Problem {

    public GSA(String filename, char[] alphabet) {
        super(filename, alphabet);
    }

    public void setUpScoringMatrix() {
        s.setMatchBonus(1);
        s.setIndelPenalty(-2);
        s.setMismatchPenalty(-1);
    }

//    public void findSolution() {
//        int l = input1.length + input2.length;
//        solution = new char[2][l];
//
//        int p = input1.length, q = input2.length;
//        while (p > 0 || q > 0) {
//            l--;
//            int path = m.getPath(p, q);
//            if (path == 0) {
//                solution[0][l] = input1[p - 1];
//                solution[1][l] = input2[q - 1];
//                p--;
//                q--;
//            }
//            if (path == 1) {
//                solution[0][l] = input1[p - 1];
//                solution[1][l] = '-';
//                p--;
//            }
//            if (path == 2) {
//                solution[0][l] = '-';
//                solution[1][l] = input2[q - 1];
//                q--;
//            }
//        }
//    }
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
