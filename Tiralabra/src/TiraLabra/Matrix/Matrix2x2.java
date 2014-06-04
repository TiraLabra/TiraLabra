package TiraLabra.Matrix;

import TiraLabra.Number.Number;

/**
 * 2x2 matriisi
 * @author riku
 * @param <T> 
 */
public class Matrix2x2<T extends Number<T>> extends Matrix<T> {
    public Matrix2x2(T[][] values, Class<? extends Number> type) {
        super(values, type);
    }
    
    @Override
    public T determinant() {
        final T ad = get(0, 0).multiply(get(1, 1));
        final T bc = get(0, 1).multiply(get(0, 1));

        final T det = ad.subtract(bc);

        return det;
    }
}
