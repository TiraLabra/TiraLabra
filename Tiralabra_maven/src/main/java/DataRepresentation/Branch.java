package DataRepresentation;

/**
 * Trees branches. Has left and right TreeMembers.
 */
public final class Branch extends TreeMember {

    /**
     * Create a new Branch.
     *
     * @param left The node left of this.
     * @param right The node right of this.
     */
    public Branch(final TreeMember left, final TreeMember right) {
        super(left, right);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public int getWeight() {
        return getLeft().getWeight() + getRight().getWeight();
    }
}
