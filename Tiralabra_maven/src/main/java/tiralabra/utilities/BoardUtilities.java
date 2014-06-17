/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import tiralabra.game.Player;

/**
 *
 * @author atte
 */
public class BoardUtilities {

    /**
     * Copies a generic 2d array and returns the copy, without altering the
     * original.
     *
     * @param array
     * @return
     */
    public static Object[][] copy2dArray(Object[][] array) {
        Object[][] copy = new Object[array.length][array[0].length];

        for (int y = 0; y < array.length; y++) {
            copy[y] = array[y].clone();
        }
        return array.getClass().cast(copy);
    }

    /**
     * Prints a board of Player enumerables, where Black is X, White is O and
     * empty tiles are '.' .
     *
     * @param board
     */
    public static void printBoard(Player[][] board) {
        StringBuilder sb = new StringBuilder();
        
        String letters = "ABCDEFGH";
        sb.append(" ");
        for (int x = 0; x < board[0].length; x++) {
            sb.append(letters.charAt(x));
        }

        sb.append('\n');
        for (int y = 0; y < board.length; y++) {
            sb.append(y + 1);
            for (int x = 0; x < board[0].length; x++) {
                sb.append(board[y][x] == Player.NONE ? '.' : board[y][x] == Player.BLACK ? 'X' : 'O');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    /**
     * Creates and returns a new Player table based on a integer 2d array where
     * each index contains a number between 0-2.
     *
     * @param table
     * @return
     */
    public static Player[][] createPlayerTable(int[][] table) {
        Player[][] board = new Player[table.length][table[0].length];

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                board[y][x] = Player.player(table[y][x]);
            }
        }
        return board;
    }
}
