package com.mycompany.tiralabra_maven.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.maze.Maze;
import com.mycompany.tiralabra_maven.maze.MazeNode;

public class AStarSearchTest {

    int S = ArrayMaze.START;
    int G = ArrayMaze.GOAL;
    AStarSearch searcher;
    private Maze maze;

    private void setUp(int[][] array) {
        maze = new ArrayMaze(array);
        searcher = new AStarSearch(maze, maze.getHeuristic());
    }

    @Test
    public void testSimple() {
        int[][] array = new int[][]{
            new int[]{S, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, G}
        };
        setUp(array);
        List<Node> path = searcher.findOptimalPath();
        assertEquals(5, path.size());
    }

    @Test
    public void testCorrectPath() {
        int[][] array = new int[][]{
            new int[]{S, 1, 1},
            new int[]{2, 2, 1},
            new int[]{2, 2, G}
        };
        setUp(array);
        List<Node> path = searcher.findOptimalPath();
        Node[] correctPath = new Node[]{
            new MazeNode(), new MazeNode(0, 1), new MazeNode(0, 2), new MazeNode(1, 2),
            new MazeNode(2, 2)};
        assertEquals(5, path.size());
        testEquals(correctPath, path);
    }

    @Test
    public void testTrickyPath() {
        int[][] array = new int[][]{
            new int[]{S, 0, 0, 0, 0},
            new int[]{1, 0, 1, 1, 1},
            new int[]{1, 3, 1, 2, 2},
            new int[]{1, 3, 1, 1, 1},
            new int[]{1, 1, 1, 5, G}
        };
        setUp(array);
        List<Node> path = searcher.findOptimalPath();
        Node[] correctPath = new Node[]{
            new MazeNode(0, 0), new MazeNode(1, 0), new MazeNode(2, 0),
            new MazeNode(3, 0), new MazeNode(4, 0), new MazeNode(4, 1),
            new MazeNode(4, 2), new MazeNode(3, 2), new MazeNode(3, 3),
            new MazeNode(3, 4), new MazeNode(4, 4)};
        assertEquals(11, path.size());
        testEquals(correctPath, path);
    }

    private void testEquals(Node[] correctPath, List<Node> path) {
        for (Node state : correctPath) {
            assertTrue(path.contains(state));
        }
    }

}
