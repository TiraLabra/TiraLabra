package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;

public class ArrayMaze extends AbstractMaze {

    public static final int WALL = 0;
    public static final int START = -1;
    public static final int GOAL = -2;

    private final int[][] maze;

    private ArrayMaze(int[][] maze, MazeNode start, MazeNode goal) {
        super(start, goal);
        this.maze = maze;
    }

    public static Maze create(int[][] maze) {
        MazeNode start = null;
        MazeNode goal = null;
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                if (maze[x][y] == START) {
                    start = new MazeNode(x, y);
                } else if (maze[x][y] == GOAL) {
                    goal = new MazeNode(x, y);
                }
            }
        }
        return new ArrayMaze(maze, start, goal);
    }

    @Override
    public int getHeight() {
        return maze.length;
    }

    @Override
    public int getWidth() {
        return maze[0].length;
    }

    @Override
    public List<Node> getSuccessors(Node node) {
        MazeNode s = (MazeNode) node;
        int x = s.getX();
        int y = s.getY();
        List<Node> list = new List<>();

        if (x > 0) {
            MazeNode n = getMazeNode(x - 1, y);
            n.setParent(node);
            list.insertLast(n);
        }
        if (y > 0) {
            MazeNode n = getMazeNode(x, y - 1);
            n.setParent(node);
            list.insertLast(n);
        }
        if (x < getHeight() - 1) {
            MazeNode n = getMazeNode(x + 1, y);
            n.setParent(node);
            list.insertLast(n);
        }
        if (y < getWidth() - 1) {
            MazeNode n = getMazeNode(x, y + 1);
            n.setParent(node);
            list.insertLast(n);
        }

        List<Node> copy = new List<>();
        for (Node state : list) {
            if (state.getCost() != 0) {
                copy.insertLast(state);
            }
        }
        expanded += 1;
        return copy;
    }

    @Override
    public MazeNode getMazeNode(int x, int y) {
        MazeNode s = new MazeNode(x, y);
        s.setCost(maze[x][y]);
        return s;
    }

}
