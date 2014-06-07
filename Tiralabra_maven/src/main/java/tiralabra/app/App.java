package tiralabra.app;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import tiralabra.game.Board;
import tiralabra.game.Game;
import tiralabra.app.ui.TextUI;
import tiralabra.utilities.Utilities;
import tiralabra.utilities.ZobristHash;


/**
 * Used for manual debugging and testing.
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        TextUI ui = new TextUI(game, scanner);
        Utilities.printBoard(game.getBoard().getBoard());
        game.start();
        ui.start();
        
        Stack stack = new Stack();
//        ZobristHash lel = new ZobristHash();
//        Board board = new Board();
//
//        System.out.println(lel.hash(board.getBoard()));
//        System.out.println(lel.hash(board.getBoard()));

    }
}
