package com.mycompany.tiralabra_maven.performance_test;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Measures the performance of the algorithms in the MatrixMath class.
 *
 * @see MatrixMath
 * @author gabriel
 */
public class Test {

    /**
     * The PrintWriter used for saving data to file.
     */
    private PrintWriter out;
    /**
     * The random number generator.
     */
    private Random random;

    /**
     * The first matrix used in testing matrix operations.
     */
    private Matrix matrixA;
    /**
     * The second matrix used in testing matrix operations.
     */
    private Matrix matrixB;

    /**
     * Class constructor.
     */
    public Test() {
        try {
            this.out = new PrintWriter("performanceTest.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.random = new Random();
    }

    /**
     * Performs performance tests and saves the results to a text file.
     */
    public void run() {
        printAdditionSubtractionaAndTranspositionTimesToFile();
        out.println();
        printDeterminantTimesToFile();
        out.println();
        printMultiplicationTimesToFile();
        out.close();
    }

    /**
     * Measures the time required to perform addition, subtraction and
     * transposition of matrices of different sizes and saves the results to
     * file.
     */
    private void printAdditionSubtractionaAndTranspositionTimesToFile() {
        out.printf("%14s %14s %14s %14s \n", "Koko/Algoritmi", "Yhteenlasku", "Vähennyslasku", "Transpoosi");
        for (int i = 1000; i < 8000; i += 1000) {
            matrixA = initMatrix(i);
            matrixB = initMatrix(i);
            out.printf("%14s", i + "*" + i);
            out.printf("%14s", additionTime());
            out.printf("%14s", subtractionTime());
            out.printf("%14s", transpositionTime());
            out.println();
        }
    }

    /**
     * Measures the time required to calculate the determinants of matrices of
     * varying sizes and saves the results to file.
     */
    private void printDeterminantTimesToFile() {
        out.printf("%14s %14s \n", "Koko/Algoritmi", "Determinantti");
        for (int i = 7; i < 12; i++) {
            matrixA = initMatrix(i);
            out.printf("%14s", i + "*" + i);
            out.printf("%14s \n", determinantTime());
        }
    }

    /**
     * Measures the time required to multiply matrices of varying sizes using
     * the naive algorithm and Strassen's algorithm, and saves the results to
     * file.
     */
    private void printMultiplicationTimesToFile() {
        out.printf("%14s %14s %14s \n", "Koko/Algoritmi", "Kertolasku", "Strassen");
        for (int i = 256; i < 2049; i += 256) {
            matrixA = initMatrix(i);
            matrixB = initMatrix(i);
            out.printf("%14s", i + "*" + i);
            out.printf("%14s", multiplicationTime());
            out.printf("%14s \n", strassenTime());
        }
    }

    /**
     * Creates a square matrix of the specified size and initializes it with
     * random number in the range -100...100.
     *
     * @param n the size of the square matrix
     * @return the matrix of the specified size initialized with random numbers
     */
    private Matrix initMatrix(int n) {
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                int nextEntry = random.nextInt(201) - 100;
                matrix.setElement(i, j, nextEntry);
            }
        }
        return matrix;
    }

    /**
     * Returns the time required to add matrixA and matrixB.
     *
     * @return the time it took to add matrixA and matrixB
     */
    private long additionTime() {
        class AdditionTimer extends Timer {

            @Override
            protected void timedOperation() {
                MatrixMath.add(matrixA, matrixB);
            }

        }
        return new AdditionTimer().computeTime();
    }

    /**
     * Returns the time required to subtract matrixB from matrixA.
     *
     * @return the time it took to subtract matrixB from matrixA
     */
    private long subtractionTime() {

        Timer timer = new Timer() {

            @Override
            protected void timedOperation() {
                MatrixMath.subtract(matrixA, matrixB);
            }

        };
        return timer.computeTime();
    }

    /**
     * Returns the time required to transpose matrixA.
     *
     * @return the time it took to transpose matrixA
     */
    private long transpositionTime() {
        Timer timer = new Timer() {

            @Override
            protected void timedOperation() {
                MatrixMath.transpose(matrixA);
            }

        };
        return timer.computeTime();
    }

    /**
     * Returns the time required to calculate the determinant of matrixA.
     *
     * @return the time it took to calculate the determinant of matrixA.
     */
    private long determinantTime() {
        Timer timer = new Timer() {

            @Override
            protected void timedOperation() {
                MatrixMath.det(matrixA);
            }

        };
        return timer.computeTime();
    }

    /**
     * Returns the time required to multiply matrixA and matrixB using the naive
     * approach.
     *
     * @return the time it took to multiply matrixA and matrixB
     */
    private long multiplicationTime() {
        Timer timer = new Timer() {

            @Override
            protected void timedOperation() {
                MatrixMath.multiply(matrixA, matrixB);
            }

        };
        return timer.computeTime();
    }

    /**
     * Returns the time required to multiply matrixA and matrixB using
     * Strassen's algorithm.
     *
     * @return the time it took to multiply matrixA and matrixB using Strassen's
     * algorithm
     */
    private long strassenTime() {
        Timer timer = new Timer() {

            @Override
            protected void timedOperation() {
                MatrixMath.strassenMultiply(matrixA, matrixB);
            }

        };
        return timer.computeTime();
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.run();
    }

}
