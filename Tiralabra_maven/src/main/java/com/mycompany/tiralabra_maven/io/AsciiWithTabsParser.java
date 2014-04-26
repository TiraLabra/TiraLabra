package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.maze.ArrayMaze;

public class AsciiWithTabsParser implements Parser {

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
        String[] array = row.trim().split("\\s");
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(MazePrinter.START_CHAR + "")) {
                result[i] = ArrayMaze.START;
            } else if (array[i].equalsIgnoreCase(MazePrinter.GOAL_CHAR + "")) {
                result[i] = ArrayMaze.GOAL;
            } else if (array[i].equalsIgnoreCase(MazePrinter.WALL_CHAR + "")) {
                result[i] = ArrayMaze.WALL;
            } else {
                result[i] = Integer.parseInt(array[i]);
            }
        }
        return result;
    }
}
