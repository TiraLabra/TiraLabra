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
    private BigInteger[][][] table;
    /**
     * The height of boards hashed.
     */
    private int height;
    /**
     * The width of boards hashed.
     */
    private int width;

    public ZobristHash(int height, int width) {
        table = new BigInteger[height][width][3];
        this.height = height;
        this.width = width;

        initializeTable();
    }

    /**
     * Initialize the table which is used for hashing.
     */
    private void initializeTable() {
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int j = 0; j < 2; j++) {
                    table[y][x][j] = new BigInteger(64, random);
                }
            }
        }
    }

    /**
     * Hashes a board.
     *
     * @param board
     * @return long - 64-bit hash of the board
     */
    public BigInteger hash(Player[][] board) {
        BigInteger hash = new BigInteger("0");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int j = board[y][x].value() - 1;
                if (j >= 0) {
                    if (hash.intValue() == 0) {
                        hash = table[y][x][j];
                    } else {
                        hash = hash.xor(table[y][x][j]);
                    }
                }
            }
        }

        return hash;
    }

    /**
     * XORs a piece in if the piece was not yet included in the hash. Otherwise
     * XORs it out, removing it from the has.
     *
     * @param x
     * @param y
     * @param player
     * @param hash
     * @return
     */
    public BigInteger xorFlip(int x, int y, Player player, BigInteger hash) {
        int j = player.value() - 1;
        BigInteger newHash = hash.xor(table[y][x][j]);

        return newHash;
    }
}
