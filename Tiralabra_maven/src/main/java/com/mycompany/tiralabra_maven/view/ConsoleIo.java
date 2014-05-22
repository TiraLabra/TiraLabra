package com.mycompany.tiralabra_maven.view;

import java.util.Objects;
import java.util.Scanner;

/**
 * A command line implementation of the Io interface.
 *
 * @author gabriel
 */
public class ConsoleIo implements Io {

    /**
     * The scanner used for reading user input. This variable must not be null.
     */
    private Scanner scanner;

    /**
     * Constructs a ConsoleIO, which uses the specified scanner.
     *
     * @param scanner the scanner for reading user input
     * @throws NullPointerException if the scanner parameter is null
     */
    public ConsoleIo(Scanner scanner) {
        Objects.requireNonNull(scanner);
        this.scanner = scanner;
    }

    public String readLine(String message) {
        printLine(message);
        return scanner.nextLine();
    }

    public void printLine(String line) {
        System.out.print(line);
    }

    public int readInt(String message) {
        while (true) {
            String intAsString = readLine(message);
            try {
                return Integer.parseInt(intAsString);
            } catch (NumberFormatException exception) {
                printLine(intAsString + " is not a valid number. Please try again.\n");
            }
        }
    }

}
