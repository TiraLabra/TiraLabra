package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.Valuable;
import java.util.Objects;

/** Node of the graph
 *
 * @author Yessergire Mohamed
 */
public class Node implements Valuable {

    /**
     * The parent of this node.
     */
    protected Node parent;

    /**
     * The accumulated cost of this node.
     */
    protected int cost;

    /**
     * The rank of this node.
     */
    protected int rank;

    /**
     * The state of this node.
     */
    protected State state;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int key() {
        return cost + rank;
    }

    /**
     * This node equals object iff their states are equal.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        return state.equals(((Node) obj).state);
    }

    /**
     * 
     * @return Objects.hashCode(state)
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(state);
    }
}
