package tiralabra.app;

import java.util.Scanner;
import tiralabra.game.Board;
import tiralabra.game.Game;

/**
 * Used for manual debugging and testing.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Game othello = new Game(false, true);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("------------");
            printBoard(othello.getBoard());
            System.out.println("");
            System.out.print("y: ");
            int y = Integer.parseInt(scanner.nextLine());
            System.out.print("x: ");
            int x = Integer.parseInt(scanner.nextLine());
            
            othello.playHumanTurn(Board.point(x, y));
            
            printBoard(othello.getBoard());
            
            Thread.sleep(1000);
            
            othello.playAITurn();
        }
    }

    public static void printBoard(byte[][] game) {
        System.out.print(" ");
        for (int x = 0; x < game.length; x++) {
            System.out.print(x);
        }
        System.out.println("");
        for (int y = 0; y < game.length; y++) {
            System.out.print(y);
            for (int x = 0; x < game[0].length; x++) {
                System.out.print(game[y][x] == 0 ? '.' : game[y][x] == 1 ? 'X' : 'O');
            }
            System.out.println("");
        }
    }
}
