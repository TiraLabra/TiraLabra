package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class Node {
    private char character;
    private int weight;
    private Node parent, left, right;


    public Node(char character, int weight) {
        this(character, weight, null, null, null);
    }

    public Node(char character, int weight, Node parent, Node left, Node right) {
        this.character = character;
        this.weight = weight;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }


    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
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

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public static Comparator<Node> getComparator() {
        return new NodeWeightComparator();
    }

    static class NodeWeightComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}

