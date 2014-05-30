package fibonacci;

import Matrix.Matrix;
import Types.Impl.Integer;

public class Fibonacci {
    private static String fibonacci(int n, boolean naive) {
        Integer values[][] =
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};
        
        Matrix matrix = new Matrix(values);
        if (naive) {
            matrix = matrix.pow_naive(n);
        } else {
            matrix = matrix.pow(n);
        }
        
        Types.Number fib = matrix.get(0, 1);
        return fib.toString();
    }
    
    private static void timeit(boolean naive) {
        long aikaAlussa = System.currentTimeMillis();
        
        System.out.println("Fibonacci(4096), " + (naive ? "naive" : "fast")
                + ": " + fibonacci(4096, naive));
        
        long aikaLopussa = System.currentTimeMillis();
        System.out.println((aikaLopussa - aikaAlussa) + "ms\n");
    }
    
    public static void main(String[] args) {
        timeit(true);
        timeit(false);
    }
}
