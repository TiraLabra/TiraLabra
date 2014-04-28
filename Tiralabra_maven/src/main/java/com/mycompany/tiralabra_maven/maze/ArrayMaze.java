package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;
import java.util.Random;

/**
 * 
 * @author  Yessergire Mohamed
 */
public class ArrayMaze extends AbstractMaze {

    /**
     *
     */
    public static final int WALL = 0;

    /**
     *
     */
    public static final int START = -1;

    /**
     *
     */
    public static final int GOAL = -2;
    private static final Random r = new Random();

    private final int[][] maze;
    private int max;

    /**
     *
     * @param intMaze
     * @param start
     * @param goal
     */
    public ArrayMaze(int[][] intMaze, MazeNode start, MazeNode goal) {
        super(start, goal);
        maze = intMaze;
        for (int[] row : intMaze) {
            for (int i = 0; i < intMaze[0].length; i++) {
                max = (max < row[i]) ? row[i] : max;
            }
        }
    }

    /**
     *
     * @param intMaze
     */
    public ArrayMaze(int[][] intMaze) {
        maze = intMaze;
        for (int x = 0; x < intMaze.length; x++) {
            for (int y = 0; y < intMaze[0].length; y++) {
                if (intMaze[x][y] == START) {
                    start = new MazeNode(x, y);
                } else if (intMaze[x][y] == GOAL) {
                    goal = new MazeNode(x, y);
                }
                max = (max < intMaze[x][y]) ? intMaze[x][y] : max;
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight() {
        return maze.length;
    }

    /**
     *
     * @return
     */
    @Override
    public int getWidth() {
        return maze[0].length;
    }

    /**
     *
     * @param node
     * @return
     */
    @Override
    public List<Node> getAdjacent(Node node) {
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

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public MazeNode getMazeNode(int x, int y) {
        MazeNode s = new MazeNode(x, y);
        s.setCost(maze[x][y]);
        return s;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaxKey() {
        return max;
    }

    /**
     *
     * @param width
     * @param height
     * @param max
     * @return
     */
    public static ArrayMaze randomMaze(int width, int height, int max) {
        int[][] array = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                array[i][j] = r.nextInt(max);
            }
        }
        array[0][0] = START;
        array[width - 1][height - 1] = GOAL;
        ArrayMaze maze = new ArrayMaze(array, new MazeNode(), new MazeNode(width - 1, height - 1));
        maze.max = max - 1;
        return maze;
    }

    /**
     *
     * @param width
     * @param height
     * @return
     */
    public static ArrayMaze emptyMaze(int width, int height) {
        int[][] array = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                array[i][j] = 1;
            }
        }
        array[0][0] = START;
        array[width-1][height - 1] = GOAL;
        ArrayMaze maze = new ArrayMaze(array, new MazeNode(0,0), new MazeNode(width-1, height - 1));
        maze.max = 1;
        return maze;
    }
}
