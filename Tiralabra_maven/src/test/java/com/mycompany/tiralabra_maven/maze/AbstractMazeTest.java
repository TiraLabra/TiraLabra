package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.algorithm.Node;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AbstractMazeTest {

    private AbstractMazeImpl maze;

    @Before
    public void setUp() {
        maze = new AbstractMazeImpl(new MazeNode(), new MazeNode(1, 2));
    }

    @Test
    public void testgetStartState() {
        assertEquals(maze.getStartNode(), new MazeNode());
        assertNotSame(maze.getStartNode(), new MazeNode(1, 2));
    }

    @Test
    public void testIsGoal() {
        assertFalse(maze.isGoalNode(new MazeNode(1, 1)));
        assertTrue(maze.isGoalNode(new MazeNode(1, 2)));
    }

    public class AbstractMazeImpl extends AbstractMaze {

        public AbstractMazeImpl(MazeNode start, MazeNode goal) {
            super(start, goal);
        }

        @Override
        public int getHeight() {
            return 10;
        }

        @Override
        public int getWidth() {
            return 10;
        }

        @Override
        public List<Node> getSuccessors(Node s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public MazeNode getMazeNode(int x, int y) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getMaxKey() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
