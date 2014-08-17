package com.mycompany.tiralabra_maven;

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
                Matrix a = ui.createMatrix();
                System.out.println("Matriisi B");
                Matrix b = ui.createMatrix();
                if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
                    System.out.println("Matriisien yhteenlaskussa matriisien täytyy olla samankokoisia");
                    continue;
                }
                Matrix c = mc.add(a, b);
                a.print();
                System.out.println("+");
                b.print();
                System.out.println("=");
                c.print();
            }else if (op == 2) {
                System.out.println("Matriisi A");
                Matrix a = ui.createMatrix();
                System.out.println("Matriisi B");
                Matrix b = ui.createMatrix();
                Matrix c = mc.substract(a, b);
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
                Matrix a = ui.createMatrix();
                System.out.println("Matriisi B");
                Matrix b = ui.createMatrix();
                if (a.numCols() != b.numRows()) {
                    System.out.println("Matriisin A sarakkeiden määrän täytyy olla sama kuin matriisin B rivien määrä");
                    continue;
                }
                Matrix c = mc.multiply(a, b);
                a.print();
                System.out.println("*");
                b.print();
                System.out.println("=");
                c.print();
            }else if (op == 4) {
                Matrix a = ui.createMatrix();
                Double b = ui.askScalar();
                Matrix c = mc.scalarMultiplication(a, b); 
                System.out.println(b + " *");
                a.print();
                System.out.println("=");
                c.print();
            }else if (op == 5) {
                Matrix a = ui.createMatrix();
                Double det = mc.getDeterminant(a); 
                a.print();
                System.out.println("Determinantti: " + det);
            }else if (op == 6) {
                Matrix a = ui.createMatrix();
                Matrix b = mc.inverse(a);
                a.print();
                System.out.println("Käänteismatriisi:");
                b.print();
            }else if (op == 7) {
                Matrix a = ui.createMatrix();
                Matrix b = mc.transpose(a);  
                a.print();
                System.out.println("Transpoosi:");
                b.print();
            }else {
                System.out.println("Valitse operaation numero");
            }
        }
    }
}
