package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.State;

public interface Maze {

    int getHeight();

    int getWidth();

    State getStartState();

    boolean isGoalState(State g);

    boolean isGoalState(int x, int y);

    State getState(int x, int y);

    List<State> getSuccessors(State s);

    int distance(State from, State to);

    int distanceFromStart(State state);

    int distanceToGoal(State state);

    int getExpandedStates();

    public int movementCost(State successor);

}
