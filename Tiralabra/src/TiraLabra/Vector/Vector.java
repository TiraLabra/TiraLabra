package TiraLabra.Vector;

import TiraLabra.Number.Number;

/**
 * N-ulotteinen vektori
 * @author riku
 * @param <T>
 */
public class Vector<T extends Number<T>> extends Number<Vector<T>> {
    /**
     * Vektorin pituus
     */
    public final int N;
    
    private final T[] vector;
    
    /**
     * 
     * @param elements 
     */
    public Vector(T... elements) {
        N = elements.length;
        vector = elements;
    }
    
    public T get(int i) {
        return vector[i];
    }
    
    /**
     * Kertoo vektorin skalaarilla
     * @param scalar
     * @return this * scalar
     */
    public Vector<T> scale(T scalar) {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].multiply(scalar);
        }
        return new Vector<T>(res);
    }
    
    /**
     * Kertoo kaksi vektoria yhteen
     * @param other
     * @return this * other
     */
    @Override
    public Vector<T> multiply(Vector<T> other) {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].multiply(other.get(i));
        }
        return new Vector<T>(res);
    }
    
    /**
     * Kahden vektorin erotus
     * @param other
     * @return this - other
     */
    @Override
    public Vector<T> subtract(Vector<T> other) {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].subtract(other.get(i));
        }
        return new Vector(res);
    }
    
    /**
     * Kahden vektorin summa
     * @param other
     * @return this + other
     */
    @Override
    public Vector<T> add(Vector<T> other) {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].add(other.get(i));
        }
        return new Vector<T>(res);
    }
    
    /**
     * Kahden vektorin pistetulo
     * @param other
     * @return 
     */
    public T dot(Vector<T> other) {
        T res = vector[0].multiply(other.get(0));
        for (int i = 1; i < N; i++) {
            res = res.add(vector[i].multiply(other.get(i)));
        }
        return res;
    }
    
    /**
     * Vektorin pituus
     * @return 
     */
    public T length() {
        T square = this.dot(this);
        return square.sqrt();
    }
    
    /**
     * 
     * @return 
     */
    public Vector<T> normalize() {
        return this.scale(length().inverse());
    }
    
    /**
     * Kahden vektorin ristitulo
     * @param other
     * @return this x other
     */
    public Vector<T> cross(Vector<T> other) {
        if (N != 3 || other.N != 3) {
            throw new UnsupportedOperationException();
        }
        
        T[] res = (T[]) new Number[3];
        
        // x = (this.y * other.z) - (this.z * other.y)
        res[0] = vector[1].multiply(other.get(2)).subtract(
                vector[2].multiply(other.get(1)));
        
        // y = (this.z * other.x) - (this.x * other.z)
        res[1] = vector[2].multiply(other.get(0)).subtract(
                vector[0].multiply(other.get(2)));
        
        // z = (this.x * other.y) - (this.y * other.x)
        res[2] = vector[0].multiply(other.get(1)).subtract(
                vector[1].multiply(other.get(0)));
        
        return new Vector<T>(res);
    }
    
    /**
     * Vektorin suurin luku
     * @return 
     */
    public T max() {
        T max = vector[0];
        for (int i = 1; i < N; i++) {
            if (vector[i].compareTo(max) > 0) {
                max = vector[i];
            }
        }
        
        return max;
    }
    
    @Override
    public boolean isZero() {
        for (int i = 0; i < N; i++) {
            if (!vector[i].isZero()) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public Vector<T> negate() {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].negate();
        }
        
        return new Vector<T>(res);
    }

    @Override
    public Vector<T> divide(Vector<T> other) {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].divide(other.vector[i]);
        }
        
        return new Vector<T>(res);
    }

    @Override
    public Vector<T> pow(int n) {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].pow(n);
        }
        
        return new Vector<T>(res);
    }

    @Override
    public Vector<T> sqrt() {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].sqrt();
        }
        
        return new Vector<T>(res);
    }

    @Override
    public boolean isNegative() {
        for (int i = 0; i < N; i++) {
            if (!vector[i].isNegative()) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public Vector<T> abs() {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].abs();
        }
        
        return new Vector<T>(res);
    }

    @Override
    public int compareTo(Vector<T> o) {
        if (this.N != o.N) {
            throw new IllegalArgumentException();
        }
        
        int sum = 0;
        
        for (int i = 0; i < N; i++) {
            sum += vector[i].compareTo(o.vector[i]);
        }
        
        return sum;
    }

    @Override
    public Vector<T> inverse() {
        T[] res = (T[]) new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].inverse();
        }
        
        return new Vector<T>(res);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Vector<T> other = (Vector<T>) obj;
        
        if (this.N != other.N) {
            return false;
        }
        
        for (int i = 0; i < N; i++) {
            if (!vector[i].equals(other.vector[i])) {
                return false;
            }
        }
        
        return true;
    }
}
