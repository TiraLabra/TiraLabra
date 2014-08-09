package com.mycompany.tiralabra_maven;

import java.io.Serializable;

public abstract class AbstractNode implements Serializable {

    private AbstractNode parent;

    public AbstractNode getParent() {
        return parent;
    }

    public void setParent(final AbstractNode parent) {
        this.parent = parent;
    }

    public abstract int getWeight();
}
