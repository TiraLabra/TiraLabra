package TiraLabra.Number;

/**
 * Mielivaltaisen kokoinen kokonaisluku
 * @author riku
 */
public class Integer extends Number<Integer> {
    public static final Integer ONE = new Integer(1);
    public static final Integer ZERO = new Integer(0);
    public static final Integer TEN = new Integer(10);
    
    // radix^2 < 2^32 / 2
    // radix = 46340
    private static final int radix = 46340;
    
    protected final boolean negative;
    protected final int[] words;
    
    /**
     * Luo luvun kokonaisluvusta
     * @param integer kokonaisluku
     */
    public Integer(int integer) {
        negative = (integer < 0);

        if (Math.abs(integer) == radix) {
            this.words = new int[2];
            this.words[0] = 0;
            this.words[1] = (negative) ? -1 : 1;
        } else {
            this.words = new int[1];
            this.words[0] = (negative) ? -integer : integer;
        }
    }
    
    /**
     * Luo luvun sanoista
     * @param integer
     * @param negative 
     */
    protected Integer(int[] integer, boolean negative) {
        this.negative = negative;
        this.words = integer;
    }
    
    /**
     * Laskee merkitsemättömien nollien määrän
     * @param integer
     * @return 
     */
    private static int leadingZeroes(int integer[]) {
        int leading = 0;
        for (int i = integer.length-1; i >= 0; i--) {
            if (integer[i] > 0) {
                return leading;
            }
            
            leading++;
        }
        
        return -1;
    }
    
    /**
     * Poistaa sanataulukosta merkitsemättömät nollat
     * @param words
     * @return 
     */
    private static int[] removeLeadingZeroes(int[] words) {
        int zeroes = leadingZeroes(words);
        
        if (zeroes > 0) {
            int res[] = new int[words.length - zeroes];
            System.arraycopy(words, 0, res, 0, words.length - zeroes);
            return res;
        } else {
            return words;
        }
    }
    
    /**
     * Lisää nollien lyhyempään
     * @param a
     * @param b
     * @return 
     */
    public static int[][] padZeroes(int a[], int b[]) {
        int res[][] = {removeLeadingZeroes(a), removeLeadingZeroes(b)};
        
        if (res[0].length == res[1].length) {
            return res;
        }
        
        int max = (res[0].length > res[1].length) ? 0 : 1;
        int min = (max + 1) % 2;
        
        int newMin[] = new int[res[max].length];
        for (int i = 0; i < res[max].length; i++) {
            newMin[i] = (i < res[min].length) ? res[min][i] : 0;
        }
        
        res[min] = newMin;
        return res;
    }
    
    @Override
    public Integer add(final Integer other) {
        if (this.isZero()) return other;
        if (other.isZero()) return this;
        
        if (this.isNegative()) {
            return other.subtract(this.negate());
        } else if (other.isNegative()) {
            return this.subtract(other.negate());
        }

        int newWords[][] = padZeroes(this.words, other.words);
        
        int res[] = new int[newWords[0].length+1], k = 0;
        for (int i = 0; i < newWords[0].length; i++) {
            final int t = newWords[0][i] + newWords[1][i] + k;
            k = t / radix;
            res[i] = t % radix;
        }
        res[res.length-1] = k;
        
        return new Integer(res, false);
    }

    @Override
    public Integer subtract(final Integer other) {
        if (other.isZero()) return this;
        if (this.isZero()) {
            return other.negate();
        }
        
        if (this.isNegative()) {
            if (other.isNegative()) {
                // -2 - (-1) = -2 + 1 = 1 - 2
                return other.negate().subtract(this.negate());
            } else {
                // -1 - 1 = -(1 + 1)
                return this.negate().add(other).negate();
            }
        } else if (other.isNegative()) {
            // 1 - (-1) = 1 + 1
            return this.add(other.negate());
        }

        if (this.compareTo(other) < 0) {
            // 1 - 2 = -(2 - 1)
            return other.subtract(this).negate();
        }
        
        int newWords[][] = padZeroes(this.words, other.words);
        
        int res[] = new int[newWords[0].length], k = 0;
        for (int i = 0; i < newWords[0].length; i++) {
            final int t = newWords[0][i] - newWords[1][i] + k;
            
            if (t < 0) {
                k = -1;
                res[i] = radix + t;
            } else {
                k = 0;
                res[i] = t % radix;
            }
        }
        
        return new Integer(res, false);
    }

