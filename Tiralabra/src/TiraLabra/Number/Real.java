package TiraLabra.Number;

/**
 * Mielivaltaisen tarkka reaaliluku
 * @author riku
 */
public class Real extends Number<Real> {
    public static final Real ONE = new Real(Integer.ONE, 0);
    public static final Real ZERO = new Real(Integer.ZERO, 0);
    public static final Real TEN = new Real(Integer.TEN, 0);
    
    public static final Real EPSILON = new Real(Integer.ONE, 10);
    
    protected final Integer mantissa;
    protected final int exponent;
    
    /**
     * Luo luvun kokonaisluvusta
     * @param integer
     */
    public Real(int integer) {
        mantissa = new Integer(integer);
        exponent = 0;
    }
    
    protected Real(Integer mantissa, int exponent) {
        this.mantissa = mantissa;
        this.exponent = exponent;
    }
   
    @Override
    public String toString() {
        boolean neg = mantissa.isNegative();
        
        String m = mantissa.abs().toString();
        if (exponent == 0) {
            return (neg ? "-" : "") + m;
        } else {
            int diff = exponent - m.length() + 1;
            for (int i = 0; i < diff; i++) {
                m = "0" + m;
            }
            
            return (neg ? "-" : "")
                    + m.substring(0, m.length() - exponent)
                    + "." + m.substring(m.length() - exponent);
        }
    }

    @Override
    public Real add(Real other) {
        if (this.exponent < other.exponent) {
            return other.add(this);
        }
        
        int s = this.exponent - other.exponent;
        Integer shifted = other.mantissa.multiply(Integer.TEN.pow(s));
        return new Real(mantissa.add(shifted), exponent);
    }

    @Override
    public Real subtract(Real other) {
        if (this.exponent < other.exponent) {
            return other.subtract(this).negate();
        }
        
        int s = this.exponent - other.exponent;
        Integer shifted = other.mantissa.multiply(Integer.TEN.pow(s));
        return new Real(mantissa.subtract(shifted), exponent);
    }

    @Override
    public Real multiply(Real other) {
        return new Real(mantissa.multiply(other.mantissa),
                exponent + other.exponent);
    }

    @Override
    public Real divide(Real other) {
        int exp = exponent + other.exponent + 2;
        Integer m = mantissa.multiply(Integer.TEN.pow(exp));
        return new Real(m.divide(other.mantissa), exp);
    }

    @Override
    public Real pow(int n) {
        return new Real(mantissa.pow(n), exponent);
    }

    private boolean isOne() {
        String s = mantissa.toString();
        
        if (s.charAt(0) != '1') {
            return false;
        }
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(1) != '0') {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public Real sqrt() {
        if (this.isOne()) {
            return this;
        }
        
        int exp = exponent * exponent;
        Integer m = mantissa.multiply(Integer.TEN.pow(exp));
        return new Real(m.sqrt(), exp);
    }

    @Override
    public boolean isNegative() {
        return mantissa.isNegative();
    }

    @Override
    public boolean isZero() {
        return mantissa.isZero();
    }

    @Override
    public Real abs() {
        return new Real(mantissa.abs(), exponent);
    }

    @Override
    public Real negate() {
        return new Real(mantissa.negate(), exponent);
    }

    @Override
    public Real inverse() {
        return ONE.divide(this);
    }
    
    @Override
    public int compareTo(Real other) {
        if (this.exponent < other.exponent) {
            return -other.compareTo(this);
        }
        
        int s = this.exponent - other.exponent;
        Integer shifted = other.mantissa.multiply(Integer.TEN.pow(s));
        return mantissa.compareTo(shifted);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final Real other = (Real) obj;
        
        Real diff = this.subtract(other).abs();
        return (diff.compareTo(EPSILON) < 0);
    }
}
