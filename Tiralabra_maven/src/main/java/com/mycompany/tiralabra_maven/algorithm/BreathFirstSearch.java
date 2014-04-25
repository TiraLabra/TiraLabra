package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.data.Maze;
import com.mycompany.tiralabra_maven.data.State;

public class BreathFirstSearch extends SearchStategy {

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