    /**
     * Ylemmät sanat kokonaisuluvusta
     * @param n
     * @return 
     */
    protected Integer highWords(int n) {
        int m = n + (words.length % 2);
        int words[] = new int[m];
        System.arraycopy(this.words, this.words.length - n, words, 0, n);
        
        if (this.words.length % 2 == 1) {
            words[m-1] = 0;
        }
        
        return new Integer(words, negative);
    }
    
    /**
     * Alemmat sanat kokonaisuluvusta
     * @param n
     * @return 
     */
    protected Integer lowWords(int n) {
        int m = words.length - n;
        
        int newWords[] = new int[m];
        System.arraycopy(this.words, 0, newWords, 0, m);
        
        return new Integer(newWords, negative);
    }
    
    /**
     * Siirtää lukua vasemmalle
     * @param count
     * @return 
     */
    protected Integer shiftLeft(int count) {
        int newWords[] = new int[this.words.length + count];
        for (int i = 0; i < count; i++) {
            newWords[i] = 0;
        }
        
        System.arraycopy(this.words, 0, newWords, count, this.words.length);
        return new Integer(newWords, negative);
    }
    
    /**
     * Kertolasku toteutettu Karatsuban algoritmilla.
     * @param other
     * @return 
     */
    @Override
    public Integer multiply(final Integer other) {
        if (other.isZero() || this.isZero()) {
            return ZERO;
        }
        
        final boolean neg = (this.isNegative() != other.isNegative());
        
        int newWords[][] = padZeroes(this.words, other.words);
        int m = newWords[0].length, m2 = m / 2;
        if (m == 1) {
            int t = this.words[0] * other.words[0];
            int res[] = {t % radix, t / radix};
            return new Integer(res, neg);
        }
        
        Integer a = new Integer(newWords[0], false),
                b = new Integer(newWords[1], false);

        Integer x1 = a.highWords(m2), x0 = a.lowWords(m2);
        Integer y1 = b.highWords(m2), y0 = b.lowWords(m2);
        
        Integer z2 = x1.multiply(y1);
        Integer z0 = x0.multiply(y0);
        Integer z1 = x1.add(x0).multiply(y1.add(y0))
                .subtract(z2.add(z0));
        
        m2 += m % 2;
        m += m % 2;
        
        Integer res = z2.shiftLeft(m).add(z1.shiftLeft(m2)).add(z0);
        
        return res;
    }

    @Override
    public Integer divide(final Integer other) {
        if (this.isZero()) return this;
        if (other.isZero()) {
            throw new ArithmeticException("Division by zero");
        }
        
        Integer divisor = other.abs();
        
        Integer q = ZERO, r = this.abs();
        while (r.compareTo(divisor) >= 0) {
            q = q.add(ONE);
            r = r.subtract(divisor);
        }
        
        q = other.isNegative() ? q.negate() : q;
        
        if (this.isNegative()) {
            q = q.negate();
            if (r.isZero()) {
                return q;
            } else {
                return q.subtract(ONE);
            }
        }
        
        return q;
    }

