package math;

import algorithms.*;

/**
 * Contains a matrix as a two-dimensional array of double precision floating point numbers,
 * and includes methods for basic matrix operations.
 * 
 * @author ydna
 */
public class Matrix {
    
    /**
     * Two-dimensional array containing the matrix elements as double values.
     */
    private double[][] array;
    
    /**
     * The number of rows in the matrix.
     */
    private int rows;
    
    /**
     * The number of columns in the matrix.
     */
    private int cols;
    
    /**
     * Constructs a matrix from a two-dimensional array.
     * @param array Two-dimensional double array.
     */
    public Matrix(double[][] array) {
        this.array = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }
    
    /**
     * Constructs a zero matrix with the given number of rows and columns.
     * @param rows The number of rows.
     * @param cols The number of columns.
     */
    public Matrix(int rows, int cols) {
        this.array = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }
    
    /**
     * Constructs a constant matrix with the given number of rows and columns.
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @param val The value of matrix elements.
     */
    public Matrix(int rows, int cols, double val) {
        this.array = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.array[i][j] = val;
            }
        }
    }
    
    /**
     * Accesses the two-dimensional array with the matrix elements.
     * @return Pointer to the array.
     */
    public double[][] getArray() {
        return this.array;
    }
    
    /**
     * Returns the row dimension of the matrix.
     * @return Number of rows.
     */
    public int getRowDim() {
        return this.rows;
    }
    
    /**
     * Returns the column dimension of the matrix.
     * @return Number of columns.
     */
    public int getColumnDim() {
        return this.cols;
    }
    
    /**
     * Returns the matrix as a string.
     * @return Matrix as a string.
     */
    @Override
    public String toString() {
        String matrix = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix += array[i][j];
                matrix += " ";
            }
            matrix += "\n";
        }
        return matrix;
    }
    
    /**
     * Adds another matrix to this matrix.
     * @param matrix Another matrix.
     * @return Sum.
     */
    public Matrix add(Matrix matrix) {
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j] + matrix.getArray()[i][j];
            }
        }
        return new Matrix(temp);
    }
    
    /**
     * Substracts another matrix from this matrix.
     * @param matrix Another matrix.
     * @return Difference.
     */
    public Matrix substract(Matrix matrix) {
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j] - matrix.getArray()[i][j];
            }
        }
        return new Matrix(temp);
    }
    
    /**
     * Multiplies this matrix with a scalar number.
     * @param number Multiplier.
     * @return Scalar product.
     */
    public Matrix multiply(double number) {
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = number * array[i][j];
            }
        }
        return new Matrix(temp);
    }
    
    /**
     * Returns the transpose of this matrix.
     * @return Transpose.
     */
    public Matrix transpose() {
        double[][] temp = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = array[j][i];
            }
        }
        return new Matrix(temp);
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix Another matrix.
     * @return Matrix product.
     */
    public Matrix multiply(Matrix matrix) {
        double[][] temp = new double[rows][matrix.getColumnDim()];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix.getColumnDim(); j++) {
                for (int k = 0; k < cols; k++) {
                    temp[i][j] += (array[i][k] * matrix.getArray()[k][j]);
                }
            }
        }
        return new Matrix(temp);
    }
    
    /**
     * Multiplies this matrix by another matrix using Strassen's algorithm.
     * @param matrix Another matrix.
     * @return Matrix product.
     */
    public Matrix strassenMultiply(Matrix matrix) {
        double[][] temp = Strassen.multiply(this.getArray(), matrix.getArray());
        if (temp.length != rows) {
            return new Matrix(temp).deleteRow(rows).deleteColumn(rows);
        } else {
            return new Matrix(temp);
        }
    }
    
    /**
     * Row addition.
     * @param row Index of the row to add the other one to.
     * @param another Index of the other row.
     */
    public void rowAdd(int row, int another) {
        for (int i = 0; i < cols; i++) {
            array[row][i] += array[another][i];
        }
    }
    
    /**
     * Row multiplication.
     * @param row Index of the row to multiplicate with the number.
     * @param number Multiplier.
     */
    public void rowMultiply(int row, double number) {
        for (int i = 0; i < cols; i++) {
            array[row][i] *= number;
        }
    }
    
    /**
     * Row switching.
     * @param row Index of the row to switch.
     * @param another Index of the other row.
     */
    public void rowSwitch(int row, int another) {
        for (int i = 0; i < cols; i++) {
            double temp = array[row][i];
            array[row][i] = array[another][i];
            array[another][i] = temp;
        }
    }
    
    /**
     * Deletes a row from this matrix.
     * @param row Index of the row to delete.
     * @return Matrix with the row deleted.
     */
    public Matrix deleteRow(int row) {
        Matrix deleted = new Matrix(rows-1, cols);
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < cols; j++) {
                if (i < row) {
                    deleted.getArray()[i][j] = array[i][j];
                } else {
                    deleted.getArray()[i][j] = array[i+1][j];
                }
            }
        }
        return deleted;
    }
    
    /**
     * Deletes a column from this matrix.
     * @param col Index of the column to delete.
     * @return Matrix with the column deleted.
     */
    public Matrix deleteColumn(int col) {
        Matrix deleted = new Matrix(rows, cols-1);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                if (j < col) {
                    deleted.getArray()[i][j] = array[i][j];
                } else {
                    deleted.getArray()[i][j] = array[i][j+1];
                }
            }
        }
        return deleted;
    }
    
    /**
     * Creates a submatrix with the given rows and columns deleted.
     * @param deleteRow Array of boolean values, true if row deleted.
     * @param deleteColumn Array of boolean values, true if row deleted.
     * @return Submatrix.
     */
    public Matrix submatrix(boolean[] deleteRow, boolean[] deleteColumn) {
        Matrix submatrix = new Matrix(this.getArray());
        for (int i = 0; i < rows; i++) {
            if (deleteRow[i]) {
                submatrix = submatrix.deleteRow(i);
            }
        }
        for (int i = 0; i < cols; i++) {
            if (deleteColumn[i]) {
                submatrix = submatrix.deleteColumn(i);
            }
        }
        return submatrix;
    }
    
}
