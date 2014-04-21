package com.mycompany.tiralabra_maven;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App {

    public static void main(String[] args) {
        int s = ArrayMaze.START;
        int g = ArrayMaze.GOAL;
        int[][] array = new int[][]{
                    new int[]{s, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    new int[]{2, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    new int[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, g}};
        Maze maze = ArrayMaze.create(array);
        System.out.println(maze.getWidth() + ", " + maze.getHeight());
        //maze = new SimpleMaze(100, 100, new State(35, 45), new State(99,99));
        SearchStategy search = new AStartSearch(maze);
        Path path = search.findOptimalPath();
        System.out.println(maze.getExpandedStates());
        System.out.println(path);
    }
}
