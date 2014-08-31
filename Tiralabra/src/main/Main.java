package main;

import java.util.Scanner;
import polynomial.IPolynomial;
import polynomial.IrreduciblePolynomialFinder;

/**
 * Main method for irreducible polynomial finder.
 * 
 * Give as argument a list of characteristics and degrees separated by comma and spaces.
 * 
 * Example: If the input is 2,10 3,15 the program will output two irreducible polynomials:
 * one of characteristic 2 and degree 10 which is irreducible over Z_2, and one
 * of characteristic 3 and degree 15 which is irreducible over Z_3.
 * 
 * The output will be in csv-format, and the program also reports the number of
 * polynomials it tried.
 * 
 * @author Sebastian Björkqvist
 */
public class Main {

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            startCLI();
            return;
        }
        
        System.out.println("Characteristic, Degree, Polynomial, Tries");
        
        for (String arg : args) {
            String[] thisInput = arg.split(",");
            int characteristic = Integer.parseInt(thisInput[0].trim());
            int degree = Integer.parseInt(thisInput[1].trim());
            IPolynomial polynomial = IrreduciblePolynomialFinder.findIrreduciblePolynomial(characteristic, degree, true, true, false);
            int numberOfTries = IrreduciblePolynomialFinder.getNumberOfPolynomialsGeneratedLastTime();
            System.out.print(characteristic);
            System.out.print(", " + degree);
            System.out.print(", " + polynomial);
            System.out.println(", " + numberOfTries);
        }
        
    }

    private static void startCLI() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Irreducible polynomial finder --");
        System.out.println("");
        System.out.print("Characteristic of polynomial: ");
        int characteristic = Integer.parseInt(scanner.nextLine());
        System.out.print("Degree of polynomial: ");
        int degree = Integer.parseInt(scanner.nextLine());

        System.out.println("Starting calculation...");
        long startTime = System.currentTimeMillis();
        IPolynomial polynomial = IrreduciblePolynomialFinder.findIrreduciblePolynomial(characteristic, degree, true, true, true);
        long endTime = System.currentTimeMillis();
        System.out.println("Irreducible polynomial: " + polynomial);
        System.out.println("Time elapsed: " + ((endTime - startTime) / 1000.0) + " seconds");
    }
}
