package TiraLabra.Number;

/**
 * Mielivaltaisen kokoinen kokonaisluku
 * @author riku
 */
public class Integer extends Number<Integer> {
    public static final Integer ONE = new Integer(1);
    public static final Integer ZERO = new Integer(0);
    public static final Integer TEN = new Integer(10);
    
    private static final int radix = java.lang.Integer.MAX_VALUE;
    
    protected final boolean negative;
    protected final int[] integer;

    /**
     * Luo luvun kokonaisluvusta
     * @param integer kokonaisluku
     */
    public Integer(int integer) {
        negative = (integer < 0);
        
        if (integer == 0) {
            this.integer = null;
        } else {
            this.integer = new int[1];
            if (integer == radix) {
                this.integer[0] = 0;
            } else {
                this.integer[0] = (negative) ? -integer : integer;
            }
        }
    }
    
    private static int leadingZeroes(int integer[]) {
        if (integer == null) {
            return -1;
        }
        
        int leading = 0;
        for (int i = integer.length-1; i >= 0; i--) {
            if (integer[i] > 0) {
                return leading;
            } else if (integer[i] == 0) {
                leading++;
            }
        }
        
        return -1;
    }
    
    public Integer(int[] integer, boolean negative) {
        this.negative = negative;
        
        int zeroes = leadingZeroes(integer);
        if (zeroes == -1){
            this.integer = null;
        } else if (zeroes > 0) {
            int int2[] = new int[integer.length - zeroes];
            System.arraycopy(integer, 0, int2, 0, zeroes);
            this.integer = int2;
        } else {
            this.integer = integer;
        }
    }
    
    private static int[][] padZeroes(int a[], int b[]) {
        int res[][] = {a, b};
        
        if (a.length == b.length) {
            return res;
        }
        
        int max = (a.length > b.length) ? 0 : 1;
        int min = (max + 1) % 2;
        
        res[min] = new int[res[max].length];
        for (int i = 0; i < res[max].length; i++) {
            if (min == 0) {
                res[min][i] = (i < a.length) ? a[i] : 0;
            } else {
                res[min][i] = (i < b.length) ? b[i] : 0;
            }
        }
        
        return res;
    }
    
    @Override
    public Integer add(Integer other) {
        if (this.isZero()) return other;
        if (other.isZero()) return this;
        
        if (this.isNegative()) { // -1 + 2 = 2 - 1
            return other.subtract(this.negate());
        } else if (other.isNegative()) { // 1 + (-2) = 1 - 2
            return this.subtract(other.negate());
        }

        int words[][] = padZeroes(integer, other.integer);
        
        int res[] = new int[words[0].length], k = 0;
        for (int i = 0; i < words[0].length; i++) {
            final int sum = words[0][i] + words[1][i] + k;
            k = (sum) / radix;
            res[i] = sum % radix;
        }
        
        return new Integer(res, false);
    }

    @Override
    public Integer subtract(Integer other) {
        if (other.isZero()) return this;
        if (this.isZero()) {
            return other.negate();
        }
        
        if (this.isNegative()) {
            if (other.isNegative()) { // -2 - (-1) = -2 + 1 = 1 - 2
                return other.negate().subtract(this.negate());
            } else { // -1 - 1 = -(1 + 1)
                return this.negate().add(other).negate();
            }
        } else if (other.isNegative()) { // 1 - (-1) = 1 + 1
            return this.add(other.negate());
        }

        if (this.compareTo(other) < 0) { // 1 - 2 = -(2 - 1)
            return other.subtract(this).negate();
        }
        
        int words[][] = padZeroes(integer, other.integer);
        
        int res[] = new int[words[0].length], k = 0;
        for (int i = words[0].length-1; i >= 0; i--) {
            final int sum = words[0][i] - words[1][i] + k;
            
            if (sum < 0) {
                throw new IllegalArgumentException();
            }
            
            k = (int) Math.floor(sum / radix);
            res[i] = sum % radix;
        }
        return new Integer(res, false);
    }
    
