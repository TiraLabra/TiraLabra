package TiraLabra.Number;

/**
 * Operaattoreita luvuille
 * @author riku
 * @param <T>
 */
public abstract class Number<T extends Number<T>> implements Comparable<T> {
    /**
     * Luo numeron
     * @param <T> luokka
     * @param type T.class
     * @param k arvo
     * @return new type(k)
     */
    public static <T extends Number<T>> T make(Class<T> type, int k) {
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
    public abstract T sqrt();
    
    /**
     * Kertoo onko luku negatiivinen
     * @return this < 0
     */
    public abstract boolean isNegative();
    
    /**
     * Onko luku nolla
     * @return this == 0
     */
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