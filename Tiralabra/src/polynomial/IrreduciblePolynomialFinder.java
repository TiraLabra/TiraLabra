package polynomial;

import java.security.SecureRandom;
import java.util.Random;
import math.MathUtil;
import polynomial.impl.linkedlist.LinkedListPolynomial;

/**
 *
 * @author Sebastian Björkqvist
 */
public class IrreduciblePolynomialFinder {

    private static int numberOfPolynomialsCheckedUsingRabinsAlgorithmLastTime;
    private static int numberOfPolynomialsGeneratedLastTime;

    /**
     * Returns an irreducible polynomial with the given characteristic and
     * degree.
     *
     * This method generates polynomials randomly and uses Rabin's irreducibility
     * test to check if the generated polynomial is irreducible.
     * 
     * The time complexity of this method is about O(c*(d^3)), where c is the degree
     * and d is the characteristic.
     * 
     * Note that the method starts becoming increasingly slow if the product of
     * the degree and the characteristic exceeds about 500.
     *
     * @param characteristic
     * @param degree
     * @param checkForRoots If true, checks if a polynomial has a root before
     * using Rabin's irreducibility test.
     * @param smartChar2Generation If true and if characteristic is 2 performs a simple check 
     * to ensure that the generated polynomial doesn't have a root. If this is true and
     * the characteristic is 2, it is advisable to set checkForRoots to false since it isn't needed anymore.
     * @param useSparsePolynomials If true, generates shorter polynomials.
     * @param debugPrint If true, prints info about number of tries.
     * @return Polynomial of given degree that is irreducible over the ring with
     * the given characteristic.
     * @throws IllegalArgumentException if the characteristic is not prime.
     * @throws IllegalArgumentException if the degree is negative.
     */
    public static IPolynomial findIrreduciblePolynomial(int characteristic, int degree, 
            boolean checkForRoots, boolean smartChar2Generation, boolean useSparsePolynomials,
            boolean debugPrint) {
        if (characteristic < 2) {
            throw new IllegalArgumentException("Characteristic " + characteristic + " is smaller than 2.");
        }        
        if (!MathUtil.isPrime(characteristic)) {
            throw new IllegalArgumentException("Characteristic " + characteristic + " is not a prime.");
        }
        if (degree < 0) {
            throw new IllegalArgumentException("Degree " + degree + " is negative!");
        }

        Random random = new SecureRandom();

        int totalAmountOfTries = 0;
        int triesUsingRabinsAlgorithm = 0;
        double ratioOfZeros = 0.7;

        while (true) {
            totalAmountOfTries++;
            if (debugPrint) {
                System.out.println("Try " + totalAmountOfTries);
            }
            IPolynomial candidate;
            if (useSparsePolynomials) {
                candidate = createSparseCandidatePolynomial(characteristic, degree, ratioOfZeros, smartChar2Generation, random);
            } else {
                candidate = createEvenlyDistributedPolynomial(characteristic, degree, smartChar2Generation, random);
            }
            if ((!smartChar2Generation || characteristic != 2) && checkForRoots && degree > 1 && hasRoot(candidate)) {
                if (debugPrint) {
                    System.out.println("Polynomial " + candidate + " has a root.");
                }
                continue;
            }
            boolean isReducible = PolynomialUtil.isReducible(candidate, debugPrint);
            triesUsingRabinsAlgorithm++;
            if (!isReducible) {
                if (debugPrint) {
                    System.out.println("Total amount of tries: " + totalAmountOfTries);
                    System.out.println("Tries using Rabin's algorithm: " + triesUsingRabinsAlgorithm);
                }
                numberOfPolynomialsGeneratedLastTime = totalAmountOfTries;
                numberOfPolynomialsCheckedUsingRabinsAlgorithmLastTime = triesUsingRabinsAlgorithm;
                return candidate;
            }
        }
    }

