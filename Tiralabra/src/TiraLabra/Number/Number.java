package TiraLabra.Number;

/**
 * Operaattoreita luvuille
 * @author riku
 * @param <T>
 */
public abstract class Number<T extends Number> implements Comparable<T> {
    public static Number make(Class<? extends Number> type, int k) {
        try {
            return type.getConstructor(int.class).newInstance(k);
        } catch (Exception ex ) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Yhteenlasku
     * @param other toinen luku
     * @return this + other
     */
    public abstract T add(T other);
    
    /**
     * Vähennyslasku
     * @param other toinen luku
     * @return this - other
     */
    public abstract T subtract(T other);
    
    /**
     * Kertolasku
     * @param other toinen luku
     * @return this * other
     */
    public abstract T multiply(T other);
    
    /**
     * Jakolasku
     * @param other toinen luku
     * @return this / other
     */
    public abstract T divide(T other);
    
    /**
     * Potenssi
     * @param n
     * @return this^n
     */
    public abstract T pow(int n);
    
    /**
     * Neliöjuuri
     * @return 
     */
    public abstract Number sqrt();
    
    /**
     * Kertoo onko luku negatiivinen
     * @return this < 0
     */
    public abstract boolean isNegative();
    
    public abstract boolean isZero();
    
    /**
     * Itseisarvo
     * @return |this|
     */
    public abstract T abs();
    
    /**
     * Vastaluku
     * @return -this
     */
    public abstract T negate();

    @Override
    public abstract int compareTo(T o);
}