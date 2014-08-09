package com.mycompany.tiralabra_maven;

public final class WeightedNode extends AbstractNode {

    private final char symbol;
    private final int weight;
    private final WeightedNode left;
    private final WeightedNode right;

    public WeightedNode(final char symbol, final int weight, final WeightedNode left, final WeightedNode right) {
        this.symbol = symbol;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public WeightedNode getLeft() {
        return left;
    }

    public WeightedNode getRight() {
        return right;
    }

    public int getHeight() {
        return recursiveGetHeight(getLeft()) + 1;
    }

    private int recursiveGetHeight(final WeightedNode node) {
        if (!isLeaf()) {
            return recursiveGetHeight(getLeft()) + 1;
        } else {
            return 1;
        }
    }

    @Override
    public int getWeight() {
        if (isLeaf()) {
            return weight;
        }
        int w = 0;
        if (left != null) {
            w += left.getWeight();
        }
        if (right != null) {
            w += right.getWeight();
        }
        return w;
    }
}
