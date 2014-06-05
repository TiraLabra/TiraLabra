/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Structures;

import Hike.Graph.Node;

/**
 *
 * @author petri
 */
public class HeapElement {

    private Node node;

    public HeapElement(Node n, int index) {
        this.node = n;
        this.node.setHeapIndex(index);
    }

    public Node getNode() {
        return this.node;
    }

    public int getIndex() {
        return this.node.getHeapIndex();
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setIndex(int index) {
        this.node.setHeapIndex(index);
    }
}
