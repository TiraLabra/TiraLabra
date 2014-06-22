/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import tiralabra.game.Board;
import tiralabra.utilities.ArrayList;

/**
 *
 * @author atte
 */
public class AIPerformance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        AI ai = new AI(board);
        ArrayList<Long> times = new ArrayList<>();

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long sum = 0;
        int operations = 5;
        int moves = 0;

        System.out.println("Running tests...");
        System.out.print("[=");
        for (int i = 0; i < operations; i++) {
            board.reset();

            while (!board.gameOver()) {
                long start = System.currentTimeMillis();

                long move = ai.move();

                long timeTaken = System.currentTimeMillis() - start;

                board.placeTile(move);

                sum += timeTaken;

                if (timeTaken > max) {
                    max = timeTaken;
                }

                if (timeTaken < min) {
                    min = timeTaken;
                }
                times.add(timeTaken);
                moves++;
                
                System.out.print("=");
            }
            System.out.print("=");
        }

        System.out.print(">]\n");
        System.out.println("Maximum time taken: " + max + "ms");
        System.out.println("Average time taken: " + ((double) sum / moves) + "ms");
        System.out.println("All times:");
        for (Long time : times) {
            System.out.print(time + ";");
        }
    }

}
