package tiralabra.app;

import java.util.Scanner;
import tiralabra.app.ui.TextUI;

/**
 * Start the project.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        TextUI ui = new TextUI(scanner);
        ui.start();
    }
}
