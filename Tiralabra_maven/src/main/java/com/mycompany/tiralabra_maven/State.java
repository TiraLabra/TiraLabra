package com.mycompany.tiralabra_maven;

public class State implements Comparable<State> {

    private State parent;
    private int cost;
    private final int x, y;

    public State(int x, int y, State parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        cost = 1;
    }

    public State(int x, int y) {
        this(x, y, null);
    }

    public State() {
        this(0, 0, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public State getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        State state = (State) obj;
        return x == state.x && y == state.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(State s) {
        return cost - s.cost;
    }

}
