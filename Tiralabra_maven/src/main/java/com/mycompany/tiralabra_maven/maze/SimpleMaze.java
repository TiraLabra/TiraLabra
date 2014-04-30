package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;

/**
 * A maze whose values are uniformally weighted
 * @author  Yessergire Mohamed
 */
public class SimpleMaze extends AbstractMaze {

    private final int width, height;

    /**
     *
     * @param width
     * @param height
     * @param start
     * @param goal
     */
    public SimpleMaze(int width, int height, MazeNode start, MazeNode goal) {
        super(start, goal);
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @param width
     * @param height
     */
    public SimpleMaze(int width, int height) {
        super(new MazeNode(), new MazeNode(width-1, height-1));
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @return
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param node
     * @return
     */
    @Override
    public List<Node> getAdjacent(Node node) {
        MazeNode n = (MazeNode) node;
        int x = n.getX();
        int y = n.getY();
        List<Node> list = new List<Node>();

        if (x > 0) {
            list.insertLast(new MazeNode(x - 1, y, n));
        }
        if (y > 0) {
            list.insertLast(new MazeNode(x, y - 1, n));
        }
        if (x < width - 1) {
            list.insertLast(new MazeNode(x + 1, y, n));
        }
        if (y < height - 1) {
            list.insertLast(new MazeNode(x, y + 1, n));
        }
        return list;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public MazeNode getMazeNode(int x, int y) {
        return new MazeNode(x, y);
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaxKey() {
        return 1;
    }

}
