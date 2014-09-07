package benchmark;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Main benchmark class.
 *
 * @author ydna
 */
public class Main {

    public static void main(String[] args) {
        try {
            PrintWriter out = new PrintWriter("test.dat");
            out.print(new Multiplication().data);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        /*try {
         PrintWriter out = new PrintWriter("determinant.dat");
         out.print(new Determinant().data);
         out.close();
         } catch (FileNotFoundException e) {
         System.out.println("File not found");
         }
         try {
         PrintWriter out = new PrintWriter("inversematrix.dat");
         out.print(new InverseMatrix().data);
         out.close();
         } catch (FileNotFoundException e) {
         System.out.println("File not found");
         }*/
    }

}
