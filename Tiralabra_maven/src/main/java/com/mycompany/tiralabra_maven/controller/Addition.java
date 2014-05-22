
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.view.Io;
import java.util.Objects;

/**
 * Enables the user two add two matrices.
 * The user is asked to enter two matrices, the add operation is performed, and the result is displayed.
 * @author gabriel
 */
public class Addition implements Command {
    
    /**
     * The io used for reading input from the user and outputting the result.
     * Must not be null.
     */
    private Io io;
        
    /**
     * Constructs an addition command using the specified io.
     * @param io the specified io
     * @throws NullPointerException if the io parameter is null
     */
    public Addition(Io io) {
        Objects.requireNonNull(io, "io must not be null");
        this.io = io;
    }
    
    

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
