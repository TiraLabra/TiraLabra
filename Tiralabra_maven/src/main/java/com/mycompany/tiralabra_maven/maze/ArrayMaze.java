package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.State;

public class ArrayMaze extends AbstractMaze {

    public static final int WALL = 0;
    public static final int START = -1;
    public static final int GOAL = -2;

    private final int[][] maze;

    private ArrayMaze(int[][] maze, State start, State goal) {
        super(start, goal);
        this.maze = maze;
    }

    public static Maze create(int[][] maze) {
        State start = null;
        State goal = null;
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                if (maze[x][y] == START) {
                    start = new State(x,y);
                } else if (maze[x][y] == GOAL) {
                    goal = new State(x,y);
                }
            }
        }
        return new ArrayMaze(maze, start, goal);
    }

    @Override
    public int getHeight() {
        return maze.length;
    }

    @Override
    public int getWidth() {
        return maze[0].length;
    }

    @Override
    public List<State> getSuccessors(State s) {
        int x = s.getX();
        int y = s.getY();
        List<State> list = new List<>();

        if (x > 0) {
            list.insertLast(getState(x - 1, y).setParent(s));
        }
        if (y > 0) {
            list.insertLast(getState(x, y - 1).setParent(s));
        }
        if (x < getHeight() - 1) {
            list.insertLast(getState(x + 1, y).setParent(s));
        }
        if (y < getWidth() - 1) {
            list.insertLast(getState(x, y + 1).setParent(s));
        }

        List<State> copy = new List<>();
        for (State state : list) {
            if (state.getCost() != 0)
                copy.insertLast(state);
        }
        expanded += 1;
        return copy;
    }

    @Override
    public State getState(int x, int y) {
        State s = new State(x, y);
        s.setCost(maze[x][y]);
        return s;
    }

}
