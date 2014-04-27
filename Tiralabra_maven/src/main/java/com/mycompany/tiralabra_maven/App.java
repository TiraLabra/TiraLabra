package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.algorithm.AStarSearch;
import com.mycompany.tiralabra_maven.io.MazePrinter;
import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import com.mycompany.tiralabra_maven.maze.Maze;
import java.io.IOException;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        int s = ArrayMaze.START;
        int g = ArrayMaze.GOAL;
        int[][] array = new int[][]{
            new int[]{s, 0, 7, 6, 5, 4},
            new int[]{8, 0, 6, 5, 4, 3},
            new int[]{7, 0, 5, 4, 3, 2},
            new int[]{6, 0, 4, 1, 1, 1},
            new int[]{5, 4, 3, 2, 8, g}
        };
        Maze maze = ArrayMaze.create(array);
        System.out.println(maze.getWidth() + ", " + maze.getHeight());
        AStarSearch search = new AStarSearch(maze, maze.getHeuristic());
        MazePrinter printer = new MazePrinter(maze);
        printer.print(search.findOptimalPath());
    }
}
