package Types;

import java.math.BigInteger;

public class ASDInteger implements ASDNumber<ASDInteger> {
    public static final ASDInteger ONE = new ASDInteger(BigInteger.ONE);
    public static final ASDInteger ZERO = new ASDInteger(BigInteger.ZERO);
    public static final ASDInteger TEN = new ASDInteger(BigInteger.TEN);
    
    public final BigInteger integer;
    
    public ASDInteger(int integer) {
        this.integer = BigInteger.valueOf(integer);
    }
    
    public ASDInteger(String string) {
        integer = new BigInteger(string);
    }
    
    public ASDInteger(BigInteger integer) {
        this.integer = integer;
    }

    @Override
    public ASDInteger add(ASDInteger other) {
        return new ASDInteger(integer.add(other.integer));
    }

    @Override
    public ASDInteger subtract(ASDInteger other) {
        return new ASDInteger(integer.subtract(other.integer));
    }

    @Override
    public ASDInteger multiply(ASDInteger other) {
        return new ASDInteger(integer.multiply(other.integer));
    }

    @Override
    public ASDInteger divide(ASDInteger other) {
        return new ASDInteger(integer.divide(other.integer));
    }

    @Override
    public ASDInteger pow(ASDInteger other) {
        return null; //new ASDInteger(integer.pow(other.integer));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ASDInteger other = (ASDInteger) obj;
        if (this.integer != other.integer && (this.integer == null || !this.integer.equals(other.integer))) {
            return false;
        }
        return true;
    }
}
