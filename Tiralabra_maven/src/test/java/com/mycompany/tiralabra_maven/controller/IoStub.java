
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.view.Io;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class IoStub implements Io{
    
    private String[] input;
    private List<String> output;
    private int inputIndex;
    
    public IoStub(String... input){
        this.input = input;
        this.inputIndex = 0;
        this.output = new ArrayList<String>();
    }

    public String readLine(String message) {
        printLine(message);
        return input[inputIndex++];
    }

    public void printLine(String line) {
        output.add(line);
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
