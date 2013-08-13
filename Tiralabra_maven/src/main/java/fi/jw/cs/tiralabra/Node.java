package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * A weighted <code>Node</code> that is going to be used by the <code>BinaryTree</code> class
 * <p/>
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class Node implements Comparable<Node> {
    private String label;
    private String code;
    private int weight;
    private Node parent;
    private Node left;
    private Node right;
    private int leftCount;
    private int rightCount;


    public Node() {
        this("", 0, null, null, null);
    }

    public Node(String label) {
        this(label, 0, null, null, null);
    }

    public Node(String label, int weight) {
        this(label, weight, null, null, null);
    }

    public Node(String label, int weight, Node parent, Node left, Node right) {
        this.label = label;
        this.weight = weight;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.leftCount = 0;
        this.rightCount = 0;
    }


    public boolean isLeaf() {
        return left == null && right == null;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getChildCount() {
        return leftCount + rightCount;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
        left.setParent(this);
        this.leftCount = 1 + left.getChildCount();
        if (parent != null) {
            parent.updateChildCount(this, getChildCount());
        }
    }

    public void updateChildCount(Node child, int total) {
        if (child.equals(left)) {
            leftCount = 1 + total;
        } else {
            rightCount = 1 + total;
        }

        if (parent != null) {
            parent.updateChildCount(this, getChildCount());
        }
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
        right.setParent(this);
        this.rightCount = 1 + right.getChildCount();
        if (parent != null) {
            parent.updateChildCount(this, getChildCount());
        }
    }

    public int getLeftCount() {
        return leftCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Comparator<Node> getComparator() {
        return new NodeWeightComparator();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (weight != node.weight) return false;
        if (code != null ? !code.equals(node.code) : node.code != null) return false;
        if (!label.equals(node.label)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                '}';
    }

    /**
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     */
    @Override
    public int compareTo(Node o) {
        if (o == null)
            throw new NullPointerException();

        return (label.compareTo(o.getLabel()));
    }

    /**
     * Utility method to avoid <code>a.compareTo(b) &lt; 0</code> style code
     *
     * @param o
     * @return
     * @throws NullPointerException if the specified object is null
     */
    public boolean lessThan(Node o) {
        if (o == null)
            throw new NullPointerException();

        return (this.compareTo(o) < 0);
    }

    /**
     * Private inner class of <code>Node</code> to provide a means to sort Nodes based on weight
     */
    private static class NodeWeightComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}

