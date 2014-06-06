/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Structures;

import Hike.Graph.Node;
import java.util.Iterator;

/**
 *  Used by LinkyList to link nodes in a list.
 *
 */
public class LinkElement {

    public Node node;
    public LinkElement next;

    LinkElement(Node node) {
        this.node = node;
    }

    void setNext(LinkElement old) {
        this.next = old;
    }

    public Node getNode() {
       return this.node;
    }
}
