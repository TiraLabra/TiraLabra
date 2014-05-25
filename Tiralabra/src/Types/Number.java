package Types;

/**
 * Operaattoreita geneerisille luvuille
 * @author riku
 * @param <T> 
 */
public interface Number<T> {
    /**
     * Yhteenlasku
     * @param other toinen luku
     * @return summa
     */
    public T add(T other);
    
    /**
     * Vähennyslasku
     * @param other toinen luku
     * @return 
     */
    public T subtract(T other);
    
    /**
     * Kertolasku
     * @param other toinen luku
     * @return 
     */
    public T multiply(T other);
    
    /**
     * Jakolasku
     * @param other toinen luku
     * @return 
     */
    public T divide(T other);
    
    /**
     * Potenssi
     * @param other toinen luku
     * @return 
     */
    public T pow(T other);
}