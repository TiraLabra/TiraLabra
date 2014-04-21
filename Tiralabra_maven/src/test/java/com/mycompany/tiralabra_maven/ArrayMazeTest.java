package com.mycompany.tiralabra_maven;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayMazeTest {

    private ArrayMaze maze;
    private int[][] array;

    @Before
    public void setUp() {
        array = new int[][]{
            new int[]{1, 0, 0},
            new int[]{1, 0, 0},
            new int[]{1, 1, 1}};
        maze = new ArrayMaze(array, new State(), new State(2, 2));
    }

    /**
     * Test of getHeight method, of class ArrayMaze.
     */
    @Test
    public void testGetHeightAndGetWidth() {
        assertEquals(array.length, maze.getWidth());
        assertEquals(array[0].length, maze.getHeight());
    }

    /**
     * Test of getHeight method, of class ArrayMaze.
     */
    @Test
    public void testGetSuccessors() {
        assertEquals(1, maze.getSuccessors(maze.getStartState()).size());
        assertEquals(2, maze.getSuccessors(new State(1, 0)).size());
        assertEquals(2, maze.getSuccessors(new State(2, 0)).size());
        assertEquals(2, maze.getSuccessors(new State(2, 1)).size());
        assertEquals(1, maze.getSuccessors(new State(2, 2)).size());
    }

}
