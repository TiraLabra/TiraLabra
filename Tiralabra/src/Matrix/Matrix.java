package Matrix;

import Types.ASDNumber;

public class Matrix<T extends ASDNumber<T>> {
    public final int N, M;
    private final Object matrix[][];
    
    public Matrix() {
        N = M = 0;
        matrix = null;
    }
    
    public Matrix(T[][] values) {
        N = values.length;
        M = values[0].length;
        
        matrix = values;
    }

    public Matrix(int n, int m) {
        this.N = n;
        this.M = m;

        matrix = new Object[n][m];
        
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = (i == j) ? 1 : 0;
            }
        }*/
    }

    public T get(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return null;
        }

        return (T) matrix[i][j];
    }
    
    public void set(int i, int j, T v) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return;
        }
        
        matrix[i][j] = v;
    }
    
    public void set(int i0, int j0, T... elements) {
        if (i0 < 0 || i0 >= N || j0 < 0 || j0 >= M) {
            return;
        }
        
        int e = 0;
        for (int i = i0; i < N; i++) {
            for (int j = j0; j < M; j++) {
                matrix[i][j] = elements[e++];

                if (e >= elements.length) {
                    return;
                }
            }
        }
    }
    
    public void add(int i, int j, T v) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return;
        }
        
        if (matrix[i][j] == null) {
            matrix[i][j] = v;
        } else {
            matrix[i][j] = ((T) matrix[i][j]).add(v);
        }
    }
    
    public Matrix<T> multiply(T scalar) {
        Matrix<T> res = new Matrix<>(N, M);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                T value = scalar.multiply(this.get(i, j));
                res.set(i, j, value);
            }
        }
        
        return res;
    }
    
    public Matrix<T> multiply(Matrix<T> other) {
        Matrix<T> res = new Matrix<>(N, M);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < other.N; k++) {
                    T value = this.get(i, k).multiply(other.get(k, j));
                    res.add(i, j, value);
                }
            }
        }
        
        return res;
    }
    
    // Tarvii jonkun kiinnostavan ratkasun geneerisen identiteettimatriisin
    // luomiseen.
    /*public Matrix<T> pow(int n) {
        Matrix<T> res = new Matrix<>(N, M);

        for (int i = 0; i < n; i++) {
            res = res.multiply(this);
        }
        
        return res;
    }*/
}
