package TiraLabra.Number;

/**
 * Käärö Javan double-luvuille
 * @author riku
 */
public class Double extends Number<Number> {
    public static final Double ONE = new Double(1.);
    public static final Double ZERO = new Double(0.);
    public static final Double TEN = new Double(10.);
    
    public final static Double EPSILON = new Double(1e-2);
    
    public final double decimal;
    
    public Double(double decimal) {
        this.decimal = decimal;
    }
    
    private double toDecimal(Number o) {
        return java.lang.Double.parseDouble(o.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Double(this.decimal + toDecimal(other));
    }

    @Override
    public Number subtract(Number other) {
        return new Double(decimal - toDecimal(other));
    }

    @Override
    public Number multiply(Number other) {
        return new Double(decimal * toDecimal(other));
    }

    @Override
    public Number divide(Number other) {
        return new Double(decimal / toDecimal(other));
    }

    @Override
    public Number pow(int n) {
        return new Double(Math.pow(decimal, n));
    }
    
    @Override
    public Number sqrt() {
        double root = Math.sqrt(decimal);
        return new Double(root);
    }
    
    @Override
    public boolean isNegative() {
        return (decimal < 0);
    }
    
    @Override
    public Number abs() {
        return new Double(Math.abs(decimal));
    }
    
    @Override
    public Number negate() {
        return new Double(decimal * -1.);
    }
    
    @Override
    public String toString() {
        return "" + decimal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final Double other = (Double) obj;
        return (this.subtract(other).abs().compareTo(EPSILON) < 0);
    }
    
    @Override
    public int compareTo(Number other) {
        return new java.lang.Double(decimal).compareTo(toDecimal(other));
    }
}