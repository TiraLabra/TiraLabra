/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.performance;

import tiralabra.game.Board;
import tiralabra.game.ai.AI;

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

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long sum = 0;
        int operations = 1;
        int moves = 0;

        System.out.println("AI time taken per turn: ");
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

                moves++;
                System.out.print("=");
            }
            System.out.print("=");
        }

        System.out.print(">]\n");
        System.out.println("Maximum time taken: " + max + "ms");
        System.out.println("Minimum time taken: " + min + "ms");
        System.out.println("Average time taken: " + ((double) sum / moves) + "ms");

    }

}
