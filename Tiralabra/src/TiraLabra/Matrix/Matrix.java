package TiraLabra.Matrix;

import TiraLabra.Number.Number;

/**
 * Matriisi
 * @author riku
 * @param <T>
 */
public class Matrix<T extends Number<T>> {
    public final int N, M;
    protected final T matrix[][];
    protected final Class<T> type;
    
    /**
     * Luo matriisin 2-uloitteisesta taulukosta
     * @param elements 
     * @param type 
     */
    public Matrix(T[][] elements, Class<T> type) {
        N = elements.length;
        M = (N > 0) ? elements[0].length : 0;
        
        matrix = elements;
        
        this.type = type;
    }

    /**
     * Luo tyhjän NxM matriisin
     * @param n
     * @param m 
     */
    public Matrix(int n, int m) {
        this.N = n;
        this.M = m;
        
        matrix = null;
        type = null;
    }
    
    /**
     * Luo NxM identiteettimatriisin
     * @param <T>
     * @param n
     * @param type
     * @return 
     */
    public static <T extends Number<T>> Matrix<T> identity(int n,
            Class<T> type) {
        T[][] val = (T[][]) new Number[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int k = (i == j) ? 1 : 0;
                val[i][j] = Number.make(type, k);
            }
        }

        return new Matrix<T>(val, type);
    }
    
    public T get(int i, int j) {
        return matrix[i][j];
    }
    
    /**
     * Kertoo matriisin skalaarilla, joka on samaa tyyppiä kuin matriisi.
     * @param scalar skalaari
     * @return uusi matriisi
     */
    public Matrix<T> multiply(T scalar) {
        T[][] val = (T[][]) new Number[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[i][j] = matrix[i][j].multiply(scalar);
            }
        }
        
        return new Matrix<T>(val, type);
    }
    
    /**
     * Kertoo matriisin millä tahansa toisella matriisilla
     * @param other toinen matriisi
     * @return uusi matriisi
     */
    public Matrix<T> multiply(Matrix<T> other) {
        T[][] val = (T[][]) new Number[N][other.M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[i][j] = Number.make(type, 0);
                
                for (int k = 0; k < other.M; k++) {
                    final T v = matrix[i][k].multiply(other.matrix[k][j]);
                    val[i][j] = val[i][j].add(v);
                }
            }
        }
        
        return new Matrix<T>(val, type);
    }
    
    /**
     * Matriisien yhteenlasku
     * @param other
     * @return 
     */
    public Matrix<T> add(Matrix<T> other) {
        if (other.N != this.N || other.M != this.M) {
            throw new UnsupportedOperationException();
        }
        
        T[][] val = (T[][]) new Number[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[i][j] = matrix[i][j].add(other.matrix[i][j]);
            }
        }
        
        return new Matrix<T>(val, type);
    }
    
    /**
     * Naiivi matriisin potenssiin korotus
     * @param n eksponentti
     * @return uusi matriisi
     */
    public Matrix<T> pow_naive(int n) {
        Matrix<T> res = identity(N, type);
        for (int i = 0; i < n; i++) {
            res = this.multiply(res);
        }
        
        return res;
    }
    
    /**
     * Nopeampi matriisin potenssiin korotus
     * @param n eksponentti
     * @return uusi matriisi
     */
    public Matrix<T> pow(int n) {
        Matrix<T> m = this, res = identity(N, type);
        while (n > 0) {
            if ((n % 2) == 1) {
                res = m.multiply(res);
            }
            
            m = m.multiply(m);
            n /= 2;
        }
        
        return res;
    }
    
    /**
     * Luo transpoosimatriisi
     * @return
     */
    public Matrix<T> transpose() {
        T[][] val = (T[][]) new Number[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[j][i] = matrix[i][j];
            }
        }
        
        return new Matrix<T>(val, type);
    }
    
    /**
     * Laskee matriisin determinantin
     * @return determinantti
     */
    public T determinant() {
        if (N != M) {
            throw new UnsupportedOperationException("Not a square matrix");
        }
        
        if (N == 1) {
            return matrix[0][0];
        }
        
        T det = Number.make(type, 0);
        for (int i = 0; i < N; i++) {
            final T n = matrix[0][i].multiply(submatrix(0, i).determinant());
            det = det.add((i % 2 == 1) ? n.negate() : n);
        }
        
        return det;
    }
    
    /**
     * Poistaa matriisista i:s rivi ja j:s sarake
     * @param i
     * @param j
     * @return uusi matriisi
     */
    public Matrix<T> submatrix(int i, int j) {
        T res[][] = (T[][]) new Number[N-1][M-1];
        
        for (int k = 0; k < N; k++) {
            if (k == i) continue;            
            for (int l = 0; l < M; l++) {
                if (l == j) continue;

                res[k - ((k > i) ? 1 : 0)][l - ((l > j) ? 1 : 0)] = matrix[k][l];
            }
         }
        
        return new Matrix<T>(res, type);
    }
}
