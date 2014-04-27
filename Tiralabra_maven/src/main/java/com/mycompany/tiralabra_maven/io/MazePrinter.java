package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.State;
import com.mycompany.tiralabra_maven.maze.Maze;

public class MazePrinter {

    public static final String START = "S";
    public static final String GOAL = "G";
    public static final String PATH = "*";
    public static final String CELL = " ";
    public static final String WALL = "#";

    private final Maze maze;

    public MazePrinter(Maze maze) {
        this.maze = maze;
    }

    public String toString(List<State> states) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < maze.getHeight(); x++) {
            for (int y = 0; y < maze.getWidth(); y++) {
                if (maze.getStartState().equals(maze.getState(x, y))) {
                    sb.append(START);
                } else if (maze.isGoalState(x, y)) {
                    sb.append(GOAL);
                } else if (states.contains(maze.getState(x, y))) {
                    sb.append(PATH);
                } else if (maze.getState(x, y).getCost() > 0) {
                    sb.append(CELL);
                } else {
                    sb.append(WALL);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void print(List<State> path) {
        System.out.println(toString(path));
    }

}
