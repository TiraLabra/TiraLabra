package Matrix;

import Types.ASDNumber;

public class Matrix2x2<T extends ASDNumber<T>> extends Matrix<T> {
    public Matrix2x2(T[][] values) {
        super(values);
    }
    
    public T determinant() {
        T ad = get(0, 0).multiply(get(1, 1));
        T bc = get(0, 1).multiply(get(0, 1));

        T det = ad.subtract(bc);

        return det;
    }
}
