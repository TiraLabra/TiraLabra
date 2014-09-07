package polynomial;

import datastructures.IntegerNode;
import datastructures.SortedIntegerList;
import math.MathUtil;
import polynomial.impl.array.ArrayPolynomial;
import polynomial.impl.linkedlist.LinkedListPolynomial;

/**
 * Class for calculating the greatest common divisor of two polynomials.
 *
 * @author Sebastian Björkqvist
 */
public class PolynomialUtil {

    /**
     * Calculates the greatest common divisor of two polynomials.
     *
     * Note that this is very likely to fail with polynomials of characteristic
     * 0, since non-integer coefficients can't be represented. Also, this method
     * fails if different implementations of polynomials are used and the
     * conversion between them isn't supported.
     *
     * Note that the greatest common divisor is unique only up to an invertible
     * constant.
     *
     * @param a First polynomial. The degree of this polynomial must be at least
     * as large as the degree of the second polynomial.
     * @param b Second polynomial
     * @return The greatest common divisor
     * @throws IllegalArgumentException if the polynomials have different
     * characteristic.
     * @throws IllegalArgumentException if either polynomial is null.
     */
    public static IPolynomial gcd(IPolynomial a, IPolynomial b) {
        if (a == null) {
            throw new IllegalArgumentException("First polynomial is null!");
        }
        if (b == null) {
            throw new IllegalArgumentException("Second polynomial is null!");
        }
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

    /**
     * Checks if a polynomial of positive characteristic is reducible.
     *
     * This method uses Rabin's test of irreducibility. The time complexity of
     * this method is O(c*(d^3)*log(log d)), where c is the characteristic and d is the
     * degree of the polynomial to be tested.
     *
     * @param polynomial The polynomial to test.
     * @param debugPrint If true, prints some info when the algorithm is
     * running.
     * @return true if the polynomial is reducible, false if it is irreducible.
     * @throws IllegalArgumentException if the characteristic of the polynomial
     * is 0.
     * @throws IllegalArgumentException if the given polynomial is null.
     */
    public static boolean isReducible(IPolynomial polynomial, boolean debugPrint) {
        if (polynomial == null) {
            throw new IllegalArgumentException("The given polynomial is null!");
        }
        int characteristic = polynomial.getCharacteristic();

        if (characteristic == 0) {
            throw new IllegalArgumentException("Irreducibility testing of polynomials "
                    + "with characteristic 0 is not supported.");
        }

        if (polynomial.getDegree() <= 1) {
            return false;
        }

        Class type = polynomial.getClass();

        if (type == LinkedListPolynomial.class) {
            LinkedListPolynomial p = (LinkedListPolynomial) polynomial;
            return isReducible(p, debugPrint);
        }
//        if (type == ArrayPolynomial.class) {
//            ArrayPolynomial p = (ArrayPolynomial) polynomial;
//            return isReducible(p, debugPrint);
//        }        

        throw new UnsupportedOperationException("Unsupported polynomial type " + type);

    }

    private static boolean isReducible(LinkedListPolynomial polynomial, boolean debugPrint) {
        int characteristic = polynomial.getCharacteristic();
        int degree = polynomial.getDegree();

        SortedIntegerList primeFactorsOfDegree = MathUtil.getUniquePrimeFactors(degree);

        if (debugPrint) {
            System.out.println("Checking if " + polynomial + " is irreducible.");
        }

        IntegerNode factorNode = primeFactorsOfDegree.getLargestNode();

        // Checking if the polynomial has a factor of degree dividing its own degree:
        while (factorNode != null) {
            int factor = factorNode.getValue();
            int exponentToCheck = degree / factor;

            if (debugPrint) {
                System.out.println("    Checking polynomial x^" + characteristic + "^" + exponentToCheck);
            }

            IPolynomial remainder = calculateXExponentiatedModuloF(characteristic, exponentToCheck, polynomial);

            remainder.addTerm(-1, 1);

            IPolynomial gcd = gcd(polynomial, remainder);

            // If gcd is not a constant, the polynomial is reducible.
            if (gcd.getDegree() != 0) {
                return true;
            }
            factorNode = factorNode.getPrev();
        }
        if (debugPrint) {
            System.out.println("    Checking polynomial x^" + characteristic + "^" + degree);
        }

        IPolynomial remainder = calculateXExponentiatedModuloF(characteristic, degree, polynomial);

        remainder.addTerm(-1, 1);

        if (remainder.getDegree() == -1) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isReducible(ArrayPolynomial polynomial, boolean debugPrint) {
        int characteristic = polynomial.getCharacteristic();
        int degree = polynomial.getDegree();

        SortedIntegerList primeFactorsOfDegree = MathUtil.getUniquePrimeFactors(degree);

        if (debugPrint) {
            System.out.println("Checking if " + polynomial + " is irreducible.");
        }

        IntegerNode factorNode = primeFactorsOfDegree.getLargestNode();

        // Checking if the polynomial has a factor of degree dividing its own degree:
        while (factorNode != null) {
            int factor = factorNode.getValue();
            int exponentToCheck = degree / factor;

            IPolynomial polynomialToCheck = new ArrayPolynomial(characteristic);

            polynomialToCheck.addTerm(1, MathUtil.pow(characteristic, exponentToCheck));
            polynomialToCheck.addTerm(-1, 1);

            if (debugPrint) {
                System.out.println("    Checking polynomial " + polynomialToCheck);
            }

            IPolynomial remainder = polynomialToCheck.divide(polynomial).remainder;

            IPolynomial gcd = gcd(polynomial, remainder);

            // If gcd is not a constant, the polynomial is reducible.
            if (gcd.getDegree() != 0) {
                return true;
            }
            factorNode = factorNode.getPrev();
        }
        // Checking if the polynomial has a factor of degree not dividing its own degree:
        IPolynomial polynomialToCheck = new ArrayPolynomial(characteristic);

        polynomialToCheck.addTerm(1, MathUtil.pow(characteristic, degree));
        polynomialToCheck.addTerm(-1, 1);

        if (debugPrint) {
            System.out.println("    Checking polynomial " + polynomialToCheck);
        }

        IPolynomial remainder = polynomialToCheck.divide(polynomial).remainder;

        if (remainder.getDegree() == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Calculates x^base^exponent mod f using repeated squaring.
     *
     * At the moment this method only supports linked list polynomials.
     *
     * @param base
     * @param exponent
     * @param f The polynomial for which modulo is taken.
     * @return x^base^exponent mod f
     * @throws IllegalArgumentException if base or exponent is non-positive.
     */
    static IPolynomial calculateXExponentiatedModuloF(int base, int exponent, IPolynomial f) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base is non-positive.");
        }
        if (exponent <= 0) {
            throw new IllegalArgumentException("Exponent is non-positive.");
        }

        int characteristic = f.getCharacteristic();

        IPolynomial xBase = new LinkedListPolynomial(characteristic);
        xBase.addTerm(1, base);
        
        xBase = xBase.divide(f).remainder;

        for (int i = 1; i < exponent; i++) {
            IPolynomial xBaseCopy = xBase.createCopyOfPolynomial();

            for (int j = 0; j < base - 1; j++) {
                xBase = (xBase.multiply(xBaseCopy)).divide(f).remainder;
            }
        }

        return xBase;
    }

}
