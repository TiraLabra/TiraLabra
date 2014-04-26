package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.State;

abstract public class AbstractMaze implements Maze {

    protected final State start;
    protected final State goal;
    protected int expanded;

    public AbstractMaze(State start, State goal) {
        this.start = start;
        this.goal = goal;
    }

    @Override
    public boolean isGoalState(State g) {
        return goal.equals(g);
    }

    @Override
    public boolean isGoalState(int x, int y) {
        return isGoalState(getState(x, y));
    }

    @Override
    public State getStartState() {
        return start;
    }

    @Override
    public int getExpandedStates() {
        return expanded;
    }

    @Override
    public int distance(State from, State to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    @Override
    public int distanceFromStart(State from) {
        return distance(from, start);
    }

    @Override
    public int distanceToGoal(State to) {
        return distance(to, goal);
    }

    @Override
    public int movementCost(State state) {
        return getState(state.getX(), state.getY()).getCost();
    }
}
