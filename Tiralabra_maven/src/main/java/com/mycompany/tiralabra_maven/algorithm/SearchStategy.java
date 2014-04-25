package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.data.Maze;
import com.mycompany.tiralabra_maven.data.Path;
import com.mycompany.tiralabra_maven.data.State;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class SearchStategy {

    protected final Maze maze;

    SearchStategy(Maze maze) {
        this.maze = maze;
    }

    public Path findOptimalPath() {
        State state = getOptimalPath();
        return new Path(maze, state);
    }

    private State getOptimalPath() {

        PriorityQueue<State> open = new PriorityQueue<>();
        Set<State> closed = new HashSet<>();
        open.add(maze.getStartState());

        while (!open.isEmpty()) {
            State current = open.poll();

            if (maze.isGoalState(current)) {
                return current;
            }

            closed.add(current);

            for (State successor : maze.getSuccessors(current)) {
                int cost = current.getCost() +  calculateCost(successor);
                /*if (open.contains(successor) && cost < successor.getCost()) {
                    open.remove(successor);
                }*/
                if (!open.contains(successor) && !closed.contains(successor)) {
                    successor.setCost(cost);
                    successor.setRank(heuristicValue(successor));
                    open.add(successor);
                }
            }
        }
        return null;
    }

    public abstract int calculateCost(State state);
    
    public abstract int heuristicValue(State state);
}
