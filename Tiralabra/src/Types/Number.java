package Types;

/**
 * Operaattoreita luvuille
 * @author riku
 */
public abstract class Number implements Comparable<Number> {
    /**
     * Yhteenlasku
     * @param other toinen luku
     * @return this + other
     */
    public abstract Number add(Number other);
    
    /**
     * Vähennyslasku
     * @param other toinen luku
     * @return this - other
     */
    public abstract Number subtract(Number other);
    
    /**
     * Kertolasku
     * @param other toinen luku
     * @return this * other
     */
    public abstract Number multiply(Number other);
    
    /**
     * Jakolasku
     * @param other toinen luku
     * @return this / other
     */
    public abstract Number divide(Number other);
    
    /**
     * Potenssi
     * @param n
     * @return this^n
     */
    public abstract Number pow(int n);
    
    /**
     * Neliöjuuri Newton-Raphsonin menetelmällä
     * @param guess
     * @param iter
     * @return 
     */
    /*public Number sqrt(Number guess, int iter) {
        Number n = guess;
        for (int i = 0; i < iter; i++) {
            Number f = n.multiply(n).subtract(this);
            Number d = n.add(n);
            n = n.subtract(f.divide(d));
        }
        return n;
    }*/
    
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
    
    /**
     * Itseisarvo
     * @return |this|
     */
    public abstract Number abs();
    
    /**
     * Vastaluku
     * @return -this
     */
    public abstract Number negate();
}