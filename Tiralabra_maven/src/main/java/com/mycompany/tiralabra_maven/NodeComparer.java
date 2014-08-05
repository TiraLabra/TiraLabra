package com.mycompany.tiralabra_maven;

import java.util.Comparator;

public final class NodeComparer implements Comparator<AbstractNode> {

    @Override
    public int compare(final AbstractNode o1, final AbstractNode o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
