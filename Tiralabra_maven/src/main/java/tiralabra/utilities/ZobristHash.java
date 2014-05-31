/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

import java.util.Random;

/**
 *
 * @author atte
 */
public class ZobristHash {
    
    private String[][] table;

    public ZobristHash() {
        table = new String[64][3];
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = Integer.toBinaryString(new Random().nextInt());
                while(table[i][j].length() < 32) {
                    table[i][j] += 0;
                }
            }
        }
    }
    
    /**
     * Hashes a board.
     * @param board
     * @return hash of the board
     */
    public int hash(int[][] board) {
        String hash = "";
        
        for (int i = 0; i < 64; i++) {
            int j = board[i / 8][i%8];
            hash = XOR(hash, table[i][j]);
        }
        
        return Integer.parseInt(hash);
    }
    
    public String XOR(String hash, String string) {
        if (hash.isEmpty()) return string;
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < string.length(); i++) {
            if (hash.charAt(i) == string.charAt(i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public String[][] getTable() {
        return table;
    }
    
    
}
