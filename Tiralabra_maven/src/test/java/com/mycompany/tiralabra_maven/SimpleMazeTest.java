package com.mycompany.tiralabra_maven;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Maze-luokan testit
 *
 * @author Yessergire Mohamed
 */
public class MazeTest {

    Maze maze;

    @Before
    public void setup() {
        maze = new Maze(10, 10, new State(), new State(1, 2));
    }

    /**
     * Testaa Maze-luokan aksessoreita.
     */
    @Test
    public void testAccessors() {
        assertEquals(new State(), maze.getStartState());
        assertEquals(10, maze.getWidth());
        assertEquals(10, maze.getHeight());
    }

    /**
     * Testaa Maze-luokan isGoal-metodia.
     */
    @Test
    public void testIsGoal() {
        assertFalse(maze.isGoal(new State(1, 1)));
        assertTrue(maze.isGoal(new State(1, 2)));
    }

    /**
     * Testaa Maze-luokan getSuccessors-metodia.
     */
    @Test(expected = NullPointerException.class)
    public void testGetSuccessorsNullThrowsException() {
        List<State> successors = maze.getSuccessors(null);
    }

    /**
     * Testaa Maze-luokan getSuccessors-metodia.
     */
    @Test
    public void testGetSuccessors() {
        State state = new State(1, 0);
        List<State> successors = maze.getSuccessors(state);

        assertFalse(successors.isEmpty());
        assertTrue(successors.contains(new State(1, 1)));
        assertEquals(state, successors.get(0).getParent());
    }

    /**
     * Testaa Maze-luokan getSuccessors-metodia.
     */
    @Test
    public void testGetSuccessorsCorners() {
        State[] corners = new State[]{
            new State(),
            new State(0, maze.getHeight() - 1),
            new State(maze.getWidth() - 1, 0),
            new State(maze.getWidth() - 1, maze.getHeight() - 1)
        };
        for (State state : corners) {
            List<State> successors = maze.getSuccessors(state);
            assertEquals(2, successors.size());
        }
    }
}
