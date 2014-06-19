
package com.mycompany.tiralabra_maven.main;

import com.mycompany.tiralabra_maven.controller.MatrixCalculator;
import com.mycompany.tiralabra_maven.view.ConsoleIo;
import com.mycompany.tiralabra_maven.view.Io;
import java.util.Scanner;

/**
 *
 * @author gabriel
 */
public class Main {
    
    public static void main(String[] args) {
        Io io = new ConsoleIo(new Scanner(System.in));
        MatrixCalculator matrixCalculator = new MatrixCalculator(io);
        matrixCalculator.run();
    }

}
