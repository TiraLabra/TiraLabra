
package com.mycompany.tiralabra_maven;

import java.util.Scanner;
import org.ejml.simple.SimpleMatrix;

public class UI {
    Scanner s;
    
    public UI() {
        s = new Scanner(System.in);
    }
    
    /**
     * UI for choosing the operation to be done.
     *  
     */
    public Integer decideOperation() {
        System.out.println("Valitse operaatio: ");
        System.out.println("1: Summa");
        System.out.println("2: Erotus");
        System.out.println("3: Tulo");
        System.out.println("4: Skalaaritulo");
        System.out.println("5: Determinantti");
        System.out.println("6: Käänteismatriisi");
        System.out.println("7: Transpoosi");
        System.out.println("0: Lopeta ohjelma");
        int op = Integer.parseInt(s.nextLine());
        return op;
    }
    
    /**
     * Ui for creating a matrix.
     * 
     */
    public SimpleMatrix createMatrix() {
        System.out.println("Anna matriisin rivien määrä (1-20): ");
        int m = Integer.parseInt(s.nextLine());
        System.out.println("Anna matriisin sarakkeiden määrä (1-20): ");
        int n = Integer.parseInt(s.nextLine());
        double[][] matrix = new double[m][n];
        for(int i=0; i<m; i++) {
            System.out.println("Luettele luvut rivillä " + (i+1));
            for(int j=0; j<n; j++) {
                int num = Integer.parseInt(s.nextLine());
                matrix[i][j] = num;
            }
        }
        return new SimpleMatrix(matrix);        
    }
    
    public Double askScalar() {
        System.out.println("Skalaari, jolla haluat kertoa:");
        double b = Double.parseDouble(s.nextLine());
        return b;
    }
    
    public void printMatrix(SimpleMatrix m) {        
        for(int i=0; i<m.numRows(); i++) {
            System.out.print("|");
            for (int j=0; j<m.numCols(); j++) {
                System.out.printf("%.2f" + " ", m.get(i, j));
            }
            System.out.println("|");
        }
    }
}
