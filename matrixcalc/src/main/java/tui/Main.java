package tui;

import math.Matrix;

/**
 * TODO: Text-based user interface for the matrix calculator.
 * 
 * @author ydna
 */
public class Main {
    
    public static void main(String[] args) {
        for (int n = 2; n <= 2048; n *= 2) {
            double[][] test = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    test[i][j] = Math.random();
                }
            }
            Matrix matrix = new Matrix(test);
            long begin, end;
            begin = System.currentTimeMillis();
            matrix.strassenMultiply(matrix);
            end = System.currentTimeMillis();
            long strassen = end - begin;
            System.out.println("Size: " + n + "; Strassen: " + strassen);
        }
    }
    
}
