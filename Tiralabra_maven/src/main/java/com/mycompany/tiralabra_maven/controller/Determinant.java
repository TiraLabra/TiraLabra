
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;
import java.text.DecimalFormat;

/**
 * Enables the user to calculate the determinant of a matrix.
 * @author gabriel
 */
public class Determinant extends OneParameterCommand {

    
    /**
     * Constructs a determinant command using the specified io.
     * @param io the specified io
     */
    public Determinant(Io io) {
        super(io);
    }    

    @Override
    public String toString() {
        return "Determinant";
    }

    @Override
    protected String performMatrixOperation(Matrix matrix) {
        DecimalFormat format = new DecimalFormat();
        String result;
        if (!matrix.isSquareMatrix()) {
            result = "The determinant is not defined, since the matrix is not a square matrix.";
        } else {
            result = "The determinant is " + format.format(MatrixMath.det(matrix));
        }
        return result;
    }


    
    
}
