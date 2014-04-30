package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.algorithm.Heuristic;
import com.mycompany.tiralabra_maven.algorithm.Node;

/**
 *
 * @author yessergire
 */
abstract public class AbstractMaze implements Maze, Heuristic {

    /**
     *
     */
    protected MazeNode start;

    /**
     *
     */
    protected MazeNode goal;

    /**
     *
     */
    protected int expanded;

    /**
     *
     * @param start
     * @param goal
     */
    public AbstractMaze(MazeNode start, MazeNode goal) {
        this.start = start;
        this.goal = goal;
    }

    /**
     *
     */
    public AbstractMaze() {
    }

    /**
     *
     * @return
     */
    @Override
    public MazeNode getStartNode() {
        return start;
    }

    /**
     *
     * @return
     */
    @Override
    public MazeNode getGoalNode() {
        return goal;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @Override
    public int distance(MazeNode from, MazeNode to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    /**
     *
     * @param from
     * @return
     */
    @Override
    public int distanceFromStart(MazeNode from) {
        return distance(from, start);
    }

    /**
     *
     * @param to
     * @return
     */
    @Override
    public int distanceToGoal(MazeNode to) {
        return distance(to, goal);
    }

    /**
     *
     * @param node
     * @return
     */
    @Override
    public int movementCost(MazeNode node) {
        return getMazeNode(node.getX(), node.getY()).getCost();
    }

    /**
     * @param from
     * @param to
     * @return movementCost(to) if to is a successor of from MAX_VALUE else
     */
    @Override
    public int weight(Node from, Node to) {
        if (distance((MazeNode)from, (MazeNode)to) <= 1) {
            return movementCost((MazeNode)to);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 
     * @param node
     * @return 
     */
    public int value(Node node) {
        return distanceToGoal((MazeNode)node);
    }

}
