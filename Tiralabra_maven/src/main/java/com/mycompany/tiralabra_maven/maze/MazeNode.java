package com.mycompany.tiralabra_maven.maze;

import com.mycompany.tiralabra_maven.algorithm.Node;
import com.mycompany.tiralabra_maven.algorithm.State;

public class MazeNode extends Node {

    public static class Position implements State {

        public final int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            return x == ((Position) o).x && y == ((Position) o).y;
        }

    }

    public MazeNode(int x, int y, Node parent) {
        setState(new Position(x, y));
        setParent(parent);
        setCost(1);
    }

    public MazeNode(int x, int y) {
        this(x, y, null);
    }

    public MazeNode() {
        this(0, 0, null);
    }

    public int getX() {
        return ((Position) state).x;
    }

    public int getY() {
        return ((Position) state).y;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + "){" + getCost() + "}";
    }

    public void setState(Position state) {
        super.setState(state);
    }

    public Position getState() {
        return (Position) super.getState();
    }

}
