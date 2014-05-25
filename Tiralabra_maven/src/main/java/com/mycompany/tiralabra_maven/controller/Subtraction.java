
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;

/**
 * Enables the user to subtract one matrix from another.
 * @author gabriel
 */
public class Subtraction extends AbstractCommand {       

    /**
     * Constructs a subtraction command using the specified io.
     * @param io the specified io
     */
    public Subtraction(Io io) {
        super(io);
    }           

    @Override
    protected String performMatrixOperation(Matrix matrixA, Matrix matrixB) {
        String result;
        if (!MatrixMath.sameSize(matrixA, matrixB)){
            result = "The difference is not defined, since the matrices have different sizes";
        } else{
            result = MatrixMath.subtract(matrixA, matrixB).toString();
        }
        return result;
    }
    
     @Override
    public String toString() {
        return "Subtraction";        
    }
    
}
