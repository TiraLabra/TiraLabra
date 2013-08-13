package fi.jw.cs.tiralabra;

/**
 * Placeholder for the appropriate data structure.
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

    public String[] keySet() {
        return keySet(root);
    }

    private String[] keySet(Node node) {
        if (node == null)
            return new String[0];


        String[] leftKeys = keySet(node.getLeft());
        String[] rightKeys = keySet(node.getLeft());

        int numKeys = 1 + leftKeys.length + rightKeys.length;
        String[] keys = new String[numKeys];
        int i = 0;
        keys[i++] = node.getLabel();

        for (int l = 0; l < leftKeys.length; l++)
            keys[i++] = leftKeys[l];

        for (int l = 0; l < rightKeys.length; l++)
            keys[i++] = leftKeys[l];

        return keys;
    }

    public void insert(Node child) {
        if (root == null) {
            root = child;
        } else {
            Node current = root;
            boolean success = false;
            while (!success) {

                if (child.lessThan(current)) {
                    if (current.getLeft() != null) {
                        current.setLeft(child);
                        child.setParent(current);
                        success = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() != null) {
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
