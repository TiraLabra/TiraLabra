package main;

import java.util.Random;
import polynomial.IPolynomial;
import polynomial.PolynomialUtil;
import polynomial.impl.array.ArrayPolynomial;
import polynomial.impl.linkedlist.LinkedListPolynomial;

/**
 *
 * @author Sebastian Björkqvist
 */
public class Main {

    public static void main(String[] args) {

        int characteristic = 7;

        IPolynomial polynomial = new ArrayPolynomial(characteristic);

        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 3);

        boolean polynomial1IsIrreducible = true;

        for (int i = 0; i < characteristic; i++) {
            if (polynomial.evaluate(i) % characteristic == 0) {
                polynomial1IsIrreducible = false;
            }
        }

        if (polynomial1IsIrreducible) {
            System.out.println(polynomial + " is irreducible mod " + characteristic);
        } else {
            System.out.println(polynomial + " is not irreducible mod " + characteristic);
        }

        IPolynomial polynomial2 = new ArrayPolynomial(characteristic);

        polynomial2.addTerm(1, 0);
        polynomial2.addTerm(2, 2);
        polynomial2.addTerm(1, 3);

        boolean polynomial2IsIrreducible = true;

        for (int i = 0; i < characteristic; i++) {
            if (polynomial2.evaluate(i) % characteristic == 0) {
                polynomial2IsIrreducible = false;
            }
        }

        if (polynomial2IsIrreducible) {
            System.out.println(polynomial2 + " is irreducible mod " + characteristic);
        } else {
            System.out.println(polynomial2 + " is not irreducible mod " + characteristic);
        }

        IPolynomial polynomial3 = new ArrayPolynomial(characteristic);

        polynomial3.addTerm(1, 0);
        polynomial3.addTerm(2, 2);
        polynomial3.addTerm(-1, 3);

        boolean polynomial3IsIrreducible = true;

        for (int i = 0; i < characteristic; i++) {
            if (polynomial.evaluate(i) % characteristic == 0) {
                polynomial3IsIrreducible = false;
            }
        }

        if (polynomial3IsIrreducible) {
            System.out.println(polynomial3 + " is irreducible mod " + characteristic);
        } else {
            System.out.println(polynomial3 + " is not irreducible mod " + characteristic);
        }

        IPolynomial polynomial4 = polynomial.multiply(polynomial2);
        IPolynomial polynomial5 = polynomial.multiply(polynomial3);

        System.out.println(polynomial4);
        System.out.println(polynomial5);

        System.out.println("Expected gcd: " + polynomial);
        System.out.println("Returned gcd: " + PolynomialUtil.gcd(polynomial4, polynomial5));

        int degree = 25;
        
        System.out.println("Irreducible polynomial mod 2 of degree " + degree + ": " 
                + findIrreduciblePolynomialOfCharacteristic2(degree));
    }

    public static IPolynomial findIrreduciblePolynomialOfCharacteristic2(int degree) {
        Random random = new Random();

        int tries = 0;
        
        while (true) {
            tries++;
            System.out.println("Try " + tries);
            IPolynomial candidate = new LinkedListPolynomial(2);
            candidate.addTerm(1, degree);
            for (int exponent = degree - 1; exponent > 0; exponent--) {
                int coefficient = random.nextInt(2);
                if (coefficient != 0) {
                    candidate.addTerm(1, exponent);
                }
            }
            candidate.addTerm(1, 0);
            if (!PolynomialUtil.isReducible(candidate)) {
                System.out.println("Tries: " + tries);
                return candidate;
            }
        }
    }
}
