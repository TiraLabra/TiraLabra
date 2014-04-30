package com.mycompany.tiralabra_maven.maze;

import static org.junit.Assert.*;
import org.junit.Test;

public abstract class AbstractMazeTest {

    protected AbstractMaze maze;


    @Test
    public void testgetStartState() {
        assertEquals(maze.getStartNode(), new MazeNode());
        assertNotSame(maze.getStartNode(), new MazeNode(1, 2));
    }

}
