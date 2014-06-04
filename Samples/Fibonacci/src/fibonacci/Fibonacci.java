package fibonacci;

import TiraLabra.Matrix.Matrix;
import TiraLabra.Number.Integer;

public class Fibonacci {
    private static String fibonacci(int n, boolean naive) {
        Integer values[][] =
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};
        
        Matrix<Integer> identity = Matrix.identity(2, 2, Integer.class);
        Matrix<Integer> matrix = new Matrix<Integer>(values);
        if (naive) {
            matrix = matrix.pow_naive(n, identity);
        } else {
            matrix = matrix.pow(n, identity);
        }
        
        Integer fib = matrix.get(0, 1);
        return fib.toString();
    }
    
    private static void timeit(boolean naive) {
        long aikaAlussa = System.currentTimeMillis();
        
        System.out.println("Fibonacci(25), " + (naive ? "naive" : "fast")
                + ": " + fibonacci(25, naive));
        
        long aikaLopussa = System.currentTimeMillis();
        System.out.println((aikaLopussa - aikaAlussa) + "ms\n");
    }
    
    public static void main(String[] args) {
        timeit(true);
        timeit(false);
    }
}
