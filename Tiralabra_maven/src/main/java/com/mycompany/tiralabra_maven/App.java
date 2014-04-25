package com.mycompany.tiralabra_maven;

import java.io.File;
import java.io.IOException;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
    	int[][] array = Parser.parseAscii(new File(args[0]));
        Maze maze = ArrayMaze.create(array);
        System.out.println(maze.getWidth() + ", " + maze.getHeight());
        SearchStategy search = new AStartSearch(maze);
        Path path = search.findOptimalPath();
        System.out.println(maze.getExpandedStates());
        System.out.println(path);
    }
}
