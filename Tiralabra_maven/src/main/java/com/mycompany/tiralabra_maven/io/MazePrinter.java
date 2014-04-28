package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;
import com.mycompany.tiralabra_maven.maze.Maze;
import com.mycompany.tiralabra_maven.maze.MazeNode;

/**
 *
 * @author yessergire
 */
public class MazePrinter {

    /**
     *
     */
    public static final String START = "S";

    /**
     *
     */
    public static final String GOAL = "G";

    /**
     *
     */
    public static final String PATH = "*";

    /**
     *
     */
    public static final String CELL = " ";

    /**
     *
     */
    public static final String WALL = "#";

    private final Maze maze;

    /**
     *
     * @param maze
     */
    public MazePrinter(Maze maze) {
        this.maze = maze;
    }

    /**
     *
     * @param states
     * @return
     */
    public String toString(List<Node> states) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < maze.getHeight(); x++) {
            for (int y = 0; y < maze.getWidth(); y++) {
                MazeNode node = maze.getMazeNode(x, y);
                if (maze.getStartNode().equals(node)) {
                    sb.append(START);
                } else if (maze.getGoalNode().equals(node)) {
                    sb.append(GOAL);
                } else if (states.contains(node)) {
                    sb.append(PATH);
                } else if (node.getCost() > 0) {
                    sb.append(node.getCost());
                } else {
                    sb.append(WALL);
                }
                sb.append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < maze.getHeight(); x++) {
            for (int y = 0; y < maze.getWidth(); y++) {
                MazeNode node = maze.getMazeNode(x, y);
                if (maze.getStartNode().equals(node)) {
                    sb.append(START);
                } else if (maze.getGoalNode().equals(node)) {
                    sb.append(GOAL);
                } else if (node.getCost() > 0) {
                    sb.append(node.getCost());
                } else {
                    sb.append(WALL);
                }
                sb.append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     *
     * @param path
     */
    public void print(List<Node> path) {
        System.out.println(toString(path));
    }

}
