package com.mycompany.tiralabra_maven;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * File handler
 *
 * @author Jari Haavisto
 */
public class InputReader {

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
            char[][] input = new char[2][0];
            input[0] = reader.readLine().toLowerCase().toCharArray();
            input[1] = reader.readLine().toLowerCase().toCharArray();
            return input;
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löydy!");
        } catch (IOException e) {
            System.out.println("Lukuvirhe: " + e);
        }
        return null;

    }
}
