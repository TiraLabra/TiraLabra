
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;

/**
 * Enables the user to multiply two matrices.
 * @author gabriel
 */
public class Multiplication extends AbstractCommand {       

    /**
     * Constructs a multiplication command using the specified io.
     * @param io the specified io
     */
    public Multiplication(Io io) {
        super(io);
    }           

    @Override
    protected String performMatrixOperation(Matrix matrixA, Matrix matrixB) {
        String result;
        if (!MatrixMath.areMultipliable(matrixA, matrixB)){
            result = "The matrices are not multipliable, since their inner dimensions do not match.";
        } else{
            result = MatrixMath.multiply(matrixA, matrixB).toString();
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "Multiplication";        
    }
    
}
