package algorithms.real;

/**
 * Implements the algorithm for computing the LU(P) decomposition of a matrix.
 * Source: Cormen, Leiserson, Rivest, Stein: Introduction to Algorithms (3rd
 * edition)
 *
 * @author ydna
 */
public class LUDecomposition {

    private double[][] lu;
    private int[] pivot;
    private final int n;
    private int pivotSign;
    private boolean singular;
    private final static double EPSILON = 1e-11;

    /**
     * Computes the LU decomposition.
     *
     * @param array The matrix as a double array.
     */
    public LUDecomposition(double[][] array) {
        n = array.length;
        if (n != array[0].length) {
            throw new IllegalArgumentException("Matrix is not square.");
        }
        lu = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lu[i][j] = array[i][j];
            }
        }
        pivot = new int[n];
        for (int i = 0; i < n; i++) {
            pivot[i] = i;
        }
        pivotSign = 1;
        singular = false;
        for (int k = 0; k < n; k++) {
            int p = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(lu[i][k]) > Math.abs(lu[p][k])) {
                    p = i;
                }
            }
            if (Math.abs(lu[p][k]) < EPSILON) {
                singular = true;
                return;
            }
            if (p != k) {
                for (int i = 0; i < n; i++) {
                    double temp = lu[p][i];
                    lu[p][i] = lu[k][i];
                    lu[k][i] = temp;
                }
                int temp = pivot[p];
                pivot[p] = pivot[k];
                pivot[k] = temp;
                pivotSign = -pivotSign;
            }
            for (int i = k + 1; i < n; i++) {
                lu[i][k] /= lu[k][k];
                for (int j = k + 1; j < n; j++) {
                    lu[i][j] -= lu[i][k] * lu[k][j];
                }
            }
        }
    }

    /**
     * Calculates the determinant of the matrix.
     *
     * @return Determinant.
     */
    public double determinant() {
        if (singular) {
            return 0;
        }
        double det = (double) pivotSign;
        for (int i = 0; i < n; i++) {
            det *= lu[i][i];
        }
        return det;
    }

    /**
     * Solves A*X=B using forward and back substitution.
     *
     * @param array
     * @return X
     */
    public double[][] solve(double[][] array) {
        if (singular) {
            throw new IllegalArgumentException("Matrix is singular.");
        }
        int m = array[0].length;
        double[][] result = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = array[pivot[i]][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    result[j][k] -= result[i][k] * lu[j][i];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < array[0].length; j++) {
                result[i][j] /= lu[i][i];
            }
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < array[0].length; k++) {
                    result[j][k] -= result[i][k] * lu[j][i];
                }
            }
        }
        return result;
    }

}
