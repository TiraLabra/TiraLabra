package tiralabra.app;

import java.util.Scanner;
import tiralabra.game.Game;

/**
 * Used for manual debugging and testing.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Game othello = new Game(true, true);
        Scanner scanner = new Scanner(System.in);
        printBoard(othello.getBoard());
        while (!othello.gameOver()) {
            othello.nextTurn();
            System.out.println("------------");
            printBoard(othello.getBoard());
            System.out.println("");
            
            
                        
        }
    }

    public static void printBoard(int[][] game) {
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
