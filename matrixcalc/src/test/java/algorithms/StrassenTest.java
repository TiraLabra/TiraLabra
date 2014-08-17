package algorithms;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ydna
 */
public class StrassenTest {
    
    private final static double EPSILON = Double.MIN_VALUE;

    @Test
    public void testMultiplication() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        double[][] product = Strassen.multiply(temp, temp);
        assertEquals((double)3*10, product[0][0], EPSILON);
        assertEquals((double)3*12, product[0][1], EPSILON);
        assertEquals((double)3*14, product[0][2], EPSILON);
        assertEquals((double)3*22, product[1][0], EPSILON);
        assertEquals((double)3*27, product[1][1], EPSILON);
        assertEquals((double)3*32, product[1][2], EPSILON);
        assertEquals((double)3*34, product[2][0], EPSILON);
        assertEquals((double)3*42, product[2][1], EPSILON);
        assertEquals((double)3*50, product[2][2], EPSILON);
    }
    
}
