package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.data.ArrayMaze;
import com.mycompany.tiralabra_maven.data.Maze;
import com.mycompany.tiralabra_maven.data.Path;
import com.mycompany.tiralabra_maven.algorithm.AStartSearch;
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
    	int[][] array = new FileParser(new AsciiWithTabsParser(), new File(args[0])).parse();
        Maze maze = ArrayMaze.create(array);
        System.out.println(maze.getWidth() + ", " + maze.getHeight());
        AStartSearch search = new AStartSearch(maze);
        Path path = search.findOptimalPath();
        System.out.println(maze.getExpandedStates());
        System.out.println(path);
    }
}
