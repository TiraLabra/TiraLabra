package fibonacci;

import Matrix.Matrix;
import Types.Integer;

public class Fibonacci {
    public static int fibonacci(int n) {
        Integer values[][] =                
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};
        
        Matrix<Integer> matrix = new Matrix<>(values);

        /* ********************************************** */
        // Ei kuulu tänne; pitäis kutsuu potenssi metodia.
        Integer identity[][] =
            {{Integer.ONE, Integer.ZERO},
             {Integer.ZERO, Integer.ONE}};
        Matrix<Integer> m2 = new Matrix<>(identity);
        for (int i = 0; i < n; i++) {
            m2 = m2.multiply(matrix);
        }
        matrix = m2;
        /* ********************************************** */
        
        // matrix = matrix.pow(n);
        
        Integer fib = matrix.get(0, 1);
        return fib.integer.intValue();
    }
    
    public static void main(String[] args) {
        System.out.println("Fibonacci(25): " + fibonacci(25));
    }
}
