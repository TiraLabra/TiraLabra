package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Maze-luokan testit
 */
public class SimpleMazeTest extends AbstractMazeTest{

    @Before
    public void setup() {
        maze = new SimpleMaze(10, 10, new MazeNode(), new MazeNode(1, 2));
    }

    /**
     * Testaa Maze-luokan aksessoreita.
     */
    @Test
    public void testAccessors() {
        assertEquals(10, maze.getWidth());
        assertEquals(10, maze.getHeight());
    }

    /**
     * Testaa Maze-luokan getSuccessors-metodia.
     */
    @Test(expected = NullPointerException.class)
    public void testGetSuccessorsNullThrowsException() {
        maze.getAdjacent(null);
    }

    /**
     * Testaa Maze-luokan getSuccessors-metodia.
     */
    @Test
    public void testGetSuccessors() {
        Node state = new MazeNode(1, 0);
        List<Node> successors = maze.getAdjacent(state);

        assertFalse(successors.isEmpty());
        assertTrue(successors.contains(new MazeNode(1, 1)));
    }

    /**
     * Testaa Maze-luokan getSuccessors-metodia.
     */
    @Test
    public void testGetSuccessorsCorners() {
        Node[] corners = new Node[]{
            new MazeNode(),
            new MazeNode(0, maze.getHeight() - 1),
            new MazeNode(maze.getWidth() - 1, 0),
            new MazeNode(maze.getWidth() - 1, maze.getHeight() - 1)
        };
        for (Node state : corners) {
            List<Node> successors = maze.getAdjacent(state);
            assertEquals(2, successors.size());
        }
    }
}
