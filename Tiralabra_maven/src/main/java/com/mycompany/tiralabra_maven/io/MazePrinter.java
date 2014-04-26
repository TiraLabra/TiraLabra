package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.State;
import com.mycompany.tiralabra_maven.maze.Maze;

public class MazePrinter {

    public static final char START_CHAR = 'S';
    public static final char GOAL_CHAR = 'G';
    public static final char PATH_CHAR = '*';
    public static final char CELL_CHAR = ' ';
    public static final char WALL_CHAR = '#';

    private final Maze maze;

    public MazePrinter(Maze maze) {
        this.maze = maze;
    }

    public String toString(List<State> states) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < maze.getHeight(); x++) {
            for (int y = 0; y < maze.getWidth(); y++) {
                if (maze.getStartState().equals(maze.getState(x, y))) {
                    sb.append(START_CHAR);
                } else if (maze.isGoalState(x, y)) {
                    sb.append(GOAL_CHAR);
                } else if (states.contains(maze.getState(x, y))) {
                    sb.append(PATH_CHAR);
                } else if (maze.getState(x, y).getCost() > 0) {
                    sb.append(CELL_CHAR);
                } else {
                    sb.append(WALL_CHAR);
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
