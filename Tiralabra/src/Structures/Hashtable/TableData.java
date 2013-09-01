package Structures.Hashtable;

public class TableData<K,V> {
    private K key;
    private V value;
    public TableData(K key, V value){
        this.key=key;
        this.value=value;
    }
    public K getKey(){
        return this.key;
    }
    public V getValue(){
        return this.value;
    }
    public void setValue(V value){
        this.value=value;
    }
}
