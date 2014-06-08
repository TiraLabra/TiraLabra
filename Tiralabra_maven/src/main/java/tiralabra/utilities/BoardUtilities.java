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

    public static Object[][] copy2dArray(Object[][] array) {
        Object[][] copy = new Object[array.length][array[0].length];

        for (int y = 0; y < array.length; y++) {
            copy[y] = array[y].clone();
        }
        return array.getClass().cast(copy);
    }

    public static void printBoard(Player[][] board) {
        StringBuilder sb = new StringBuilder();
                
        sb.append(" ");
        for (int x = 0; x < board[0].length; x++) {
            sb.append(x);
        }
        
        sb.append('\n');
        for (int y = 0; y < board.length; y++) {
            sb.append(y);
            for (int x = 0; x < board[0].length; x++) {
                sb.append(board[y][x] == Player.NONE ? '.' : board[y][x] == Player.BLACK ? 'X' : 'O');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

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
