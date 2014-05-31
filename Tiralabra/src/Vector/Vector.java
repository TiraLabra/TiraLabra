package Vector;

import Types.Number;

public class Vector {
    public final int N;
    private final Number[] vector;
    
    public Vector(Number[] elements) {
        N = elements.length;
        vector = elements;
    }
    
    public Number get(int i) {
        return vector[i];
    }
    
    public Vector multiply(Number scalar) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].multiply(scalar);
        }
        return new Vector(res);
    }
    
    public Vector divide(Number scalar) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].divide(scalar);
        }
        return new Vector(res);
    }
    
    public Vector multiply(Vector other) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].multiply(other.get(i));
        }
        return new Vector(res);
    }
    
    public Vector subtract(Vector other) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].subtract(other.get(i));
        }
        return new Vector(res);
    }
    
    public Vector add(Vector other) {
        Number[] res = new Number[N];
        for (int i = 0; i < N; i++) {
            res[i] = vector[i].add(other.get(i));
        }
        return new Vector(res);
    }
    
    public Number dot(Vector other) {
        Number res = vector[0].multiply(other.get(0));
        for (int i = 1; i < N; i++) {
            res = res.add(vector[i].multiply(other.get(i)));
        }
        return res;
    }
    
    public Number length() {
        Number square = this.dot(this);
        return square.sqrt();
    }
    
    public Vector normalize() {
        return this.divide(length());
    }
    
    public Vector cross(Vector other) {
        throw new UnsupportedOperationException();
    }
}