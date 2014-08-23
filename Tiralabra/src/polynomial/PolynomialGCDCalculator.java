package polynomial;

/**
 * Class for calculating the greatest common divisor of two polynomials.
 *
 * @author Sebastian Björkqvist
 */
public class PolynomialGCDCalculator {

    /**
     * Calculates the greatest common divisor of two polynomials.
     *
     * Note that this is very likely to fail with polynomials of characteristic
     * 0, since non-integer coefficients can't be represented. Also, this method
     * fails if different implementations of polynomials are used and the
     * conversion between them isn't supported.
     *
     * Note that the gcd is unique only up to an invertible constant.
     * 
     * @param a First polynomial. The degree of this polynomial must be at least
     * as large as the degree of the second polynomial.
     * @param b Second polynomial
     * @return The greatest common divisor
     * @throws IllegalArgumentException if the polynomials have different
     * characteristic.
     */
    public static IPolynomial gcd(IPolynomial a, IPolynomial b) {
        if (a.getCharacteristic() != b.getCharacteristic()) {
            throw new IllegalArgumentException("The characteristic of the polynomials don't match!");
        }

        if (a.getDegree() < b.getDegree()) {
            return gcdPrivate(b, a);
        }

        return gcdPrivate(a, b);
    }

    /*
     This method is used to avoid having to check the characteristics
     and degrees at every step of the calculation.    
     */
    private static IPolynomial gcdPrivate(IPolynomial a, IPolynomial b) {
        // If b is the zero polynomial, we've reached the end of the recursion.
        if (b.getDegree() == -1) {
            return a;
        }

        return gcdPrivate(b, a.divide(b).remainder);
    }

}
