package com.mycompany.tiralabra_maven.model;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Represents a matrix.
 *
 * @author gabriel
 */
public class Matrix {

    /**
     * The elements in the matrix.
     */
    private double[][] elements;
    /**
     * The number of rows in the matrix. This variable must be positive.
     */
    private int rows;
    /**
     * The number of columns in the matrix. This variable must be positive.
     */
    private int cols;

    /**
     * Constructs a matrix from an array.
     *
     * @param values a 2d double array containing the elements of the matrix
     */
    public Matrix(double[][] values) {
        this.elements = values;
        this.rows = this.elements.length;
        this.cols = this.elements[0].length;
    }

    /**
     * Constructs a zero matrix with the specified dimensions.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException if the number of rows or columns is less
     * than one
     */
    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("The number of rows and columns must be positive.");
        }
        this.rows = rows;
        this.cols = cols;
        this.elements = new double[rows][cols];
    }

    /**
     * Returns the number of rows in the matrix.
     *
     * @return the number of rows in the matrix
     */
    public int rows() {
        return rows;
    }

    /**
     * Returns the number of columns in the matrix.
     *
     * @return the number of columns in the matrix
     */
    public int cols() {
        return cols;
    }
    
    /**
     * Returns the element at the specified position in this matrix.
     * @param row the row index of the element to return
     * @param col the column index of the element to return
     * @return the element at the specified position
     * @throws IllegalArgumentException if the row or column index is out of range (row < 0 || row >= rows() || col < 0 || col >= cols())
     */
    public double getElement(int row, int col){
        if(indexOutOfRange(row, 0, rows)){
            int maxIndex = rows-1;
            throw new IllegalArgumentException("Row index must be in the range 0... " + maxIndex + " but was " + row);
        }
        if (indexOutOfRange(col, 0, cols)){
            int maxIndex = cols-1;
            throw new IllegalArgumentException("Column index must be in the range 0... " + maxIndex + " but was " + col);
        }
        return elements[row][col];
    }
        
    /**
     * Replaces the element at the specified position in this matrix with the specified element.
     * @param row the row index of the element to replace
     * @param col the column index of the element to replace
     * @param value the element to be inserted at the specified position
     * @throws IllegalArgumentException if the row or column index is out of range (row < 0 || row >= rows() || col < 0 || col >= cols())
     */
    public void setElement(int row, int col, double value){
        if(indexOutOfRange(row, 0, rows)){
            int maxIndex = rows-1;
            throw new IllegalArgumentException("Row index must be in the range 0... " + maxIndex + " but was " + row);
        }
        if (indexOutOfRange(col, 0, cols)){
            int maxIndex = cols-1;
            throw new IllegalArgumentException("Column index must be in the range 0... " + maxIndex + " but was " + row);
        }
        elements[row][col] = value;
    }
    
    public boolean isSquareMatrix(){
        return rows == cols;
    }

    @Override
    public String toString() {
        String returnString = "";
        DecimalFormat format = new DecimalFormat();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                returnString += format.format(elements[i][j]);
                if (j != cols - 1) {
                    returnString += "\t";
                }
            }
            if (i != rows - 1) {
                returnString += "\n";
            }
        }

        return returnString;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (!(otherObject instanceof Matrix)) {
            return false;
        }
        Matrix other = (Matrix) otherObject;
        if (this.rows() != other.rows || this.cols != other.cols) return false;
        return allElementsEqual(other);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Arrays.deepHashCode(this.elements);
        return hash;
    }

    /**
     * Indicates whether all elements in corresponding positions are equal in this matrix and the other matrix.
     * @param other the matrix to which this matrix is compared
     * @return true if all elements in corresponding positions of this and the other matrix are equal; false otherwise 
     */
    private boolean allElementsEqual(Matrix other) {
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                if (this.elements[i][j] != other.elements[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Evaluates whether the specified index is within the specified range.
     * @param index the index to be evaluated
     * @param min the range minimum (included)
     * @param max the range maximum (excluded)
     * @return true if the index is out of the specified range (index < min || index >= max); false otherwise
     */
    private boolean indexOutOfRange(int index, int min, int max) {
        return index < min || index >= max;
    }

}
