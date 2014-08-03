package math;

/**
 * Class for mathematical methods.
 * 
 * @author Sebastian Björkqvist
 */
public class MathUtil {

    /**
     * Returns the absolute value of an integer.
     * 
     * @param number 
     * @return Absolute value of number
     */
    public static int abs(int number) {
        if (number >= 0) {
            return number;
        }
        return -number;
    }
    
    /**
     * Returns the value of base raised to exponent.
     * 
     * This method has a linear time implementation at the moment. It could easily
     * be changed to a log time implementation.
     * 
     * This method calculates 0^0 to be 1.
     * 
     * @param base The base
     * @param exponent The exponent. Must be non-negative.
     * @return base^exponent
     * @throws IllegalArgumentException if exponent is non-negative.
     */
    public static int pow(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent " + exponent + " is negative.");
        }
        
        int result = 1;
        
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        
        return result;
    }
}
