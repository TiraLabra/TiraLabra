package com.mycompany.tiralabra_maven.data;

import com.mycompany.tiralabra_maven.data.State;
import java.util.LinkedList;

public class Path {

    private final LinkedList<State> states;
    private final Maze maze;

    public Path(Maze maze, State state) {
        this.maze = maze;
        states = new LinkedList<>();
        while (state != null) {
            states.addFirst(state);
            state = state.getParent();
        }
    }

    @Override
    public String toString() {
        return maze.toString(getStates());
    }

    public LinkedList<State> getStates() {
        return states;
    }

}
