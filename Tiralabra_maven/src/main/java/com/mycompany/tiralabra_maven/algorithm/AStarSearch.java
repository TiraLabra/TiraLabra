package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.Set;
import com.mycompany.tiralabra_maven.datastructures.State;
import com.mycompany.tiralabra_maven.maze.Maze;
import java.util.PriorityQueue;

public class AStarSearch {

    protected final Maze maze;

    public AStarSearch(Maze maze) {
        this.maze = maze;
    }

    public List<State> findOptimalPath() {
        List<State> states = new List<>();
        State state = getOptimalPath();
        while (state != null) {
            states.insertLast(state);
            state = state.getParent();
        }
        return states;
    }

    private State getOptimalPath() {

        PriorityQueue<State> open = new PriorityQueue<>();
        Set<State> closed = new Set<>();
        open.add(maze.getStartState());

        while (!open.isEmpty()) {
            State current = open.poll();

            if (maze.isGoalState(current)) {
                return current;
            }

            closed.add(current);

            for (State successor : maze.getSuccessors(current)) {
                int cost = current.getCost() +  maze.movementCost(successor);
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

    public int heuristicValue(State state) {
        return maze.distanceToGoal(state);
    }
    
}