    @Override
    public Integer multiply(Integer other) {
        if (other.isZero() || this.isZero()) {
            return ZERO;
        }
        
        final int m = other.integer.length-1;
        
        int res[] = new int[integer.length + m + 1];
        for (int i = 0; i < integer.length; i++) {
            res[i+m] = 0;
        }
        
        for (int j = m; j >= 0; j--) {
            int k = 0;
            for (int i = integer.length-1; i >= 0; i--) {
                final int u = this.integer[i];
                final int v = other.integer[j];
                final int t = (u * v) + res[i+j] + k;
                res[i+j] = t % radix;
                k = t / radix;
            }
        }
        
        final boolean neg = (this.isNegative() != other.isNegative());
        return new Integer(res, neg);
    }

    @Override
    public Integer divide(Integer other) {
        if (this.isZero()) return this;
        if (other.isZero()) {
            throw new IllegalArgumentException("Division by zero");
        }
        
        int res[] = new int[integer.length];
        for (int i = 0; i < integer.length; i++) {
            res[i] = integer[i] / other.integer[i];
        }
        
        final boolean neg = (this.isNegative() != other.isNegative());
        return new Integer(res, neg);
    }

    @Override
    public Integer pow(int n) {
        Integer res = Integer.ONE;
        for (int i = 0; i < n; i++) {
            res = this.multiply(res);
        }
        return res;
    }
    
    @Override
    public Integer sqrt() {
        Integer n = this;
        for (int i = 0; i < 5; i++) {
            Integer f = n.multiply(n).subtract(this);
            Integer d = n.add(n);
            n = n.subtract(f.divide(d));
        }
        return n.subtract(ONE);
    }
    
    @Override
    public Integer abs() {
        return new Integer(integer, false);
    }
    
    @Override
    public Integer negate() {
        return new Integer(integer, !negative);
    }
    
    public boolean isZero() {
        return (integer == null);
    }
    
    @Override
    public boolean isNegative() {
        return negative;
    }
    
    private static String[] pad(String a, String b) {
        String[] res = {a, b};
        
        int max = (a.length() > b.length()) ? 0 : 1;
        int min = (max + 1) % 2;
        
        int diff = res[max].length() - res[min].length();
        for (int i = 0; i < diff; i++) {
            res[min] = "0" + res[min];
        }
        
        return res;
    }
    
    private static String add(String a, String b) {
        String res = "";

        String s[] = pad(a, b);
        
        int k = 0;
        for (int i = 0; i < s[0].length(); i++) {
            final int sa = java.lang.Integer.parseInt(""+s[0].charAt(i));
            final int sb = java.lang.Integer.parseInt(""+s[1].charAt(i));
            
            final int sum = sa + sb + k;
            k = sum / 9;
            res += sum % 9;
        }
        
        return res;
    }
    
    @Override
    public String toString() {
        if (isZero()) {
            return "0";
        }

        String res = "";
        for (int i = 0; i < integer.length; i++) {
            String c = "" + integer[i];
            for (int j = 0; j < i; j++) {
                c = add(c, "" + (radix - 1));
            }
            res = add(res, c);
        }
        return (isNegative() ? "-"+res : res);
    }
    
    @Override
    public int compareTo(Integer other) {
        if (this.isZero()) {
            if (other.isZero()) return 0;
            return other.isNegative() ? 1 : -1;
        } else if (other.isZero()) {
            return this.isNegative() ? -1 : 1;
        }
        
        if (this.isNegative() && !other.isNegative()) {
            return -1;
        } else if (!this.isNegative() && other.isNegative()) {
            return 1;
        }
        
        if (integer.length > other.integer.length) {
            return 1;
        } else if (integer.length < other.integer.length) {
            return -1;
        }
        
        for (int i = 0; i < integer.length; i++) {
            if (integer[i] == 0 && other.integer[i] != 0) {
                return 1;
            } else if (other.integer[i] == 0 && integer[i] != 0) {
                return -1;
            }
            
            if (integer[i] > other.integer[i]) {
                return 1;
            } else if (integer[i] < other.integer[i]) {
                return -1;
            }
        }
        
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final Integer other = (Integer) obj;
        
        if (this.isZero() || other.isZero()) {
            return (this.isZero() == other.isZero());
        }
        
        if (integer.length != other.integer.length ||
                this.isNegative() != other.isNegative()) {
            return false;
        }
        
        for (int i = 0; i < integer.length; i++) {
            if (integer[i] != other.integer[i]) {
                return false;
            }
        }
        
        return true;
    }
}
