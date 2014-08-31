package cli;

import java.util.*;
import java.io.*;
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
     * TODO: Create implementation of HashMap
     */
    HashMap<String, Matrix> memory;
    HashMap<String, ComplexMatrix> complexMemory;
    
    /**
     * Constructor.
     */
    public CLI() {
        this.active = true;
        this.read = new Scanner(System.in);
        this.memory = new HashMap<String, Matrix>();
    }
    
    /**
     * Parser.
     * @param line String to parse.
     */
    public void parse(String line) {
        String[] parts = line.split(" ");
        if (parts[0].equals("ls")) {
            File[] files = new File(".").listFiles();
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else if (parts[0].equals("exit")) {
            active = false;
        } else if (parts[0].equals("help")) {
            System.out.println("List of available commands:");
            System.out.println("ls" + "\t" + "- lists contents of current directory");
            System.out.println("load [filename]" + "\t" + "- loads the matrix with the given filename");
            System.out.println("save [matrix] [filename]" + "\t" + "- saves the matrix to the given file");
            System.out.println("add [matrix1] [matrix2]" + "\t" + "- adds matrix1 and matrix2 and prints the result");
            System.out.println("subtract [matrix1] [matrix2]" + "\t" + "- subtracts matrix2 from matrix1 and prints the result");
            System.out.println("mmultiply [matrix1] [matrix2]" + "\t" + "- multiplies matrix1 and matrix2 and prints the result");
            System.out.println("multiply [matrix] [int]" + "\t" + "- multiplies matrix with int and prints the result");
            System.out.println("mpower [matrix] [n]" + "\t" + "- calculates the n:th power of matrix and prints the result");
            System.out.println("tranpose [matrix]" + "\t" + "- prints the transpose of matrix");
            System.out.println("determinant [matrix]" + "\t" + "- calculates the determinant and prints the result");
            System.out.println("invert [matrix]" + "\t" + "- calculates the inverse of matrix and prints the result");
            System.out.println("help" + "\t" + "- displays this help text");
            System.out.println("exit" + "\t" + "- exits the program");
        } else if (parts[0].equals("load") && parts.length > 1) {
            try {
                Matrix matrix = new Matrix(parts[1]);
                memory.put(parts[1], matrix);
                System.out.println(parts[1] + " = ");
                System.out.println(matrix);
            } catch (Exception e) {
                System.out.println("Unable to load matrix from file " + parts[1] + ".");
            }
        } else if (parts[0].equals("save") && parts.length > 2) {
            try {
                if (memory.containsKey(parts[1])) {
                    memory.get(parts[1]).saveMatrix(parts[2]);
                }
            } catch (Exception e) {
                System.out.println("Unable to save matrix to file " + parts[2] + ".");
            }
        } else if (parts[0].equals("add") && parts.length > 2) {
            if (memory.containsKey(parts[1])) {
                if (memory.containsKey(parts[2])) {
                    System.out.println(memory.get(parts[1]).add(memory.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("subtract") && parts.length > 2) {
            if (memory.containsKey(parts[1])) {
                if (memory.containsKey(parts[2])) {
                    System.out.println(memory.get(parts[1]).subtract(memory.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("mmultiply") && parts.length > 2) {
            if (memory.containsKey(parts[1])) {
                if (memory.containsKey(parts[2])) {
                    System.out.println(memory.get(parts[1]).multiply(memory.get(parts[2])));
                } else {
                    System.out.println("Matrix " + parts[2] + " not loaded yet.");
                }
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
         } else if (parts[0].equals("mpower") && parts.length > 2) {
            if (memory.containsKey(parts[1])) {
                if (parts[2].matches("-?\\d+(\\.\\d+)?")) {
                    Matrix power = memory.get(parts[1]);
                    Matrix temp = new Matrix(power.getArray());
                    for (int i = 1; i < Integer.valueOf(parts[2]); i++) {
                        temp = temp.multiply(temp);
                    }
                    System.out.println(temp);
                } else {
                    System.out.println(parts[2] + " is not a number.");
                }
            } else {
                System.out.println("Matrix "+ parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("multiply") && parts.length > 2) {
            if (memory.containsKey(parts[1])) {
                if (parts[2].matches("-?\\d+(\\.\\d+)?")) {
                    System.out.println(memory.get(parts[1]).multiply(Double.valueOf(parts[2])));
                } else {
                    System.out.println(parts[2] + " is not a number.");
                }
            } else {
                System.out.println("Matrix "+ parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("transpose") && parts.length > 1) {
            if (memory.containsKey(parts[1])) {
                System.out.println(memory.get(parts[1]).transpose());
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("determinant") && parts.length > 1) {
            if (memory.containsKey(parts[1])) {
                System.out.println(memory.get(parts[1]).determinant());
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        } else if (parts[0].equals("invert") && parts.length > 1) {
            if (memory.containsKey(parts[1])) {
                System.out.println(memory.get(parts[1]).inverse());
            } else {
                System.out.println("Matrix " + parts[1] + " not loaded yet.");
            }
        }
    }
    
    /**
     * Runs the program until user ends it.
     */
    public void run() {
        while(active) {
            System.out.print("> ");
            String line = read.nextLine();
            parse(line);
        }
    }
    
}
