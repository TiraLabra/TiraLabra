package Matrix;

/**
 * Geneerinen matriisi
 * @author riku
 * @param <T> skalaarityyppi
 */
public class Matrix<T extends Types.Number<T>> {
    public final int N, M;
    private final Object matrix[][];
    
    /**
     * Luo tyhjän 0x0 matriisin.
     */
    public Matrix() {
        N = M = 0;
        matrix = null;
    }
    
    /**
     * Luo matriisin 2-uloitteisesta taulukosta
     * @param elements 
     */
    public Matrix(T[][] elements) {
        N = elements.length;
        M = elements[0].length;
        
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

        matrix = new Object[n][m];
        
        // Pitäis pystyä tekemään identiteettimatriisi, muuten tästä
        // tulee turha ja tyhjä matriisi.
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = (i == j) ? 1 : 0;
            }
        }*/
    }

    public T get(int i, int j) {
        return (T) matrix[i][j];
    }
    
    public void set(int i, int j, T v) {
        matrix[i][j] = v;
    }
    
    /**
     * Setteri monelle elementille samaan aikaan, asettaa A[i0][j0] eteenpäin
     * @param i0 i-indeksin lähtöpaikka
     * @param j0 j-indeksin lähtöpaikka
     * @param elements skalaareita
     */
    public void set(int i0, int j0, T... elements) {
        if (i0 < 0 || i0 >= N || j0 < 0 || j0 >= M) {
            throw new IndexOutOfBoundsException();
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

    /**
     * Wrapperi skalaarityypin add-metodille
     * @param i
     * @param j
     * @param v 
     */
    private void add(int i, int j, T v) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            throw new IndexOutOfBoundsException();
        }
        
        if (matrix[i][j] == null) {
            matrix[i][j] = v;
        } else {
            matrix[i][j] = ((T) matrix[i][j]).add(v);
        }
    }
    
    /**
     * Kertoo matriisin skalaarilla, joka on samaa tyyppiä kuin matriisi.
     * @param scalar skalaari
     * @return uusi matriisi
     */
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
    
    /**
     * Kertoo matriisin millä tahansa toisella matriisilla
     * @param other toinen matriisi
     * @return uusi matriisi
     */
    public Matrix<T> multiply(Matrix<T> other) {
        Matrix<T> res = new Matrix<>(N, other.M);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < other.M; k++) {
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
    
    /**
     * Laskee matriisin determinantin
     * @return determinantti
     */
    public T determinant() {
        if (M != N) {
            throw new UnsupportedOperationException("Not a square matrix");
        }
        
        throw new UnsupportedOperationException();
    }
}
