package Types;

/**
 * Operaattoreita luvuille
 * @author riku
 */
public abstract class Number {
    /**
     * Yhteenlasku
     * @param other toinen luku
     * @return summa
     */
    public abstract Number add(Number other);
    
    /**
     * Vähennyslasku
     * @param other toinen luku
     * @return 
     */
    public abstract Number subtract(Number other);
    
    /**
     * Kertolasku
     * @param other toinen luku
     * @return 
     */
    public abstract Number multiply(Number other);
    
    /**
     * Jakolasku
     * @param other toinen luku
     * @return 
     */
    public abstract Number divide(Number other);
    
    /**
     * Potenssi
     * @param n
     * @return 
     */
    public abstract Number pow(int n);
    
    /**
     * Neliöjuuri
     * @return 
     */
    public abstract Number sqrt();
}