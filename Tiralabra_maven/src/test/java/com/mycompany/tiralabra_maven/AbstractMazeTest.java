package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.data.AbstractMaze;
import com.mycompany.tiralabra_maven.data.State;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractMazeTest {

    private AbstractMazeImpl maze;

    @Before
    public void setUp() {
        maze = new AbstractMazeImpl(new State(), new State(1, 2));
    }

    @Test
    public void testgetStartState() {
        assertEquals(maze.getStartState(), new State());
        assertNotSame(maze.getStartState(), new State(1, 2));
    }

    @Test
    public void testIsGoal() {
        assertFalse(maze.isGoalState(new State(1, 1)));
        assertTrue(maze.isGoalState(new State(1, 2)));
    }
    
    public class AbstractMazeImpl extends AbstractMaze {

        public AbstractMazeImpl(State start, State goal) {
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
        public List<State> getSuccessors(State s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public State getState(int x, int y) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String toString(LinkedList<State> states) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
