package fi.jleh.reittiopas.datastructures;

/**
 * Object that is stored in hashtable.
 * @param <T>
 */
public class HashNode<T> {

	private int code;
	private T object;
	private HashNode<T> next;
	
	public HashNode(int code, T object) {
		this.code = code;
		this.object = object;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public HashNode<T> getNext() {
		return next;
	}

	public void setNext(HashNode<T> next) {
		this.next = next;
	}
	
	
}
