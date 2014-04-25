package com.mycompany.tiralabra_maven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Parser {

    public static int[][] parseAscii(File tiedosto) throws IOException {
        String[] rows = new String(Files.readAllBytes(tiedosto.toPath())).split("\n");

        int[][] result = new int[rows.length][rows[0].length()];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length() - 1; j++) {
                result[i][j] = Character.getNumericValue(rows[i].charAt(j));
                if (rows[i].charAt(j) == Maze.START_CHAR) {
                    result[i][j] = ArrayMaze.START;
                } else if (rows[i].charAt(j) == Maze.GOAL_CHAR) {
                    result[i][j] = ArrayMaze.GOAL;
                } else if (rows[i].charAt(j) == Maze.WALL_CHAR) {
                    result[i][j] = ArrayMaze.WALL;
                }
            }
        }
        return result;
    }

}
