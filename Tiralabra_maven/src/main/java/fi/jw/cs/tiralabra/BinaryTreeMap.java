package fi.jw.cs.tiralabra;

/**
 * A String->String map with a binary tree as the data structure. Does not implement full a <code>java.util.Map</code> class
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class BinaryTreeMap {
    BinaryTree bt;

    public BinaryTreeMap() {
        bt = new BinaryTree();
    }

    public String[] keys() {
        return bt.keys();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTreeMap that = (BinaryTreeMap) o;

        if (!bt.equals(that.bt)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return bt.hashCode();
    }

    @Override
    public String toString() {
        return "BinaryTreeMap{" +
                "bt=" + bt +
                '}';
    }

    public void put(String key, String value) {
        Node n = bt.searchByLabel(key);
        if (n == null) {
            Node item = new Node();
            item.setLabel(key);
            item.setCode(value);
            bt.insert(item);
        } else {
            n.setCode(value);
        }
    }

    public boolean containsKey(String key) {
        Node n = bt.searchByLabel(key);
        return (n != null);
    }

    public String get(String key) {
        Node n = bt.searchByLabel(key);
        return (n == null ? null : n.getCode());
    }

    public boolean isEmpty() {
        return bt.isEmpty();
    }
}
