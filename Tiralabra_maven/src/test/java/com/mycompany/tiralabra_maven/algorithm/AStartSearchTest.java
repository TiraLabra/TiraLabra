package com.mycompany.tiralabra_maven.algorithm;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import com.mycompany.tiralabra_maven.datastructures.State;
import java.util.LinkedList;

public class AStartSearchTest {

    int S = ArrayMaze.START;
    int G = ArrayMaze.GOAL;
    AStartSearch searcher;

    @Test
    public void testSimple() {
        int[][] array = new int[][]{
            new int[]{S, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, G}
        };
        searcher = new AStartSearch(ArrayMaze.create(array));
        List<State> path = searcher.findOptimalPath().getStates();
        assertEquals(5, path.size());
    }

    @Test
    public void testCorrectPath() {
        int[][] array = new int[][]{
            new int[]{S, 1, 1},
            new int[]{2, 2, 1},
            new int[]{2, 2, G}
        };
        searcher = new AStartSearch(ArrayMaze.create(array));
        List<State> path = searcher.findOptimalPath().getStates();
        State[] correctPath = new State[]{
            new State(), new State(0, 1), new State(0, 2), new State(1, 2),
            new State(2, 2)};
        assertEquals(5, path.size());
        assertArrayEquals(correctPath, path.toArray());
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
        searcher = new AStartSearch(ArrayMaze.create(array));
        LinkedList<State> path = searcher.findOptimalPath().getStates();
        State[] correctPath = new State[]{
            new State(0, 0), new State(1, 0), new State(2, 0),
            new State(3, 0), new State(4, 0), new State(4, 1),
            new State(4, 2), new State(3, 2), new State(3, 3),
            new State(3, 4), new State(4, 4)};
        assertEquals(11, path.size());
        assertArrayEquals(correctPath, path.toArray());
    }

}
