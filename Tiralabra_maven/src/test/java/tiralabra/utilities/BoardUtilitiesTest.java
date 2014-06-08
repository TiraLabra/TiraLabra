/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.game.Player;

/**
 *
 * @author atte
 */
public class BoardUtilitiesTest {

    @Test
    public void convertingIntegerTablesToPlayerTablesWorks() {
        int[][] table = initializeAnIntegerTable();

        Player[][] board = BoardUtilities.createPlayerTable(table);

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                if (table[y][x] == 0) {
                    assertEquals("Wrong player at (" + x + "," + y + ")", Player.NONE, board[y][x]);
                } else if (table[y][x] == 1) {
                    assertEquals("Wrong player at (" + x + "," + y + ")", Player.BLACK, board[y][x]);
                } else {
                    assertEquals("Wrong player at (" + x + "," + y + ")", Player.WHITE, board[y][x]);
                }
            }
        }
    }

    private int[][] initializeAnIntegerTable() {
        int[][] table = new int[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                table[y][x] = new Random().nextInt(3);
            }
        }
        return table;
    }
}
