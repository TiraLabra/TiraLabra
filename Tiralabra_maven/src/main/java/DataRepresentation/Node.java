package DataRepresentation;

/**
 * Trees branches and leaves. Has character symbol, its weight, and left and
 * right nodes.
 */
public final class Node extends TreeMember {

    private final char symbol;
    private final int weight;

    /**
     * Create a new node.
     *
     * @param symbol The character symbol of the node.
     * @param weight The occurrence of the character symbol.
     * @param left The node left of this.
     * @param right The node right of this.
     */
    public Node(final char symbol, final int weight, final TreeMember left, final TreeMember right) {
        super(left, right);
        this.symbol = symbol;
        this.weight = weight;
    }

    /**
     * The character attached to the node.
     *
     * @return The character attached to the node.
     */
    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
