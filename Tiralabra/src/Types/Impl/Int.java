package Types.Impl;

import Types.Number;

public class Int extends Number {
    public static final Int ONE = new Int(1);
    public static final Int ZERO = new Int(0);
    public static final Int TEN = new Int(10);
    
    public final int integer;
    
    public Int(int integer) {
        this.integer = integer;
    }
    
    private int toInt(Number o) {
        return java.lang.Integer.parseInt(o.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Int(this.integer + toInt(other));
    }

    @Override
    public Number subtract(Number other) {
        return new Int(integer - toInt(other));
    }

    @Override
    public Number multiply(Number other) {
        
        return new Int(integer * toInt(other));
    }

    @Override
    public Number divide(Number other) {
        return new Int(integer / toInt(other));
    }

    @Override
    public Number pow(int n) {
        return new Int((int) Math.pow(integer, n));
    }
    
    @Override
    public Number sqrt() {
        int root = (int) Math.sqrt(integer);
        return new Int(root);
    }
    
    @Override
    public String toString() {
        return "" + integer;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Int other = (Int) obj;
        return this.integer == other.integer;
    }
}