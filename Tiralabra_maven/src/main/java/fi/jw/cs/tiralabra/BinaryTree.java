package fi.jw.cs.tiralabra;

import java.util.Arrays;

/**
 * Simple binary tree implementation with <code>Node</code> instances.
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {

    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Equality is based ultimately on the keyset of each tree.
     *
     * @param o other BinaryTree
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTree that = (BinaryTree) o;


        if (root != null ? !root.equals(that.root) : that.root != null) return false;

        String[] keys = keys();
        String[] other = that.keys();
        if (keys.length != other.length) return false;

        for (int i = 0; i < keys.length; i++)
            if (!keys[i].equals(other[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        return root != null ? Arrays.deepHashCode(keys()) : 0;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }


    public boolean isEmpty() {
        return (getRoot() == null);
    }


    public String[] keys() {
        return keys(root);
    }

    private String[] keys(Node node) {
        if (node == null)
            return new String[0];


        String[] leftKeys = keys(node.getLeft());
        String[] rightKeys = keys(node.getRight());

        int numKeys = 1 + leftKeys.length + rightKeys.length;
        String[] keys = new String[numKeys];
        int i = 0;
        keys[i++] = node.getLabel();

        for (int l = 0; l < leftKeys.length; l++)
            keys[i++] = leftKeys[l];

        for (int l = 0; l < rightKeys.length; l++)
            keys[i++] = rightKeys[l];

        return keys;
    }

    public void insert(Node child) {
        if (root == null) {
            root = child;
        } else {
            Node current = root;
            boolean success = false;
            if (current == null) {
                throw new NullPointerException("BinaryTree.insert() current was null before loop");
            }
            while (!success) {
                if (current == null) {
                    throw new NullPointerException("BinaryTree.insert() current was null");
                }

                if (child.lessThan(current)) {
                    if (current.getLeft() == null) {
                        current.setLeft(child);
                        child.setParent(current);
                        success = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(child);
                        child.setParent(current);
                        success = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
    }

    public Node searchByLabel(String needle) {
        Node lostItem = null;
        Node n = new Node(needle);
        Node current = root;
        boolean found = false;

        while (!found && current != null) {
            if (n.compareTo(current) == 0) {
                lostItem = current;
                found = true;
            } else {
                if (n.lessThan(current)) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
        }

        return lostItem;
    }
}
