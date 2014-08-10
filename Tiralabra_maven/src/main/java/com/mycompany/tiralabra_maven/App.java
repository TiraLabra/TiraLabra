package com.mycompany.tiralabra_maven;

import org.ejml.simple.SimpleMatrix;


public class App
{
    public static void main( String[] args )
    {
        System.out.println("MATRIISILASKIN");
        run();
    }    
    
    /**
     * Program loop, may need some refactoring.
     */
    public static void run() {
        MatrixCalculator mc = new MatrixCalculator();
        UI ui = new UI();
        while (true) {
            int op = ui.decideOperation();
            if (op == 0) {
                break;
            }else if (op == 1) {                
                System.out.println("Matriisi A");
                SimpleMatrix a = ui.createMatrix();
                System.out.println("Matriisi B");
                SimpleMatrix b = ui.createMatrix();
                if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
                    System.out.println("Matriisien yhteenlaskussa matriisien täytyy olla samankokoisia");
                    continue;
                }
                SimpleMatrix c = mc.add(a, b);
                a.print();
                System.out.println("+");
                b.print();
                System.out.println("=");
                c.print();
            }else if (op == 2) {
                System.out.println("Matriisi A");
                SimpleMatrix a = ui.createMatrix();
                System.out.println("Matriisi B");
                SimpleMatrix b = ui.createMatrix();
                SimpleMatrix c = mc.substract(a, b);
                if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
                    System.out.println("Matriisien vähennyslaskussa matriisien täytyy olla samankokoisia");
                    continue;
                }
                a.print();
                System.out.println("-");
                b.print();
                System.out.println("=");
                c.print();
            }else if (op == 3) {
                System.out.println("Matriisi A");
                SimpleMatrix a = ui.createMatrix();
                System.out.println("Matriisi B");
                SimpleMatrix b = ui.createMatrix();
                if (a.numCols() != b.numRows()) {
                    System.out.println("Matriisin A sarakkeiden määrän täytyy olla sama kuin matriisin B rivien määrä");
                    continue;
                }
                SimpleMatrix c = mc.multiply(a, b);  
                a.print();
                System.out.println("*");
                b.print();
                System.out.println("=");
                c.print();
            }else if (op == 4) {
                SimpleMatrix a = ui.createMatrix();
                Double b = ui.askScalar();
                SimpleMatrix c = mc.scalarMultiplication(a, b); 
                System.out.println(b + " *");
                c.print();
            }else if (op == 5) {
                SimpleMatrix a = ui.createMatrix();
                Double det = mc.getDeterminant(a); 
                a.print();
                System.out.println("Determinantti: " + det);
            }else if (op == 6) {
                SimpleMatrix a = ui.createMatrix();
                SimpleMatrix b = mc.inverse(a);
                a.print();
                System.out.println("Käänteismatriisi:");
                b.print();
            }else if (op == 7) {
                SimpleMatrix a = ui.createMatrix();
                SimpleMatrix b = mc.transpose(a);  
                a.print();
                System.out.println("Transpoosi:");
                b.print();
            }else {
                System.out.println("Valitse operaation numero");
            }
        }
    }
}
