package tiralabra.app;

import java.util.Scanner;
import tiralabra.app.ui.MoveTransmitter;
import tiralabra.app.ui.TextUI;

/**
 * Used for manual debugging and testing.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        TextUI ui = new TextUI(scanner);

        ui.start();
    }
}
