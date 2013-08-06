
package Structures.Hashtable;

import Utils.Iterator;
import Structures.LinkedList.LinkedList;

public class Hashtable<K,V> {
    private Object table[];
    private LinkedList<K> keySet;
    public Hashtable(){
        this.table=new Object[300];
        this.keySet=new LinkedList<K>();
        for(int i=0; i<this.table.length; i++){
            this.table[i]=new LinkedList<TableData<K,V>>();
        }
    }
    public Hashtable(int size){
        this.table=new Object[size];
        this.keySet=new LinkedList<K>();
        for(int i=0; i<this.table.length; i++){
            this.table[i]=new LinkedList<TableData<K,V>>();
        }
    }
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
    public void put(K key,V value){
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
    public LinkedList<K> keySet(){
        return this.keySet;
    }
}
