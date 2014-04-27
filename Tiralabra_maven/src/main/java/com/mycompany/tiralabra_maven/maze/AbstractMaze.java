package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.algorithm.Heuristic;
import com.mycompany.tiralabra_maven.algorithm.Node;

abstract public class AbstractMaze implements Maze {

    protected MazeNode start;
    protected MazeNode goal;
    protected int expanded;

    public AbstractMaze(MazeNode start, MazeNode goal) {
        this.start = start;
        this.goal = goal;
    }

    public AbstractMaze() {
    }

    @Override
    public boolean isGoalNode(Node g) {
        return goal.equals(g);
    }

    @Override
    public boolean isGoalNode(int x, int y) {
        return isGoalNode(getMazeNode(x, y));
    }

    @Override
    public Node getStartNode() {
        return start;
    }

    @Override
    public int distance(MazeNode from, MazeNode to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    @Override
    public int distanceFromStart(MazeNode from) {
        return distance(from, start);
    }

    @Override
    public int distanceToGoal(MazeNode to) {
        return distance(to, goal);
    }

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

    @Override
    public Heuristic getHeuristic() {
        return new Heuristic() {
            public int value(Node node) {
                return distanceToGoal((MazeNode)node);
            }
        };
    }

}
