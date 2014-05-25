

package com.mycompany.tiralabra_maven.view;

/**
 * Provides an interface for handling user input and output.
 * @author gabriel
 */
public interface Io {
    /**
     * Reads a line from the user.
     * @param message specification of what the user should input
     * @return the user input
     */    
    String readLine(String message);
    
    /**
     * Reads an integer from the user.
     * @param message specification of the input expected from the user
     * @return the integer the user entered
     */
    int readInt(String message);
        
    /**
     * Displays a line for the user. 
     * @param line the line to be displayed for the user
     */
    void printLine(String line);        

}
