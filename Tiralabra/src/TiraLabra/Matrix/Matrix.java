package TiraLabra.Matrix;

import TiraLabra.Number.Number;

/**
 * Matriisi
 * @author riku
 * @param <T>
 */
public class Matrix<T extends Number<T>> {
    /**
     * Matriisin koot
     */
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
    public Matrix(int n, int m, Class<T> type) {
        this.N = n;
        this.M = m;
        
        matrix = null;
        this.type = type;
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
                val[i][j] = T.make(type, k);
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
        
        final T zero = Number.make(type, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[i][j] = zero;
                
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
     * Matriisien vähennyslasku
     * @param other
     * @return 
     */
    public Matrix<T> subtract(Matrix<T> other) {
        if (other.N != this.N || other.M != this.M) {
            throw new UnsupportedOperationException();
        }
        
        T[][] val = (T[][]) new Number[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                val[i][j] = matrix[i][j].subtract(other.matrix[i][j]);
            }
        }
        
        return new Matrix<T>(val, type);
    }
    
    /**
     * Matriisin potenssiin korotus
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
            n = n >> 1;
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
        
        T det = T.make(type, 0);
        for (int i = 0; i < N; i++) {
            final T n = matrix[0][i].multiply(submatrix(0, i).determinant());
            
            if ((i % 2) == 0) {
                det = det.add(n);
            } else {
                det = det.subtract(n);
            }
            
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
    
    @Override
    public String toString() {
        String res = "{";
        for (int i = 0; i < N; i++) {
            res += "{";
            for (int j = 0; j < M; j++) {
                res += matrix[i][j];
                
                if (j < M-1) {
                    res += ", ";
                }
            }
            
            res += "}";
            
            if (i < N-1) {
                res += ", ";
            }
        }
        res += "}";
        
        return res;
    }
    
    /**
     * Palauttaa matriisin arvot neljässä pienemmässä matriisisissa
     * @return
     * blockMatrices[0] = A_1,1
     * blockMatrices[1] = A_1,2
     * blockMatrices[2] = A_2,1
     * blockMatrices[3] = A_2,2
     */
    public Matrix<T>[] blockMatrices() {
        int n = N / 2;
        
        T val[][][] = (T[][][]) new Number[4][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val[0][i][j] = matrix[i][j];
                val[1][i][j] = matrix[i][j + n];
                val[2][i][j] = matrix[i + n][j];
                val[3][i][j] = matrix[i + n][j + n];
            }
        }
        
        Matrix<T> res[] = new Matrix[4];
        for (int i = 0; i < 4; i++) {
            res[i] = new Matrix<T>(val[i], type);
        }
        
        return res;
    }
    
    /**
     * Luo matriisin neljästä lohkomatriisista
     * @param <T>
     * @param blocks
     * @return 
     */
    protected static <T extends Number<T>> Matrix<T>
            fromBlockMatrices(Matrix<T>... blocks) {
        if (blocks.length != 4) {
            throw new IllegalArgumentException();
        }
        
        int n2 = blocks[0].N, n = n2 * 2;
        T[][] val = (T[][]) new Number[n][n];
        
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
                val[i][j] = blocks[0].matrix[i][j];
                val[i][j + n2] = blocks[1].matrix[i][j];
                val[i + n2][j] = blocks[2].matrix[i][j];
                val[i + n2][j + n2] = blocks[3].matrix[i][j];
            }
        }
        
        return new Matrix<T>(val, blocks[0].type);
    }

    /**
     * Strassenin algoritmin rekursiivinen "sydän"
     * @param <T>
     * @param a matriisi
     * @param b matriisi
     * @return a * b
     */
    protected static <T extends Number<T>> Matrix<T> strassenR(Matrix<T> a,
            Matrix<T> b) {
        if (a.N < 8) {
            return a.multiply(b);
        }
        
        Matrix<T> blockA[] = a.blockMatrices(),
                blockB[] = b.blockMatrices();
        
        // p1 = (a11 + a22) * (b11 + b22)
        Matrix<T> p1 = strassenR(blockA[0].add(blockA[3]),
                blockB[0].add(blockB[3]));
        
        // p2 = (a21 + a22) * b11
        Matrix<T> p2 = strassenR(blockA[2].add(blockA[3]), blockB[0]);
        
        // p3 = a11 * (b12 - b22)
        Matrix<T> p3 = strassenR(blockA[0], blockB[1].subtract(blockB[3]));
        
        // p4 = a22 * (b21 - b11)
        Matrix<T> p4 = strassenR(blockA[3], blockB[2].subtract(blockB[0]));
        
        // p5 = (a11+a12) * b22
        Matrix<T> p5 = strassenR(blockA[0].add(blockA[1]), blockB[3]);
        
        // p6 = (a21-a11) * (b11+b12)
        Matrix<T> p6 = strassenR(blockA[2].subtract(blockA[0]),
                blockB[0].add(blockB[1]));
        
        // p7 = (a12-a22) * (b21+b22)
        Matrix<T> p7 = strassenR(blockA[1].subtract(blockA[3]),
                blockB[2].add(blockB[3]));
        
        Matrix<T> c11 = p1.add(p4).subtract(p5).add(p7),
                c12 = p3.add(p5),
                c21 = p2.add(p4),
                c22 = p1.add(p3).subtract(p2).add(p6);
                
        return fromBlockMatrices(c11, c12, c21, c22);
    }
    
    /**
     * Antaa n:ää seuraavan kahden potenssin
     * @param n
     * @return 
     */
    private static int nextPowerOfTwo(int n) {
        int k = 1;
        while (k < n) {
            k *= 2;
        }
        return k;
    }
    
    /**
     * Kertoo matriisin toisella käyttäen Strassenin algoritmia
     * @param other toinen matriisi
     * @return this * other
     */
    public Matrix<T> strassen(Matrix<T> other) {
        if (other.N != this.N || other.M != this.M) {
            throw new UnsupportedOperationException();
        } else if (N != M || other.N != other.M) {
            throw new UnsupportedOperationException("Not a square matrix");
        }
        
        final int m = nextPowerOfTwo(N);
        
        T[][] a = (T[][]) new Number[m][m],
                b = (T[][]) new Number[m][m];
        
        final T zero = T.make(type, 0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = zero;
                b[i][j] = zero;
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {    
                a[i][j] = this.matrix[i][j];
                b[i][j] = other.matrix[i][j];
            }
        }
        
        final Matrix<T> C = strassenR(new Matrix<T>(a, type),
                new Matrix<T>(b, type));
        
        T[][] c = (T[][]) new Number[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                c[i][j] = C.matrix[i][j];
            }
        }
        return new Matrix<T>(c, type);
    }
}
