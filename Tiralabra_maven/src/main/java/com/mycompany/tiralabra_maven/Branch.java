package com.mycompany.tiralabra_maven;

public final class Branch extends AbstractNode {

    private final AbstractNode left;
    private final AbstractNode right;

    public Branch(final AbstractNode left, final AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int getWeight() {
        return left.getWeight() + right.getWeight();
    }
}
