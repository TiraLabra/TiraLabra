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
        final T bc = get(0, 1).multiply(get(1, 0));

        final T det = ad.subtract(bc);

        return det;
    }
    
    public T[] eigenvalues() {
        T t = get(0, 0).add(get(1, 1));
        
        final T two = (T) Number.make(type, 2), four = two.add(two);
        final T det = t.multiply(t).subtract(determinant().multiply(four))
                .sqrt();
        
        T[] res = (T[]) new Number[2];
        res[0] = t.add(det).divide(two);
        res[1] = t.subtract(det).divide(two);
        return res;
    }
}
