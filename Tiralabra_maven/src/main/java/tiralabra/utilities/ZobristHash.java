/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.math.BigInteger;
import java.util.Random;
import tiralabra.game.Player;

/**
 * This class can hash a board using Zobrist hashing:
 * http://en.wikipedia.org/wiki/Zobrist_hashing . In the future, will also work
 * as a HashMap for 2d Player-arrays to hold the calculated board-values, best
 * moves, etc.
 *
 * @author atte
 */
public class ZobristHash {

    /**
     * Holds the randomly generated numbers which determine a boards hashing.
     */
    private BigInteger[][] table;

    public ZobristHash() {
        table = new BigInteger[64][3];
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 2; j++) {
                table[i][j] = new BigInteger(64, new Random());
            }
        }
    }

    /**
     * Hashes a board.
     *
     * @param board
     * @return hash of the board
     */
    public int hash(Player[][] board) {
        BigInteger hash = new BigInteger("0");

        for (int i = 0; i < 64; i++) {
            int j = board[i / 8][i % 8].value() - 1;
            if (j >= 0) {
                if (hash.intValue() == 0) {
                    hash = table[i][j];
                } else {
                    hash = hash.xor(table[i][j]);
                }
            }
        }

        return hash.intValue();
    }
}
