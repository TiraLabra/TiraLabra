package com.mycompany.tiralabra_maven;

import java.io.Serializable;

/**
 * Remnant class.
 */
public abstract class AbstractNode implements Serializable, Comparable<AbstractNode> {

    private AbstractNode parent;

    public AbstractNode getParent() {
        return parent;
    }

    public void setParent(final AbstractNode parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(final AbstractNode o) {
        return getWeight() - o.getWeight();
    }

    public abstract int getWeight();
}
