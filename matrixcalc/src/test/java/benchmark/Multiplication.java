package benchmark;

import math.RealMatrix;

/**
 * Benchmark class for calculating the product of two real square matrices.
 *
 * @author ydna
 */
public class Multiplication {
    
    public String data;
    
    public Multiplication() {
        data = "";
        long tmp = 0;
        for (int size = 100; size <= 500; size += 10) {
            for (int j = 0; j < 10; j++) {
                RealMatrix temp1 = RealMatrix.randomMatrix(size, size, -100, 100);
                RealMatrix temp2 = RealMatrix.randomMatrix(size, size, -100, 100);
                long begin = System.nanoTime();
                temp1.multiply(temp2);
                long end = System.nanoTime();
                tmp += end - begin;
            }
            data += (size + "\t" + tmp/10 + "\n");
            tmp = 0;
        }
    }
    
}
