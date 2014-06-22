package fibonacci;

import TiraLabra.Matrix.Matrix;
import TiraLabra.Number.Integer;

public class Fibonacci {
    private static String fibonacci_hidas(int n) {
        Integer i = Integer.ZERO, j = Integer.ONE;
        for (int k = 0; k < n; k++) {
            Integer tmp = i;
            i = i.add(j);
            j = tmp;
        }
        
        return ""; //i.toString();
    }
    
    private static String fibonacci(int n) {
        Integer values[][] =
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};

        Matrix<Integer> matrix = new Matrix<Integer>(values, Integer.class);
        matrix = matrix.pow(n);
        return ""; //matrix.get(0, 1).toString();
    }
    
    private static void timeit(int i, boolean hidas) {
        long aikaAlussa = System.currentTimeMillis();
        
        System.out.println(i + ", "
                + (hidas ? fibonacci_hidas(i) : fibonacci(i)));
        
        long aikaLopussa = System.currentTimeMillis();
        System.out.println((aikaLopussa - aikaAlussa) + "ms\n");
    }
    
    public static void main(String[] args) {
        timeit(100000, false);
        timeit(100000, true);
    }
}
