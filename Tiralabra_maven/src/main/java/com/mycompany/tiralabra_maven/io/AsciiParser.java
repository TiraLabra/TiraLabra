package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import com.mycompany.tiralabra_maven.maze.Maze;

public class AsciiParser implements Parser {

    @Override
    public int[][] parse(String ascii) {
        String[] rows = ascii.split("\n");

        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            result[i] = parseRow(rows[i]);
        }
        return result;
    }

    @Override
    public int[] parseRow(String row) {
        char[] chars = row.toCharArray();
        int[] result = new int[row.length()];
        for (int i = 0; i < row.length(); i++) {
            result[i] = Character.getNumericValue(chars[i]);
            if (chars[i] == MazePrinter.START_CHAR) {
                result[i] = ArrayMaze.START;
            } else if (chars[i] == MazePrinter.GOAL_CHAR) {
                result[i] = ArrayMaze.GOAL;
            } else if (chars[i] == MazePrinter.WALL_CHAR) {
                result[i] = ArrayMaze.WALL;
            }
        }
        return result;
    }

}
