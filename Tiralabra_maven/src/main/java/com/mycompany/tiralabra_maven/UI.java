
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
     * @return Matrix
     */
    public Matrix createMatrix() {        
        System.out.println("Anna matriisin rivien määrä: ");
        int m = Integer.parseInt(s.nextLine());
        System.out.println("Anna matriisin sarakkeiden määrä: ");
        int n = Integer.parseInt(s.nextLine());    
        System.out.println("Annetaanko matriisille satunnaiset luvut?");        
        int r = 0;
        while (r != 1 && r != 2) {
            System.out.println("1: Kyllä");
            System.out.println("2: Ei");
            r = Integer.parseInt(s.nextLine());
        }
        if (r == 1) {
            System.out.println("Anna alaraja luvuille");
            int l = Integer.parseInt(s.nextLine());
            System.out.println("Anna yläraja luvuille");
            int h = Integer.parseInt(s.nextLine());
            return new Matrix(m,n,l,h);
        }else {
            Matrix matrix = new Matrix(m,n);
            for(int i=0; i<m; i++) {                
                System.out.println("Luettele luvut rivillä " + (i+1));
                for(int j=0; j<n; j++) {
                    int num = Integer.parseInt(s.nextLine());
                    matrix.setValue(i, j, num);
                }                
            }    
            return matrix;
        }        
    }
    
    /**
     * Asks the scalar from the user.
     * @return Scalar as double
     */
    public Double askScalar() {
        System.out.println("Skalaari, jolla haluat kertoa:");
        double b = Double.parseDouble(s.nextLine());
        return b;
    }
}
