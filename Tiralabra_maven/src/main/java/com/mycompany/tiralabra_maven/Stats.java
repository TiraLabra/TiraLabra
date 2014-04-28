package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.algorithm.AStarSearch;
import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import java.util.ArrayList;

/**
 *
 * @author yessergire
 */
public class Stats {
    private static ArrayMaze maze;
    private static ArrayList<String> list;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        calcSearchTime();
        for (String string : list)
            System.out.println(string);
    }

    private static void calcSearchTime() {
        list = new ArrayList<>();
        for (int i = 10; i < 300; i++) {
            long time = timeSearch(i);
            list.add(i + ", " + time);
        }
    }

    private static long timeSearch(int i) {
        long start = System.currentTimeMillis();
        maze = ArrayMaze.emptyMaze(i, i);
        AStarSearch searcher = new AStarSearch(maze, maze);
        searcher.findPath(maze.getStartNode(), maze.getGoalNode());
        long stop = System.currentTimeMillis();
        return stop - start;
    }
}
