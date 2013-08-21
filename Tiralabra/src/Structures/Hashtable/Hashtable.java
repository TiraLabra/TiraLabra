
package Structures.Hashtable;

import Utils.Iterator;
import Structures.LinkedList.LinkedList;

/**
 * Hash table structure
 */
public class Hashtable<K,V> {
    private Object table[];
    private LinkedList<K> keySet;
    /**
     * Creates a hash table
     */
    public Hashtable(){
        this.table=new Object[300];
        this.keySet=new LinkedList<K>();
        for(int i=0; i<this.table.length; i++){
            this.table[i]=new LinkedList<TableData<K,V>>();
        }
    }
    /**
     * Creates a hash table of certain size
     */
    public Hashtable(int size){
        this.table=new Object[size];
        this.keySet=new LinkedList<K>();
        for(int i=0; i<this.table.length; i++){
            this.table[i]=new LinkedList<TableData<K,V>>();
        }
    }
    /**
     * Finds the object in the hash table that has the given key
     * @param Key of the object
     * @return Object connected with the given key
     */
    public V get(K key){
        Iterator<TableData<K,V>> i = new Iterator<TableData<K,V>>((LinkedList<TableData<K,V>>)table[(Math.abs(key.hashCode())%this.table.length)]);
        while(i.hasNext()){
            TableData<K,V> data = i.getNext();
            if(data.getKey().hashCode()==key.hashCode()){
                return data.getValue();
            }
        }
        return null;
    }
    /**
     * Removes the given key from the hash table
     */
    public void remove(K key){
        int location = (Math.abs(key.hashCode())%this.table.length);
        LinkedList<TableData<K,V>> keys = (LinkedList<TableData<K,V>>)this.table[location];
        Iterator<TableData<K,V>> i = new Iterator<TableData<K,V>>(keys);
        while(i.hasNext()){
            TableData<K,V> data = i.getNext();
            if(data.getKey().hashCode()==key.hashCode()){
                i.remove();
                return;
            }
        }
        Iterator<K> n = new Iterator<K>(this.keySet);
        while(n.hasNext()){
            K currentKey = n.getNext();
            if(currentKey.hashCode()==key.hashCode()){
                n.remove();
                return;
            }
        }
    }
    /**
     * Adds an object connected with the given key to the hash table
     */
    public void put(K key,V value){
        manage();
        LinkedList<TableData<K,V>> list = (LinkedList<TableData<K,V>>)table[(Math.abs(key.hashCode())%this.table.length)];
        if(list.isEmpty()){
            list.add(new TableData<K,V>(key,value));
            this.keySet.add(key);
            return;
        }
        Iterator<TableData<K,V>> i = new Iterator<TableData<K,V>>(list);
        while(i.hasNext()){
            TableData<K,V> m = i.getNext();
            if(m.getKey().hashCode()==key.hashCode()){
                m.setValue(value);
                return;
            }
        }
        list.add(new TableData<K,V>(key,value));
        this.keySet.add(key);
    }
    /**
     * If keys' share in the table becomes too large, this method will call for the extend method to make the table bigger
     */
    private void manage(){
        if(this.keySet.size()/this.table.length>1){
            extend();
        }
    }
    private void extend(){
        Object[] newTable = new Object[this.table.length*2];
        for(int i=0; i<newTable.length; i++){
            newTable[i]=new LinkedList<TableData<K,V>>();
        }
        LinkedList<TableData<K,V>> data = new LinkedList<TableData<K,V>>();
        Iterator<K> keyIterator = new Iterator<K>(this.keySet);
        while(keyIterator.hasNext()){
            K key = keyIterator.getNext();
            V value = get(key);
            data.add(new TableData<K,V>(key,value));
        }
        this.table=newTable;
        Iterator<TableData<K,V>> dataIterator = new Iterator<TableData<K,V>>(data);
        while(dataIterator.hasNext()){
            TableData<K,V> currentData = dataIterator.getNext();
            put(currentData.getKey(),currentData.getValue());
        }
    }
    /**
     * Checks if hash table contains the given key
     */
    public boolean containsKey(K key){
        LinkedList<TableData<K,V>> list = (LinkedList<TableData<K,V>>)table[(Math.abs(key.hashCode())%this.table.length)];
        if(list.isEmpty()){
            return false;
        }
        Iterator<TableData<K,V>> i = new Iterator<TableData<K,V>>(list);
        while(i.hasNext()){
            TableData<K,V> m = i.getNext();
            if(m.getKey().hashCode()==key.hashCode()){
                return true;
            }
        }
        return false;
    }
    /**
     * Returns all the keys in the hash table
     */
    public LinkedList<K> keySet(){
        return this.keySet;
    }
}
