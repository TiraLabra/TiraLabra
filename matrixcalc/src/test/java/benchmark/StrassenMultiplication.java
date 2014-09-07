package benchmark;

import math.RealMatrix;

/**
 * Benchmark class for calculating the product of two real square matrices.
 *
 * @author ydna
 */
public class StrassenMultiplication {

    public String data;

    public StrassenMultiplication() {
        data = "";
        long tmp1 = 0;
        long tmp2 = 0;
        long begin;
        long end;
        for (int size = 10; size <= 500; size += 10) {
            for (int j = 0; j < 10; j++) {
                RealMatrix temp1 = RealMatrix.randomMatrix(size, size, -100, 100);
                RealMatrix temp2 = RealMatrix.randomMatrix(size, size, -100, 100);
                begin = System.nanoTime();
                temp1.multiply(temp2);
                end = System.nanoTime();
                tmp1 += end - begin;
                begin = System.nanoTime();
                temp1.strassenMultiply(temp2);
                end = System.nanoTime();
                tmp2 += end - begin;
            }
            data += (size + "\t" + tmp1 / 10 + "\t" + tmp2 / 10 + "\n");
            tmp1 = 0;
            tmp2 = 0;
        }
    }

}
