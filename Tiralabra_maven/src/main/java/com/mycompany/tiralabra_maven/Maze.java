package com.mycompany.tiralabra_maven;

import java.util.LinkedList;
import java.util.List;

public interface Maze {

    char START_CHAR = 'S';
    char GOAL_CHAR = 'G';
    char PATH_CHAR = '¤';
    char CELL_CHAR = '.';
    char WALL_CHAR = '#';

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

    String toString(LinkedList<State> states);

    public int movementCost(State successor);

}
