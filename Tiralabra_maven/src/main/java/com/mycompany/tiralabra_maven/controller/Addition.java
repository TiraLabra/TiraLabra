
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;

/**
 * Enables the user to add two matrices.
 * @author gabriel
 */
public class Addition extends TwoParameterCommand {       

    /**
     * Constructs an addition command using the specified io.
     * @param io the specified io
     */
    public Addition(Io io) {
        super(io);
    }           

    @Override
    protected String performMatrixOperation(Matrix matrixA, Matrix matrixB) {
        String result;
        if (!MatrixMath.sameSize(matrixA, matrixB)){
            result = "The sum is not defined, since the matrices have different sizes";
        } else{
            result = MatrixMath.add(matrixA, matrixB).toString();
        }
        return result;
    }
    
     @Override
    public String toString() {
        return "Addition";        
    }
    
}
