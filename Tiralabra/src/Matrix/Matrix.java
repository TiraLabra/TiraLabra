package Matrix;

import java.lang.reflect.InvocationTargetException;

import Types.Number;

/**
 * Matriisi
 * @author riku
 */
public class Matrix {
    public final int N, M;
    private final Number matrix[][];
    
    /**
     * Luo matriisin 2-uloitteisesta taulukosta
     * @param elements 
     */
    public Matrix(Number[][] elements) {
        N = elements.length;
        M = (N > 0) ? elements[0].length : 0;
        
        matrix = elements;
    }

    /**
     * Luo NxM matriisin
     * @param n
     * @param m 
     */
    public Matrix(int n, int m) {
        this.N = n;
        this.M = m;

        matrix = new Number[n][m];
    }
    
    /**
     * Luo NxM identiteettimatriisin
     * @param n 
     * @param m
     * @param type
     * @return 
     */
    public static Matrix identity(int n, int m, Class<? extends Number> type) {
        Number[][] val = new Number[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int k = (i == j) ? 1 : 0;

                try {
                    val[i][j] = type.getConstructor(int.class).newInstance(k);
                } catch (InstantiationException | IllegalAccessException |
                        IllegalArgumentException | InvocationTargetException |
                        NoSuchMethodException | SecurityException ex ) {
                    throw new IllegalArgumentException();
                }
            }
        }

        return new Matrix(val);
    }
    
    public Number get(int i, int j) {
        return matrix[i][j];
    }
    
    /**
     * Kertoo matriisin skalaarilla, joka on samaa tyyppiä kuin matriisi.
     * @param scalar skalaari
     * @return uusi matriisi
     */
    public Matrix multiply(Number scalar) {
        Number[][] val = new Number[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[i][j] = matrix[i][j].multiply(scalar);
            }
        }
        
        return new Matrix(val);
    }
    
    /**
     * Kertoo matriisin millä tahansa toisella matriisilla
     * @param other toinen matriisi
     * @return uusi matriisi
     */
    public Matrix multiply(Matrix other) {
        Number[][] val = new Number[N][other.M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < other.M; k++) {
                    final Number v = matrix[i][k].multiply(other.get(k, j));
                    val[i][j] = (val[i][j] == null) ? v : val[i][j].add(v);
                }
            }
        }
        
        return new Matrix(val);
    }
    
    /**
     * Naiivi matriisin potenssiin korotus
     * @param n eksponentti
     * @return uusi matriisi
     */
    public Matrix pow_naive(int n) {
        Matrix res = identity(N, M, Types.Impl.Integer.class);
        for (int i = 0; i < n; i++) {
            res = res.multiply(this);
        }
        
        return res;
    }
    
    /**
     * Nopea matriisin potenssiin korotus
     * @param n eksponentti
     * @return uusi matriisi
     */
    public Matrix pow(int n) {
        Matrix m = this;
        Matrix res = Matrix.identity(2, 2, Types.Impl.Integer.class);
        while (n > 0) {
            if ((n % 2) == 1) {
                res = res.multiply(m);
            }
            
            m = m.multiply(m);
            n /= 2;
        }
        
        return res;
    }
    
    /**
     * Laskee matriisin determinantin
     * @return determinantti
     */
    public Number determinant() {
        if (M != N) {
            throw new UnsupportedOperationException("Not a square matrix");
        }
        
        throw new UnsupportedOperationException();
    }
}
