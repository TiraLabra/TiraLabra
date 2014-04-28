package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.algorithm.Graph;
import com.mycompany.tiralabra_maven.algorithm.Heuristic;

/**
 * A maze object
 * @author Yessergire Mohamed
 */
public interface Maze extends Graph, Heuristic {

    /**
     *
     * @return
     */
    int getHeight();

    /**
     *
     * @return
     */
    int getWidth();

    /**
     *
     * @param x
     * @param y
     * @return
     */
    MazeNode getMazeNode(int x, int y);

    /**
     *
     * @param from
     * @param to
     * @return
     */
    int distance(MazeNode from, MazeNode to);

    /**
     *
     * @param node
     * @return
     */
    int distanceFromStart(MazeNode node);

    /**
     *
     * @param node
     * @return
     */
    int distanceToGoal(MazeNode node);

    /**
     *
     * @param node
     * @return
     */
    int movementCost(MazeNode node);

    /**
     *
     * @return
     */
    public int getMaxKey();
    
    /**
     * 
     * @return 
     */
    MazeNode getStartNode();
    
    /**
     * 
     * @return 
     */
    MazeNode getGoalNode();
}
