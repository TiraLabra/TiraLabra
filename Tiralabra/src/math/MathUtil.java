package math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * easily be changed to a log time implementation. This method also easily
     * overflows. If you want to calculate powers modulo an integer, use the
     * method powModulo.
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
     * Calculates number^exponent mod modulo
     *
     * @param base any integer
     * @param exponent a non-negative integer
     * @param modulo a positive integer
     * @return (number^exponent) mod (modulo)
     * @exception IllegalArgumentException if modulo < 1
     * @exception IllegalArgumentException if exponent < 0
     */
    public static int powModulo(int base, int exponent, int modulo) {
        if (modulo < 1) {
            throw new IllegalArgumentException("Modulo must be positive.");
        }

        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative.");
        }

        if (exponent == 0) {
            return 1;
        }

        int indexOfHighestOneBit = log2(exponent);

        long[] numberExponentiated = new long[indexOfHighestOneBit + 1];
        numberExponentiated[0] = base % modulo;

        for (int i = 1; i < numberExponentiated.length; i++) {
            numberExponentiated[i] = (numberExponentiated[i - 1] * numberExponentiated[i - 1]) % modulo;
        }

        long result = numberExponentiated[numberExponentiated.length - 1];

        for (int i = numberExponentiated.length - 2; i >= 0; i--) {
            if (isOne(exponent, i)) {
                result = (result * numberExponentiated[i]) % modulo;
            }
        }

        while (result < 0) {
            result += modulo;
        }

        return (int) result;
    }

    /**
     * Returns the index of the highest one bit of a given number
     *
     * @param number a positive integer
     * @return the index of the highest one bit of number
     * @exception IllegalArgumentException if number <= 0
     */
    static int log2(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive.");
        }
        return 31 - Integer.numberOfLeadingZeros(number);
    }

    /**
     * Tells if a given index of a number in binary form is one
     *
     * @param number a positive integer
     * @param index the desired index, from 0 to 31.
     * @return true if index is 1, false if index is 0
     * @exception IllegalArgumentException if number < 0
     * @exception IllegalArgumentException if index not in range 0...31.
     */
    static boolean isOne(int number, int index) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be positive.");
        }
        if (index < 0 || index > 31) {
            throw new IllegalArgumentException("Index must be in range 0...31.");
        }

        /* The only index that is not zero in (1 << index) is 
         * the index 'index'. Therefore, if number is one at the 
         * index 'index', the method returns true, and if number 
         * is zero at the index 'index', the method returns false.
         */        
        return (number & (1 << index)) != 0;
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

    /**
     * Returns all prime numbers that are smaller or equal than the given
     * number.
     *
     * Returns empty list if n is smaller than 2.
     *
     * The method used is the sieve of Eratosthenes.
     *
     * @param n Largest number to consider
     * @return List of primes from smallest to largest.
     */
    public static List<Integer> getAllPrimesSmallerOrEqualThanN(int n) {
        int[] numberArray = new int[n + 1];
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i < numberArray.length; i++) {
            numberArray[i] = i;
            isPrime[i] = true;
        }

        for (int i = 2; i < numberArray.length; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < numberArray.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();

        for (int i = 2; i < numberArray.length; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        return primeList;
    }

    /**
     * Finds unique prime factors of an integer.
     *
     * @param n The integer to factorize
     * @throws IllegalArgumentException if n is smaller than 2.
     * @return A set containing the unique prime factors of n.
     */
    public static Set<Integer> getUniquePrimeFactors(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("The given number n is smaller than 2");
        }
        
        Set<Integer> uniqueFactors = new HashSet<>();
        List<Integer> primes = getAllPrimesSmallerOrEqualThanN((int) Math.floor(Math.sqrt(n)));

        for (Integer p : primes) {
            if (p * p > n) {
                break;
            }
            while (n % p == 0) {
                uniqueFactors.add(p);
                n /= p;
            }
        }
        if (n > 1) {
            uniqueFactors.add(n);
        }

        return uniqueFactors;
    }
}