    @Override
    public Integer pow(int n) {
        Integer m = this, res = ONE;
        while (n > 0) {
            if ((n % 2) == 1) {
                res = m.multiply(res);
            }
            
            m = m.multiply(m);
            n = n >> 1;
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
        return new Integer(words, false);
    }
    
    @Override
    public Integer negate() {
        return new Integer(words, !negative);
    }
    
    @Override
    public boolean isZero() {
        for (int i = 0; i < words.length; i++) {
            if (words[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public boolean isNegative() {
        return negative;
    }
    
    /**
     * Täyttää kaksi merkkijonoa nollilla saman pituisiksi
     * @param a
     * @param b
     * @return 
     */
    private static String[] pad(final String a, final String b) {
        String[] res = {a, b};
        
        int max = (a.length() > b.length()) ? 0 : 1;
        int min = (max + 1) % 2;
        
        int diff = res[max].length() - res[min].length();
        for (int i = 0; i < diff; i++) {
            res[min] = "0" + res[min];
        }
        
        return res;
    }
    
    /**
     * Laskee yhteen merkkijonoa täynnä numeroita
     * @param a 
     * @param b
     * @return 
     */
    private static String add(final String a, final String b) {
        String res = "";

        String s[] = pad(a, b);
        
        int k = 0;
        for (int i = s[0].length()-1; i >= 0; i--) {
            final int sa = java.lang.Integer.parseInt(s[0].substring(i, i+1));
            final int sb = java.lang.Integer.parseInt(s[1].substring(i, i+1));
            
            final int t = sa + sb + k;
            k = t / 10;
            res = t % 10 + res;
        }
        
        return res;
    }
    
    /**
     * Laskee kahden numeroita sisältävän merkkijonon tulon
     * @param a
     * @param b
     * @return 
     */
    private static String multiply(final String a, final String b) {
        final String s[] = pad(a, b);
        
        String res = "";
        for (int i = s[0].length()-1; i >= 0; i--) {
            final int sa = java.lang.Integer.parseInt(s[0].substring(i, i+1));
            
            String l = "";
            
            int k = 0;
            for (int j = s[0].length()-1; j >= 0; j--) {
                int sb = java.lang.Integer.parseInt(s[1].substring(j, j+1));
                
                int t = (sa * sb) + k;
                k = t / 10;
                l = t % 10 + l;
            }
            l = k + l;
            
            for (int j = 0; j < s[0].length()-i-1; j++) {
                l = l + "0";
            }
            
            res = add(res, l);
        }
        
        return res;
    }
    
    /**
     * Laskee nollien määrän merkkijonon alussa
     * @param s
     * @return 
     */
    private static int leading(final String s) {
        int leading = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                return leading;
            }
            
            leading++;
        }
        
        return s.length() - 1;
    }
    /**
     * Korottaa merkkijonon luvut potenssiin
     * @param a luku
     * @param n eksponentti
     * @return 
     */
    private static String pow(final String a, int n) {
        String m = a, res = "1";
        while (n > 0) {
            if ((n % 2) == 1) {
                res = multiply(m, res);
            }
            
            m = multiply(m, m);
            n = n >> 1;
        }
        return res;
    }
    
    @Override
    public String toString() {
        final String sradix = "" + radix;
        
        String res = "";
        for (int i = 0; i < words.length; i++) {
            String c = "" + words[i];
            res = add(res, multiply(c, pow(sradix, i)));
        }
        
        res = res.substring(leading(res));
        return (isNegative() ? "-"+res : res);
    }
    
    @Override
    public int compareTo(Integer other) {
        if (this.isNegative() && !other.isNegative()) {
            return -1;
        } else if (!this.isNegative() && other.isNegative()) {
            return 1;
        }
        
        final int thisWords[] = removeLeadingZeroes(this.words);
        final int otherWords[] = removeLeadingZeroes(other.words);
        
        if (thisWords.length > otherWords.length) {
            return 1;
        } else if (thisWords.length < otherWords.length) {
            return -1;
        }
        
        for (int i = thisWords.length-1; i >= 0; i--) {
            if (thisWords[i] > otherWords[i]) {
                return 1;
            } else if (thisWords[i] < otherWords[i]) {
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
        
        if (this.isNegative() != other.isNegative()) {
            return false;
        }
        
        final int thisWords[] = removeLeadingZeroes(this.words);
        final int otherWords[] = removeLeadingZeroes(other.words);
        
        if (thisWords.length != otherWords.length) {
            return false;
        }

        for (int i = 0; i < thisWords.length; i++) {
            if (thisWords[i] != otherWords[i]) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public Integer inverse() {
        return ZERO;
    }
}
