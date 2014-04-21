package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private final int width, height;
    private final State start;
    private final State goal;

    public Maze(int width, int height, State start, State goal) {
        this.width = width;
        this.height = height;
        this.start = start;
        this.goal = goal;
    }

    public boolean isGoal(State g) {
        return goal.equals(g);
    }

    public State getStartState() {
        return start;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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
        return list;
    }
}
