package com.mycompany.tiralabra_maven;

public class LUDecomposition {

  private final double[][] LU;
  private final int m;
  private int pivsign;
  private final int[] piv;

  /**
   * Produces the LU decomposition of a matrix.
   * @param a 
   */
  public LUDecomposition(Matrix a) {
    LU = a.getArrayCopy();
    m = a.numRows();
    piv = new int[m];
    for (int i = 0; i < m; i++) {
      piv[i] = i;
    }
    pivsign = 1;
    double[] rowi;
    double[] colj = new double[m];

    for (int j = 0; j < m; j++) {
      for (int i = 0; i < m; i++) {
        colj[i] = LU[i][j];
      }
      for (int i = 0; i < m; i++) {
        rowi = LU[i];
        int kmax = Math.min(i, j);
        double s = 0.0;
        for (int k = 0; k < kmax; k++) {
          s += rowi[k] * colj[k];
        }
        rowi[j] = colj[i] -= s;
      }
      int p = j;
      for (int i = j + 1; i < m; i++) {
        if (Math.abs(colj[i]) > Math.abs(colj[p])) {
          p = i;
        }
      }
      if (p != j) {
        for (int k = 0; k < m; k++) {
          double t = LU[p][k];
          LU[p][k] = LU[j][k];
          LU[j][k] = t;
        }
        int k = piv[p];
        piv[p] = piv[j];
        piv[j] = k;
        pivsign = -pivsign;
      }
      if (j < m & LU[j][j] != 0.0) {
        for (int i = j + 1; i < m; i++) {
          LU[i][j] /= LU[j][j];
        }
      }
    }
  }
  
  /**
   * Returns the LU Decomposition of a matrix.
   * @return 
   */
  public Matrix getLU() {
      Matrix lu = new Matrix(LU);
      return lu;
  }
  
  public int getPivsign() {
      return pivsign;
  }

  /**
   * Returns true if matrix is non-singular.
   * @return 
   */
  public boolean isNonsingular() {
    for (int j = 0; j < m; j++) {
      if (LU[j][j] == 0)
        return false;
    }
    return true;
  }
  
  /**
   * Inverses matrix using LU decomposition.
   * First an identity matrix A is created.
   * Then the equation X*Y = A is solved.
   * @return 
   */
  public Matrix inverse() {
    //Create identity matrix
    Matrix a = new Matrix(m, m);
    double[][] arr = a.getArray();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < m; j++) {
          if (i==j) {
              arr[i][j] = 1.0;
          }else {
              arr[i][j] = 0.0;
          }
      }
    }
    
    int nx = a.numCols();
    Matrix mat = a.subMatrix(piv, 0, nx - 1);
    double[][] y = mat.getArray();
    
    for (int k = 0; k < m; k++) {
      for (int i = k + 1; i < m; i++) {
          for (int j = 0; j < nx; j++) {
              y[i][j] -= y[k][j] * LU[i][k];
          }
      }
    }
    
    for (int k = m - 1; k >= 0; k--) {
      for (int j = 0; j < nx; j++) {
        y[k][j] /= LU[k][k];
      }
      for (int i = 0; i < k; i++) {
        for (int j = 0; j < nx; j++) {
          y[i][j] -= y[k][j] * LU[i][k];
        }
      }
    }
    return mat;
  }
}

