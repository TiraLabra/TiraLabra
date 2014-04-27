package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.algorithm.AStarSearch;
import com.mycompany.tiralabra_maven.algorithm.Node;
import com.mycompany.tiralabra_maven.algorithm.Search;
import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.gui.GraphDrawer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class ArrayMazeDrawer extends GraphDrawer {

    private ArrayMaze maze;
    private float max;
    private Search search;
    private JLabel[][] cells;

    public ArrayMazeDrawer(ArrayMaze maze) {
        setup(maze);
    }

    private void setup(ArrayMaze maze) {
        this.maze = maze;
        search = new AStarSearch(maze, maze.getHeuristic());
        max = maze.getMaxKey();
        GridLayout l = new GridLayout(maze.getHeight(), maze.getWidth());
        setLayout(l);
        cells = new JLabel[maze.getHeight()][maze.getWidth()];
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                MazeNode node = maze.getMazeNode(i, j);
                cells[i][j] = new JLabel();
                setBGColor(node, i, j);
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                cells[i][j].setPreferredSize(new Dimension(50, 50));
                cells[i][j].setOpaque(true);
                add(cells[i][j]);
            }
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                MazeNode node = maze.getMazeNode(i, j);
                setBGColor(node, i, j);
            }
        }
    }

    @Override
    public void drawPath() {
        List<Node> path = search.findOptimalPath();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                MazeNode node = maze.getMazeNode(i, j);
                if (path.contains(node))
                    cells[i][j].setBackground(Color.green);
            }
        }
        updateUI();
    }

    private void setBGColor(Node node, int i, int j) {
        float key = node.getCost() / max;
        if (node.getCost() == ArrayMaze.START) {
            cells[i][j].setBackground(Color.yellow);
        } else if (node.getCost() == ArrayMaze.GOAL) {
            cells[i][j].setBackground(Color.green);
        } else if (node.getCost() == ArrayMaze.WALL) {
            cells[i][j].setBackground(Color.black);
        } else {
            cells[i][j].setBackground(new Color(.3f, .3f, 1, key));
        }
    }

    @Override
    public void drawRandom() {
        removeAll();
        setup(ArrayMaze.randomMaze(25, 25, 5));
    }

}
