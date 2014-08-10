package com.mycompany.tiralabra_maven;

/**
 * Trees branches and leaves. Has character symbol, its weight, and left and
 * right nodes.
 */
public final class Node extends AbstractNode {

    private final char symbol;
    private final int weight;
    private final Node left;
    private final Node right;

    /**
     * Create a new node.
     *
     * @param symbol The character symbol of the node.
     * @param weight The occurrence of the character symbol.
     * @param left The node left of this.
     * @param right The node right of this.
     */
    public Node(final char symbol, final int weight, final Node left, final Node right) {
        this.symbol = symbol;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public char getSymbol() {
        return symbol;
    }

    /**
     * Is the node a leaf.
     *
     * @return Is the node a leaf.
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Node to the left.
     *
     * @return Node to the left or null is there isn't one.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Node to the right.
     *
     * @return Node to the right or null is there isn't one.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Get the height of the node.
     *
     * @return The height of the node.
     */
    public int getHeight() {
        return recursiveGetHeight(getLeft()) + 1;
    }

    private int recursiveGetHeight(final Node node) {
        if (!isLeaf()) {
            return recursiveGetHeight(getLeft()) + 1;
        } else {
            return 1;
        }
    }

    /**
     * Get the weight of the node.
     *
     * @return The weight of the node.
     */
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
