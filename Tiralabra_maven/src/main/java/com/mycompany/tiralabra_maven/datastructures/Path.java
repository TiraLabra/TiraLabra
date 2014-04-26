package com.mycompany.tiralabra_maven.datastructures;

import com.mycompany.tiralabra_maven.maze.Maze;

public class Path {

    private final List states;
    private final Maze maze;

    public Path(Maze maze, State state) {
        this.maze = maze;
        states = new List<>();
        while (state != null) {
            states.insertLast(state);
            state = state.getParent();
        }
    }

    @Override
    public String toString() {
        return maze.toString(getStates());
    }

    public List<State> getStates() {
        return states;
    }

}
