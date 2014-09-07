/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 12.8.2014 
 */
package TestSuite.Engine;

import TestSuite.UI.TextUI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class FilePrinter {

    /**
     * File writer. If file exists, results can be added to the end of the file.
     *
     * @param fileName filename as parameter
     * @param results prints given arraylist to file
     */
    public void printToFile(String fileName, ArrayList<String> results) {

        boolean writeToFile = true;
        TextUI t = new TextUI();

        try {

            File f = new File(fileName + ".csv");

            if (f.exists() && !f.isDirectory()) {

             writeToFile = t.existingFile();
            }
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f, writeToFile)));

            for (String result : results) {
                writer.println(result);
            }

            writer.close();
            System.out.println("Printed to file: " + fileName + ".csv\n");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
