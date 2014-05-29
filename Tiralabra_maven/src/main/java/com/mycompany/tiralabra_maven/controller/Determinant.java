
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.model.MatrixMath;
import com.mycompany.tiralabra_maven.view.Io;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Enables the user to calculate the determinant of a matrix.
 * @author gabriel
 */
public class Determinant implements Command {

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
     * Constructs a determinant command using the specified io.
     * @param io the specified io
     * @throws NullPointerException if the io parameter is null
     */
    public Determinant(Io io) {
        Objects.requireNonNull(io, "io must not be null");
        this.io = io;
        this.matrixReader = new MatrixReader(io);
    }
    
    @Override
    public void execute() {
        DecimalFormat format = new DecimalFormat();
        String result;
        io.printLine("Enter the matrix:\n");
        Matrix matrix = matrixReader.readMatrix();
        if (!matrix.isSquareMatrix()) {
            result = "The determinant is not defined, since the matrix is not a square matrix.";
        } else {
            result = "The determinant is " + format.format(MatrixMath.det(matrix)) + "\n";
        }
        io.printLine(result);
    }

    @Override
    public String toString() {
        return "Determinant";
    }
    
    
}