    /**
     * Creates a candidate polynomial randomly.
     * 
     * The parameter ratioOfZeros should be a double between 0 and 1. 
     * 
     * If the characteristic is 2, this method will never return a polynomial
     * with a root.
     * 
     * @param characteristic
     * @param degree
     * @param ratioOfZeros A double between 0 and 1 that decides the ratio of zero coefficients.
     * @param smartChar2Generation If true and if characteristic is 2 performs a simple check 
     * to ensure that the polynomial doesn't have a root.
     * @param random
     * @return 
     */
    private static IPolynomial createSparseCandidatePolynomial(int characteristic, int degree, 
            double ratioOfZeros, boolean smartChar2Generation, Random random) {
        IPolynomial candidate = new LinkedListPolynomial(characteristic);
        int leadingCoefficient = 1;
        candidate.addTerm(leadingCoefficient, degree);
        
        int amountOfNonZeroCoefficients = 1;
        
        for (int exponent = degree - 1; exponent > 0; exponent--) {
            int coefficient = getSparseCoefficient(characteristic, false, ratioOfZeros, random);
            if (coefficient != 0) {
                candidate.addTerm(coefficient, exponent);
                amountOfNonZeroCoefficients++;
            }
        }
        int constantCoefficient = getSparseCoefficient(characteristic, true, ratioOfZeros, random);
        candidate.addTerm(constantCoefficient, 0);
        amountOfNonZeroCoefficients++;
        
        // If the characteristic is 2 and we have an even amount of terms,
        // the polynomial will automatically have the root 1. Thus if this
        // is the case, we add a term to change the amount of non-zero coefficents.        
        if (smartChar2Generation && characteristic == 2 && amountOfNonZeroCoefficients % 2 == 0) {
            candidate.addTerm(1, 1);
        }
        
        return candidate;
    }
    
    private static IPolynomial createEvenlyDistributedPolynomial(int characteristic, int degree, 
            boolean smartChar2Generation, Random random) {
        IPolynomial candidate = new LinkedListPolynomial(characteristic);
        int leadingCoefficient = 1;
        candidate.addTerm(leadingCoefficient, degree);
        
        int amountOfNonZeroCoefficients = 1;
        
        for (int exponent = degree - 1; exponent > 0; exponent--) {
            int coefficient = getEvenlyDistributedCoefficient(characteristic, false, random);
            if (coefficient != 0) {
                candidate.addTerm(coefficient, exponent);
                amountOfNonZeroCoefficients++;
            }
        }
        int constantCoefficient = getEvenlyDistributedCoefficient(characteristic, true, random);
        candidate.addTerm(constantCoefficient, 0);
        amountOfNonZeroCoefficients++;
        
        // If the characteristic is 2 and we have an even amount of terms,
        // the polynomial will automatically have the root 1. Thus if this
        // is the case, we add a term to change the amount of non-zero coefficents.        
        if (smartChar2Generation && characteristic == 2 && amountOfNonZeroCoefficients % 2 == 0) {
            candidate.addTerm(1, 1);
        }
        
        return candidate;
    }

    /**
     * Evaluates a polynomial in all values to see if it has a root other than
     * 0.
     *
     * If it has a root and it's degree is higher than 1, it is reducible.
     *
     * @param polynomial The polynomial to check
     * @return true if the polynomial has a root other than 0, false if it
     * doesn't.
     */
    private static boolean hasRoot(IPolynomial polynomial) {
        int characteristic = polynomial.getCharacteristic();
        
        for (int i = 1; i < polynomial.getCharacteristic(); i++) {
            if (polynomial.evaluate(i) % characteristic == 0) {
                return true;
            }
        }
        
        return false;
    }


    private static int getEvenlyDistributedCoefficient(int characteristic, boolean mustBePositive,
            Random random) {
        int coefficient;
        if (mustBePositive) {
            coefficient = random.nextInt(characteristic - 1) + 1;
        } else {
            coefficient = random.nextInt(characteristic);
        }

        return coefficient;
    }    
    
    private static int getSparseCoefficient(int characteristic, boolean mustBePositive,
            double ratioOfZeros, Random random) {
        double randomNumber = random.nextDouble();

        if (!mustBePositive && randomNumber < ratioOfZeros) {
            return 0;
        }

        int coefficient = random.nextInt(characteristic - 1) + 1;

        return coefficient;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getSparseCoefficient(5, false, 0.7, new Random()));
        }
    }

    /**
     * Returns the amount of polynomials that were tested with Rabin's algorithm when
     * findIrreduciblePolynomial was last called.
     *
     * @return Number of polynomials tested with Rabin's algorithm.
     */
    public static int getNumberOfPolynomialsCheckedUsingRabinsAlgorithmLastTime() {
        return numberOfPolynomialsCheckedUsingRabinsAlgorithmLastTime;
    }

    /**
     * Returns the number of polynomials that were generated when 
     * findIrreduciblePolynomial was last called.
     * 
     * @return Number of polynomials generated.
     */
    public static int getNumberOfPolynomialsGeneratedLastTime() {
        return numberOfPolynomialsGeneratedLastTime;
    }
    
    
}
