package com.mycompany.tiralabra_maven;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author yessergire
 */
class Searcher {

    private final Maze maze;

    Searcher(Maze maze) {
        this.maze = maze;
    }

    public List<State> findOptimalPath() {
        State result = getOptimalPath();
        LinkedList<State> list = new LinkedList<>();
        while (result != null) {
            list.addFirst(result);
            result = result.getParent();
        }
        return list;
    }

    private State getOptimalPath() {

        PriorityQueue<State> open = new PriorityQueue<>();
        Set<State> closed = new HashSet<>();
        open.add(maze.getStartState());

        while (!open.isEmpty()) {
            State current = open.poll();

            if (maze.isGoal(current)) {
                return current;
            }

            closed.add(current);

            for (State successor : maze.getSuccessors(current)) {
                if (!open.contains(successor) && !closed.contains(successor)) {
                    successor.setCost(1 + successor.getParent().getCost());
                    open.add(successor);
                }
            }
        }
        return null;
    }

}
