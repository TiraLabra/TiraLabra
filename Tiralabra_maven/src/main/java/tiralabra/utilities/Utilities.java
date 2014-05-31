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
public class Utilities {

    public static int[][] copy2dArray(int[][] array) {
        int[][] copy = new int[array.length][array[0].length];

        for (int y = 0; y < array.length; y++) {
            copy[y] = array[y].clone();
        }
        return copy;
    }

    public static void printBoard(Player[][] game) {
        System.out.print(" ");
        for (int x = 0; x < game[0].length; x++) {
            System.out.print(x);
        }
        System.out.println("");
        for (int y = 0; y < game.length; y++) {
            System.out.print(y);
            for (int x = 0; x < game[0].length; x++) {
                System.out.print(game[y][x] == Player.NONE ? '.' : game[y][x] == Player.BLACK ? 'X' : 'O');
            }
            System.out.println("");
        }
    }
    
    public static Player[][] createPlayerTable(int[][] table) {
        Player[][] board = new Player[table.length][table[0].length];

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                if (table[y][x] == 0) {
                    board[y][x] = Player.NONE;
                } else if (table[y][x] == 1) {
                    board[y][x] = Player.BLACK;
                } else {
                    board[y][x] = Player.WHITE;
                }
            }
        }
        return board;
    }
}
