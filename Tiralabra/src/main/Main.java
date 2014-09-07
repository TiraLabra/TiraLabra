package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import math.MathUtil;
import polynomial.IPolynomial;
import polynomial.IrreduciblePolynomialFinder;

/**
 * Main method for irreducible polynomial finder.
 *
 * Give as argument a list of characteristics and degrees separated by comma and
 * spaces.
 *
 * Example: If the input is 2,10 3,15 the program will output two irreducible
 * polynomials: one of characteristic 2 and degree 10 which is irreducible over
 * Z_2, and one of characteristic 3 and degree 15 which is irreducible over Z_3.
 *
 * The output will be in csv-format, and the program also reports the number of
 * polynomials it generated, and the number of polynomials it checked using
 * Rabin's algorithm.
 *
 * @author Sebastian Björkqvist
 */
public class Main {

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            writeUsage();
            return;
        }

        if (args[0].equals("cli")) {
            startCLI();
            return;
        }

        System.out.println("Characteristic, Degree, Polynomial, GeneratedPolynomials, NoOfTestsWithRabin");

        File file = new File(args[0]);
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File " + file + " could not be opened!");
            return;
        }
        String heuristic;
        if (args.length > 1) {
            heuristic = args[1];
        } else {
            heuristic = "naive";
        }

        boolean useSparsePolynomials;
        boolean checkForRoots;
        boolean smartChar2Generation;

        switch (heuristic) {
            case "naive":
                useSparsePolynomials = false;
                checkForRoots = false;
                smartChar2Generation = false;
                break;
            case "sparse":
                useSparsePolynomials = true;
                checkForRoots = false;
                smartChar2Generation = false;
                break;
            case "smartchar2":
                useSparsePolynomials = false;
                checkForRoots = false;
                smartChar2Generation = true;
                break;
            case "checkroots":
                useSparsePolynomials = false;
                checkForRoots = true;
                smartChar2Generation = false;
                break;
            case "sparse_smartchar2":
                useSparsePolynomials = true;
                checkForRoots = false;
                smartChar2Generation = true;
                break;
            case "sparse_checkroots":
                useSparsePolynomials = true;
                checkForRoots = true;
                smartChar2Generation = false;
                break;
            case "sparse_smartchar2_checkroots":
                useSparsePolynomials = true;
                checkForRoots = true;
                smartChar2Generation = true;
                break;
            default:
                System.out.println("Could not parse given heuristic command line argument: " + heuristic);
                return;
        }

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] lineSplit = line.split(",");
            int characteristic;
            int degree;

            try {
                characteristic = Integer.parseInt(lineSplit[0].trim());
                degree = Integer.parseInt(lineSplit[1].trim());
            } catch (Exception e) {
                System.err.println("Could not parse characteristic " + lineSplit[0]
                        + " or degree " + lineSplit[1]);
                continue;
            }

            if (!MathUtil.isPrime(characteristic)) {
                System.err.println("Characteristic " + characteristic + " is not prime. "
                        + "Ignoring row " + line);
                continue;
            }
            if (degree < 2) {
                System.err.println("Degree " + degree + " is smaller than 2. "
                        + "Ignoring row " + line);
                continue;
            }

            IPolynomial polynomial = IrreduciblePolynomialFinder.findIrreduciblePolynomial(
                    characteristic, degree, checkForRoots, smartChar2Generation,
                    useSparsePolynomials, false);
            int generated = IrreduciblePolynomialFinder.getNumberOfPolynomialsGeneratedLastTime();
            int checkedWithRabin = IrreduciblePolynomialFinder.getNumberOfPolynomialsCheckedUsingRabinsAlgorithmLastTime();
            System.out.println(characteristic + ", " + degree + ", " + polynomial
                    + ", " + generated + ", " + checkedWithRabin);
        }

    }

    private static void writeUsage() {
        System.out.println("Irreducible polynomial finder usage:");
        System.out.println("");
        System.out.println("To start the command line interface, give 'cli' "
                + "(without quotes) as the only command line argument.");
        System.out.println("The command line interface automatically uses the"
                + " most efficient heuristic for finding polynomials.");
        System.out.println("");
        System.out.println("To read data from a file, give the file name as the first argument.");
        System.out.println("The file must contain one characteristic-degree pair "
                + "on each row separated with a comma. The characteristic comes first.");
        System.out.println("Example:");
        System.out.println("2, 10");
        System.out.println("3, 15");
        System.out.println("");
        System.out.println("If the data is read from a file, you may also specify"
                + " (using a second command line argument) what heuristic the algorithm will use.");
        System.out.println("Possible choices are: ");
        System.out.println("    naive - Generates evenly distributed polynomials "
                + "and inserts them directly into Rabin's irreducibility test.");
        System.out.println("    sparse - Generates shorter polynomials (more zero coefficients)");
        System.out.println("    checkroots - Checks if the generated polynomial has a root before "
                + "using Rabin's irreducibility test");
        System.out.println("    sparse_checkroots - Combination of sparse and checkroots ");
        System.out.println("    smartchar2 - If the characteristic is 2, performs a simple and very quick test to ensure that "
                + "the generated polynomial never has a root.");
        System.out.println("    sparse_smartchar2 - Combination of sparse and smartchar2.");
        System.out.println("    sparse_smartchar2_checkroots - Combination of sparse, smartchar2 and checkroots. "
                + "This is sensible mainly if the input contains both characteristic 2 and other characteristics.");
        System.out.println("");
        System.out.println("The results will be printed in csv-format to the standard output. It may be"
                + " redirected to a file from there.");
    }

    private static void startCLI() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Irreducible polynomial finder --");
        System.out.println("");

        int characteristic;
        int degree;
        String input = null;
        while (true) {
            try {
                System.out.print("Characteristic of polynomial: ");
                input = scanner.nextLine();
                characteristic = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number " + input);
                continue;
            }
            if (!MathUtil.isPrime(characteristic)) {
                System.out.println("The characteristic " + characteristic + " is not prime!");
            } else {
                break;
            }
        }
        while (true) {
            try {
                System.out.print("Degree of polynomial: ");
                input = scanner.nextLine();
                degree = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number " + input);
                continue;
            }
            if (degree < 1) {
                System.out.println("The degree must be positive!");
            } else {
                break;
            }
        }        
        
        System.out.println("Starting calculation...");
        long startTime = System.currentTimeMillis();
        IPolynomial polynomial = IrreduciblePolynomialFinder.findIrreduciblePolynomial(characteristic, degree, true, true, true, true);
        long endTime = System.currentTimeMillis();
        System.out.println("Irreducible polynomial: " + polynomial);
        System.out.println("Time elapsed: " + ((endTime - startTime) / 1000.0) + " seconds");
    }
}
