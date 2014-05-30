package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.model.Matrix;
import com.mycompany.tiralabra_maven.view.Io;
import java.util.Objects;

/**
 * Creates a matrix according to user input.
 *
 * @author gabriel
 */
public class MatrixReader {

    /**
     * The io used for reading the matrix specification from the user.
     */
    private Io io;

    /**
     * Constructs a matrix reader, which uses the specified io.
     * @param io the io used for reading the matrix specification from the user
     * @throws NullPointerException if the io parameter is null
     */
    public MatrixReader(Io io) {
        Objects.requireNonNull(io, "Parameter io must not be null.");
        this.io = io;
    }
    
    /**
     * Creates a matrix based on user specification.
     * The user enter the number rows and columns of the matrix, and then the elements row by row.
     * @return the matrix specified by the user
     */    
    public Matrix readMatrix() {
        int rows = readMatrixDimension("Number of rows: ");
        int cols = readMatrixDimension("Number of columns: ");
        Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            double[] row = readMatrixRow(i + 1, cols);
            for (int j = 0; j < cols; j++) {
                matrix.setElement(i, j, row[j]);
            }
        }
        return matrix;
    }
    
    /**
     * Asks the user to input the number of rows or columns.
     * This method verifies that the number is positive.
     * @param message the specification of which number the user should enter
     * @return the dimension specified by the user
     */
    private int readMatrixDimension(String message) {
        int dimension = io.readInt(message);
        if (dimension > 0) {
            return dimension;
        } else {
            return readMatrixDimension("The dimension must be positive. Please try again.\n");
        }
    }
    
    /**
     * Asks the user to enter the elements of a row in the matrix.
     * @param rowNumber the number of the row to be entered
     * @param rowLength the number of elements of the row
     * @return the elements of the row
     */
    private double[] readMatrixRow(int rowNumber, int rowLength) {        
        String rowString = io.readLine("Row number " + rowNumber + ": ");
        String[] stringElements = rowString.split(" ");
        double[] elements = new double[rowLength];
        if(!convertStringArrayToDoubleArray(stringElements, elements)){
            io.printLine("You entered an invalid row. Please all the elements of the row, separated by whitespace\n");
            return readMatrixRow(rowNumber, rowLength);
        }
        return elements;
    }  
    
    /**
     * Converts string elements to double elements.
     * Returns true if the elements were successfully converted.
     * @param stringElements the elements in string format
     * @param elements the array in which the double elements are inserted
     * @return true if as many elements as the row length were converted to doubles; false otherwise
     */
    private boolean convertStringArrayToDoubleArray(String[] stringElements, double[] elements) {
        if (stringElements.length != elements.length) {
            return false;
        }
        try {
            for (int i = 0; i < elements.length; i++) {
                elements[i] = Double.parseDouble(stringElements[i]);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
