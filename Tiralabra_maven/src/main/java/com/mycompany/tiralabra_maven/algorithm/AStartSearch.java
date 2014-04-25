package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.data.Maze;
import com.mycompany.tiralabra_maven.data.State;

public class AStartSearch extends SearchStategy {

    public AStartSearch(Maze maze) {
        super(maze);
    }
    
    @Override
    public int calculateCost(State state) {
        return maze.movementCost(state);
    }

    @Override
    public int heuristicValue(State state) {
        return maze.distanceToGoal(state);
    }
    
}
