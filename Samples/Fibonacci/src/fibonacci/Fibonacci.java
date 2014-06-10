package fibonacci;

import TiraLabra.Matrix.Matrix;
import TiraLabra.Number.Integer;

public class Fibonacci {
    private static String fibonacci(int n, boolean naive) {
        Integer values[][] =
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};

        Matrix<Integer> matrix = new Matrix<Integer>(values, Integer.class);
        matrix = naive ? matrix.pow_naive(n) : matrix.pow(n);
        return matrix.get(0, 1).toString();
    }
    
    private static void timeit(int i, boolean naive) {
        long aikaAlussa = System.currentTimeMillis();
        
        System.out.println(i + ", " + fibonacci(i, naive));
        
        long aikaLopussa = System.currentTimeMillis();
        System.out.println((aikaLopussa - aikaAlussa) + "ms\n");
    }
    
    public static void main(String[] args) {
        //timeit(true);
        
        for (int i = 90; i < 200; i += 20) {
            timeit(i, false);
        }
    }
}
