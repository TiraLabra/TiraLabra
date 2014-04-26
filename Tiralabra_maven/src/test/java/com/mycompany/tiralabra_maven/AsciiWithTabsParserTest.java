package com.mycompany.tiralabra_maven;

import static com.mycompany.tiralabra_maven.maze.ArrayMaze.GOAL;
import static com.mycompany.tiralabra_maven.maze.ArrayMaze.START;
import static com.mycompany.tiralabra_maven.maze.ArrayMaze.WALL;
import com.mycompany.tiralabra_maven.io.AsciiWithTabsParser;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsciiWithTabsParserTest {

    @Test
    public void testParseRow() throws Exception {
        int[] expected = new int[]{1, 2};
        int[] result = new AsciiWithTabsParser().parseRow("1 2");
        assertArrayEquals(expected, result);

        expected = new int[]{START, 10, 20, GOAL, WALL};
        result = new AsciiWithTabsParser().parseRow("S 10 20 G #");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testParseAsciiWithTabs() throws Exception {
        int[][] expected = new int[][]{
            new int[]{1, 2},
            new int[]{3, 4}
        };
        int[][] result = new AsciiWithTabsParser().parse("1 2\n3 4");
        assertArrayEquals(expected, result);

        result = new AsciiWithTabsParser().parse("1 2 \n 3 4 ");
        assertArrayEquals(expected, result);
    }

}
