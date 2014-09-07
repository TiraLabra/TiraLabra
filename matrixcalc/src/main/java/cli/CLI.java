package cli;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import math.*;

/**
 * Command line interface for the matrix calculator.
 *
 * @author ydna
 */
public class CLI {

    /**
     * True if program is active.
     */
    private boolean active;

    /**
     * Reads user input.
     */
    private final Scanner read;

    /**
     * Key-value container for real matrices.
     */
    HashMap<String, RealMatrix> realMatrices;

    /**
     * Key-value container for complex matrices.
     */
    HashMap<String, ComplexMatrix> complexMatrices;

    /**
     * Constructor.
     */
    public CLI() {
        this.active = true;
        this.read = new Scanner(System.in);
        this.realMatrices = new HashMap<>();
        this.complexMatrices = new HashMap<>();
    }

    /**
     * User input parser.
     *
     * @param line String to parse.
     */
    public void parse(String line) {
        String[] parts = line.split(" ");
        if (parts[0].equals("ls")) {
            File[] files = new File(".").listFiles();
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else if (parts[0].equals("load") && parts.length > 1) {
            try {
                RealMatrix matrix = new RealMatrix(parts[1]);
                realMatrices.put(parts[1], matrix);
                System.out.println(parts[1] + " = ");
                System.out.println(matrix);
            } catch (Exception e1) {
                try {
                    ComplexMatrix matrix = new ComplexMatrix(parts[1]);
                    complexMatrices.put(parts[1], matrix);
                    System.out.println(parts[1] + " = ");
                    System.out.println(matrix);
                } catch (Exception e2) {
                    System.out.println("Unable to load matrix from file " + parts[1] + ".");
                }
            }
        } else if (parts[0].equals("save") && parts.length > 2) {
            try {
                if (realMatrices.containsKey(parts[1])) {
                    realMatrices.get(parts[1]).saveMatrix(parts[2]);
                } else if (complexMatrices.containsKey(parts[1])) {
                    complexMatrices.get(parts[1]).saveMatrix(parts[2]);
                }
            } catch (Exception e) {
                System.out.println("Unable to save matrix to file " + parts[2] + ".");
            }
        } else if (parts[0].equals("add") && parts.length > 2) {
            if (realMatrices.containsKey(parts[1])) {
                if (realMatrices.containsKey(parts[2])) {
                    System.out.println((realMatrices.get(parts[1])).add(realMatrices.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else if (complexMatrices.containsKey(parts[1])) {
                if (complexMatrices.containsKey(parts[2])) {
                    System.out.println((complexMatrices.get(parts[1])).add(complexMatrices.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("subtract") && parts.length > 2) {
            if (realMatrices.containsKey(parts[1])) {
                if (realMatrices.containsKey(parts[2])) {
                    System.out.println(realMatrices.get(parts[1]).subtract(realMatrices.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else if (complexMatrices.containsKey(parts[1])) {
                if (complexMatrices.containsKey(parts[2])) {
                    System.out.println(complexMatrices.get(parts[1]).subtract(complexMatrices.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("multiply") && parts.length > 2) {
            if (realMatrices.containsKey(parts[1])) {
                if (realMatrices.containsKey(parts[2])) {
                    System.out.println(realMatrices.get(parts[1]).multiply(realMatrices.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else if (complexMatrices.containsKey(parts[1])) {
                if (complexMatrices.containsKey(parts[2])) {
                    System.out.println(complexMatrices.get(parts[1]).multiply(complexMatrices.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("mpower") && parts.length > 2) {
            if (realMatrices.containsKey(parts[1])) {
                if (parts[2].matches("-?\\d+(\\.\\d+)?")) {
                    RealMatrix power = realMatrices.get(parts[1]);
                    RealMatrix temp = new RealMatrix(power.getArray());
                    for (int i = 1; i < Integer.valueOf(parts[2]); i++) {
                        temp = temp.multiply(temp);
                    }
                    System.out.println(temp);
                } else {
                    System.out.println(parts[2] + " is not a number.");
                }
            } else if (complexMatrices.containsKey(parts[1])) {
                if (parts[2].matches("-?\\d+(\\.\\d+)?")) {
                    ComplexMatrix power = complexMatrices.get(parts[1]);
                    ComplexMatrix temp = new ComplexMatrix(power.getArray());
                    for (int i = 1; i < Integer.valueOf(parts[2]); i++) {
                        temp = temp.multiply(temp);
                    }
                    System.out.println(temp);
                } else {
                    System.out.println(parts[2] + " is not a number.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("smultiply") && parts.length > 2) {
            if (realMatrices.containsKey(parts[1])) {
                if (parts[2].matches("-?\\d+(\\.\\d+)?")) {
                    System.out.println(realMatrices.get(parts[1]).multiply(Double.valueOf(parts[2])));
                } else {
                    System.out.println(parts[2] + " is not a number.");
                }
            } else if (complexMatrices.containsKey(parts[1])) {
                if (parts[2].matches("-?\\d+(\\.\\d+)?")) {
                    System.out.println(complexMatrices.get(parts[1]).multiply(Double.valueOf(parts[2])));
                } else {
                    System.out.println(parts[2] + " is not a number.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("transpose") && parts.length > 1) {
            if (realMatrices.containsKey(parts[1])) {
                System.out.println(realMatrices.get(parts[1]).transpose());
            } else if (complexMatrices.containsKey(parts[1])) {
                System.out.println(complexMatrices.get(parts[1]).transpose());
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("determinant") && parts.length > 1) {
            if (realMatrices.containsKey(parts[1])) {
                System.out.println(realMatrices.get(parts[1]).determinant());
            } else if (complexMatrices.containsKey(parts[1])) {
                System.out.println(complexMatrices.get(parts[1]).determinant());
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("invert") && parts.length > 1) {
            if (realMatrices.containsKey(parts[1])) {
                System.out.println(realMatrices.get(parts[1]).inverse());
            } else if (complexMatrices.containsKey(parts[1])) {
                System.out.println(complexMatrices.get(parts[1]).inverse());
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("help")) {
            System.out.println("List of available commands:");
            System.out.println("ls" + "\t" + "- lists contents of current directory");
            System.out.println("load [file]" + "\t" + "- loads the matrix from the given file");
            System.out.println("save [matrix] [file]" + "\t" + "- saves the matrix to the given file");
            System.out.println("add [matrix1] [matrix2]" + "\t" + "- adds matrix1 to matrix2 and prints the result");
            System.out.println("subtract [matrix1] [matrix2]" + "\t" + "- subtracts matrix2 from matrix1 and prints the result");
            System.out.println("multiply [matrix1] [matrix2]" + "\t" + "- multiplies matrix1 and matrix2 and prints the result");
            System.out.println("smultiply [matrix] [scalar]" + "\t" + "- multiplies matrix with scalar and prints the result");
            System.out.println("mpower [matrix] [n]" + "\t" + "- calculates the n:th power of matrix and prints the result");
            System.out.println("transpose [matrix]" + "\t" + "- prints the transpose of matrix");
            System.out.println("determinant [matrix]" + "\t" + "- calculates the determinant of matrix and prints the result");
            System.out.println("invert [matrix]" + "\t" + "- calculates the inverse of matrix and prints the result");
            System.out.println("help" + "\t" + "- displays this help text");
            System.out.println("exit" + "\t" + "- exits the program");
        } else if (parts[0].equals("exit")) {
            active = false;
        }
    }

    /**
     * Runs the program until user ends it.
     */
    public void run() {
        while (active) {
            System.out.print("> ");
            parse(read.nextLine());
        }
    }

}
