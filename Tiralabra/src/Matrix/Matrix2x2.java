package Matrix;

import Number.Number;

/**
 * 2x2 matriisi
 * @author riku
 * @param <T> 
 */
public class Matrix2x2 extends Matrix {
    public Matrix2x2(Number[][] values) {
        super(values);
    }
    
    @Override
    public Number determinant() {
        final Number ad = get(0, 0).multiply(get(1, 1));
        final Number bc = get(0, 1).multiply(get(0, 1));

        final Number det = ad.subtract(bc);

        return det;
    }
}
