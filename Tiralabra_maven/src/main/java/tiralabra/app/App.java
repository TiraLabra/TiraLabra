package tiralabra.app;

import java.util.Scanner;
import tiralabra.game.Board;
import tiralabra.game.Player;
import tiralabra.utilities.Utilities;
import tiralabra.utilities.ZobristHash;

/**
 * Used for manual debugging and testing.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Board othello = new Board();
        Scanner scanner = new Scanner(System.in);
        Utilities.printBoard(othello.getBoard());
        
        othello.place(5, 3, Player.BLACK, true);
        Utilities.printBoard(othello.getBoard());
        othello.undo();
//        ZobristHash lel = new ZobristHash();
//        System.out.println("12345678901234567890123456789012");
//        for (int i = 0; i < 64; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println(lel.getTable()[i][j]);
//            }
//        }
    }
}
