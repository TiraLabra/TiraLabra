package com.mycompany.tiralabra_maven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * File handler that reads the input files.
 *
 * @author Jari Haavisto
 */
public class InputReader {
    
    /**
     * Checks what characters are present in given String.
     * 
     * @param sequences 
     * @return 
     */
    private static char[] parseAlphabet(String sequences) {
        return sequences.replaceAll("(.)(?=.*\\1)", "").toCharArray();
    }

    /**
     * Reads a file that begins with two lines of input. Stores the input in two char arrays and returns both as one array. 
     * If file is not found or is not formatted correctly, returns null.
     * 
     * @param filename Name of the file to be read.
     * @return char[][] or null
     */
    public static char[][] readInput(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            char[][] input = new char[3][0];
            String line1 = reader.readLine().trim().toLowerCase();
            String line2 = reader.readLine().trim().toLowerCase();
            input[0] = line1.toCharArray();
            input[1] = line2.toCharArray();
            input[2] = parseAlphabet(line1 + line2);
            return input;
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löydy!");
        } catch (IOException e) {
            System.out.println("Lukuvirhe: " + e);
        }
        return null;

    }
    
    
}
