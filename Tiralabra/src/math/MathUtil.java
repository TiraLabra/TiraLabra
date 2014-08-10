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
     * This method has a linear time implementation at the moment. It could
     * easily be changed to a log time implementation.
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

    /**
     * Performs the Extended Euclidean algorithm on the given integers.
     *
     * The result will be gcd = a*x + b*y, where gcd is the greatest common
     * divisor of a and b.
     *
     * @param a
     * @param b
     * @return Numbers gcd, x and y such that gcd = a*x + b*y
     */
    public static ExtendedEuclideanResult extendedEuclideanAlgorithm(int a, int b) {
        if (b == 0) {
            return new ExtendedEuclideanResult(a, 1, b);
        }

        ExtendedEuclideanResult resultFromNextStep = extendedEuclideanAlgorithm(b, a % b);

        int gcd = resultFromNextStep.gcd;
        int x = resultFromNextStep.y;
        int y = resultFromNextStep.x - (a / b) * resultFromNextStep.y;

        return new ExtendedEuclideanResult(gcd, x, y);
    }
    
    /**
     * Finds the inverse of the number n modulo p.
     * 
     * The inverse always exists if p is a prime. Otherwise it might not exist,
     * in which case the algorithm returns -1;
     * 
     * @param n The number whose inverse is to be found.
     * @param p The modulo.
     * @return The inverse of n modulo p, -1 if the inverse doesn't exist.
     */
    public static int getInverseModP(int n, int p) {
        ExtendedEuclideanResult result = extendedEuclideanAlgorithm(n, p);
        
        if (result.gcd != 1) {
            return -1;
        }
        
        return result.x;
    }

    /**
     * Checks if a number is prime.
     *
     * The implementation is naive. It divides the given number with 2 and all
     * odd numbers smaller than the square root of n.
     *
     * 1 is not considered to be a prime.
     *
     * @param n The number to examine
     * @return true if n is prime, false if it isn't
     * @throws IllegalArgumentException if n is smaller than 2.
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("n must be 2 or larger!");
        }

        if (n == 2) {
            return true;
        }
        
        if (n % 2 == 0) {
            return false;
        }

        int testNumber = 3;

        while (testNumber * testNumber <= n) {
            if (n % testNumber == 0) {
                return false;
            }
            testNumber += 2;
        }

        return true;
    }
}
