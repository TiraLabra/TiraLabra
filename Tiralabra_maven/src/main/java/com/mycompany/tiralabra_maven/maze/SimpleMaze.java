package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.State;

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
        List<State> list = new List<>();

        if (x > 0) {
            list.insertLast(new State(x - 1, y, s));
        }
        if (y > 0) {
            list.insertLast(new State(x, y - 1, s));
        }
        if (x < width - 1) {
            list.insertLast(new State(x + 1, y, s));
        }
        if (y < height - 1) {
            list.insertLast(new State(x, y + 1, s));
        }
        expanded += 1;
        return list;
    }

    @Override
    public State getState(int x, int y) {
        return new State(x, y);
    }

}
