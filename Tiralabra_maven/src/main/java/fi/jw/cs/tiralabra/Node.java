package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class Node {
    private String label;
    private String code;
    private int weight;
    private Node parent;
    private Node left;
    private Node right;


    public Node(String label, int weight) {
        this(label, weight, null, null, null);
    }

    public Node(String label, int weight, Node parent, Node left, Node right) {
        this.label = label;
        this.weight = weight;
        this.parent = parent;
        this.left = left;
        this.right = right;
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

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
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

    private static class NodeWeightComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}

