package com.mycompany.tiralabra_maven;

public final class WeightedNode extends AbstractNode {

    private final char symbol;
    private final int weight;

    public WeightedNode(final WeightedNode oldNode) {
        this(oldNode.symbol, oldNode.weight + 1);
    }

    public WeightedNode(final char symbol) {
        this(symbol, 0);
    }

    private WeightedNode(final char symbol, final int weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
