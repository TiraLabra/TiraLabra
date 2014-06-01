
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;
import java.util.Objects;

/**
 * Enables the user two perform matrix operations using two matrices.
 * The user is asked to enter two matrices, the specified operation is performed, and the result is displayed.
 * @author gabriel
 */
public abstract class TwoParameterCommand implements Command {
    
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
     * Constructs an abstract command using the specified io.
     * @param io the specified io
     * @throws NullPointerException if the io parameter is null
     */
    public TwoParameterCommand(Io io) {
        Objects.requireNonNull(io, "io must not be null");
        this.io = io;
        this.matrixReader = new MatrixReader(io);
    }
        
    public void execute() {        
        io.printLine("Enter the first matrix:\n");
        Matrix matrixA = matrixReader.readMatrix();
        io.printLine("Enter the second matrix:\n");
        Matrix matrixB = matrixReader.readMatrix();
        String result = performMatrixOperation(matrixA, matrixB);        
        io.printLine(result + "\n");
    }
    
    
    protected abstract String performMatrixOperation(Matrix matrixA, Matrix matrixB);
    
}
