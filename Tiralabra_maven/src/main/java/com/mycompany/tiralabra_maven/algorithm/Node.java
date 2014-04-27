package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.algorithm.State;
import com.mycompany.tiralabra_maven.datastructures.Valuable;
import java.util.Objects;

public class Node implements Valuable {

    protected Node parent;
    protected int cost;
    protected int rank;
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

    public int key() {
        return cost + rank;
    }

    @Override
    public boolean equals(Object obj) {
        return state.equals(((Node) obj).state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(state);
    }
}
