package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.algorithm.*;
import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import com.mycompany.tiralabra_maven.maze.Maze;
import com.mycompany.tiralabra_maven.datastructures.Path;
import com.mycompany.tiralabra_maven.io.AsciiWithTabsParser;
import com.mycompany.tiralabra_maven.io.FileParser;

import java.io.File;
import java.io.IOException;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
    	int s = ArrayMaze.START;
    	int g = ArrayMaze.GOAL;
    	int[][] array = new int [][]{
    			new int[] {s, 0, 7, 6, 5, 4},
    			new int[] {8, 0, 6, 5, 4, 3},
    			new int[] {7, 0, 5, 4, 3, 2},
    			new int[] {6, 0, 4, 1, 1, 1},
    			new int[] {5, 4, 3, 2, 8, g}
    	};
        Maze maze = ArrayMaze.create(array);
        System.out.println(maze.getWidth() + ", " + maze.getHeight());
        SearchStategy search = new AStartSearch(maze);
        Path path = search.findOptimalPath();
        System.out.println(maze.getExpandedStates());
        System.out.println(path);
    }
}
