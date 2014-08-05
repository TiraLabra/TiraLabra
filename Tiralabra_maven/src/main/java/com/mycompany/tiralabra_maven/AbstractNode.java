package com.mycompany.tiralabra_maven;

public abstract class AbstractNode {

    private AbstractNode parent;

    public AbstractNode getParent() {
        return parent;
    }

    public void setParent(final AbstractNode parent) {
        this.parent = parent;
    }

    public abstract int getWeight();
}
