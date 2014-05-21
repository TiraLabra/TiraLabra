package tiralabra.app;

import java.util.Scanner;
import tiralabra.game.Board;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Board game = new Board();

        Scanner scanner = new Scanner(System.in);
        int turn = 1;
        while (true) {
            System.out.println("------------");
            printBoard(game.getBoard());
            System.out.println("");
            System.out.print("y: ");
            int y = Integer.parseInt(scanner.nextLine());
            System.out.print("x: ");
            int x = Integer.parseInt(scanner.nextLine());
            if (game.put(x, y, turn)) {
                turn = turn == 1 ? 2 : 1;
            }
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
