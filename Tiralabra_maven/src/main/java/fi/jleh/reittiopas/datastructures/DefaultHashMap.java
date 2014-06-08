package fi.jleh.reittiopas.datastructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DefaultHashMap<K, V> implements Map<K, V> {

	private final int m;
	private final double A = (Math.sqrt(5) - 1) / 2;
	private HashNode<V>[] array;
	
	private double fr(double x) {
		return x - (int) x; 
	}
	
	@SuppressWarnings("unchecked")
	public DefaultHashMap(int m) {
		this.m = m;
		this.array = new HashNode[m];
	}
	
	public DefaultHashMap() {
		this(11);
	}
	
	/**
	 * Generates hash code. This method is public for testing purposes.
	 * @param code
	 * @return
	 */
	public int calculateHash(int code) {
		return (int) (m * fr(A * code));
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {
		int code = key.hashCode();
		int index = calculateHash(code);
		
		HashNode<V> arrayNode = array[index];
		
		while (arrayNode != null && arrayNode.getCode() != code) {
			arrayNode = arrayNode.getNext();
		}
		
		if (arrayNode == null)
			return null;
		else
			return arrayNode.getObject();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		HashNode<V> hashNode = new HashNode<V>(key.hashCode(), value);
		int index = calculateHash(hashNode.getCode());
		
		if (array[index] == null)
			array[index] = hashNode;
		else {
			HashNode<V> arrayNode = array[index];
			
			while (arrayNode.getNext() != null) {
				arrayNode = arrayNode.getNext();
			}
			
			arrayNode.setNext(hashNode);
		}
		
		return value;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
