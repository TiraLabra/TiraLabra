package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;

public class SimpleMaze extends AbstractMaze {

    private final int width, height;

    public SimpleMaze(int width, int height, MazeNode start, MazeNode goal) {
        super(start, goal);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<Node> getSuccessors(Node node) {
        MazeNode n = (MazeNode) node;
        int x = n.getX();
        int y = n.getY();
        List<Node> list = new List<>();

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
        expanded += 1;
        return list;
    }

    @Override
    public MazeNode getMazeNode(int x, int y) {
        return new MazeNode(x, y);
    }

    @Override
    public int getMaxKey() {
        return 1;
    }

}
