
package com.mycompany.tiralabra_maven;

/**
 * Class containing matrix operations.
 */
public class MatrixCalculator {
    
    /**
     * Algorithm for calculating the determinant of a matrix to be done.     
     */
    public Double getDeterminant(Matrix a) {
        return 0.0;
    }
    
    /**
     * Algorithm for inverting a matrix to be done.     
     */
    public Matrix inverse(Matrix a) {
        Matrix c = new Matrix(a.numRows(), a.numCols());
        return c;
    }    
    
    /**
     * Strassen algorithm for matrix multiplication.
     * @param a
     * @param b
     * @return Result Matrix
     */
    public Matrix StrassenMultiplication(Matrix a, Matrix b) {
        a = matrixSizeCheck(a);
        b = matrixSizeCheck(b);
        int n = a.numRows();
        Matrix c = new Matrix(n, n);  
        if (n==1) {
            c.setValue(0, 0, a.get(0, 0)*b.get(0, 0));            
            return c;
        }        
        Matrix a11 = a.subMatrix(0, n/2-1, 0, n/2-1);
        Matrix a12 = a.subMatrix(0, n/2-1, n/2, n-1);
        Matrix a21 = a.subMatrix(n/2, n-1, 0, n/2-1);
        Matrix a22 = a.subMatrix(n/2, n-1, n/2, n-1);
        Matrix b11 = b.subMatrix(0, n/2-1, 0, n/2-1);
        Matrix b12 = b.subMatrix(0, n/2-1, n/2, n-1);
        Matrix b21 = b.subMatrix(n/2, n-1, 0, n/2-1);
        Matrix b22 = b.subMatrix(n/2, n-1, n/2, n-1);        

        Matrix M1 = StrassenMultiplication(a11.add(a22), b11.add(b22));
        Matrix M2 = StrassenMultiplication(a21.add(a22), b11);
        Matrix M3 = StrassenMultiplication(a11, b12.subtract(b22));
        Matrix M4 = StrassenMultiplication(a22, b21.subtract(b11));
        Matrix M5 = StrassenMultiplication(a11.add(a12), b22);
        Matrix M6 = StrassenMultiplication(a21.subtract(a11), b11.add(b12));
        Matrix M7 = StrassenMultiplication(a12.subtract(a22), b21.add(b22));
        
        Matrix c11 = M1.add(M4).subtract(M5).add(M7);
        Matrix c12 = M3.add(M5);
        Matrix c21 = M2.add(M4);
        Matrix c22 = M1.add(M3).subtract(M2).add(M6);
        c = join(c11,c12,c21,c22);
        return c;
    }   
    
    /**
     * Checks if the matrix size is a power of 2.
     * If matrix is not a power of 2, zeros are added to make it so.
     * @param m
     * @return 
     */
    public Matrix matrixSizeCheck(Matrix m) {        
        if ((m.numRows() & -m.numRows()) != m.numRows()) {  
            int n = m.numRows();
            while((n & -n) != n) {
                n++;
            }
            Matrix u = new Matrix(n, n);
            for (int i=0; i<m.numCols(); i++) {
                for (int j=0; j<m.numRows(); j++) {                    
                      u.setValue(i, j, m.get(i, j));
                    }                    
                }     
            return u;
        }else {
            return m;
        }
    }    
    
    /**
     * Joins 4 submatrices into one matrix. 
     * @param m1
     * @param m2
     * @param m3
     * @param m4
     * @return Matrix m
     */
    public Matrix join(Matrix m1, Matrix m2, Matrix m3, Matrix m4) {
        Matrix m = new Matrix(m1.numRows()+m2.numRows(), m1.numCols()+m2.numCols());
        for (int i=0; i<m1.numRows(); i++) {
            for (int j=0; j<m1.numCols(); j++) {
                m.setValue(i, j, m1.get(i, j));
                m.setValue(i, j+m1.numCols(), m2.get(i, j));
                m.setValue(i+m1.numRows(), j, m3.get(i, j));
                m.setValue(i+m1.numRows(), j+m1.numCols(), m4.get(i, j));
            }
        }        
        return m;
    }
    
    /**
     * Gauss-Jordan Elimination.
     * Converts a matrix into reduced row echelon form.
     * @param m 
     */
    public void GaussJordan(Matrix m) {
      int i = 0;
      int j = 0;
      while(i<m.numRows() && j<m.numCols()){
         int k = i;
         while(k<=m.numRows() && m.get(k, j) ==0) {
             k++;
         }
         if( k<=m.numRows() ){
            if(k != i) {
               m.swapRows(i, k);
            }
            if(m.get(i, j) != 1) {
               m.divideRow(i, m.get(i, j));
            }
            
            for(int p=0; p < m.numRows(); p++){
                if( p!=i && m.get(p, j) != 0){
                   for(int q=j+1; q < m.numCols(); q++){
                      m.setValue(p, q, m.get(p, q) - m.get(p, j)*m.get(i, q));
                   }
                   m.setValue(p, j, 0);
                }
             }
            i++;
         }
         j++;
      }
    }
}
