
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
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
     * The matrix reader used for reading matrix specifications from the user
     */
    private MatrixReader matrixReader;
        
    /**
     * Constructs an addition command using the specified io.
     * @param io the specified io
     * @throws NullPointerException if the io parameter is null
     */
    public Addition(Io io) {
        Objects.requireNonNull(io, "io must not be null");
        this.io = io;
        this.matrixReader = new MatrixReader(io);
    }
        
    public void execute() {        
        io.printLine("Enter the first matrix:\n");
        Matrix matrixA = matrixReader.readMatrix();
        io.printLine("Enter the second matrix:\n");
        Matrix matrixB = matrixReader.readMatrix();
        String result;
        if (!MatrixMath.sameSize(matrixA, matrixB)){
            result = "The sum is not defined, since the matrices have different sizes";
        } else{
            result = MatrixMath.add(matrixA, matrixB).toString();
        }
        io.printLine(result + "\n");
    }

    @Override
    public String toString() {
        return "Addition";        
    }
    
    
}
