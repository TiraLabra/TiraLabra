package com.mycompany.tiralabra_maven.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleMaze extends AbstractMaze {

    private final int width, height;

    public SimpleMaze(int width, int height, State start, State goal) {
        super(start, goal);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<State> getSuccessors(State s) {
        int x = s.getX();
        int y = s.getY();
        ArrayList<State> list = new ArrayList<>();

        if (x > 0) {
            list.add(new State(x - 1, y, s));
        }
        if (y > 0) {
            list.add(new State(x, y - 1, s));
        }
        if (x < width - 1) {
            list.add(new State(x + 1, y, s));
        }
        if (y < height - 1) {
            list.add(new State(x, y + 1, s));
        }
        expanded += 1;
        return list;
    }

    @Override
    public State getState(int x, int y) {
        return new State(x, y);
    }

    @Override
    public String toString(LinkedList<State> states) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < getHeight(); x++) {
            for (int y = 0; y < getWidth(); y++) {
                if (getStartState().equals(getState(x, y))) {
                    sb.append(Maze.START_CHAR);
                } else if (isGoalState(x, y)) {
                    sb.append(Maze.GOAL_CHAR);
                } else if (states.contains(getState(x, y))) {
                    sb.append(Maze.PATH_CHAR);
                } else if (getState(x, y).getCost() > 0) {
                    sb.append(Maze.CELL_CHAR);
                } else {
                    sb.append(Maze.WALL_CHAR);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
