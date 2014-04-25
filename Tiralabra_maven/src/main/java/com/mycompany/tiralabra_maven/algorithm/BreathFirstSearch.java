package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.Maze;
import com.mycompany.tiralabra_maven.State;

class BreathFirstSearch extends SearchStategy {

    public BreathFirstSearch(Maze maze) {
        super(maze);
    }

    @Override
    public int heuristicValue(State state) {
        return 0;
    }

    @Override
    public int calculateCost(State state) {
        return 1;
    }

}
