package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.algorithm.AStartSearch;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
    	int[][] array = Parser.parseAscii(new File("/home/yessergire/src/testi2.txt"));
        for(int[] row : array)
            System.out.println(Arrays.toString(row));
        Maze maze = ArrayMaze.create(array);
        System.out.println(maze.getWidth() + ", " + maze.getHeight());
        AStartSearch search = new AStartSearch(maze);
        Path path = search.findOptimalPath();
        System.out.println(maze.getExpandedStates());
        System.out.println(path);
    }
}
