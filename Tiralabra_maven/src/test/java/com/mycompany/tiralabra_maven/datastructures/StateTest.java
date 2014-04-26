package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    /**
     * Test of getPosition method, of class State.
     */
    @Test
    public void testAccessors() {
        State parent = new State(0, 1);
        State state = new State(0, 2, parent);
        assertEquals(0, state.getX());
        assertEquals(2, state.getY());
        assertEquals(parent, state.getParent());
    }

    /**
     * Test of equals method, of class State.
     */
    @Test
    public void testEquals() {
        assertEquals(new State(), new State());
        assertNotSame(new State(), new State(0, 1));
    }

    /**
     * Test of equals method, of class State.
     */
    @Test
    public void testHashcode() {
        State s = new State();
        s.setCost(10);
        s.setRank(5);
        assertEquals(15, s.hashCode());
    }

}
