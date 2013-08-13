package fi.jw.cs.tiralabra;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class BinaryTreeMap {
    BinaryTree bt;

    public BinaryTreeMap() {
        bt = new BinaryTree();
    }

    public String[] keySet() {
        return bt.keySet();
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

    public String get(String key) {
        Node n = bt.searchByLabel(key);
        return (n == null ? null : n.getCode());
    }
}
