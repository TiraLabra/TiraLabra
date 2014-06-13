package tiralabra.app;

import java.util.Scanner;
import tiralabra.app.ui.Runnable;
import tiralabra.app.ui.TextUI;

/**
 * Used for manual debugging and testing.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Runnable game = new Runnable();
        TextUI ui = new TextUI(game, scanner);

        game.start();
        ui.start();
    }
}
