package TiraLabra.Vector;

import TiraLabra.Number.Number;

/**
 * N-ulotteinen vektori
 * @author riku
 * @param <T>
 */
public class Vector<T extends Number<T>> {
    /**
     * Vektorin pituus
     */
    public final int N;
    
    private final T[] vector;
    
    /**
     * 
     * @param elements 
     */
    public Vector(T[] elements) {
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
    public Vector multiply(T scalar) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].multiply(scalar);
        }
        return new Vector(res);
    }
    
    /**
     * Jakaa vektorin skalaarilla
     * @param scalar
     * @return this / scalar
     */
    public Vector divide(T scalar) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].divide(scalar);
        }
        return new Vector(res);
    }
    
    /**
     * Kertoo kaksi vektoria yhteen
     * @param other
     * @return this * other
     */
    public Vector multiply(Vector<T> other) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].multiply(other.get(i));
        }
        return new Vector(res);
    }
    
    /**
     * Kahden vektorin erotus
     * @param other
     * @return this - other
     */
    public Vector subtract(Vector<T> other) {
        Number[] res = new Number[N];
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
    public Vector add(Vector<T> other) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].add(other.get(i));
        }
        return new Vector(res);
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
    public Vector normalize() {
        return this.divide(length());
    }
    
    /**
     * Kahden vektorin ristitulo
     * @param other
     * @return this x other
     */
    public Vector cross(Vector<T> other) {
        if (N != 3 || other.N != 3) {
            throw new UnsupportedOperationException();
        }
        
        Number[] res = new Number[3];
        
        // x = (this.y * other.z) - (this.z * other.y)
        res[0] = vector[1].multiply(other.get(2)).subtract(
                vector[2].multiply(other.get(1)));
        
        // y = (this.z * other.x) - (this.x * other.z)
        res[1] = vector[2].multiply(other.get(0)).subtract(
                vector[0].multiply(other.get(2)));
        
        // z = (this.x * other.y) - (this.y * other.x)
        res[2] = vector[0].multiply(other.get(1)).subtract(
                vector[1].multiply(other.get(0)));
        
        return new Vector(res);
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
}
