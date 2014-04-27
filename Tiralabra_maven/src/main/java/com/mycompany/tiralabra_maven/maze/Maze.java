package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.algorithm.Graph;

public interface Maze extends Graph {

    int getHeight();

    int getWidth();

    boolean isGoalNode(int x, int y);

    MazeNode getMazeNode(int x, int y);

    int distance(MazeNode from, MazeNode to);

    int distanceFromStart(MazeNode node);

    int distanceToGoal(MazeNode node);

    int movementCost(MazeNode node);

}
