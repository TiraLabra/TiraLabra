/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.structures;

import Hike.graph.Edge;

/**
 * Used by LinkyList to link nodes in a list.
 *
 */
public class LinkElement {

    public Edge edge;
    public LinkElement next;


    LinkElement(Edge edge) {
        this.edge = edge;
    }

    void setNext(LinkElement old) {
        this.next = old;
    }

    public Edge getEdge() {
        return this.edge;
    }
}
