package algorithms.real;

/**
 * Implements the Strassen algorithm for fast matrix multiplication. Source:
 * Cormen, Leiserson, Rivest, Stein: Introduction to Algorithms (3rd edition)
 *
 * @author ydna
 */
public class Strassen {

    private final static int LEAF_SIZE = 64;

    /**
     * Adds two matrices.
     *
     * @param A First matrix.
     * @param B Second matrix.
     * @return Sum of the first and second matrix.
     */
    private static double[][] add(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    /**
     * Subtracts two matrices.
     *
     * @param A First matrix.
     * @param B Second matrix.
     * @return Difference between the first and second matrix.
     */
    private static double[][] subtract(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    /**
     * Multiplies two matrices using the naïve algorithm.
     *
     * @param A First matrix.
     * @param B Second matrix.
     * @return Product of the first and second matrix.
     */
    private static double[][] multiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        return C;
    }

    /**
     * Multiplies two matrices using Strassen's algorithm.
     *
     * @param first First matrix.
     * @param second Second matrix.
     * @return Product of the first and second matrix.
     */
    public static double[][] strassen(double[][] first, double[][] second) {
        int length = first.length;
        int n = (int) Math.pow(2, Math.ceil(Math.log(length) / Math.log(2)));
        double[][] A = new double[n][n];
        double[][] B = new double[n][n];
        double[][] C = new double[n][n];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                A[i][j] = first[i][j];
                B[i][j] = second[i][j];
            }
        }
        if (n <= LEAF_SIZE) {
            return multiply(A, B);
        } else {
            /* 1. Divide the input matrices A and B into n/2 x n/2 submatrices */
            int size = n / 2;
            double[][] A11 = new double[size][size];
            double[][] A12 = new double[size][size];
            double[][] A21 = new double[size][size];
            double[][] A22 = new double[size][size];
            double[][] B11 = new double[size][size];
            double[][] B12 = new double[size][size];
            double[][] B21 = new double[size][size];
            double[][] B22 = new double[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    A11[i][j] = A[i][j];
                    A12[i][j] = A[i][j + size];
                    A21[i][j] = A[i + size][j];
                    A22[i][j] = A[i + size][j + size];
                    B11[i][j] = B[i][j];
                    B12[i][j] = B[i][j + size];
                    B21[i][j] = B[i + size][j];
                    B22[i][j] = B[i + size][j + size];
                }
            }
            /* 2. Create 10 matrices, each of which is the sum or difference of two matrices created in step 1 */
            double[][] S1 = subtract(B12, B22);
            double[][] S2 = add(A11, A12);
            double[][] S3 = add(A21, A22);
            double[][] S4 = subtract(B21, B11);
            double[][] S5 = add(A11, A22);
            double[][] S6 = add(B11, B22);
            double[][] S7 = subtract(A12, A22);
            double[][] S8 = add(B21, B22);
            double[][] S9 = subtract(A11, A21);
            double[][] S10 = add(B11, B12);
            /* 3. Using the matrices created in steps 1 and 2, recursively compute seven matrix products */
            double[][] P1 = strassen(A11, S1);
            double[][] P2 = strassen(S2, B22);
            double[][] P3 = strassen(S3, B11);
            double[][] P4 = strassen(A22, S4);
            double[][] P5 = strassen(S5, S6);
            double[][] P6 = strassen(S7, S8);
            double[][] P7 = strassen(S9, S10);
            /* 4. Compute the submatrices of the result matrix C by adding and substracting matrices created in step 3 */
            double[][] C11 = add(subtract(add(P5, P4), P2), P6);
            double[][] C12 = add(P1, P2);
            double[][] C21 = add(P3, P4);
            double[][] C22 = subtract(subtract(add(P5, P1), P3), P7);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    C[i][j] = C11[i][j];
                    C[i][j + size] = C12[i][j];
                    C[i + size][j] = C21[i][j];
                    C[i + size][j + size] = C22[i][j];
                }
            }
        }
        double[][] temp = new double[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[i][j] = C[i][j];
            }
        }
        return temp;
    }

}
