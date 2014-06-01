
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;

/**
 * Enables the user to calculate the transpose of a matrix.
 * @author gabriel
 */
public class Transposition extends OneParameterCommand {

    /**
     * Constructs a transposition command using the specified io.
     * @param io the specified io
     */
    public Transposition(Io io){
        super(io);
    }
    
    @Override
    protected String performMatrixOperation(Matrix matrix) {
        return "The transpose is\n" + MatrixMath.transpose(matrix);
    }

    @Override
    public String toString() {
        return "Transposition";
    }
    
    
}
