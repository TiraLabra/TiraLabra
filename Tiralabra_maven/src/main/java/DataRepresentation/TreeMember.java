package DataRepresentation;

import java.io.Serializable;

/**
 * Abstarct TreeMember, has right and left members. All subclasses must provide
 * a way to calculate member weight and define are they leafs.
 */
public abstract class TreeMember implements Serializable, Comparable<TreeMember> {

    private final TreeMember left;
    private final TreeMember right;

    /**
     * Create a new TreeMember.
     *
     * @param left The TreeMember left of this.
     * @param right The TreeMember right of this.
     */
    protected TreeMember(final TreeMember left, final TreeMember right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Node to the left.
     *
     * @return Node to the left or null is there isn't one.
     */
    public final TreeMember getLeft() {
        return left;
    }

    /**
     * Node to the right.
     *
     * @return Node to the right or null is there isn't one.
     */
    public final TreeMember getRight() {
        return right;
    }

    /**
     * Compare TreeMember to another.
     *
     * @param that The other member.
     * @return The comparision of the weights.
     */
    @Override
    public final int compareTo(final TreeMember that) {
        return that.getWeight() - this.getWeight();
    }

    /**
     * Get the weight of the TreeMember.
     *
     * @return The weight of the TreeMember.
     */
    public abstract int getWeight();

    /**
     * Is the TreeMember a leaf.
     *
     * @return Is the TreeMember a leaf.
     */
    public abstract boolean isLeaf();
}
