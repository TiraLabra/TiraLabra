package com.mycompany.tiralabra_maven;

import org.junit.Test;
import static org.junit.Assert.*;
import static com.mycompany.tiralabra_maven.maze.ArrayMaze.*;
import com.mycompany.tiralabra_maven.io.AsciiParser;

public class AsciiParserTest {

    @Test
    public void testParseRowOfChars() throws Exception {
        int[] expected = new int[]{1, 2};
        int[] result = new AsciiParser().parseRow("12");
        assertArrayEquals(expected, result);

        expected = new int[]{START, 1, 2, GOAL, WALL};
        result = new AsciiParser().parseRow("S12G#");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testParseAscii() throws Exception {
        int[][] expected = new int[][]{
            new int[]{1, 2},
            new int[]{3, 4}
        };
        int[][] result = new AsciiParser().parse("12\n34");
        assertArrayEquals(expected, result);

        expected = new int[][]{
            new int[]{START, WALL, WALL},
            new int[]{1, 2, 3},
            new int[]{1, 2, GOAL},};
        result = new AsciiParser().parse("S##\n123\n12G");
        assertArrayEquals(expected, result);
    }

}
