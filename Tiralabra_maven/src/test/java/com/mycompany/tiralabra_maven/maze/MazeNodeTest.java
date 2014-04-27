package com.mycompany.tiralabra_maven.maze;

import org.junit.Test;

import static org.junit.Assert.*;

public class MazeNodeTest {

    @Test
    public void testAccessors() {
        MazeNode parent = new MazeNode(0, 1);
        MazeNode state = new MazeNode(0, 2, parent);
        assertEquals(0, state.getX());
        assertEquals(2, state.getY());
        assertEquals(parent, state.getParent());
    }

    @Test
    public void testEquals() {
        assertEquals(new MazeNode(), new MazeNode());
        assertNotSame(new MazeNode(), new MazeNode(0, 1));
    }

}
