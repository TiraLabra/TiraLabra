/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Graph;

/**
 *
 * @author petri
 */
public class Edge {

    private Node parent;
    private Node child;
    private double cost;

    public Edge(Node p, Node c, double w) {
        this.parent = p;
        this.child = c;
        this.cost = w;

    }

    public Node getParent() {
        return parent;
    }

    public Node getChild() {
        return child;
    }

    public double getCost() {
        return cost;
    }
}
