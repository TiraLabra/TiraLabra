package benchmark;

import math.RealMatrix;

/**
 * Benchmark class for calculating the inverse of a real square matrix.
 *
 * @author ydna
 */
public class InverseMatrix {

    public String data;

    public InverseMatrix() {
        data = "";
        long tmp = 0;
        for (int size = 100; size <= 500; size += 10) {
            for (int j = 0; j < 10; j++) {
                RealMatrix temp = RealMatrix.randomMatrix(size, size, -100, 100);
                long begin = System.nanoTime();
                temp.inverse();
                long end = System.nanoTime();
                tmp += end - begin;
            }
            data += (size + "\t" + tmp / 10 + "\n");
            tmp = 0;
        }
    }

}
