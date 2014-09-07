package algorithms.complex;

import math.Complex;

/**
 * Implements the algorithm for computing the LU(P) decomposition of a matrix.
 * Source: Cormen, Leiserson, Rivest, Stein: Introduction to Algorithms (3rd
 * edition)
 *
 * @author ydna
 */
public class LUDecomposition {

    private Complex[][] lu;
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
    public LUDecomposition(Complex[][] array) {
        n = array.length;
        if (n != array[0].length) {
            throw new IllegalArgumentException("Matrix is not square.");
        }
        lu = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lu[i][j] = new Complex(array[i][j].re(), array[i][j].im());
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
                if (lu[i][k].abs() > lu[p][k].abs()) {
                    p = i;
                }
            }
            if (lu[p][k].abs() < EPSILON) {
                singular = true;
                return;
            }
            if (p != k) {
                for (int i = 0; i < n; i++) {
                    Complex temp = new Complex(lu[p][i].re(), lu[p][i].im());
                    lu[p][i] = new Complex(lu[k][i].re(), lu[k][i].im());
                    lu[k][i] = temp;
                }
                int temp = pivot[p];
                pivot[p] = pivot[k];
                pivot[k] = temp;
                pivotSign = -pivotSign;
            }
            for (int i = k + 1; i < n; i++) {
                lu[i][k] = lu[i][k].divide(lu[k][k]);
                for (int j = k + 1; j < n; j++) {
                    lu[i][j] = lu[i][j].subtract(lu[i][k].multiply(lu[k][j]));
                }
            }
        }
    }

    /**
     * Calculates the determinant of the matrix.
     *
     * @return Determinant.
     */
    public Complex determinant() {
        if (singular) {
            return new Complex(0);
        }
        Complex det = new Complex(pivotSign);
        for (int i = 0; i < n; i++) {
            det = det.multiply(lu[i][i]);
        }
        return det;
    }

    /**
     * Solves A*X=B using forward and back substitution.
     *
     * @param array
     * @return X
     */
    public Complex[][] solve(Complex[][] array) {
        if (singular) {
            throw new IllegalArgumentException("Matrix is singular.");
        }
        int m = array[0].length;
        Complex[][] result = new Complex[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = new Complex(array[pivot[i]][j].re(), array[pivot[i]][j].im());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    result[j][k] = result[j][k].subtract(result[i][k].multiply(lu[j][i]));
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < array[0].length; j++) {
                result[i][j] = result[i][j].divide(lu[i][i]);
            }
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < array[0].length; k++) {
                    result[j][k] = result[j][k].subtract(result[i][k].multiply(lu[j][i]));
                }
            }
        }
        return result;
    }

}
