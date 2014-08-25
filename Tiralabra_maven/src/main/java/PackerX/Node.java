package PackerX;

import java.io.Serializable;

/**
 * Trees branches and leaves. Has character symbol, its weight, and left and
 * right nodes.
 */
public final class Node implements Serializable, Comparable<Node> {

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

    /**
     * The character attached to the node.
     *
     * @return The character attached to the node.
     */
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
     * Get the weight of the node.
     *
     * @return The weight of the node.
     */
    public int getWeight() {
        if (isLeaf()) {
            return weight;
        }
        return left.getWeight() + right.getWeight();
    }

    @Override
    public int compareTo(final Node that) {
        return this.getWeight() - that.getWeight();
    }
}
