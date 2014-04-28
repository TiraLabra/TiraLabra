package com.mycompany.tiralabra_maven;

import java.util.Scanner;
import sequencealgorithms.GSA;
import sequencealgorithms.GSAwGap;
import sequencealgorithms.LCS;
import sequencealgorithms.LSA;
import sequencealgorithms.Problem;

/**
 * User interface for the program.
 *
 * @author Jari Haavisto
 */
public class SeqAlgUI {

    Problem problem;
    Scanner reader;
    final String DEFAULT_INPUT_FILE = "input.txt";
    final String DEFAULT_OUTPUT_FILE = "output.txt";

    /**
     * Sets up a new user interface.
     */
    public SeqAlgUI() {
        System.out.println("Sequence Algorithms\n");
        reader = new Scanner(System.in);
    }

    /**
     * The main body of the interface.
     */
    public void run() {
        while (true) {
            selectProblem();
            if (problem == null) {
                break;
            }
            long startTime = System.currentTimeMillis();
            problem.solve();
            long endTime = System.currentTimeMillis();
            System.out.println("Solve time: " + (endTime - startTime) + "\n");
            System.out.println("Solution:");
            printSolution(problem.getSolution());
            writeSolution(problem.getSolution(), readFilename(true));
        }
    }

    /**
     * Problem selector. Asks input and sets up the problem to be solved.
     */
    private void selectProblem() {
        switch (readProblemSelection()) {
            case 1:
                problem = new LCS(readFilename(false));
                break;
            case 2:
                problem = new GSA(readFilename(false));
                setUpScoring(false);
                break;
            case 3:
                problem = new LSA(readFilename(false));
                setUpScoring(false);
                break;
            case 4:
                problem = new GSAwGap(readFilename(false));
                setUpScoring(true);
                break;
            default:
                System.out.println("Quitting...");
                problem = null;
        }
    }

    /**
     * Reads user input and returns the selection. If the input is invalid,
     * returns 0.
     *
     * @return
     */
    public int readProblemSelection() {
        System.out.println("\nSelect problem type:");
        System.out.println("1 - Longest Common Substring");
        System.out.println("2 - Global Sequence Alignment");
        System.out.println("3 - Local Sequence Alignment");
        System.out.println("4 - Global Sequence Alignment with gap penalties");
        System.out.println("0 - Quit");
        System.out.print(">");
        int selection = 0;
        try {
            selection = Integer.parseInt(reader.nextLine());
        } catch (NumberFormatException e) {
        }
        return selection;
    }

    /**
     * Asks input and sets up the scoring used to solve the current problem.
     *
     * @param withGap True, if affine gap penalty is used.
     */
    private void setUpScoring(boolean withGap) {
        switch (readScoringSelection()) {
            case 1:
                readUserScoring(withGap);
                break;
            default:
                useDefaultScoring();
        }
    }

    /**
     * Reads user input and returns the selection. If the input is invalid,
     * returns 0.
     *
     * @return
     */
    private int readScoringSelection() {
        System.out.println("0 - Use default scoring");
        System.out.println("1 - Use simple scoring scheme");
        System.out.print(">");
        int selection = 0;
        try {
            selection = Integer.parseInt(reader.nextLine());
        } catch (NumberFormatException e) {
        }
        return selection;

    }

    /**
     * Reads scoring scheme from user input. Limited to reading only 4 values: a
     * match bonus, a mismatch penalty, an indel penalty and optionally a gap
     * penalty.
     *
     * @param withGap
     */
    private void readUserScoring(boolean withGap) {
        System.out.println("Enter match bonus:");
        System.out.print(">");
        double matchBonus = readDouble();
        System.out.println("Enter mismatch penalty (negative):");
        System.out.print(">");
        double mismatchPenalty = readDouble();
        System.out.println("Enter indel penalty (negative):");
        System.out.print(">");
        double indelPenalty = readDouble();
        double gapPenalty = 0;
        if (withGap) {
            System.out.println("Enter gap penalty (negative):");
        System.out.print(">");
            gapPenalty = readDouble();
        }
        problem.setUpScoring(matchBonus, mismatchPenalty, indelPenalty, gapPenalty);
    }

    /**
     * Uses a default scoring.
     */
    private void useDefaultScoring() {
        System.out.println("Using match bonus 5, mismatch penalty -3, indel penalty -1 and gap penalty 0.");
        problem.setUpScoring(5, -3, -1, 0);
    }

    /**
     * Prints the solution.
     *
     * @param solution
     */
    private void printSolution(char[][] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                System.out.print(solution[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private void writeSolution(char[][] solution, String filename) {
        String solutionStr = new String(solution[0]) + "\n" + new String(solution[1]);
        InputReader.writeOutput(solutionStr, filename);
    }

    /**
     * Reads user input and returns a file name. Does not test the validity of
     * the input in any way. If user input is empty, returns default value.
     * 
     * @param output true, if used to read output file name.
     * @return 
     */
    public String readFilename(boolean output) {
        String defaultFile = DEFAULT_INPUT_FILE;
        if (output) {
            defaultFile = DEFAULT_OUTPUT_FILE;
        }
        
        System.out.println("Enter file name (default: "+ defaultFile + "):");
        System.out.print(">");
        String filename = reader.nextLine();
        if (filename.equals("")) {
            return defaultFile;
        }
        while (!output && !InputReader.fileExists(filename)) {
            System.out.println("File not found, please try again:");
            filename = reader.nextLine();
        }
        return filename;
    }

    /**
     * Asks for a double until user gives a valid double.
     *
     * @return
     */
    private double readDouble() {
        boolean cont = true;
        double dbl = 0;
        while (cont) {
            try {
                dbl = Double.parseDouble(reader.nextLine());
                cont = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid double:");
            }
        }
        return dbl;
    }

    /**
     * Asks for an integer until user gives a valid integer.
     *
     * @return
     */
    private int readInt() {
        boolean cont = true;
        int i = 0;
        while (cont) {
            try {
                i = Integer.parseInt(reader.nextLine());
                cont = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer:");
            }
        }
        return i;
    }

}
