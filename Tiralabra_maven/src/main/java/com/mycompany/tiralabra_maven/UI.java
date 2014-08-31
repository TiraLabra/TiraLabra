
package com.mycompany.tiralabra_maven;

import java.util.Scanner;

public class UI {
    Scanner s;
    MatrixCalculator mc;
    
    public UI() {
        s = new Scanner(System.in);
        mc = new MatrixCalculator();
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
        System.out.println("8: Gauss-Jordan eliminaatio");
        System.out.println("0: Lopeta ohjelma");
        int op = 10;
        try {
            op = Integer.parseInt(s.nextLine());
        }catch (Exception e) {
            System.out.println("Anna operaation numero");            
        }
        return op;
    }
    
    /**
     * Performs the operation chosen by the user.
     * @param op 
     */
    public void doOperation(int op) {
        if (op == 1) {                
            System.out.println("Matriisi A");
            Matrix a = createMatrix();
            System.out.println("Matriisi B");
            Matrix b = createMatrix(a.numRows(), a.numCols());            
            Matrix c = a.add(b);
            print(a);
            System.out.println("+");
            print(b);
            System.out.println("=");
            print(c);
        }else if (op == 2) {
            System.out.println("Matriisi A");
            Matrix a = createMatrix();
            System.out.println("Matriisi B");
            Matrix b = createMatrix(a.numRows(), a.numCols());
            Matrix c = a.subtract(b);
            print(a);
            System.out.println("-");
            print(b);
            System.out.println("=");
            print(c);
        }else if (op == 3) {
            System.out.println("Matriisi A");
            Matrix a = createMatrix();
            System.out.println("Matriisi B");
            Matrix b = createMatrix();
            if (a.numCols() != b.numRows()) {
                System.out.println("Matriisin A sarakkeiden määrän täytyy olla sama kuin matriisin B rivien määrä");
                return;
            }
            Matrix c = mc.StrassenMultiplication(a, b);
            print(a);
            System.out.println("*");
            print(b);
            System.out.println("=");
            print(c);
        }else if (op == 4) {
            Matrix a = createMatrix();
            Double b = askScalar();
            Matrix c = a.multiplyByScalar(b); 
            System.out.println(b + " *");
            print(a);
            System.out.println("=");
            print(c);
        }else if (op == 5) {
            Matrix a = createSquareMatrix();
            Double det = mc.determinant(a); 
            print(a);
            System.out.println("Determinantti: " + det);
        }else if (op == 6) {
            Matrix a = createSquareMatrix();
            print(a);          
            a = mc.inverse(a);
            if (a == null) {
                System.out.println("Matriisi ei ole kääntyvä");
                return;
            }
            System.out.println("Käänteismatriisi:");
            print(a);
        }else if (op == 7) {
            Matrix a = createMatrix();
            Matrix b = a.transpose();  
            print(a);
            System.out.println("Transpoosi:");
            print(b);
        }else if (op == 8) {
            Matrix a = createMatrix();
            print(a);
            mc.GaussJordan(a);  
            System.out.println("Tulos:");
            print(a);
        }else {
            System.out.println("Valitse operaation numero");
        }
    }
    
    /**
     * Create a matrix of arbitrary size.
     * @return Matrix
     */
    public Matrix createMatrix() {        
        System.out.println("Anna matriisin rivien määrä: ");
        int m = Integer.parseInt(s.nextLine());
        System.out.println("Anna matriisin sarakkeiden määrä: ");
        int n = Integer.parseInt(s.nextLine());   
        return createMatrix(m,n);
             
    }
    
    /**
     * Creates a square matrix.
     * @return 
     */
    public Matrix createSquareMatrix() {        
        System.out.println("Anna matriisin rivien/sarakkeiden määrä: ");
        int n = Integer.parseInt(s.nextLine());    
        return createMatrix(n,n);
    }
    
    public Matrix createMatrix(int m, int n) {
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
            for(int i=0; i<n; i++) {                
                System.out.println("Luettele luvut rivillä " + (i+1));
                for(int j=0; j<n; j++) {
                    int num = Integer.parseInt(s.nextLine());
                    matrix.setValue(i, j, num);
                }                
            }    
            return matrix;
        } 
    }
    
    public void print(Matrix m) {
        for(int i=0; i<m.numRows(); i++) {
            System.out.print("|");
            for (int j=0; j<m.numCols(); j++) {
                System.out.printf("%.2f" + " ", m.get(i, j));
            }
            System.out.println("|");
        }
    } 
    
    /**
     * Asks the scalar for scalar multiplication.
     * @return Scalar as double
     */
    public Double askScalar() {
        System.out.println("Skalaari, jolla haluat kertoa:");
        double b = Double.parseDouble(s.nextLine());
        return b;
    }
}
