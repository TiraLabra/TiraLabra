

package Tietorakenteet;


public class OmaTreeNode<K, V> {
    private K key;
    private V value;
    private OmaTreeNode vasen;
    private OmaTreeNode oikea;
    
    public OmaTreeNode() {
        key = null;
        value = null;
        vasen = null;
        oikea = null;
    }
    
}
